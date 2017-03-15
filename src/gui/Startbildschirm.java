package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class Startbildschirm extends JDialog implements ActionListener {
	
	JPanel jpAuswahl;
	JPanel jpNamen;
	JButton jbAvailability;
	JButton jbGetList;
	JButton jbMaterialRequirementPlanning;
	
	public Startbildschirm() {
		
//		setLayout(new GridLayout(2, 1));
		setLayout(null);
		
		getContentPane().setSize(800, 500);
		getContentPane().setBackground(Color.WHITE);
		
		jpAuswahl = new JPanel();
		jpNamen = new JPanel();
		jbAvailability = new JButton("Availability");
		jbGetList = new JButton("GetList");
		jbMaterialRequirementPlanning = new JButton("MaterialRequirementPlanning");
		
		jpAuswahl.add(jbAvailability);
		jpAuswahl.add(jbGetList);
		jpAuswahl.add(jbMaterialRequirementPlanning);
		
		jpNamen.add(new JLabel("Bastian Schattenberg, Jan Schönbrunn, Jens Fischer"));
		jpNamen.setBounds(0, 100, 100, 100);
//		jpNamen.setLocation(100, 100);
		
//		getContentPane().add(jpAuswahl);
		getContentPane().add(jpNamen);
	}
	
	public static void main(String[] args) {
		Startbildschirm startbildschirm = new Startbildschirm();
		startbildschirm.setSize(800, 500);
		startbildschirm.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbAvailability) {
			GUI_Get_List get_Availability = new GUI_Get_List();
		}
		if (e.getSource() == jbGetList) {
			GUI_Availability get_availability = new GUI_Availability();
		}
		if (e.getSource() == jbMaterialRequirementPlanning) {
			GUI_Material_Requirement_Planning material_Requirement_Planning = new GUI_Material_Requirement_Planning();
		}
	}
	
}
