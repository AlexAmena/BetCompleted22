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

	/*
	 * Pasatako kirola ez dago DBan. Esperotako emaitza false da eta gertaera ez sortzea DBan.
	 */
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
			
			testDA.open();
			event = testDA.findEventWithDescriptionAndDate(description1, d, sportName);
			testDA.close();
			assertNull(event);
			
		}catch(Exception e) {
			e.printStackTrace();
			fail();
		}finally {
			System.out.println("Lehenengo probaren amaiera:\n");
		}

	}
	/*
	 * Bigarren bidea ez da probatu, ezinezko bidea baita.
	 */
	
	/*
	 * Gertaera berria sortuko da DBan.
	 */
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

		
			assertTrue(emaitza);
			
			//Egiaztatu gertaera DB-an dagoela.
			testDA.open();
			event = testDA.findEventWithDescriptionAndDate("a-b", d, sportName);
			testDA.close();
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
			testDA.close();
			System.out.println("Bigarren probaren amaiera:\n");
		}
	}
	/*
	 * DBan gertaera berria sortuko da.
	 */
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
			
			//Deitu probatu nahi den metodoari
			String description2 = "b-a";
			String sport2Name = "sport2";
			Sport s2 = testDA.addSport(sport2Name);
			testDA.close();

			boolean emaitza = sut.gertaerakSortu(description2, d, sport2Name);
			
			assertTrue(emaitza);
			
			//Egiaztatu gertaera gehitu dela DB-ra
			testDA.open();
			event = testDA.findEventWithDescriptionAndDate("b-a", d, sport2Name);
			testDA.close();
			assertEquals(event.getDescription(),description2);
			assertEquals(event.getEventDate(), d);
			assertEquals(event.getSport().getIzena(), "sport2");
			assertEquals(event.getLokala().getIzena(), k);
			assertEquals(event.getKanpokoa().getIzena(),l);	

		}catch(Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			//Itzuli DB-a aurreko egoerara
			testDA.open();
			testDA.removeSport(sportName);
			testDA.removeSport("sport2");
			testDA.removeTeam(l);
			testDA.removeTeam(k);
			testDA.removeEvent(ev1);
			testDA.removeEvent(event);
			testDA.close();
			System.out.println("\n4.probaren amaiera.");
		}
	}
	/*
	 * Data eta deskribapen hori dituen gertaera jadanik DBan dago, beraz ez du bigarren gertaera sortuko.
	 */
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
			Sport s2 = testDA.addSport("sport2");
			ev1 = testDA.addEvent(description1, d, a, b, s);
			testDA.close();
			
			//Deitu probatu nahi den metodoari

			boolean emaitza = sut.gertaerakSortu(description1, d, "sport2");
			
			assertTrue(!emaitza);
			
			//Egiaztatu DBra ez dela gertaera gehitu, hau da, DB-an dagoen kirola "sport" kirola duena dela, ez "sport2" duena.
			testDA.open();
			event = testDA.findEventWithDescriptionAndDate("a-b", d, "sport2");
			testDA.close();
			assertNull(event);

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
			testDA.removeSport("sport2");
			testDA.close();
			System.out.println("\n5.probaren amaiera.");
		}
	}
}
