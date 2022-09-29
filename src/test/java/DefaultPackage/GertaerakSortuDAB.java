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

			//Deitu probatu nahi den metodoari
			testDA.open();
			event = testDA.findEventWithDescriptionAndDate(description1, d);
			testDA.close();


			//Egiaztatu dena ondo doala.
			assertTrue(emaitza);
			assertEquals(event.getDescription(),description1);
			/*assertEquals(event.getEventDate(), d);
			assertEquals(event.getSport().getIzena(), sportName);
			assertEquals(event.getLokala().getIzena(), l);
			assertEquals(event.getKanpokoa().getIzena(),team);		*/

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
			testDA.addEvent(description1, d, a, b, s);
			testDA.close();

			boolean emaitza = sut.gertaerakSortu(description1, d, sportName);

			//Deitu probatu nahi den metodoari
			testDA.open();
			event = testDA.findEventWithDescriptionAndDate("a-b", d);
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
			System.out.println("Bigarren probaren amaiera.\n");
		}
	}
	@Test
	public void test3() {
		System.out.println("Hirugarren probaren hasiera:\n");
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
			//Team c = testDA.addTeam(team);
			Sport s = testDA.addSport(sportName);
			testDA.close();

			//Deitu probatu nahi den metodoari

			String kirola = "Judo";
			boolean emaitza = sut.gertaerakSortu(description1, d, kirola);

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
			System.out.println("Hirugarren probaren amaiera.\n");
		}
	}
	@Test
	public void test4() {
		String errorea = "java.lang.NullPointerException";
		System.out.println("Laugarren probaren hasiera:\n");

		try {
			//Beharrezko datuak sartu
			description1 = null;
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
			//Team c = testDA.addTeam(team);
			Sport s = testDA.addSport(sportName);
			testDA.close();


			//Deitu probatu nahi den metodoari
			
			boolean emaitza = sut.gertaerakSortu(description1, d, sportName);

			//Egiaztatu dena ondo doala.
			assertTrue(!emaitza);	

		}catch(Exception e) {
			assertEquals(e.getClass().getName(),errorea);
		} finally {
			//Itzuli DB-a aurreko egoerara
			testDA.open();
			testDA.removeSport(sportName);
			testDA.removeTeam(l);
			testDA.removeTeam(k);
			testDA.close();
			System.out.println("Laugarren probaren amaiera.\n");

		}
	}
	@Test
	public void test5() {
		System.out.println("Bostgarren probaren hasiera:\n");
		String errorea = "java.lang.ArrayIndexOutOfBoundsException";
		try {
			//Beharrezko datuak sartu
			description1 = "a b";
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


			//Deitu probatu nahi den metodoari
			boolean emaitza = sut.gertaerakSortu(description1, d, sportName);
			
			//Egiaztatu dena ondo doala.
			assertTrue(!emaitza);	

		}catch(Exception e) {
			assertEquals(e.getClass().getName(),errorea);
		} finally {
			//Itzuli DB-a aurreko egoerara
			testDA.open();
			testDA.removeSport(sportName);
			testDA.removeTeam(l);
			testDA.removeTeam(k);
			testDA.close();
			System.out.println("Bostgarren probaren amaiera:\n");
		}
	}
	@Test
	public void test6() {
		System.out.println("Seigarren probaren hasiera:\n");
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

			//Egiaztatu dena ondo doala.
			assertTrue(!emaitza);	

		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			fail();
		} finally {
			//Itzuli DB-a aurreko egoerara
			testDA.open();
			testDA.removeSport(sportName);
			testDA.removeTeam(l);
			testDA.removeTeam(k);
			testDA.close();
			System.out.println("Seigarren probaren amaiera:\n");

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
			d.setMonth(d.getMonth()-1);
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

			//Deitu probatu nahi den metodoari
			try {
			boolean emaitza = sut.gertaerakSortu(description1, d, sportName);
	
			

			//Egiaztatu dena ondo doala.
			assertTrue(!emaitza);	

			}catch (java.lang.IllegalArgumentException e) {
				System.out.println(e.getMessage());
				assertEquals(e.getMessage(), errorea);
			}
		}catch(Exception e) {
			e.printStackTrace();
			assertEquals(e.getMessage(), errorea);
		} finally {
			//Itzuli DB-a aurreko egoerara
			testDA.open();
			testDA.removeTeam(l);
			testDA.removeTeam(k);
			testDA.removeSport(sportName);
			//remove the event
			testDA.close();
			System.out.println("7.probaren amaiera.\n");
		}
	}
	@Test
	public void test8() {
		System.out.println("8.probaren hasiera:\n");

		String errorea = "Unexpected null argument";
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
			sportName = null;

			//Sartu DB-ra
			testDA.open();
			Team a = testDA.addTeam(l);
			Team b = testDA.addTeam(k);
			testDA.close();

			//Deitu probatu nahi den metodoari
			try {
			boolean emaitza = sut.gertaerakSortu(description1, d, sportName);
	
			

			//Egiaztatu dena ondo doala.
			assertTrue(!emaitza);	

			}catch (java.lang.IllegalArgumentException e) {
				System.out.println(e.getMessage());
				assertEquals(e.getMessage(), errorea);
			}
		}catch(Exception e) {
			e.printStackTrace();
			assertEquals(e.getMessage(), errorea);
		} finally {
			//Itzuli DB-a aurreko egoerara
			testDA.open();
			testDA.removeTeam(l);
			testDA.removeTeam(k);
			testDA.close();
			System.out.println("8.probaren amaiera.\n");

		}
	}
}
