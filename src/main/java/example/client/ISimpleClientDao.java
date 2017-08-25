package example.client;

import com.reforms.ann.TargetQuery;

import java.util.List;

public interface ISimpleClientDao {
    /**
     * CREATE TABLE clients (
     *  id bigint NOT NULL,
     *  name character varying(127) NOT NULL,
     *  state int NOT NULL);
     */
    @TargetQuery("SELECT id, " +   // link with field 'id'
                 "       name, " + // link with field 'name'
                 "       state " + // link with field 'state'
                 "FROM clients")
    List<Client> allClients();
}
