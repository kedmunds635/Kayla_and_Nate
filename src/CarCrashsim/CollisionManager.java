package CarCrashsim;

import java.util.ArrayList;

import javax.xml.xpath.XPath;

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
        double finalDx = calculateFinalDx(car, car2);

        car.setDx(finalDx);
        car2.setDx(finalDx);

        double finalDy = calculateFinalDy(car, car2);

        car.setDy(finalDy);
        car2.setDy(finalDy);
    }

    private double calculateFinalDx(Car car, Car car2) {
        double xMomentum1 = car.getMass() * car.getDx();
        double xMomentum2 = car2.getMass() * car2.getDx();
        double finalXMomentum = xMomentum1 + xMomentum2;
        return finalXMomentum / (car.getMass() + car2.getMass());
    }

    private double calculateFinalDy(Car car, Car car2) {
        double yMomentum1 = car.getMass() * car.getDy();
        double yMomentum2 = car2.getMass() * car2.getDy();
        double finalYMomentum = yMomentum1 + yMomentum2;
        return finalYMomentum / (car.getMass() + car2.getMass());
    }

    private void calculateRotationalV(Car car, Car car2) {
        Vector carVel = new Vector(car.getDx(), car.getDy());
        Vector car2Vel = new Vector(car2.getDx(), car2.getDy());
        Vector rVel = carVel.subtract(car2Vel);
        if (!carsApproching(rVel, car, car2)) {
            return;
        }

        double al = 1 / car.getMass() + Math.pow(cross(carVel, rVel), 2) + car.getMassOfInertia() +
            1 / car2.getMass() + Math.pow(cross(car2Vel, rVel), 2) + car2.getMassOfInertia();
        Vector j = rVel.multiply(-1.0).divide(al);
    }

    private double cross(Vector v1, Vector v2) {
        return v1.getDx() * v2.getDy() - v1.getDy() * v2.getDx();
        
    }

    private boolean carsApproching(Vector r, Car car, Car car2) {
        if (r.getDx() > 0 || car2.getX() < car.getX()) {
            return true;
        }
        if (r.getDy() > 0 || car2.getY() < car.getY()) {
            return true;
        }
        if (r.getDx() < 0 || car2.getX() > car.getX()) {
            return true;
        }
        if (r.getDy() < 0 || car2.getY() > car.getY()) {
            return true;
        }
        return false;
    }

// var vp = a.vel + cross(a.r_vel, a.r) - b.vel - cross(b.r_vel, b.r);
// var vp_p = dot(vp,n); // negative val = moving towards each other
// if (vp_p >= 0) { // do they move apart?
//     return false;
// }

// // normal impulse
// var j = - e * vp_p / (
//             1/a.mass + cross(a.r,n)^2 / a.i +
//             1/b.mass + cross(b.r,n)^2 / b.i
//         );
// var jn = j * n;
// //
// a.vel = a.vel + jp / a.mass;
// a.r_vel = a.r_vel + cross(a.r,jn) / a.i;
// b.vel = b.vel - jp / b.mass;
// b.r_vel = b.r_vel - cross(b.r,jn) / b.i;
}
