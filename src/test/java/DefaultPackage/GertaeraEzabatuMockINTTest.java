package DefaultPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.runners.MockitoJUnitRunner;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.ApustuAnitza;
import domain.Apustua;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Registered;
import domain.Team;
import domain.User;

@RunWith(MockitoJUnitRunner.class)
public class GertaeraEzabatuMockINTTest {
	@Mock
	DataAccess dataAccess;
	
	
	@InjectMocks
	 BLFacadeImplementation sut;
	
	@Test
	public void test1() {//Event not in db
		Team t1=new Team("RSO");
		Team t2=new Team("ATL");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate=null;
		try {
			oneDate = sdf.parse("12/12/2022");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			
		
		try {boolean emaitza=true;
			Event ev=new Event("Finala",oneDate,t1,t2);
			Mockito.doReturn(false).when(dataAccess).gertaeraEzabatu(ev);	 //Berez metodoak exception jaurti beharko luke, baino bakarrik signatura ikusiz false bueltatzen du
			emaitza=(sut.gertaeraEzabatu(ev)); 
			assertFalse(emaitza);	//GAIZKI  metodoak ez du ezer bueltatzen, programak errorea jaurtitzen du eta tratatu gabe dago
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROREA TEST1");
			System.out.println(e.getMessage());
			fail();
		}
			
	}
	
	@Test
	public void test2() {//Event not in db
		Team t1=new Team("RSO");
		Team t2=new Team("ATL");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate=null;
		try {
			oneDate = sdf.parse("12/12/2022");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			
		
		try {
			Event ev=new Event("Finala",oneDate,t1,t2);
			ev.addQuestion("Who wins", 2);
			//Mockito prestatu
			ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
			Mockito.doReturn(false).when(dataAccess).gertaeraEzabatu(ev);	
			
			//deitu sut
			boolean emaitza=sut.gertaeraEzabatu(ev); 
			//konprobatu
			assertFalse(emaitza);	
			
			//verify arguments
			Mockito.verify(dataAccess,Mockito.times(1)).gertaeraEzabatu(eventCaptor.capture());
			assertEquals(ev,eventCaptor.getValue());
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROREA TEST2");
			System.out.println(e.getMessage());
			fail();
		}
			
	}
	
	@Test
	public void test3() {
		Registered u=new Registered("iosu","123",11);
		Team t1=new Team("RSO");
		Team t2=new Team("ATL");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate=null;
		try {
			oneDate = sdf.parse("12/12/2022");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			
		
		try {
			Event ev=new Event("Finala",oneDate,t1,t2);
			Question quest=ev.addQuestion("Who wins", 2);
			ev.getQuestions().get(0).setResult("Realak");
			Quote kuota=quest.addQuote(2.5,"realak irabazi", quest);
			ApustuAnitza apA=new ApustuAnitza(u,2.3); 
			Apustua ap=new Apustua(apA,kuota);
			kuota.addApustua(ap);
			//Mockito prestatu
			ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
			Mockito.doReturn(true).when(dataAccess).gertaeraEzabatu(ev);	
			
			//deitu sut
			boolean emaitza=sut.gertaeraEzabatu(ev); 
			//konprobatu
			assertTrue(emaitza);	
			
			//verify arguments
			Mockito.verify(dataAccess,Mockito.times(1)).gertaeraEzabatu(eventCaptor.capture());
			assertEquals(ev,eventCaptor.getValue());
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROREA TEST3");
			fail();
		}
	}
		
		@Test //data atzo
		public void test4() {
			Registered u=new Registered("iosu","123",11);
			Team t1=new Team("RSO");
			Team t2=new Team("ATL");
			Date oneDate = new Date();
			Calendar c = Calendar.getInstance(); 
			c.setTime(oneDate); 
			c.add(Calendar.DATE, -1);
			oneDate = c.getTime();	
			
				
			
			try {
				Event ev=new Event("Finala",oneDate,t1,t2);
				Question quest=ev.addQuestion("Who wins", 2);
				ev.getQuestions().get(0).setResult("Realak");
				Quote kuota=quest.addQuote(2.5,"realak irabazi", quest);
				ApustuAnitza apA=new ApustuAnitza(u,2.3); 
				Apustua ap=new Apustua(apA,kuota);
				kuota.addApustua(ap);
				//Mockito prestatu
				ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
				Mockito.doReturn(true).when(dataAccess).gertaeraEzabatu(ev);	
				
				//deitu sut
				boolean emaitza=sut.gertaeraEzabatu(ev); 
				//konprobatu
				assertTrue(emaitza);	
				
				//verify arguments
				Mockito.verify(dataAccess,Mockito.times(1)).gertaeraEzabatu(eventCaptor.capture());
				assertEquals(ev,eventCaptor.getValue());
				
			}catch(Exception e) {
				System.out.println(e.getMessage());
				System.out.println("ERROREA TEST4");
				fail();
			}
		}
			
			@Test //data gaur
			public void test5() {
				Registered u=new Registered("iosu","123",11);
				Team t1=new Team("RSO");
				Team t2=new Team("ATL");
				Date oneDate = new Date();
				Calendar c = Calendar.getInstance(); 
				c.setTime(oneDate); 
				oneDate = c.getTime();	
				
					
				
				try {
					Event ev=new Event("Finala",oneDate,t1,t2);
					Question quest=ev.addQuestion("Who wins", 2);
					ev.getQuestions().get(0).setResult("Realak");
					Quote kuota=quest.addQuote(2.5,"realak irabazi", quest);
					ApustuAnitza apA=new ApustuAnitza(u,2.3); 
					Apustua ap=new Apustua(apA,kuota);
					kuota.addApustua(ap);
					//Mockito prestatu
					ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
					Mockito.doReturn(true).when(dataAccess).gertaeraEzabatu(ev);	
					
					//deitu sut
					boolean emaitza=sut.gertaeraEzabatu(ev); 
					//konprobatu
					assertTrue(emaitza);	
					
					//verify arguments
					Mockito.verify(dataAccess,Mockito.times(1)).gertaeraEzabatu(eventCaptor.capture());
					assertEquals(ev,eventCaptor.getValue());
					
				}catch(Exception e) {
					System.out.println(e.getMessage());
					System.out.println("ERROREA TEST5");
					fail();
				}
	}
			
			@Test //data gaur
			public void test6() {
				Registered u=new Registered("iosu","123",11);
				Team t1=new Team("RSO");
				Team t2=new Team("ATL");
				Date oneDate = new Date();
				Calendar c = Calendar.getInstance(); 
				c.setTime(oneDate); 
				c.add(Calendar.DATE, +1);
				oneDate = c.getTime();			
				
				try {
					Event ev=new Event("Finala",oneDate,t1,t2);
					Question quest=ev.addQuestion("Who wins", 2);
					ev.getQuestions().get(0).setResult("Realak");
					Quote kuota=quest.addQuote(2.5,"realak irabazi", quest);
					ApustuAnitza apA=new ApustuAnitza(u,2.3); 
					Apustua ap=new Apustua(apA,kuota);
					kuota.addApustua(ap);
					//Mockito prestatu
					ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
					Mockito.doReturn(true).when(dataAccess).gertaeraEzabatu(ev);	
					
					//deitu sut
					boolean emaitza=sut.gertaeraEzabatu(ev); 
					//konprobatu
					assertTrue(emaitza);	
					
					//verify arguments
					Mockito.verify(dataAccess,Mockito.times(1)).gertaeraEzabatu(eventCaptor.capture());
					assertEquals(ev,eventCaptor.getValue());
					
				}catch(Exception e) {
					System.out.println(e.getMessage());
					System.out.println("ERROREA TEST6");
					fail();
				}
			}
			
			@Test //event=null
			public void test7() {
							
				
				try {
					Event ev=null;
					//Mockito prestatu
					ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
					Mockito.doReturn(false).when(dataAccess).gertaeraEzabatu(ev);	
					
					//deitu sut
					
					 boolean emaitza=sut.gertaeraEzabatu(ev); 
					
					//konprobatu
					assertFalse(emaitza);	
					
					//verify arguments
					Mockito.verify(dataAccess,Mockito.times(1)).gertaeraEzabatu(eventCaptor.capture());
					assertEquals(ev,eventCaptor.getValue());
					
				}catch(Exception e) {
					System.out.println(e.getMessage());
					System.out.println("ERROREA TEST7");
					
				}
	}
	
	
	
	
	
	
	
}



