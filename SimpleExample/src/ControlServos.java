import com.tmteam.jamaestro.binding.LibUsbDriverBinding;
import com.tmteam.jamaestro.MaestroServoController;
import com.tmteam.jamaestro.api.ChannelMode;
import com.tmteam.jamaestro.api.Product;
import com.tmteam.jamaestro.binding.DriverBinding;
import com.tmteam.jamaestro.settings.*;

import java.io.IOException;

/**
 * Created by Su on 11/03/17.
 */
public class ControlServos {
    public static void main(String[] args) throws IOException, InterruptedException {

        Settings2 settings = Serializer.readSettings("set.json");

        Product mini24 = Product.MINI24;

        BindingListener listener = new BindingListener();
        System.out.println("Binding");
        LibUsbDriverBinding.bindToDevice(mini24, listener);
        DriverBinding driver = listener.getBindingOrNull();
        if(driver==null){
            System.out.print("No driver found");
            return;
        }


        MaestroServoController controller = new MaestroServoController(driver,settings);

        controller.setAcceleration(1, 0);
        controller.setSpeed(1, 0);
        controller.setTarget(1, settings.getChannel(1).getMinimum());
        Thread.sleep(2000);                 //1000 milliseconds is one second.

        controller.setAcceleration(1, 1);

        controller.setSpeed(1, 40);
        controller.setTarget(1, settings.getChannel(1).getMaximum());


        System.out.println("Wait");
        //System.console().readLine();
        // Display "Hello World!"
        System.out.println("Hello World!");
    }
}
