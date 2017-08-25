package example.client;

import com.reforms.orm.OrmDao;

import example.client.IClientFilterParamsDao.ClientFilter;
import test.H2DataSource;

public class ClientLauncher {


    public static void main(String[] args) {
//        loadClients();
//        saveClient();
//        filterClients("ovy", null);
//        filterClients("ovy", ClientState.ACTIVE);
//        filterClients(null, ClientState.ACTIVE);
        loadClientsFil();
    }

    private static void loadClientsFil() {
        System.out.println("load clients begining");
        try (H2DataSource h2ds = new H2DataSource("example.client")) {
            h2ds.invoke("client.sql", ClientLauncher.class);
            IClientDao cliensDao = OrmDao.createDao(h2ds, IClientDao.class);
            System.out.println(cliensDao.loadClients());
        }
        System.out.println("load clients ok");
    }

    private static void loadClients() {
        System.out.println("load clients begining");
        try (H2DataSource h2ds = new H2DataSource("example.client")) {
            h2ds.invoke("client.sql", ClientLauncher.class);
            IClientDao cliensDao = OrmDao.createDao(h2ds, IClientDao.class);
            System.out.println(cliensDao.loadAllClients());
        }
        System.out.println("load clients ok");
    }

    private static void saveClient() {
        System.out.println("save begining");
        try (H2DataSource h2ds = new H2DataSource("example.client")) {
            h2ds.invoke("client.sql", ClientLauncher.class);
            IClientDao cliensDao = OrmDao.createDao(h2ds, IClientDao.class);
            Client client = new Client();
            client.setId(2);
            client.setName("John Visky");
            client.setState(ClientState.ACTIVE);
            cliensDao.saveClient(client);
        }
        System.out.println("save ok");
    }

    private static void filterClients(String name, ClientState state) {
        System.out.println("filtering clients begining");
        try (H2DataSource h2ds = new H2DataSource("example.client")) {
            h2ds.invoke("client.sql", ClientLauncher.class);
            IClientFilterParamsDao cliensDao = OrmDao.createDao(h2ds, IClientFilterParamsDao.class);
            System.out.println(cliensDao.filterClientsRequired(new ClientFilter(name, state)));
        }
        System.out.println("filtering clients ok");
    }

    private static H2DataSource resolveConnection() {
        //return ... code here to resolve your connection or connection hodler instance
        // in my case its new H2DataSource("example.client")
        return new H2DataSource("example.client");
    }
}
