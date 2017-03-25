package com.tmteam.jamaestro.settings;

import com.tmteam.jamaestro.api.HomeMode;
import com.tmteam.jamaestro.api.SerialMode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Su on 14/03/17.
 */
class SerializerTest {
    @Test
    void originAndDeserializedChannelSettings_areEqual(){
        ChannelSettings origin = new ChannelSettings();

        origin.setRange(100);
        origin.setMaximum(1000);
        origin.setMinimum(10);
        origin.setHomeMode(HomeMode.GOTO);

        String serialized = Serializer.serialize(origin);

        ChannelSettings restored = Serializer.deserializeChannelSettings(serialized);

        Assertions.assertTrue(origin.isSimilarTo(restored));
    }
    @Test
    void originAndDeserializedSettingsFields_areEqual(){
        MaestroSettings origin = new MaestroSettings();

        origin.setBaudRate(100);
        origin.setDeviceNumber(5);
        origin.setEnableCrc(true);
        origin.setMiniMaestroServoPeriod(42);
        origin.setSerialMode(SerialMode.USB_CHAINED);

        String serialized = Serializer.serialize(origin);

        MaestroSettings restored = Serializer.deserializeSettings(serialized);

        Assertions.assertTrue(origin.isSimilarTo(restored));
    }

    @Test
    void originAndDeserializedSettingsChannels_areEqual(){
        MaestroSettings origin = new MaestroSettings();


        ChannelSettings channel1 = new ChannelSettings();

        channel1.setRange(100);
        channel1.setMaximum(1000);
        channel1.setMinimum(10);
        channel1.setHomeMode(HomeMode.GOTO);


        ChannelSettings channel2 = new ChannelSettings();

        channel2.setRange(100);
        channel2.setMaximum(1000);
        channel2.setMinimum(10);
        channel2.setHomeMode(HomeMode.GOTO);

        origin.AddChannel(1, channel1);
        origin.AddChannel(2, channel2);

        String serialized = Serializer.serialize(origin);

        MaestroSettings restored = Serializer.deserializeSettings(serialized);

        Assertions.assertTrue(origin.isSimilarTo(restored));
    }
}