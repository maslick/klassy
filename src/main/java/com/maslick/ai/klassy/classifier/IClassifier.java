package com.maslick.ai.klassy.classifier;

import weka.core.Attribute;
import weka.core.Instance;

import java.util.ArrayList;

public interface IClassifier<T> {
    ArrayList<Attribute> createAttributeList();
    Instance calculateFeatures(T data);
    String classify(T data);
}
