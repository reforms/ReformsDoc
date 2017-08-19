package example.client;

// You can use lombok to escape boilerplate code like get/set/toString
// @lombok.Data
public class Client {

    private long id;

    private String name;

    // automatically converted from state column
    private ClientState state;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClientState getState() {
        return state;
    }

    public void setState(ClientState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Client(" + id + ", " + name + ", " + state + ")";
    }
}