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
        MODEL = "house.model";         // model file name without path
        classifierType = REGRESSION;   // CLASSIFICATION or REGRESSION
        classIndex = 5;                // usually comes last (5) or first (0)
        relation = "house";            // name of the dataset (optional)
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
        atts.add(new Attribute("sellingPrice"));    // class attribute (classIndex=5)

        return atts;
    }

    @Override
    public Instance calculateFeatures(House data) {
        // valid instance size is 5 or 6 (with class attribute)
        Instance instance = new DenseInstance(5);

        instance.setValue(attributes.get(0), data.getHouseSize());
        instance.setValue(attributes.get(1), data.getLotSize());
        instance.setValue(attributes.get(2), data.getBedrooms());
        instance.setValue(attributes.get(3), data.getGranite());
        instance.setValue(attributes.get(4), data.getBathroom());

        return instance;
    }
}