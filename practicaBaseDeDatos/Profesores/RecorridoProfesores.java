package practicaBaseDeDatos.Profesores;

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

public class RecorridoProfesores extends Frame implements WindowListener, ActionListener {
	private static final long serialVersionUID = 1L;
	//conexion a la base de datos
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/academia";
	String login = "root";
	String password = "studium";
	
	
	//muestra de los alumnos
	Label lblDNI = new Label ("DNI");
	Label lblNombre = new Label ("Nombre del profesor");
	Label lblApellido1 = new Label ("Primer apellido");
	Label lblApellido2 = new Label ("Segundo Apellido");
	Label lblidFK = new Label ("Centro Asignado");
	TextField dni = new TextField(20);
	TextField nombreProfesor = new TextField(20);
	TextField apellidoProfesor = new TextField(20);
	TextField apellido2Profesor = new TextField(20);
	TextField idCentroFK = new TextField(3);
	Button first = new Button("<<");
	Button last = new Button(">>");
	Button next = new Button(">");
	Button previous = new Button("<");
	String sentencia = "SELECT * FROM Profesores";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	

	public RecorridoProfesores() {
		setLayout(new GridLayout(7,3));
		setSize(300, 250);
		setResizable(true);
		setTitle("Tabla Profesores");
		add(lblDNI);
		add(dni);
		add(lblNombre);
		add(nombreProfesor);
		add(lblApellido1);
		add(apellidoProfesor);
		add(lblApellido2);
		add(apellido2Profesor);
		add(lblidFK);
		add(idCentroFK);
		
		add(previous);
		add(next);
		add(first);
		add(last);
		
		
		dni.setEditable(false);
		nombreProfesor.setEditable(false);
		apellidoProfesor.setEditable(false);
		apellido2Profesor.setEditable(false);
		idCentroFK.setEditable(false);
		
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
			dni.setText(rs.getString("dni"));
			nombreProfesor.setText(rs.getString("nombreProfesor"));
			apellidoProfesor.setText(rs.getString("apellidoProfesor"));
			apellido2Profesor.setText(rs.getString("apellido2Profesor"));
			idCentroFK.setText(Integer.toString(rs.getInt("idCentroFK")));
		} catch (SQLException e) {
			System.out.println("Error en la sentencia SQL");
		}
		
		setVisible(false);
	}

	public static void main(String[] args) {
		new RecorridoProfesores();
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
					dni.setText(rs.getString("dni"));
					nombreProfesor.setText(rs.getString("nombreProfesor"));
					apellidoProfesor.setText(rs.getString("apellidoProfesor"));
					apellido2Profesor.setText(rs.getString("apellido2Profesor"));
					idCentroFK.setText(Integer.toString(rs.getInt("idCentroFK")));
				}
			} else if (ae.getSource().equals(previous)) {
				if (rs.previous()) {
					dni.setText(rs.getString("dni"));
					nombreProfesor.setText(rs.getString("nombreProfesor"));
					apellidoProfesor.setText(rs.getString("apellidoProfesor"));
					apellido2Profesor.setText(rs.getString("apellido2Profesor"));
					idCentroFK.setText(Integer.toString(rs.getInt("idCentroFK")));
				}
			}else if (ae.getSource().equals(first)) {
				if (rs.first()) {
					dni.setText(rs.getString("dni"));
					nombreProfesor.setText(rs.getString("nombreProfesor"));
					apellidoProfesor.setText(rs.getString("apellidoProfesor"));
					apellido2Profesor.setText(rs.getString("apellido2Profesor"));
					idCentroFK.setText(Integer.toString(rs.getInt("idCentroFK")));
				}
			}
			else if (ae.getSource().equals(last)) {
				if (rs.last()) {
					dni.setText(rs.getString("dni"));
					nombreProfesor.setText(rs.getString("nombreProfesor"));
					apellidoProfesor.setText(rs.getString("apellidoProfesor"));
					apellido2Profesor.setText(rs.getString("apellido2Profesor"));
					idCentroFK.setText(Integer.toString(rs.getInt("idCentroFK")));
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
