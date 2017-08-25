package example.client;

import com.reforms.orm.OrmDao;

import test.H2DataSource;

public class ClientOrgApp {

    public static void main(String[] args) {
        ClientOrgApp clientApp = new ClientOrgApp();
        clientApp.loadClients();
    }

    void loadClients() {
        System.out.println("load clients begining");
        try (H2DataSource h2ds = new H2DataSource("example.client")) {
            h2ds.invoke("client.sql", IClientOrgDao.class);
            IClientOrgDao cliensDao = OrmDao.createDao(h2ds, IClientOrgDao.class);
            System.out.println(cliensDao.clientsOrg());
        }
        System.out.println("load clients ok");
    }
}
