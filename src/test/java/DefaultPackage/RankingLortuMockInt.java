package DefaultPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Registered;

@RunWith(MockitoJUnitRunner.class)
public class RankingLortuMockInt {
	
	DataAccess dataAccess=Mockito.mock(DataAccess.class);
	
	@InjectMocks
	 BLFacade sut=new BLFacadeImplementation(dataAccess);
	 
	
	 @Test
	public void test1() {
		 List<Registered> Ranking = new ArrayList<Registered>();
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
		 
		 Registered user1 = new Registered(username1, password1, bankAccount1, gains1);
		 Registered user2 = new Registered(username2, password2, bankAccount2, gains2);
		 Registered user3 = new Registered(username3, password3, bankAccount3, gains3);
		 
		 List<Registered> prueba = new ArrayList<Registered>();
		 
		 
		 Ranking.add(user1);
		 Ranking.add(user2);
		 Ranking.add(user3);
		 
		 Mockito.doReturn(Ranking).when(dataAccess).rankingLortu();
		 
		 prueba = sut.rankingLortu();
		 
		 for(int i=0; i<prueba.size();i++) {
			 assertTrue(prueba.get(i).getUsername().equals(Ranking.get(i).getUsername()));
			 assertTrue(prueba.get(i).getPassword().equals(Ranking.get(i).getPassword()));
			 assertTrue(prueba.get(i).getBankAccount()==Ranking.get(i).getBankAccount());
			 assertEquals(prueba.get(i).getIrabazitakoa(),Ranking.get(i).getIrabazitakoa());
		 }
		 
		
		
		
	}
	 
	 @Test
	 public void test2() {
		 List<Registered> Ranking = new ArrayList<Registered>();
		 String username1="user1124";
		 String password1="password";
		 int bankAccount1=1;
		 Double gains1=5.0;
		 
		 Registered user1 = new Registered(username1, password1, bankAccount1, gains1);
		 List<Registered> prueba = new ArrayList<Registered>();
		 Ranking.add(user1);
		 Mockito.doReturn(Ranking).when(dataAccess).rankingLortu();
		 
		 prueba = sut.rankingLortu();
		 
		 for(int i=0; i<prueba.size();i++) {
			 assertTrue(prueba.get(i).getUsername().equals(Ranking.get(i).getUsername()));
			 assertTrue(prueba.get(i).getPassword().equals(Ranking.get(i).getPassword()));
			 assertTrue(prueba.get(i).getBankAccount()==Ranking.get(i).getBankAccount());
			 assertEquals(prueba.get(i).getIrabazitakoa(),Ranking.get(i).getIrabazitakoa());
		 }
		 
	 }
	 @Test
	 public void test3() {
		 
		 List<Registered> Ranking = new ArrayList<Registered>();
		 String username1="user123";
		 String password1="password";
		 int bankAccount1=1;
		 Double gains1=0.0;
		 String username2="user241";
		 String password2="password";
		 int bankAccount2=2;
		 Double gains2=0.0;
		 String username3="user319";
		 String password3="password";
		 int bankAccount3=3;
		 Double gains3=0.0;
		 
		 Registered user1 = new Registered(username1, password1, bankAccount1, gains1);
		 Registered user2 = new Registered(username2, password2, bankAccount2, gains2);
		 Registered user3 = new Registered(username3, password3, bankAccount3, gains3);
		 
		 List<Registered> prueba = new ArrayList<Registered>();
		 
		 Ranking.add(user1);
		 Ranking.add(user2);
		 Ranking.add(user3);
		 
		 Mockito.doReturn(Ranking).when(dataAccess).rankingLortu();
		 
		 prueba = sut.rankingLortu();
		 
		 for(int i=0; i<prueba.size();i++) {
			 assertTrue(prueba.get(i).getUsername().equals(Ranking.get(i).getUsername()));
			 assertTrue(prueba.get(i).getPassword().equals(Ranking.get(i).getPassword()));
			 assertTrue(prueba.get(i).getBankAccount()==Ranking.get(i).getBankAccount());
			 assertEquals(prueba.get(i).getIrabazitakoa(),Ranking.get(i).getIrabazitakoa());
		 }
		 
	 }
	 @Test
	 public void test4() {
		 
		 List<Registered> Ranking = new ArrayList<Registered>();
		 
		 List<Registered> prueba = new ArrayList<Registered>();
		 
		 Mockito.doReturn(Ranking).when(dataAccess).rankingLortu();
		 
		 prueba = sut.rankingLortu();
		 
		 assertTrue(prueba.size() == 0);
		 
		 
	 }
	 
	 @Test
	 public void test5() {
		 List<Registered> Ranking = new ArrayList<Registered>();
		 String username1="user112";
		 String password1="password";
		 int bankAccount1=1;
		 Double gains1=25.0;
		 String username2="user2111";
		 String password2="password";
		 int bankAccount2=2;
		 Double gains2=35.0;
		 String username3="user332";
		 String password3="password";
		 int bankAccount3=3;
		 Double gains3=55.0;
		 
		 Registered user1 = new Registered(username1, password1, bankAccount1, gains1);
		 Registered user2 = new Registered(username2, password2, bankAccount2, gains2);
		 Registered user3 = new Registered(username3, password3, bankAccount3, gains3);
		 List<Registered> prueba = new ArrayList<Registered>();
		 
		 Ranking.add(user3);
		 Ranking.add(user2);
		 Ranking.add(user1);
		 
		 Mockito.doReturn(Ranking).when(dataAccess).rankingLortu();
		 
		 prueba = sut.rankingLortu();
		 
		 for(int i=0; i<prueba.size();i++) {
			 assertTrue(prueba.get(i).getUsername().equals(Ranking.get(i).getUsername()));
			 assertTrue(prueba.get(i).getPassword().equals(Ranking.get(i).getPassword()));
			 assertTrue(prueba.get(i).getBankAccount()==Ranking.get(i).getBankAccount());
			 assertEquals(prueba.get(i).getIrabazitakoa(),Ranking.get(i).getIrabazitakoa());
		 }
		 
		 
	 }
	 
	 
	 
	 
}
