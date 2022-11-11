package gui;

import java.awt.Color;
import java.net.URL;
import java.util.Locale;
import java.util.Scanner;

import javax.swing.UIManager;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import businessLogic.Factory;
import configuration.ConfigXML;
import dataAccess.DataAccess;

public class ApplicationLauncher { 
	
	private static Factory logicFactory = new Factory();
	
	public static void main(String[] args) {

		ConfigXML c=ConfigXML.getInstance();
	
		System.out.println(c.getLocale());
		
		Locale.setDefault(new Locale(c.getLocale()));
		
		System.out.println("Locale: "+Locale.getDefault());
		
		MainGUI a=new MainGUI();
		//a.setVisible(false);
		a=new MainGUI();
		a.setVisible(false);

		
		MainUserGUI b = new MainUserGUI(); 
		//b.setVisible(true);
		
		b = new MainUserGUI(); 
		b.setVisible(true);


		try {
			
			
			BLFacade appFacadeInterface;
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			
			appFacadeInterface = logicFactory.createBLFacade(c.isBusinessLogicLocal());
		
			MainGUI.setBussinessLogic(appFacadeInterface);

		

			
		}catch (Exception e) {
			a.jLabelSelectOption.setText("Error: "+e.toString());
			a.jLabelSelectOption.setForeground(Color.RED);	
			
			System.out.println("Error in ApplicationLauncher: "+e.toString());
		}
		//a.pack();


	}

}
