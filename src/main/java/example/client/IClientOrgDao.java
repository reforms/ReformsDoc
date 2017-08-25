package example.client;

import com.reforms.ann.TargetQuery;

import java.util.List;

public interface IClientOrgDao {
    /**
     * CREATE TABLE clients (
     *  id bigint NOT NULL,
     *  name character varying(127) NOT NULL,
     *  state int NOT NULL);
     */
    @TargetQuery("SELECT id     AS orgId, "   + // link with field 'orgId'
                 "       name   AS orgName, " + // link with field 'orgName'
                 "       state  AS orgState " + // link with field 'orgState'
                 "FROM clients")
    List<ClientOrg> allClientsOrg();
    /** It's important. Output SQL: SELECT id, name, state FROM clients */

    @TargetQuery("SELECT id     AS aId:orgId, "   +       // link with field 'orgId'
                 "       name   AS aOrgName:orgName, " +  // link with field 'orgName'
                 "       state  AS aOrgState:orgState " + // link with field 'orgState'
                 "FROM clients")
    List<ClientOrg> clientsOrg();
    /** It's important. Output SQL:
     * SELECT id AS aId, name AS aOrgName, state AS aArgState FROM clients */
}