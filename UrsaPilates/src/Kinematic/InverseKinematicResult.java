package Kinematic;

public class InverseKinematicResult{

    /**
     * angle between X-axis and l1
     */
    public final double baseAngle;

    /**
     * angle between l1 and l2 (bottom side)
     */
    public final double bendAngle;

    public InverseKinematicResult(double baseAngle, double bendAngle) {
        this.baseAngle = baseAngle;
        this.bendAngle = bendAngle;
    }

    public boolean AreActual(){
        return !Double.isNaN(baseAngle) && !Double.isNaN(bendAngle);
    }
}
