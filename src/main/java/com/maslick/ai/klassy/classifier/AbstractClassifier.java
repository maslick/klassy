package com.maslick.ai.klassy.classifier;

import com.maslick.ai.klassy.io.IFileLoader;
import com.maslick.ai.klassy.io.IModelLoader;
import com.maslick.ai.klassy.io.ModelLoader;
import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;

import java.io.IOException;
import java.util.ArrayList;

import static com.maslick.ai.klassy.classifier.ClassifierType.CLASSIFICATION;

public abstract class AbstractClassifier<T> implements IClassifier<T> {
    private IFileLoader fileLoader;
    protected String MODEL = "model.model";
    public Classifier classifier;
    protected int classIndex = -1;
    protected String relation = "Test relation";
    protected ArrayList<Attribute> attributes;
    protected ClassifierType classifierType;

    public AbstractClassifier(IFileLoader fileLoader) {
        this.fileLoader = fileLoader;
    }

    protected void loadClassifierModel() {
        try {
            IModelLoader modelLoader = new ModelLoader(fileLoader);
            classifier = modelLoader.getClassifierFromFile(MODEL);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Instances createInstancesPlaceholder() {
        if (attributes == null) attributes = createAttributeList();
        Instances instances = new Instances(relation, attributes, 1);
        instances.setClassIndex(classIndex);
        return instances;
    }

    public String classify(T data) {
        // 0. create attribute list
        // 1. create instances placeholder
        Instances instancesPlaceholder = createInstancesPlaceholder();
        // 2. calculate features
        Instance features = calculateFeatures(data);
        // 3. add features to placeholder
        instancesPlaceholder.add(features);
        // 4. classify
        String clazz = null;
        try {
            Instance instance = instancesPlaceholder.instance(0);
            Double predictedClass = classifier.classifyInstance(instance);
            if (classifierType == CLASSIFICATION)
                clazz = instancesPlaceholder.classAttribute().value((predictedClass.intValue()));
            else if (classifierType == ClassifierType.REGRESSION)
                clazz = predictedClass.toString();
        } catch (Exception e) {
            System.out.println("Problem found when classifying the text");
            e.printStackTrace();
        }
        return clazz;
    }
}
