package com.maslick.ai.classy;

import com.maslick.ai.klassy.io.ContextLoader;
import org.junit.Assert;
import org.junit.Test;


public class Testing {
    @Test
    public void housing() {
        Houser classifier = new Houser(new ContextLoader());
        House house = new House(3198, 9669, 5, 2, 1);
        String klass = classifier.classify(house);
        Assert.assertEquals(219328, Double.valueOf(klass), 1);
    }
}