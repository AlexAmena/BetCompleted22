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
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Transaction;
import domain.User;
import exceptions.ApustuaAlreadyExist;
import exceptions.EventFinished;
import iterator.ExtendedIteratorEvents;

import java.awt.Font;

public class ApustuaEginGUI extends JFrame{

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
	private final JTextField textFieldDiruKop = new JTextField();
	private final JButton jButtonCreate = new JButton();

	private JComboBox jComboBoxQuotes;
	DefaultComboBoxModel<Quote> modelQuotes = new DefaultComboBoxModel<Quote>();
	
	private User user; 
	private final JLabel lblError = new JLabel("Errorea");

	private JScrollPane scrollBar;

	private JList<Quote> list;
	private DefaultListModel<Quote> quoteList = new DefaultListModel<Quote>();
	
	private final JButton btnApustuaGehitu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ApustuaGehitu"));
	
	private Double maxMinBet=0.0;
	
	private Vector<Quote> quoteVec = new Vector<Quote>();

	private JLabel lblApustua;

	private JButton jButtonFinish;
	
	
	public ApustuaEginGUI(Vector<domain.Event> v, User u, Quote q) {
		user = u; 
		textFieldDiruKop.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldDiruKop.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldDiruKop.setText(""); 
		textFieldDiruKop.setBounds(210, 313, 394, 37);
		textFieldDiruKop.setColumns(10);
		try {
			jbInit(v, q);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit(Vector<domain.Event> v, Quote q) throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(1250, 470));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("ApustuaEgin"));
		jComboBoxEvents.setFont(new Font("Tahoma", Font.PLAIN, 16));
		

		jComboBoxEvents.setModel(modelEvents);
		jComboBoxEvents.setBounds(new Rectangle(330, 80, 274, 34));
		jLabelListOfEvents.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jLabelListOfEvents.setBounds(new Rectangle(330, 50, 286, 20));

