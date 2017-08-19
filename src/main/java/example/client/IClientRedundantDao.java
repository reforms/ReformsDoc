package example.client;

import com.reforms.ann.TargetQuery;

import java.util.List;

public interface IClientRedundantDao {

    @TargetQuery(query = "SELECT id, name, state FROM clients",
                 orm = Car.class)
    List<Client> loadAllClients();
}