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
        classIndex = 5;                // usually comes last (5) or first (0)
        MODEL = "house.model";         // model file name without path
        classifierType = REGRESSION;   // CLASSIFICATION or REGRESSION
        relation = "house";            // not necessary (name of the dataset)
    }

    @Override
    public ArrayList<Attribute> createAttributeList() {
        ArrayList<Attribute> atts = new ArrayList<>();

        // Weka doesn't take attribute names into account, but their order!
        // However, for clarity one should specify attribute names like below

        atts.add(new Attribute("houseSize"));
        atts.add(new Attribute("lotSize"));
        atts.add(new Attribute("bedrooms"));
        atts.add(new Attribute("granite"));
        atts.add(new Attribute("bathroom"));
        atts.add(new Attribute("sellingPrice"));

        return atts;
    }

    @Override
    public Instance calculateFeatures(House data) {
        // valid instance size is 5 or 6 (with class attribute)
        Instance instance = new DenseInstance(5);

        instance.setValue(0, data.getHouseSize());
        instance.setValue(1, data.getLotSize());
        instance.setValue(2, data.getBedrooms());
        instance.setValue(3, data.getGranite());
        instance.setValue(4, data.getBathroom());
        return instance;
    }
}