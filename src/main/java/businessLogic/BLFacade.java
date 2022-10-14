package businessLogic;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import domain.ApustuAnitza;
import domain.Apustua;
import domain.Elkarrizketa;
import domain.ElkarrizketaContainer;
import domain.Event;
import domain.Message;
import domain.MezuakContainer;
//import domain.Booking;
import domain.Question;
import domain.Quote;
import domain.Registered;
import domain.Sport;
import domain.Team;
import domain.Transaction;
import domain.User;
import exceptions.EventFinished;
import exceptions.EventNotFinished;
import exceptions.QuestionAlreadyExist;
import exceptions.QuoteAlreadyExist;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade{
	  

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	@WebMethod Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;
	
	
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> getEvents(Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();

	@WebMethod public User isLogin(String username, String password); 
	
	@WebMethod public boolean isRegister(String username); 
	@WebMethod public void storeRegistered(String username, String password, Integer bankAccount);
	
	@WebMethod public boolean gertaerakSortu(String description,Date eventDate, String sport) throws EventFinished;
	
	@WebMethod public void storeQuote(String forecast, Double quote, Question question) throws QuoteAlreadyExist;
	
	@WebMethod public Collection<Question> findQuestion(Event event);
	
	@WebMethod public void DiruaSartu(User u, Double dirua, String mota); 
	
	@WebMethod public Collection<Quote> findQuote(Question question); 
	
	@WebMethod public boolean ApustuaEgin(User u, Vector<Quote> q, Double balioa, Integer apustuaGalarazi); 
	
	@WebMethod public List<Apustua> findApustua(User u); 
	
	@WebMethod public Event findEvent(Quote q);
	
	@WebMethod public Question findQuestionFromQuote(Quote q);
	
	@WebMethod public Event findEventFromApustua(Apustua q);
	
	@WebMethod public List<ApustuAnitza> findApustuAnitza(User u); 
	
	@WebMethod public void apustuaEzabatu(User user1, ApustuAnitza apustua); 
	
	@WebMethod public List<Transaction> findTransakzioak(User u); 
	
	@WebMethod public void EmaitzakIpini(Quote quote) throws EventNotFinished;
	
	@WebMethod  public boolean gertaeraEzabatu(Event ev);
	
	@WebMethod public String saldoaBistaratu(User u);
	
	@WebMethod public List<ElkarrizketaContainer> elkarrizketakLortu(User u);
	
	@WebMethod public boolean mezuaBidali(User igorle,  Elkarrizketa m,Vector<String> osagaiak);
	
	@WebMethod public List<Registered> rankingLortu();
	
	@WebMethod public List<Event> getEventsAll();
	
	@WebMethod public void mezuaIkusita(Message m);
	
	@WebMethod public boolean gertaerakKopiatu(Event e, Date date);
	
	@WebMethod public boolean jarraitu(Registered jabea, Registered jarraitua, Double limit);
	
	@WebMethod public Sport gehiengoaLortu(User reg);
	
	@WebMethod public Sport popularrenaLortu();
	
	@WebMethod public void ezJarraituTaldea(User u);
	
	@WebMethod public List<Team> getAllTeams();
	
	@WebMethod public void jarraituTaldea(User u, Team t);
	
	@WebMethod public User findUser(User user);
	
	@WebMethod public List<Event> getEventsTeam(Team t);
	
	@WebMethod public Elkarrizketa findElkarrizketa(Elkarrizketa elk);
	
	@WebMethod public List<MezuakContainer> mezuakLortu(Elkarrizketa e);
	
	@WebMethod public Team findTeam(User u);
	
	@WebMethod public Sport findSport(Event q);
}
