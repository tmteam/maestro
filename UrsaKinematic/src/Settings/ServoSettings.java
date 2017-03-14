package Settings;

/**
 * Created by Su on 14/03/17.
 */
public class ServoSettings {
    private double maxValue = 180;
    private double minValue = 0;
    private String name = "unknownName";
    private double maxSpeed  = 0;
    private int channel;

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public double getMinValue() {
        return minValue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }
}
