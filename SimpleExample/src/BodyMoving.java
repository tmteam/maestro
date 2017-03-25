import Kinematic.Body;
import Settings.BodyKinematicSettings;
import Settings.Serializer;
import com.tmteam.jamaestro.MaestroServoController;
import com.tmteam.jamaestro.api.Product;
import com.tmteam.jamaestro.binding.BindingListener;
import com.tmteam.jamaestro.binding.DriverBinding;
import com.tmteam.jamaestro.binding.LibUsbDriverBinding;

import java.io.IOException;

/**
 * Created by Su on 15/03/17.
 */
public class BodyMoving {
    public static void main(String[] args) throws IOException, InterruptedException {

        BodyKinematicSettings bodySettings = Serializer.readSettings("bodyKinematic.json");
        Body body = new Body(bodySettings);

        System.out.println("FR-1 servo name: "
                + body.getFrontRightLeg().getLeg().getServoM1().getServoSettings().getName());

        BindingListener listener = new BindingListener();
        System.out.println("Binding");
        LibUsbDriverBinding.bindToDevice(Product.MINI24, listener);
        DriverBinding driver = listener.getBindingOrNull();
        if(driver==null){
            System.out.print("No driver found");
            return;
        }
        else
        {
            System.out.print("Driver connected");
        }
        MaestroServoController controller = new MaestroServoController(driver, bodySettings.getMaestro());

    }
}