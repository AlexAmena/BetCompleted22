package DefaultPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import businessLogic.BLFacadeImplementation;
import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import domain.Sport;
import domain.Team;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import test.BusinessLogic.TestFacadeImplementation;

public class GertaerakSortuINTTest {
	static BLFacadeImplementation sut;
	static TestFacadeImplementation testBL;

	private Event ev;

	@BeforeClass
	public static void setUpClass() {
		//sut= new BLFacadeImplementation();

		// you can parametrize the DataAccess used by BLFacadeImplementation
		//DataAccess da= new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));
		DataAccess da= new DataAccess();

		sut=new BLFacadeImplementation(da);

		testBL= new TestFacadeImplementation();
	}

	@Test
	//gertaera sortuko da
	public void test1() {
		String description=null;
		String sportName = null;
		String team1 = null;
		String team2 = null;
		Date d = null;
		Event ev = null;
		try {

			//define paramaters
			description="a-b";
			sportName="sport";
			team1 = "a";
			team2 = "b";
			d = new Date();
			d.setMonth(d.getMonth()+1);
			d.setHours(0);
			d.setMinutes(0);
			d.setSeconds(0);

			//configure the state of the system (create object in the dabatase)
			Sport s = testBL.addSport(sportName);
			Team t1 = testBL.addTeam(team1);
			Team t2 = testBL.addTeam(team2);

			//invoke System Under Test (sut)  
			boolean b = sut.gertaerakSortu(description, d, sportName);

			//egiaztatu emaitzak
			assertTrue(b);

			ev = testBL.findEventWithDescriptionAndDate(description, d, sportName);
			assertEquals(ev.getDescription(),description);
			assertEquals(ev.getEventDate(),d);
			assertEquals(ev.getSport().getIzena(),sportName);
			assertEquals(ev.getLokala().getIzena(),team1);
			assertEquals(ev.getKanpokoa().getIzena(),team2);
		}catch (Exception e) {
			e.printStackTrace();
			fail();
		}finally {
			//Remove the created objects in the database (cascade removing)   
			testBL.removeSport(sportName);
			boolean b=testBL.removeEvent(ev);
			testBL.removeTeam(team1);
			testBL.removeTeam(team2);
			System.out.println("Finally ");          
		}
	}

