package DefaultPackage;

import java.util.ArrayList;
import java.util.List;

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
	
	
	
	List<Registered> Ranking = new ArrayList<Registered>();

}
