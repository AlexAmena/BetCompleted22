package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.persistence.Table;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ApustuakIkusiGUI extends JFrame{
	private JTable table;
	//	public ApustuakIkusiGUI(JTable table) {
	//		this.setSize(new Dimension(604, 370));
	//		this.table = table;
	//		
	//		getContentPane().add(table, BorderLayout.CENTER);
	//	}
	//}


	public ApustuakIkusiGUI(JTable table) {
		setBounds(10,10,1000,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.table = table;
		//table.setAutoCreateRowSorter(true);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(600,280));
		JPanel panel = new JPanel();
		panel.add(scrollPane);
		add(panel,BorderLayout.CENTER);
	}
}