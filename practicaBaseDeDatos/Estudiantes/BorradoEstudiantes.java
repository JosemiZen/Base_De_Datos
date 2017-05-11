package practicaBaseDeDatos.Estudiantes;

import java.awt.Button;
import java.awt.Choice;
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

public class BorradoEstudiantes extends Frame implements WindowListener, ActionListener, ItemListener {
	private static final long serialVersionUID = 1L;
	TextField numExpediente = new TextField(20);
	TextField nombreAlumno = new TextField(20);
	TextField apellidoAlumno = new TextField(20);
	TextField apellido2Alumno = new TextField(20);
	TextField direccionAlumno = new TextField(20);
	TextField dniAlumno = new TextField(9);
	Button aceptar = new Button("Aceptar");
	Button cancelar = new Button("Cancelar");
	Button aceptar1 = new Button("Aceptar");
	Button cancelar1 = new Button("Cancelar");
	Dialog dialogo = new Dialog(this, "Datos a borrar", true);
	Dialog exito = new Dialog(this, "Exito", true);
	Dialog confirm = new Dialog(this, "Cuidado", true);
	Label lblExpediente = new Label("Número de expediente");
	Label lblNombre = new Label("Nombre el alumno");
	Label lblApellido = new Label("Primer apellido");
	Label lblApellido2 = new Label("Segundo apellido");
	Label lblDireccion = new Label("Direccion del alumno");
	Label lblDni = new Label("DNI del alumno");
	
	
	Label mensaje = new Label("Seleccionar alumno a borrar:");
	Label e = new Label("Operación realizada correctamente!");
	Label lblConfirm = new Label("¿Esta usted seguro de querer borrar estos datos?");
	Choice lista = new Choice();
	String s = "";
	int cerrar = 0;
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/Academia";
	String login = "root";
	String password = "studium";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	

	public BorradoEstudiantes() {
		setLayout(new FlowLayout());
		setTitle("Borrado Estudiantes");
		dialogo.setLayout(new FlowLayout());
		dialogo.setSize(250, 500);
		setSize(500, 100);
		dialogo.setResizable(false);
		add(mensaje);
		add(lista);
		numExpediente.setEditable(false);
		nombreAlumno.setEditable(false);
		apellidoAlumno.setEditable(false);
		apellido2Alumno.setEditable(false);
		direccionAlumno.setEditable(false);
		dniAlumno.setEditable(false);
		dialogo.setLayout(new GridLayout(14, 1));
		dialogo.add(lblExpediente);
		dialogo.add(numExpediente);
		dialogo.add(lblNombre);
		dialogo.add(nombreAlumno);
		dialogo.add(lblApellido);
		dialogo.add(apellidoAlumno);
		dialogo.add(lblApellido2);
		dialogo.add(apellido2Alumno);
		dialogo.add(lblDireccion);
		dialogo.add(direccionAlumno);
		dialogo.add(lblDni);
		dialogo.add(dniAlumno);
		dialogo.add(aceptar);
		dialogo.add(cancelar);
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
		confirm.setSize(350, 250);
		aceptar1.addActionListener(this);
		cancelar1.addActionListener(this);
		// Para poder cerrar el Diálogo
		exito.addWindowListener(this);
		dialogo.addWindowListener(this);
		confirm.addWindowListener(this);
		// Añadimos el listener a la lista
		lista.addItemListener(this);
		lista.add("Seleccionar uno");
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
		recargaAlumno(lista, connection);
	}
	//creamos un metodo para que sea mas rapido la carga de los datos en la lista
	public Choice recargaAlumno(Choice lista, Connection connection) {
		lista.removeAll();
		lista.add("Seleccione un Alumno");
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery("SELECT * FROM Estudiantes");
			while (rs.next()) {
				s = rs.getString("numExpediente");
				s = s + "-" + rs.getString("nombreAlumno");
				lista.add(s);

			}

		} catch (SQLException e) {
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		return lista;

	}

	public static void main(String[] args) {
		new BorradoEstudiantes();
	}

	public void windowActivated(WindowEvent windowEvent) {
	}

	public void windowClosed(WindowEvent windowEvent) {
	}

	public void windowClosing(WindowEvent windowEvent)

	{
		switch (cerrar) {
		// Principal
		case 0:
			// Cerrar los elementos
			
			setVisible(false);
			break;
		// d
		case 1:
			cerrar = 0;
			exito.setVisible(false);
			dialogo.setVisible(false);
			break;
		// dialogo
		case 2:
			cerrar = 0;
			dialogo.setVisible(false);
			break;
		}
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
		// Mostraremos dialogo con los datos cargados
		String[] array = ie.getItem().toString().split("-");
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery("SELECT * FROM Estudiantes WHERE numExpediente = '" + array[0] + "'");
			while (rs.next()) {
				numExpediente.setText(rs.getString("numExpediente"));
				nombreAlumno.setText(rs.getString("nombreAlumno"));
				apellidoAlumno.setText(rs.getString("apellidoAlumno"));
				apellido2Alumno.setText(rs.getString("apellido2Alumno"));
				direccionAlumno.setText(rs.getString("direccionAlumno"));
				dniAlumno.setText(rs.getString("dniAlumno"));
			}
		} catch (SQLException e) {
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}

		cerrar = 2;
		dialogo.setVisible(true);
	}

	public void actionPerformed(ActionEvent actionEvent) {
		// Hemos pulsado Insertar
		if (aceptar.equals(actionEvent.getSource())) {
			confirm.setVisible(true);
		}
		if (aceptar1.equals(actionEvent.getSource())) {

			try {

				statement.executeUpdate("DELETE FROM Estudiantes WHERE numExpediente='" + numExpediente.getText() + "'");
				cerrar = 1;
				confirm.setVisible(false);
				exito.setVisible(true);
				recargaAlumno(lista, connection);
			} catch (SQLException se) {
				System.out.println("Error en la sentencia SQL" + se.toString());
			}
		} else if (cancelar1.equals(actionEvent.getSource())) {
			confirm.setVisible(false);
		} else {
		}
		cerrar = 0;
		dialogo.setVisible(false);
	}
}
