package CarCrashsim;

import java.util.ArrayList;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;

public interface Car {

    public ArrayList<Point> getPoints();

    public void move(double dt);

    public boolean checkPointForCollision(Point point);

    public double getMass();

    public double getDx();

    public double getDy();

    public GraphicsGroup getGraphics();

    public void buildGraphics();
}
