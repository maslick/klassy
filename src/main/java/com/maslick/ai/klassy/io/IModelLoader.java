package com.maslick.ai.klassy.io;

import weka.classifiers.Classifier;

import java.io.IOException;

public interface IModelLoader {
    Classifier getClassifierFromFile(String modelName) throws IOException, ClassNotFoundException;
}
