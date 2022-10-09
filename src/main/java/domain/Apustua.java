package domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Apustua implements Serializable{

	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer apustuaNumber; 
	
	@XmlIDREF
	private Quote kuota;
	@XmlIDREF
	private ApustuAnitza apustuAnitza;
	private String egoera;
	
	public Apustua(ApustuAnitza ap, Quote q) {
		super();
		this.apustuAnitza=ap;
		this.kuota=q;
		this.egoera="jokoan";
	}
	
	public Apustua() {
		super();
	}

	public Integer getApostuaNumber() {
		return apustuaNumber;
	}

	public void setApostuaNumber(Integer apustuaNumber) {
		this.apustuaNumber = apustuaNumber;
	}

	public Quote getKuota() {
		return kuota;
	}

	public void setKuota(Quote kuota) {
		this.kuota = kuota;
	}

	public String getEgoera() {
		return egoera;
	}

	public void setEgoera(String egoera) {
		this.egoera = egoera;
	}
	
	public ApustuAnitza getApustuAnitza() {
		return apustuAnitza;
	}

	public void setApustuAnitza(ApustuAnitza apustuAnitza) {
		this.apustuAnitza = apustuAnitza;
	}
	
	public boolean galdutaMarkatu(Quote quo) {
		if(kuota.getQuestion().getQuestionNumber().equals(quo.getQuestion().getQuestionNumber()) && 
				!quo.getQuoteNumber().equals(kuota.getQuoteNumber())) {
			this.egoera="galduta";
			return true;
		}
		return false;
	}
	
	public void eguneratuApustuKant(Sport s) {
		s.eguneratuApustuKantitatea();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o==null) return false;
		if(this.getClass() != o.getClass()) return false;
		Apustua a = (Apustua) o; 
		
		return this.getApostuaNumber().equals(a.getApostuaNumber()); 
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(apustuaNumber, egoera, kuota, apustuAnitza);
	}
}
