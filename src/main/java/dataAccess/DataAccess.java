package dataAccess;

import java.util.ArrayList;
//hello
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Admin;
import domain.ApustuAnitza;
import domain.Apustua;
import domain.Elkarrizketa;
import domain.ElkarrizketaContainer;
import domain.Event;
import domain.Jarraitzailea;
import domain.KirolEstatistikak;
import domain.Message;
import domain.MezuakContainer;
import domain.Question;
import domain.Quote;
import domain.Registered;
import domain.Sport;
import domain.Team;
import domain.Transaction;
import domain.User;
import exceptions.EventNotFinished;
import exceptions.QuestionAlreadyExist;
import exceptions.QuoteAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;


	ConfigXML c=ConfigXML.getInstance();

     public DataAccess(boolean initializeMode)  {
		
		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		open(initializeMode);
		
	}

	public DataAccess()  {	
		 this(false);
	}
	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		db.getTransaction().begin();
		try {

			
		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   month+=1;
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=0; year+=1;}  
		   
		   Team team1= new Team("Atletico");
		   Team team2= new Team("Athletic");
		   Team team3= new Team("Eibar");
		   Team team4= new Team("Barcelona");
		   Team team5= new Team("Getafe");
		   Team team6= new Team("Celta");
		   Team team7= new Team("Alaves");
		   Team team8= new Team("Deportivo");
		   Team team9= new Team("Espanol");
		   Team team10= new Team("Villareal");
		   Team team11= new Team("Las_Palmas");
		   Team team12= new Team("Sevilla");
		   Team team13= new Team("Malaga");
		   Team team14= new Team("Valencia");
		   Team team15= new Team("Girona");
		   Team team16= new Team("Leganes");
		   Team team17= new Team("Real_Sociedad");
		   Team team18= new Team("Levante");
		   Team team19= new Team("Betis");
		   Team team20= new Team("Real_Madrid");
		   Team team21= new Team("LA_Lakers");
		   Team team22= new Team("Phoenix_Suns");
		   Team team23= new Team("Atlanta_Hawks");
		   Team team24= new Team("Houston_Rockets");
		   Team team25= new Team("Miami_Heat");
		   Team team26= new Team("Chicago_Bulls");
		   Team team27= new Team("Boston_Celtics");
		   Team team28= new Team("Memphis_Grizzlies");
		   Team team29= new Team("Nadal");
		   Team team30= new Team("Alcaraz");
		   Team team31= new Team("Djokovic");
		   Team team32= new Team("Federer");
		   
	    
			Event ev1=new Event(1, "Atletico-Athletic", UtilDate.newDate(year,month,17), team1, team2);
			Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17), team3, team4);
			Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17), team5, team6);
			Event ev4=new Event(4, "Alaves-Deportivo", UtilDate.newDate(year,month,17), team7, team8);
			Event ev5=new Event(5, "Espanol-Villareal", UtilDate.newDate(year,month,17), team9, team10);
			Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17), team11, team12);
			Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17), team13, team14);
			Event ev8=new Event(8, "Girona-Leganes", UtilDate.newDate(year,month,17), team15, team16);
			Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17), team17, team18);
			Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17), team19, team20);

			Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1), team1, team2);
			Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1), team3, team4);
			Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1), team5, team6);
			Event ev14=new Event(14, "Alaves-Deportivo", UtilDate.newDate(year,month,1), team7, team8);
			Event ev15=new Event(15, "Espanol-Villareal", UtilDate.newDate(year,month,1), team9, team10);
			Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1), team11, team12);
			

			Event ev17=new Event(17, "Malaga-Valencia", UtilDate.newDate(year,month+1,28), team13, team14);
			Event ev18=new Event(18, "Girona-Leganes", UtilDate.newDate(year,month+1,28), team15, team16);
			Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month+1,28), team17, team18);
			Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month+1,28), team19, team20);
			Event ev21=new Event(21, "Real Madrid-Barcelona", UtilDate.newDate(year, month-2, 21), team20, team4);
			
			Event ev22=new Event(22, "LA Lakers-Phoenix Suns", UtilDate.newDate(year,month,17), team21, team22);
			Event ev23=new Event(23, "Atlanta Hawks-Houston Rockets", UtilDate.newDate(year,month,17), team23, team24);
			Event ev24=new Event(24, "Miami Heat-Chicago Bulls", UtilDate.newDate(year,month,17), team25, team26);
			Event ev25=new Event(25, "Boston Celtics-Memphis Grizzlies", UtilDate.newDate(year,month,1), team27, team28);
			
			Event ev26=new Event(26, "Nadal-Alcaraz", UtilDate.newDate(year,month,1), team29, team30);
			Event ev27=new Event(27, "Djokovic-Federer", UtilDate.newDate(year,month,17), team31, team32);
			
			Sport sp1=new Sport("Futbol");
			Sport sp2=new Sport("Baloncesto");
			Sport sp3=new Sport("Tennis");
			
			sp1.addEvent(ev1);
			sp1.addEvent(ev2);
			sp1.addEvent(ev3);
			sp1.addEvent(ev4);
			sp1.addEvent(ev5);
			sp1.addEvent(ev6);
			sp1.addEvent(ev7);
			sp1.addEvent(ev8);
			sp1.addEvent(ev9);
			sp1.addEvent(ev10);
			sp1.addEvent(ev11);
			sp1.addEvent(ev12);
			sp1.addEvent(ev13);
			sp1.addEvent(ev14);
			sp1.addEvent(ev15);
			sp1.addEvent(ev16);
			sp1.addEvent(ev17);
			sp1.addEvent(ev18);
			sp1.addEvent(ev19);
			sp1.addEvent(ev20);
			sp1.addEvent(ev21);
			sp2.addEvent(ev22);
			sp2.addEvent(ev23);
			sp2.addEvent(ev24);
			sp2.addEvent(ev25);
			sp3.addEvent(ev26);
			sp3.addEvent(ev27);
			
			ev1.setSport(sp1);
			ev2.setSport(sp1);
			ev3.setSport(sp1);
			ev4.setSport(sp1);
			ev5.setSport(sp1);
			ev6.setSport(sp1);
			ev7.setSport(sp1);
			ev8.setSport(sp1);
			ev9.setSport(sp1);
			ev10.setSport(sp1);
			ev11.setSport(sp1);
			ev12.setSport(sp1);
			ev13.setSport(sp1);
			ev14.setSport(sp1);
			ev15.setSport(sp1);
			ev16.setSport(sp1);
			ev17.setSport(sp1);
			ev18.setSport(sp1);
			ev19.setSport(sp1);
			ev20.setSport(sp1);
			ev21.setSport(sp1);
			ev22.setSport(sp2);
			ev23.setSport(sp2);
			ev24.setSport(sp2);
			ev25.setSport(sp2);
			ev26.setSport(sp3);
			ev27.setSport(sp3);
			
			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;
			Question q7;
			Question q8;
			Question q9;
			Question q10;
			Question q11;
			
			User ad1=new Admin("admin", "123", 1234);
			Registered reg1 =new Registered("registered", "123", 1234);
			Registered reg2 = new Registered("andrea", "123", 1111);
			Registered reg3 = new Registered("markel", "123", 1111);
			Registered reg4 = new Registered("mikel", "123", 1111);
									
			if (Locale.getDefault().equals(new Locale("es"))) {
				q1=ev1.addQuestion("¿Quién ganará el partido?",1);
				q2=ev1.addQuestion("¿Quién meterá el primer gol?",2);
				q3=ev11.addQuestion("¿Quién ganará el partido?",1);
				q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
				q5=ev17.addQuestion("¿Quién ganará el partido?",1);
				q6=ev17.addQuestion("¿Habrá goles en la primera parte?",2);
				
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				q1=ev1.addQuestion("Who will win the match?",1);
				q2=ev1.addQuestion("Who will score first?",2);
				q3=ev11.addQuestion("Who will win the match?",1);
				q4=ev11.addQuestion("How many goals will be scored in the match?",2);
				q5=ev17.addQuestion("Who will win the match?",1);
				q6=ev17.addQuestion("Will there be goals in the first half?",2);
				
			}			
			else {
				q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
				q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
				q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
				q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
				q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);
				
				
			}
			q7 = ev21.addQuestion("Emaitza?", 1);
			q8 = ev21.addQuestion("Emaitza?", 1);
			q9 = ev22.addQuestion("Irabazlea?", 1.5);
			q10 = ev26.addQuestion("Irabazlea?", 1.5);
			q11 = ev27.addQuestion("Zeinek irabaziko du lehenengo set-a", 3.0);
			
			
			Quote quote1 = q1.addQuote(1.3, "1", q1); 
			Quote quote2 = q2.addQuote(2.5, "X", q2); 
			Quote quote3 = q3.addQuote(100.0, "2", q3);
			Quote quote4 = q7.addQuote(2.5, "2", q7);
			Quote quote5 = q8.addQuote(2.0, "1", q8);
			Quote quote6 = q7.addQuote(5.0, "1", q7);
			Quote quote7 = q9.addQuote(3.0, "1", q9);
			Quote quote8 = q9.addQuote(1.5, "2", q9);
			Quote quote9 = q10.addQuote(2.5, "1", q10);
			Quote quote10 = q10.addQuote(1.6, "2", q10);
			Quote quote11 =q11.addQuote(2.3, "1", q11);
			Quote quote12 =q11.addQuote(1.5, "2", q11);
			
			ApustuAnitza apA1 = new ApustuAnitza(reg1, 5.0);
			ApustuAnitza apA3 = new ApustuAnitza(reg4, 34.5);
			ApustuAnitza apA4 = new ApustuAnitza(reg2, 14.5);
			ApustuAnitza apA5 = new ApustuAnitza(reg3, 10.0);
			ApustuAnitza apA6 = new ApustuAnitza(reg4, 4.5);
			ApustuAnitza apA7 = new ApustuAnitza(reg1, 6.0);
			ApustuAnitza apA8 = new ApustuAnitza(reg1, 2.5);
			ApustuAnitza apA9 = new ApustuAnitza(reg2, 4.0);
			ApustuAnitza apA10= new ApustuAnitza(reg2, 7.5);
			ApustuAnitza apA11= new ApustuAnitza(reg3, 4.5);
			ApustuAnitza apA12= new ApustuAnitza(reg3, 6.5);
			ApustuAnitza apA13= new ApustuAnitza(reg2, 6.5);
		
			Apustua ap1 = new Apustua(apA1, quote4);
			Apustua ap2 = new Apustua(apA1, quote1);
			apA1.addApustua(ap2);
			apA1.addApustua(ap1);
			
			Apustua ap3 = new Apustua(apA3, quote4);
			apA3.addApustua(ap3);
			
			Apustua ap4 = new Apustua(apA4, quote4);
			apA4.addApustua(ap4);
			
			Apustua ap5 = new Apustua(apA5, quote4);
			apA5.addApustua(ap5);
			
			Apustua ap6 = new Apustua(apA6, quote1);
			Apustua ap13 = new Apustua(apA6, quote2);
			apA6.addApustua(ap6);
			apA6.addApustua(ap13);
			
			Apustua ap7 = new Apustua(apA7, quote7);
			apA7.addApustua(ap7);
			
			Apustua ap8 = new Apustua(apA8, quote9);
			apA8.addApustua(ap8);
			
			Apustua ap9 = new Apustua(apA9, quote8);
			apA9.addApustua(ap9);
			
			Apustua ap10= new Apustua(apA10, quote10);
			apA10.addApustua(ap10);
			
			Apustua ap11= new Apustua(apA11, quote10);
			apA11.addApustua(ap11);
			
			Apustua ap12= new Apustua(apA12, quote11);
			apA12.addApustua(ap12);
			
			Apustua ap14= new Apustua(apA13, quote8);
			apA13.addApustua(ap14);
			
			quote4.addApustua(ap1);
			ap1.eguneratuApustuKant(sp1);
			quote1.addApustua(ap1);
			ap1.eguneratuApustuKant(sp1);
			
			quote4.addApustua(ap3);
			ap3.eguneratuApustuKant(sp1);
			
			quote4.addApustua(ap4);
			ap4.eguneratuApustuKant(sp1);
					
			quote4.addApustua(ap5);
			ap5.eguneratuApustuKant(sp1);
						
			quote1.addApustua(ap6);
			quote2.addApustua(ap6);
			ap6.eguneratuApustuKant(sp1);
			ap6.eguneratuApustuKant(sp1);
			
			quote7.addApustua(ap7);
			ap7.eguneratuApustuKant(sp2);
			quote8.addApustua(ap9);
			ap9.eguneratuApustuKant(sp2);
			
			quote9.addApustua(ap8);
			ap8.eguneratuApustuKant(sp3);
			quote10.addApustua(ap10);
			ap10.eguneratuApustuKant(sp3);
			
			quote10.addApustua(ap11);
			ap11.eguneratuApustuKant(sp3);
			quote11.addApustua(ap12);
			ap12.eguneratuApustuKant(sp3);
			
			quote8.addApustua(ap14);
			ap14.eguneratuApustuKant(sp2);
			
			reg1.addApustuAnitza(apA1);
			KirolEstatistikak ke1 = new KirolEstatistikak(reg1, sp1);
			reg1.addKirolEstatistikak(ke1);
			sp1.addKirolEstatistikak(ke1);
			ke1.eguneratuKont(2);
			
			reg4.addApustuAnitza(apA3);
			KirolEstatistikak ke2 = new KirolEstatistikak(reg4, sp1);
			reg4.addKirolEstatistikak(ke2);
			sp1.addKirolEstatistikak(ke2);
			ke2.eguneratuKont(1);
			
			reg2.addApustuAnitza(apA4);
			KirolEstatistikak ke3 = new KirolEstatistikak(reg2, sp1);
			reg2.addKirolEstatistikak(ke3);
			sp1.addKirolEstatistikak(ke3);
			ke3.eguneratuKont(1);
			
			reg3.addApustuAnitza(apA5);
			KirolEstatistikak ke4 = new KirolEstatistikak(reg3, sp1);
			reg3.addKirolEstatistikak(ke4);
			sp1.addKirolEstatistikak(ke4);
			ke4.eguneratuKont(1);
			
			reg4.addApustuAnitza(apA6);
			ke2.eguneratuKont(2);
			
			
			reg1.addApustuAnitza(apA7);
			KirolEstatistikak ke6 = new KirolEstatistikak(reg1, sp2);
			reg1.addKirolEstatistikak(ke6);
			sp2.addKirolEstatistikak(ke6);
			ke6.eguneratuKont(1);
			
			reg1.addApustuAnitza(apA8);
			KirolEstatistikak ke7 = new KirolEstatistikak(reg1, sp3);
			reg1.addKirolEstatistikak(ke7);
			sp3.addKirolEstatistikak(ke7);
			ke7.eguneratuKont(1);
			
			reg2.addApustuAnitza(apA9);
			KirolEstatistikak ke8 = new KirolEstatistikak(reg2, sp2);
			reg2.addKirolEstatistikak(ke8);
			sp2.addKirolEstatistikak(ke8);
			ke8.eguneratuKont(1);
			
			reg2.addApustuAnitza(apA10);
			KirolEstatistikak ke9 = new KirolEstatistikak(reg2, sp3);
			reg2.addKirolEstatistikak(ke9);
			sp3.addKirolEstatistikak(ke9);
			ke9.eguneratuKont(1);
			
			reg3.addApustuAnitza(apA11);
			KirolEstatistikak ke10 = new KirolEstatistikak(reg3, sp3);
			reg3.addKirolEstatistikak(ke10);
			sp3.addKirolEstatistikak(ke10);
			ke10.eguneratuKont(1);
			
			reg3.addApustuAnitza(apA12);
			ke10.eguneratuKont(1);
			
			reg2.addApustuAnitza(apA9);
			ke8.eguneratuKont(1);
			
			String apustuaEgin = "ApustuaEgin";
			Transaction t1 = new Transaction(reg1, apA1.getBalioa(), new Date(), apustuaEgin);
			Transaction t3 = new Transaction(reg2, apA4.getBalioa(), new Date(), apustuaEgin);
			Transaction t4 = new Transaction(reg3, apA5.getBalioa(), new Date(), apustuaEgin);
			Transaction t5 = new Transaction(reg4, apA3.getBalioa(), new Date(), apustuaEgin);
			Transaction t6 = new Transaction(reg4, apA6.getBalioa(), new Date(), apustuaEgin);
			Transaction t7 = new Transaction(reg1, apA7.getBalioa(), new Date(), apustuaEgin);
			Transaction t8 = new Transaction(reg1, apA8.getBalioa(), new Date(), apustuaEgin);
			Transaction t9 = new Transaction(reg2, apA9.getBalioa(), new Date(), apustuaEgin);
			Transaction t10 = new Transaction(reg2, apA10.getBalioa(), new Date(), apustuaEgin);
			Transaction t11 = new Transaction(reg3, apA11.getBalioa(), new Date(), apustuaEgin);
			Transaction t12 = new Transaction(reg3, apA12.getBalioa(), new Date(), apustuaEgin);
			
			reg1.addTransaction(t1);
			reg2.addTransaction(t3);
			reg3.addTransaction(t4);
			reg4.addTransaction(t5);
			reg4.addTransaction(t6);
			reg1.addTransaction(t7);
			reg1.addTransaction(t8);
			reg2.addTransaction(t9);
			reg2.addTransaction(t10);
			reg3.addTransaction(t11);
			reg3.addTransaction(t12);
			
			Elkarrizketa elka1 = new Elkarrizketa("holi", reg1, ad1);
			reg1.addElkarrizketak(elka1);
			ad1.addElkarrizketak(elka1);
			
			
			Message m1 = new Message(reg1, ad1, "hola");
			m1.setIrakurrita(true);
			elka1.addMezua(m1);
			m1.setElkarrizketa(elka1);
			Message m2 = new Message(ad1, reg1, "que tal");
			m2.setIrakurrita(false);
			elka1.addMezua(m2);
			m2.setElkarrizketa(elka1);
			Message m3 = new Message(reg1, ad1, "que pasa");
			m3.setIrakurrita(false);
			elka1.addMezua(m3);
			m3.setElkarrizketa(elka1);
			Message m4 = new Message(ad1, reg1, "nada");
			m4.setIrakurrita(true);
			elka1.addMezua(m4);
			m4.setElkarrizketa(elka1);
			Message m5= new Message(reg1, ad1, "vale");
			m5.setIrakurrita(true);
			elka1.addMezua(m5);
			m5.setElkarrizketa(elka1);
			Message m6 = new Message(ad1, reg1, "ok");
			m6.setIrakurrita(false);
			elka1.addMezua(m6);
			m6.setElkarrizketa(elka1);
			
			Elkarrizketa elka2 = new Elkarrizketa("quea", reg1, ad1);
			reg1.addElkarrizketak(elka2);
			ad1.addElkarrizketak(elka2);
		
			Message m7 = new Message(ad1, reg1, "hola");
			m7.setIrakurrita(false);
			elka2.addMezua(m7);
			m7.setElkarrizketa(elka2);
			Message m8 = new Message(reg1, ad1, "que tal");
			m8.setIrakurrita(true);
			elka2.addMezua(m8);
			m8.setElkarrizketa(elka2);
			Message m9 = new Message(ad1, reg1, "hola");
			m9.setIrakurrita(true);
			elka2.addMezua(m9);
			m9.setElkarrizketa(elka2);
			Message m10 = new Message(reg1, ad1, "hola");
			m10.setIrakurrita(false);
			elka2.addMezua(m10);
			m10.setElkarrizketa(elka2);
			Message m11= new Message(ad1, reg1, "hola");
			m11.setIrakurrita(false);
			elka2.addMezua(m11);
			m11.setElkarrizketa(elka2);
			Message m12 = new Message(reg1, ad1, "hola");
			m12.setIrakurrita(true);
			elka2.addMezua(m12);
			m12.setElkarrizketa(elka2);
			
			m1.setMessageNumber(1);
			m2.setMessageNumber(2);
			m3.setMessageNumber(3);
			m4.setMessageNumber(4);
			m5.setMessageNumber(5);
			m6.setMessageNumber(6);
			m7.setMessageNumber(7);
			m8.setMessageNumber(8);
			m9.setMessageNumber(9);
			m10.setMessageNumber(10);
			m11.setMessageNumber(11);
			m12.setMessageNumber(12);
			
			team1.addEvent(ev1);
			team2.addEvent(ev1);
			team3.addEvent(ev2);
			team4.addEvent(ev2);
			team5.addEvent(ev3);
			team6.addEvent(ev3);
			team7.addEvent(ev4);
			team8.addEvent(ev4);
			team9.addEvent(ev5);
			team10.addEvent(ev5);
			team11.addEvent(ev6);
			team12.addEvent(ev6);
			team13.addEvent(ev7);
			team14.addEvent(ev7);
			team15.addEvent(ev8);
			team16.addEvent(ev8);
			team17.addEvent(ev9);
			team18.addEvent(ev9);
			team19.addEvent(ev10);
			team20.addEvent(ev10);
			team1.addEvent(ev11);
			team2.addEvent(ev11);
			team3.addEvent(ev12);
			team4.addEvent(ev12);
			team5.addEvent(ev13);
			team6.addEvent(ev13);
			team7.addEvent(ev14);
			team8.addEvent(ev14);
			team9.addEvent(ev15);
			team10.addEvent(ev15);
			team11.addEvent(ev16);
			team12.addEvent(ev16);
			team13.addEvent(ev17);
			team14.addEvent(ev17);
			team15.addEvent(ev18);
			team16.addEvent(ev18);
			team17.addEvent(ev19);
			team18.addEvent(ev19);
			team19.addEvent(ev20);
			team20.addEvent(ev20);
			team20.addEvent(ev21);
			team4.addEvent(ev21);
			team21.addEvent(ev22);
			team22.addEvent(ev22);
			team23.addEvent(ev23);
			team24.addEvent(ev23);
			team25.addEvent(ev24);
			team26.addEvent(ev24);
			team27.addEvent(ev25);
			team28.addEvent(ev25);
			team29.addEvent(ev26);
			team30.addEvent(ev26);
			team31.addEvent(ev27);
			team32.addEvent(ev27);
			
			
			db.persist(team1);
			db.persist(team2);
			db.persist(team3);
			db.persist(team4);
			db.persist(team5);
			db.persist(team6);
			db.persist(team7);
			db.persist(team8);
			db.persist(team9);
			db.persist(team10);
			db.persist(team11);
			db.persist(team12);
			db.persist(team13);
			db.persist(team14);
			db.persist(team15);
			db.persist(team16);
			db.persist(team17);
			db.persist(team18);
			db.persist(team19);
			db.persist(team20);
			db.persist(team21);
			db.persist(team22);
			db.persist(team23);
			db.persist(team24);
			db.persist(team25);
			db.persist(team26);
			db.persist(team27);
			db.persist(team28);
			db.persist(team29);
			db.persist(team30);
			db.persist(team31);
			db.persist(team32);
			
			db.persist(apA1);
			db.persist(apA3);
			db.persist(apA4);
			db.persist(apA5);
			db.persist(apA6);
			db.persist(apA7);
			db.persist(apA8);
			db.persist(apA9);
			db.persist(apA10);
			db.persist(apA11);
			db.persist(apA12);
			db.persist(apA13);
			
			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);
			db.persist(q7);
			db.persist(q8);
			db.persist(q9);
			db.persist(q10);
			db.persist(q11);
			
			db.persist(ke1);
			db.persist(ke2);
			db.persist(ke3);
			db.persist(ke4);
			db.persist(ke6);
			db.persist(ke7);
			db.persist(ke8);
			db.persist(ke9);
			db.persist(ke10);
	        
			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);		
			db.persist(ev21);
			db.persist(ev22);
			db.persist(ev23);
			db.persist(ev24);
			db.persist(ev25);
			db.persist(ev26);
			db.persist(ev27);
			
			db.persist(sp1);
			db.persist(sp2);
			db.persist(sp3);
			
			db.persist(ad1);
			db.persist(reg1);
			db.persist(reg2);
			db.persist(reg3);
			db.persist(reg4);
			
			db.persist(quote3);
			db.persist(quote2); 
			db.persist(quote1); 
			db.persist(quote4);
			db.persist(quote5);
			db.persist(quote6);
			db.persist(quote7);
			db.persist(quote8);
			db.persist(quote9);
			db.persist(quote10);
			db.persist(quote11);
			db.persist(quote12);
			
			db.persist(ap1);
			db.persist(ap2);
			db.persist(ap3);
			db.persist(ap4);
			db.persist(ap5);
			db.persist(ap6);
			db.persist(ap7);
			db.persist(ap8);
			db.persist(ap9);
			db.persist(ap10);
			db.persist(ap11);
			db.persist(ap12);
			db.persist(ap13);
			db.persist(ap14);
			
			db.persist(t1);
			db.persist(t3);
			db.persist(t4);
			db.persist(t5);
			db.persist(t6);
			db.persist(t7);
			db.persist(t8);
			db.persist(t9);
			db.persist(t10);
			db.persist(t11);
			db.persist(t12);
			
			db.persist(elka1);
			db.persist(elka2);
			
			db.persist(m1);
			db.persist(m2);
			db.persist(m3);
			db.persist(m4);
			db.persist(m5);
			db.persist(m6);
			
			db.persist(m7);
			db.persist(m8);
			db.persist(m9);
			db.persist(m10);
			db.persist(m11);
			db.persist(m12);
			
			db.getTransaction().commit();
			
			this.DiruaSartu(reg1, 50.0, new Date(), "DiruaSartu");
			this.DiruaSartu(reg2, 50.0, new Date(), "DiruaSartu");
			this.DiruaSartu(reg3, 50.0, new Date(), "DiruaSartu");
			this.DiruaSartu(reg4, 50.0, new Date(), "DiruaSartu");
			
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
		
			Event ev = db.find(Event.class, event.getEventNumber());
			
			if (ev.doesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
			
			db.getTransaction().begin();
			Question q = ev.addQuestion(question, betMinimum);
			//db.persist(q);
			db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
							// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
			db.getTransaction().commit();
			return q;
		
	}
	
	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1",Event.class);   
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
	 	 for (Event ev:events){
	 	   System.out.println(ev.toString());		 
		   res.add(ev);
		  }
	 	return res;
	}
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2",Date.class);   
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
	 	 for (Date d:dates){
	 	   System.out.println(d.toString());		 
		   res.add(d);
		  }
	 	return res;
	}
	

