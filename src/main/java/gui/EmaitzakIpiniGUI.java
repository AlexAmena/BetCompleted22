package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.User;
import exceptions.EventNotFinished;
import iterator.ExtendedIteratorEvents;

import javax.swing.SwingConstants;

public class EmaitzakIpiniGUI extends JFrame{
	private BLFacade businessLogic = MainGUI.getBusinessLogic();

	private static final long serialVersionUID = 1L;

	private JComboBox<Event> jComboBoxEvents = new JComboBox<Event>();
	DefaultComboBoxModel<Event> modelEvents = new DefaultComboBoxModel<Event>();
	
	private JLabel jLabelListOfEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ListEvents"));
	private JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private JCalendar jCalendar = new JCalendar();
	private Calendar calendarAct = null;
	private Calendar calendarAnt = null;

	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	
	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();
	private final JLabel jLabelQuestion = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
	private final JComboBox<Question> jComboBoxQuestions = new JComboBox<Question>();
	DefaultComboBoxModel<Question> modelQuestions = new DefaultComboBoxModel<Question>();
	
	private domain.Event event;
	private final JLabel jLabelQuotes = new JLabel(); 

	private JComboBox jComboBoxQuotes;
	DefaultComboBoxModel<Quote> modelQuotes = new DefaultComboBoxModel<Quote>();
	
	private User user; 
	private final JButton jButtonEmaitzaIpini = new JButton(ResourceBundle.getBundle("Etiquetas").getString("EmaitzaIpini")); 
	private final JLabel jLabelError = new JLabel();
	
	public EmaitzakIpiniGUI(Vector<domain.Event> v) {
		try {
			jbInit(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit(Vector<domain.Event> v) throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 370));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("EmaitzaIpini"));
		
		jLabelError.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelError.setBounds(116, 205, 322, 13);
		getContentPane().add(jLabelError);
		jLabelError.setVisible(false);

		jComboBoxEvents.setModel(modelEvents);
		jComboBoxEvents.setBounds(new Rectangle(275, 47, 250, 20));
		jLabelListOfEvents.setBounds(new Rectangle(290, 18, 277, 20));

		jCalendar.setBounds(new Rectangle(40, 50, 225, 150));
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));
		jButtonClose.setBounds(new Rectangle(291, 228, 130, 30));
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});

		this.getContentPane().add(jButtonClose, null);
		this.getContentPane().add(jLabelListOfEvents, null);
		this.getContentPane().add(jComboBoxEvents, null);

		this.getContentPane().add(jCalendar, null);
		
		
		BLFacade facade = MainGUI.getBusinessLogic();
		datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar.getDate());
		paintDaysWithEvents(jCalendar,datesWithEventsCurrentMonth);
		
		

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelEventDate.setBounds(40, 16, 140, 25);
		getContentPane().add(jLabelEventDate);
		jLabelQuestion.setText(ResourceBundle.getBundle("Etiquetas").getString("GalderaLista")); 
		jLabelQuestion.setBounds(290, 77, 103, 13);
		
		getContentPane().add(jLabelQuestion);
		jComboBoxQuestions.setModel(modelQuestions);
		jComboBoxQuestions.setBounds(275, 100, 250, 21);
		
		getContentPane().add(jComboBoxQuestions);
		
		jComboBoxEvents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				event = ((domain.Event) jComboBoxEvents.getSelectedItem());
				jComboBoxQuestions.removeAllItems();
				for(domain.Question question : businessLogic.findQuestion(event)) {
					if(question.getResult()==null)
						modelQuestions.addElement(question); 
				}
					
				
				if(modelQuestions.getSize()>0) {
					jButtonEmaitzaIpini.setEnabled(true);
				}else {
					jButtonEmaitzaIpini.setEnabled(false);
				}
				}
				

		});
		jLabelQuotes.setText(ResourceBundle.getBundle("Etiquetas").getString("KuotaLista")); 
		jLabelQuotes.setBounds(290, 131, 103, 13);
		
		getContentPane().add(jLabelQuotes);
		
		jComboBoxQuotes = new JComboBox();
		jComboBoxQuotes.setBounds(275, 158, 250, 21);
		jComboBoxQuotes.setModel(modelQuotes);
		
		getContentPane().add(jComboBoxQuotes);
		jButtonEmaitzaIpini.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Question que= (Question) jComboBoxQuestions.getSelectedItem();
				Quote q = (Quote)jComboBoxQuotes.getSelectedItem(); 
				try {
					businessLogic.EmaitzakIpini(q);
					jComboBoxQuestions.removeAllItems();
					for(domain.Question question : businessLogic.findQuestion(event)) {
						if(question.getResult()==null)
							modelQuestions.addElement(question); 
					}
					
					if(modelQuotes.getSize()>0) {
						jButtonEmaitzaIpini.setEnabled(true);
					}else {
						jButtonEmaitzaIpini.setEnabled(false);
					}
					
				} catch (EventNotFinished e1) {
					jLabelError.setVisible(true);
					jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("GertaeraEzAmaitu"));
					
				}
			}
		});
		jButtonEmaitzaIpini.setBounds(132, 228, 133, 30);
		
		getContentPane().add(jButtonEmaitzaIpini);
		jComboBoxQuestions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Question q = ((domain.Question) jComboBoxQuestions.getSelectedItem());
				jComboBoxQuotes.removeAllItems();
				for(domain.Quote quote : businessLogic.findQuote(q)) {
					modelQuotes.addElement(quote); 
				}
				
				if(jComboBoxQuotes.getSelectedItem()!=null) {
					jButtonEmaitzaIpini.setEnabled(true);
				}else {
					jButtonEmaitzaIpini.setEnabled(false);
				}
				
				
			}
		});
		

		
		
		// Code for JCalendar
		this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
				jLabelError.setVisible(false);
				if(modelQuotes.getSize()>0) {
					jButtonEmaitzaIpini.setEnabled(true);
				}else {
					jButtonEmaitzaIpini.setEnabled(false);
				}
