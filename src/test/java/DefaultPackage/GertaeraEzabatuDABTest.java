package DefaultPackage;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import dataAccess.DataAccess;
import domain.ApustuAnitza;
import domain.Apustua;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Registered;
import domain.Sport;
import domain.Team;
import test.DataAccess.TestDataAccess;

public class GertaeraEzabatuDABTest {
	//sut:system under test
	static DataAccess sut=new DataAccess();
			 
		//additional operations needed to execute the test 
	static TestDataAccess testDA=new TestDataAccess();

	private Event ev;

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
		testDA.open();
		assertFalse(testDA.existsEvent(ev)); //konprobatu ez dela existitzen datubasean
		testDA.close();
		
		try {
			assertFalse(sut.gertaeraEzabatu(ev)); //GAIZKI  metodoak ez du ezer bueltatzen, programak errorea jaurtitzen du eta tratatu gabe dago
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("TEST 1 EZ GAINDITUTA");
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
			
			testDA.open();
			ev = testDA.addEventWithQuestion("Europa League", oneDate, "Who will win the match", 1, team1, team2);
			Question q3= ev.getQuestions().get(0);
			testDA.close();
			//invoke System Under Test (sut)  
			boolean emaitza=sut.gertaeraEzabatu(ev);
			
			//verify
			assertTrue(ev!=null);
			assertFalse(emaitza);//emaitza false dela egiaztatu
			
			//ev is DB
			testDA.open();
			boolean dago=testDA.existsEvent(ev);
			boolean existq3 = testDA.existQuestion(ev,q3);
			
			
				
			assertTrue(dago);
			assertTrue(existq3);				
			
			testDA.close();
			
		   } catch (Exception e) {
			   System.out.println("ERROREA PROBA3");
			   fail();
			} finally {
				  //Remove the created objects in the database (cascade removing)   
				testDA.open();
		         boolean b=testDA.removeEvent(ev);
		         
		        
		          testDA.close();
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
			
			testDA.open();
			t1="Team1";
			t2="Team2";
			s="Futbola";
			us="iosu";
			Team team1=testDA.addTeam(t1);
			Team team2=testDA.addTeam(t2);
			Sport sport=testDA.addSport(s);
			u=testDA.addUser(us, "passwd123", 12,sport);
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date oneDate=null;
			try {
				oneDate = sdf.parse("2022-11-22"); //gertaera jada pasa zen
			} catch (ParseException e) {
				e.printStackTrace();
			}	
			//configure the state of the system (create object in the dabatase)
			
			ev = testDA.addEventWithQuestion("Europa League", oneDate, "How many penalties there will be?", 1, team1, team2);				
			Question q7= ev.getQuestions().get(0);
			ev.setSport(sport);
			testDA.setQuestionResult(q7, "2 penalties");
			testDA.addQuotetoQuestion(q7, 1.43, "Forecast 1 penalty");
			testDA.close();
			Quote k2=q7.getQuotes().get(0);
			double diruaHasieran=100.0;
			sut.DiruaSartu(u, diruaHasieran, oneDate, "sartu");
			sut.ApustuaEgin(u, ev.getQuestions().get(0).getQuotes(), 4.0, -1);
			testDA.open();
			Apustua apustu=testDA.aurkituApustua(ev.getQuestions().get(0).getQuotes().get(0));
			ApustuAnitza apa=apustu.getApustuAnitza();
			testDA.close();
			
								
			//invoke System Under Test (sut)  
			boolean emaitza=sut.gertaeraEzabatu(ev);
							
			//verify
			assertNotNull(ev);
			assertTrue(emaitza);//emaitza true dela egiaztatu
			
			//ev is DB
			testDA.open();
			testDA.ezabatuKE(u);
			boolean dago=testDA.existsEvent(ev);
			boolean existq7 = testDA.questionExistitzenDa(q7);
			boolean existKuote=testDA.kuotaExistitzenDa(k2);
			boolean existAp=testDA.apustuaExistitzenDa(apustu);
			boolean existApA= testDA.apustuAnitzaExistitzenDa(apa);
			boolean existUser=testDA.userExistitzenDa(u);
			boolean existTeam=testDA.teamExistitzenDa(team1);
			boolean existTeam2=testDA.teamExistitzenDa(team2);
			testDA.close();
			double diruTotala=Double.parseDouble(sut.saldoaBistaratu(u));
			
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
			   System.out.println("Errorea proba 7");
			   e.printStackTrace();
			   fail();
			} finally {
				testDA.open();
				boolean b1=testDA.removeTeam(t1);
				boolean b2=testDA.removeTeam(t2);
				boolean b5=testDA.ezabatuTransakzio(u);
				boolean b0=testDA.removeUser(us);
				boolean b4=testDA.removeSport(s);
				
					testDA.close();
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
			
			testDA.open();
			t1="Team1";
			t2="Team2";
			s="Futbola";
			us="iosu";
			Team team1=testDA.addTeam(t1);
			Team team2=testDA.addTeam(t2);
			Sport sport=testDA.addSport(s);
			u=testDA.addUser(us, "passwd123", 12,sport);
			
			Date oneDate = new Date();
			Calendar c = Calendar.getInstance(); 
			c.setTime(oneDate); 
			c.add(Calendar.DATE, -1);
			oneDate = c.getTime();	
			Format formatter = new SimpleDateFormat("yyyy-MM-dd");
			String gaurkodataString = formatter.format(oneDate);
			System.out.println("Atzokodata data: "+gaurkodataString);
			//configure the state of the system (create object in the dabatase)
			testDA.open();
			ev = testDA.addEventWithQuestion("Europa League", oneDate, "Who will score first?", 1, team1, team2);	
			ev.setSport(sport);
			Question q2= ev.getQuestions().get(0);
			testDA.setQuestionResult(q2, "Messi");
			testDA.addQuotetoQuestion(q2, 1.5, "Messi scores first");
			testDA.close();
			Quote k2=q2.getQuotes().get(0);
			double diruaHasieran=100.0;
			sut.DiruaSartu(u, diruaHasieran, oneDate, "sartu");
			sut.ApustuaEgin(u, ev.getQuestions().get(0).getQuotes(), 4.0, -1);
			testDA.open();
			Apustua apustu=testDA.aurkituApustua(ev.getQuestions().get(0).getQuotes().get(0));
			ApustuAnitza apa=apustu.getApustuAnitza();
			testDA.close();
							
			//invoke System Under Test (sut)  
			boolean emaitza=sut.gertaeraEzabatu(ev);
							
			//verify
			assertNotNull(ev);
			assertTrue(emaitza);//emaitza true dela egiaztatu
			
			//ev is DB
			testDA.open();
			testDA.ezabatuKE(u);
			boolean dago=testDA.existsEvent(ev);
			boolean existq2 = testDA.questionExistitzenDa(q2);
			boolean existKuote=testDA.kuotaExistitzenDa(k2);
			boolean existAp=testDA.apustuaExistitzenDa(apustu);
			boolean existApA= testDA.apustuAnitzaExistitzenDa(apa);
			boolean existUser=testDA.userExistitzenDa(u);
			boolean existTeam=testDA.teamExistitzenDa(team1);
			boolean existTeam2=testDA.teamExistitzenDa(team2);
			testDA.close();
			double diruTotala=Double.parseDouble(sut.saldoaBistaratu(u));
			
			assertFalse(dago);			
			assertFalse(existq2);
			assertFalse(existKuote);
			assertFalse(existAp);
			assertTrue(existUser);//user ez da berez automatikoki borratu behar
			assertNotEquals(diruaHasieran,diruTotala);			//DIRUA EZ DA BUELTATU        
			assertTrue(existTeam);//user ez da berez automatikoki borratu behar
			assertTrue(existTeam2);//user ez da berez automatikoki borratu behar
			assertTrue(existApA);//apustuAnitza ez da berez automatikoki borratu behar 
			
		   } catch (Exception e) {
			   System.out.println("ERROREA TEST4");
			   e.getMessage();
			   fail();
			   
			} finally {
				testDA.open();
				boolean b1=testDA.removeTeam(t1);
				boolean b2=testDA.removeTeam(t2);
				boolean b5=testDA.ezabatuTransakzio(u);
				boolean b0=testDA.removeUser(us);
				boolean b4=testDA.removeSport(s);
				
					testDA.close();
				System.out.println("Finally "+b0+b1+b2+b4+b5);        
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
			
			testDA.open();
			t1="Team1";
			t2="Team2";
			s="Futbola";
			us="iosu";
			Team team1=testDA.addTeam(t1);
			Team team2=testDA.addTeam(t2);
			Sport sport=testDA.addSport(s);
			u=testDA.addUser(us, "passwd123", 12,sport);
			
			String gaurkodataString;
			Format formatter;
			Date oneDate = new Date();

			
			formatter = new SimpleDateFormat("yyyy-MM-dd");
			gaurkodataString = formatter.format(oneDate);
			System.out.println("Gaurko data: "+gaurkodataString);
			//configure the state of the system (create object in the dabatase)
			testDA.open();
			ev = testDA.addEventWithQuestion("Europa League", oneDate, "Who will score first?", 1, team1, team2);	
			ev.setSport(sport);
			Question q2= ev.getQuestions().get(0);
			testDA.setQuestionResult(q2, "Messi");
			testDA.addQuotetoQuestion(q2, 1.5, "Messi scores first");
			testDA.close();
			Quote k2=q2.getQuotes().get(0);
			double diruaHasieran=100.0;
			sut.DiruaSartu(u, diruaHasieran, oneDate, "sartu");
			sut.ApustuaEgin(u, ev.getQuestions().get(0).getQuotes(), 4.0, -1);
			testDA.open();
			Apustua apustu=testDA.aurkituApustua(ev.getQuestions().get(0).getQuotes().get(0));
			ApustuAnitza apa=apustu.getApustuAnitza();
			testDA.close();
							
			//invoke System Under Test (sut)  
			boolean emaitza=sut.gertaeraEzabatu(ev);
							
			//verify
			assertNotNull(ev);
			assertTrue(emaitza);//emaitza true dela egiaztatu
			
			//ev is DB
			testDA.open();
			testDA.ezabatuKE(u);
			boolean dago=testDA.existsEvent(ev);
			boolean existq2 = testDA.questionExistitzenDa(q2);
			boolean existKuote=testDA.kuotaExistitzenDa(k2);
			boolean existAp=testDA.apustuaExistitzenDa(apustu);
			boolean existApA= testDA.apustuAnitzaExistitzenDa(apa);
			boolean existUser=testDA.userExistitzenDa(u);
			boolean existTeam=testDA.teamExistitzenDa(team1);
			boolean existTeam2=testDA.teamExistitzenDa(team2);
			testDA.close();
			double diruTotala=Double.parseDouble(sut.saldoaBistaratu(u));
			
			assertFalse(dago);			
			assertFalse(existq2);
			assertFalse(existKuote);
			assertFalse(existAp);
			assertTrue(existUser);//user ez da berez automatikoki borratu behar
			assertNotEquals(diruaHasieran,diruTotala);			//DIRUA EZ DA BUELTATU
			assertTrue(existTeam);//user ez da berez automatikoki borratu behar
			assertTrue(existTeam2);//user ez da berez automatikoki borratu behar
			assertTrue(existApA);//apustuAnitza ez da berez automatikoki borratu behar 
			
		   } catch (Exception e) {
			   System.out.println("ERROREA TEST4");
			   e.getMessage();
			   fail();
			   
			} finally {
				testDA.open();
				boolean b1=testDA.removeTeam(t1);
				boolean b2=testDA.removeTeam(t2);
				boolean b5=testDA.ezabatuTransakzio(u);
				boolean b0=testDA.removeUser(us);
				boolean b4=testDA.removeSport(s);
				
					testDA.close();
				System.out.println("Finally "+b0+b1+b2+b4+b5);        
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
			
			testDA.open();
			t1="Team1";
			t2="Team2";
			s="Futbola";
			us="iosu";
			Team team1=testDA.addTeam(t1);
			Team team2=testDA.addTeam(t2);
			Sport sport=testDA.addSport(s);
			u=testDA.addUser(us, "passwd123", 12,sport);
			
			
			
			Date oneDate = new Date();
			
			Calendar c = Calendar.getInstance(); 
			c.setTime(oneDate); 
			c.add(Calendar.DATE, 1);
			oneDate = c.getTime();

			
			Format formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dataString = formatter.format(oneDate);
			System.out.println("Biharko data: "+dataString);
			//configure the state of the system (create object in the dabatase)
			testDA.open();
			ev = testDA.addEventWithQuestion("Europa League", oneDate, "Who will score first?", 1, team1, team2);	
			ev.setSport(sport);
			Question q2= ev.getQuestions().get(0);
			testDA.setQuestionResult(q2, "Messi");
			testDA.addQuotetoQuestion(q2, 1.5, "Messi scores first");
			testDA.close();
			Quote k2=q2.getQuotes().get(0);
			double diruaHasieran=100.0;
			sut.DiruaSartu(u, diruaHasieran, oneDate, "sartu");
			sut.ApustuaEgin(u, ev.getQuestions().get(0).getQuotes(), 4.0, -1);
			testDA.open();
			Apustua apustu=testDA.aurkituApustua(ev.getQuestions().get(0).getQuotes().get(0));
			ApustuAnitza apa=apustu.getApustuAnitza();
			testDA.close();
							
			//invoke System Under Test (sut)  
			boolean emaitza=sut.gertaeraEzabatu(ev);
							
			//verify
			assertNotNull(ev);
			assertTrue(emaitza);//emaitza true dela egiaztatu
			
			//ev is DB
			testDA.open();
			testDA.ezabatuKE(u);
			boolean dago=testDA.existsEvent(ev);
			boolean existq2 = testDA.questionExistitzenDa(q2);
			boolean existKuote=testDA.kuotaExistitzenDa(k2);
			boolean existAp=testDA.apustuaExistitzenDa(apustu);
			boolean existApA= testDA.apustuAnitzaExistitzenDa(apa);
			boolean existUser=testDA.userExistitzenDa(u);
			boolean existTeam=testDA.teamExistitzenDa(team1);
			boolean existTeam2=testDA.teamExistitzenDa(team2);
			testDA.close();
			double diruTotala=Double.parseDouble(sut.saldoaBistaratu(u));
			
			assertFalse(dago);			
			assertFalse(existq2);
			assertFalse(existKuote);
			assertFalse(existAp);
			assertTrue(existUser);//user ez da berez automatikoki borratu behar
			assertNotEquals(diruaHasieran,diruTotala);			//DIRUA EZ DA BUELTATU
			assertTrue(existTeam);//user ez da berez automatikoki borratu behar
			assertTrue(existTeam2);//user ez da berez automatikoki borratu behar
			assertFalse(existApA);//apustuAnitza borratzen da 
			
		   } catch (Exception e) {
			   System.out.println("ERROREA TEST4");
			   e.getMessage();
			   fail();
			   
			} finally {
				testDA.open();
				boolean b1=testDA.removeTeam(t1);
				boolean b2=testDA.removeTeam(t2);
				boolean b5=testDA.ezabatuTransakzio(u);
				boolean b0=testDA.removeUser(us);
				boolean b4=testDA.removeSport(s);
				
					testDA.close();
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
			System.out.println("TEST 7 EZ GAINDITUTA");
			System.out.println(e.getMessage());
			fail();
		}	
	
	}
	
	
}
