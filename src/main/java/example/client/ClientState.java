package example.client;

import com.reforms.ann.TargetField;

public enum ClientState {

    ACTIVE(1),
    BLOCKED(2),
    DELETED(3);

    @TargetField
    private int state;

    ClientState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}