//				this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
//					public void propertyChange(PropertyChangeEvent propertychangeevent) {
				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar.setLocale((Locale) propertychangeevent.getNewValue());
				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					System.out.println("calendarAnt: "+calendarAnt.getTime());
					System.out.println("calendarAct: "+calendarAct.getTime());
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar.getLocale());
					
					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);
					if (monthAct!=monthAnt) {
						if (monthAct==monthAnt+2) { 
							// Si en JCalendar estÃ¡ 30 de enero y se avanza al mes siguiente, devolverÃ­a 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este cÃ³digo se dejarÃ¡ como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt+1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}
						
						jCalendar.setCalendar(calendarAct);
						
						BLFacade facade = MainGUI.getBusinessLogic();

						datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar.getDate());
					}



					paintDaysWithEvents(jCalendar,datesWithEventsCurrentMonth);

					//	Date firstDay = UtilDate.trim(new Date(jCalendar.getCalendar().getTime().getTime()));
					Date firstDay = UtilDate.trim(calendarAct.getTime());

					try {
						BLFacade facade = MainGUI.getBusinessLogic();

						//Vector<domain.Event> events = facade.getEvents(firstDay);
						ExtendedIteratorEvents events = (ExtendedIteratorEvents) facade.getEvents(firstDay);
						
						
						//if (events.isEmpty()) {
						if(events.getEvents().isEmpty()) {
							jLabelListOfEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")
									+ ": " + dateformat1.format(calendarAct.getTime()));
							System.out.println("no events"); 
						
						} else {
							jLabelListOfEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events") + ": "
									+ dateformat1.format(calendarAct.getTime()));
						}
						jComboBoxEvents.removeAllItems();
						System.out.println("Events " + events);

						/*for (domain.Event ev : events) {
							modelEvents.addElement(ev);
						}*/
						while(events.hasNext()) {
							modelEvents.addElement(events.next());
						}
						jComboBoxEvents.repaint();
						
						if (modelQuotes.getSize() == 0)
							jButtonEmaitzaIpini.setEnabled(false);
						else
							jButtonEmaitzaIpini.setEnabled(true);

					} catch (Exception e1) {

						System.out.println(e1.getMessage());
					}

				}
			}
		});
	}

	
public static void paintDaysWithEvents(JCalendar jCalendar,Vector<Date> datesWithEventsCurrentMonth) {
		// For each day with events in current month, the background color for that day is changed.

		
		Calendar calendar = jCalendar.getCalendar();
		
		int month = calendar.get(Calendar.MONTH);
		int today=calendar.get(Calendar.DAY_OF_MONTH);
		int year=calendar.get(Calendar.YEAR);
		
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int offset = calendar.get(Calendar.DAY_OF_WEEK);

		if (Locale.getDefault().equals(new Locale("es")))
			offset += 4;
		else
			offset += 5;
		
		
	 	for (Date d:datesWithEventsCurrentMonth){

	 		calendar.setTime(d);
	 		System.out.println(d);
	 		

			
			// Obtain the component of the day in the panel of the DayChooser of the
			// JCalendar.
			// The component is located after the decorator buttons of "Sun", "Mon",... or
			// "Lun", "Mar"...,
			// the empty days before day 1 of month, and all the days previous to each day.
			// That number of components is calculated with "offset" and is different in
			// English and Spanish
//			    		  Component o=(Component) jCalendar.getDayChooser().getDayPanel().getComponent(i+offset);; 
			Component o = (Component) jCalendar.getDayChooser().getDayPanel()
					.getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
			o.setBackground(Color.CYAN);
	 	}
	 	
 			calendar.set(Calendar.DAY_OF_MONTH, today);
	 		calendar.set(Calendar.MONTH, month);
	 		calendar.set(Calendar.YEAR, year);

	 	
	}

	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

}
