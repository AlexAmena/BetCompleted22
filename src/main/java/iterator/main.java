package iterator;

import java.util.Calendar;
import java.util.Date;

import businessLogic.BLFacade;
import businessLogic.Factory;
import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Event;
import gui.ApplicationLauncher;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean isLocal=true;

		ConfigXML c=ConfigXML.getInstance();

		//Facade objektua lortu lehendabiziko ariketa erabiliz
		Factory f = new Factory();
		BLFacade facadeInterface;
		facadeInterface = f.createBLFacade(c);
		//BLFacade facadeInterface=……….
		Calendar today = Calendar.getInstance();

		int month=today.get(Calendar.MONTH);
		month+=1;
		int year=today.get(Calendar.YEAR);
		if (month==12) { month=0; year+=1;}
		ExtendedIterator i=facadeInterface.getEventsIterator(UtilDate.newDate(year,month,17));

		System.out.println("=======================================================\n\n\n");
		System.out.println("=======================================================");

		Event ev;
		i.goLast();
		while (i.hasPrevious()){
			ev=(Event)i.previous();
			System.out.println(ev.toString());
		}
		//Nahiz eta suposatu hasierara ailegatu garela, eragiketa egiten dugu.
		i.goFirst();
		while (i.hasNext()){
			ev=(Event)i.next();
			System.out.println(ev.toString());
		}
	}

}
