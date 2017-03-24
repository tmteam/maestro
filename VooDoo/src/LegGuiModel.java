import Cross.ICrossModel;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Su on 24/03/17.
 */
public class LegGuiModel {

    private  ILog log;

    public ICrossModel getSideCrossModel(){
        throw  new NotImplementedException();
    }

    public ICrossModel getFrontCrossModel(){
        throw  new NotImplementedException();
    }

    public boolean isConnected(){
        return  false;
    }

    public void connect(){

    }

    public ILog getLog() {
        return log;
    }

    public void setLog(ILog log) {
        this.log = log;
    }
}
