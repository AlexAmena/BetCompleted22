package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

/**
 * @author Software Engineering teachers
 */
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;
import domain.Event;
import domain.User;


public class RegisteredGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton jButtonQueryQueries = null;

    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	protected JLabel jLabelSelectOption;
	private JButton jButtonDiruaSartu;
	private User user; 
	private JButton jButtonApustuaEgin;
	private JButton jButtonApustuaEzabatu;
	private JButton jButtonMugimenduakBistaratu;
	private JButton jButtonDesLogin;
	private JFrame thisw;
	private JButton btnNewButton;
	private JButton btnRank;
	private JButton btnDestacados;
	
	/**
	 * This is the default constructor
	 */
	public RegisteredGUI(User u) {
		super();
		user = u; 
		thisw=this;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(650, 600);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("RegisteredTitle"));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setBackground(Color.WHITE);
			jContentPane.setLayout(null);
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getBoton3());
			jContentPane.add(getJButtonDiruaSartu());
			jContentPane.add(getJButtonApustuaEgin());
			jContentPane.add(getJButtonApustuaEzabatu());
			jContentPane.add(getJButtonMugimenduakBistaratu());
			jContentPane.add(getJButtonDesLogin());
			jContentPane.add(getBtnNewButton());
			jContentPane.add(getBtnRank());
			jContentPane.add(getBtnDestacados());
			
			JButton btnIkusiApostuak = new JButton(ResourceBundle.getBundle("Etiquetas").getString("RegisteredGUI.btnIkusiApostuak.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnIkusiApostuak.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					JFrame a = new ApustuakIkusiGUI((new JTable));//HAU ZUZENTZEKOD AGO

					a.setVisible(true);
				}
			});
			btnIkusiApostuak.setForeground(Color.DARK_GRAY);
			btnIkusiApostuak.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnIkusiApostuak.setBackground(Color.PINK);
			btnIkusiApostuak.setBounds(10, 484, 282, 68);
			jContentPane.add(btnIkusiApostuak);
		}
		return jContentPane;
	}
	
	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (jButtonQueryQueries == null) {
			jButtonQueryQueries = new JButton();
			jButtonQueryQueries.setFont(new Font("Tahoma", Font.PLAIN, 16));
			jButtonQueryQueries.setForeground(Color.DARK_GRAY);
			jButtonQueryQueries.setBackground(Color.PINK);
			jButtonQueryQueries.setOpaque(true);
			jButtonQueryQueries.setBounds(10, 118, 282, 68);
			jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
			jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new FindQuestionsGUI();

					a.setVisible(true);
				}
			});
		}
		return jButtonQueryQueries;
	}
	

	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			jLabelSelectOption.setOpaque(true);
			jLabelSelectOption.setBackground(Color.PINK);
			jLabelSelectOption.setBounds(161, 30, 305, 58);
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 20));
			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
	}
	
	
	
	
	private JButton getJButtonDiruaSartu() {
		if (jButtonDiruaSartu == null) {
			jButtonDiruaSartu = new JButton();
			jButtonDiruaSartu.setFont(new Font("Tahoma", Font.PLAIN, 16));
			jButtonDiruaSartu.setForeground(Color.DARK_GRAY);
			jButtonDiruaSartu.setBackground(Color.PINK);
			jButtonDiruaSartu.setText(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartu")); //$NON-NLS-1$ //$NON-NLS-2$
			jButtonDiruaSartu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new DiruaSartuGUI(user); 
					a.setVisible(true);
				}
			});
			jButtonDiruaSartu.setBounds(329, 118, 297, 68);
		}
		return jButtonDiruaSartu;
	}
	private JButton getJButtonApustuaEgin() {
		if (jButtonApustuaEgin == null) {
			jButtonApustuaEgin = new JButton(); 
			jButtonApustuaEgin.setFont(new Font("Tahoma", Font.PLAIN, 16));
			jButtonApustuaEgin.setBackground(Color.PINK);
			jButtonApustuaEgin.setForeground(Color.DARK_GRAY);
			jButtonApustuaEgin.setText(ResourceBundle.getBundle("Etiquetas").getString("ApustuaEgin")); //$NON-NLS-1$ //$NON-NLS-2$
			jButtonApustuaEgin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new ApustuaEginGUI(new Vector<Event>(),user, null); 
					a.setVisible(true);
				}
			});
			jButtonApustuaEgin.setBounds(10, 211, 282, 68);
		}
		return jButtonApustuaEgin;
	}
	private JButton getJButtonApustuaEzabatu() {
		if (jButtonApustuaEzabatu == null) {
			jButtonApustuaEzabatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ApustuaEzabatu"));
			jButtonApustuaEzabatu.setFont(new Font("Tahoma", Font.PLAIN, 16));
			jButtonApustuaEzabatu.setForeground(Color.DARK_GRAY);
			jButtonApustuaEzabatu.setBackground(Color.PINK);
			jButtonApustuaEzabatu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new ApustuakEzabatuGUI(user); 
					a.setVisible(true);
				}
			});
			jButtonApustuaEzabatu.setBounds(327, 211, 299, 68);
		}
		return jButtonApustuaEzabatu;
	}
	private JButton getJButtonMugimenduakBistaratu() {
		if (jButtonMugimenduakBistaratu == null) {
			jButtonMugimenduakBistaratu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MugimenduakBistaratu")); //$NON-NLS-1$ //$NON-NLS-2$
			jButtonMugimenduakBistaratu.setFont(new Font("Tahoma", Font.PLAIN, 16));
			jButtonMugimenduakBistaratu.setBackground(Color.PINK);
			jButtonMugimenduakBistaratu.setForeground(Color.DARK_GRAY);
			jButtonMugimenduakBistaratu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new MugimenduakBistaratuGUI(user); 
					a.setVisible(true);
				}
			});
			jButtonMugimenduakBistaratu.setBounds(10, 302, 282, 68);
		}
		return jButtonMugimenduakBistaratu;
	}
	private JButton getJButtonDesLogin() {
		if (jButtonDesLogin == null) {
			jButtonDesLogin = new JButton(ResourceBundle.getBundle("Etiquetas").getString("DesLogin")); //$NON-NLS-1$ //$NON-NLS-2$
			jButtonDesLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
			jButtonDesLogin.setForeground(Color.WHITE);
			jButtonDesLogin.setBackground(Color.DARK_GRAY);
			jButtonDesLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new MainUserGUI();
					a.setVisible(true);
					thisw.setVisible(false);
				}
			});
			jButtonDesLogin.setBounds(467, 484, 159, 49);
		}
		return jButtonDesLogin;
	}
	

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Mezuak")); //$NON-NLS-1$ //$NON-NLS-2$
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnNewButton.setBackground(Color.PINK);
			btnNewButton.setForeground(Color.DARK_GRAY);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new SarreraOntziaGUI(user);
					a.setVisible(true);
				}
			});
			btnNewButton.setBounds(10, 391, 282, 68);
		}
		return btnNewButton;
	}
	private JButton getBtnRank() {
		if (btnRank == null) {
			btnRank = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Ranking")); //$NON-NLS-1$ //$NON-NLS-2$
			btnRank.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnRank.setBackground(Color.PINK);
			btnRank.setForeground(Color.DARK_GRAY);
			btnRank.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new JarraituGUI(user);
					a.setVisible(true);
				}
			});
			btnRank.setBounds(327, 302, 299, 68);
		}
		return btnRank;
	}
	private JButton getBtnDestacados() {
		if (btnDestacados == null) {
			btnDestacados = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Destacados")); //$NON-NLS-1$ //$NON-NLS-2$
			btnDestacados.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnDestacados.setForeground(Color.DARK_GRAY);
			btnDestacados.setBackground(Color.PINK);
			btnDestacados.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a =new DestacadosGUI(user);
					a.setVisible(true);
				}
			});
			btnDestacados.setBounds(327, 391, 299, 68);
		}
		return btnDestacados;
	}
} // @jve:decl-index=0:visual-constraint="0,0"

