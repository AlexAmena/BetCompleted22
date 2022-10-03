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
		System.out.println("\nLehenengo probaren hasiera:\n");
		try {
			//beharrezko aldagaiak
			description1 = "a-b";
			d = new Date();
			d.setMonth(d.getMonth()+1);
			d.setHours(0);
			d.setMinutes(0);
			d.setSeconds(0);
			
			sportName = "aa";
			
			//deitu metodoari
			boolean emaitza = sut.gertaerakSortu(description1, d, sportName);
			
			//egiaztatu emaitza
			assertTrue(!emaitza);
		}catch(Exception e) {
			e.printStackTrace();
			fail();
		}finally {
			System.out.println("Lehenengo probaren amaiera:\n");
		}

	}
	@Test
	public void test3() {
		System.out.println("\nBigarren probaren hasiera:\n");
		try {
			//Beharrezko datuak sartu DB-an
			description1 = "a-b";
			d = new Date();
			d.setMonth(d.getMonth()+1);
			d.setHours(0);
			d.setMinutes(0);
			d.setSeconds(0);
			l = "a";
			k =  "b";
			sportName = "sport";
			
			testDA.open();
			Team a = testDA.addTeam(l);
			Team b = testDA.addTeam(k);
			Sport s =testDA.addSport(sportName);
			testDA.close();

			//Deitu probatu nahi den metodoari

			boolean emaitza = sut.gertaerakSortu(description1, d, sportName);

			testDA.open();
			event = testDA.findEventWithDescriptionAndDate("a-b", d);
			testDA.close();
			assertTrue(emaitza);
			/*assertEquals(event.getDescription(),description1);
			assertEquals(event.getEventDate(), d);
			assertEquals(event.getSport().getIzena(), sportName);
			assertEquals(event.getLokala().getIzena(), l);
			assertEquals(event.getKanpokoa().getIzena(),k);	*/
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
			System.out.println("Bigarren probaren amaiera:\n");
		}
	}
	@Test
	public void test4() {
		System.out.println("\n4. probaren hasiera:\n");
		try {
			//Beharrezko datuak sartu DB-an
			description1 = "a-b";
			d = new Date();
			d.setMonth(d.getMonth()+1);
			d.setHours(0);
			d.setMinutes(0);
			d.setSeconds(0);
			l = "a";
			k =  "b";
			sportName = "sport";

			testDA.open();

			Team a = testDA.addTeam(l);
			Team b = testDA.addTeam(k);
			Sport s =testDA.addSport(sportName);

			ev1 = testDA.addEvent(description1, d, a, b, s);
			testDA.close();
			
			//Deitu probatu nahi den metodoari
			String description2 = "b-a";
			boolean emaitza = sut.gertaerakSortu(description2, d, sportName);
			
			testDA.open();
			event = testDA.findEventWithDescriptionAndDate("b-a", d);
			testDA.close();
			
			assertTrue(emaitza);
			/*assertEquals(event.getDescription(),description2);
			assertEquals(event.getEventDate(), d);
			assertEquals(event.getSport().getIzena(), sportName);
			assertEquals(event.getLokala().getIzena(), k);
			assertEquals(event.getKanpokoa().getIzena(),l);	*/

		}catch(Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			//Itzuli DB-a aurreko egoerara
			testDA.open();
			testDA.removeSport(sportName);
			testDA.removeTeam(l);
			testDA.removeTeam(k);
			testDA.removeEvent(ev1);
			testDA.removeEvent(event);
			//remove the event
			testDA.close();
			System.out.println("\n4.probaren amaiera.");
		}
	}
	@Test
	public void test5() {
		System.out.println("\n5.probaren hasiera:\n");
		try {
			//Beharrezko datuak sartu DB-an
			description1 = "a-b";
			d = new Date();
			d.setMonth(d.getMonth()+1);
			d.setHours(0);
			d.setMinutes(0);
			d.setSeconds(0);
			l = "a";
			k =  "b";
			sportName = "sport";

			testDA.open();

			Team a = testDA.addTeam(l);
			Team b = testDA.addTeam(k);
			Sport s =testDA.addSport(sportName);

			ev1 = testDA.addEvent(description1, d, a, b, s);
			testDA.close();
			
			//Deitu probatu nahi den metodoari

			boolean emaitza = sut.gertaerakSortu(description1, d, sportName);
			
			testDA.open();
			event = testDA.findEventWithDescriptionAndDate("a-b", d);
			testDA.close();
			
			assertTrue(!emaitza);
			/*assertEquals(event.getDescription(),description1);
			assertEquals(event.getEventDate(), d);
			assertEquals(event.getSport().getIzena(), sportName);
			assertEquals(event.getLokala().getIzena(), l);
			assertEquals(event.getKanpokoa().getIzena(),k);	*/

		}catch(Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			//Itzuli DB-a aurreko egoerara
			testDA.open();
			testDA.removeSport(sportName);
			testDA.removeTeam(l);
			testDA.removeTeam(k);
			testDA.removeEvent(ev1);
			testDA.close();
			System.out.println("\n5.probaren amaiera.");
		}
	}
}
