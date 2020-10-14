package CarCrashsim;

import edu.macalester.graphics.CanvasWindow;

public class CarCrashSim {
    private CanvasWindow canvas;
    private CarManager carM;
    private CollisionManager collisionM;

    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 800;
    private static final double TIME_INCERMENT = 0.1;

    public CarCrashSim() {
        canvas  = new CanvasWindow("Car Crash Simulator 2020", CANVAS_WIDTH, CANVAS_HEIGHT);
        carM = new CarManager();
        collisionM =  new CollisionManager();

        SmartCar testCar = new SmartCar(10, 0, 100, 400);
        SmartCar testCar2 = new SmartCar(10, 50, 100, 100);
        Sedan testSedan = new Sedan(-20, 0, 700, 700);
        Truck testTruck = new Truck(-10, -10, 700, 500);
        carM.addCar(testCar);
        carM.addCar(testCar2);
        carM.addCar(testSedan);
        carM.addCar(testTruck);
        canvas.add(testCar.getGraphics());
        canvas.add(testCar2.getGraphics());
        canvas.add(testSedan.getGraphics());
        canvas.add(testTruck.getGraphics());

        canvas.animate(() -> {
            carM.moveAllCars(TIME_INCERMENT);
            return;
        });
    }

    public static void main(String[] args) {
        new CarCrashSim();
    }
}
