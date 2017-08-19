package example.client;

import com.reforms.ann.TargetConstructor;

public class Car {

    private int number;

    private String color;

    public Car() {
        this(0);
    }

    @TargetConstructor
    public Car(int number) {
        this(number, "white");
    }

    public Car(int number, String color) {
        this.number = number;
        this.color = color;
    }
}
