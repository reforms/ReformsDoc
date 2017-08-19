package example.client;

import com.reforms.ann.TargetDao;
import com.reforms.ann.TargetFilter;
import com.reforms.ann.TargetQuery;

import java.util.List;

@TargetDao(name = "It's dao for client. Table: clients", orm = Client.class)
public interface IClientDao {

    // All records from clients table will automatically converted to List<Client>
    @TargetQuery("SELECT id," +
                 "       name," +
                 "       state " +
                 "FROM clients")
    List<Client> loadAllClients();

    @TargetQuery("INSERT INTO clients ( id,  name,  state) " +
                 "VALUES              (:id, :name, :state)")
    void saveClient(@TargetFilter Client client);
}