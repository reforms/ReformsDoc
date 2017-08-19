package test;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class H2DataSource implements Closeable {

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (Exception ex) {
            throw new IllegalStateException("Класс 'org.h2.Driver' не возможно проинициализировать", ex);
        }
    }

    private Connection connection;

    private String dbName;

    public H2DataSource(String dbName) {
        this.dbName = dbName;
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:h2:mem:" + dbName);
            } catch (SQLException sqle) {
                throw new IllegalStateException("Не возможно получить соединение к БД", sqle);
            }
        }
        return connection;
    }

    public void invoke(String scenarioName, Class<?> owner) {
        try {
            ScriptIterator si = ScriptIterator.getResourceIterator(scenarioName, owner);
            while (si.hasNext()) {
                String statement = si.next();
                invokeStatement(statement);
            }
        } catch (Exception e) {
            throw new IllegalStateException("Can not exec script " + scenarioName, e);
        }
    }


    public void invokeStatement(String query) {
        try (PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.execute();
        } catch (SQLException sqle) {
            throw new IllegalStateException("Невозможно выполнить запрос '" + query + "'", sqle);
        }
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new IllegalStateException("Не возможно закрыть соединение к БД", ex);
            }
        }
        connection = null;
    }

}
