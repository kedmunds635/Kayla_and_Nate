package CarCrashsim;

import java.util.ArrayList;

import edu.macalester.graphics.Point;

public class CollisionManager {
    private ArrayList<Car> carList;

    public CollisionManager() {
        carList = new ArrayList<Car>();
    }

    public void addCars(ArrayList<Car> cars) {
        for (Car car : cars) {
            carList.add(car);
        }
    }

    public void checkForCollisions() {
        for (Car car : carList) {
            for (Car car2 : carList) {
                if (car2 != car) {
                    ArrayList<Point> points = car2.getPoints();
                    for (Point point : points) {
                        if (car.checkPointForCollision(point)) {
                            System.out.print("collision");
                            handleCollision(car, car2);
                        }
                    }
                }
            }
        }
    }

    private void handleCollision(Car car, Car car2) {
        return;
    }
}