		jCalendar.setBounds(new Rectangle(10, 62, 286, 206));
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));
		jButtonClose.setForeground(Color.WHITE);
		jButtonClose.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jButtonClose.setBackground(Color.DARK_GRAY);
		jButtonClose.setBounds(new Rectangle(456, 360, 148, 51));
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
		jLabelEventDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelEventDate.setBounds(22, 27, 274, 25);
		getContentPane().add(jLabelEventDate);
		jLabelQuestion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jLabelQuestion.setText(ResourceBundle.getBundle("Etiquetas").getString("GalderaLista")); //$NON-NLS-1$ //$NON-NLS-2$
		jLabelQuestion.setBounds(330, 124, 127, 29);
		
		getContentPane().add(jLabelQuestion);
		jComboBoxQuestions.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jComboBoxQuestions.setModel(modelQuestions);
		jComboBoxQuestions.setBounds(330, 163, 274, 34);
		
		getContentPane().add(jComboBoxQuestions);
		
		jComboBoxEvents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				event = ((domain.Event) jComboBoxEvents.getSelectedItem());
				jComboBoxQuestions.removeAllItems();
				for(domain.Question question : businessLogic.findQuestion(event))
					modelQuestions.addElement(question); 
				}
		});
		jLabelQuotes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jLabelQuotes.setText(ResourceBundle.getBundle("Etiquetas").getString("KuotaLista")); 
		jLabelQuotes.setBounds(330, 207, 113, 18);
		
		getContentPane().add(jLabelQuotes);
		
		jComboBoxQuotes = new JComboBox();
		jComboBoxQuotes.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jComboBoxQuotes.setBounds(330, 235, 274, 34);
		jComboBoxQuotes.setModel(modelQuotes);
		
		getContentPane().add(jComboBoxQuotes);
		jComboBoxQuestions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Question q = ((domain.Question) jComboBoxQuestions.getSelectedItem());
				jComboBoxQuotes.removeAllItems();
				for(domain.Quote quote : businessLogic.findQuote(q)) {
					modelQuotes.addElement(quote); 
				}
				if(modelQuotes.getSize()==0) {
					btnApustuaGehitu.setEnabled(false);
				}else {
					btnApustuaGehitu.setEnabled(true);
				}
			}
		});
		
		
		JLabel jLabelDiruKopurua = new JLabel(); 
		jLabelDiruKopurua.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jLabelDiruKopurua.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabelDiruKopurua.setText(ResourceBundle.getBundle("Etiquetas").getString("DiruKop")); 
		jLabelDiruKopurua.setBounds(10, 313, 178, 37);
		getContentPane().add(jLabelDiruKopurua);
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		lblError.setVisible(false);
		
		jButtonFinish = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ApustuAmaitu"));
		jButtonFinish.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jButtonFinish.setBackground(Color.PINK);
		jButtonFinish.setForeground(Color.DARK_GRAY);
		
		getContentPane().add(textFieldDiruKop);
		textFieldDiruKop.setVisible(false);
		jLabelDiruKopurua.setVisible(false);
		jButtonCreate.setForeground(Color.DARK_GRAY);
		jButtonCreate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jButtonCreate.setBackground(new Color(255, 105, 180));
		
		jButtonCreate.setVisible(false);
		jButtonCreate.setText(ResourceBundle.getBundle("Etiquetas").getString("ApustuaEgin"));
		jButtonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textua = textFieldDiruKop.getText().trim(); 
				Double zenb; 
				try {
					zenb = Double.parseDouble(textua);
					if(zenb>0.0) {
						if(zenb>=maxMinBet) {
							Boolean b = businessLogic.ApustuaEgin(user, quoteVec, zenb, -1);
							quoteList= new DefaultListModel<Quote>();
							quoteVec= new Vector<Quote>();
							list.setModel(quoteList);
							if(b) {
								lblError.setVisible(true); 
								lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("ApustuaCorrect"));
								jButtonFinish.setEnabled(false);
								btnApustuaGehitu.setVisible(true);
								jButtonCreate.setVisible(false);
								textFieldDiruKop.setVisible(false);
								jLabelDiruKopurua.setVisible(false);
							}else {
								lblError.setVisible(true); 
								lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("ApustuaError1")); 
							}							
						}else {
							lblError.setVisible(true); 
							lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("ApustuaError4")+ " " + maxMinBet); 
						}
					}else {
						lblError.setVisible(true); 
						lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber")); 
					}
					
				}catch(NumberFormatException e1) {
					lblError.setVisible(true); 
					lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
				}
				
			}
		});
		jButtonCreate.setBounds(40, 360, 148, 51);
		
		getContentPane().add(jButtonCreate);
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setBounds(40, 283, 564, 20);
		
		getContentPane().add(lblError);
		
		list = new JList<Quote>();
		list.setFont(new Font("Tahoma", Font.PLAIN, 18));
		list.setModel(quoteList);
		list.setBounds(10, 0, 392, 256);
		getContentPane().add(list);
		
		scrollBar = new JScrollPane(list);
		scrollBar.setBounds(626, 47, 589, 364);
		getContentPane().add(scrollBar);
		
		jButtonFinish.setEnabled(false);
		jButtonFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jLabelDiruKopurua.setVisible(true);
				textFieldDiruKop.setVisible(true);
				btnApustuaGehitu.setVisible(false);
				jButtonCreate.setVisible(true);
				jButtonCreate.setEnabled(true);
				jButtonFinish.setEnabled(false);
				textFieldDiruKop.setVisible(true);
				jLabelDiruKopurua.setVisible(true);
				textFieldDiruKop.setText("");
			}
		});
		jButtonFinish.setBounds(282, 360, 148, 51);
		getContentPane().add(jButtonFinish);
		
		lblApustua = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ZureApustua"));
		lblApustua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblApustua.setHorizontalAlignment(SwingConstants.CENTER);
		lblApustua.setBounds(626, 18, 589, 18);
		getContentPane().add(lblApustua);
		btnApustuaGehitu.setForeground(Color.DARK_GRAY);
		btnApustuaGehitu.setBackground(new Color(224, 255, 255));
		btnApustuaGehitu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnApustuaGehitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Quote q = (Quote)jComboBoxQuotes.getSelectedItem();
				boolean comp = false;
				
				if(new Date().compareTo(businessLogic.findEvent(q).getEventDate())<0) {
					for(Quote quo: quoteVec) {
						if(businessLogic.findQuestionFromQuote(quo).getQuestionNumber().equals(businessLogic.findQuestionFromQuote(q).getQuestionNumber())) {
							comp=true;
						}
					}
					if(!comp) {
						quoteList.addElement(q);
						quoteVec.add(q);
						if(maxMinBet<businessLogic.findQuestionFromQuote(q).getBetMinimum()) {
							maxMinBet=businessLogic.findQuestionFromQuote(q).getBetMinimum();
						}
						jButtonFinish.setEnabled(true);
						lblError.setVisible(false);
					}else {
						lblError.setVisible(true);
						lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("ApustuaError2"));
					}
				}else {
					lblError.setVisible(true);
					lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("ApustuaError3"));
				}
			}
		});
		btnApustuaGehitu.setBounds(117, 360, 148, 51);
		
		getContentPane().add(btnApustuaGehitu);
		
		if(q!=null) {
			quoteList.addElement(q);
			quoteVec.add(q);
			jButtonFinish.setEnabled(true);
			btnApustuaGehitu.setEnabled(true);
		}
		
		
		
		// Code for JCalendar
		this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
				lblError.setVisible(false);
				if(modelQuotes.getSize()>0) {
					
					btnApustuaGehitu.setEnabled(true);
				}else {
					
					btnApustuaGehitu.setEnabled(false);
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
						
						if (events.getEvents().isEmpty()) {
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
						
						//if (events.isEmpty()) {
						if(events.getEvents().isEmpty()) {
							btnApustuaGehitu.setEnabled(false);
						}else {
							
							btnApustuaGehitu.setEnabled(true);
						}

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
