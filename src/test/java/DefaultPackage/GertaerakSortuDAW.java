package DefaultPackage;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Event;
import domain.Sport;
import domain.Team;
import test.DataAccess.TestDataAccess;

public class GertaerakSortuDAW {

	static DataAccess sut=new DataAccess();

	//additional operations needed to execute the test 
	static TestDataAccess testDA=new TestDataAccess();
	private Event ev1;
	private Event event;
	private String description1;
	private Date d;
	private String l;
	private String k;
	private String team;
	private String sportName;

	@Test
	public void test1() {

		description1 = "a-b";
		d = new Date();
		sportName = "aa";
		boolean emaitza = sut.gertaerakSortu(description1, d, sportName);
		assertTrue(!emaitza);
	}
	@Test
	public void test3() {

		try {
			//Beharrezko datuak sartu DB-an
			description1 = "a-b";
			d = new Date();
			l = "a";
			k =  "b";
			sportName = "sport";
			
			testDA.open();
			
			Team a = testDA.addTeam(l);
			Team b = testDA.addTeam(k);
			Sport s =testDA.addSport(sportName);
			


			//Deitu probatu nahi den metodoari
			
			boolean emaitza = sut.gertaerakSortu(description1, d, sportName);
			event = testDA.findEventWithDescriptionAndDate("a-b", d);
			
			assertTrue(emaitza);
			assertEquals(event.getDescription(),description1);
			assertEquals(event.getEventDate(), d);
			assertEquals(event.getSport().getIzena(), sportName);
			assertEquals(event.getLokala().getIzena(), l);
			assertEquals(event.getKanpokoa().getIzena(),k);	
			testDA.close();

		}catch(Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			//Itzuli DB-a aurreko egoerara
			testDA.open();
			testDA.removeSport(sportName);
			testDA.removeTeam(l);
			testDA.removeTeam(k);
			testDA.removeEvent(event);
			//remove the event
			testDA.close();
		}
	}
	@Test
	public void test4() {

		try {
			//Beharrezko datuak sartu DB-an
			description1 = "a-b";
			d = new Date();
			l = "a";
			k =  "b";
			sportName = "sport";
			
			testDA.open();
			
			Team a = testDA.addTeam(l);
			Team b = testDA.addTeam(k);
			Sport s =testDA.addSport(sportName);
			
			ev1 = testDA.addEvent(description1, d, a, b, s);

			//Deitu probatu nahi den metodoari
			
			String description2 = "b-a";
			boolean emaitza = sut.gertaerakSortu(description2, d, sportName);
			event = testDA.findEventWithDescriptionAndDate("b-a", d);
			
			assertTrue(emaitza);
			assertEquals(event.getDescription(),description2);
			assertEquals(event.getEventDate(), d);
			assertEquals(event.getSport().getIzena(), sportName);
			assertEquals(event.getLokala().getIzena(), k);
			assertEquals(event.getKanpokoa().getIzena(),l);	

		}catch(Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			//Itzuli DB-a aurreko egoerara
			testDA.open();
			testDA.removeSport(sportName);
			testDA.removeTeam(l);
			testDA.removeTeam(k);
			testDA.removeEvent(event);
			testDA.removeEvent(ev1);
			//remove the event
			testDA.close();
		}
	}
	@Test
	public void test5() {

		try {
			//Beharrezko datuak sartu DB-an
			description1 = "a-b";
			d = new Date();
			l = "a";
			k =  "b";
			sportName = "sport";
			
			testDA.open();
			
			Team a = testDA.addTeam(l);
			Team b = testDA.addTeam(k);
			Sport s =testDA.addSport(sportName);
			
			ev1 = testDA.addEvent(description1, d, a, b, s);

			//Deitu probatu nahi den metodoari
			
			boolean emaitza = sut.gertaerakSortu(description1, d, sportName);
			event = testDA.findEventWithDescriptionAndDate("a-b", d);
			
			assertTrue(!emaitza);
			assertEquals(event.getDescription(),description1);
			assertEquals(event.getEventDate(), d);
			assertEquals(event.getSport().getIzena(), sportName);
			assertEquals(event.getLokala().getIzena(), l);
			assertEquals(event.getKanpokoa().getIzena(),k);	

		}catch(Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			//Itzuli DB-a aurreko egoerara
			testDA.open();
			testDA.removeSport(sportName);
			testDA.removeTeam(l);
			testDA.removeTeam(k);
			testDA.removeEvent(event);
			testDA.removeEvent(ev1);
			//remove the event
			testDA.close();
		}
	}
}
