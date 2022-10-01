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
			d.setMonth(d.getMonth());
			d.setHours(d.getHours()+24);
			
			//configure Mock
			Mockito.doReturn(true).when(dataAccess).gertaerakSortu(description, d, sportName);



			//invoke System Under Test (sut) 
			boolean emaitza=sut.gertaerakSortu(description, d, sportName);
			
			//Egiaztatu emaitza zuzena dela
			assertTrue(emaitza);
			//verify the results
			//Mockito.verify(dataAccess,Mockito.times(1)).createQuestion(Mockito.any(Event.class),Mockito.any(String.class), Mockito.any(Integer.class));


			ArgumentCaptor<String> descriptionCaptor = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<Date> dateCaptor = ArgumentCaptor.forClass(Date.class);
			ArgumentCaptor<String> sportNameCaptor = ArgumentCaptor.forClass(String.class);

			Mockito.verify(dataAccess,Mockito.times(1)).gertaerakSortu(descriptionCaptor.capture(),dateCaptor.capture(), sportNameCaptor.capture());

			assertEquals(descriptionCaptor.getValue(),description);
			assertEquals(dateCaptor.getValue(),d);
			assertEquals(sportNameCaptor.getValue(),sportName);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*@Test
	//sut.createQuestion:  The event is null.
	public void test3() {
		try {
			//define paramaters
			String queryText="proba galdera";
			Float betMinimum=new Float(2);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

			//configure Mock
			Mockito.doReturn(oneDate).when(mockedEvent).getEventDate();
			Mockito.doReturn(null).when(dataAccess).createQuestion(Mockito.any(Event.class),Mockito.any(String.class), Mockito.any(Integer.class));



			//invoke System Under Test (sut) 
			Question q=sut.createQuestion(null, queryText, betMinimum);

			//verify the results
			Mockito.verify(dataAccess,Mockito.times(1)).createQuestion(Mockito.any(Event.class),Mockito.any(String.class), Mockito.any(Integer.class));



			assertTrue(q==null);


		} catch (QuestionAlreadyExist e) {
			// TODO Auto-generated catch block
			fail();
		} catch (EventFinished e) {
			fail();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void test7() {
		try {
			//define paramaters
			String queryText="proba galdera";
			Float betMinimum=new Float(2);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

			//configure Mock
			Mockito.doReturn(oneDate).when(mockedEvent).getEventDate();
			Mockito.when(dataAccess.createQuestion(Mockito.any(Event.class),Mockito.any(String.class), Mockito.any(Integer.class))).thenThrow(QuestionAlreadyExist.class);


			//invoke System Under Test (sut) 
			sut.createQuestion(mockedEvent, queryText, betMinimum);

			//if the program continues fail
			fail();
		} catch (QuestionAlreadyExist e) {
			// TODO Auto-generated catch block

			// if the program goes to this point OK
			assertTrue(true);
		} catch (EventFinished e) {
			// if the program goes to this point fail
			fail();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
