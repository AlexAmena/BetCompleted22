package domain;

import java.io.Serializable;
import java.util.Date;
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

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class ApustuAnitza implements Serializable{
	@Id
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer apustuAnitzaNumber;
	private Date data;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Vector<Apustua> apustuak = new Vector<Apustua>();
	@XmlIDREF
	private Registered user;
	private String egoera;
	private Integer apustuKopia;
	private Double balioa; 
	
	public ApustuAnitza() {
		super();
	}
	
	public ApustuAnitza(Registered user,Double balioa) {
		this.user=user;
		this.data=new Date();
		this.apustuKopia=0;
		this.egoera="jokoan";
		this.balioa= balioa;
	}
	
	public Registered getUser() {
		return user;
	}

	public void setUser(Registered user) {
		this.user = user;
	}

	public Double getBalioa() {
		return balioa;
	}

	public void setBalioa(Double balioa) {
		this.balioa = balioa;
	}

	public Integer getApustuAnitzaNumber() {
		return apustuAnitzaNumber;
	}

	public void setApustuAnitzaNumber(Integer apustuAnitzaNumber) {
		this.apustuAnitzaNumber = apustuAnitzaNumber;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Vector<Apustua> getApustuak() {
		return apustuak;
	}

	public void setApustuak(Vector<Apustua> apustuak) {
		this.apustuak = apustuak;
	}
	
	public void addApustua(Apustua ap) {
		this.apustuak.add(ap);
	}
	
	public String getEgoera() {
		return egoera;
	}

	public void setEgoera(String egoera) {
		this.egoera = egoera;
	}

	public Integer getApustuKopia() {
		return apustuKopia;
	}

	public void setApustuKopia(Integer apustuKopia) {
		this.apustuKopia = apustuKopia;
	}

	public void addKirolaKant() {
		Sport s=null;
		for(Apustua q: apustuak) {
			s=q.getKuota().getQuestion().getEvent().getSport();
			s.setApustuKantitatea(s.getApustuKantitatea()+1);
		}
	}
	
	public boolean galdutaMarkatu() {
		for(Apustua a: apustuak) {
			if(a.getEgoera().equals("galduta")) {
				this.egoera="galduta";
				return true;
			}
		}
		return false;
	}
	
	public boolean irabazitaMarkatu() {
		if(this.egoera.equals("jokoan")) {
			for(Apustua a: apustuak) {
				if(!a.getEgoera().equals("irabazita")) {
					return false;
				}
			}
			this.egoera="irabazita";
			return true;
		}
		return false;
	}
	
	public void removeApustua(Apustua ap) {
		this.apustuak.remove(ap);
	}
	
	@Override
	public String toString() {
		return this.data + "; Zenbat: "+ this.balioa.toString() + "; Egoera: " + this.egoera;
	}
}
