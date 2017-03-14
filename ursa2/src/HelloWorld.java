import com.jamief.maestro.binding.LibUsbDriverBinding;
import com.jamierf.maestro.MaestroServoController;
import com.jamierf.maestro.api.ChannelMode;
import com.jamierf.maestro.api.Product;
import com.jamierf.maestro.binding.DriverBinding;
import com.jamierf.maestro.settings.ChannelSettings;
import com.jamierf.maestro.settings.Settings;

/**
 * Created by Su on 11/03/17.
 */
public class HelloWorld {
    public static void main(String[] args) {

        Product mini24 = Product.MINI24;

        BindingListener listener = new BindingListener();
        System.out.println("Binding");
        LibUsbDriverBinding.bindToDevice(mini24, listener);
        DriverBinding driver = listener.getBindingOrNull();
        if(driver==null){
            System.out.print("No driver found");
            return;
        }

        ChannelSettings channelSettings = ChannelSettings.builder()
                .setChannelMode(ChannelMode.SERVO)
                .setMinimum(1500)
                .setMaximum(10000)
              //  .setRange(10000)
              //  .setNeutral(3600)
                .build();


        Settings settings = Settings
                .builder()
                .addChannel(1, channelSettings)
                .build();


        MaestroServoController controller = new MaestroServoController(driver,settings);
        //controller.resetTarget(1);
        controller.setAcceleration(1, 0);

        controller.setSpeed(1, 0);

        controller.setTarget(1, channelSettings.getMinimum());
        try {
            Thread.sleep(2000);                 //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        controller.setAcceleration(1, 1);

        controller.setSpeed(1, 40);
        controller.setTarget(1, channelSettings.getMaximum());

        /*
        for (int i = channelSettings.getMinimum(); i< channelSettings.getMaximum(); i+=500) {
            System.out.println(i);
            controller.setTarget(1, i);
            try {
                Thread.sleep(500);                 //1000 milliseconds is one second.
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

        controller.setTarget(1, 2000);
*/
        System.out.println("Wait");
        //System.console().readLine();
        // Display "Hello World!"
        System.out.println("Hello World!");
    }
}
