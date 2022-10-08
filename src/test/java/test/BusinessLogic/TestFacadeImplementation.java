package test.BusinessLogic;


import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import domain.Event;
import domain.Registered;
import domain.Sport;
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

		public Event addEvent(String description, Date d, Team l, Team k, Sport s) {
			dbManagerTest.open();
			Event e = dbManagerTest.addEvent(description,d,l,k,s);
			dbManagerTest.close();
			return e;
		}
		public Team addTeam(String t) {
			dbManagerTest.open();
			Team l = dbManagerTest.addTeam(t);
			dbManagerTest.close();
			return l;
		}
		public Sport addSport(String sportName) {
			dbManagerTest.open();
			Sport s = dbManagerTest.addSport(sportName);
			dbManagerTest.close();
			return s;
		}
		public boolean removeTeam(String teamName) {
			dbManagerTest.open();
			boolean b = dbManagerTest.removeTeam(teamName);
			dbManagerTest.close();
			return b;
		}
		public boolean removeSport(String sportName) {
			dbManagerTest.open();
			boolean b = dbManagerTest.removeSport(sportName);
			dbManagerTest.close();
			return b;
		}
		public Event findEventWithDescriptionAndDate(String description, Date d, String sportName) {
			dbManagerTest.open();
			Event e = dbManagerTest.findEventWithDescriptionAndDate(description,d,sportName);
			dbManagerTest.close();
			return e;
		}
		public Event findEventWhenDateIsNull(String description, String sportName) {
			dbManagerTest.open();
			Event e = dbManagerTest.findEventWhenDateIsNull(description,sportName);
			dbManagerTest.close();
			return e;
		}
		public Event findEventWithoutSport(String description, Date d) {
			dbManagerTest.open();
			Event e = dbManagerTest.findEventWithoutSport(description,d);
			dbManagerTest.close();
			return e;
		}
		public Event findEventWithDescriptionNull(Date d, String sportName) {
			dbManagerTest.open();
			Event e = dbManagerTest.findEventWithDescriptionNull(d,sportName);
			dbManagerTest.close();
			return e;
		}
		
		public List<Registered> deleteAllUsers(){
			dbManagerTest.open();
			List<Registered> e = dbManagerTest.deleteAllUsers();
			dbManagerTest.close();
			return e;
		}
		
		public void addUser(Registered r) {
			dbManagerTest.open();
			dbManagerTest.addUser(r);
			dbManagerTest.close();
		}
		public List<Registered> getAllUsers() {
			dbManagerTest.open();
			List<Registered> x =dbManagerTest.getAllUsers();
			dbManagerTest.close();
			return x;
		}
}
