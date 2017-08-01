package com.maslick.ai.klassy;

import com.maslick.ai.klassy.io.ContextLoader;
import org.junit.Assert;
import org.junit.Test;


public class Testing {
    @Test
    public void housing() {
        Houser model = new Houser(new ContextLoader());
        House house = House.builder()
                .houseSize(3198)
                .lotSize(9669)
                .bedrooms(5)
                .granite(2)
                .bathroom(1)
                .build();

        String klass = model.classify(house);
        Assert.assertEquals(219328, Double.valueOf(klass), 1);
    }
}