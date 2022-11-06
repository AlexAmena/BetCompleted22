package iterator;

import java.util.Iterator;
import java.util.Vector;

import domain.Event;

public interface ExtendedIterator extends Iterator {
	public void setEvents(Vector<Event> events);
	//uneko elementua itzultzen du eta aurrekora pasatzen da
	public Object previous();
	//true aurreko elementua existitzen bada.
	public boolean hasPrevious();
	//Lehendabiziko elementuan kokatzen da.
	public void goFirst();
	//Azkeneko elementuan kokatzen da.
	public void goLast();
	}