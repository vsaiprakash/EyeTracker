/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eyetracker;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import org.openimaj.image.FImage;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.processing.face.detection.DetectedFace;
import org.openimaj.image.processing.face.detection.HaarCascadeDetector;
import org.openimaj.video.VideoDisplay;
import org.openimaj.video.VideoDisplayListener;
import org.openimaj.video.capture.VideoCapture;

/**
 *
 * @author Sai Prakash
 */
public class Demo{
    static String outputFolderPath = "C:\\Users\\DELL\\Desktop\\images\\";
    
    public static void main(String args[]) throws IOException, InterruptedException{
//        gettingImageFromWebcam();
//        recognizeFaceInImage();
        readingImageAndDetectingFace();
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
    
    public static void recognizeFaceInImage() throws IOException{
        HaarCascadeDetector detector = new HaarCascadeDetector();
        List<DetectedFace> faces = null;
        File file = new File(outputFolderPath+"hello0"+".png");
        BufferedImage image = ImageIO.read(file);
        detector.detectFaces(ImageUtilities.createFImage(image));
       faces = detector.detectFaces(ImageUtilities.createFImage(image));
       if (faces == null) {
        System.out.println("No faces found in the captured image");
        return;
       }
       Iterator <DetectedFace> dfi = faces.iterator();
       while (dfi.hasNext()) {
        DetectedFace face = dfi.next();
        System.out.println("FOUND at: "+face.getBounds());
       }
    }
    
    public static void readingImageAndDetectingFace() throws IOException, InterruptedException{
        HaarCascadeDetector detector = new HaarCascadeDetector();
        List<DetectedFace> faces = null;
        
        Webcam webcam = Webcam.getDefault();
        webcam.open();
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        while(true){
            
            faces = null;
            BufferedImage image = webcam.getImage();
            faces = detector.detectFaces(ImageUtilities.createFImage(image));
            if (faces == null) {
                System.out.println("No faces found in the captured image");
                continue;
            }
            Iterator <DetectedFace> dfi = faces.iterator();
            if(dfi.hasNext()){
                date = calendar.getTime();
                String dateString = date.toString().replaceAll(" ","").replaceAll(":","");
                ImageIO.write(image, "PNG", new File((outputFolderPath+dateString+".png")));
            }
            while (dfi.hasNext()) {
                DetectedFace face = dfi.next();
                System.out.println("FOUND at: "+face.getBounds());
            }
            //sleep after every second to avoid/delay Date collision
            Thread.sleep(1000);
        }
//        webcam.close();
        
        
    }
}
