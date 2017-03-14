package Settings;

/**
 * Created by Su on 14/03/17.
 */
public class LegBindingSettings {

    public LegBindingSettings(){
        legSettings = new LegSettings();

    }

    private LegSettings legSettings;
    /**
     * угол поворота ноги(если смотреть спереди)
     */
    private double frontAngle;
    /**
     * угол поворота ноги относительно  хорды (если смотреть справа)
     */
    private double rightAngle;
    /**
     * угол поворота ноги относительно длинны хорды (если смотреть сверху)
     */
    private double topAngle;

    /**
     * Отступ от акселерометра до ноги по плечам (если смотреть сверху)
     * право это положительное значение
     */
    private double rightOffsetX;

    /**
     * Отступ от акселерометра до ноги вдоль хорды(если смотреть сбоку)
     */
    private double hordOffsetY;

    /**
     * Отступ от акселерометра до ноги по высоте(если смотреть сбоку)
     */
    private double topOffsetZ;


    public LegSettings getLegSettings() {
        return legSettings;
    }
}