	@Test
	//Gertaera ez da sortuko, data horretan badago eta deskribapen hori duen beste gertaera bat
	public void test2() {
		//Parametroak
		String sportName = "sport";
		String sport2Name = "sport2";
		String team1 = "a";
		String team2 = "b";
		String description = "a-b";
		Date d = new Date();
		d.setMonth(d.getMonth()+1);
		d.setHours(0);
		d.setMinutes(0);
		d.setSeconds(0);
		Event ev1 = null;

		try {

			//configure the state of the system (create object in the dabatase)
			Sport s = testBL.addSport(sportName);
			Sport s2 = testBL.addSport(sport2Name);
			Team t1 = testBL.addTeam(team1);
			Team t2 = testBL.addTeam(team2);
			ev1 = testBL.addEvent(description, d, t1, t2, s);

			//invoke System Under Test (sut)  
			boolean b = sut.gertaerakSortu(description, d, sport2Name);


			//verify the results
			assertTrue(!b);
			
			//egiaztatu gertaera ez dela datu basera gehitu:
			Event event = testBL.findEventWithDescriptionAndDate(description, d, sport2Name);
			assertNull(event);

		} catch (Exception e) {
			// if the program goes to this point fail
			fail();
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//Remove the created objects in the database (cascade removing)   
			testBL.removeSport(sportName);
			testBL.removeSport(sport2Name);
			boolean b=testBL.removeEvent(ev1);
			testBL.removeTeam(team1);
			testBL.removeTeam(team2);
			System.out.println("Finally "+b);          
		}
	}
	@Test
	//Deskribapena null da, beraz gertaera ez da sortuko eta salbuespen bat jarti beharko luke
	public void test3() {
		//Parametroak
		String sportName = "sport";
		String description = null;
		Date d = new Date();
		d.setMonth(d.getMonth()+1);
		d.setHours(0);
		d.setMinutes(0);
		d.setSeconds(0);
		Event ev1 = null;

		try {

			//configure the state of the system (create object in the dabatase)
			Sport s = testBL.addSport(sportName);

			//invoke System Under Test (sut)  
			boolean b = sut.gertaerakSortu(description, d, sportName);


			//verify the results
			fail();
			
			//egiaztatu gertaera ez dela datu basera gehitu:
			

		} catch (Exception e) {
			e.printStackTrace();

			//Javaren defektuzko salbuespen bat jaurti da.
			//egiaztatu gertaera ez dagoela datu basean:
			ev1 = testBL.findEventWithDescriptionNull(d, sportName);
			assertNull(ev1);
			fail();
		} finally {//
			//Remove the created objects in the database (cascade removing)   
			testBL.removeSport(sportName);
			System.out.println("Finally ");          
		}
	}
	@Test
	//Deskribapenaren formatu ez zuzena
	public void test4() {
		//Parametroak
		String sportName = "sport";
		String description = "a b";
		Date d = new Date();
		d.setMonth(d.getMonth()+1);
		d.setHours(0);
		d.setMinutes(0);
		d.setSeconds(0);
		Event ev1 = null;

		try {

			//configure the state of the system (create object in the dabatase)
			Sport s = testBL.addSport(sportName);

			//invoke System Under Test (sut)  
			boolean b = sut.gertaerakSortu(description, d, sportName);

			//verify the results
			fail();
			
			//egiaztatu gertaera ez dela datu basera gehitu:
			

		} catch (Exception e) {
			e.printStackTrace();

			// if the program goes to this point fail
			//egiaztatu gertaera ez dagoela datu basean:
			ev1 = testBL.findEventWhenDateIsNull(description, sportName);
			assertNull(ev1);
			fail();	
		} finally {
			//Remove the created objects in the database (cascade removing)   
			testBL.removeSport(sportName);
			System.out.println("Finally ");          
		}
	}
	@Test
	//Data null izanik
	public void test5() {
		//Parametroak
		String sportName = "sport2";
		String description = "a-b";
		String team1 = "a";
		String team2 = "b";
		Date d = null;
		Event ev1 = null;

		try {

			//configure the state of the system (create object in the dabatase)
			Sport s = testBL.addSport(sportName);
			Team t1 = testBL.addTeam(team1);
			Team t2 = testBL.addTeam(team2);

			//invoke System Under Test (sut)  
			boolean b = sut.gertaerakSortu(description, d, sportName);


			//verify the results
			fail();
			
			//egiaztatu gertaera ez dela datu basera gehitu:
			

		} catch (Exception e) {
			e.printStackTrace();
			// if the program goes to this point fail
			assertTrue(true);
			//egiaztatu gertaera ez dagoela datu basean:
			ev1 = testBL.findEventWhenDateIsNull(description, sportName);
			assertNull(ev1);
			fail();
		} finally {
			//Remove the created objects in the database (cascade removing)   
			testBL.removeSport(sportName);
			testBL.removeTeam(team1);
			testBL.removeTeam(team2);
			System.out.println("Finally ");          
		}
	}
	@Test
	//^Pasata dagoen data batekin
	public void test6() {
		//Parametroak
		String sportName = "sport3";
		String description = "a-b";
		String team1 = "a";
		String team2 = "b";
		Date d = new Date();
		d.setMonth(d.getMonth()-1);
		d.setHours(0);
		d.setMinutes(0);
		d.setSeconds(0);
		Event ev1 = null;

		try {

			//configure the state of the system (create object in the dabatase)
			Sport s = testBL.addSport(sportName);
			Team t1 = testBL.addTeam(team1);
			Team t2 = testBL.addTeam(team2);

			//invoke System Under Test (sut)  
			boolean b = sut.gertaerakSortu(description, d, sportName);

			//verify the results
			fail();			
			
		} catch (EventFinished e) {
			//egiaztatu gertaera ez dagoela datu basean:
			ev1 = testBL.findEventWhenDateIsNull(description, sportName);
			assertNull(ev1);
		}catch(Exception e2) {
			e2.printStackTrace();
			fail();
		} finally {
			//Remove the created objects in the database (cascade removing)   
			testBL.removeSport(sportName);
			testBL.removeTeam(team1);
			testBL.removeTeam(team2);
			System.out.println("Finally ");          
		}
	}
	@Test
	//Kirol izena null denean
	public void test7() {
		//Parametroak
		String sportName = null;
		String description = "a-b";
		Date d = new Date();
		d.setMonth(d.getMonth()+1);
		d.setHours(0);
		d.setMinutes(0);
		d.setSeconds(0);
		Event ev1 = null;
		try {

			//configure the state of the system (create object in the dabatase)
			//invoke System Under Test (sut)  
			boolean b = sut.gertaerakSortu(description, d, sportName);
			//verify the results
			fail();			
			
		} catch (Exception e) {
			e.printStackTrace();
			//egiaztatu gertaera ez dagoela datu basean:
			ev1 = testBL.findEventWhenDateIsNull(description, sportName);
			assertNull(ev1);
			fail();
		} finally {
			//Remove the created objects in the database (cascade removing)   
			System.out.println("Finally ");          
		}
	}
	@Test
	//kirola DBan ez dagoenean
	public void test8() {
		//Parametroak
		String sportName = "sport";
		String description = "a-b";
		Date d = new Date();
		d.setMonth(d.getMonth()+1);
		d.setHours(0);
		d.setMinutes(0);
		d.setSeconds(0);
		Event ev1 = null;

		try {

			//invoke System Under Test (sut)  
			boolean b = sut.gertaerakSortu(description, d, sportName);

			assertTrue(!b);
			//verify the results
			//egiaztatu gertaera ez dagoela datu basean:
			ev1 = testBL.findEventWhenDateIsNull(description, sportName);
			assertNull(ev1);	
			
		} catch (Exception e) {
			fail();
		} finally {
			//Remove the created objects in the database (cascade removing)   
			System.out.println("Finally ");          
		}
	}
	@Test
	public void test1MB1() {
		String description=null;
		String sportName = null;
		String team1 = null;
		String team2 = null;
		Date d = null;
		Event ev = null;
		try {

			//define paramaters
			description="a-b";
			sportName="sport";
			team1 = "a";
			team2 = "b";
			d = new Date();
			d.setMonth(d.getMonth());
			d.setHours(d.getHours()-24);

			//configure the state of the system (create object in the dabatase)
			Sport s = testBL.addSport(sportName);
			Team t1 = testBL.addTeam(team1);
			Team t2 = testBL.addTeam(team2);

			//invoke System Under Test (sut)  
			boolean b = sut.gertaerakSortu(description, d, sportName);
			fail();
			//egiaztatu emaitzak
			
		}catch (Exception e) {
			//egiaztatu gertaera ez dagoela datu basean:
			ev = testBL.findEventWhenDateIsNull(description, sportName);
			assertNull(ev);	
		}finally {
			//Remove the created objects in the database (cascade removing)   
			testBL.removeSport(sportName);
			testBL.removeTeam(team1);
			testBL.removeTeam(team2);
			System.out.println("Finally ");          
		}
	}
	@Test
	public void test1MB2() {
		String description=null;
		String sportName = null;
		String team1 = null;
		String team2 = null;
		Date d = null;
		Event ev = null;
		try {

			//define paramaters
			description="a-b";
			sportName="sport";
			team1 = "a";
			team2 = "b";
			d = new Date();
			d.setMonth(d.getMonth());
			d.setHours(d.getHours());

			//configure the state of the system (create object in the dabatase)
			Sport s = testBL.addSport(sportName);
			Team t1 = testBL.addTeam(team1);
			Team t2 = testBL.addTeam(team2);

			//invoke System Under Test (sut)  
			boolean b = sut.gertaerakSortu(description, d, sportName);
			fail();
			
		}catch (Exception e) {
			//egiaztatu gertaera ez dagoela datu basean:
			ev = testBL.findEventWhenDateIsNull(description, sportName);
			assertNull(ev);	
		}finally {
			//Remove the created objects in the database (cascade removing)   
			testBL.removeSport(sportName);
			testBL.removeTeam(team1);
			testBL.removeTeam(team2);
			System.out.println("Finally ");          
		}
	}
	@Test
	public void test1MB3() {
		String description=null;
		String sportName = null;
		String team1 = null;
		String team2 = null;
		Date d = null;
		Event ev = null;
		try {

			//define paramaters
			description="a-b";
			sportName="sport";
			team1 = "a";
			team2 = "b";
			d = new Date();
			d.setMonth(d.getMonth());
			d.setHours(d.getHours()+24);
			System.out.println(d);
			//configure the state of the system (create object in the dabatase)
			Sport s = testBL.addSport(sportName);
			Team t1 = testBL.addTeam(team1);
			Team t2 = testBL.addTeam(team2);

			//invoke System Under Test (sut)  
			boolean b = sut.gertaerakSortu(description, d, sportName);
			//egiaztatu emaitzak
			assertTrue(b);
			
			//egiaztatu gertaera ez dagoela datu basean:
			ev = testBL.findEventWithDescriptionAndDate(description, d, sportName);
			assertEquals(ev.getDescription(),description);
			assertEquals(ev.getEventDate(),d);
			assertEquals(ev.getSport().getIzena(),sportName);
			assertEquals(ev.getLokala().getIzena(),team1);
			assertEquals(ev.getKanpokoa().getIzena(),team2);

			
		}catch (Exception e) {
			e.printStackTrace();
			fail();
		}finally {
			//Remove the created objects in the database (cascade removing)   
			testBL.removeSport(sportName);
			testBL.removeEvent(ev);
			testBL.removeTeam(team1);
			testBL.removeTeam(team2);
			System.out.println("Finally ");          
		}
	}
}