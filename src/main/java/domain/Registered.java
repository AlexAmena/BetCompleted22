package domain;

import java.io.Serializable;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Registered extends User implements Serializable{
	private Double dirukop;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Transaction> transakzioak; 
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<ApustuAnitza> apustuAnitzak; 
	private Double irabazitakoa;
	private int zenbat;
	private String mode;
	private Double diruLimitea;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Vector<Jarraitzailea> niriLista;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Vector<Jarraitzailea> nikLista;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Vector<KirolEstatistikak> sportEstatistikak=new Vector<KirolEstatistikak>();
	@XmlIDREF
	private Team taldea;

	public Registered(String username, String password, Integer bankAccount) {
		super(username, password, bankAccount);
		this.dirukop = 0.0; 
		this.transakzioak = new Vector<Transaction>();
		this.apustuAnitzak = new Vector<ApustuAnitza>();
		this.irabazitakoa=0.0;
		this.zenbat=0;
		this.mode=null;
		this.diruLimitea=0.0;
		this.nikLista= new Vector<Jarraitzailea>();
		this.niriLista= new Vector<Jarraitzailea>();
		this.taldea=null;
	}
	public Registered() {
		super();
	}
	
	public Registered(String username, String password, Integer bankAccount, Double gains) {
		super(username, password, bankAccount);
		this.irabazitakoa=gains;
	}
	
	public Vector<Transaction> getTransakzioak() {
		return transakzioak;
	}

	public void setTransakzioak(Vector<Transaction> transakzioak) {
		this.transakzioak = transakzioak;
	}
	
	public Double getDirukop() {
		return dirukop;
	}

	public void setDirukop(Double dirukop) {
		this.dirukop = dirukop;
	}

	public Vector<ApustuAnitza> getApustuAnitzak() {
		return apustuAnitzak;
	}

	public void setApustuAnitzak(Vector<ApustuAnitza> apustuak) {
		this.apustuAnitzak = apustuak;
	}
	
	public Double getIrabazitakoa() {
		return irabazitakoa;
	}
	
	public void setIrabazitakoa(Double irabazitakoa) {
		this.irabazitakoa = irabazitakoa;
	}
	
	public int getZenbat() {
		return zenbat;
	}
	
	public void setZenbat(int zenbat) {
		this.zenbat = zenbat;
	}
	
	public String getMode() {
		return mode;
	}
	
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public Double getDiruLimitea() {
		return diruLimitea;
	}
	
	public void setDiruLimitea(Double diruLimitea) {
		this.diruLimitea = diruLimitea;
	}
	
	public Vector<Jarraitzailea> getJarraitzaileLista() {
		return niriLista;
	}
	
	public void setJarraitzaileLista(Vector<Jarraitzailea> jarraitzaileLista) {
		this.niriLista = jarraitzaileLista;
	}
	
	public Vector<Jarraitzailea> getJarraitutakoLista() {
		return nikLista;
	}
	public void setJarraitutakoLista(Vector<Jarraitzailea> jarraitutakoLista) {
		this.nikLista = jarraitutakoLista;
	}
	public void addTransaction(Transaction t) {
		this.transakzioak.add(t); 
	}
	
	public void updateDiruKontua(Double z) {
		this.dirukop = this.dirukop + z; 
	}
	
	public void addApustuAnitza(ApustuAnitza a) {
		this.apustuAnitzak.add(a); 
	}
	
	public void removeApustua(ApustuAnitza a) {
		this.apustuAnitzak.remove(a);
	}
	
	public void addJarraitzailea(Jarraitzailea reg) {
		this.niriLista.add(reg);
	}
	
	public void addJarraitutako(Jarraitzailea reg) {
		this.nikLista.add(reg);
	}
	
	public boolean containsKirola(Sport sp) {
		boolean b=false;
		for(KirolEstatistikak ke: sportEstatistikak) {
			if(ke.getSport().equals(sp)) {
				b=true;
			}
		}
		return b;
	}
	
	public void addKirolEstatistikak(KirolEstatistikak ke) {
		this.sportEstatistikak.add(ke);
	}
	
	public void removeSportEstatistikak(Sport spo) {
		this.sportEstatistikak.remove(spo);
	}
	
	public KirolEstatistikak kirolEstatistikakLortu(Sport sp) {
		for(KirolEstatistikak ke: sportEstatistikak) {
			if(ke.getSport().equals(sp)) {
				return ke;
			}
		}
		return null;
	}
	
	
	
	public Vector<KirolEstatistikak> getSportEstatistikak() {
		return sportEstatistikak;
	}
	public void setSportEstatistikak(Vector<KirolEstatistikak> sportEstatistikak) {
		this.sportEstatistikak = sportEstatistikak;
	}
	
	@Override
	public String toString() {
		if(this.mode=="RankingGUI")
			return this.getUsername() + " " + this.zenbat +": " + this.irabazitakoa + "€";
		else
			return this.getUsername();
	}
	
	public Team getTaldea() {
		return taldea;
	}
	
	public void setTaldea(Team taldea) {
		this.taldea = taldea;
	}
	
}
