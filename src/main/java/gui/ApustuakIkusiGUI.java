package gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.BorderLayout;

public class ApustuakIkusiGUI extends JFrame{
	private JTable table;
	public ApustuakIkusiGUI(JTable table) {
		this.setSize(new Dimension(604, 370));
		this.table = table;
		getContentPane().add(table, BorderLayout.CENTER);
	}
}
