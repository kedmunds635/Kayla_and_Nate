package CarCrashsim;

import java.util.ArrayList;

import edu.macalester.graphics.CanvasWindow;

public class CarCrashSim {
    private CanvasWindow canvas;
    private CarManager carM;
    private CollisionManager collisionM;

    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 800;
    private static final double TIME_INCERMENT = 1;

    public CarCrashSim() {
        canvas  = new CanvasWindow("Car Crash Simulator 2020", CANVAS_WIDTH, CANVAS_HEIGHT);
        carM = new CarManager();
        collisionM =  new CollisionManager();

        SmartCar testCar = new SmartCar(0, 0, 100, 400);
        // SmartCar testCar2 = new SmartCar(-60, -20, 700, 600);
        // Sedan testSedan = new Sedan(20, 0, 100, 400);
        // Truck testTruck = new Truck(0, -20, 400, 700);
        ArrayList<Car> carList = new ArrayList<>();
        carList.add(testCar);
        // carList.add(testCar2);
        // carList.add(testSedan);
        // carList.add(testTruck);

        testCar.setRVelocity(new Vector(0, 2));;
        
        collisionM.addCars(carList);
        carM.addCars(carList);

        canvas.add(testCar.getGraphics());
        // canvas.add(testCar2.getGraphics());
        // canvas.add(testSedan.getGraphics());
        // canvas.add(testTruck.getGraphics());

        canvas.animate(() -> {
            collisionM.checkForCollisions();
            carM.moveAllCars(TIME_INCERMENT);
            System.out.println(testCar.getRVel());
            return;
        });
    }

    public static void main(String[] args) {
        new CarCrashSim();
    }

}