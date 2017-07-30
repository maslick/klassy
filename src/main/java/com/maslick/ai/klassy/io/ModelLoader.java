package com.maslick.ai.klassy.io;

import weka.classifiers.Classifier;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ModelLoader implements IModelLoader {

    private IFileLoader fileLoader;

    public ModelLoader(IFileLoader fileLoader) {
        this.fileLoader = fileLoader;
    }

    @Override
    public Classifier getClassifierFromFile(String modelName) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(fileLoader.getFile(modelName));
        Classifier classifier = (Classifier) ois.readObject();
        ois.close();
        return classifier;
    }
}
