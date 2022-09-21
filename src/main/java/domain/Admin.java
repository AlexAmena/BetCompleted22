package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Admin extends User implements Serializable{

	public Admin(String username, String password, Integer bankAccount) {
		super(username, password, bankAccount);
		
	}
	public Admin() {
		super();
	}

}
