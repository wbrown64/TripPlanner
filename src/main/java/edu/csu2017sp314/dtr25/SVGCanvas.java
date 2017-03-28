package main.java.edu.csu2017sp314.dtr25;

import org.apache.batik.swing.*;
import org.apache.batik.swing.svg.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.Event.*;
import java.io.File;

public class SVGCanvas {

  JSVGCanvas svgCanvas = new JSVGCanvas();

  public static void main(String args[]) {
    JFrame frame = new JFrame("Your Trip of Colorado");
    frame.setSize(900, 900);

    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent ev) {
        System.exit(0);
      }
    });
    File f=new File("backgroundmap.svg");
    String s=f.getAbsolutePath();
    new SVGCanvas(frame,s);
  }

  public SVGCanvas(JFrame frame,String filename) {
    frame.getContentPane().setLayout(new BorderLayout());
    frame.getContentPane().add("Center", svgCanvas);
    frame.setVisible(true);
    svgCanvas.setURI(filename);
  }
}