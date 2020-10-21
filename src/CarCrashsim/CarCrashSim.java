package CarCrashsim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import edu.macalester.graphics.CanvasWindow;

public class CarCrashSim {
    private CanvasWindow canvas;
    private CarManager carM;
    private CollisionManager collisionM;

    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 800;
    private static final double TIME_INCERMENT = 0.04;
    private static final Random rand = new Random();

    public CarCrashSim() {
        canvas  = new CanvasWindow("Car Crash Simulator 2020", CANVAS_WIDTH, CANVAS_HEIGHT);
        carM = new CarManager();
        collisionM =  new CollisionManager(canvas);

        ArrayList<Car> carList = new ArrayList<>();

        // SmartCar testCar2 = new SmartCar(0, 120, 420, 200); 
        // SmartCar testCar3 = new SmartCar(0, 0, 400, 600); 
        // carList.add(testCar2);
        // carList.add(testCar3);
        // canvas.add(testCar2.getGraphics());
        // canvas.add(testCar3.getGraphics());

        // Sedan testSedan = new Sedan(20, 0, 400, 400);
        // Sedan testSedan2 = new Sedan(0, 0, 400, 600);
        // carList.add(testSedan);
        // carList.add(testSedan2);
        // canvas.add(testSedan.getGraphics());
        // canvas.add(testSedan2.getGraphics());

        // Truck testCar = new Truck(400, 0, 100, 400);
        // Truck testTruck = new Truck(-20, 0, 600, 400);
        // carList.add(testCar);
        // carList.add(testTruck);
        // canvas.add(testCar.getGraphics());
        // canvas.add(testTruck.getGraphics());

        List<Double> pos = getRandPos(List.of(0.0));

        Car car = generateCar(pos.get(0), pos.get(1), pos.get(2), pos.get(3));
        carList.add(car);
        canvas.add(car.getGraphics());

        List<Double> pos2 = getRandPos(pos);

        Car car2 = generateCar(pos2.get(0), pos2.get(1), pos2.get(2), pos2.get(3));
        carList.add(car2);
        canvas.add(car2.getGraphics());
        
        collisionM.addCars(carList);
        carM.addCars(carList);

        canvas.animate(() -> {
            collisionM.checkForCollisions();
            carM.moveAllCars(TIME_INCERMENT);
            return;
        });
    }

    private List<Double> getRandPos(List<Double> repeat) {
        List<Double> toReturn = repeat;
        while (toReturn.equals(repeat)) {
            Map<String, List<Double>> xVals = new HashMap<>();
            ArrayList<String> positions = new ArrayList<>();
            positions.add("Left far");
            positions.add("Right far");
            positions.add("Top far");
            positions.add("Bottom far");
            xVals.put("Left far", List.of(100.0, 400.0, 30.0, 0.0));
            xVals.put("Right far", List.of(700.0, 400.0, -30.0, 0.0));
            xVals.put("Top far", List.of(400.0, 100.0, 0.0, 30.0));
            xVals.put("Bottom far", List.of(400.0, 700.0, 0.0, -30.0));
            toReturn = xVals.get(positions.get(rand.nextInt(positions.size())));
        }
        return toReturn;
    }

    private Car generateCar(double x, double y, double xVel, double yVel) {
        Map<String, Car> carTypes = new HashMap<>();
        carTypes.put("Truck", new Truck(xVel, yVel, x, y));
        carTypes.put("Sedan", new Sedan(xVel, yVel, x, y));
        carTypes.put("Smartcar", new SmartCar(xVel, yVel, x, y));
        return carTypes.get(randCarType());
    }

    private String randCarType() {
        List<String> types = List.of("Truck", "Sedan", "Smartcar");
        return types.get(rand.nextInt(types.size()));
    }

    public static void main(String[] args) {
        new CarCrashSim();
    }

}