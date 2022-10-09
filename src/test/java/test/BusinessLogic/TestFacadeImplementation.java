package test.BusinessLogic;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import domain.ApustuAnitza;
import domain.Apustua;
import domain.Elkarrizketa;
import domain.ElkarrizketaContainer;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Registered;
import domain.Sport;
import domain.Team;
import domain.Transaction;
import domain.User;
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
		
		public boolean existQuestion(Event ev,Question q) {
			dbManagerTest.open();
			boolean b = dbManagerTest.existQuestion(ev, q);
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
		
		public Registered addUser(String name,String pass,Integer bank,Sport sp) {
			dbManagerTest.open();
			Registered l = dbManagerTest.addUser(name, pass, bank, sp);
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
		public Event findEventWithDescriptionDateAndSport(String description, Date d, String sportName) {
			dbManagerTest.open();
			Event e = dbManagerTest.findEventWithDescriptionDateAndSport(description,d,sportName);
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
		
		
		public boolean addQuestiontoEvent(Event ev,String desc,double betmin) {
			dbManagerTest.open();
			boolean a=dbManagerTest.addQuestiontoEvent(ev, desc, betmin);
			dbManagerTest.close();
			return a;
		}
		
		public boolean setQuestionResult(Question quest, String res) {
			dbManagerTest.open();
			boolean a=dbManagerTest.setQuestionResult(quest, res);
			dbManagerTest.close();
			return a;
		}
		public boolean questionExistitzenDa(Question quest) {
			dbManagerTest.open();
			boolean a=dbManagerTest.questionExistitzenDa(quest);
			dbManagerTest.close();
			return a;
		}
		
		public boolean kuotaExistitzenDa(Quote kuota) {
			dbManagerTest.open();
			boolean a=dbManagerTest.kuotaExistitzenDa(kuota);
			dbManagerTest.close();
			return a;			
		}
		
		public boolean apustuaExistitzenDa(Apustua ap) {
			dbManagerTest.open();
			boolean a=dbManagerTest.apustuaExistitzenDa(ap);
			dbManagerTest.close();
			return a;
		}
		
		public boolean apustuAnitzaExistitzenDa(ApustuAnitza apa) {
			dbManagerTest.open();
			boolean a=dbManagerTest.apustuAnitzaExistitzenDa(apa);
			dbManagerTest.close();
			return a;
		}
		
		public boolean userExistitzenDa(User u) {
			dbManagerTest.open();
			boolean a=dbManagerTest.userExistitzenDa(u);
			dbManagerTest.close();
			return a;
		}
		
		
		public boolean teamExistitzenDa(Team t) {
			dbManagerTest.open();
			boolean a=dbManagerTest.teamExistitzenDa(t);
			dbManagerTest.close();
			return a;
		}
		
		public boolean ezabatuKE(Registered u) {
			dbManagerTest.open();
			boolean a=dbManagerTest.ezabatuKE(u);
			dbManagerTest.close();
			return a;
		}
		public boolean ezabatuTransakzio(Registered u) {
			dbManagerTest.open();
			boolean a=dbManagerTest.ezabatuTransakzio(u);
			dbManagerTest.close();
			return a;
		}
		
		public boolean existQuote(Question quest, String fore) {
			dbManagerTest.open();
			boolean a=dbManagerTest.existQuote(quest, fore);
			dbManagerTest.close();
			return a;
		}
		
		public boolean addQuotetoQuestion(Question quest,double kuota,String forecast) {
			dbManagerTest.open();
			boolean a=dbManagerTest.addQuotetoQuestion(quest, kuota, forecast);
			dbManagerTest.close();
			return a;
		}
		
		public boolean aldatuEgoeraApustuari(Apustua ap, String egoera) {
			dbManagerTest.open();
			boolean a=dbManagerTest.aldatuEgoeraApustuari(ap, egoera);
			dbManagerTest.close();
			return a;
		}
		
		public Apustua aurkituApustua(Quote q) {
			dbManagerTest.open();
			Apustua a=dbManagerTest.aurkituApustua(q);
			dbManagerTest.close();
			return a;
		}
		
		public boolean removeUser(String name) {
			dbManagerTest.open();
			boolean a=dbManagerTest.removeUser(name);
			dbManagerTest.close();
			return a;
		}
		public boolean existsEvent(Event ev) {
			dbManagerTest.open();
			boolean a=dbManagerTest.existsEvent(ev);
			dbManagerTest.close();
			return a;
		}
		
		public boolean apustuaXAldiz(User u, Vector<Quote> quote, Double balioa, Integer apustuBikoitzaGalarazi,boolean bikoitza) {
			dbManagerTest.open();
			boolean a=dbManagerTest.apustuaXaldiz(u, quote, balioa, apustuBikoitzaGalarazi,bikoitza);
			dbManagerTest.close();
			return a;
		}
		
		public void DiruaSartu(Registered u, Double dirua, Date data, String mota) {
			dbManagerTest.open();
			dbManagerTest.DiruaSartu(u, dirua, data, mota);
			dbManagerTest.close();
		}
		
		public String saldoaBistaratu(User u) {
			dbManagerTest.open();
			String em=dbManagerTest.saldoaBistaratu(u);
			dbManagerTest.close();
			return em;
		}


		public Question getQuestion(Event ev) {
			dbManagerTest.open();
			Question q=dbManagerTest.getQuestion(ev);
			dbManagerTest.close();
			return q;
		}


		public Vector<Quote> getQuotes(Question q7) {
			dbManagerTest.open();
			Vector <Quote> list=dbManagerTest.getQuotes(q7);
			dbManagerTest.close();
			return list;
		}


		public Registered getRegistered(Registered u) {
			dbManagerTest.open();
			Registered r=dbManagerTest.getRegistered(u);
			dbManagerTest.close();
			return r;
		}


		public Event setSport(Event ev, Sport sport) {
			dbManagerTest.open();
			Event r=dbManagerTest.setSport(ev,sport);
			dbManagerTest.close();
			return r;
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
