package CarCrashsim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.ui.Button;

public class CarCrashSim {
    private CanvasWindow canvas;
    private CarManager carM;
    private CollisionManager collisionM;
    private double timeIncrement;
    private int timeIndex;

    private static final double[] TIME_OPTIONS = {0.1, 0.05, 0.02, 0.01};
    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 800;
    private static final Random rand = new Random();

    public CarCrashSim() {
        canvas  = new CanvasWindow("Car Crash Simulator 2020", CANVAS_WIDTH, CANVAS_HEIGHT);
        carM = new CarManager();
        collisionM =  new CollisionManager(canvas);
        timeIncrement = TIME_OPTIONS[1];

        Button resetButton = createResetButton();

        Button incrementButton = createIncButton();

        resetButton.onClick(() -> reset());

        incrementButton.onClick(() -> changeTimeIncrement());

        reset();

        canvas.animate(() -> {
            collisionM.checkForCollisions();
            carM.moveAllCars(timeIncrement);
            return;
        });
    }

    private Button createIncButton() {
        Button button = new Button("Change time increment");
        button.setPosition(50, 85);
        canvas.add(button);
        return button;
    }

    private Button createResetButton() {
        Button button = new Button("Reset");
        button.setPosition(50, 50);
        canvas.add(button);
        return button;
    }

    private void changeTimeIncrement() {
        timeIndex += 1;
        timeIndex = timeIndex % 4;
        timeIncrement = TIME_OPTIONS[timeIndex];
    }

    private void clear() {
        canvas.removeAll();
        carM.Reset();
        collisionM.Reset();
        Button resetButton  = createResetButton();
        resetButton.onClick(() -> reset());
        Button incremenButton  = createIncButton();
        incremenButton.onClick(() -> changeTimeIncrement());
    }

    private void reset() {
        clear();

        ArrayList<Car> carList = new ArrayList<Car>();

        List<Double> pos = getRandPos(List.of(0.0));

        ArrayList<Double> randPos = randomizeStart(pos);

        Car car = generateCar(randPos.get(0), randPos.get(1), randPos.get(2), randPos.get(3));
        carList.add(car);
        canvas.add(car.getGraphics());

        List<Double> pos2 = getRandPos(pos);

        ArrayList<Double> randPos2 = randomizeStart(pos2);

        Car car2 = generateCar(randPos2.get(0), randPos2.get(1), randPos2.get(2), randPos2.get(3));
        carList.add(car2);
        canvas.add(car2.getGraphics());
        
        collisionM.addCars(carList);
        carM.addCars(carList);
    }

    private ArrayList<Double> randomizeStart(List<Double> list) {
        ArrayList<Double> randList = new ArrayList<>();
        for (Double num : list) {
            num *= (1 + (rand.nextDouble() - 0.5) * 0.2);
            randList.add(num);
        }
        return randList;
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
            positions.add("Top left");
            positions.add("Top right");
            positions.add("Bottom left");
            positions.add("Bottom right");
            xVals.put("Left far", List.of(100.0, 400.0, 30.0, 0.0));
            xVals.put("Right far", List.of(700.0, 400.0, -30.0, 0.0));
            xVals.put("Top far", List.of(400.0, 100.0, 0.0, 30.0));
            xVals.put("Bottom far", List.of(400.0, 700.0, 0.0, -30.0));
            xVals.put("Top left", List.of(100.0, 100.0, 30.0 * 1.2, 30.0 * 1.2));
            xVals.put("Top right", List.of(700.0, 100.0, -30.0 * 1.2, 30.0 * 1.2));
            xVals.put("Bottom left", List.of(100.0, 700.0, 30.0 * 1.2, -30.0 * 1.2));
            xVals.put("Bottom right", List.of(700.0, 700.0, -30.0 * 1.2, -30.0 * 1.2));
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