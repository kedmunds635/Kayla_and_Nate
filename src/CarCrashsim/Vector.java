package CarCrashsim;

public class Vector {
    private double dx;
    private double dy;

    public Vector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public double getVelocity () {
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    public double getAngle() {
        return Math.atan(dx/dy);
    }

    public Vector subtract(Vector other) {
        return new Vector(this.dx - other.getDx(), this.dy - other.getDy());
    }

    public Vector multiply(double num) {
        return new Vector(this.dx * num, this.dy * num);
    }

    public Vector divide(double num) {
        return new Vector(this.dx / num, this.dy / num);
    }

    
}
