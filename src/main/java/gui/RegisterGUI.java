package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;


public class RegisterGUI extends JFrame {
	
	private JPanel contentPane= null;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JLabel izenaSartu = new JLabel(); 
	private JLabel passSartu = new JLabel(); 
	private JLabel kontuaSartu = new JLabel(); 
	private BLFacade businessLogic = MainGUI.getBusinessLogic();
	private JFrame thisw;
	private JTextField txtBank;

	public RegisterGUI()
	{
		try
		{
			jbInit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}

	
	private void jbInit() throws Exception
	{
		thisw=this;
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Register"));
		getContentPane().setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(263, 114, 224, 51);
		txtUsername.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsername.setFont(new Font("Times New Roman", Font.BOLD, 20));
		//txtUsername.setText(ResourceBundle.getBundle("Etiquetas").getString("Username"));
		getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(263, 208, 224, 51);
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		//txtPassword.setText(ResourceBundle.getBundle("Etiquetas").getString("Password")); //$NON-NLS-1$ //$NON-NLS-2$
		txtPassword.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtPassword.setColumns(10);
		getContentPane().add(txtPassword);
		
		JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Username")); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel_1.setBounds(44, 123, 190, 30);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Password")); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel_2.setBounds(44, 213, 190, 40);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblNewLabel_2);
		
		izenaSartu = new JLabel("");
		izenaSartu.setBounds(111, 91, 540, 13);
		izenaSartu.setVisible(false);
		izenaSartu.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(izenaSartu);
		
		passSartu = new JLabel(""); //$NON-NLS-1$ //$NON-NLS-2$
		passSartu.setBounds(263, 185, 224, 13);
		passSartu.setVisible(false);
		passSartu.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(passSartu);
		
		JLabel lblNewLabel_3 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BankAccount")); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_3.setBounds(44, 289, 209, 30);
		getContentPane().add(lblNewLabel_3);
		
		txtBank = new JTextField();
		txtBank.setHorizontalAlignment(SwingConstants.CENTER);
		txtBank.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtBank.setBounds(263, 289, 224, 51);
		getContentPane().add(txtBank);
		txtBank.setColumns(10);
		
		kontuaSartu = new JLabel(""); //$NON-NLS-1$ //$NON-NLS-2$
		kontuaSartu.setVisible(false); 
		kontuaSartu.setBounds(263, 266, 224, 13);
		getContentPane().add(kontuaSartu);
		
		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Register")); //$NON-NLS-1$ //$NON-NLS-2$
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String izena = txtUsername.getText(); 
				String password = txtPassword.getText();
				String bankAccount = txtBank.getText(); 
				
				if(izena.isEmpty()) {
					izenaSartu.setVisible(true);
					izenaSartu.setText(ResourceBundle.getBundle("Etiquetas").getString("UsernameError1"));					
				}else if(password.isEmpty()) {
					passSartu.setVisible(true);
					passSartu.setText(ResourceBundle.getBundle("Etiquetas").getString("PasswordError")); 
					
				}else if (bankAccount.isEmpty()) {
					kontuaSartu.setVisible(true); 
					kontuaSartu.setText(ResourceBundle.getBundle("Etiquetas").getString("BankAccountError")); 
					
				}else{
					izenaSartu.setVisible(false);
					passSartu.setVisible(false);
					kontuaSartu.setVisible(false);
					
					if(businessLogic.isRegister(izena) == false) {
						try {
							Integer i = Integer.parseInt(bankAccount);
							businessLogic.storeRegistered(izena, password, i);
						}catch (NumberFormatException ex) {
							//System.out.println(ex.getMessage());
						}
						
						JFrame log= new LoginGUI();
						thisw.setVisible(false);
						log.setVisible(true);
						
					}else { 
						izenaSartu.setVisible(true);
						izenaSartu.setText(ResourceBundle.getBundle("Etiquetas").getString("UsernameError3"));
					}
				}
			}
			
		});
		btnNewButton.setBounds(275, 366, 123, 66);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel4 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Register")); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel4.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel4.setBounds(263, 43, 224, 38);
		getContentPane().add(lblNewLabel4);

	}
}
