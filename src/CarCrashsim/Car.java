package CarCrashsim;

import edu.macalester.graphics.GraphicsGroup;

public interface Car {

    public void move(double dt);

    public boolean checkForCollision();

    public double getMass();

    public double getDx();

    public double getDy();

    public double getX();

    public double getY();

    public GraphicsGroup getGraphics();

    public void buildGraphics();
}
