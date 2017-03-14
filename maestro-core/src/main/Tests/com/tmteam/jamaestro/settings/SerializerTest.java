package com.tmteam.jamaestro.settings;

import com.tmteam.jamaestro.api.HomeMode;
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

        String serialized = Serializer.serializeChannelSettings(origin);

        ChannelSettings2 restored = Serializer.deserializeChannelSettings(serialized);

        Assertions.assertEquals(origin.getRange(), restored.getRange());
        Assertions.assertEquals(origin.getMaximum(), restored.getMaximum());
        Assertions.assertEquals(origin.getMinimum(), restored.getMinimum());
        Assertions.assertEquals(origin.getHomeMode(), restored.getHomeMode());

    }
}