package DefaultPackage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.Assert.assertEquals;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.junit.BeforeClass;

import org.junit.jupiter.api.DisplayName;
import org.junit.Test;

import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.ApustuAnitza;
import domain.Apustua;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Registered;
import domain.Sport;
import domain.Team;
import test.BusinessLogic.TestFacadeImplementation;

public class GertaeraEzabatuINTTest {
	static BLFacadeImplementation sut;
	static TestFacadeImplementation testBL;
	
	
	private Event ev;
	

	@BeforeClass //JUNIT 4
	public static void setUpClass() {
		
		DataAccess da= new DataAccess();

		sut=new BLFacadeImplementation(da);

		testBL= new TestFacadeImplementation();
	}
	
	
	@Test
	@DisplayName("Event not in DB")
	public void test1() {
		Team t1=new Team("RSO");
		Team t2=new Team("ATL");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date oneDate=null;
		try {
			oneDate = sdf.parse("2022-10-30");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ev =new Event(99,"Finala",oneDate,t1,t2);
	
		assertFalse(testBL.existsEvent(ev)); //konprobatu ez dela existitzen datubasean
		
		
		try {
			assertFalse(sut.gertaeraEzabatu(ev)); //GAIZKI  metodoak ez du ezer bueltatzen, programak errorea jaurtitzen du eta tratatu gabe dago
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROREA TEST1");
			System.out.println(e.getMessage());
			fail();
		}
		
		
	}
	
	@Test
	@DisplayName("Galderak erantzurik gabe daude")
	public void test2() {//emaitzak ez dituzte
		try {
			//define paramaters
			
			Team team1=new Team("Atletico de Madrid");
			Team team2= new Team("FC Barcelona");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date oneDate=null;
			try {
				oneDate = sdf.parse("2022-10-30");
			} catch (ParseException e) {
				e.printStackTrace();
			}	
	
			ev = testBL.addEventWithQuestion("Europa League", oneDate, "Who will win the match", 1, team1, team2);
			Question q3= ev.getQuestions().get(0);
			
			//invoke System Under Test (sut)  
			boolean emaitza=sut.gertaeraEzabatu(ev);
			
			//verify
			assertTrue(ev!=null);
			assertFalse(emaitza);//emaitza false dela egiaztatu
			
			//ev is DB
			
			boolean dago=testBL.existsEvent(ev);
			boolean existq3 = testBL.existQuestion(ev,q3);
			
			
				
			assertTrue(dago);
			assertTrue(existq3);				
			
			
			
		   } catch (Exception e) {
			   System.out.println("ERROREA PROBA2");
			   fail();
			} finally {
				  //Remove the created objects in the database (cascade removing)   
				
		         boolean b=testBL.removeEvent(ev);
		         
		        
		         
		         System.out.println("Finally "+b);          
		        }
		   }
	
	@Test
	@DisplayName("emaitzak argitaratuta daude --> itzuli dirua eta borratu normal")
	public void test3() {
		
		String t1=null;
		String t2=null;
		String s=null;
		String us=null;
		Registered u=null;
		
		try {
			//define paramaters
			
			
			t1="Team1";
			t2="Team2";
			s="Futbola";
			us="iosu";
			Team team1=testBL.addTeam(t1);
			Team team2=testBL.addTeam(t2);
			Sport sport=testBL.addSport(s);
			u=testBL.addUser(us, "passwd123", 12,sport);
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date oneDate=null;
			try {
				oneDate = sdf.parse("2022-11-22"); //gertaera jada pasa zen
			} catch (ParseException e) {
				e.printStackTrace();
			}	
			//configure the state of the system (create object in the dabatase)
			
			ev = testBL.addEventWithQuestion("Europa League", oneDate, "How many penalties there will be?", 1, team1, team2);	
			testBL.setSport(ev,sport);
			Question q7= testBL.getQuestion(ev);
			
			testBL.setQuestionResult(q7, "2 penalties");
			testBL.addQuotetoQuestion(q7, 1.43, "Forecast 1 penalty");
			Vector<Quote> kuotalista=testBL.getQuotes(q7);
			
			Registered dbUser=testBL.getRegistered(u);
			double diruaHasieran=100.0;
			
			testBL.DiruaSartu(dbUser, diruaHasieran, oneDate, "sartu");
			testBL.apustuaXAldiz(dbUser, kuotalista, 4.0, -1,false);
			Apustua apustu=testBL.aurkituApustua(kuotalista.get(0));
			ApustuAnitza apa=apustu.getApustuAnitza();
			Quote k2=kuotalista.get(0);
			
								
			//invoke System Under Test (sut)  
			boolean emaitza=sut.gertaeraEzabatu(ev);
							
			//verify
			assertNotNull(ev);
			assertTrue(emaitza);//emaitza true dela egiaztatu
			
			//ev is DB
			
			testBL.ezabatuKE(u);
			boolean dago=testBL.existsEvent(ev);
			boolean existq7 = testBL.questionExistitzenDa(q7);
			boolean existKuote=testBL.kuotaExistitzenDa(k2);
			boolean existAp=testBL.apustuaExistitzenDa(apustu);
			boolean existApA= testBL.apustuAnitzaExistitzenDa(apa);
			boolean existUser=testBL.userExistitzenDa(u);
			boolean existTeam=testBL.teamExistitzenDa(team1);
			boolean existTeam2=testBL.teamExistitzenDa(team2);
			double diruTotala=Double.parseDouble(testBL.saldoaBistaratu(u));
			
			assertFalse(dago);			
			assertFalse(existq7);
			assertFalse(existKuote);
			assertFalse(existAp);
			assertTrue(existUser);//user ez da berez automatikoki borratu behar
			assertEquals(diruaHasieran,diruTotala);//konparatu hasieran zuen diru kantitate berdina duela
			assertTrue(existTeam);//user ez da berez automatikoki borratu behar
			assertTrue(existTeam2);//user ez da berez automatikoki borratu behar
			assertFalse(existApA);//apustuAnitza azkeneko iterazioan ezabatzen da, beraz ez da existitu behar	
			
		   } catch (Exception e) {
			   System.out.println("Errorea proba 3");
			   e.printStackTrace();
			   fail();
			} finally {
				
				boolean b1=testBL.removeTeam(t1);
				boolean b2=testBL.removeTeam(t2);
				boolean b5=testBL.ezabatuTransakzio(u);
				boolean b0=testBL.removeUser(us);
				boolean b4=testBL.removeSport(s);
				System.out.println("Finally "+b0+b1+b2+b4+b5);         
		    }
	}
	
	@Test
	@DisplayName("gertaera pasa zen: MugaBalioa n-1 (gertaera atzo)")
	public void test4() {
		String t1=null;
		String t2=null;
		String s=null;
		String us=null;
		Registered u=null;
		
		try {
			//define paramaters
			
			
			t1="Team1";
			t2="Team2";
			s="Futbola";
			us="iosu";
			Team team1=testBL.addTeam(t1);
			Team team2=testBL.addTeam(t2);
			Sport sport=testBL.addSport(s);
			u=testBL.addUser(us, "passwd123", 12,sport);
			
			Date oneDate = new Date();
			Calendar c = Calendar.getInstance(); 
			c.setTime(oneDate); 
			c.add(Calendar.DATE, -1);
			oneDate = c.getTime();	
			Format formatter = new SimpleDateFormat("yyyy-MM-dd");
			String gaurkodataString = formatter.format(oneDate);
			System.out.println("Atzokodata data: "+gaurkodataString);
			//configure the state of the system (create object in the dabatase)
			
			ev = testBL.addEventWithQuestion("Europa League", oneDate, "How many penalties there will be?", 1, team1, team2);	
			testBL.setSport(ev,sport);
			Question q7= testBL.getQuestion(ev);
			
			testBL.setQuestionResult(q7, "2 penalties");
			testBL.addQuotetoQuestion(q7, 1.43, "Forecast 1 penalty");
			Vector<Quote> kuotalista=testBL.getQuotes(q7);
			
			Registered dbUser=testBL.getRegistered(u);
			double diruaHasieran=100.0;
			
			testBL.DiruaSartu(dbUser, diruaHasieran, oneDate, "sartu");
			testBL.apustuaXAldiz(dbUser, kuotalista, 4.0, -1,false);
			Apustua apustu=testBL.aurkituApustua(kuotalista.get(0));
			ApustuAnitza apa=apustu.getApustuAnitza();
			Quote k2=kuotalista.get(0);
			
								
			//invoke System Under Test (sut)  
			boolean emaitza=sut.gertaeraEzabatu(ev);
							
			//verify
			assertNotNull(ev);
			assertTrue(emaitza);//emaitza true dela egiaztatu
			
			//ev is DB
			
			testBL.ezabatuKE(u);
			boolean dago=testBL.existsEvent(ev);
			boolean existq7 = testBL.questionExistitzenDa(q7);
			boolean existKuote=testBL.kuotaExistitzenDa(k2);
			boolean existAp=testBL.apustuaExistitzenDa(apustu);
			boolean existApA= testBL.apustuAnitzaExistitzenDa(apa);
			boolean existUser=testBL.userExistitzenDa(u);
			boolean existTeam=testBL.teamExistitzenDa(team1);
			boolean existTeam2=testBL.teamExistitzenDa(team2);
			double diruTotala=Double.parseDouble(testBL.saldoaBistaratu(u));
			
			assertFalse(dago);			
			assertFalse(existq7);
			assertFalse(existKuote);
			assertFalse(existAp);
			assertTrue(existUser);//user ez da berez automatikoki borratu behar
			//assertNotEquals(diruaHasieran,diruTotala); 	//dirua ez dela bueltatu konprobatu
			assertFalse(diruaHasieran==diruTotala); 	//dirua ez dela bueltatu konprobatu

			assertTrue(existTeam);//user ez da berez automatikoki borratu behar
			assertTrue(existTeam2);//user ez da berez automatikoki borratu behar
			assertTrue(existApA);		//kasu honetan apustuanitza ez du borratu behar, gertaera jada pasa zen
			
		   } catch (Exception e) {
			   System.out.println("Errorea proba 3");
			   e.printStackTrace();
			   fail();
			} finally {
				
				boolean b1=testBL.removeTeam(t1);
				boolean b2=testBL.removeTeam(t2);
				boolean b0=testBL.removeUser(us);
				boolean b4=testBL.removeSport(s);
				System.out.println("Finally "+b0+b1+b2+b4);         
		    }
	}
	
	@Test
	@DisplayName("gertaera pasa zen: MugaBalioa n (gaur)")
	public void test5() {
		String t1=null;
		String t2=null;
		String s=null;
		String us=null;
		Registered u=null;
		
		try {
			//define paramaters
			
			
			t1="Team1";
			t2="Team2";
			s="Futbola";
			us="iosu";
			Team team1=testBL.addTeam(t1);
			Team team2=testBL.addTeam(t2);
			Sport sport=testBL.addSport(s);
			u=testBL.addUser(us, "passwd123", 12,sport);
			
			String gaurkodataString;
			Format formatter;
			Date oneDate = new Date();

			
			formatter = new SimpleDateFormat("yyyy-MM-dd");
			gaurkodataString = formatter.format(oneDate);
			System.out.println("Gaurko data: "+gaurkodataString);
			//configure the state of the system (create object in the dabatase)
			
			ev = testBL.addEventWithQuestion("Europa League", oneDate, "How many penalties there will be?", 1, team1, team2);	
			testBL.setSport(ev,sport);
			Question q7= testBL.getQuestion(ev);
			
			testBL.setQuestionResult(q7, "2 penalties");
			testBL.addQuotetoQuestion(q7, 1.43, "Forecast 1 penalty");
			Vector<Quote> kuotalista=testBL.getQuotes(q7);
			
			Registered dbUser=testBL.getRegistered(u);
			double diruaHasieran=100.0;
			
			testBL.DiruaSartu(dbUser, diruaHasieran, oneDate, "sartu");
			testBL.apustuaXAldiz(dbUser, kuotalista, 4.0, -1,false);
			Apustua apustu=testBL.aurkituApustua(kuotalista.get(0));
			ApustuAnitza apa=apustu.getApustuAnitza();
			Quote k2=kuotalista.get(0);
			
								
			//invoke System Under Test (sut)  
			boolean emaitza=sut.gertaeraEzabatu(ev);
							
			//verify
			assertNotNull(ev);
			assertTrue(emaitza);//emaitza true dela egiaztatu
			
			//ev is DB
			
			testBL.ezabatuKE(u);
			boolean dago=testBL.existsEvent(ev);
			boolean existq7 = testBL.questionExistitzenDa(q7);
			boolean existKuote=testBL.kuotaExistitzenDa(k2);
			boolean existAp=testBL.apustuaExistitzenDa(apustu);
			boolean existApA= testBL.apustuAnitzaExistitzenDa(apa);
			boolean existUser=testBL.userExistitzenDa(u);
			boolean existTeam=testBL.teamExistitzenDa(team1);
			boolean existTeam2=testBL.teamExistitzenDa(team2);
			double diruTotala=Double.parseDouble(testBL.saldoaBistaratu(u));
			
			assertFalse(dago);			
			assertFalse(existq7);
			assertFalse(existKuote);
			assertFalse(existAp);
			assertTrue(existUser);//user ez da berez automatikoki borratu behar
			//assertNotEquals(diruaHasieran,diruTotala); 	//dirua ez dela bueltatu konprobatu
			assertFalse(diruaHasieran==diruTotala); 	//dirua ez dela bueltatu konprobatu

			assertTrue(existTeam);//user ez da berez automatikoki borratu behar
			assertTrue(existTeam2);//user ez da berez automatikoki borratu behar
			assertTrue(existApA);		//kasu honetan apustuanitza ez du borratu behar, gertaera jada pasa zen
			
		   } catch (Exception e) {
			   System.out.println("Errorea proba 3");
			   e.printStackTrace();
			   fail();
			} finally {
				
				boolean b1=testBL.removeTeam(t1);
				boolean b2=testBL.removeTeam(t2);
				boolean b0=testBL.removeUser(us);
				boolean b4=testBL.removeSport(s);
				System.out.println("Finally "+b0+b1+b2+b4);         
		    }
	}
	
	@Test
	@DisplayName("gertaera pasa zen: MugaBalioa n+1 (gertara bihar)")
	public void test6() {
		String t1=null;
		String t2=null;
		String s=null;
		String us=null;
		Registered u=null;
		
		try {
			//define paramaters
			

			t1="Team1";
			t2="Team2";
			s="Futbola";
			us="iosu";
			Team team1=testBL.addTeam(t1);
			Team team2=testBL.addTeam(t2);
			Sport sport=testBL.addSport(s);
			u=testBL.addUser(us, "passwd123", 12,sport);
			
			
			
			Date oneDate = new Date();
			
			Calendar c = Calendar.getInstance(); 
			c.setTime(oneDate); 
			c.add(Calendar.DATE, 1);
			oneDate = c.getTime();

			
			Format formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dataString = formatter.format(oneDate);
			System.out.println("Biharko data: "+dataString);
			//configure the state of the system (create object in the dabatase)
		
			ev = testBL.addEventWithQuestion("Europa League", oneDate, "How many penalties there will be?", 1, team1, team2);	
			testBL.setSport(ev,sport);
			Question q7= testBL.getQuestion(ev);
			
			testBL.setQuestionResult(q7, "2 penalties");
			testBL.addQuotetoQuestion(q7, 1.43, "Forecast 1 penalty");
			Vector<Quote> kuotalista=testBL.getQuotes(q7);
			
			Registered dbUser=testBL.getRegistered(u);
			double diruaHasieran=100.0;
			
			testBL.DiruaSartu(dbUser, diruaHasieran, oneDate, "sartu");
			testBL.apustuaXAldiz(dbUser, kuotalista, 4.0, -1,false);
			Apustua apustu=testBL.aurkituApustua(kuotalista.get(0));
			ApustuAnitza apa=apustu.getApustuAnitza();
			Quote k2=kuotalista.get(0);
			
								
			//invoke System Under Test (sut)  
			boolean emaitza=sut.gertaeraEzabatu(ev);
							
			//verify
			assertNotNull(ev);
			assertTrue(emaitza);//emaitza true dela egiaztatu
			
			//ev is DB
			
			testBL.ezabatuKE(u);
			boolean dago=testBL.existsEvent(ev);
			boolean existq7 = testBL.questionExistitzenDa(q7);
			boolean existKuote=testBL.kuotaExistitzenDa(k2);
			boolean existAp=testBL.apustuaExistitzenDa(apustu);
			boolean existApA= testBL.apustuAnitzaExistitzenDa(apa);
			boolean existUser=testBL.userExistitzenDa(u);
			boolean existTeam=testBL.teamExistitzenDa(team1);
			boolean existTeam2=testBL.teamExistitzenDa(team2);
			double diruTotala=Double.parseDouble(testBL.saldoaBistaratu(u));
			
			assertFalse(dago);			
			assertFalse(existq7);
			assertFalse(existKuote);
			assertFalse(existAp);
			assertTrue(existUser);//user ez da berez automatikoki borratu behar
			assertEquals(diruaHasieran,diruTotala);//konparatu hasieran zuen diru kantitate berdina duela
			assertTrue(existTeam);//user ez da berez automatikoki borratu behar
			assertTrue(existTeam2);//user ez da berez automatikoki borratu behar
			assertFalse(existApA);//apustuAnitza azkeneko iterazioan ezabatzen da, beraz ez da existitu behar	
			
		   } catch (Exception e) {
			   System.out.println("Errorea proba 3");
			   e.printStackTrace();
			   fail();
			} finally {
				
				boolean b1=testBL.removeTeam(t1);
				boolean b2=testBL.removeTeam(t2);
				boolean b5=testBL.ezabatuTransakzio(u);
				boolean b0=testBL.removeUser(us);
				boolean b4=testBL.removeSport(s);
				System.out.println("Finally "+b0+b1+b2+b4+b5);         
		    }
	}
	
	@Test
	@DisplayName("Event=null?")
	public void test7() {
		ev =null;
		try {
			assertFalse(sut.gertaeraEzabatu(ev));//GAIZKI  emaitza ez du ezer bueltatzen, programak errorea jaurtitzen du eta tratatu gabe dago
		}catch(Exception e) {
			System.out.println("ERROREA TEST7");
			System.out.println(e.getMessage());
			fail();
		}	
	
	}

}

