package adapter;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import businessLogic.BLFacade;
import businessLogic.Factory;
import configuration.ConfigXML;
import domain.Registered;
import domain.User;
import gui.ApustuakIkusiGUI;

public class Main {

	public static void main(String[] args) {
		try {
			ConfigXML c=ConfigXML.getInstance();

			//Facade objektua lortu lehendabiziko ariketa erabiliz
			Factory f = new Factory();
			BLFacade facadeInterface;
			facadeInterface = f.createBLFacade(c.isBusinessLogicLocal());
			Registered u = new Registered("mikel","123",123);
			
			User user = facadeInterface.findUser(u);
			Registered r = (Registered)user;
			
			UserAdapter ua = new UserAdapter(r);
			JTable table = new JTable(ua);
			
			ApustuakIkusiGUI gui = new ApustuakIkusiGUI(table);
			gui.setVisible(true);
		
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
