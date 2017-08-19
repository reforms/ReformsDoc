package example.client;

import com.reforms.orm.OrmDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ClientApp {

    public void doWorkWithClients() throws SQLException {
        try (Connection connection = resolveConnection()) {
            IClientDao cliensDao = OrmDao.createDao(connection, IClientDao.class);
            List<Client> clients = cliensDao.loadAllClients();
            clients.forEach(System.out::println);
        }
    }

    public void saveClient(long id, String name, ClientState state) throws SQLException {
        Client newClient = new Client();
        newClient.setId(id);
        newClient.setName(name);
        newClient.setState(state);
        try (Connection connection = resolveConnection()) {
            IClientDao cliensDao = OrmDao.createDao(connection, IClientDao.class);
            cliensDao.saveClient(newClient);
        }
    }

    private Connection resolveConnection() {
        // code here to resolving your connection or connection holder
        return null;
    }
}
