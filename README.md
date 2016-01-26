# BlueRand
True Random Number Generator written in Java that uses the blue channel of pairs of images as input sources. 

##How it works

* It uses two different images (same pixel size). Because the two images are taken in two diffent time the two input are considered as independent. It than use the Mixing technique [fonte] so it is sufficient that one image only have to be umpredictable.
* One some of the total pixels are considered. The CSPRNG SecureRandom determines for each pixel considered how many consecutive pixels have to be discarded. So correlation between consecutive pixels is avoided. This also guarantee that if the generator is run twice, two different outputs are generated.
* The blue channel only is considered. This because can exist a correlation between the 3 RGB channels.
* Only the least significant bit of each byte is considered. The noise....
* You can choose to consider the second least significant bit too. (set it before the generation)

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

##Tests
A test result [ent](http://www.fourmilab.ch/random/) says:
made on input images 
```
Entropy = 7.998091 bits per byte.

Optimum compression would reduce the size
of this 95817 byte file by 0 percent.

Chi square distribution for 95817 samples is 253.11, and randomly
would exceed this value 52.16 percent of the times.

Arithmetic mean value of data bytes is 127.7242 (127.5 = random).
Monte Carlo value for Pi is 3.146346045 (error 0.15 percent).
Serial correlation coefficient is 0.003103 (totally uncorrelated = 0.0).
```


Output as an image 

![noise](https://github.com/prgpascal/bluerand/blob/master/sample/output/multiRuns_output.bmp)

## License

[Apache License 2.0][7]



[1]: sample/Sample.java
[7]: http://www.apache.org/licenses/LICENSE-2.0


