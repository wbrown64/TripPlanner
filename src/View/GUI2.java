package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Location;
import Model.Model;
import presenter.SQLinterpreter;
import presenter.Writer;

import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class GUI2 extends JFrame {
	private static View view;
	private static Model model;
	private String[] list=new String[1];
	protected ArrayList<Location> backupItinerary;
	public boolean list3selected=false;
	public boolean list4selected=false;
	public boolean list5selected=false;
	public boolean list6selected=false;

	JRadioButton rdbtnId;
	JRadioButton rdbtnLocations;
	JRadioButton rdbtnMileage;
	JRadioButton rdbtn2opt;
	JRadioButton rdbtn3opt;
	JRadioButton rdbtnMiles;
	JRadioButton rdbtnkilos;
	JList<String> list1;
	JList<String> list2;
	JList<String> list3;
	JList<String> list4;
	JList<String> list5;
	JList<String> list6;
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI2 frame = new GUI2(model,view);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI2(Model model, View view) {
		this.view=view;
    	this.model=model;
    	backupItinerary=model.getItinerary();
    	
    	
		
		setBackground(new Color(173, 216, 230));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 849, 677);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scollPane1 = new JScrollPane();
		scollPane1.setBounds(6, 378, 290, 271);
		contentPane.add(scollPane1);
		
		list1 = new JList();
		scollPane1.setViewportView(list1);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(432, 378, 290, 271);
		contentPane.add(scrollPane2);
		
		list2 = new JList();
		scrollPane2.setViewportView(list2);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(409, 6, 12, 643);
		separator.setVisible(true);
		contentPane.add(separator);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] selected=list1.getSelectedValuesList().toArray(new String[0]);
				SQLinterpreter sqli=new SQLinterpreter("wbrown64","830285807");
				System.out.println("here");
				Writer writer=new Writer(selected,model,sqli);
				ArrayList<Location>temp=writer.createLocations();
				if(model.getItinerary()!=null)
				temp.addAll(model.getItinerary());
				model.setItinerary(temp);
				ArrayList<String>names=new ArrayList<String>();
				for(Location L:model.getItinerary()){
					//System.out.println(L);
					names.add(L.getBrewery());
				}
				setJList(list2,names.toArray(new String[0]));
				
			}
		});
		btnNewButton.setBounds(308, 378, 99, 271);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane3 = new JScrollPane();
		scrollPane3.setBounds(6, 22, 290, 62);
		contentPane.add(scrollPane3);
		
		list3 = new JList();
		scrollPane3.setColumnHeaderView(list3);
		scrollPane3.setViewportView(list3);
        String[] continents={"Europe","Asia","North America","Africa","Antarctica","South America","Oceania"};
		setJList(list3,continents);
		
		JLabel lblCountries = new JLabel("Continents");
		lblCountries.setBounds(98, 6, 80, 16);
		contentPane.add(lblCountries);
		
		JLabel lblNewLabel = new JLabel("Countries");
		lblNewLabel.setBounds(98, 86, 80, 16);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane4 = new JScrollPane();
		scrollPane4.setBounds(6, 106, 290, 62);
		contentPane.add(scrollPane4);
		
		list4 = new JList();
		scrollPane4.setColumnHeaderView(list4);
		scrollPane4.setViewportView(list4);
		
		JLabel lblRegion = new JLabel("Regions");
		lblRegion.setBounds(98, 169, 61, 16);
		contentPane.add(lblRegion);
		
		JScrollPane scrollPane5 = new JScrollPane();
		scrollPane5.setBounds(6, 185, 290, 70);
		contentPane.add(scrollPane5);
		
		list5 = new JList();
		scrollPane5.setColumnHeaderView(list5);
		scrollPane5.setViewportView(list5);
		
		JLabel lblMunicipality = new JLabel("Municipality");
		lblMunicipality.setBounds(98, 256, 99, 16);
		contentPane.add(lblMunicipality);
		
		JScrollPane scrollPane6 = new JScrollPane();
		scrollPane6.setBounds(6, 277, 290, 70);
		contentPane.add(scrollPane6);
		
		list6 = new JList();
		scrollPane6.setViewportView(list6);
		
		JLabel lblAirpots = new JLabel("Airports");
		lblAirpots.setBounds(98, 350, 61, 16);
		contentPane.add(lblAirpots);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					search();
				} catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnSearch.setBounds(308, 22, 99, 243);
		contentPane.add(btnSearch);
		
		JButton btnNewButton_1 = new JButton("Plan Trip");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					model.initializeModel();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	if(model.twoOpt)
		    		model.twoOpt();

				view.createSvg(model.getItinerary(),view.filename+".svg",view.SVG);
				view.createXML(model.getItinerary(),view.filename+".xml");
		    	
