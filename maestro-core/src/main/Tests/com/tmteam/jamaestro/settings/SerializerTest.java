package com.tmteam.jamaestro.settings;

import com.tmteam.jamaestro.api.HomeMode;
import com.tmteam.jamaestro.api.SerialMode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import sun.jvm.hotspot.utilities.Assert;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Su on 14/03/17.
 */
class SerializerTest {
    @Test
    void originAndDeserializedChannelSettings_areEqual(){
        ChannelSettings2 origin = new ChannelSettings2();

        origin.setRange(100);
        origin.setMaximum(1000);
        origin.setMinimum(10);
        origin.setHomeMode(HomeMode.GOTO);

        String serialized = Serializer.serialize(origin);

        ChannelSettings2 restored = Serializer.deserializeChannelSettings(serialized);

        Assertions.assertTrue(origin.isSimilarTo(restored));
    }
    @Test
    void originAndDeserializedSettingsFields_areEqual(){
        Settings2 origin = new Settings2();

        origin.setBaudRate(100);
        origin.setDeviceNumber(5);
        origin.setEnableCrc(true);
        origin.setMiniMaestroServoPeriod(42);
        origin.setSerialMode(SerialMode.USB_CHAINED);

        String serialized = Serializer.serialize(origin);

        Settings2 restored = Serializer.deserializeSettings(serialized);

        Assertions.assertTrue(origin.isSimilarTo(restored));
    }

    @Test
    void originAndDeserializedSettingsChannels_areEqual(){
        Settings2 origin = new Settings2();


        ChannelSettings2 channel1 = new ChannelSettings2();

        channel1.setRange(100);
        channel1.setMaximum(1000);
        channel1.setMinimum(10);
        channel1.setHomeMode(HomeMode.GOTO);


        ChannelSettings2 channel2 = new ChannelSettings2();

        channel2.setRange(100);
        channel2.setMaximum(1000);
        channel2.setMinimum(10);
        channel2.setHomeMode(HomeMode.GOTO);

        origin.AddChannel(1, channel1);
        origin.AddChannel(2, channel2);

        String serialized = Serializer.serialize(origin);

        Settings2 restored = Serializer.deserializeSettings(serialized);

        Assertions.assertTrue(origin.isSimilarTo(restored));
    }
}