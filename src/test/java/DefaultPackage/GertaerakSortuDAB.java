package DefaultPackage;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Event;
import domain.Sport;
import domain.Team;
import test.DataAccess.TestDataAccess;

public class GertaerakSortuDAB {

	static DataAccess sut=new DataAccess();

	//additional operations needed to execute the test 
	static TestDataAccess testDA=new TestDataAccess();

	private Event event;
	private String description1;
	private Date d;
	private String l;
	private String k;
	private String team;
	private String sportName;

	@Test
	public void test1() {
		try {
			//Beharrezko datuak sartu
			description1 = "a-b";
			d = new Date();
			l = "a";
			k =  "b";
			team = "c";
			sportName = "sport";

			//Sartu DB-ra
			testDA.open();
			Team a = testDA.addTeam(l);
			Team b = testDA.addTeam(k);
			Team c = testDA.addTeam(team);
			Sport s = testDA.addSport(sportName);
			testDA.addEvent(description1, d, a, b, s);
			String description2 = "a-c";
			boolean emaitza = sut.gertaerakSortu(description2, d, sportName);

			//Deitu probatu nahi den metodoari
			event = testDA.findEventWithDescriptionAndDate("a-c", d);

			testDA.close();

			//Egiaztatu dena ondo doala.
			assertTrue(emaitza);
			assertEquals(event.getDescription(),description2);
			assertEquals(event.getEventDate(), d);
			assertEquals(event.getSport().getIzena(), sportName);
			assertEquals(event.getLokala().getIzena(), l);
			assertEquals(event.getKanpokoa().getIzena(),team);		

		}catch(Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			//Itzuli DB-a aurreko egoerara
			testDA.open();
			testDA.removeSport(sportName);
			testDA.removeTeam(l);
			testDA.removeTeam(k);
			testDA.removeTeam(team);
			//remove the event
			testDA.removeEvent(event);
			event = testDA.findEventWithDescriptionAndDate(description1, d);
			testDA.removeEvent(event);
			testDA.close();
		}
	}
	@Test
	public void test2() {
		try {
			//Beharrezko datuak sartu
			System.out.println("Bigarren proba kasua:");
			description1 = "a-b";
			d = new Date();
			l = "a";
			k =  "b";
			sportName = "sport";

			//Sartu DB-ra
			testDA.open();
			Team a = testDA.addTeam(l);
			Team b = testDA.addTeam(k);
			Sport s = testDA.addSport(sportName);
			testDA.addEvent(description1, d, a, b, s);

			boolean emaitza = sut.gertaerakSortu(description1, d, sportName);

			//Deitu probatu nahi den metodoari
			event = testDA.findEventWithDescriptionAndDate("a-b", d);
			System.out.println("Lehenengoa ondo sartu du.");
			assertTrue(!emaitza);	

			testDA.close();

			//Egiaztatu dena ondo doala.
			assertTrue(!emaitza);	

		}catch(Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			//Itzuli DB-a aurreko egoerara
			testDA.open();
			testDA.removeSport(sportName);
			testDA.removeTeam(l);
			testDA.removeTeam(k);
			//remove the event
			testDA.removeEvent(event);
			testDA.close();
		}
	}
	@Test
	public void test3() {
		try {
			//Beharrezko datuak sartu
			description1 = "a-b";
			d = new Date();
			l = "a";
			k =  "b";
			sportName = "sport";

			//Sartu DB-ra
			testDA.open();
			Team a = testDA.addTeam(l);
			Team b = testDA.addTeam(k);
			//Team c = testDA.addTeam(team);
			Sport s = testDA.addSport(sportName);

			//Deitu probatu nahi den metodoari

			String kirola = "Judo";
			boolean emaitza = sut.gertaerakSortu(description1, d, kirola);

			assertTrue(!emaitza);	

			testDA.close();

			//Egiaztatu dena ondo doala.
			assertTrue(!emaitza);	

		}catch(Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			//Itzuli DB-a aurreko egoerara
			testDA.open();
			testDA.removeSport(sportName);
			testDA.removeTeam(l);
			testDA.removeTeam(k);
			//testDA.removeTeam(team);
			//remove the event
			testDA.close();
		}
	}
	@Test
	public void test4() {
		try {
			//Beharrezko datuak sartu
			description1 = null;
			d = new Date();
			l = "a";
			k =  "b";
			sportName = "sport";

			//Sartu DB-ra
			testDA.open();
			Team a = testDA.addTeam(l);
			Team b = testDA.addTeam(k);
			//Team c = testDA.addTeam(team);
			Sport s = testDA.addSport(sportName);
			testDA.close();


			//Deitu probatu nahi den metodoari
			
			boolean emaitza = sut.gertaerakSortu(description1, d, sportName);

			assertTrue(!emaitza);	


			//Egiaztatu dena ondo doala.
			assertTrue(!emaitza);	

		}catch(Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			//Itzuli DB-a aurreko egoerara
			testDA.open();
			testDA.removeSport(sportName);
			testDA.removeTeam(l);
			testDA.removeTeam(k);
			//testDA.removeTeam(team);
			//remove the event
			testDA.close();
		}
	}
	@Test
	public void test5() {
		try {
			//Beharrezko datuak sartu
			description1 = "a b";
			d = new Date();
			l = "a";
			k =  "b";
			sportName = "sport";

			//Sartu DB-ra
			testDA.open();
			Team a = testDA.addTeam(l);
			Team b = testDA.addTeam(k);
			Sport s = testDA.addSport(sportName);
			testDA.close();


			//Deitu probatu nahi den metodoari
			
			boolean emaitza = sut.gertaerakSortu(description1, d, sportName);

			assertTrue(!emaitza);	


			//Egiaztatu dena ondo doala.
			assertTrue(!emaitza);	

		}catch(Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			//Itzuli DB-a aurreko egoerara
			testDA.open();
			testDA.removeSport(sportName);
			testDA.removeTeam(l);
			testDA.removeTeam(k);
			//remove the event
			testDA.close();
		}
	}
	@Test
	public void test6() {
		try {
			//Beharrezko datuak sartu
			description1 = "a-b";
			d = null;
			l = "a";
			k =  "b";
			sportName = "sport";

			//Sartu DB-ra
			testDA.open();
			Team a = testDA.addTeam(l);
			Team b = testDA.addTeam(k);
			Sport s = testDA.addSport(sportName);
			testDA.close();


			//Deitu probatu nahi den metodoari
			
			boolean emaitza = sut.gertaerakSortu(description1, d, sportName);

			assertTrue(!emaitza);	


			//Egiaztatu dena ondo doala.
			assertTrue(!emaitza);	

		}catch(Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			//Itzuli DB-a aurreko egoerara
			testDA.open();
			testDA.removeSport(sportName);
			testDA.removeTeam(l);
			testDA.removeTeam(k);
			//remove the event
			testDA.close();
		}
	}
	@Test
	public void test7() {
		try {
			//Beharrezko datuak sartu
			description1 = "a-b";
			d = new Date();
			l = "a";
			k =  "b";
			sportName = null;

			//Sartu DB-ra
			testDA.open();
			Team a = testDA.addTeam(l);
			Team b = testDA.addTeam(k);
			testDA.close();


			//Deitu probatu nahi den metodoari
			
			boolean emaitza = sut.gertaerakSortu(description1, d, sportName);

			assertTrue(!emaitza);	


			//Egiaztatu dena ondo doala.
			assertTrue(!emaitza);	

		}catch(Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			//Itzuli DB-a aurreko egoerara
			testDA.open();
			testDA.removeTeam(l);
			testDA.removeTeam(k);
			//remove the event
			testDA.close();
		}
	}
}
