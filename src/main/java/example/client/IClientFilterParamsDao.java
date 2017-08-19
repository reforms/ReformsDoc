package example.client;

import com.reforms.ann.TargetFilter;
import com.reforms.ann.TargetQuery;

import java.util.List;

public interface IClientFilterParamsDao {
    // Example: N1
    @TargetQuery("SELECT id, name, state " +
                 "FROM clients " +
                 "WHERE name = :name AND state = :state")
    Client filterClientByIdShort(String name, ClientState state);

    // Example: N2
    @TargetQuery("SELECT id, name, state " +
                 "FROM clients " +
                 "WHERE name = :name AND state = :state")
    Client filterClientByIdFull(@TargetFilter("name") String name, @TargetFilter("state") ClientState state);

    // Example: N3
    @TargetQuery("SELECT id, name, state " +
                 "FROM clients " +
                 "WHERE name LIKE :name AND " +
                 "      state = :state")
    List<Client> filterClientsRequired(@TargetFilter ClientFilter filter);

    // Example: N4
    @TargetQuery("SELECT id, name, state " +
                 "FROM clients " +
                 "WHERE id = :clientId")
    String loadClientName(long clientId, @TargetFilter(columnFilter = true) int[] columns);

    static class ClientFilter {

        private final String name;

        private final ClientState state;

        public ClientFilter(String likeName, ClientState state) {
            this.name = likeName;
            this.state = state;
        }
    }
}