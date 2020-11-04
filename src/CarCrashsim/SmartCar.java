package CarCrashsim;

import java.util.ArrayList;

import edu.macalester.graphics.*;

public class SmartCar implements Car{
    private GraphicsGroup group;
    private double dx;
    private double dy;
    private Path carShape;
    private double rVel;
    private Point centerOfMass;
    private double orientation;
    private Path lMirror;
    private Path rMirror;
    private Path windShield;

    private static final double MASS = 9000;
    private static final double LENGTH = 50;
    private static final double HEIGHT = 40;
    private static final double LENGTH_METERS = LENGTH / 15;
    private static final double HEIGHT_METERS = HEIGHT / 15;

    public SmartCar(double dx, double dy, double x, double y) {
        this.dx = dx;
        this.dy = dy;
        group = new GraphicsGroup();
        buildGraphics();
        group.setPosition(x, y);
        centerOfMass = new Point(LENGTH * 0.5, HEIGHT * 0.5);
        rVel = 0.0;
        orientation = 0;
    }

    public void applyFriction(double dt) {
        dx *= 1 - (0.05 * dt);
        dy *= 1 - (0.05 * dt);
        rVel *= 1 - (0.05 * dt);
    }

    public Point getCenterOfMass() {
        return centerOfMass;
    }

    public double getMassOfInertia() {
        return 4.0/3.0 * HEIGHT_METERS * LENGTH_METERS * (HEIGHT_METERS*HEIGHT_METERS + LENGTH_METERS*LENGTH_METERS) * (MASS / LENGTH_METERS * HEIGHT_METERS);
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
        return new Vector(collide.getX() - (centerOfMass.getX() + group.getX()), collide.getY() - (centerOfMass.getY() + group.getY()));
    }

    public double getRVel() {
        return rVel;
    }

    public void setRVelocity(double vel) {
        rVel = vel;
    }

    public double rVelToRadians() {
        double dist = Math.sqrt(Math.pow(LENGTH * 0.5, 2) + Math.pow(HEIGHT * 0.5, 2));
        double circumfence = 2 * Math.PI * dist;
        return -(rVel / circumfence) / (Math.PI * 2);
    }

    public void spinAllParts(double dt) {
            spin(rVel * dt, getCarShapePoints(), carShape);
            spin(rVel * dt, getLMirrorPoints(), lMirror);
            spin(rVel * dt, getRMirrorPoints(), rMirror);
            spin(rVel * dt, getWindPoints(), windShield);
        }

    public void spin(double rads, ArrayList<Point> points, Path shape) {
        ArrayList<Point> rotatedPoints = new ArrayList<>();
        for (Point point : points) {
            rotatedPoints.add(point.rotate(rads + orientation, centerOfMass));
        }
        orientation += rads;
        shape.setVertices(rotatedPoints);
    }

    public ArrayList<Point> spinPoints(double rads, ArrayList<Point> points) {
        ArrayList<Point> rotatedPoints = new ArrayList<>();
        for (Point point : points) {
            rotatedPoints.add(point.rotate(rads, centerOfMass));
        }
        return rotatedPoints;
    }

    public boolean checkPointForCollision(Point point) {
        return carShape.testHit(point.getX() - group.getX(), point.getY() - group.getY());
    }
    //how do we acount for rotation of car when condiering weather of not they are colliding?

    public ArrayList<Point> getPoints() {
        ArrayList<Point> points = getCarShapePoints();
        ArrayList<Point> rotatedPoints = spinPoints(orientation, points);
        ArrayList<Point> collidePoints = new ArrayList<>();
        for (Point point : rotatedPoints) {
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
        windShield = new Path(getWindPoints(), true);
        rMirror = new Path(getRMirrorPoints(), true);
        lMirror = new Path(getLMirrorPoints(), true);
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