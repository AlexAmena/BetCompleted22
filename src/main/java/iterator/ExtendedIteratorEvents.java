package iterator;

import java.util.Vector;

import domain.Event;

public class ExtendedIteratorEvents implements ExtendedIterator{
	
	private Vector<Event> events;
	private int position;
	
	
	
	public ExtendedIteratorEvents(Vector<Event> vector) {
		events = vector;
		position = 0;
	}
	
	
	public Vector<Event> getEvents() {
		return events;
	}
	public void setEvents(Vector<Event> events) {
		this.events = events;
	}
		
	//uneko elementua itzultzen du eta aurrekora pasatzen da
	public Event previous() {
		Event event =events.get(position);
		position = position - 1;
		return event;
	}
	//true aurreko elementua existitzen bada.
	public boolean hasPrevious() {
		return (position>0);
	}
	//Lehendabiziko elementuan kokatzen da.
	public void goFirst() {
		position = 0;
	}
	//Azkeneko elementuan kokatzen da.
	public void goLast() {
		position = events.size()-1;
	}
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return position < events.size();
	}
	@Override
	public Event next() {
		// TODO Auto-generated method stub
		Event event =events.get(position);
		position = position + 1;
		return event;
	}
}
