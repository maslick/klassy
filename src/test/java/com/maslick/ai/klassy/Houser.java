package com.maslick.ai.klassy;

import com.maslick.ai.klassy.classifier.AbstractClassifier;
import com.maslick.ai.klassy.io.IFileLoader;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;

import java.util.ArrayList;

import static com.maslick.ai.klassy.classifier.ClassifierType.REGRESSION;

public class Houser extends AbstractClassifier<House> {

    public Houser(IFileLoader fileLoader) {
        super(fileLoader);
        classIndex = 5;
        MODEL = "house.model";
        classifierType = REGRESSION;
        relation = "house";
    }

    @Override
    public ArrayList<Attribute> createAttributeList() {
        ArrayList<Attribute> atts = new ArrayList<>();
        atts.add(new Attribute("houseSize",    0));
        atts.add(new Attribute("lotSize",      1));
        atts.add(new Attribute("bedrooms",     2));
        atts.add(new Attribute("granite",      3));
        atts.add(new Attribute("bathroom",     4));
        atts.add(new Attribute("sellingPrice", 5));

        return atts;
    }

    @Override
    public Instance calculateFeatures(House data) {
        Instance instance = new DenseInstance(6);
        instance.setValue(attributes.get(0), data.getHouseSize());
        instance.setValue(attributes.get(1), data.getLotSize());
        instance.setValue(attributes.get(2), data.getBedrooms());
        instance.setValue(attributes.get(3), data.getGranite());
        instance.setValue(attributes.get(4), data.getBathroom());
        return instance;
    }
}
