/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eyetracker;

/**
 *
 * @author Sai Prakash
 */
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import org.openimaj.math.geometry.shape.Rectangle;
 
class ImagePanel extends JPanel {
    private Image img;
    private ArrayList<Rectangle> facesDetected;
    
    public ImagePanel(String img) {
        this(new ImageIcon(img).getImage());
    }
    
    public ImagePanel(Image img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }
    
    public ImagePanel(Image img, ArrayList facesDetected) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
        this.facesDetected = facesDetected;
    }
    
    public void paintComponent(Graphics g) {
        g.drawImage(this.img, 0, 0, null);
        for(Rectangle face: facesDetected){
            g.drawRect((int)face.x, (int)face.y, (int)face.width, (int)face.height);
        }
    }
 }
