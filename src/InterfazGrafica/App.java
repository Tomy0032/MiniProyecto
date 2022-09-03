package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BDControl.DatabaseManager;
import Classes.Funcionalidad;
import Classes.Persona;
import Classes.Rol;
import DAO.DAOFuncionalidad;
import DAO.DAOPersona;
import DAO.DAORol;
import DAO.DAORolFuncionalidad;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JToggleButton;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

public class App extends JFrame {

	private LinkedList<Rol> roles = null;
	private ArrayList listaRoles = null;
	private String[] nombreRoles = null;
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
	private JPasswordField passwordFieldPassword1Signin;
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
	private JComboBox comboRoles = new JComboBox();
	private JPanel panelMostar;
	private JButton btnMostrar;
	private JPanel panelRoles;
	private JPanel containterRol;
	private JLabel lblAgregarRol;
	private JTextField textFieldNombreRol;
	private JTextField textFieldDescripcionRol;
	private JPanel panelRoles_1;
	private JPanel containterRol_1;
	private JLabel lblAgregarFuncionalidad;
	private JTextField textFieldNombreFuncionalidad;
	private JLabel lblNombreFuncionalidad;
	private JLabel lblDescripcinFuncionalidad;
	private JTextField textFieldDescripcionFuncionalidad;
	private JButton btnAgregarFuncionalidad;
	private JPanel panelMostar_1;
	private JButton btnMostrarFuncionalidades;
	private JLabel lblRolFuncionalidad;
	private JComboBox comboRolesFuncionalidad = new JComboBox();
	private JLabel lblCamposRol;
	private JLabel lblErrorRol;
	private JLabel lblRolAgregado;
	private JLabel lblErrorFuncionalidad;
	private JLabel lblCamposFuncionalidad;
	private JLabel lblFuncionalidadAgregada;

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
	
	public void cargarRoles() {
		roles = DAORol.findAll();
		listaRoles = new ArrayList<>();
		for(Rol r : roles) {
			listaRoles.add(r.getNombre());
		}
		nombreRoles = (String[]) listaRoles.toArray(new String[listaRoles.size()]);
		comboRoles.setModel(new DefaultComboBoxModel(nombreRoles));
		comboRolesFuncionalidad.setModel(new DefaultComboBoxModel(nombreRoles));
	}

