
package View;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.awt.event.ActionEvent;        

public class FilenameSelector {
	private static JTextField textField;
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
	static View view;
	public FilenameSelector(View v){
		this.view=v;
	}
    private static void createAndShowGUI() {
        //Create and set up the window.
        final JFrame frame = new JFrame("Choose a name for subset");
        frame.setSize(230, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        //Add the ubiquitous "Hello World" label.
//        JLabel label = new JLabel("Hello World");
//        frame.getContentPane().add(label);
        
        textField = new JTextField();
        textField.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        frame.getContentPane().add(textField, BorderLayout.NORTH);
        textField.setColumns(10);
        
        JButton btnNewButton =  new JButton("Ok");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
//        		System.out.println("in fs "+textField.getText());
        		view.xmlFilename=textField.getText();
        		try {
					view.writeSubsetXML();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		frame.dispose();
        	}
        });
        frame.getContentPane().add(btnNewButton, BorderLayout.SOUTH);

        //Display the window.
        //frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