public void open(boolean initializeMode){
		
		System.out.println("Opening DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		String fileName=c.getDbFilename();
		if (initializeMode) {
			fileName=fileName+";drop";
			System.out.println("Deleting the DataBase");
		}
		
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
	public boolean existQuestion(Event event, String question) {
		System.out.println(">> DataAccess: existQuestion=> event= "+event+" question= "+question);
		Event ev = db.find(Event.class, event.getEventNumber());
		return ev.doesQuestionExists(question);
	
	}
	
	public User isLogin(String username, String password) {
		User u = db.find(User.class, username);
		if(u!=null) {
			if(u.getPassword().equals(password)) return u;
		}
		return null;
	}
	
	public boolean isRegister(String username) {
		User u = db.find(User.class, username); 
		return (u!=null);
	}
	
	public void storeRegistered(String username, String password, Integer bankAccount) {
		db.getTransaction().begin();
		Registered ad = new Registered(username, password, bankAccount);
		db.persist(ad);
		db.getTransaction().commit();
	}
	
	public boolean gertaerakSortu(String description,Date eventDate, String sport) {
		boolean b = true;

		db.getTransaction().begin();
		Sport spo =db.find(Sport.class, sport);
		if(spo!=null) {
			TypedQuery<Event> Equery = db.createQuery("SELECT e FROM Event e WHERE e.getEventDate() =?1 ",Event.class);
			Equery.setParameter(1, eventDate);
			for(Event ev: Equery.getResultList()) {
				if(ev.getDescription().equals(description)) {
					b = false;
				}
			}
			if(b) {
				String[] taldeak = description.split("-");
				Team lokala = new Team(taldeak[0]);
				Team kanpokoa = new Team(taldeak[1]);
				Event e = new Event(description, eventDate, lokala, kanpokoa);
				e.setSport(spo);
				spo.addEvent(e);
				db.persist(e);
			}
		}else {
			return false;
		}
		db.getTransaction().commit();
		return b;
	}
	
	public Quote storeQuote(String forecast, Double Quote, Question question) throws QuoteAlreadyExist {
		//System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
		
		Question q = db.find(Question.class, question.getQuestionNumber());
		
		if (q.doesQuoteExist(forecast)) throw new QuoteAlreadyExist("Kuota existitzen da");
		
		db.getTransaction().begin();
		Quote quo = q.addQuote(Quote, forecast, q);
		db.persist(quo); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
						// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return quo;
	
	}
	
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}
	
	public Sport findSport(Event q){
		Event sp = db.find(Event.class, q.getEventNumber());
		return sp.getSport(); 
	}
	
	public Event findEvent(Quote q){
		Quote quo = db.find(Quote.class, q.getQuoteNumber());
		return quo.getQuestion().getEvent(); 
	}
	
	public Team findTeam(User u){
		Registered user = (Registered) db.find(User.class, u.getUsername());
		return user.getTaldea(); 
	}
	
	public Event findEventFromApustua(Apustua q){
		Apustua quo = db.find(Apustua.class, q.getApostuaNumber());
		return quo.getKuota().getQuestion().getEvent(); 
	}
	
	public Question findQuestionFromQuote(Quote q){
		Quote quo = db.find(Quote.class, q.getQuoteNumber());
		return quo.getQuestion(); 
	}
	
	public Collection<Question> findQuestion(Event event){
		TypedQuery<Question> Qquery = db.createQuery("SELECT q FROM Question q WHERE q.getEvent() =?1 ",Question.class);
		Qquery.setParameter(1, event);
		return Qquery.getResultList();
	}
	
	public Collection<Quote> findQuote(Question question){
		TypedQuery<Quote> Qquery = db.createQuery("SELECT q FROM Quote q WHERE q.getQuestion() =?1 ",Quote.class);
		Qquery.setParameter(1, question);
		return Qquery.getResultList();
	}
	
	public void DiruaSartu(User u, Double dirua, Date data, String mota) {
		Registered user = (Registered) db.find(User.class, u.getUsername()); 
		db.getTransaction().begin();
		Transaction t = new Transaction(user, dirua, data, mota); 
		System.out.println(t.getMota());
		user.addTransaction(t);
		user.updateDiruKontua(dirua);
		db.persist(t);
		db.getTransaction().commit();
	}
	
	public boolean ApustuaEgin(User u, Vector<Quote> quote, Double balioa, Integer apustuBikoitzaGalarazi) {
		Registered user = (Registered) db.find(User.class, u.getUsername());
		Boolean b;
		if(user.getDirukop()>=balioa) {
			db.getTransaction().begin();
			ApustuAnitza apustuAnitza = new ApustuAnitza(user, balioa);
			db.persist(apustuAnitza);
			for(Quote quo: quote) {
				Quote kuote = db.find(Quote.class, quo.getQuoteNumber());
				Apustua ap = new Apustua(apustuAnitza, kuote);
				db.persist(ap);
				apustuAnitza.addApustua(ap);
				kuote.addApustua(ap);
			}
			db.getTransaction().commit();
			db.getTransaction().begin();
			if(apustuBikoitzaGalarazi==-1) {
				apustuBikoitzaGalarazi=apustuAnitza.getApustuAnitzaNumber();
			}
			apustuAnitza.setApustuKopia(apustuBikoitzaGalarazi);
			user.updateDiruKontua(-balioa);
			Transaction t = new Transaction(user, balioa, new Date(), "ApustuaEgin"); 
			user.addApustuAnitza(apustuAnitza);
			for(Apustua a: apustuAnitza.getApustuak()) {
				Apustua apu = db.find(Apustua.class, a.getApostuaNumber());
				Quote q = db.find(Quote.class, apu.getKuota().getQuoteNumber());
				Sport spo =q.getQuestion().getEvent().getSport();
				spo.setApustuKantitatea(spo.getApustuKantitatea()+1);
				if(!user.containsKirola(spo)) {
					KirolEstatistikak ke=new KirolEstatistikak(user, spo);
					ke.setKont(1);
					user.addKirolEstatistikak(ke);
					spo.addKirolEstatistikak(ke);
				}else {
					KirolEstatistikak ke=user.kirolEstatistikakLortu(spo);
					ke.eguneratuKont(1);
				}
			}
			user.addTransaction(t);
			db.persist(t);
			db.getTransaction().commit();
			for(Jarraitzailea reg:user.getJarraitzaileLista()) {
				Jarraitzailea erab=db.find(Jarraitzailea.class, reg.getJarraitzaileaNumber());
				b=true;
				for(ApustuAnitza apu: erab.getNork().getApustuAnitzak()) {
					if(apu.getApustuKopia()==apustuAnitza.getApustuKopia()) {
						b=false;
					}
				}
				if(b) {
					if(erab.getNork().getDiruLimitea()<balioa) {
						this.ApustuaEgin(erab.getNork(), quote, erab.getNork().getDiruLimitea(), apustuBikoitzaGalarazi);
					}else{
						this.ApustuaEgin(erab.getNork(), quote, balioa, apustuBikoitzaGalarazi);
					}
				}
			}
			return true; 
		}else{
			return false; 
		}
		
	}
	
	public void apustuaEzabatu(User user1, ApustuAnitza ap) {
		Registered user = (Registered) db.find(User.class, user1.getUsername());
		ApustuAnitza apustuAnitza = db.find(ApustuAnitza.class, ap.getApustuAnitzaNumber());
		db.getTransaction().begin();
		user.updateDiruKontua(apustuAnitza.getBalioa());
		Transaction t = new Transaction(user, apustuAnitza.getBalioa(), new Date(), "ApustuaEzabatu");
		user.addTransaction(t);
		db.persist(t);
		user.removeApustua(apustuAnitza);
		int i;
		for(i=0; i<apustuAnitza.getApustuak().size(); i++) {
			apustuAnitza.getApustuak().get(i).getKuota().removeApustua(apustuAnitza.getApustuak().get(i));
			Sport spo =apustuAnitza.getApustuak().get(i).getKuota().getQuestion().getEvent().getSport();
			spo.setApustuKantitatea(spo.getApustuKantitatea()-1);
			KirolEstatistikak ke=user.kirolEstatistikakLortu(spo);
			ke.setKont(ke.getKont()-1);
		}
		db.remove(apustuAnitza);
		db.getTransaction().commit();
	}
	
	public List<Apustua> findApustua(User u){
		Registered user = (Registered) db.find(User.class, u.getUsername()); 
		TypedQuery<Apustua> Aquery = db.createQuery("SELECT a FROM Apustua a WHERE a.getUser().getUsername() =?1 ", Apustua.class);
		Aquery.setParameter(1, u.getUsername());
		return Aquery.getResultList();
	}
	public List<ApustuAnitza> findApustuAnitza(User u){
		Registered user = (Registered) db.find(User.class, u.getUsername()); 
		TypedQuery<ApustuAnitza> Aquery = db.createQuery("SELECT aa FROM ApustuAnitza aa WHERE aa.getUser().getUsername() =?1 ", ApustuAnitza.class);
		Aquery.setParameter(1, u.getUsername());
		return Aquery.getResultList();
	}
	
	public List<Transaction> findTransakzioak(User u){
		Registered user = (Registered) db.find(User.class, u.getUsername()); 
		TypedQuery<Transaction> Tquery = db.createQuery("SELECT t FROM Transaction t WHERE t.getErabiltzailea().getUsername() =?1 ", Transaction.class);
		Tquery.setParameter(1, u.getUsername());
		return Tquery.getResultList();
		
	}
	
	public void ApustuaIrabazi(ApustuAnitza apustua) {
		ApustuAnitza apustuAnitza = db.find(ApustuAnitza.class, apustua.getApustuAnitzaNumber());
		Registered reg = (Registered) apustuAnitza.getUser();
		Registered r = (Registered) db.find(User.class, reg.getUsername());
		db.getTransaction().begin();
		apustuAnitza.setEgoera("irabazita");
		Double d=apustuAnitza.getBalioa();
		for(Apustua ap: apustuAnitza.getApustuak()) {
			d = d*ap.getKuota().getQuote();
		}
		r.updateDiruKontua(d);
		r.setIrabazitakoa(r.getIrabazitakoa()+d);
		r.setZenbat(r.getZenbat()+1);
		Transaction t = new Transaction(r, d, new Date(), "ApustuaIrabazi"); 
		db.persist(t);
		db.getTransaction().commit();
	}
	
	public void EmaitzakIpini(Quote quote) throws EventNotFinished{
		
		Quote q = db.find(Quote.class, quote); 
		String result = q.getForecast();
		
		if(new Date().compareTo(q.getQuestion().getEvent().getEventDate())<0)
			throw new EventNotFinished();

		List<Apustua> listApustuak = q.getApustuak();
		db.getTransaction().begin();
		Question que = q.getQuestion(); 
		Question question = db.find(Question.class, que); 
		question.setResult(result);
		for(Quote quo: question.getQuotes()) {
			for(Apustua apu: quo.getApustuak()) {
				
				Boolean b=apu.galdutaMarkatu(quo);
				if(b) {
					apu.getApustuAnitza().setEgoera("galduta");
				}else {
					apu.setEgoera("irabazita");
				}
			}
		}
		db.getTransaction().commit();
		for(Apustua a : listApustuak) {
			db.getTransaction().begin();
			Boolean bool=a.getApustuAnitza().irabazitaMarkatu();
			db.getTransaction().commit();
			if(bool) {
				this.ApustuaIrabazi(a.getApustuAnitza());
			}
		}
	}
	
	public boolean gertaeraEzabatu(Event ev) {
		Event event  = db.find(Event.class, ev); 
		boolean resultB = true; 
		List<Question> listQ = event.getQuestions(); 
		
		for(Question q : listQ) {
			if(q.getResult() == null) {
				resultB = false; 
			}
		}
		if(resultB == false) {
			return false;
		}else if(new Date().compareTo(event.getEventDate())<0) {
			TypedQuery<Quote> Qquery = db.createQuery("SELECT q FROM Quote q WHERE q.getQuestion().getEvent().getEventNumber() =?1", Quote.class);
			Qquery.setParameter(1, event.getEventNumber()); 
			List<Quote> listQUO = Qquery.getResultList();
			for(int j=0; j<listQUO.size(); j++) {
				Quote quo = db.find(Quote.class, listQUO.get(j));
				for(int i=0; i<quo.getApustuak().size(); i++) {
					ApustuAnitza apustuAnitza = quo.getApustuak().get(i).getApustuAnitza();
					ApustuAnitza ap1 = db.find(ApustuAnitza.class, apustuAnitza.getApustuAnitzaNumber());
					db.getTransaction().begin();
					ap1.removeApustua(quo.getApustuak().get(i));
					db.getTransaction().commit();
					if(ap1.getApustuak().isEmpty() && !ap1.getEgoera().equals("galduta")) {
						this.apustuaEzabatu(ap1.getUser(), ap1);
					}else if(!ap1.getApustuak().isEmpty() && ap1.irabazitaMarkatu()){
						this.ApustuaIrabazi(ap1);
					}
					db.getTransaction().begin();
					Sport spo =quo.getQuestion().getEvent().getSport();
					spo.setApustuKantitatea(spo.getApustuKantitatea()-1);
					KirolEstatistikak ke=ap1.getUser().kirolEstatistikakLortu(spo);
					ke.setKont(ke.getKont()-1);
					db.getTransaction().commit();
				}
			}
			
		}
		db.getTransaction().begin();
		db.remove(event);
		db.getTransaction().commit();
		return true; 
	}
	
	public String saldoaBistaratu(User u) {
		Registered reg = (Registered)db.find(User.class, u.getUsername());
		return reg.getDirukop().toString();
	}
	
	public List<ElkarrizketaContainer> elkarrizketakLortu(User u){
		User user = db.find(User.class, u.getUsername());
		List<Elkarrizketa> elkList = user.getElkarrizketak();
		List<ElkarrizketaContainer> lista= new LinkedList<ElkarrizketaContainer>();
		for(Elkarrizketa e: elkList) {
			ElkarrizketaContainer ec = new ElkarrizketaContainer(e);
			lista.add(ec);
		}
		return lista;
	}
	
	public List<MezuakContainer> mezuakLortu(Elkarrizketa e){
		Elkarrizketa elka = db.find(Elkarrizketa.class, e.getElkarrizketaNumber());
		List<MezuakContainer> lista= new LinkedList<MezuakContainer>();
		for(Message m: elka.getMessages()) {
			MezuakContainer ec = new MezuakContainer(m);
			lista.add(ec);
		}
		return lista;
	}
	
	public boolean mezuaBidali(User igorlea, String hartzailea, String titulo, String test, Elkarrizketa elkarrizketa) {
		User igorle = db.find(User.class, igorlea.getUsername());
		User hartzaile = db.find(User.class, hartzailea);
		Elkarrizketa elk=null;
		if(hartzaile==null) {
			return false;
		}else {
			db.getTransaction().begin();
			Message m = new Message(igorle, hartzaile, test);
			db.persist(m);
			if(elkarrizketa!=null) {
				elk = db.find(Elkarrizketa.class, elkarrizketa.getElkarrizketaNumber());
			}else {
				elk= new Elkarrizketa(titulo, igorle, hartzaile);
				db.persist(elk);
				m.setElkarrizketa(elk);
				igorle.addElkarrizketak(elk);
				hartzaile.addElkarrizketak(elk);
			}
			elk.addMezua(m);
			igorle.addBidalitakoMezuak(m);
			hartzaile.addJasotakoMezuak(m);
			db.getTransaction().commit();
			return true;
		}
	}
	
	public List<Registered> rankingLortu(){
		TypedQuery<Registered> Rquery = db.createQuery("SELECT r FROM Registered r", Registered.class);
		List<Registered> listR = Rquery.getResultList();
		List<Registered> ema= new ArrayList<Registered>();
		int i;
		for(Registered r: listR) {
			if(ema.isEmpty()) {
				ema.add(0, r);
			}else {
				i=0;
				while(i<ema.size() && r.getIrabazitakoa()<ema.get(i).getIrabazitakoa()) {
					i++;
				}
				ema.add(i, r);
			}
		}
		return ema;
	}
	
	public List<Event> getEventsAll() {	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev ",Event.class);   
		List<Event> events = query.getResultList();
	 	return events;
	}
	
	public void mezuaIkusita(Message m) {
		Message mezua = db.find(Message.class, m.getMessageNumber()); 
		db.getTransaction().begin();
		mezua.setIrakurrita(true);
		db.getTransaction().commit();
	}
	
	public Elkarrizketa findElkarrizketa(Elkarrizketa elk){
		Elkarrizketa elkarrizketa = db.find(Elkarrizketa.class, elk.getElkarrizketaNumber()); 
		return elkarrizketa;
	}
	
	public boolean gertaerakKopiatu(Event e, Date date) {
		Boolean b=false;
		Event gertaera = db.find(Event.class, e.getEventNumber());
		db.getTransaction().begin();
		
		
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.getDescription()=?1 and ev.getEventDate()=?2",Event.class);   
		query.setParameter(1,gertaera.getDescription());
		query.setParameter(2, date);
		if(query.getResultList().isEmpty()) {
			b=true;
			String[] taldeak = gertaera.getDescription().split("-");
			Team lokala = new Team(taldeak[0]);
			Team kanpokoa = new Team(taldeak[1]);
			Event gertKopiatu = new Event(gertaera.getDescription(), date, lokala, kanpokoa);
			gertKopiatu.setSport(gertaera.getSport());
			gertaera.getSport().addEvent(gertKopiatu);
			db.persist(gertKopiatu);
				for(Question q : gertaera.getQuestions()) {
					Question que= new Question(q.getQuestion(), q.getBetMinimum(), gertKopiatu);
					gertKopiatu.listaraGehitu(que);
					Question galdera = db.find(Question.class, q.getQuestionNumber());
					db.persist(que);
					for(Quote k: galdera.getQuotes()) {
						Quote kuo= new Quote(k.getQuote(), k.getForecast(), que);
						que.listaraGehitu(kuo);
						db.persist(kuo);
					}
				}
		}
		db.getTransaction().commit();
		return b;
	}
	
	public boolean jarraitu(Registered jabea, Registered jarraitua, Double limit) {
		Boolean b=false;
		Registered jarraitu = (Registered) db.find(User.class, jarraitua.getUsername());
		Registered harpideduna = (Registered) db.find(User.class, jabea.getUsername());
		if(!harpideduna.getJarraitutakoLista().contains(jarraitu)) {
			db.getTransaction().begin();
			Jarraitzailea jar = new Jarraitzailea(harpideduna, jarraitu);
			harpideduna.addJarraitutako(jar);
			jarraitu.addJarraitzailea(jar);
			b=true;
			db.persist(jar);
			harpideduna.setDiruLimitea(limit);
			db.getTransaction().commit();
		}
		return b;
	}
	
	public Sport gehiengoaLortu(User reg) {
		Registered user =(Registered) db.find(User.class, reg.getUsername());
		Integer max=Integer.MIN_VALUE;
		Sport s=null;
		for(KirolEstatistikak ke: user.getSportEstatistikak()) {
			if(ke.getKont()>max) {
				s=ke.getSport();
				max=ke.getKont();
			}
		}
		return s;
	}
	
	public Sport popularrenaLortu() {
		Integer max=Integer.MIN_VALUE;
		Sport s=null;
		TypedQuery<Sport> query = db.createQuery("SELECT sp FROM Sport sp ",Sport.class);
		List<Sport>sp =query.getResultList();
		Integer kont;
		for(Sport sport: sp) {
			kont=sport.getApustuKantitatea();
			if(kont>max) {
				s=sport;
				max=kont;
			}
		}
		return s;
	}
	
	public void ezJarraituTaldea(User u) {
		Registered r = (Registered) db.find(User.class, u.getUsername()); 
		db.getTransaction().begin();
		Team t = db.find(Team.class, r.getTaldea());
		t.removeUser(r);
		r.setTaldea(null);
		db.getTransaction().commit();
	}
	
	public List<Team> getAllTeams() {	
		TypedQuery<Team> query = db.createQuery("SELECT t FROM Team t ",Team.class);   
		List<Team> teams = query.getResultList();
	 	return teams;
	}
	
	public void jarraituTaldea(User u, Team t) {
		Registered r = (Registered) db.find(User.class, u.getUsername());
		Team team = db.find(Team.class, t.getIzena());
		db.getTransaction().begin();
		r.setTaldea(team);
		team.addUser(r);
		db.getTransaction().commit();
	}
	
	public User findUser(User user){
		return db.find(User.class, user.getUsername());
	}
	
	public List<Event> getEventsTeam(Team team) {
		Team t=db.find(Team.class, team.getIzena());
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.getLokala().getIzena()=?1 OR ev.getKanpokoa().getIzena()=?2",Event.class);
		query.setParameter(1, t.getIzena());
		query.setParameter(2, t.getIzena());
		return query.getResultList();
		
	}
}