	public App() {
		
	
		cargarRoles();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1041, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		panelLogin = new JPanel();
		panelLogin.setBackground(new Color(245, 255, 250));
		panelLogin.setBounds(10, 11, 294, 333);
		contentPane.add(panelLogin);
		panelLogin.setLayout(null);
		
		
		loginContainer = new JPanel();
		loginContainer.setBackground(new Color(245, 255, 250));
		loginContainer.setBounds(57, 70, 179, 197);
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
						JOptionPane.showMessageDialog(null, "Sesión iniciada correctamente");
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
		lblErrorCamposLogin.setBounds(30, 284, 233, 14);
		lblErrorCamposLogin.setVisible(false);
		panelLogin.add(lblErrorCamposLogin);
		
		lblErrorLogin = new JLabel("Error de autentificación");
		lblErrorLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblErrorLogin.setForeground(new Color(255, 0, 0));
		lblErrorLogin.setBounds(20, 284, 253, 14);
		lblErrorLogin.setVisible(false);
		panelLogin.add(lblErrorLogin);
		
		panelSignin = new JPanel();
		panelSignin.setBackground(new Color(245, 255, 250));
		panelSignin.setBounds(314, 11, 403, 493);
		contentPane.add(panelSignin);
		panelSignin.setLayout(null);
		
		signinContainer = new JPanel();
		signinContainer.setLayout(null);
		signinContainer.setBackground(new Color(245, 255, 250));
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
				String nombreRol = (String)comboRoles.getSelectedItem().toString();
				int idRol = DAORol.buscarNombreRol(nombreRol).getIdRol();
				
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
						p.setIdRol(idRol);
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
		lblPassword1Signin.setBounds(13, 179, 152, 14);
		signinContainer.add(lblPassword1Signin);
		
		passwordFieldPassword1Signin = new JPasswordField();
		passwordFieldPassword1Signin.setColumns(10);
		passwordFieldPassword1Signin.setBounds(13, 193, 152, 20);
		signinContainer.add(passwordFieldPassword1Signin);
		
		lblPassword2Signin = new JLabel("*Repetir contraseña");
		lblPassword2Signin.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword2Signin.setBounds(185, 179, 152, 14);
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
		
		comboRoles.setBounds(185, 238, 152, 20);
		signinContainer.add(comboRoles);
		
		JLabel lblRol = new JLabel("Rol");
		lblRol.setHorizontalAlignment(SwingConstants.CENTER);
		lblRol.setBounds(185, 224, 152, 14);
		signinContainer.add(lblRol);
		
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
		
		panelMostar = new JPanel();
		panelMostar.setBackground(new Color(245, 255, 250));
		panelMostar.setBounds(10, 434, 294, 70);
		contentPane.add(panelMostar);
		panelMostar.setLayout(null);
		
		btnMostrar = new JButton("Mostrar roles por persona");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnMostrar bt = new btnMostrar();
				bt.mostrar();
				
			}
		});
		btnMostrar.setBackground(SystemColor.control);
		btnMostrar.setBounds(51, 23, 191, 23);
		panelMostar.add(btnMostrar);
		
		panelRoles = new JPanel();
		panelRoles.setBackground(new Color(245, 255, 250));
		panelRoles.setBounds(727, 11, 288, 223);
		contentPane.add(panelRoles);
		panelRoles.setLayout(null);
		
		containterRol = new JPanel();
		containterRol.setBackground(new Color(245, 255, 250));
		containterRol.setBounds(34, 27, 219, 169);
		panelRoles.add(containterRol);
		containterRol.setLayout(null);
		
		lblAgregarRol = new JLabel("Agregar Rol");
		lblAgregarRol.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgregarRol.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAgregarRol.setBounds(37, 0, 145, 22);
		containterRol.add(lblAgregarRol);
		
		textFieldNombreRol = new JTextField();
		textFieldNombreRol.setBounds(10, 48, 199, 20);
		containterRol.add(textFieldNombreRol);
		textFieldNombreRol.setColumns(10);
		
		JLabel lblNombreRol = new JLabel("Nombre");
		lblNombreRol.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreRol.setBounds(64, 33, 90, 14);
		containterRol.add(lblNombreRol);
		
		JLabel lblDescripcinRol = new JLabel("Descripción");
		lblDescripcinRol.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcinRol.setBounds(64, 82, 90, 14);
		containterRol.add(lblDescripcinRol);
		
		textFieldDescripcionRol = new JTextField();
		textFieldDescripcionRol.setColumns(10);
		textFieldDescripcionRol.setBounds(10, 97, 199, 20);
		containterRol.add(textFieldDescripcionRol);
		
		JButton btnAgregarRol = new JButton("Enviar");
		btnAgregarRol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean continuar = true;
				lblCamposRol.setVisible(false);
				lblErrorRol.setVisible(false);
				lblRolAgregado.setVisible(false);
				
				String nombreRol = textFieldNombreRol.getText();
				String descripcionRol = textFieldDescripcionRol.getText();
				
				if(nombreRol.equals("") && continuar) {
					lblCamposRol.setVisible(true);
					continuar = false;
				}
				if(descripcionRol.equals("") && continuar) {
					lblCamposRol.setVisible(true);
					continuar = false;
				}
				
				if(continuar) {
					Rol rol = new Rol(nombreRol,descripcionRol);
					if(DAORol.insertarRol(rol)) {
						lblRolAgregado.setVisible(true);
						cargarRoles();
					}else {
						lblErrorRol.setVisible(true);
					}
				}
			}
		});
		btnAgregarRol.setBounds(new Rectangle(0, 0, 0, 5));
		btnAgregarRol.setBackground(SystemColor.menu);
		btnAgregarRol.setBounds(62, 128, 95, 23);
		containterRol.add(btnAgregarRol);
		
		lblCamposRol = new JLabel("Debe llenar todos los campos");
		lblCamposRol.setHorizontalAlignment(SwingConstants.CENTER);
		lblCamposRol.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCamposRol.setForeground(new Color(255, 0, 0));
		lblCamposRol.setBounds(34, 198, 219, 14);
		lblCamposRol.setVisible(false);
		panelRoles.add(lblCamposRol);
		
		lblErrorRol = new JLabel("El rol ya existe");
		lblErrorRol.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorRol.setForeground(new Color(255, 0, 0));
		lblErrorRol.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblErrorRol.setBounds(10, 200, 268, 14);
		lblErrorRol.setVisible(false);
		panelRoles.add(lblErrorRol);
		
		lblRolAgregado = new JLabel("Rol agregado correctamente");
		lblRolAgregado.setHorizontalAlignment(SwingConstants.CENTER);
		lblRolAgregado.setForeground(new Color(50, 205, 50));
		lblRolAgregado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRolAgregado.setBounds(17, 200, 254, 14);
		lblRolAgregado.setVisible(false);
		panelRoles.add(lblRolAgregado);
		
		panelRoles_1 = new JPanel();
		panelRoles_1.setLayout(null);
		panelRoles_1.setBackground(new Color(245, 255, 250));
		panelRoles_1.setBounds(727, 245, 288, 259);
		contentPane.add(panelRoles_1);
		
		containterRol_1 = new JPanel();
		containterRol_1.setLayout(null);
		containterRol_1.setBackground(new Color(245, 255, 250));
		containterRol_1.setBounds(34, 11, 219, 218);
		panelRoles_1.add(containterRol_1);
		
		lblAgregarFuncionalidad = new JLabel("Agregar Funcionalidad");
		lblAgregarFuncionalidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgregarFuncionalidad.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAgregarFuncionalidad.setBounds(10, 11, 199, 22);
		containterRol_1.add(lblAgregarFuncionalidad);
		
		textFieldNombreFuncionalidad = new JTextField();
		textFieldNombreFuncionalidad.setColumns(10);
		textFieldNombreFuncionalidad.setBounds(10, 59, 199, 20);
		containterRol_1.add(textFieldNombreFuncionalidad);
		
		lblNombreFuncionalidad = new JLabel("Nombre");
		lblNombreFuncionalidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreFuncionalidad.setBounds(64, 44, 90, 14);
		containterRol_1.add(lblNombreFuncionalidad);
		
		lblDescripcinFuncionalidad = new JLabel("Descripción");
		lblDescripcinFuncionalidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcinFuncionalidad.setBounds(64, 93, 90, 14);
		containterRol_1.add(lblDescripcinFuncionalidad);
		
		textFieldDescripcionFuncionalidad = new JTextField();
		textFieldDescripcionFuncionalidad.setColumns(10);
		textFieldDescripcionFuncionalidad.setBounds(10, 108, 199, 20);
		containterRol_1.add(textFieldDescripcionFuncionalidad);
		
		btnAgregarFuncionalidad = new JButton("Enviar");
		btnAgregarFuncionalidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean continuar = true;
				lblCamposFuncionalidad.setVisible(false);
				lblErrorFuncionalidad.setVisible(false);
				lblFuncionalidadAgregada.setVisible(false);
				
				String nombreFuncionalidad = textFieldNombreFuncionalidad.getText();
				String descripcionFuncionalidad = textFieldDescripcionFuncionalidad.getText();
				String nombreRol = (String)comboRolesFuncionalidad.getSelectedItem().toString();
				
				if(nombreFuncionalidad.equals("") && continuar) {
					lblCamposRol.setVisible(true);
					continuar = false;
				}
				if(descripcionFuncionalidad.equals("") && continuar) {
					lblCamposRol.setVisible(true);
					continuar = false;
				}
				
				if(continuar) {
					Funcionalidad funcionalidad = new Funcionalidad(nombreFuncionalidad,descripcionFuncionalidad);
					if(DAOFuncionalidad.insertarFuncion(funcionalidad)) {
						lblFuncionalidadAgregada.setVisible(true);
						DAORolFuncionalidad.insertar(DAORol.buscarNombreRol(nombreRol), DAOFuncionalidad.buscarNombreFuncionalidad(nombreFuncionalidad));
						cargarRoles();
					}else {
						lblErrorFuncionalidad.setVisible(true);
					}
				}
			}
		});
		btnAgregarFuncionalidad.setBounds(new Rectangle(0, 0, 0, 5));
		btnAgregarFuncionalidad.setBackground(SystemColor.menu);
		btnAgregarFuncionalidad.setBounds(62, 192, 95, 23);
		containterRol_1.add(btnAgregarFuncionalidad);
		
		lblRolFuncionalidad = new JLabel("Rol");
		lblRolFuncionalidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblRolFuncionalidad.setBounds(10, 139, 199, 14);
		containterRol_1.add(lblRolFuncionalidad);
		
		comboRolesFuncionalidad.setBounds(10, 153, 199, 20);
		containterRol_1.add(comboRolesFuncionalidad);
		
		lblErrorFuncionalidad = new JLabel("Ya existe la funcionalidad");
		lblErrorFuncionalidad.setForeground(new Color(255, 0, 0));
		lblErrorFuncionalidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblErrorFuncionalidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorFuncionalidad.setBounds(27, 234, 233, 14);
		lblErrorFuncionalidad.setVisible(false);
		panelRoles_1.add(lblErrorFuncionalidad);
		
		lblCamposFuncionalidad = new JLabel("Debe llenar todos los campos");
		lblCamposFuncionalidad.setForeground(new Color(255, 0, 0));
		lblCamposFuncionalidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCamposFuncionalidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCamposFuncionalidad.setBounds(15, 236, 258, 14);
		lblCamposFuncionalidad.setVisible(false);
		panelRoles_1.add(lblCamposFuncionalidad);
		
		lblFuncionalidadAgregada = new JLabel("Funcionalidad agregada correctamente");
		lblFuncionalidadAgregada.setHorizontalAlignment(SwingConstants.CENTER);
		lblFuncionalidadAgregada.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFuncionalidadAgregada.setForeground(new Color(50, 205, 50));
		lblFuncionalidadAgregada.setBounds(12, 236, 263, 14);
		lblFuncionalidadAgregada.setVisible(false);
		panelRoles_1.add(lblFuncionalidadAgregada);
		
		panelMostar_1 = new JPanel();
		panelMostar_1.setLayout(null);
		panelMostar_1.setBackground(new Color(245, 255, 250));
		panelMostar_1.setBounds(10, 353, 294, 70);
		contentPane.add(panelMostar_1);
		
		btnMostrarFuncionalidades = new JButton("Mostrar funcionalidades");
		btnMostrarFuncionalidades.setBackground(SystemColor.menu);
		btnMostrarFuncionalidades.setBounds(51, 23, 191, 23);
		panelMostar_1.add(btnMostrarFuncionalidades);
		
	}
}
