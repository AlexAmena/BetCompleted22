package DefaultPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;

import org.junit.Test;

import dataAccess.DataAccess;
import test.DataAccess.TestDataAccess;
import domain.Registered;

public class RankingLortuDAB {
	 //sut:system under test
	 static DataAccess sut=new DataAccess();
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();
	 
	 private List<Registered> ra = new ArrayList<Registered>();
	 
	 @Test
	 public void test1() {
		 try {
			 String username1="user1";
			 String password1="password";
			 int bankAccount1=1;
			 Double gains1=5.0;
			 String username2="user2";
			 String password2="password";
			 int bankAccount2=2;
			 Double gains2=22.1;
			 String username3="user3";
			 String password3="password";
			 int bankAccount3=3;
			 Double gains3=41.1;
		 
			 List<Registered>List1 = new ArrayList<Registered>();
			 List<Registered>testList = new ArrayList<Registered>();
		 
			 Registered user1 = new Registered(username1, password1, bankAccount1, gains1);
			 Registered user2 = new Registered(username2, password2, bankAccount2, gains2);
			 Registered user3 = new Registered(username3, password3, bankAccount3, gains3);
		 
			 ra.add(user3);
			 ra.add(user2);
			 ra.add(user1);
			 
			 testDA.open();
			 testDA.addUser(user1);
			 testDA.addUser(user2);
			 testDA.addUser(user3);
			 testDA.close();
			 
			 List1 = sut.rankingLortu();
		 
			 for(Registered r : List1) {
				 if(r.getIrabazitakoa() != 0) {
					 testList.add(r);
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
		 finally{
			 testDA.open();
			 for(Registered r:ra) {
				 testDA.deleteUser(r);
			 }
			 testDA.close();
		 } 
	 }
	 
	
	@Test
	 public void test2(){
		 testDA.open();
		 List<Registered> testList= testDA.getAllUsers();
		 testDA.close();
		 ra=sut.rankingLortu();
		 for(int i=0; i<ra.size();i++) {
			 assertTrue(ra.get(i).getUsername().equals(testList.get(i).getUsername()));
			 assertTrue(ra.get(i).getPassword().equals(testList.get(i).getPassword()));
			 assertTrue(ra.get(i).getBankAccount()==testList.get(i).getBankAccount());
			 assertEquals(ra.get(i).getIrabazitakoa(),0.0);
		 }
		 
	 }
	 
	 
	 @Test
	 public void test3() {
		 
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
	 public void test4() {
		 try {
			 String username1="user1";
			 String password1="password";
			 int bankAccount1=1;
			 Double gains1=5.0;
			 String username2="user2";
			 String password2="password";
			 int bankAccount2=2;
			 Double gains2=5.0;
			 String username3="user3";
			 String password3="password";
			 int bankAccount3=3;
			 Double gains3=5.0;
	 
			 List<Registered>List1 = new ArrayList<Registered>();
			 List<Registered>testList = new ArrayList<Registered>();
	 
			 Registered user1 = new Registered(username1, password1, bankAccount1, gains1);
			 Registered user2 = new Registered(username2, password2, bankAccount2, gains2);
			 Registered user3 = new Registered(username3, password3, bankAccount3, gains3);
	 
			 ra.add(user1);
			 ra.add(user2);
			 ra.add(user3);
	 
			 testDA.open();
			 testDA.addUser(user1);
			 testDA.addUser(user2);
			 testDA.addUser(user3);
			 testDA.close();
		 
			 List1 = sut.rankingLortu();
	 
			 for(Registered r : List1) {
				 if(r.getIrabazitakoa() != 0) {
					 testList.add(r);
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
		 finally{
			 testDA.open();
			 for(Registered r:ra) {
				 testDA.deleteUser(r);
			 }
			 testDA.close();
		 } 
		 
	 }
	 
	 @Test
	 public void test5() {
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
	 
	 
	 

}
