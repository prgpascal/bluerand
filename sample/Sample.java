 /*
 * Copyright (C) 2016 Riccardo Leschiutta
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
import java.util.ArrayList;
import com.prgpascal.bluerand.BlueRand; 


/**
 * Sample class for BlueRand.
 * It provides a sample of Single Run test and Multiple Runs test.
 */
public class Sample {    
    
    /** Main method */
    public static void main(String[] args){
        /* Single run test */
        singleRunTest();
        
        /* Multiple runs test */
        multiRunTest();
    }
    
    
    /**
     * Execute a single run test (2 input images only).
     * The output will be stored inside "singleRun_ab.txt" and "singleRun_ab.bmp".
     */
    public static void singleRunTest(){    	
        BlueRand random = new BlueRand("sample/input/a.jpg", "sample/input/b.jpg");
        random.createOutputFile(true);
        random.setOutputFile("sample/output/singleRun_ab.txt");
        random.createOutputImage(true);
        random.setOutputImage("sample/output/singleRun_ab.bmp");
        
        System.out.println("Single run started, please wait... ");
        ArrayList<Byte> output = random.generateRandom();
        System.out.println("Single run finished. Bytes generated: " + output.size());
    }
    
    
    /**
     * Execute a multiple runs test (more than 2 input images).
     * Append the output of each generation to the same output Files,
     * named "multiRuns_output.txt" and "multiRuns_output.bmp".
     */
    public static void multiRunTest(){
        /* Input resources.
         * For each String:
         *  - the first character is the name of the first image.
         *  - the second character is the name of the second image.
         */
        String[] inputs = new String[]{
            "ab",
            "cd",
        };
        
        BlueRand random = new BlueRand();
        random.createOutputFile(true);
        random.setOutputFile("sample/output/multiRuns_output.txt");
        random.createOutputImage(true);
        random.overwriteOutputFile(false);
        random.setOutputImage("sample/output/multiRuns_output.bmp");
        
        
        ArrayList<Byte> output = null;
        for (String s : inputs){
            random.setInputImages("sample/input/"+s.charAt(0)+".jpg",
                                  "sample/input/"+s.charAt(1)+".jpg");
                                  
            System.out.println("Multiple runs started, please wait...");
            output = random.generateRandom();
            System.out.println("run finished... Bytes generated: " + output.size());
        }
        System.out.println("Multiple runs finished.");
    }
    
}
