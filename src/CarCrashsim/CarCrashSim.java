package CarCrashsim;

import java.util.ArrayList;

import edu.macalester.graphics.CanvasWindow;

public class CarCrashSim {
    private CanvasWindow canvas;
    private CarManager carM;
    private CollisionManager collisionM;

    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 800;
    private static final double TIME_INCERMENT = 0.03;

    public CarCrashSim() {
        canvas  = new CanvasWindow("Car Crash Simulator 2020", CANVAS_WIDTH, CANVAS_HEIGHT);
        carM = new CarManager();
        collisionM =  new CollisionManager();

        ArrayList<Car> carList = new ArrayList<>();

        // SmartCar testCar2 = new SmartCar(-50, 0, 700, 600); 
        // SmartCar testCar3 = new SmartCar(0, 0, 300, 600); 
        // carList.add(testCar2);
        // carList.add(testCar3);
        // canvas.add(testCar2.getGraphics());
        // canvas.add(testCar3.getGraphics());

        Sedan testSedan = new Sedan(20, 0, 400, 400);
        Sedan testSedan2 = new Sedan(20, 20, 400, 100);
        carList.add(testSedan);
        carList.add(testSedan2);
        canvas.add(testSedan.getGraphics());
        canvas.add(testSedan2.getGraphics());

        // Truck testCar = new Truck(0, 0, 100, 400);
        // Truck testTruck = new Truck(-20, -20, 400, 700);
        // carList.add(testCar);
        // carList.add(testTruck);
        // canvas.add(testCar.getGraphics());
        // canvas.add(testTruck.getGraphics());
        
        collisionM.addCars(carList);
        carM.addCars(carList);

        canvas.animate(() -> {
            collisionM.checkForCollisions();
            carM.moveAllCars(TIME_INCERMENT);
            return;
        });
    }

    public static void main(String[] args) {
        new CarCrashSim();
    }

}