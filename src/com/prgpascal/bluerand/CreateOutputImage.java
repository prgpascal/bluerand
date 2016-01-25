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

package com.prgpascal.bluerand;

import java.io.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.color.ColorSpace;
import java.awt.*;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;
import java.lang.Math;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;


public class CreateOutputImage {
    // Input / Output
    private File outputImage;
    private ArrayList<Byte> bytes;
    private File inputFile;
    
    
    /**
     * Constructor.
     *
     * @param output the output File.
     * @param bytes the input bytes.
     */
    public CreateOutputImage(File output, ArrayList<Byte> bytes){
        this.outputImage = output;
        this.bytes = bytes;
    }
    
    
    /**
     * Constructor.
     *
     * @param output the output File.
     * @param bytes the input File that contains random bytes.
     */
    public CreateOutputImage(File output, File inputFile){
        this.outputImage = output;
        this.inputFile = inputFile;
    }
    
    
    /**
     * Create the output image.
     *
     * @return true if the image is created succesfully.
     */
    public boolean create(){
        
        try {
            // Delete the outputImage if already exists.
            if (outputImage.exists()){
                outputImage.delete();
            }
            
            // Create new outputImage
            outputImage.createNewFile();
            
            // Check if I need to read bytes from input file
            if (inputFile != null){
                // Must read random bytes from external File.
                readFromInputFile();
            }

            // Output image size
            int outputImageSize = (int)Math.sqrt(bytes.size())+1;
            byte[] byteArray = new byte[outputImageSize*outputImageSize];
            
            // Convert to byte Array
            int i=0;
            for (Byte s : bytes){
                byteArray[i] = s.byteValue();
                i++;
            }
            
            // Write to output image
            OutputStream stream = new FileOutputStream(outputImage);
            BufferedImage imageout = getGrayscale(outputImageSize, outputImageSize, byteArray);
            
            try {
                ImageIO.write(imageout, "BMP", stream);
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                stream.close();
            }
            
            // Finished!!
            return true;
 
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return false;
    }

    
    /**
     * Create a grayscale output image.
     *
     * @param width the width of the output image.
     * @param height the height of the output image.
     * @param buffer the input bytes.
     * @return output grayscale BufferedImage.
     */
    private BufferedImage getGrayscale(int width, int height, byte[] buffer) {
        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        int[] nBits = { 8 };
        ColorModel cm = new ComponentColorModel(cs, nBits, false, true,
                Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
        SampleModel sm = cm.createCompatibleSampleModel(width, height);
        DataBufferByte db = new DataBufferByte(buffer, width * height);
        WritableRaster raster = Raster.createWritableRaster(sm, db, null);
        BufferedImage result = new BufferedImage(cm, raster, false, null);

        return result;
    }
    
    
    /**
     * Read the random bytes from an external input File and store them into
     * an ArrayList<Byte>.
     */
    private boolean readFromInputFile(){
        // Instantiate the ArrayList
        bytes = new ArrayList<Byte>();
      
        try{
            FileInputStream in = new FileInputStream(inputFile);
            byte[] tmp = new byte[1];

            while(in.read(tmp) != -1){
                bytes.add(new Byte(tmp[0]));
            }
        
            in.close();
            
            // Finished!!
            return true;
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return false;
    }
}
