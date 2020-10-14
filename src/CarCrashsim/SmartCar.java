package CarCrashsim;

import java.util.ArrayList;

import edu.macalester.graphics.*;
import edu.macalester.graphics.Rectangle;

public class SmartCar implements Car{
    private GraphicsGroup group;
    private double dx;
    private double dy;
    private double x;
    private double y;

    private static final double MASS = 10;
    private static final double LENGTH = 50;
    private static final double WIDTH = 40;

    public SmartCar(double dx, double dy, double x, double y) {
        this.dx = dx;
        this.dy = dy;
        this.x = x;
        this.y = y;
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
        group.add(rMirror);
        group.add(lMirror);
        group.add(carShape);
        group.add(windShield);
    }

    private ArrayList<Point> getWindPoints() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(getX() + 7, getY() + 3));
        points.add(new Point(getX() + 7, getY() + 37));
        points.add(new Point(getX() + 22, getY() + 37));
        points.add(new Point(getX() + 22, getY() + 3));
        return points;
    }

        private ArrayList<Point> getRMirrorPoints() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(getX() + 14, getY()));
        points.add(new Point(getX() + 18, getY()));
        points.add(new Point(getX() + 21, getY() - 8));
        points.add(new Point(getX() + 17, getY() - 8));
        return points;
    }

        private ArrayList<Point> getLMirrorPoints() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(getX() + 14, getY() + 40));
        points.add(new Point(getX() + 18, getY() + 40));
        points.add(new Point(getX() + 21, getY() + 48));
        points.add(new Point(getX() + 17, getY() + 48));
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
}