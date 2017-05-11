package practicaBaseDeDatos.Estudiantes;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class RecorridoEstudiantes extends Frame implements WindowListener, ActionListener {
	private static final long serialVersionUID = 1L;
	//conexion a la base de datos
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/academia";
	String login = "root";
	String password = "studium";
	
	
	//muestra de los alumnos
	Label lblExpediente = new Label ("Numero de expediente");
	Label lblNombre = new Label ("Nombre del alumno");
	Label lblApellido1 = new Label ("Primer apellido");
	Label lblApellido2 = new Label ("Segundo Apellido");
	TextField numExpediente = new TextField(20);
	TextField nombreAlumno = new TextField(20);
	TextField apellidoAlumno = new TextField(20);
	TextField apellido2Alumno = new TextField(20);
	Button first = new Button("<<");
	Button last = new Button(">>");
	Button next = new Button(">");
	Button previous = new Button("<");
	String sentencia = "SELECT * FROM estudiantes";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	

	public RecorridoEstudiantes() {
		setLayout(new GridLayout(6,3));
		setSize(285, 200);
		setResizable(true);
		setTitle("Tabla Estudiantes");
		add(lblExpediente);
		add(numExpediente);
		add(lblNombre);
		add(nombreAlumno);
		add(lblApellido1);
		add(apellidoAlumno);
		add(lblApellido2);
		add(apellido2Alumno);

		add(previous);
		add(next);
		add(first);
		add(last);
		
		
		
		
		numExpediente.setEditable(false);
		nombreAlumno.setEditable(false);
		apellidoAlumno.setEditable(false);
		apellido2Alumno.setEditable(false);
		
		first.addActionListener(this);
		last.addActionListener(this);
		previous.addActionListener(this);
		next.addActionListener(this);
		
		addWindowListener(this);
		 //Cargar el Driver
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println("Se ha producido un error al cargar el Driver");
		}
		// Establecer la conexión con la base de datos

		try {
			connection = DriverManager.getConnection(url, login, password);
		} catch (SQLException e) {
			System.out.println("Se produjo un error al conectar a la Base de Datos");
		}
		// Preparar el statement
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(sentencia);
			rs.next();
			// Poner en los TextField los valores obtenidos del 1º
			numExpediente.setText(rs.getString("numExpediente"));
			nombreAlumno.setText(rs.getString("nombreAlumno"));
			apellidoAlumno.setText(rs.getString("apellidoAlumno"));
			apellido2Alumno.setText(rs.getString("apellido2Alumno"));
		} catch (SQLException e) {
			System.out.println("Error en la sentencia SQL");
		}
		
		setVisible(false);
	}

	public static void main(String[] args) {
		new RecorridoEstudiantes();
	}

	public void windowActivated(WindowEvent windowEvent) {
	}

	public void windowClosed(WindowEvent windowEvent) {
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		try {
			// Si no hemos llegado al final
			if (ae.getSource().equals(next)) {
				if (rs.next()) {

					// Poner en los TextField los valores obtenidos
					numExpediente.setText(rs.getString("numExpediente"));
					nombreAlumno.setText(rs.getString("nombreAlumno"));
					apellidoAlumno.setText(rs.getString("apellidoAlumno"));
					apellido2Alumno.setText(rs.getString("apellido2Alumno"));
				}
			} else if (ae.getSource().equals(previous)) {
				if (rs.previous()) {
					numExpediente.setText(rs.getString("numExpediente"));
					nombreAlumno.setText(rs.getString("nombreAlumno"));
					apellidoAlumno.setText(rs.getString("apellidoAlumno"));
					apellido2Alumno.setText(rs.getString("apellido2Alumno"));
				}
			}else if (ae.getSource().equals(first)) {
				if (rs.first()) {
					numExpediente.setText(rs.getString("numExpediente"));
					nombreAlumno.setText(rs.getString("nombreAlumno"));
					apellidoAlumno.setText(rs.getString("apellidoAlumno"));
					apellido2Alumno.setText(rs.getString("apellido2Alumno"));
				}
			}
			else if (ae.getSource().equals(last)) {
				if (rs.last()) {
					numExpediente.setText(rs.getString("numExpediente"));
					nombreAlumno.setText(rs.getString("nombreAlumno"));
					apellidoAlumno.setText(rs.getString("apellidoAlumno"));
					apellido2Alumno.setText(rs.getString("apellido2Alumno"));
				}
			}	

		} catch (SQLException e) {
			System.out.println("Error en la sentencia SQL" + e.getMessage());
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		setVisible(false);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}