//		    	System.out.println("pressing generate map button ");
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
		});
		btnNewButton_1.setBounds(732, 56, 117, 243);
		contentPane.add(btnNewButton_1);
		
		JButton btnClear = new JButton(" Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setItinerary(null);
				clearJList(list2);
			}
		});
		btnClear.setBounds(732, 406, 117, 29);
		contentPane.add(btnClear);
		
		JButton btnLoad = new JButton(" Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLoad.setBounds(734, 441, 117, 29);
		contentPane.add(btnLoad);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSave.setBounds(734, 474, 117, 29);
		contentPane.add(btnSave);
		
		JLabel lblOptions = new JLabel("Options");
		lblOptions.setBounds(432, 24, 61, 16);
		contentPane.add(lblOptions);
		
		rdbtnMileage = new JRadioButton("Distance");
		rdbtnMileage.setBounds(432, 47, 141, 23);
		contentPane.add(rdbtnMileage);
		
		rdbtnId = new JRadioButton("ID");
		rdbtnId.setBounds(432, 70, 141, 23);
		contentPane.add(rdbtnId);
		
		rdbtnLocations = new JRadioButton("Locations");
		rdbtnLocations.setBounds(432, 92, 141, 23);
		contentPane.add(rdbtnLocations);
		
		rdbtn2opt = new JRadioButton("2-Opt");
		rdbtn2opt.setBounds(432, 117, 141, 23);
		contentPane.add(rdbtn2opt);
		
		rdbtn3opt = new JRadioButton("3-Opt");
		rdbtn3opt.setBounds(432, 138, 141, 23);
		contentPane.add(rdbtn3opt);
		
		rdbtnMiles = new JRadioButton("Miles");
		rdbtnMiles.setBounds(432, 165, 141, 23);
		contentPane.add(rdbtnMiles);
		
		rdbtnkilos = new JRadioButton("Kilometers");
		rdbtnkilos.setBounds(432, 183, 141, 23);
		contentPane.add(rdbtnkilos);
		
        checkFlags();

		
		JLabel lblSelectedAirports = new JLabel(" Selected Airports");
		lblSelectedAirports.setBounds(518, 350, 117, 16);
		contentPane.add(lblSelectedAirports);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] empty={};
				list3.clearSelection();
				list4.clearSelection();
				setJList(list4,empty);
				list5.clearSelection();
				setJList(list5,empty);
				list6.clearSelection();
				setJList(list6,empty);
				list1.clearSelection();
				setJList(list1,empty);;
			}
		});
		btnReset.setBounds(312, 274, 95, 73);
		contentPane.add(btnReset);
	}
	private void checkFlags(){
    	if(view.showID){
    		rdbtnId.setSelected(true);
    	}
    	if(view.showMileage){
    		rdbtnMileage.setSelected(true);
    	}
    	if(view.showName){
    		rdbtnLocations.setSelected(true);
    	}
    	if(model.twoOpt)
    		rdbtn2opt.setSelected(true);
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
   private void setJList(JList<String> list,String[] array){
     list.setModel(new javax.swing.AbstractListModel<String>() {
     String[] strings = array;
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
	   setJList(list2);
	}
   private void search() throws ClassNotFoundException, SQLException{
	   list3selected=!list3.getSelectedValuesList().isEmpty();
	   list4selected=!list4.getSelectedValuesList().isEmpty();
	   list5selected=!list5.getSelectedValuesList().isEmpty();
	   list6selected=!list6.getSelectedValuesList().isEmpty();
	   //System.out.println(list3selected+" "+list4selected+" "+list5selected);
	   SQLinterpreter sqli=new SQLinterpreter("wbrown64","830285807");
	   if(list3selected&&!list4selected&&!list5selected){
		   String[] selected=list3.getSelectedValuesList().toArray(new String[0]);
		  selected=sqli.selectContinents(selected);	
		  selected=sqli.selectCountries(selected);
		  setJList(list4,selected);
	   }
	   else if(list3selected&&list4selected&&!list5selected){
		   String[] selected=list4.getSelectedValuesList().toArray(new String[0]);
		   selected=sqli.selectCountryCodes(selected);
		   selected=sqli.selectReigons(selected);
		   setJList(list5,selected);
	   }
	   else if(list3selected&&list4selected&&list5selected&&!list6selected){
		   String[]selected=list5.getSelectedValuesList().toArray(new String[0]);
		   selected=sqli.selectRegionCode(selected);
		   //selected=sqli.selectAirports(selected);
		   selected=sqli.selectMunicipality(selected);
		   setJList(list6,selected);
		   //Writer w=new Writer(selected,model,sqli);
	   }
	   else if(list6selected){
		   String[] selected=list6.getSelectedValuesList().toArray(new String[0]);
		   selected=sqli.selectAirports(selected);
		   setJList(list1,selected);
	   }
	   
//		  for(String S:selected){
//		  System.out.println(S);
//	  }
		  
   }
}
