# BlueRand
Java True Random Number Generator (TRNG) that uses JPEG images as entropy source.

##How it works
* It uses two different input images (with same pixel resolution). These images are considered independent because captured in two different moments. 
* It use the [Mixing technique](https://tools.ietf.org/html/rfc4086#section-5), so the output will be unpredictable if at least one  image is unpredictable.
* Only some of the total amount of pixels are considered. The CSPRNG SecureRandom determines for each considered pixel how many consecutive pixels that have to be discarded. In this manner the correlation between consecutive pixels is avoided. This also guarantees that if the generator is run twice two different outputs are generated.
* Only the BLUE channel is considered. So the correlation between different RGB channels is avoided.
* The noise that can be found in images is located in the least significant bits [(LSB)](https://en.wikipedia.org/wiki/Least_significant_bit). For this reason only the LSB of each input byte is considered. You can choose to consider the second least significant bit too.

## Sample
Simple execution:
```java
BlueRand random = new BlueRand("sample/input/a.jpg", "sample/input/b.jpg");
try {
	ArrayList<Byte> output = random.generateRandom();
} catch(BlueRandException e){
	e.printStackTrace();
}
```
Please check [here](sample/Sample.java) for more samples.

##Customize options
```java
BlueRand random = new BlueRand();
random.random.setInputImages("a.jpg", "b.jpg");
random.considerTwoLSB(true);
random.setOutputFile("ab.txt");
random.setOutputImage("ab.bmp");
random.overwriteOutputFile(true);
random.deleteInputFiles(true);
```

##Tests
An [ENT](http://www.fourmilab.ch/random/) test result:
```
Entropy = 7.999495 bits per byte.

Optimum compression would reduce the size
of this 383209 byte file by 0 percent.

Chi square distribution for 383209 samples is 268.48, and randomly
would exceed this value 26.89 percent of the times.

Arithmetic mean value of data bytes is 127.4875 (127.5 = random).
Monte Carlo value for Pi is 3.145049164 (error 0.11 percent).
Serial correlation coefficient is -0.001040 (totally uncorrelated = 0.0).
```
The random bytes as image:

![noise](https://github.com/prgpascal/bluerand/blob/master/sample/output/multiRuns_output.bmp)

## License
BlueRand is licensed under [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0) license.
