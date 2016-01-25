# BlueRand

Random Number Generator from 2 different camera pictures.

How it works
[elenco puntato]


## Sample
Simple execution:
```java
BlueRand random = new BlueRand("input/a.jpg", "input/b.jpg");
random.createOutputFile(true);
random.setOutputFile("output/ab.txt");
random.createOutputImage(true);
random.setOutputImage("output/ab.bmp");
ArrayList<Byte> output = random.generateRandom();
System.out.println("Bytes generated: "+output.size());
```
Please check the [test file][1] for more tests.

##Customize options
```java
BlueRand random = new BlueRand();
random.random.setInputImages("input/a.jpg", "input/b.jpg");
random.createOutputFile(true);
random.setOutputFile("output/ab.txt");
random.createOutputImage(true);
random.setOutputImage("output/ab.bmp");
random.overwriteOutputFile(false);
```


## License

[Apache License 2.0][7]



[1]: test/Test.java
[7]: http://www.apache.org/licenses/LICENSE-2.0


