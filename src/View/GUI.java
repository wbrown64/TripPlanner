/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
//This is for testing purposes
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;

import org.apache.batik.swing.JSVGCanvas;

import Model.Location;
import Model.Model;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 *
 * @author wbrown
 */
   class MyCustomFilter extends javax.swing.filechooser.FileFilter {
        @Override
        public boolean accept(File file) {
            // Allow only directories, or files with ".txt" extension
            return file.isDirectory() || file.getAbsolutePath().endsWith(".csv");
        }
        @Override
        public String getDescription() {
            // This description will be displayed in the dialog,
            // hard-coded = ugly, should be done via I18N
            return "CSV documents (*.csv)";
        }
    }
public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form GUIexampleUI
     * @param model 
     */
   


    public GUI(View v, Model model) {
    	this.view=v;
    	this.model=model;
    	backupItinerary=model.getItinerary();
    	initComponents();
        

	}


	/**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        fileChooser2= new javax.swing.JFileChooser();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();        
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new JList(model.getItinerary().toArray());
        jList2 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        OpenCSV = new javax.swing.JMenuItem();
        OpenXML = new javax.swing.JMenuItem();
        Exit = new javax.swing.JMenuItem();

        checkFlags();
        
        fileChooser.setDialogTitle("Choose a file");


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jRadioButton1.setText("Mileage");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("ID");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jRadioButton3.setText("Names");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jRadioButton4.setText("2-Opt");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        jRadioButton5.setText("3-Opt");
        
        jScrollPane1.setViewportView(jList1);

        jScrollPane2.setViewportView(jList2);


        jButton1.setText("Add Selected");
        jButton1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int[] indicies=jList1.getSelectedIndices();
        		ArrayList<Location>new_itinerary=new ArrayList<Location>();
        		for(int i=0;i<indicies.length;i++){
        			String name=jList1.getModel().getElementAt(indicies[i]);
        			for(Location L:model.getItinerary()){
//    					System.out.println("name from jList "+name);
//        				System.out.println("comparing to this from itinerary "+L.getBrewery());
        				if(L.getBrewery().equals(name)){
//        					System.out.println("success");
        					new_itinerary.add(L);
        				}
        			}
        		}
        		model.setItinerary(new_itinerary);
        		for(Location L:model.getItinerary()){
        			System.out.println(L.getBrewery());
        		}
        		System.out.println("");
        		setJList(jList2);
        	}
        });

        jButton2.setText("Remove All");
        jButton2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		clearJList(jList2);
        		model.setItinerary(backupItinerary);
        		
        	}
        });
        jLabel1.setText("Options");

        jButton3.setText("Generate Map");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					jButton3ActionPerformed(evt);
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        });

        jMenu1.setText("Choose a File");
        
        

        OpenCSV.setText("Select an input CSV file");
        OpenCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					OpenActionPerformed(evt);
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        });
        jMenu1.add(OpenCSV);
        
        OpenXML.setText("Select a subset XML file");
        OpenXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					OpenActionPerformed(evt);
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        });
        jMenu1.add(OpenXML);
        
        Exit.setText("Exit");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
        jMenu1.add(Exit);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);
        btnChooseSubsetFile = new JButton("Save Subset to XML");
        btnChooseSubsetFile.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//System.out.println("clicking add .xml file button");
        			FilenameSelector fs=new FilenameSelector(view);
        			fs.main(null);
        			
		
        		
        	}
        });
       
        
        setJList(jList1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(176, 176, 176)
        					.addComponent(jButton3))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jButton1)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(jButton2)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnChooseSubsetFile))
        				.addComponent(jLabel1)
        				.addComponent(jRadioButton5)
        				.addComponent(jRadioButton4)
        				.addComponent(jRadioButton3)
        				.addComponent(jRadioButton2)
        				.addComponent(jRadioButton1))
        			.addContainerGap(295, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(16)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
        				.addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                        .addComponent(jScrollPane1))
        				.addComponent(jButton3)
        				//.addComponent(jList1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jButton1)
        				.addComponent(jButton2)
        				.addComponent(btnChooseSubsetFile))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jLabel1)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jRadioButton1)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jRadioButton2)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jRadioButton3)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jRadioButton4)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jRadioButton5)
        			.addContainerGap(149, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>                        

    private void OpenActionPerformed(java.awt.event.ActionEvent evt) throws Exception {                                     
    int returnVal = fileChooser.showOpenDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        //
        
       // System.out.println(file.getAbsolutePath());
        String prefix=file.getName().substring(file.getName().length()-4,file.getName().length());
        if(prefix.equalsIgnoreCase(".csv")){
        model.filename=file.getName();
        model.newItinerary();
        setJList(jList1);
        }
        else if(prefix.equalsIgnoreCase(".xml")){
        	view.XML=file.getName();
        	System.out.println(file.getName());
        	generateSubset();
        }
        
    } else {
        System.out.println("File access cancelled by user.");
    }    }                                    


	


	private void ExitActionPerformed(java.awt.event.ActionEvent evt) {                                     
        System.exit(0);
    }                                    

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                              
    	if(view.showMileage)
    		view.showMileage=false;
    	else
    		view.showMileage=true;
    	//System.out.println("mileage is now: "+view.showMileage);
    } 
    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {   
    	if(view.showID)
    		view.showID=false;
    	else
    		view.showID=true;
    //	System.out.println("ID is now: "+view.showID);    
    	}                                             

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                              
    	if(view.showName)
    		view.showName=false;
    	else
    		view.showName=true;
    	//System.out.println("names is now: "+view.showName);    
    	}                                             
    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                              
    		if(model.twoOpt)
    			model.twoOpt=false;
    		else
    			model.twoOpt=true;
//    		System.out.println("twoOpt is now "+model.twoOpt);
    	}
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
    	model.initializeModel();
    	if(model.twoOpt)
    		model.twoOpt();

		view.createSvg(model.getItinerary(),view.filename+".svg",view.SVG);
		view.createXML(model.getItinerary(),view.filename+".xml");
    	
//    	System.out.println("pressing generate map button ");
        JFrame frame = new JFrame("Trip");
        frame.setSize(1200, 700);

        frame.addWindowListener(new WindowAdapter() {
        	
          public void windowClosing(WindowEvent ev) {
            System.exit(0);
          }
        });
       // System.out.println(view.filename+".svg");
        new SVGCanvas(frame,view.filename+".svg");
        
    } 
    /**
     */
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI(view,model).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JMenuItem Exit;
    private javax.swing.JMenuItem OpenCSV;
    private javax.swing.JMenuItem OpenXML;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JFileChooser fileChooser2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;

    // End of variables declaration  
	private static View view;
	private static Model model;
	private String[] list=new String[1];
	protected ArrayList<Location> backupItinerary;

	private JButton btnChooseSubsetFile;
	private String subsetFilename="";

    private void checkFlags(){
    	if(view.showID){
    		jRadioButton2.setSelected(true);
    	}
    	if(view.showMileage){
    		jRadioButton1.setSelected(true);
    	}
    	if(view.showName){
    		jRadioButton3.setSelected(true);
    	}
    	if(model.twoOpt)
    		jRadioButton4.setSelected(true);
    }
   private void initjLists(){
    	for(int i=0;i<model.getItinerary().size();i++){
    		list[i]=model.getItinerary().get(i).getBrewery();
    		//System.out.println(view.itinerary.get(i).getBrewery());
    		//System.out.println(list[i]);
    		list=Arrays.copyOf(list, list.length+1);
    	}

    }
   private String[] createArray(){
	   String names[]=new String[1];
		for(int i=0;i<model.getItinerary().size();i++){
    		names[i]=model.getItinerary().get(i).getBrewery();
    		//System.out.println(view.itinerary.get(i).getBrewery());
    		//System.out.println(list[i]);
    		names=Arrays.copyOf(names, names.length+1);
    	}
		return names;
   }
   private void setJList(JList<String> list){
	   String[] names=createArray();
     list.setModel(new javax.swing.AbstractListModel<String>() {
     String[] strings = names;
     public int getSize() { return strings.length; }
     public String getElementAt(int i) { return strings[i]; }
     });
   }
   private void clearJList(JList<String> list){
	   list.setModel(new javax.swing.AbstractListModel<String>() {
		     String[] strings ={""};
		     public int getSize() { return strings.length; }
		     public String getElementAt(int i) { return strings[i]; }
		     });
   }
   
   private void generateSubset() {
	   view.readSubsetXML();
	   setJList(jList2);
	}
//   private void saveSubset(){
//	  for(Location L:model.getItinerary()){
//		  System.out.println(L.getId());
//	  }
//   }
    
}
