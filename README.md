# BlueRand
Java True Random Number Generator (TRNG) that uses JPEG images as entropy source.

##How it works
* It uses two different input images (with same resolution). These images are considered independent because taken in two different moments. 
* It use the [Mixing technique](https://tools.ietf.org/html/rfc4086#section-5), so the output will be unpredictable if at least one input image is unpredictable.
* Only some of the total amount of pixels are considered. The CSPRNG SecureRandom determines for each considered pixel how many consecutive pixels that have to be discarded. In this manner the correlation between consecutive pixels is avoided. This also guarantee that if the generator is run twice two different outputs are generated.
* Only the BLUE channel is considered. So the correlation between different RGB channels is avoided.
* The noise that can be found on images is located in least significant bits [(LSB)](https://en.wikipedia.org/wiki/Least_significant_bit). For this reason only the LSB for each input image is considered. You can choose to consider the second least significant bit too.

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
Please check the [test file](sample/Sample.java) for more tests.

##Customize options
```java
BlueRand random = new BlueRand();
random.random.setInputImages("a.jpg", "b.jpg");
random.considerTwoLSB(true);
random.createOutputFile(true);
random.setOutputFile("ab.txt");
random.createOutputImage(true);
random.setOutputImage("ab.bmp");
random.overwriteOutputFile(true);
random.deleteInputFiles(true);
```

##Tests
An [ENT](http://www.fourmilab.ch/random/) test result:
```
Entropy = 7.998236 bits per byte.

Optimum compression would reduce the size
of this 95764 byte file by 0 percent.

Chi square distribution for 95764 samples is 235.18, and randomly
would exceed this value 80.83 percent of the times.

Arithmetic mean value of data bytes is 127.3139 (127.5 = random).
Monte Carlo value for Pi is 3.149122807 (error 0.24 percent).
Serial correlation coefficient is -0.000265 (totally uncorrelated = 0.0).

```

Output as output image:
![noise](https://github.com/prgpascal/bluerand/blob/master/sample/output/multiRuns_output.bmp)

## License
BlueRand is licensed under [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0) license.
