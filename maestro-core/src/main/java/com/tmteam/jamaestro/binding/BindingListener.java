package com.tmteam.jamaestro.binding;

/**
 * Created by Su on 11/03/17.
 */
public class BindingListener implements AsyncBindingListener {
    private DriverBinding driver;

    @Override
    public void onBind(int vendorId, int productId, DriverBinding driver) {
        this.driver = driver;
        System.out.print("onBind" + vendorId + " "+ productId + " "+ driver);
    }
    public  DriverBinding getBindingOrNull(){
        return driver;
    }
    @Override
    public void onException(Throwable t) {
        System.out.print("Exception "+ t.getMessage());
    }
}
