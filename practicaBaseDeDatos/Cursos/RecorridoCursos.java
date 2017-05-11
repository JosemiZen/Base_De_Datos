package practicaBaseDeDatos.Cursos;

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

public class RecorridoCursos extends Frame implements WindowListener, ActionListener {
	private static final long serialVersionUID = 1L;
	//conexion a la base de datos
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/academia";
	String login = "root";
	String password = "studium";
	
	
	//muestra de los alumnos
	Label lblidCurso = new Label ("id del curso");
	Label lblNombreCurso = new Label ("Nombre del curso");
	Label lblHorasCurso = new Label ("Horas del curso");
	TextField idCurso = new TextField(20);
	TextField nombreCurso = new TextField(20);
	TextField horasCurso = new TextField(20);
	Button first = new Button("<<");
	Button last = new Button(">>");
	Button next = new Button(">");
	Button previous = new Button("<");
	String sentencia = "SELECT * FROM cursos";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	

	public RecorridoCursos() {
		setLayout(new GridLayout(5,3));
		setSize(285, 150);
		setResizable(true);
		setTitle("Tabla Cursos");
		add(lblidCurso);
		add(idCurso);
		add(lblNombreCurso);
		add(nombreCurso);
		add(lblHorasCurso);
		add(horasCurso);

		add(previous);
		add(next);
		add(first);
		add(last);
		
		
		
		
		idCurso.setEditable(false);
		nombreCurso.setEditable(false);
		horasCurso.setEditable(false);
		
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
			idCurso.setText(rs.getString("idCurso"));
			nombreCurso.setText(rs.getString("nombreCurso"));
			horasCurso.setText(rs.getString("horasCurso"));
			
		} catch (SQLException e) {
			System.out.println("Error en la sentencia SQL");
		}
		
		setVisible(false);
	}

	public static void main(String[] args) {
		new RecorridoCursos();
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
					idCurso.setText(rs.getString("idCurso"));
					nombreCurso.setText(rs.getString("nombreCurso"));
					horasCurso.setText(rs.getString("horasCurso"));
					
				}
			} else if (ae.getSource().equals(previous)) {
				if (rs.previous()) {
					idCurso.setText(rs.getString("idCurso"));
					nombreCurso.setText(rs.getString("nombreCurso"));
					horasCurso.setText(rs.getString("horasCurso"));
					
				}
			}else if (ae.getSource().equals(first)) {
				if (rs.first()) {
					idCurso.setText(rs.getString("idCurso"));
					nombreCurso.setText(rs.getString("nombreCurso"));
					horasCurso.setText(rs.getString("horasCurso"));
					
				}
			}
			else if (ae.getSource().equals(last)) {
				if (rs.last()) {
					idCurso.setText(rs.getString("idCurso"));
					nombreCurso.setText(rs.getString("nombreCurso"));
					horasCurso.setText(rs.getString("horasCurso"));
					
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