package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;
import domain.Elkarrizketa;
import domain.Message;
import domain.User;

public class MezuakBidaliGUI extends JFrame{
	private BLFacade businessLogic = MainGUI.getBusinessLogic();

	private static final long serialVersionUID = 1L;
	
	private JLabel lblZureMezua;

	private JLabel lblHartzailea;

	private JLabel lblAsunto;
	private JTextField txtHartzailea;
	private JTextField txtAsunto;
	private JTextField txtTestua;

	private JButton btnClose;

	private JButton btnEnviar;
	private Elkarrizketa elkarrizketa;
	private User user;
	private JFrame thisw;
	private JLabel lblError;
	
	public MezuakBidaliGUI(User u, Elkarrizketa elka, User igorlea) {
		thisw=this;
		user=u;
		elkarrizketa=elka;
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(500, 300));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MezuakBidali"));
		
		lblZureMezua = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ZureMezuak"));
		lblZureMezua.setBackground(Color.PINK);
		lblZureMezua.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblZureMezua.setOpaque(true);
		lblZureMezua.setHorizontalAlignment(SwingConstants.CENTER);
		lblZureMezua.setBounds(143, 10, 158, 19);
		getContentPane().add(lblZureMezua);
		
		lblHartzailea = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Hartzailea"));
		lblHartzailea.setHorizontalAlignment(SwingConstants.CENTER);
		lblHartzailea.setBackground(Color.PINK);
		lblHartzailea.setOpaque(true);
		lblHartzailea.setBounds(10, 41, 65, 13);
		getContentPane().add(lblHartzailea);
		
		lblAsunto = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Asunto"));
		lblAsunto.setHorizontalAlignment(SwingConstants.CENTER);
		lblAsunto.setBackground(Color.PINK);
		lblAsunto.setOpaque(true);
		lblAsunto.setBounds(10, 67, 65, 13);
		getContentPane().add(lblAsunto);
		
		txtHartzailea = new JTextField();
		txtHartzailea.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		txtHartzailea.setBounds(83, 38, 354, 19);
		getContentPane().add(txtHartzailea);
		txtHartzailea.setColumns(10);
		
		txtAsunto = new JTextField();
		txtAsunto.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		txtAsunto.setBounds(83, 64, 354, 19);
		getContentPane().add(txtAsunto);
		txtAsunto.setColumns(10);
		
		txtTestua = new JTextField();
		txtTestua.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		txtTestua.setBounds(83, 90, 354, 129);
		getContentPane().add(txtTestua);
		txtTestua.setColumns(10);
		
		lblError = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setBounds(143, 229, 201, 21);
		getContentPane().add(lblError);
		lblError.setVisible(false);
		
		btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		btnClose.setBackground(Color.DARK_GRAY);
		btnClose.setForeground(Color.WHITE);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});
		btnClose.setBounds(365, 229, 85, 21);
		getContentPane().add(btnClose);
		
		
		btnEnviar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Enviar"));
		btnEnviar.setForeground(Color.DARK_GRAY);
		btnEnviar.setBackground(Color.PINK);
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!user.getUsername().equals(txtHartzailea.getText())) {
					Vector <String>osagaiak=new Vector<String>();
					osagaiak.add(txtHartzailea.getText());
					osagaiak.add(txtAsunto.getText());
					osagaiak.add(txtTestua.getText());
					Boolean ema= businessLogic.mezuaBidali(user, elkarrizketa,osagaiak);
					if(ema) {
						thisw.setVisible(false);
						JFrame a = new SarreraOntziaGUI(user);
						a.setVisible(true);
					}else {
						lblError.setVisible(true);
						lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("HartzError"));
					}
				}else {
					lblError.setVisible(true);
					lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorMessage"));
				}
				
			}
		});
		btnEnviar.setBounds(27, 229, 85, 21);
		getContentPane().add(btnEnviar);
		
		if(igorlea!=null) {
			txtHartzailea.setText(igorlea.getUsername());
			txtAsunto.setText(elkarrizketa.getTitulo());
		}
		
		
	}
	
	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

}
