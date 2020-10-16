package CarCrashsim;

import edu.macalester.graphics.*;
import java.util.ArrayList;

public class Sedan implements Car{
    private GraphicsGroup group;
    private double dx;
    private double dy;
    private Path carShape;

    private static final double MASS = 25;
    private static final double LENGTH = 90;
    private static final double WIDTH = 45;

    public Sedan(double dx, double dy, double x, double y) {
        this.dx = dx;
        this.dy = dy;
        group = new GraphicsGroup();
        buildGraphics();
        group.setPosition(x, y);
    }

    public double getMassOfInertia() {
        return 4.0/3.0 * WIDTH * LENGTH * (WIDTH*WIDTH + LENGTH*LENGTH) * (MASS / LENGTH * WIDTH);
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getX() {
        return group.getX();
    }

    public double getY() {
        return group.getY();
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

    public boolean checkPointForCollision(Point point) {
        return carShape.testHit(point.getX() - group.getX(), point.getY() - group.getY());
    }

    public ArrayList<Point> getPoints() {
        ArrayList<Point> points = getCarShapePoints();
        ArrayList<Point> collidePoints = new ArrayList<>();
        for (Point point : points) {
            collidePoints.add(point.add(group.getPosition()));
        }
        return collidePoints;
    }

    public void move(double dt) {
        group.moveBy(dt * dx, dt * dy);
    }

    public GraphicsGroup getGraphics() {
        return group;
    }

    public void buildGraphics() {
        carShape = new Path(getCarShapePoints());
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
        points.add(new Point(9, 4));
        points.add(new Point(9, 41));
        points.add(new Point(25, 41));
        points.add(new Point(25, 4));
        return points;
    }

        private ArrayList<Point> getRMirrorPoints() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(16, 0));
        points.add(new Point(20, 0));
        points.add(new Point(23, 0 - 8));
        points.add(new Point(19, 0 - 8));
        return points;
    }

        private ArrayList<Point> getLMirrorPoints() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(16, 45));
        points.add(new Point(20, 45));
        points.add(new Point(23, 53));
        points.add(new Point(19, 53));
        return points;
    }

        private ArrayList<Point> getCarShapePoints() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(0, WIDTH));
        points.add(new Point(LENGTH, WIDTH));
        points.add(new Point(LENGTH, 0));
        return points;
    }
}
