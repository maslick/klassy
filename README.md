# =klassy=
Weka classification wrapper

[![Build Status](https://travis-ci.org/maslick/klassy.svg?branch=master)](https://travis-ci.org/maslick/klassy)
[ ![Download](https://api.bintray.com/packages/maslick/maven/klassy/images/download.svg?version=0.1) ](https://bintray.com/maslick/maven/klassy/0.1/link)


## Description
 * Klassy provides a Weka friendly API with only two methods to implement
 * Intended for **using** existing Weka models in production code
 * Build against Weka 3.8.1 (but can be used with earlier versions)

## Installation
```
repositories {
    jcenter()
}
...
dependencies {    
    compile('com.maslick.ai:klassy:0.1.6')
}
```

## Usage ([see example](https://github.com/maslick/klassy/tree/master/src/test/java/com/maslick/ai/klassy))
* extend the AbstractClassifier class and implement two methods - ``createAttributeList`` and ``calculateFeatures``
* depending on your deployment target (pc, android, etc.) implement the ``IFileLoader`` interface (see below)
* instantiate your model (classifier) and invoke its classify method:

```java
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
```

```java
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

        instance.setValue(0, data.getHouseSize());
        instance.setValue(1, data.getLotSize());
        instance.setValue(2, data.getBedrooms());
        instance.setValue(3, data.getGranite());
        instance.setValue(4, data.getBathroom());
        return instance;
    }
}
```

--------
## IFileLoader
IFileLoader interface allows you to abstract away from your deployment target and load weka models (it may well be a PC, as well as the mobile phone).

SpringBoot implementation:
```java
public class SpringFileLoader implements IFileLoader {
    @Override
    public InputStream getFile(String filename) throws IOException {
        return new ClassPathResource(filename).getInputStream();
    }
}
```

Android implementation:
```java
public class AndroidFileLoader implements IFileLoader {
    private Context context;
    
    public AndroidFileLoader(Context context) {
        this.context = context;
    }

    @Override
    public InputStream getFile(String filename) throws IOException {
        return context.applicationContext.assets.open(filename);
    }
}
```

Classy provides the default ContextLoader implementation:
```java
public class ContextLoader implements IFileLoader{
    @Override
    public InputStream getFile(String filename) throws IOException {
        return ClassLoader.getSystemResourceAsStream(filename);
    }
}
```
