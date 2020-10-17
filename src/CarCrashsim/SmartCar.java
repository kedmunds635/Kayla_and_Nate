package CarCrashsim;

import java.util.ArrayList;

import edu.macalester.graphics.*;
import edu.macalester.graphics.Rectangle;

public class SmartCar implements Car{
    private GraphicsGroup group;
    private double dx;
    private double dy;
    private Path carShape;
    private Vector rVel;
    private Point centerOfMass;
    private double orientation;

    private static final double MASS = 10;
    private static final double LENGTH = 50;
    private static final double HEIGHT = 40;

    public SmartCar(double dx, double dy, double x, double y) {
        this.dx = dx;
        this.dy = dy;
        group = new GraphicsGroup();
        buildGraphics();
        group.setPosition(x, y);
        centerOfMass = new Point(LENGTH * 0.5, HEIGHT * 0.5);
        rVel = new Vector(0, 0);
        orientation = 0;
    }

    public double getMassOfInertia() {
        return 4.0/3.0 * HEIGHT * LENGTH * (HEIGHT*HEIGHT + LENGTH*LENGTH) * (MASS / LENGTH * HEIGHT);
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
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

    public double getX() {
        return group.getX();
    }

    public double getY() {
        return group.getY();
    }

    public Vector getR(Point collide) {
        return new Vector(collide.getX() - centerOfMass.getX(), collide.getY() - centerOfMass.getY());
    }

    public Vector getRVel() {
        return rVel;
    }

    public void setRVelocity(Vector vel) {
        rVel = vel;
    }

    public double rVelToRadians() {
        double dist = Math.sqrt(Math.pow(LENGTH * 0.5, 2) + Math.pow(HEIGHT * 0.5, 2));
        double circumfence = 2 * Math.PI * dist;
        System.out.println(rVel.getVelocity());
        return (rVel.getVelocity() / circumfence) * 2 * Math.PI;
    }

    public void spin(double rads) {
        ArrayList<Point> points = getCarShapePoints();
        ArrayList<Point> rotatedPoints = new ArrayList<>();
        for (Point point : points) {
            rotatedPoints.add(point.rotate(rads + orientation, centerOfMass));
        }
        orientation += rads;
        carShape.setVertices(rotatedPoints);
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
        carShape = new Path(getCarShapePoints(), true);
        Path windShield = new Path(getWindPoints(), true);
        Path rMirror = new Path(getRMirrorPoints(), true);
        Path lMirror = new Path(getLMirrorPoints(), true);
        group.add(rMirror);
        group.add(lMirror);
        group.add(carShape);
        group.add(windShield);
    }

    private ArrayList<Point> getWindPoints() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(7, 3));
        points.add(new Point(7, 37));
        points.add(new Point(22, 37));
        points.add(new Point(22, 3));
        return points;
    }

        private ArrayList<Point> getRMirrorPoints() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(14, 0));
        points.add(new Point(18, 0));
        points.add(new Point(21, -8));
        points.add(new Point(17, -8));
        return points;
    }

        private ArrayList<Point> getLMirrorPoints() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(14, 40));
        points.add(new Point(18, 40));
        points.add(new Point(21, 48));
        points.add(new Point(17, 48));
        return points;
    }

        private ArrayList<Point> getCarShapePoints() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(0, HEIGHT));
        points.add(new Point(LENGTH, HEIGHT));
        points.add(new Point(LENGTH, 0));
        return points;
    }
}