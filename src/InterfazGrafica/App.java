package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BDControl.DatabaseManager;
import Classes.Persona;
import DAO.DAOPersona;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JToggleButton;
import javax.swing.JPasswordField;

public class App extends JFrame {

	private Persona usuario;
	private JPanel contentPane;
	private Connection connection = DatabaseManager.getConnection();
	private JPanel panelLogin;
	private JLabel lblLogin;
	private JTextField textFieldMailLogin;
	private JPasswordField passwordFieldPasswordLogin;
	private JButton btnLogin;
	private JPanel loginContainer;
	private JPanel panelSignin;
	private JLabel lblMailLogin;
	private JLabel lblPasswordLogin;
	private JPanel signinContainer;
	private JLabel lblRegistrarse;
	private JTextField textFieldDocumentoSignin;
	private JTextField textFieldApellido1Signin;
	private JButton btnSignin;
	private JLabel lblDocumentoSignin;
	private JLabel lblApellido1Signin;
	private JLabel lblApellido2Signin;
	private JTextField textFieldApellido2Signin;
	private JLabel lblNombre1Signin;
	private JTextField textFieldNombre1Signin;
	private JLabel lblNombre2Signin;
	private JTextField textFieldNombre2Signin;
	private JLabel lblPassword1Signin;
	private JTextField passwordFieldPassword1Signin;
	private JLabel lblPassword2Signin;
	private JPasswordField passwordFieldPassword2Signin;
	private JLabel lblNacimientoSignin;
	private JLabel lblMailSignin;
	private JTextField textFieldMailSignin;
	private JDateChooser dateChooserNacimientoSignin;
	private JLabel lblErrorPassword;
	private JLabel lblErrorCampos;
	private JLabel lblErrorInsert;
	private JLabel lblCorrectInsert;
	private JLabel lblErrorCamposLogin;
	private JLabel lblErrorLogin;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public App() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		panelLogin = new JPanel();
		panelLogin.setBackground(SystemColor.inactiveCaption);
		panelLogin.setBounds(10, 11, 294, 454);
		contentPane.add(panelLogin);
		panelLogin.setLayout(null);
		
		
		loginContainer = new JPanel();
		loginContainer.setBackground(SystemColor.inactiveCaption);
		loginContainer.setBounds(57, 128, 179, 197);
		panelLogin.add(loginContainer);
		loginContainer.setLayout(null);
		
		lblLogin = new JLabel("Iniciar sesión");
		lblLogin.setBounds(38, 11, 102, 22);
		loginContainer.add(lblLogin);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		
		textFieldMailLogin = new JTextField();
		textFieldMailLogin.setBounds(13, 58, 152, 20);
		loginContainer.add(textFieldMailLogin);
		textFieldMailLogin.setColumns(10);
		
		passwordFieldPasswordLogin = new JPasswordField();
		passwordFieldPasswordLogin.setBounds(13, 112, 152, 20);
		loginContainer.add(passwordFieldPasswordLogin);
		passwordFieldPasswordLogin.setColumns(10);
		
		btnLogin = new JButton("Ingresar");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lblErrorLogin.setVisible(false);
				lblErrorCamposLogin.setVisible(false);
				boolean continuar = true; 
				
				String mail = textFieldMailLogin.getText();
				String clave = passwordFieldPasswordLogin.getText();
				
