package iterator;

import java.util.Iterator;
import java.util.Vector;

import domain.Event;

public class EventList implements Aggregate {
	private Vector<Event> eventList;
	
	public EventList(Vector<Event> eventList) {
		this.eventList = eventList;
	}

	@Override
	public Iterator getIterator() {
		// TODO Auto-generated method stub
		return new ExtendedIteratorEvents(this.eventList);
	}
}
