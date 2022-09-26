package test.BusinessLogic;


import java.util.Date;

import configuration.ConfigXML;
import domain.Event;
import domain.Team;
import test.DataAccess.TestDataAccess;

public class TestFacadeImplementation {
	TestDataAccess dbManagerTest;
 	
    
	   public TestFacadeImplementation()  {
			
			System.out.println("Creating TestFacadeImplementation instance");
			ConfigXML c=ConfigXML.getInstance();
			dbManagerTest=new TestDataAccess(); 
			dbManagerTest.close();
		}
		
		 
		public boolean removeEvent(Event ev) {
			dbManagerTest.open();
			boolean b=dbManagerTest.removeEvent(ev);
			dbManagerTest.close();
			return b;

		}
		
		public Event addEventWithQuestion(String desc, Date d, String q, float qty, Team lokal, Team kanpokoa) {
			dbManagerTest.open();
			Event o=dbManagerTest.addEventWithQuestion(desc,d,q, qty,lokal,kanpokoa);
			dbManagerTest.close();
			return o;

		}

}