				if(mail.equals("")) {
					continuar = false;
					lblErrorCamposLogin.setVisible(true);
				}
				if(clave.equals("") && continuar) {
					continuar = false;
					lblErrorCamposLogin.setVisible(true);
				}
				if(continuar) {
					if(DAOPersona.comprobarLogin(mail, clave)) {
						panelLogin.setVisible(false);
						panelSignin.setVisible(false);
					}else {
						lblErrorLogin.setVisible(true);
					}
				}				
			}
		});
		btnLogin.setBackground(SystemColor.control);
		btnLogin.setBounds(42, 161, 95, 23);
		loginContainer.add(btnLogin);
		
		lblMailLogin = new JLabel("Mail");
		lblMailLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblMailLogin.setBounds(52, 44, 74, 14);
		loginContainer.add(lblMailLogin);
		
		lblPasswordLogin = new JLabel("Constraseña");
		lblPasswordLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasswordLogin.setBounds(52, 98, 74, 14);
		loginContainer.add(lblPasswordLogin);
		
		lblErrorCamposLogin = new JLabel("Debe llenar todos los campos");
		lblErrorCamposLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblErrorCamposLogin.setForeground(new Color(255, 0, 0));
		lblErrorCamposLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorCamposLogin.setBounds(30, 336, 233, 14);
		lblErrorCamposLogin.setVisible(false);
		panelLogin.add(lblErrorCamposLogin);
		
		lblErrorLogin = new JLabel("Error de autentificación");
		lblErrorLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblErrorLogin.setForeground(new Color(255, 0, 0));
		lblErrorLogin.setBounds(20, 336, 253, 14);
		lblErrorLogin.setVisible(false);
		panelLogin.add(lblErrorLogin);
		
		panelSignin = new JPanel();
		panelSignin.setBackground(SystemColor.inactiveCaption);
		panelSignin.setBounds(314, 11, 403, 454);
		contentPane.add(panelSignin);
		panelSignin.setLayout(null);
		
		signinContainer = new JPanel();
		signinContainer.setLayout(null);
		signinContainer.setBackground(SystemColor.inactiveCaption);
		signinContainer.setBounds(28, 65, 347, 323);
		panelSignin.add(signinContainer);
				
		lblRegistrarse = new JLabel("Registrarse");
		lblRegistrarse.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRegistrarse.setBounds(122, 11, 102, 22);
		signinContainer.add(lblRegistrarse);
		
		textFieldDocumentoSignin = new JTextField();
		textFieldDocumentoSignin.setColumns(10);
		textFieldDocumentoSignin.setBounds(13, 58, 152, 20);
		signinContainer.add(textFieldDocumentoSignin);
		
		btnSignin = new JButton("Enviar");
		btnSignin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean continuar = true;
				lblErrorPassword.setVisible(false);
				lblErrorCampos.setVisible(false);
				lblErrorInsert.setVisible(false);
				lblCorrectInsert.setVisible(false);
				
				String documento = textFieldDocumentoSignin.getText();
				String apellido1 = textFieldApellido1Signin.getText();
				String apellido2 = textFieldApellido2Signin.getText();
				String nombre1 = textFieldNombre1Signin.getText();
				String nombre2 = textFieldNombre2Signin.getText();
				String mail = textFieldMailSignin.getText();
				String clave1 = passwordFieldPassword1Signin.getText();
				String clave2 = passwordFieldPassword2Signin.getText();
				
				if(dateChooserNacimientoSignin.getDate() != null) {
					
					Date fechaNac = new java.sql.Date((dateChooserNacimientoSignin.getDate().getTime()));
					if(documento.equals("")) {
						lblErrorCampos.setVisible(true);
						continuar = false;
					}
					if(apellido1.equals("") && continuar) {
						lblErrorCampos.setVisible(true);
						continuar = false;
					}
					if(nombre1.equals("") && continuar) {
						lblErrorCampos.setVisible(true);
						continuar = false;
					}
					if(clave1.equals("") && continuar) {
						lblErrorCampos.setVisible(true);
						continuar = false;
					}
					if(clave2.equals("") && continuar) {
						lblErrorCampos.setVisible(true);
						continuar = false;
					}
					if(mail.equals("") && continuar) {
						lblErrorCampos.setVisible(true);
						continuar = false;
					}
					
					if(!(clave1.equals(clave2)) && continuar) {
						lblErrorPassword.setVisible(true);
						continuar = false;
					}
					
					if(continuar) {
						Persona p = new Persona(documento,apellido1,apellido2,nombre1,nombre2,fechaNac,clave1,mail);
						if(DAOPersona.insertarPersona(p)) {
							lblCorrectInsert.setVisible(true);
						}else {
							lblErrorInsert.setVisible(true);
						}
					}					
					
				}else{
					lblErrorCampos.setVisible(true);
					continuar = false;
				}			
			}
		});
		btnSignin.setBounds(new Rectangle(0, 0, 0, 5));
		btnSignin.setBackground(SystemColor.menu);
		btnSignin.setBounds(126, 287, 95, 23);
		signinContainer.add(btnSignin);
		
		lblDocumentoSignin = new JLabel("*Documento");
		lblDocumentoSignin.setHorizontalAlignment(SwingConstants.CENTER);
		lblDocumentoSignin.setBounds(44, 44, 90, 14);
		signinContainer.add(lblDocumentoSignin);
		
		lblNombre1Signin = new JLabel("*Primer nombre");
		lblNombre1Signin.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre1Signin.setBounds(26, 89, 126, 14);
		signinContainer.add(lblNombre1Signin);
		
		textFieldNombre1Signin = new JTextField();
		textFieldNombre1Signin.setColumns(10);
		textFieldNombre1Signin.setBounds(13, 103, 152, 20);
		signinContainer.add(textFieldNombre1Signin);
		
		lblNombre2Signin = new JLabel("Segundo nombre");
		lblNombre2Signin.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre2Signin.setBounds(198, 89, 126, 14);
		signinContainer.add(lblNombre2Signin);
		
		textFieldNombre2Signin = new JTextField();
		textFieldNombre2Signin.setColumns(10);
		textFieldNombre2Signin.setBounds(185, 103, 152, 20);
		signinContainer.add(textFieldNombre2Signin);
		
		lblPassword1Signin = new JLabel("*Constraseña");
		lblPassword1Signin.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword1Signin.setBounds(52, 179, 74, 14);
		signinContainer.add(lblPassword1Signin);
		
		passwordFieldPassword1Signin = new JTextField();
		passwordFieldPassword1Signin.setColumns(10);
		passwordFieldPassword1Signin.setBounds(13, 193, 152, 20);
		signinContainer.add(passwordFieldPassword1Signin);
		
		lblPassword2Signin = new JLabel("*Repetir contraseña");
		lblPassword2Signin.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword2Signin.setBounds(198, 179, 126, 14);
		signinContainer.add(lblPassword2Signin);
		
		passwordFieldPassword2Signin = new JPasswordField();
		passwordFieldPassword2Signin.setColumns(10);
		passwordFieldPassword2Signin.setBounds(185, 193, 152, 20);
		signinContainer.add(passwordFieldPassword2Signin);
		
		lblNacimientoSignin = new JLabel("*Fecha de nacimiento");
		lblNacimientoSignin.setHorizontalAlignment(SwingConstants.CENTER);
		lblNacimientoSignin.setBounds(13, 224, 155, 14);
		signinContainer.add(lblNacimientoSignin);
		
		lblMailSignin = new JLabel("*Mail");
		lblMailSignin.setHorizontalAlignment(SwingConstants.CENTER);
		lblMailSignin.setBounds(217, 44, 90, 14);
		signinContainer.add(lblMailSignin);
		
		textFieldMailSignin = new JTextField();
		textFieldMailSignin.setColumns(10);
		textFieldMailSignin.setBounds(185, 58, 152, 20);
		signinContainer.add(textFieldMailSignin);
		
		dateChooserNacimientoSignin = new JDateChooser();
		dateChooserNacimientoSignin.setBounds(13, 238, 152, 20);
		signinContainer.add(dateChooserNacimientoSignin);
		
		lblApellido2Signin = new JLabel("Segundo apellido");
		lblApellido2Signin.setBounds(205, 134, 113, 14);
		signinContainer.add(lblApellido2Signin);
		lblApellido2Signin.setHorizontalAlignment(SwingConstants.CENTER);
		
		textFieldApellido2Signin = new JTextField();
		textFieldApellido2Signin.setBounds(185, 148, 152, 20);
		signinContainer.add(textFieldApellido2Signin);
		textFieldApellido2Signin.setColumns(10);
		
		lblApellido1Signin = new JLabel("*Primer apellido");
		lblApellido1Signin.setBounds(31, 134, 116, 14);
		signinContainer.add(lblApellido1Signin);
		lblApellido1Signin.setHorizontalAlignment(SwingConstants.CENTER);
		
		textFieldApellido1Signin = new JTextField();
		textFieldApellido1Signin.setBounds(13, 148, 152, 20);
		signinContainer.add(textFieldApellido1Signin);
		textFieldApellido1Signin.setColumns(10);
		
		lblErrorPassword = new JLabel("Las contraseñas no coinciden");
		lblErrorPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblErrorPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorPassword.setForeground(new Color(255, 0, 0));
		lblErrorPassword.setBounds(41, 399, 320, 17);
		lblErrorPassword.setVisible(false);
		panelSignin.add(lblErrorPassword);
		
		lblErrorCampos = new JLabel("Debe llenar los campos obligatorios");
		lblErrorCampos.setForeground(new Color(255, 0, 0));
		lblErrorCampos.setBackground(new Color(255, 0, 0));
		lblErrorCampos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblErrorCampos.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorCampos.setBounds(33, 399, 337, 17);
		lblErrorCampos.setVisible(false);
		panelSignin.add(lblErrorCampos);
		
		lblErrorInsert = new JLabel("Error. No se a podido registrar el usuario");
		lblErrorInsert.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorInsert.setForeground(new Color(255, 0, 0));
		lblErrorInsert.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblErrorInsert.setBounds(24, 399, 355, 14);
		lblErrorInsert.setVisible(false);
		panelSignin.add(lblErrorInsert);
		
		lblCorrectInsert = new JLabel("Usuario regsitrado correctamente");
		lblCorrectInsert.setHorizontalAlignment(SwingConstants.CENTER);
		lblCorrectInsert.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCorrectInsert.setForeground(new Color(0, 128, 0));
		lblCorrectInsert.setBounds(52, 399, 298, 14);
		lblCorrectInsert.setVisible(false);
		panelSignin.add(lblCorrectInsert);
		
	}
}
