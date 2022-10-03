package test.DataAccess;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import domain.ApustuAnitza;
import domain.Apustua;
import domain.Event;
import domain.KirolEstatistikak;
import domain.Question;
import domain.Quote;
import domain.Registered;
import domain.Sport;
import domain.Team;
import domain.User;

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
		
		public Event addEventWithQuestionAndSport(String desc, Date d, String question, float qty, Team lok, Team kanpokoa, Sport s) {
			System.out.println(">> DataAccessTest: addEvent");
			Event ev=null;
				db.getTransaction().begin();
				try {
				    ev=new Event(desc,d,lok,kanpokoa);
				    ev.addQuestion(question, qty);
				    ev.setSport(s);
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
		
		public Registered addUser(String name,String pass,Integer bank,Sport sp) {
			db.getTransaction().begin();
			Registered u=new Registered(name,pass,bank);
			u.kirolEstatistikakLortu(sp);
			db.persist(u);
			db.getTransaction().commit();
			return u;
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
		
		
		public boolean addQuestiontoEvent(Event ev,String desc, double betMin) {
			System.out.println(">> PROBATZEN");
			Event e = db.find(Event.class, ev.getEventNumber());
			if (e!=null) {
				db.getTransaction().begin();
				e.addQuestion(desc,betMin);
				db.getTransaction().commit();
				return true;
			} else 
			return false;
			
		}
		
		public boolean setQuestionResult(Question quest, String res) {
			System.out.println(">> setQuestionResult");
			Question q = db.find(Question.class, quest.getQuestionNumber());
			if (q!=null) {
				db.getTransaction().begin();
				q.setResult(res);
				db.getTransaction().commit();
				
				return true;
			} else 
			return false;
			
		}
		
		public boolean questionExistitzenDa(Question quest) {
			System.out.println(">> existQuestion");
			Question q = db.find(Question.class, quest.getQuestionNumber());
			if (q!=null) {
				return true;
			} else 
			return false;
			
		}
		
		public boolean kuotaExistitzenDa(Quote kuota) {
			System.out.println(">> existQuote");
			Quote q = db.find(Quote.class, kuota.getQuoteNumber());
			if (q!=null) {
				return true;
			} else 
			return false;
			
		}
		
		public boolean apustuaExistitzenDa(Apustua a) {
			System.out.println(">> existApustu");
			Apustua q = db.find(Apustua.class, a.getApostuaNumber() );
			if (q!=null) {
				return true;
			} else 
			return false;
			
		}
		
		public boolean apustuAnitzaExistitzenDa(ApustuAnitza a) {
			System.out.println(">> existApustuAnitza");
				ApustuAnitza q = db.find(ApustuAnitza.class, a.getApustuAnitzaNumber() );
				if (q!=null) {
					return true;
				} else 
				return false;
		}
		
		public boolean userExistitzenDa(User a) {
			System.out.println(">> existUser");
			Registered q = db.find(Registered.class, a.getUsername());
			if (q!=null) {
				return true;
			} else 
			return false;
			
		}
		
		public boolean teamExistitzenDa(Team a) {
			System.out.println(">> existUser");
			Team q = db.find(Team.class, a.getIzena());
			if (q!=null) {
				return true;
			} else 
			return false;
			
		}
		
		
			
			
		public boolean existQuote(Question quest, String fore) {
				System.out.println(">> DataAccessTest: existQuestion");
				Question q = db.find(Question.class, quest.getQuestionNumber());
				if (q!=null) {
					return q.doesQuoteExist(fore);
				} else 
					
				return false;
				
		}
			
		public boolean addQuotetoQuestion(Question quest,double kuota,String forecast) {
				System.out.println(">> DataAccessTest: addQuote");
				Question q = db.find(Question.class, quest.getQuestionNumber());
				if (q!=null) {
					db.getTransaction().begin();
					q.addQuote(kuota, forecast, q);
					db.getTransaction().commit();
					return true;
				} else 
				return false;
				
		}
			
			public ApustuAnitza sortuApusutuAnitza(Registered r,double bal,Sport sp) {
				db.getTransaction().begin();
				ApustuAnitza ap=new ApustuAnitza(r,bal);
				db.persist(ap);
				KirolEstatistikak ke=new KirolEstatistikak(r,sp);
				db.persist(ke);
				r.addKirolEstatistikak(ke);
				db.getTransaction().commit();
				return ap;
				
			}
			
			public Apustua sortuApustua(ApustuAnitza apa, Quote kuota) {
				System.out.println(">> DataAccessTest: addApustuatoKuota");
				Quote q = db.find(Quote.class, kuota.getQuoteNumber());
				ApustuAnitza apaDB =db.find(ApustuAnitza.class, apa.getApustuAnitzaNumber());
				if (q!=null) {
					db.getTransaction().begin();
					Apustua a=new Apustua(apaDB,q);
					db.persist(a);
					apaDB.addApustua(a);
					q.addApustua(a);
					
					db.getTransaction().commit();
					return a;
				} else 
				return null;
				
			}
			
			public boolean addApustuatoQuote(Quote k, Apustua a) {
				System.out.println(">> DataAccessTest: addApustua");
				Quote q = db.find(Quote.class, k.getQuoteNumber());
				if (q!=null) {
					db.getTransaction().begin();
					q.addApustua(a);
					db.getTransaction().commit();
					return true;
				} else 
				return false;
				
			}
			
			
			
			public boolean removeApustuAnitza(ApustuAnitza z) {
				System.out.println(">> DataAccessTest: removeUser");
				ApustuAnitza s = db.find(ApustuAnitza.class, z.getApustuAnitzaNumber());
				if (s!=null) {
					db.getTransaction().begin();
					db.remove(s);
					db.getTransaction().commit();
					return true;
				} else 
				return false;
			}
			

			
			public boolean removeUser(String name) {
				System.out.println(">> DataAccessTest: removeUser");
				Registered s = db.find(Registered.class, name);
				if (s!=null) {
					db.getTransaction().begin();
					db.remove(s);
					db.getTransaction().commit();
					return true;
				} else 
				return false;
			}
			
			public Event existsEvent(Event ev) {
				System.out.println(">> DataAccessTest: findEvent");
				Event e = db.find(Event.class, ev.getEventNumber());
				return e;
			}


			
			
			
			
			
}