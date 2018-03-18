/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eyetracker;

import com.github.sarxos.webcam.Webcam;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import org.openimaj.image.FImage;
import org.openimaj.image.Image;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.SingleBandImage;
import org.openimaj.image.processing.face.detection.DetectedFace;
import org.openimaj.image.processing.face.detection.HaarCascadeDetector;
import org.openimaj.math.geometry.shape.Rectangle;

/**
 *
 * @author Sai Prakash
 */
public class FaceRecognizer {
    public void faceRecognizer() throws IOException, InterruptedException{
        JFrame fr  = new JFrame("Discovered Faces");;
        HaarCascadeDetector detector = new HaarCascadeDetector();
        List<DetectedFace> faces = null;
        Webcam webcam = Webcam.getDefault();
        webcam.open();
        while(true){
            BufferedImage image = webcam.getImage();
            //display the reading image
            ImagePanel p;
            
//            fr.repaint();
            faces = null;
            
            faces = detector.detectFaces(ImageUtilities.createFImage(image));
            if (faces == null) {
                System.out.println("No faces found in the captured image");
                continue;
            }
            Iterator <DetectedFace> dfi = faces.iterator();
            ArrayList facesDetected = new ArrayList<>();
           
            fr = new JFrame("Discovered Faces");
            while (dfi.hasNext()) {
                DetectedFace face = dfi.next();
                Rectangle faceBounds = face.getBounds();
                facesDetected.add(faceBounds);
//                fr.dispose();
                if(!dfi.hasNext()){
                    //print only after all faces detected
//                    fr.remove(p);
//                    fr.repaint();
                    p = new ImagePanel(image,facesDetected);
                    fr.add(p);
                }
                System.out.println("FOUND at: "+faceBounds);
                
                //draw box around detected faces
                
                
                fr.setLayout(new FlowLayout(0));
                fr.setSize(500, 500);
                fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                fr.setVisible(true);
            }
        }
//        webcam.close();
        
    }
}
