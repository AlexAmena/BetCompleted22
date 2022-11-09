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

	public ApustuakIkusiGUI(JTable table) {
		setBounds(10,10,1000,400);
		JScrollPane scrollPane = new JScrollPane(table);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.table = table;
		JPanel panel = new JPanel();
		getContentPane().add(panel,BorderLayout.NORTH);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		scrollPane.setPreferredSize(new Dimension(600,280));
	}
}