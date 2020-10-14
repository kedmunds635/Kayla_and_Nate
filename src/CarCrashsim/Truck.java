package CarCrashsim;

import edu.macalester.graphics.*;
import java.util.ArrayList;

public class Truck implements Car{
    private GraphicsGroup group;
    private double dx;
    private double dy;
    private double x;
    private double y;

    private static final double MASS = 40;
    private static final double LENGTH = 90;
    private static final double WIDTH = 45;

    public Truck(double dx, double dy, double x, double y) {
        this.dx = dx;
        this.dy = dy;
        this.y = y;
        this.x = x;
        group = new GraphicsGroup();
        buildGraphics();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public double getMass() {
        return MASS;
    }

    public boolean checkForCollision() {
        return false;
    }

    public void move(double dt) {
        group.setPosition(group.getX() + dt * dx, group.getY() + dt * dy);
    }

    public GraphicsGroup getGraphics() {
        return group;
    }

    public void buildGraphics() {
        Path carShape = new Path(getCarShapePoints());
        Path windShield = new Path(getWindPoints());
        Path rMirror = new Path(getRMirrorPoints());
        Path lMirror = new Path(getLMirrorPoints());
        Path bed = new Path(getTruckBedPoints());
        group.add(bed);
        group.add(rMirror);
        group.add(lMirror);
        group.add(carShape);
        group.add(windShield);
    }

    private ArrayList<Point> getWindPoints() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(getX() + 9, getY() + 4));
        points.add(new Point(getX() + 9, getY() + 41));
        points.add(new Point(getX() + 25, getY() + 41));
        points.add(new Point(getX() + 25, getY() + 4));
        return points;
    }

    private ArrayList<Point> getRMirrorPoints() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(getX() + 16, getY()));
        points.add(new Point(getX() + 20, getY()));
        points.add(new Point(getX() + 23, getY() - 8));
        points.add(new Point(getX() + 19, getY() - 8));
        return points;
    }

    private ArrayList<Point> getLMirrorPoints() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(getX() + 16, getY() + 45));
        points.add(new Point(getX() + 20, getY() + 45));
        points.add(new Point(getX() + 23, getY() + 53));
        points.add(new Point(getX() + 19, getY() + 53));
        return points;
    }

    private ArrayList<Point> getCarShapePoints() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(getX(), getY()));
        points.add(new Point(getX(), getY() + WIDTH));
        points.add(new Point(getX() + LENGTH, getY() + WIDTH));
        points.add(new Point(getX() + LENGTH, getY()));
        return points;
    }

    private ArrayList<Point> getTruckBedPoints() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(getX() + LENGTH - 41, getY() + 4));
        points.add(new Point(getX() + LENGTH - 4, getY() + 4));
        points.add(new Point(getX() + LENGTH - 4, getY() + 41));
        points.add(new Point(getX() + LENGTH - 41, getY() + 41));
        return points;
    }
}
