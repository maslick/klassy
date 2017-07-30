# =klassy=
Weka classification wrapper

[![Build Status](https://travis-ci.org/maslick/klassy.svg?branch=master)](https://travis-ci.org/maslick/klassy)
[ ![Download](https://api.bintray.com/packages/maslick/maven/klassy/images/download.svg?version=0.1) ](https://bintray.com/maslick/maven/klassy/0.1/link)


## Description
 * Klassy provides a Weka friendly API with only two methods to implement.
 * Build against Weka 3.8.1 (but can be used with earlier versions)

## Installation
```
repositories {
    maven {
        url  "https://dl.bintray.com/maslick/maven/"
    }
}
...
dependencies {    
    compile('com.maslick.ai:klassy:0.1.3')
}
```

## Usage
* extend the AbstractClassifier class and implement two methods - ``createAttributeList`` and ``calculateFeatures``
* depending on your deployment target (pc, android, etc.) implement the ``IFileLoader`` interface (see below)
* instantiate your model (classifier) and invoke its classify method:

```
@Test
public void housing() {
    Houser classifier = new Houser(new ContextLoader());
    House house = new House(3198, 9669, 5, 2, 1);
    String klass = classifier.classify(house);
    Assert.assertEquals(219328, Double.valueOf(klass), 1);
}
```

```
public class Houser extends AbstractClassifier<House> {

    public Houser(IFileLoader fileLoader) {
        super(fileLoader);
        classIndex = 5;
        MODEL = "house.model";
        classifierType = REGRESSION;
        relation = "house";
        super.loadClassifierModel();
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
```

--------
## IFileLoader
IFileLoader interface allows you to abstract away from your running environment (it may be the pc, as well as the mobile) and load weka models.

SpringBoot implementation:
```
public class SpringFileLoader implements IFileLoader {
    @Override
    public InputStream getFile(String filename) throws IOException {
        return new ClassPathResource(filename).getInputStream();
    }
}
```

Android implementation:
```
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
```
public class ContextLoader implements IFileLoader{
    @Override
    public InputStream getFile(String filename) throws IOException {
        return ClassLoader.getSystemResourceAsStream(filename);
    }
}
```
