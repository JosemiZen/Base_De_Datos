package practicaBaseDeDatos.Estudiantes;

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

import practicaBaseDeDatos.Estudiantes.InsertarEstudiantes;

public class InsertarEstudiantes extends Frame implements WindowListener, ActionListener, ItemListener {
	private static final long serialVersionUID = 1L;

	TextField numExpediente = new TextField(9);
	TextField nombreAlumno = new TextField(20);
	TextField apellidoAlumno = new TextField(20);
	TextField apellido2Alumno = new TextField(20);
	TextField direccionAlumno = new TextField(20);
	TextField dniAlumno = new TextField(20);

	Button aceptar = new Button("Aceptar");
	Button cancelar = new Button("Cancelar");
	Button aceptar1 = new Button("Aceptar");
	Button cancelar1 = new Button("Cancelar");
	Dialog exito = new Dialog(this, "Exito", true);
	Dialog confirm = new Dialog(this, "Cuidado", true);
	Label lblnumExpediente = new Label("Número de expediente");
	Label lblnombreAlumno = new Label("Nombre del Alumno");
	Label lblapellidoAlumno = new Label("Primer apellido");
	Label lblapellido2Alumno = new Label("Segundo apellido");
	Label lbldireccionAlumno = new Label ("Direccion del Alumno");
	Label lbldniAlumno = new Label("Centro asignado");

	Label e = new Label("Operación realizada correctamente!");
	Label lblConfirm = new Label("¿Esta usted seguro de insertar estos datos?");

	int cerrar = 0;
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/Academia";
	String login = "root";
	String password = "studium";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	public InsertarEstudiantes() {
		setLayout(new FlowLayout());
		setTitle("Insertar Estudiantes");
		setSize(200, 350);
		setResizable(false);

		setLayout(new GridLayout(14, 1));
		add(lblnumExpediente);
		add(numExpediente);
		add(lblnombreAlumno);
		add(nombreAlumno);
		add(lblapellidoAlumno);
		add(apellidoAlumno);
		add(lblapellido2Alumno);
		add(apellido2Alumno);
		add(lbldireccionAlumno);
		add(direccionAlumno);
		add(lbldniAlumno);
		add(dniAlumno);

		add(aceptar);
		add(cancelar);
		aceptar.addActionListener(this);
		cancelar.addActionListener(this);
		addWindowListener(this);
		// Diálogo

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
		// Para poder cerrar el Diálogo
		exito.addWindowListener(this);
		addWindowListener(this);
		confirm.addWindowListener(this);
		// Añadimos el listener a la lista
		setVisible(false);

		// Cargar el Driver
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println("Se ha producido un error al cargar el Driver");
		}
		// Establecer la conexión con la Base de datos
		try {
			connection = DriverManager.getConnection(url, login, password);
		} catch (SQLException e) {
			System.out.println("Se produjo un error al conectar a la Base de Datos");
		}
		// Preparar el statement

	}

	public static void main(String[] args) {
		new InsertarEstudiantes();
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
				statement.executeUpdate("INSERT INTO Estudiantes VALUES ('" + numExpediente.getText() + "', '"
						+ nombreAlumno.getText() + "', '" + apellidoAlumno.getText() + "', '"
						+ apellido2Alumno.getText() + "', '"+ direccionAlumno.getText() + "', '" + dniAlumno.getText() + "');");

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

