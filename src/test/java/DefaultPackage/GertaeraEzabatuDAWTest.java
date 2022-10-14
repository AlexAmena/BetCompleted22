package DefaultPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Event;
import domain.Team;
import domain.*;
import exceptions.QuestionAlreadyExist;
import test.DataAccess.TestDataAccess;

public class GertaeraEzabatuDAWTest {
	//sut:system under test
	static DataAccess sut=new DataAccess();
		 
	//additional operations needed to execute the test 
	static TestDataAccess testDA=new TestDataAccess();

		private Event ev;

		@Test
		public void proba2() {//galderarik gabeko gertera
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
				//configure the state of the system (create object in the dabatase)
				testDA.open();
				ev = testDA.addEventWithQuestion("Europa League", oneDate, "Who will win the match", 1, team1, team2);
				Question q= ev.getQuestions().get(0);
				testDA.close();
				
				//invoke System Under Test (sut)  
				boolean emaitza=sut.gertaeraEzabatu(ev);
				
				//verify
				assertTrue(ev!=null);
				assertFalse(emaitza);//emaitza false dela egiaztatu
				
				//ev is DB
				testDA.open();
				boolean dago=testDA.existsEvent(ev);
				boolean exist = testDA.existQuestion(ev,q);
					
				//assertEquals(ev.getEventNumber(),dago.getEventNumber());//datubasean dago
				assertTrue(dago);
				
				assertTrue(exist);
				testDA.close();
				
			   } catch (Exception e) {
				   System.out.println("ERROREA PROBA2");
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
		public void proba3() {//galdera batzuk bakarrik dituzte emaitza
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
				//configure the state of the system (create object in the database)
				testDA.open();
				ev = testDA.addEventWithQuestion("Europa League", oneDate, "Who will win the match", 1, team1, team2);
				
				testDA.addQuestiontoEvent(ev, "How many Yellow Cards?", 2.0);
				
				
				Question q3= ev.getQuestions().get(0);
				Question q4= ev.getQuestions().get(1);
				testDA.setQuestionResult(q3, "7");
				
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
				boolean existq4 = testDA.existQuestion(ev,q4);
				
					
				//assertEquals(ev.getEventNumber(),dago.getEventNumber());//datubasean dago
				assertTrue(dago);
				
				assertTrue(existq3);
				assertTrue(existq4);				
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
		public void proba4() {//gertaera jada pasa zen
			try {
				//define paramaters
				
				Team team1=new Team("Atletico de Madrid");
				Team team2= new Team("FC Barcelona");
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date oneDate=null;
				try {
					oneDate = sdf.parse("2022-01-01"); 
				} catch (ParseException e) {
					e.printStackTrace();
				}	
				//configure the state of the system (create object in the dabatase)
				testDA.open();
				ev = testDA.addEventWithQuestion("Europa League", oneDate, "How many penalties there will be?", 1, team1, team2);				
				Question q5= ev.getQuestions().get(0);
				testDA.setQuestionResult(q5, "8");
				
				testDA.close();
								
				//invoke System Under Test (sut)  
				boolean emaitza=sut.gertaeraEzabatu(ev);
								
				//verify
				assertNotNull(ev);
				assertTrue(emaitza);//emaitza true dela egiaztatu
				
				//ev is DB
				testDA.open();
				boolean dago=testDA.existsEvent(ev);
				boolean existq3 = testDA.questionExistitzenDa(q5);
				assertFalse(dago);			
				assertFalse(existq3);
				testDA.close();
				
			   } catch (Exception e) {
				   System.out.println("ERROREA PROBA4");
				   fail();
				} finally {
					 // ez da ezer egin behar, dena ondo borratu delako        
			        }
			   }
		
		@Test //gertaerako galdera guztiek dituzte emaitzak
		public void proba5() {
			try {
				//define paramaters
				
				Team team1=new Team("Atletico de Madrid");
				Team team2= new Team("FC Barcelona");
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date oneDate=null;
				try {
					oneDate = sdf.parse("2022-11-05"); //gertaera jada pasa zen
				} catch (ParseException e) {
					e.printStackTrace();
				}	
				//configure the state of the system (create object in the dabatase)
				testDA.open();
				ev = testDA.addEventWithQuestion("Europa League", oneDate, "How many penalties there will be?", 1, team1, team2);				
				Question q50= ev.getQuestions().get(0);
				testDA.setQuestionResult(q50, "2 penalties");
				testDA.close();
								
				//invoke System Under Test (sut)  
				boolean emaitza=sut.gertaeraEzabatu(ev);
								
				//verify
				assertNotNull(ev);
				assertTrue(emaitza);//emaitza true dela egiaztatu
				
				//ev is DB
				testDA.open();
				boolean dago=testDA.existsEvent(ev);
				boolean existq3 = testDA.questionExistitzenDa(q50);
				assertFalse(dago);			
				assertFalse(existq3);
				testDA.close();
				
			   } catch (Exception e) {
				   System.out.println("ERROREA PROBA5");
				   fail();
				} finally {
					 // ez da ezer egin behar, dena ondo borratu delako        
			        }
			   }
		
		
		@Test //gertaerako galderek kuotak dituzte baino ez dago apusturik
		public void proba6() {
			try {
				//define paramaters
				
				Team team1=new Team("Atletico de Madrid");
				Team team2= new Team("FC Barcelona");
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date oneDate=null;
				try {
					oneDate = sdf.parse("2022-11-22"); //gertaera jada pasa zen
				} catch (ParseException e) {
					e.printStackTrace();
				}	
				//configure the state of the system (create object in the dabatase)
				testDA.open();
				ev = testDA.addEventWithQuestion("Europa League", oneDate, "How many penalties there will be?", 1, team1, team2);				
				Question q6= ev.getQuestions().get(0);
				testDA.setQuestionResult(q6, "2 penalties");
				testDA.addQuotetoQuestion(q6, 1.43, "Forecast 1 penalty");
				Quote k1=q6.getQuotes().get(0);
				boolean proba = testDA.questionExistitzenDa(q6);
				testDA.close();
				
				assertTrue(proba);
								
				//invoke System Under Test (sut)  
				boolean emaitza=sut.gertaeraEzabatu(ev);
								
				//verify
				assertNotNull(ev);
				assertTrue(emaitza);//emaitza true dela egiaztatu
				
				//ev is DB
				testDA.open();
				boolean dago=testDA.existsEvent(ev);
				boolean existq6 = testDA.questionExistitzenDa(q6);
				
				//aldatu doesQuestion exist eta doesKuote exist, 
				assertFalse(dago);			
				assertFalse(existq6);
				assertFalse(testDA.kuotaExistitzenDa(k1));
				testDA.close();
				
			   } catch (Exception e) {
				   System.out.println("ERROREA PROBA6");
				   fail();
				} finally {
					 // ez da ezer egin behar, dena ondo borratu delako        
			        }
		}
		
		
		
		@Test //kuotak apustuak dituzte, baino ez apustuAnitzak dituzten apustuak borratzean egoera != galduta
		public void proba7() {
			
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
				Transaction t = new Transaction(u, 100.0, oneDate, "sartu");
				sut.DiruaSartu(t);
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
				
				assertFalse(dago);			
				assertFalse(existq7);
				assertFalse(existKuote);
				assertFalse(existAp);
				assertTrue(existUser);//user ez da berez automatikoki borratu behar
				assertTrue(existTeam);//user ez da berez automatikoki borratu behar
				assertTrue(existTeam2);//user ez da berez automatikoki borratu behar
				assertFalse(existApA);//bide honetan ApusuAnitza borratu behar da
				
				
				
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
		
		
		@Test //kuotak apustuak dituzte. apustuAnitzak hainbat apustu eta apustuanitzaren egoera  irabazita
		public void proba8() {
			
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
				testDA.open();
				
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
				
				ev = testDA.addEventWithQuestionAndSport("Europa League", oneDate, "How many penalties there will be?", 1, team1, team2,sport);		
				
				Question q7= ev.getQuestions().get(0);
				testDA.setQuestionResult(q7, "2 penalties");
				testDA.addQuotetoQuestion(q7, 1.43, "Forecast 1 penalty");
				Quote k2=q7.getQuotes().get(0);
				testDA.close();
				
				
				
				Transaction t = new Transaction(u, 10.0, oneDate, "Diruasartu");
				sut.DiruaSartu(t);
								
				testDA.open();
				testDA.apustuaXaldiz(u, ev.getQuestions().get(0).getQuotes(), 4.0, 2,true);//bikoitza egin
				Apustua apustu=testDA.aurkituApustua(ev.getQuestions().get(0).getQuotes().get(0));
				ApustuAnitza apa=apustu.getApustuAnitza();
				testDA.aldatuEgoeraApustuari(apustu, "irabazita");
				testDA.close();
				
				testDA.open();
				boolean proba1=testDA.apustuaExistitzenDa(apustu);
				boolean proba2=testDA.apustuAnitzaExistitzenDa(apa);
				assertTrue(proba1);
				assertTrue(proba2);
				
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
				
				
				assertFalse(dago);			
				assertFalse(existq7);
				assertFalse(existKuote);
				assertFalse(existAp);
				assertFalse(existApA);
				
				assertTrue(existUser);//user ez da berez automatikoki borratu behar
				assertTrue(existTeam);//user ez da berez automatikoki borratu behar
				assertTrue(existTeam2);//user ez da berez automatikoki borratu behar
				
				
				
				testDA.close();
				
			   } catch (Exception e) {
				   System.out.println("Errorea proba 8");
				   e.printStackTrace();
				   fail();
				} finally {
					testDA.open();
					
					 boolean b5=testDA.ezabatuTransakzio(u);
					 boolean b0=testDA.removeUser(us);
					 boolean b1=testDA.removeTeam(t1);
					 boolean b2=testDA.removeTeam(t2);
					 boolean b4=testDA.removeSport(s);
					 
					 
					
					
					testDA.close();
					System.out.println("Finally "+b0+b1+b2+b4+b5);         
			        }
		}
		
		
		@Test //kuotak apustuak dituzte,  gainera apustuAnitzen egoera guztiak !=galduta eta !=irabazita, hau da, jokoan
		public void proba9() {
			
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
				testDA.open();
				
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
				
				ev = testDA.addEventWithQuestionAndSport("Europa League", oneDate, "How many penalties there will be?", 1, team1, team2,sport);		
				
				Question q7= ev.getQuestions().get(0);
				testDA.setQuestionResult(q7, "2 penalties");
				testDA.addQuotetoQuestion(q7, 1.43, "Forecast 1 penalty");
				Quote k2=q7.getQuotes().get(0);
				testDA.close();
				
				
				
				Transaction t = new Transaction(u, 10.0, oneDate, "Diruasartu");
				sut.DiruaSartu(t);
				sut.ApustuaEgin(u, ev.getQuestions().get(0).getQuotes(), 4.0, -1);
				testDA.open();
				Apustua apustu=testDA.aurkituApustua(ev.getQuestions().get(0).getQuotes().get(0));
				ApustuAnitza apa=apustu.getApustuAnitza();
				testDA.aldatuEgoeraApustuari(apustu, "jokoan");
				testDA.close();
				
				testDA.open();
				boolean proba1=testDA.apustuaExistitzenDa(apustu);
				boolean proba2=testDA.apustuAnitzaExistitzenDa(apa);
				assertTrue(proba1);
				assertTrue(proba2);
				
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
				
				
				assertFalse(dago);			
				assertFalse(existq7);
				assertFalse(existKuote);
				assertFalse(existAp);
				assertFalse(existApA);
				
				assertTrue(existUser);//user ez da berez automatikoki borratu behar
				assertTrue(existTeam);//user ez da berez automatikoki borratu behar
				assertTrue(existTeam2);//user ez da berez automatikoki borratu behar
				
				
				
				testDA.close();
				
			   } catch (Exception e) {
				   System.out.println("Errorea proba 8");
				   e.printStackTrace();
				   fail();
				} finally {
					testDA.open();
					
					 boolean b5=testDA.ezabatuTransakzio(u);
					 boolean b0=testDA.removeUser(us);
					 boolean b1=testDA.removeTeam(t1);
					 boolean b2=testDA.removeTeam(t2);
					 boolean b4=testDA.removeSport(s);
					 
					 
					
					
					testDA.close();
					System.out.println("Finally "+b0+b1+b2+b4+b5);         
			   }
		}
		
}

