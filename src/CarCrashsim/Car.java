package CarCrashsim;

import java.util.ArrayList;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Path;
import edu.macalester.graphics.Point;

public interface Car {
    public Point getCenterOfMass();

    public void spin(double rad, ArrayList<Point> points, Path shape);

    public ArrayList<Point> spinPoints(double rad, ArrayList<Point> points);

    public void spinAllParts(double dt);

    public double rVelToRadians();

    public void setRVelocity(double vel);

    public Vector getR(Point collide);

    public double getRVel();

    public double getMassOfInertia();

    public double getX();

    public double getY();

    public void setDx(double dx);

    public void setDy(double dy);

    public ArrayList<Point> getPoints();

    public void move(double dt);

    public boolean checkPointForCollision(Point point);

    public double getMass();

    public double getDx();

    public double getDy();

    public GraphicsGroup getGraphics();

    public void buildGraphics();
}
