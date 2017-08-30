package magic.plan;

import org.w3c.dom.Document;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class DocApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        WebView webview = new WebView();
        final WebEngine webengine = webview.getEngine();
        webengine.getLoadWorker().stateProperty().addListener(
            new ChangeListener<Worker.State>() {
                @Override
                public void changed(ObservableValue ov, Worker.State oldState, Worker.State newState) {
                    if (newState == Worker.State.SUCCEEDED) {
                        Document doc = webengine.getDocument();
                        try {
                            Transformer transformer = TransformerFactory.newInstance().newTransformer();
                            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
                            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                            transformer.setOutputProperty("omit-xml-declaration", "yes");
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            DOMSource domSource = new DOMSource(doc);
                            StreamResult streamResult = new StreamResult(new OutputStreamWriter(baos, "UTF-8"));
                            transformer.transform(domSource, streamResult);
                            String content = baos.toString("UTF-8");
                            String docsHtml = patch(content);
                            ByteArrayInputStream finalDocStream = new ByteArrayInputStream(docsHtml.getBytes("UTF-8"));
                            Files.copy(finalDocStream, new File("doc.html").toPath(), StandardCopyOption.REPLACE_EXISTING);

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
        webengine.load(new File("codes.html").toURI().toString());
        primaryStage.setScene(new Scene(webview, 1920, 800));
        primaryStage.show();
    }

    private static String patch(String content) throws IOException {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new StringReader(content))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                String pLine = line.trim().toLowerCase();
                if (pLine.startsWith("<meta")) {
                    result.append(line).append("\n");
                    result.append(loadCss("css/docs.css")).append("\n");
                    result.append(loadCss("css/default.min.css")).append("\n");
                } else if (pLine.startsWith("<link")) {
                    // escape all links
                } else if (pLine.startsWith("<script")) {
                    // escape all scripts
                } else {
                    result.append(line).append("\n");
                }
            }
        }
        return result.toString();
    }

    private static String loadCss(String relativePath) throws IOException {
        return "<style>\n" +
                new String (Files.readAllBytes(new File(relativePath).toPath()), "UTF-8") +
        "\n</style>";
    }
}
