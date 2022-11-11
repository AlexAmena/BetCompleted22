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
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Event;
import domain.Question;
import exceptions.QuoteAlreadyExist;
import iterator.ExtendedIteratorEvents;

public class KuotakIpiniGUI extends JFrame{
	
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
	private JLabel jLabelMsg = new JLabel();
	
	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();
	private final JLabel jLabelQuestion = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
	private final JComboBox<Question> jComboBoxQuestions = new JComboBox<Question>();
	DefaultComboBoxModel<Question> modelQuestions = new DefaultComboBoxModel<Question>();
	private final JLabel jLabelForecast = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel jLabelQuote = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
	private JTextField txtForecast;
	private final JTextField txtQuote = new JTextField();
	
	private domain.Event event;
	private final JLabel jLabelErrorForecast = new JLabel(); 
	private final JLabel jLabelErrorQuote = new JLabel(); 
	
	public KuotakIpiniGUI(Vector<domain.Event> v) {
		try {
			jbInit(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit(Vector<domain.Event> v) throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 370));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("KuotakIpini"));
		

		jComboBoxEvents.setModel(modelEvents);
		jComboBoxEvents.setBounds(new Rectangle(275, 47, 250, 20));
		jLabelListOfEvents.setBounds(new Rectangle(290, 18, 277, 20));

		jCalendar.setBounds(new Rectangle(40, 50, 225, 150));
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));
		jButtonClose.setBounds(new Rectangle(249, 275, 130, 30));
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});

		jLabelMsg.setBounds(new Rectangle(275, 182, 305, 20));
		jLabelMsg.setForeground(Color.red);

		this.getContentPane().add(jLabelMsg, null);

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
		jLabelQuestion.setText(ResourceBundle.getBundle("Etiquetas").getString("GalderaLista")); //$NON-NLS-1$ //$NON-NLS-2$
		jLabelQuestion.setBounds(290, 119, 103, 13);
		
		getContentPane().add(jLabelQuestion);
		jComboBoxQuestions.setModel(modelQuestions);
		jComboBoxQuestions.setBounds(275, 142, 250, 21);
		
		getContentPane().add(jComboBoxQuestions);
		jLabelForecast.setText(ResourceBundle.getBundle("Etiquetas").getString("Forecast")); //$NON-NLS-1$ //$NON-NLS-2$
		jLabelForecast.setBounds(40, 210, 58, 13);
		
		getContentPane().add(jLabelForecast);
		jLabelQuote.setText(ResourceBundle.getBundle("Etiquetas").getString("Quote")); //$NON-NLS-1$ //$NON-NLS-2$
		jLabelQuote.setBounds(40, 242, 58, 13);
		
		getContentPane().add(jLabelQuote);
		
		txtForecast = new JTextField();
		txtForecast.setBounds(105, 210, 420, 19);
		getContentPane().add(txtForecast);
		txtForecast.setColumns(10);
		
		
		txtQuote.setBounds(105, 239, 422, 19);
		txtQuote.setColumns(10);
		
		getContentPane().add(txtQuote);
		
		JButton jButtonCreate = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Create")); //$NON-NLS-1$ //$NON-NLS-2$
		jButtonCreate.setBounds(89, 275, 150, 30);
		getContentPane().add(jButtonCreate);
		jLabelErrorForecast.setForeground(Color.RED);
		jLabelErrorForecast.setBounds(275, 189, 250, 13);
		jLabelErrorForecast.setVisible(false);
		
		getContentPane().add(jLabelErrorForecast);
		jLabelErrorQuote.setForeground(Color.RED);
		jLabelErrorQuote.setBounds(389, 268, 191, 13);
		jLabelErrorQuote.setVisible(false);
		
		jComboBoxEvents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				event = ((domain.Event) jComboBoxEvents.getSelectedItem());
				jComboBoxQuestions.removeAllItems();
				for(domain.Question question : businessLogic.findQuestion(event))
					modelQuestions.addElement(question); 
				
				if(jComboBoxQuestions.getSelectedItem()!=null) {
					jButtonCreate.setEnabled(true);
				}else {
					jButtonCreate.setEnabled(false);
				}
				}
				

		});
		
		
		
		getContentPane().add(jLabelErrorQuote);
		jButtonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Question q = (Question) jComboBoxQuestions.getSelectedItem(); 
				
				jLabelErrorQuote.setVisible(false);
				jLabelErrorForecast.setVisible(false);
				
				if(txtForecast.getText().isEmpty()) {
					jLabelErrorForecast.setVisible(true);
					jLabelErrorForecast.setText(ResourceBundle.getBundle("Etiquetas").getString("ForecastError"));
				}else if(txtQuote.getText().isEmpty()) {
					jLabelErrorQuote.setVisible(true);
					jLabelErrorQuote.setText(ResourceBundle.getBundle("Etiquetas").getString("QuoteError"));
				}else {
					String forecast = txtForecast.getText();
					try {
						Double quote = Double.parseDouble(txtQuote.getText());
						if(quote>0.0) {
							try {
								businessLogic.storeQuote(forecast, quote, q);
								jLabelErrorForecast.setVisible(true);
								jLabelErrorForecast.setText(ResourceBundle.getBundle("Etiquetas").getString("QuoCorrect"));
							} catch (QuoteAlreadyExist e1) {
								// TODO Auto-generated catch block
								jLabelErrorForecast.setVisible(true);
								jLabelErrorForecast.setText(ResourceBundle.getBundle("Etiquetas").getString("QuoteExist"));
							}
						}else {
							jLabelErrorQuote.setVisible(true);
							jLabelErrorQuote.setText(ResourceBundle.getBundle("Etiquetas").getString("Quote") +" " + ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
						}
					} catch (NumberFormatException error){
						jLabelErrorQuote.setVisible(true);
						jLabelErrorQuote.setText(ResourceBundle.getBundle("Etiquetas").getString("Quote") +" " + ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
					}
					
				}
			}
		});
		

		
		
		// Code for JCalendar
		this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
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
						
						//if (events.size() == 0)
						if(events.getEvents().isEmpty())
							jButtonCreate.setEnabled(false);
						else
							jButtonCreate.setEnabled(true);

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
