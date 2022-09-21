package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Event implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer eventNumber;
	private String description; 
	private Date eventDate;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Vector<Question> questions=new Vector<>();
	@XmlIDREF
	private Sport sport;
	@XmlIDREF
	private Team lokala;
	@XmlIDREF
	private Team kanpokoa;

	public Vector<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Vector<Question> questions) {
		this.questions = questions;
	}

	public Event() {
		super();
	}

	public Event(Integer eventNumber, String description,Date eventDate, Team lokala, Team kanpokoa) {
		this.eventNumber = eventNumber;
		this.description = description;
		this.eventDate=eventDate;
		this.lokala=lokala;
		this.kanpokoa=kanpokoa;
	}
	
	public Event(String description,Date eventDate, Team lokala, Team kanpokoa) {
		this.description = description;
		this.eventDate=eventDate;
		this.lokala=lokala;
		this.kanpokoa=kanpokoa;
	}

	public Integer getEventNumber() {
		return eventNumber;
	}

	public void setEventNumber(Integer eventNumber) {
		this.eventNumber = eventNumber;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description=description;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}	
	
	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

	public String toString(){
		return eventNumber+";"+description;
	}
	
	/**
	 * This method creates a bet with a question, minimum bet ammount and percentual profit
	 * 
	 * @param question to be added to the event
	 * @param betMinimum of that question
	 * @return Bet
	 */
	public Question addQuestion(String question, double betMinimum)  {
        Question q=new Question(question,betMinimum, this);
        questions.add(q);
        return q;
	}
	
	public void listaraGehitu(Question q)  {
        questions.add(q);
	}

	
	/**
	 * This method checks if the question already exists for that event
	 * 
	 * @param question that needs to be checked if there exists
	 * @return true if the question exists and false in other case
	 */
	public boolean doesQuestionExists(String question)  {	
		for (Question q:this.getQuestions()){
			if (q.getQuestion().compareTo(question)==0)
				return true;
		}
		return false;
	}
		
	public Team getLokala() {
		return lokala;
	}

	public void setLokala(Team lokala) {
		this.lokala = lokala;
	}

	public Team getKanpokoa() {
		return kanpokoa;
	}

	public void setKanpokoa(Team kanpokoa) {
		this.kanpokoa = kanpokoa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + eventNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		return (eventNumber == other.eventNumber);
			
		
	}
	
	
}
