package domain;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import businessLogic.BLFacade;
import gui.MainGUI;

public class ElkarrizketaRenderer extends JLabel implements ListCellRenderer<ElkarrizketaContainer>{

	private BLFacade businessLogic = MainGUI.getBusinessLogic();
	private User user; 
	private Boolean bool;
	 public ElkarrizketaRenderer(User u) {
		  setOpaque(true);
		  user=u;
	    }
	 
	    @Override
	    public Component getListCellRendererComponent(JList<? extends ElkarrizketaContainer> list, ElkarrizketaContainer elkarrizketa, int index,
	            boolean isSelected, boolean cellHasFocus) {
	 
	    	setText(elkarrizketa.toString());
	    	String code = "m1"; 
	    	
	    	 ImageIcon imageIcon = new ImageIcon(".\\src/main/resources\\data\\"+elkarrizketa.getElka().denakIrakurrita(user)+".png"); // load the image to a imageIcon
		     Image image = imageIcon.getImage(); // transform it 
		     Image newimg = image.getScaledInstance(25, 25, Image.SCALE_SMOOTH); // scale it the smooth way  
		     imageIcon = new ImageIcon(newimg);
	         setIcon(imageIcon);
	         
	         
	        	if (!elkarrizketa.getElka().denakIrakurrita(user) && !isSelected) {
	 	             setBackground(list.getSelectionBackground().PINK);
	 	           	 setForeground(list.getSelectionForeground().BLACK);
	 	            
	 	        } else if(isSelected) {
	 	             setBackground(list.getSelectionBackground().gray);
	 		         setForeground(list.getSelectionForeground().WHITE);
	 	        }else {
	 	        	 setBackground(list.getBackground());
	 		         setForeground(list.getForeground());
	 	        }
	        return this;
	    }
	    
}