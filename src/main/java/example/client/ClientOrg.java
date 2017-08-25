package example.client;

// You can use lombok to escape boilerplate code like get/set/toString
// @lombok.Data
public class ClientOrg {
    private long orgId;
    private String orgName;
    private ClientState orgState;
    @Override
    public String toString() {
        return "ClientOrg [orgId=" + orgId + ", orgName=" + orgName + ", orgState=" + orgState + "]";
    }
}