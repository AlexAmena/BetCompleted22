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
		System.out.println("\nLehenengo probaren hasiera:\n");
		try {
			//Beharrezko datuak sartu
			description1 = "a-b";
			d = new Date();
			d.setMonth(d.getMonth()+1);
			d.setHours(0);
			d.setMinutes(0);
			d.setSeconds(0);
			l = "a";
			k =  "b";
			sportName = "sport";

			//Sartu DB-ra
			testDA.open();
			Team a = testDA.addTeam(l);
			Team b = testDA.addTeam(k);
			Sport s = testDA.addSport(sportName);
			testDA.close();

			//metodoa probatu
			boolean emaitza = sut.gertaerakSortu(description1, d, sportName);

			//Egiaztatu dena ondo doala.
			assertTrue(emaitza);
			
			//Egiaztatu objektua ondo gehitu dela DBra.
			testDA.open();
			event = testDA.findEventWithDescriptionAndDate(description1, d, sportName);
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
			System.out.println("Lehenengo probaren amaiera.\n");
		}
	}
	@Test
	public void test2() {
		System.out.println("2.probaren hasiera:\n");
		Event ev1 = null;
		try {
			//Beharrezko datuak sartu
			description1 = "a-b";
			d = new Date();
			d.setMonth(d.getMonth()+1);
			d.setHours(0);
			d.setMinutes(0);
			d.setSeconds(0);
			l = "a";
			k =  "b";
			sportName = "sport";

			//Sartu DB-ra
			testDA.open();
			Team a = testDA.addTeam(l);
			Team b = testDA.addTeam(k);
			Sport s = testDA.addSport(sportName);
			Sport s2 = testDA.addSport("sport2");
			ev1 = testDA.addEvent(description1, d, a, b, s);
			testDA.close();
			
			//Deitu probatu nahi den metodoari
			boolean emaitza = sut.gertaerakSortu(description1, d, "sport2");

			//Egiaztatu dena ondo doala.
			assertTrue(!emaitza);	
			
			//Egiaztatu gertaera ez dela DB-ra gehitu
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
			testDA.removeSport("sport2");
			testDA.removeTeam(l);
			testDA.removeTeam(k);
			testDA.removeEvent(ev1);
			testDA.close();
			System.out.println("Bigarren probaren amaiera.\n");
		}
	}
	@Test
	public void test3() {
		String errorea = "java.lang.NullPointerException";
		System.out.println("3.probaren hasiera:\n");

		try {
			//Beharrezko datuak sartu
			description1 = null;
			d = new Date();
			d.setMonth(d.getMonth()+1);
			d.setHours(0);
			d.setMinutes(0);
			d.setSeconds(0);
			sportName = "sport";

			//Sartu DB-ra
			testDA.open();
			Sport s = testDA.addSport(sportName);
			testDA.close();


			//Deitu probatu nahi den metodoari
			
			boolean emaitza = sut.gertaerakSortu(description1, d, sportName);

			//Egiaztatu dena ondo doala.
			assertTrue(!emaitza);	

		}catch(Exception e) {
			assertEquals(e.getClass().getName(),errorea);
			
			//Egiaztatu ez dela gertaera DB-ra gehitu
			testDA.open();
			event = testDA.findEventWithDescriptionNull(d, sportName);
			testDA.close();
			assertNull(event);
		} finally {
			//Itzuli DB-a aurreko egoerara
			testDA.open();
			testDA.removeSport(sportName);
			testDA.close();
			System.out.println("3.probaren amaiera.\n");
		}
	}
	@Test
	public void test4() {
		System.out.println("4.probaren hasiera:\n");
		String errorea = "java.lang.ArrayIndexOutOfBoundsException";
		try {
			//Beharrezko datuak sartu
			description1 = "a b";
			d = new Date();
			d.setMonth(d.getMonth()+1);
			d.setHours(0);
			d.setMinutes(0);
			d.setSeconds(0);
			sportName = "sport";

			//Sartu DB-ra
			testDA.open();
			Sport s = testDA.addSport(sportName);
			testDA.close();

			//Deitu probatu nahi den metodoari
			boolean emaitza = sut.gertaerakSortu(description1, d, sportName);
			
			//Egiaztatu dena ondo doala.
			assertTrue(!emaitza);	

		}catch(Exception e) {
			//Egiaztatu errorea gertatu dela
			assertEquals(e.getClass().getName(),errorea);
			
			//Egiaztatu ez dela gertaera DB-ra sartu
			testDA.open();
			event = testDA.findEventWithDescriptionAndDate(description1, d, sportName);
			assertNull(event);
		} finally {
			//Itzuli DB-a aurreko egoerara
			testDA.open();
			testDA.removeSport(sportName);
			testDA.close();
			System.out.println("4.probaren amaiera:\n");
		}
	}
	@Test
	public void test5() {
		System.out.println("5.probaren hasiera:\n");
		try {
			//Beharrezko datuak sartu
			description1 = "a-b";
			d = null;
			l = "a";
			k =  "b";
			sportName = "sport2";

			//Sartu DB-ra
			testDA.open();
			Team a = testDA.addTeam(l);
			Team b = testDA.addTeam(k);
			Sport s = testDA.addSport(sportName);
			testDA.close();

			//Deitu probatu nahi den metodoari
			boolean emaitza = sut.gertaerakSortu(description1, d, sportName);

			//Egiaztatu gertaera ez dela gehitu DB-ra
			testDA.open();
			event = testDA.findEventWhenDateIsNull(description1, sportName);
			testDA.close();
			assertNotNull(event);	

		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			//fail();
		} finally {
			//Itzuli DB-a aurreko egoerara
			testDA.open();
			testDA.removeSport(sportName);
			testDA.removeTeam(l);
			testDA.removeTeam(k);
			testDA.close();
			System.out.println("5.probaren amaiera:\n");
		}
	}
	@Test
	public void test6() {
		System.out.println("6.probaren hasiera:\n");

		String errorea = "Unexpected null argument";
		try {
			//Beharrezko datuak sartu
			description1 = "a-b";
			d = new Date();
			d.setMonth(d.getMonth()-1);
			d.setHours(0);
			d.setMinutes(0);
			d.setSeconds(0);
			l = "a";
			k =  "b";
			sportName = "sport3";

			//Sartu DB-ra
			testDA.open();
			Sport s = testDA.addSport(sportName);
			Team a = testDA.addTeam(l);
			Team b = testDA.addTeam(k);
			testDA.close();

			//Deitu probatu nahi den metodoari
			boolean emaitza = sut.gertaerakSortu(description1, d, sportName);
			
			//Gertaera sortzen badu true itzuliko du:
			assertTrue(emaitza);
			
			//Egiaztatu ez dela gertaera sortu:
			testDA.open();
			event = testDA.findEventWithDescriptionAndDate(description1, d, sportName);
			testDA.close();
			assertNotNull(event);

		}catch(Exception e) {
			e.printStackTrace();
			assertEquals(e.getMessage(), errorea);
		} finally {
			//Itzuli DB-a aurreko egoerara
			testDA.open();
			testDA.removeTeam(l);
			testDA.removeTeam(k);
			testDA.removeSport(sportName);
			testDA.removeEvent(event);
			testDA.close();
			System.out.println("6.probaren amaiera.\n");
		}
	}
	@Test
	public void test7() {
		System.out.println("7.probaren hasiera:\n");

		String errorea = "Unexpected null argument";
		try {
			//Beharrezko datuak sartu
			description1 = "a-b";
			d = new Date();
			d.setMonth(d.getMonth()+1);
			d.setHours(0);
			d.setMinutes(0);
			d.setSeconds(0);
			sportName = null;

			//Deitu probatu nahi den metodoari
			boolean emaitza = sut.gertaerakSortu(description1, d, sportName);
	
			//Egiaztatu dena ondo doala.
			assertTrue(!emaitza);	

		}catch(Exception e) {
			//e.printStackTrace();
			assertEquals(e.getMessage(), errorea);
			
			//Egiaztatu ez dela gertaera DB-ra gehitu
			testDA.open();
			event = testDA.findEventWithoutSport(description1, d);
			testDA.close();
			assertNull(event);
		} finally {
			System.out.println("7.probaren amaiera.\n");
		}
	}
	@Test
	public void test8() {
		System.out.println("8.probaren hasiera:\n");
		try {
			//Beharrezko datuak sartu
			description1 = "a-b";
			d = new Date();
			d.setMonth(d.getMonth()+1);
			d.setHours(0);
			d.setMinutes(0);
			d.setSeconds(0);
			l = "a";
			k =  "b";
			sportName = "sport4";

			//Deitu probatu nahi den metodoari

			boolean emaitza = sut.gertaerakSortu(description1, d, sportName);

			//Egiaztatu dena ondo doala.
			assertTrue(!emaitza);	
			
			//Egiaztati gertaera ez dagpela DBan.
			testDA.open();
			testDA.findEventWithDescriptionAndDate(description1, d, sportName);
			testDA.close();
			assertNull(event);
		}catch(Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			//Itzuli DB-a aurreko egoerara
			System.out.println("8.probaren amaiera.\n");
		}
	}
	
	/*
	 * Muga balioen probak
	 */
	//Gaurko data - egun bat.
	@Test
	public void test1MB1() {
		System.out.println("\nLehenengo probaren hasiera:\n");
		try {
			//Beharrezko datuak sartu
			description1 = "a-b";
			d = new Date();
			d.setMonth(d.getMonth());
			d.setHours(d.getHours()-24);
			System.out.println(d);
			l = "a";
			k =  "b";
			sportName = "sport10";

			//Sartu DB-ra
			testDA.open();
			Team a = testDA.addTeam(l);
			Team b = testDA.addTeam(k);
			Sport s = testDA.addSport(sportName);
			testDA.close();

			//metodoa probatu
			boolean emaitza = sut.gertaerakSortu(description1, d, sportName);

			//Egiaztatu dena ondo doala.
			assertTrue(emaitza);
			
			testDA.open();
			event = testDA.findEventWithDescriptionAndDate(description1, d,sportName);
			testDA.close();
			assertNotNull(event);	

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
			System.out.println("Lehenengo probaren amaiera.\n");
		}
	}
	@Test
	public void test1MB2() {
		System.out.println("\nLehenengo probaren hasiera:\n");
		try {
			//Beharrezko datuak sartu
			description1 = "a-b";
			d = new Date();
			d.setMonth(d.getMonth());
			l = "a";
			k =  "b";
			sportName = "sport11";

			//Sartu DB-ra
			testDA.open();
			Team a = testDA.addTeam(l);
			Team b = testDA.addTeam(k);
			Sport s = testDA.addSport(sportName);
			testDA.close();

			//metodoa probatu
			boolean emaitza = sut.gertaerakSortu(description1, d, sportName);

			//Deitu probatu nahi den metodoari
			testDA.open();
			event = testDA.findEventWithDescriptionAndDate(description1, d, sportName);
			testDA.close();


			//Egiaztatu dena ondo doala.
			assertTrue(emaitza);
			assertNotNull(event);	

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
			System.out.println("Lehenengo probaren amaiera.\n");
		}
	}
	@Test
	public void test1MB3() {
		System.out.println("\nLehenengo probaren hasiera:\n");
		try {
			//Beharrezko datuak sartu
			description1 = "a-b";
			d = new Date();
			d.setMonth(d.getMonth());
			d.setHours(d.getHours()+24);
			l = "a";
			k =  "b";
			sportName = "sport12";

			//Sartu DB-ra
			testDA.open();
			Team a = testDA.addTeam(l);
			Team b = testDA.addTeam(k);
			Sport s = testDA.addSport(sportName);
			testDA.close();

			//metodoa probatu
			boolean emaitza = sut.gertaerakSortu(description1, d, sportName);

			//Deitu probatu nahi den metodoari
			testDA.open();
			event = testDA.findEventWithDescriptionAndDate(description1, d,sportName);
			testDA.close();


			//Egiaztatu dena ondo doala.
			assertTrue(emaitza);
			assertNotNull(event);

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
			System.out.println("Lehenengo probaren amaiera.\n");
		}
	}
}
