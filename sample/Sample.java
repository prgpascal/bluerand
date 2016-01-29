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
import com.prgpascal.bluerand.BlueRandException; 


/**
 * Sample class for BlueRand.
 * It provides samples for single and multiple runs.
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
     * The output will be stored into "singleRun_ab.txt" and "singleRun_ab.bmp".
     */
    public static void singleRunTest(){    	
        BlueRand random = new BlueRand("sample/input/a.jpg", "sample/input/b.jpg");
        random.createOutputFile(true);
        random.setOutputFile("sample/output/singleRun_ab.txt");
        random.createOutputImage(true);
        random.setOutputImage("sample/output/singleRun_ab.bmp");
        random.deleteInputFiles(false);
        
        try {
	        System.out.println("Single run started, please wait... ");
	        ArrayList<Byte> output = random.generateRandom();
	        System.out.println("Single run finished. Bytes generated: " + output.size());
        } catch(BlueRandException e){
        	System.out.println(e.getMessage());
        }
    }
    
    
    /**
     * Make a multiple run (with more than 2 input images).
     * It appends the output of each generation to the output files named "multiRuns_output.txt" 
     * and "multiRuns_output.bmp".
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
        random.deleteInputFiles(false);  
        
        ArrayList<Byte> output = null;
        try {
	        for (String s : inputs){
	            random.setInputImages("sample/input/"+s.charAt(0)+".jpg",
	                                  "sample/input/"+s.charAt(1)+".jpg");      
	            System.out.println("Multiple runs started, please wait...");
	            output = random.generateRandom();
	            System.out.println("run finished... Bytes generated: " + output.size());
	        }
        } catch (BlueRandException e){
        	System.out.println(e.getMessage());
        }
        System.out.println("Multiple runs finished.");
    }
    
}
