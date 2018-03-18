/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eyetracker;

import com.github.sarxos.webcam.Webcam;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.openimaj.image.MBFImage;
import org.openimaj.video.VideoDisplay;
import org.openimaj.video.VideoDisplayListener;
import org.openimaj.video.capture.VideoCapture;

/**
 *
 * @author Sai Prakash
 */
public class Demo {
    static String outputFolderPath = "C:\\Users\\DELL\\Desktop\\images\\";
    
    public static void main(String args[]) throws IOException{
        gettingImageFromWebcam();
    }
    
    public static void gettingImageFromWebcam() throws IOException{
        Webcam webcam = Webcam.getDefault();
        webcam.open();
        int number = 0;
        String fileName = "hello";
        while(number < 10){
            ImageIO.write(webcam.getImage(), "PNG", new File(outputFolderPath+fileName+number+".png"));
            number++;
        }
        webcam.close();
    }
}
