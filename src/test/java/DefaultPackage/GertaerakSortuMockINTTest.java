package DefaultPackage;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GertaerakSortuMockINTTest {
	DataAccess dataAccess=Mockito.mock(DataAccess.class);
	Event mockedEvent=Mockito.mock(Event.class);

	@InjectMocks
	BLFacade sut=new BLFacadeImplementation(dataAccess);

	//sut.createQuestion:  The event has one question with a queryText. 


	@Test
	//sut.createQuestion:  The event has NOT a question with a queryText.
	public void test1() {
		String description=null;
		String firstTeam=null;
		String secondTeam=null;
		String sportName = null;
		Date d = new Date();

		try {
			//define paramaters
			description = "a-b";
			firstTeam = "a";
			secondTeam = "b";
			sportName = "sport";
			d.setMonth(d.getMonth()+1);
			d.setHours(0);
			d.setMinutes(0);
			d.setSeconds(0);

			//configure Mock
			Mockito.doReturn(true).when(dataAccess).gertaerakSortu(description, d, sportName);
			ArgumentCaptor<String> descriptionCaptor = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<Date> dateCaptor = ArgumentCaptor.forClass(Date.class);
			ArgumentCaptor<String> sportNameCaptor = ArgumentCaptor.forClass(String.class);

			//invoke System Under Test (sut) 
			boolean emaitza=sut.gertaerakSortu(description, d, sportName);

			//Egiaztatu emaitza zuzena dela
			assertTrue(emaitza);
			//verify the results
			//Mockito.verify(dataAccess,Mockito.times(1)).createQuestion(Mockito.any(Event.class),Mockito.any(String.class), Mockito.any(Integer.class));

			Mockito.verify(dataAccess,Mockito.times(1)).gertaerakSortu(descriptionCaptor.capture(),dateCaptor.capture(), sportNameCaptor.capture());

			assertEquals(descriptionCaptor.getValue(),description);
			assertEquals(dateCaptor.getValue(),d);
			assertEquals(sportNameCaptor.getValue(),sportName);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	//sut.createQuestion:  The event has NOT a question with a queryText.
	public void test2() {
		String description=null;
		String firstTeam=null;
		String secondTeam=null;
		String sportName = null;
		Date d = new Date();

		try {
			//define paramaters
			description = "a-b";
			firstTeam = "a";
			secondTeam = "b";
			sportName = "sport2";
			d.setMonth(d.getMonth()+1);
			d.setHours(0);
			d.setMinutes(0);
			d.setSeconds(0);
			
			//configure Mock
			ArgumentCaptor<String> descriptionCaptor = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<Date> dateCaptor = ArgumentCaptor.forClass(Date.class);
			ArgumentCaptor<String> sportNameCaptor = ArgumentCaptor.forClass(String.class);
			Mockito.doReturn(false).when(dataAccess).gertaerakSortu(description, d, sportName);

			//invoke System Under Test (sut) 
			boolean emaitza=sut.gertaerakSortu(description, d, sportName);

			//Egiaztatu emaitza zuzena dela
			assertTrue(!emaitza);
			//verify the results
			//Mockito.verify(dataAccess,Mockito.times(1)).createQuestion(Mockito.any(Event.class),Mockito.any(String.class), Mockito.any(Integer.class));

			Mockito.verify(dataAccess,Mockito.times(1)).gertaerakSortu(descriptionCaptor.capture(),dateCaptor.capture(), sportNameCaptor.capture());

			assertEquals(descriptionCaptor.getValue(),description);
			assertEquals(dateCaptor.getValue(),d);
			assertEquals(sportNameCaptor.getValue(),sportName);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	//sut.createQuestion:  The event has NOT a question with a queryText.
	public void test3() {
		String description=null;
		String firstTeam=null;
		String secondTeam=null;
		String sportName = null;
		Date d = new Date();
		ArgumentCaptor<String> descriptionCaptor = null;;
		ArgumentCaptor<Date> dateCaptor = null;
		ArgumentCaptor<String> sportNameCaptor = null;
		
		
		try {
			//define paramaters
			
			firstTeam = "a";
			secondTeam = "b";
			sportName = "sport";
			d.setMonth(d.getMonth()+1);
			d.setHours(0);
			d.setMinutes(0);
			d.setSeconds(0);

			//configure Mock
			descriptionCaptor = ArgumentCaptor.forClass(String.class);
			dateCaptor = ArgumentCaptor.forClass(Date.class);
			sportNameCaptor = ArgumentCaptor.forClass(String.class);
			Mockito.doReturn(new Exception()).when(dataAccess).gertaerakSortu(description, d, sportName);

			//invoke System Under Test (sut) 
			boolean emaitza=sut.gertaerakSortu(description, d, sportName);

			fail();
			
		} catch (Exception e) {
			assertTrue(true);
			Mockito.verify(dataAccess,Mockito.times(1)).gertaerakSortu(descriptionCaptor.capture(),dateCaptor.capture(), sportNameCaptor.capture());

			assertEquals(descriptionCaptor.getValue(),description);
			assertEquals(dateCaptor.getValue(),d);
			assertEquals(sportNameCaptor.getValue(),sportName);
		}
	}
	@Test
	//sut.createQuestion:  The event has NOT a question with a queryText.
	public void test4() {
		String description=null;
		String firstTeam=null;
		String secondTeam=null;
		String sportName = null;
		Date d = new Date();
		ArgumentCaptor<String> descriptionCaptor = null;;
		ArgumentCaptor<Date> dateCaptor = null;
		ArgumentCaptor<String> sportNameCaptor = null;
		
		try {
			//define paramaters
			description = "a b";
			firstTeam = "a";
			secondTeam = "b";
			sportName = "sport2";
			d.setMonth(d.getMonth()+1);
			d.setHours(0);
			d.setMinutes(0);
			d.setSeconds(0);
			
			//configure Mock
			descriptionCaptor = ArgumentCaptor.forClass(String.class);
			dateCaptor = ArgumentCaptor.forClass(Date.class);
			sportNameCaptor = ArgumentCaptor.forClass(String.class);
			Mockito.doReturn(new Exception()).when(dataAccess).gertaerakSortu(description, d, sportName);

			fail();
			
		} catch (Exception e) {
			assertTrue(true);
			Mockito.verify(dataAccess,Mockito.times(1)).gertaerakSortu(descriptionCaptor.capture(),dateCaptor.capture(), sportNameCaptor.capture());

			assertEquals(descriptionCaptor.getValue(),description);
			assertEquals(dateCaptor.getValue(),d);
			assertEquals(sportNameCaptor.getValue(),sportName);
		}
	}
	@Test
	//sut.createQuestion:  The event has NOT a question with a queryText.
	public void test5() {
		String description=null;
		String firstTeam=null;
		String secondTeam=null;
		String sportName = null;
		Date d = null;
		ArgumentCaptor<String> descriptionCaptor = null;;
		ArgumentCaptor<Date> dateCaptor = null;
		ArgumentCaptor<String> sportNameCaptor = null;
		
		try {
			//define paramaters
			description = "a-b";
			firstTeam = "a";
			secondTeam = "b";
			sportName = "sport2";

			//configure Mock
			descriptionCaptor = ArgumentCaptor.forClass(String.class);
			dateCaptor = ArgumentCaptor.forClass(Date.class);
			sportNameCaptor = ArgumentCaptor.forClass(String.class);
			Mockito.doReturn(new Exception()).when(dataAccess).gertaerakSortu(description, d, sportName);

			//invoke System Under Test (sut) 
			boolean emaitza=sut.gertaerakSortu(description, d, sportName);

			
			fail();			

		} catch (Exception e) {
			assertTrue(true);
			Mockito.verify(dataAccess,Mockito.times(1)).gertaerakSortu(descriptionCaptor.capture(),dateCaptor.capture(), sportNameCaptor.capture());

			assertEquals(descriptionCaptor.getValue(),description);
			assertEquals(dateCaptor.getValue(),d);
			assertEquals(sportNameCaptor.getValue(),sportName);
		}
	}
	@Test
	//sut.createQuestion:  The event has NOT a question with a queryText.
	public void test6() {
		String description=null;
		String firstTeam=null;
		String secondTeam=null;
		String sportName = null;
		Date d = new Date();
		ArgumentCaptor<String> descriptionCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<Date> dateCaptor = ArgumentCaptor.forClass(Date.class);
		ArgumentCaptor<String> sportNameCaptor = ArgumentCaptor.forClass(String.class);

		try {
			//define paramaters
			description = "a-b";
			firstTeam = "a";
			secondTeam = "b";
			sportName = "sport2";
			d.setMonth(d.getMonth()-1);
			d.setHours(0);
			d.setMinutes(0);
			d.setSeconds(0);

			//configure Mock
			descriptionCaptor = ArgumentCaptor.forClass(String.class);
			dateCaptor = ArgumentCaptor.forClass(Date.class);
			sportNameCaptor = ArgumentCaptor.forClass(String.class);

			//invoke System Under Test (sut) 
			boolean emaitza=sut.gertaerakSortu(description, d, sportName);
			sportNameCaptor = ArgumentCaptor.forClass(String.class);
			Mockito.doReturn(new EventFinished()).when(dataAccess).gertaerakSortu(description, d, sportName);

			//Egiaztatu emaitza zuzena dela
			fail();
			//verify the results
			//Mockito.verify(dataAccess,Mockito.times(1)).createQuestion(Mockito.any(Event.class),Mockito.any(String.class), Mockito.any(Integer.class));

		} catch (Exception e) {
			assertTrue(true);
			Mockito.verify(dataAccess,Mockito.times(1)).gertaerakSortu(descriptionCaptor.capture(),dateCaptor.capture(), sportNameCaptor.capture());
			assertEquals(descriptionCaptor.getValue(),description);
			assertEquals(dateCaptor.getValue(),d);
			assertEquals(sportNameCaptor.getValue(),sportName);

		}
	}
	@Test
	//sut.createQuestion:  The event has NOT a question with a queryText.
	public void test7() {
		String description=null;
		String firstTeam=null;
		String secondTeam=null;
		String sportName = null;
		ArgumentCaptor<String> descriptionCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<Date> dateCaptor = ArgumentCaptor.forClass(Date.class);
		ArgumentCaptor<String> sportNameCaptor = ArgumentCaptor.forClass(String.class);

		Date d = new Date();

		try {
			//define paramaters
			description = "a-b";
			firstTeam = "a";
			secondTeam = "b";
			d.setMonth(d.getMonth()+1);
			d.setHours(0);
			d.setMinutes(0);
			d.setSeconds(0);

			//configure Mock
			descriptionCaptor = ArgumentCaptor.forClass(String.class);
			dateCaptor = ArgumentCaptor.forClass(Date.class);
			sportNameCaptor = ArgumentCaptor.forClass(String.class);
			Mockito.doReturn(new Exception()).when(dataAccess).gertaerakSortu(description, d, sportName);

			//invoke System Under Test (sut) 
			boolean emaitza=sut.gertaerakSortu(description, d, sportName);

			//Egiaztatu emaitza zuzena dela
			fail();

		} catch (Exception e) {
			assertTrue(true);
			Mockito.verify(dataAccess,Mockito.times(1)).gertaerakSortu(descriptionCaptor.capture(),dateCaptor.capture(), sportNameCaptor.capture());

			assertEquals(descriptionCaptor.getValue(),description);
			assertEquals(dateCaptor.getValue(),d);
			assertEquals(sportNameCaptor.getValue(),sportName);
		}
	}
	@Test
	//sut.createQuestion:  The event has NOT a question with a queryText.
	public void test8() {
		String description=null;
		String firstTeam=null;
		String secondTeam=null;
		String sportName = null;
		Date d = new Date();
		ArgumentCaptor<String> descriptionCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<Date> dateCaptor = ArgumentCaptor.forClass(Date.class);
		ArgumentCaptor<String> sportNameCaptor = ArgumentCaptor.forClass(String.class);
		
		try {
			//define paramaters
			description = "a-b";
			firstTeam = "a";
			secondTeam = "b";
			sportName = "sport4";
			d.setMonth(d.getMonth()+1);
			d.setHours(0);
			d.setMinutes(0);
			d.setSeconds(0);
			
			//configure Mock
			descriptionCaptor = ArgumentCaptor.forClass(String.class);
			dateCaptor = ArgumentCaptor.forClass(Date.class);
			sportNameCaptor = ArgumentCaptor.forClass(String.class);
			Mockito.doReturn(false).when(dataAccess).gertaerakSortu(description, d, sportName);

			//invoke System Under Test (sut) 
			boolean emaitza=sut.gertaerakSortu(description, d, sportName);

			Mockito.verify(dataAccess,Mockito.times(1)).gertaerakSortu(descriptionCaptor.capture(),dateCaptor.capture(), sportNameCaptor.capture());
			assertTrue(true);
			assertEquals(descriptionCaptor.getValue(),description);
			assertEquals(dateCaptor.getValue(),d);
			assertEquals(sportNameCaptor.getValue(),sportName);
		} catch (Exception e) {
			fail();
		}
	}
	@Test
	//sut.createQuestion:  The event has NOT a question with a queryText.
	public void test1MB1() {
		String description=null;
		String firstTeam=null;
		String secondTeam=null;
		String sportName = null;
		Date d = new Date();
		ArgumentCaptor<String> descriptionCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<Date> dateCaptor = ArgumentCaptor.forClass(Date.class);
		ArgumentCaptor<String> sportNameCaptor = ArgumentCaptor.forClass(String.class);

		try {
			//define paramaters
			description = "a-b";
			firstTeam = "a";
			secondTeam = "b";
			sportName = "sport";
			d.setMonth(d.getMonth());
			d.setHours(d.getHours()-24);

			//configure Mock
			descriptionCaptor = ArgumentCaptor.forClass(String.class);
			dateCaptor = ArgumentCaptor.forClass(Date.class);
			sportNameCaptor = ArgumentCaptor.forClass(String.class);
			Mockito.doReturn(new EventFinished()).when(dataAccess).gertaerakSortu(description, d, sportName);

			//invoke System Under Test (sut) 
			boolean emaitza=sut.gertaerakSortu(description, d, sportName);

			fail();
			
		} catch (Exception e) {
			Mockito.verify(dataAccess,Mockito.times(1)).gertaerakSortu(descriptionCaptor.capture(),dateCaptor.capture(), sportNameCaptor.capture());

			assertEquals(descriptionCaptor.getValue(),description);
			assertEquals(dateCaptor.getValue(),d);
			assertEquals(sportNameCaptor.getValue(),sportName);
		}
	}
	@Test
	//sut.createQuestion:  The event has NOT a question with a queryText.
	public void test1MB2() {
		String description=null;
		String firstTeam=null;
		String secondTeam=null;
		String sportName = null;
		Date d = new Date();
		ArgumentCaptor<String> descriptionCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<Date> dateCaptor = ArgumentCaptor.forClass(Date.class);
		ArgumentCaptor<String> sportNameCaptor = ArgumentCaptor.forClass(String.class);

		try {
			//define paramaters
			description = "a-b";
			firstTeam = "a";
			secondTeam = "b";
			sportName = "sport";
			d = new Date();

			//configure Mock
			descriptionCaptor = ArgumentCaptor.forClass(String.class);
			dateCaptor = ArgumentCaptor.forClass(Date.class);
			sportNameCaptor = ArgumentCaptor.forClass(String.class);
			Mockito.doReturn(new EventFinished()).when(dataAccess).gertaerakSortu(description, d, sportName);

			//invoke System Under Test (sut) 
			boolean emaitza=sut.gertaerakSortu(description, d, sportName);

			fail();
			
		} catch (Exception e) {
			Mockito.verify(dataAccess,Mockito.times(1)).gertaerakSortu(descriptionCaptor.capture(),dateCaptor.capture(), sportNameCaptor.capture());

			assertEquals(descriptionCaptor.getValue(),description);
			assertEquals(dateCaptor.getValue(),d);
			assertEquals(sportNameCaptor.getValue(),sportName);
		}
	}
	@Test
	//sut.createQuestion:  The event has NOT a question with a queryText.
	public void test1MB3() {
		String description=null;
		String firstTeam=null;
		String secondTeam=null;
		String sportName = null;
		Date d = new Date();
		ArgumentCaptor<String> descriptionCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<Date> dateCaptor = ArgumentCaptor.forClass(Date.class);
		ArgumentCaptor<String> sportNameCaptor = ArgumentCaptor.forClass(String.class);

		try {
			//define paramaters
			description = "a-b";
			firstTeam = "a";
			secondTeam = "b";
			sportName = "sport";
			d = new Date();
			d.setHours(d.getHours()+24);

			//configure Mock
			descriptionCaptor = ArgumentCaptor.forClass(String.class);
			dateCaptor = ArgumentCaptor.forClass(Date.class);
			sportNameCaptor = ArgumentCaptor.forClass(String.class);
			Mockito.doReturn(true).when(dataAccess).gertaerakSortu(description, d, sportName);

			//invoke System Under Test (sut) 
			boolean emaitza=sut.gertaerakSortu(description, d, sportName);

			assertTrue(emaitza);
			Mockito.verify(dataAccess,Mockito.times(1)).gertaerakSortu(descriptionCaptor.capture(),dateCaptor.capture(), sportNameCaptor.capture());

			assertEquals(descriptionCaptor.getValue(),description);
			assertEquals(dateCaptor.getValue(),d);
			assertEquals(sportNameCaptor.getValue(),sportName);
			
		} catch (Exception e) {
			fail();
		}
	}
}