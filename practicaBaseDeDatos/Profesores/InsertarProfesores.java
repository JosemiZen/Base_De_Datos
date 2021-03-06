package practicaBaseDeDatos.Profesores;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import practicaBaseDeDatos.Profesores.InsertarProfesores;

public class InsertarProfesores extends Frame implements WindowListener, ActionListener, ItemListener {
	private static final long serialVersionUID = 1L;

	TextField dni = new TextField(9);
	TextField nombreProfesor = new TextField(20);
	TextField apellidoProfesor = new TextField(20);
	TextField apellido2Profesor = new TextField(20);
	TextField idCentroFK = new TextField(20);

	Button aceptar = new Button("Aceptar");
	Button cancelar = new Button("Cancelar");
	Button aceptar1 = new Button("Aceptar");
	Button cancelar1 = new Button("Cancelar");
	Dialog exito = new Dialog(this, "Exito", true);
	Dialog confirm = new Dialog(this, "Cuidado", true);
	Label lbldni = new Label("D.N.I.");
	Label lblnombreProfesor = new Label("Nombre del Profesor");
	Label lblapellidoProfesor = new Label("Primer apellido");
	Label lblapellido2Profesor = new Label("Segundo apellido");
	Label lblIDCentroFK = new Label("Centro asignado");

	Label e = new Label("Operaci�n realizada correctamente!");
	Label lblConfirm = new Label("�Esta usted seguro de insertar estos datos?");

	int cerrar = 0;
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/Academia";
	String login = "root";
	String password = "studium";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	public InsertarProfesores() {
		setLayout(new FlowLayout());
		setTitle("Insertar Profesores");
		setSize(200, 300);
		setResizable(false);

		setLayout(new GridLayout(12, 1));
		add(lbldni);
		add(dni);
		add(lblnombreProfesor);
		add(nombreProfesor);
		add(lblapellidoProfesor);
		add(apellidoProfesor);
		add(lblapellido2Profesor);
		add(apellido2Profesor);
		add(lblIDCentroFK);
		add(idCentroFK);

		add(aceptar);
		add(cancelar);
		aceptar.addActionListener(this);
		cancelar.addActionListener(this);
		addWindowListener(this);
		// Di�logo

		exito.setLayout(new FlowLayout());
		exito.add(e);
		exito.setSize(250, 150);

		confirm.setLayout(new FlowLayout());
		confirm.add(lblConfirm);
		confirm.add(aceptar1);
		confirm.add(cancelar1);
		confirm.setSize(300, 175);
		aceptar1.addActionListener(this);
		cancelar1.addActionListener(this);
		// Para poder cerrar el Di�logo
		exito.addWindowListener(this);
		addWindowListener(this);
		confirm.addWindowListener(this);
		// A�adimos el listener a la lista
		setVisible(false);

		// Cargar el Driver
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println("Se ha producido un error al cargar el Driver");
		}
		// Establecer la conexi�n con la Base de datos
		try {
			connection = DriverManager.getConnection(url, login, password);
		} catch (SQLException e) {
			System.out.println("Se produjo un error al conectar a la Base de Datos");
		}
		// Preparar el statement

	}

	public static void main(String[] args) {
		new InsertarProfesores();
	}

	public void windowActivated(WindowEvent windowEvent) {
	}

	public void windowClosed(WindowEvent windowEvent) {
	}

	public void windowClosing(WindowEvent windowEvent)

	{
		setVisible(false);
	}

	public void windowDeactivated(WindowEvent windowEvent) {
	}

	public void windowDeiconified(WindowEvent windowEvent) {
	}

	public void windowIconified(WindowEvent windowEvent) {
	}

	public void windowOpened(WindowEvent windowEvent) {
	}

	public void itemStateChanged(ItemEvent ie) {
		// Mostraremos con los datos cargados
	}

	public void actionPerformed(ActionEvent actionEvent) {
		// Hemos pulsado Insertar
		if (aceptar.equals(actionEvent.getSource())) {
			confirm.setVisible(true);
		}
		if (cancelar.equals(actionEvent.getSource())) {
			setVisible(false);
		}
		if (aceptar1.equals(actionEvent.getSource())) {

			try {
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				statement.executeUpdate("INSERT INTO Profesores VALUES ('" + dni.getText() + "', '"
						+ nombreProfesor.getText() + "', '" + apellidoProfesor.getText() + "', '"
						+ apellido2Profesor.getText() + "', " + idCentroFK.getText() + ");");

				cerrar = 1;
				confirm.setVisible(false);
				exito.setVisible(false);
			} catch (SQLException se) {
				System.out.println("Error en la sentencia SQL" + se.toString());
			}
		} else if (cancelar1.equals(actionEvent.getSource())) {
			confirm.setVisible(false);
		} else {
		}
		cerrar = 0;
	}
}
