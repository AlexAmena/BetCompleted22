package DefaultPackage;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Event;
import test.DataAccess.TestDataAccess;

public class GertaerakSortuDAW {

	static DataAccess sut=new DataAccess();
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();

	private Event ev;
	
	@Test
	public void test1() {
		
		String description = null;
		boolean emaitza = sut.gertaerakSortu(description, new Date(), "Futbola");
		assertTrue(!emaitza);
	}
	@Test
	public void test2() {
		
		String description = null;
		boolean emaitza = sut.gertaerakSortu(description, new Date(), "Futbola");
		assertTrue(!emaitza);
	}
}
