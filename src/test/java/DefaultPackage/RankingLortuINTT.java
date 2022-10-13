package DefaultPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Registered;
import test.BusinessLogic.TestFacadeImplementation;

public class RankingLortuINTT {
	
	static BLFacadeImplementation sut;
	static TestFacadeImplementation testBL;
	
	@BeforeClass
	public static void setUpClass() {
		//sut= new BLFacadeImplementation();
		
		// you can parametrize the DataAccess used by BLFacadeImplementation
		//DataAccess da= new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));
		DataAccess da= new DataAccess();

		sut=new BLFacadeImplementation(da);
		
		testBL= new TestFacadeImplementation();
	}
	
	@Test
	public void test1() {
		
		List<Registered> List1  = new ArrayList<Registered>();
		List<Registered> testList  = new ArrayList<Registered>();
		
		
		try {
		 String username1="user412343";
		 String password1="password";
		 int bankAccount1=1;
		 Double gains1=5.0;
		 String username2="user2124146";
		 String password2="password";
		 int bankAccount2=2;
		 Double gains2=22.1;
		 String username3="user3112412";
		 String password3="password";
		 int bankAccount3=3;
		 Double gains3=41.1;
		 
		 Registered user1 = new Registered(username1, password1, bankAccount1, gains1);
		 Registered user2 = new Registered(username2, password2, bankAccount2, gains2);
		 Registered user3 = new Registered(username3, password3, bankAccount3, gains3);
		 
		 
		 
		 List1.add(user3);
		 List1.add(user2);
		 List1.add(user1);
		 
		 
		 testBL.addUser(user3);
		 testBL.addUser(user2);
		 testBL.addUser(user1);
		 
		 List<Registered>ra=sut.rankingLortu();
		 for(int i = 0; i<ra.size(); i++) {
			 if(ra.get(i).getIrabazitakoa()!=0.0) {
				testList.add(ra.get(i));
			 }
		 }
		 
		 for(int i=0; i<testList.size();i++) {
			 assertTrue(testList.get(i).getUsername().equals(List1.get(i).getUsername()));
			 assertTrue(testList.get(i).getPassword().equals(List1.get(i).getPassword()));
			 assertTrue(testList.get(i).getBankAccount()==List1.get(i).getBankAccount());
			 assertEquals(testList.get(i).getIrabazitakoa(),List1.get(i).getIrabazitakoa());
		 }
		}
		catch (Exception e) {
			fail();
		}
		
		finally {
			for(Registered r : List1) {
				testBL.deleteUser(r);
			}
		}
		
		}
		 
	
	@Test
	public void test2() {
		
		 List<Registered> testList= testBL.getAllUsers();
		 
		 List<Registered> ra=sut.rankingLortu();
		 for(int i=0; i<ra.size();i++) {
			 assertTrue(ra.get(i).getUsername().equals(testList.get(i).getUsername()));
			 assertTrue(ra.get(i).getPassword().equals(testList.get(i).getPassword()));
			 assertTrue(ra.get(i).getBankAccount()==testList.get(i).getBankAccount());
			 assertTrue(ra.get(i).getIrabazitakoa()==0.0);
		 }
		
		
		
	}
	
	
	//Test hau ezinda egin datu basea hutsa egon behar duelakoa
	
	//@Test
	/*public void test3() {
		 
		 List<Registered> testList= testBL.deleteAllUsers();
	 try {
		
		 List<Registered> ra=sut.rankingLortu();
		 assertEquals(ra.size(), 0);
	 }
	 catch(Exception e){
		 fail();
	 }
	 finally{
		 for (Registered r : testList) {
			 testBL.addUser(r);
		 }
	 }
	}
	*/
	
	
	@Test 
	public void test4() {
		 String username1="user1113";
		 String password1="password";
		 int bankAccount1=1;
		 Double gains1=5.0;
		 Registered user11 = new Registered(username1, password1, bankAccount1, gains1);
		 List<Registered> testList  = new ArrayList<Registered>();
		 try {
			 testBL.addUser(user11);
			 List<Registered> lista = sut.rankingLortu();
			 
			 for(int i = 0; i<lista.size(); i++) {
				 if(lista.get(i).getIrabazitakoa()!=0.0) {
					 testList.add(lista.get(i));
				 }
			 }
			 
			 assertEquals(testList.size(),1);
			 assertTrue(testList.get(0).getUsername().equals(user11.getUsername()));
			 assertTrue(testList.get(0).getPassword().equals(user11.getPassword()));
			 assertTrue(testList.get(0).getBankAccount()==user11.getBankAccount());
			 assertEquals(testList.get(0).getIrabazitakoa(),user11.getIrabazitakoa());
		 }
		 catch (Exception e) {
			 fail();
		 }
		 finally {
			 testBL.deleteUser(user11);
		 }
			 
		 }
		 
	 
	
	@Test
	public void test5() {
		 List<Registered> ra = new ArrayList<Registered>();
		 List<Registered>testList = new ArrayList<Registered>();
		 List<Registered>list = new ArrayList<Registered>();
		try {
			 String username1="user1123";
			 String password1="password";
			 int bankAccount1=1;
			 Double gains1=5.0;
			 String username2="user24364";
			 String password2="password";
			 int bankAccount2=2;
			 Double gains2=5.0;
			 String username3="user323526";
			 String password3="password";
			 int bankAccount3=3;
			 Double gains3=5.0;
	 
			 
			 
	 
			 Registered user1 = new Registered(username1, password1, bankAccount1, gains1);
			 Registered user2 = new Registered(username2, password2, bankAccount2, gains2);
			 Registered user3 = new Registered(username3, password3, bankAccount3, gains3);
	 
			 ra.add(user1);
			 ra.add(user2);
			 ra.add(user3);
	 
			 
			 testBL.addUser(user1);
			 testBL.addUser(user2);
			 testBL.addUser(user3);
			 
		 
			 list = sut.rankingLortu();
			 
			 for(int i = 0; i<list.size(); i++) {
				 if(list.get(i).getIrabazitakoa()==0.0) {
					 testList.add(list.get(i));
				 }
			 }
	 
			 
			 for(int i=0; i<ra.size();i++) {
				 assertTrue(ra.get(i).getUsername().equals(testList.get(i).getUsername()));
				 assertTrue(ra.get(i).getPassword().equals(testList.get(i).getPassword()));
				 assertTrue(ra.get(i).getBankAccount()==testList.get(i).getBankAccount());
				 assertEquals(ra.get(i).getIrabazitakoa(),testList.get(i).getIrabazitakoa());
			 }
		 	
		 }
		 catch(Exception e) {
			 fail();
		 }
		 finally {
			 for(Registered r: ra) {
				 testBL.deleteUser(r);
			 }
		 }
			 
			 
		 } 
	

}
	



