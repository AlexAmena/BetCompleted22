package DefaultPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Registered;
import test.DataAccess.TestDataAccess;

public class RankingLortuDAW {
	
	 //sut:system under test
	 static DataAccess sut=new DataAccess();
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();
	 
	 private List<Registered> ra = new ArrayList<Registered>();
	
	@Test 
	public void test1() {
		testDA.open();
		 List<Registered> testList= testDA.getAllUsers();
	 try {
		 for (Registered r : testList) {
			 testDA.deleteUser(r);
		 }
		 
		 
		 
		 ra=sut.rankingLortu();
		 assertEquals(ra.size(), 0);
	 }
	 catch(Exception e){
		 fail();
	 }
	 finally{
		 for (Registered r : testList) {
			 testDA.addUser(r);
		 }
	 }
	 testDA.close();
	}
	
	
	@Test
	public void test2() {
		String username1="user1";
		 String password1="password";
		 int bankAccount1=1;
		 Double gains1=5.0;
		 Registered user11 = new Registered(username1, password1, bankAccount1, gains1);
		 testDA.open();
		 List<Registered> testList= testDA.getAllUsers();
		 try {
			 for (Registered r : testList) {
				 testDA.deleteUser(r);
			 }
			 testDA.addUser(user11);
			 List<Registered> lista = sut.rankingLortu();
			 
			 assertEquals(lista.size(),1);
			 assertTrue(lista.get(0).getUsername().equals(user11.getUsername()));
			 assertTrue(lista.get(0).getPassword().equals(user11.getPassword()));
			 assertTrue(lista.get(0).getBankAccount()==user11.getBankAccount());
			 assertEquals(lista.get(0).getIrabazitakoa(),user11.getIrabazitakoa());
		 }
		 catch (Exception e) {
			 fail();
		 }
		 finally { 
				 for (Registered r : testList) {
					 testDA.addUser(r);
				 }
				 testDA.deleteUser(user11);
			 
		 }
		 
		 testDA.close();
	}
	
	@Test
	public void test3() {
		
		testDA.open();
		List<Registered>a= testDA.getAllUsers();
		try {
			 String username1="user117";
			 String password1="password";
			 int bankAccount1=1;
			 Double gains1=50.0;
			 String username2="user21";
			 String password2="password";
			 int bankAccount2=2;
			 Double gains2=22.1;
			 

			
			 for (Registered r : a) {
				 testDA.deleteUser(r);
			 }
			 List<Registered>List1 = new ArrayList<Registered>();
			 
		 
			 Registered user1 = new Registered(username1, password1, bankAccount1, gains1);
			 Registered user2 = new Registered(username2, password2, bankAccount2, gains2);
			
		 
			 
			 ra.add(user1);
			 ra.add(user2);
			 
			 
			 testDA.addUser(user1);
			 testDA.addUser(user2);
			 
			 
			 List1 = sut.rankingLortu();
			 
			 for(int i=0; i<ra.size();i++) {
				 assertTrue(ra.get(i).getUsername().equals(List1.get(i).getUsername()));
				 assertTrue(ra.get(i).getPassword().equals(List1.get(i).getPassword()));
				 assertTrue(ra.get(i).getBankAccount()==List1.get(i).getBankAccount());
				 assertEquals(ra.get(i).getIrabazitakoa(),List1.get(i).getIrabazitakoa());
			 }
			 	
		 }
		 catch(Exception e) {
			 fail();
		 }
		 finally{
			
			 for(Registered r:ra) {
				 testDA.deleteUser(r);
			 }
			 for (Registered r : a) {
				 testDA.addUser(r);
			 }
			 testDA.close();
		 } 
	}

}
