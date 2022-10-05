package test.DataAccess;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import domain.Event;
import domain.Question;
import domain.Registered;
import domain.Sport;
import domain.Team;

public class TestDataAccess {
	protected  EntityManager  db;
	protected  EntityManagerFactory emf;

	ConfigXML  c=ConfigXML.getInstance();


	public TestDataAccess()  {
		
		System.out.println("Creating TestDataAccess instance");

		open();
		
	}

	
	public void open(){
		
		System.out.println("Opening TestDataAccess instance ");

		String fileName=c.getDbFilename();
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
		
	}
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}

	public boolean removeEvent(Event ev) {
		System.out.println(">> DataAccessTest: removeEvent");
		Event e = db.find(Event.class, ev.getEventNumber());
		if (e!=null) {
			db.getTransaction().begin();
			db.remove(e);
			db.getTransaction().commit();
			return true;
		} else 
		return false;
    }
	
		public Event addEventWithQuestion(String desc, Date d, String question, float qty, Team lok, Team kanpokoa) {
			System.out.println(">> DataAccessTest: addEvent");
			Event ev=null;
				db.getTransaction().begin();
				try {
				    ev=new Event(desc,d,lok,kanpokoa);
				    ev.addQuestion(question, qty);
					db.persist(ev);
					db.getTransaction().commit();
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return ev;
	    }
		public boolean existQuestion(Event ev,Question q) {
			System.out.println(">> DataAccessTest: existQuestion");
			Event e = db.find(Event.class, ev.getEventNumber());
			if (e!=null) {
				return e.doesQuestionExists(q.getQuestion());
			} else 
			return false;
			
		}
		
		public Event addEvent(String description, Date d, Team l, Team k, Sport s) {
			db.getTransaction().begin();
			Event ev = new Event(description,d,l,k);
			ev.setSport(s);
			ev.setLokala(l);
			ev.setKanpokoa(k);
			db.persist(ev);
			db.getTransaction().commit();
			return ev;
		}
		public Team addTeam(String t) {
			db.getTransaction().begin();
			Team l = new Team(t);
			db.persist(l);
			db.getTransaction().commit();
			return l;
		}
		public Sport addSport(String sportName) {
			db.getTransaction().begin();
			Sport s = new Sport(sportName);
			db.persist(s);
			db.getTransaction().commit();
			return s;
		}
		public boolean removeTeam(String teamName) {
			System.out.println(">> DataAccessTest: removeTeam");
			Team team = db.find(Team.class, teamName);
			if (team!=null) {
				db.getTransaction().begin();
				db.remove(team);
				db.getTransaction().commit();
				return true;
			} else 
			return false;
		}
		public boolean removeSport(String sportName) {
			System.out.println(">> DataAccessTest: removeSport");
			Sport s = db.find(Sport.class, sportName);
			if (s!=null) {
				db.getTransaction().begin();
				db.remove(s);
				db.getTransaction().commit();
				return true;
			} else 
			return false;
		}
		public Event findEventWithDescriptionAndDate(String description, Date d, String sportName) {
			System.out.println(">> DataAccessTest: findEvent");
			//db.getTransaction().begin();
			System.out.println("hola");
			TypedQuery<Event> query = null;
			try {
			query = db.createQuery("SELECT e FROM Event e",Event.class);
			}catch(Exception e) {
				e.printStackTrace();
			}
			System.out.println("hola");
			
			List<Event> events = query.getResultList();
			Event event=null;
			for(Event e : events) {
				if(e.getDescription().equals(description) && e.getEventDate().equals(d) && e.getSport().getIzena().equals(sportName)) {
					event = e;
				}
			}
			//db.getTransaction().commit();
			return event;
		}
		public Event findEventWhenDateIsNull(String description, String sportName) {
			System.out.println(">> DataAccessTest: findEvent");
			//db.getTransaction().begin();
			System.out.println("hola");
			TypedQuery<Event> query = null;
			try {
			query = db.createQuery("SELECT e FROM Event e",Event.class);
			}catch(Exception e) {
				e.printStackTrace();
			}
			System.out.println("hola");
			
			List<Event> events = query.getResultList();
			Event event=null;
			for(Event e : events) {
				if(e.getDescription().equals(description) && e.getSport().getIzena().equals(sportName)) {
					event = e;
				}
			}
			//db.getTransaction().commit();
			return event;
		}
		public Event findEventWithoutSport(String description, Date d) {
			System.out.println(">> DataAccessTest: findEvent");
			System.out.println("hola");
			TypedQuery<Event> query = null;
			try {
			query = db.createQuery("SELECT e FROM Event e",Event.class);
			}catch(Exception e) {
				e.printStackTrace();
			}
			System.out.println("hola");
			
			List<Event> events = query.getResultList();
			Event event=null;
			for(Event e : events) {
				if(e.getDescription().equals(description) && e.getEventDate().equals(d)) {
					event = e;
				}
			}
			return event;
		}
		public Event findEventWithDescriptionNull(Date d, String sportName) {
			System.out.println(">> DataAccessTest: findEvent");
			System.out.println("hola");
			TypedQuery<Event> query = null;
			try {
			query = db.createQuery("SELECT e FROM Event e",Event.class);
			}catch(Exception e) {
				e.printStackTrace();
			}
			System.out.println("hola");
			
			List<Event> events = query.getResultList();
			Event event=null;
			for(Event e : events) {
				if(e.getEventDate().equals(d) && e.getSport().getIzena().equals(sportName)) {
					event = e;
				}
			}
			return event;
		}
		
		public Registered addUser(Registered user) {
			db.getTransaction().begin();
			db.persist(user);
			db.getTransaction().commit();
			return user;
		}
		
		public boolean deleteUser(Registered user) {
			System.out.println(">> DataAccessTest: removeSport");
			user= db.find(Registered.class, user);
			if (user!=null) {
				db.getTransaction().begin();
				db.remove(user);
				db.getTransaction().commit();
				return true;
			} else 
			return false;
		}
		
		public List<Registered> getAllUsers(){
			TypedQuery<Registered> Rquery = db.createQuery("SELECT r FROM Registered r", Registered.class);
			List<Registered> listR = Rquery.getResultList();
			return listR;
		}
}

