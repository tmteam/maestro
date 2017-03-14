package Settings;

/**
 * Created by Su on 14/03/17.
 */
public class LegSettings {

    public LegSettings(){
        top_0 = new ServoSettings();
        middle_1 = new ServoSettings();
        bottom_2 = new ServoSettings();
        name="unnamedLeg";
    }
    private String name;

    private ServoSettings top_0;
    private ServoSettings middle_1;
    private ServoSettings bottom_2;

    private double topToMiddleVerticalOffset;
    private double topToMiddleLength;
    private double middleLength;
    private double bottomLength;


    public void setTop_0(ServoSettings top_0) {
        this.top_0 = top_0;
    }

    public ServoSettings getTop_0() {
        return top_0;
    }

    public void setMiddle_1(ServoSettings middle_1) {
        this.middle_1 = middle_1;
    }

    public ServoSettings getMiddle_1() {
        return middle_1;
    }

    public void setBottom_2(ServoSettings bottom_2) {
        this.bottom_2 = bottom_2;
    }

    public ServoSettings getBottom_2() {
        return bottom_2;
    }

    public double getTopToMiddleLength() {
        return topToMiddleLength;
    }

    public double getMiddleLength() {
        return middleLength;
    }

    public double getBottomLength() {
        return bottomLength;
    }

    public double getTopToMiddleVerticalOffset() {
        return topToMiddleVerticalOffset;
    }

    public String getName() {
        return name;
    }
}
