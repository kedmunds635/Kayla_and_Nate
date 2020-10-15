package CarCrashsim;

import java.util.ArrayList;

public class CarManager {
    private ArrayList<Car> carList;

    public CarManager() {
        carList = new ArrayList<Car>();
    }

    public void addCars(ArrayList<Car> cars) {
        for (Car car : cars) {
            carList.add(car);
        }
    }

    public void moveAllCars(double dt) {
        for (Car car : carList) {
            car.move(dt);
        }
    }
}
