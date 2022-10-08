package racingcarwinner;

import java.util.Map;

public class Car {

    private String carName;
    private int position;
    private static final int DEFAULT_POSITION = 0;

    public Car(String carName) {
        this.carName = carName;
        this.position = DEFAULT_POSITION;
    }

    public String getCarName() {
        return this.carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
