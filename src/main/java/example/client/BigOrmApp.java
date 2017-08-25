package example.client;

import com.reforms.orm.OrmDao;

import java.sql.Connection;
import java.sql.SQLException;

import test.H2DataSource;

public class BigOrmApp {

    public static void main(String[] args) {
        System.out.println("load big orm begining");
        try (H2DataSource h2ds = new H2DataSource("example.bigorm")) {
            h2ds.invoke("bigorm.sql", BigOrmApp.class);
            IBigOrmDao biformsDao = OrmDao.createDao(h2ds, IBigOrmDao.class);
            System.out.println(biformsDao.load(new int[] {1, 2, 3}));
        }
        System.out.println("load big orm ok");
    }

    public void workWithBigOrm() throws SQLException {
        try (Connection connection = resolveConnection()) {
            IBigOrmDao biformsDao = OrmDao.createDao(connection, IBigOrmDao.class);
            biformsDao.load(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        }
    }

    private Connection resolveConnection() {
        // code here to resolving your connection or connection holder
        return null;
    }
}
