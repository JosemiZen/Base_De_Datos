package practicaBaseDeDatos.Libros;

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

public class RecorridoLibros extends Frame implements WindowListener, ActionListener {
	private static final long serialVersionUID = 1L;
	//conexion a la base de datos
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/academia";
	String login = "root";
	String password = "studium";
	
	
	//muestra de los alumnos
	Label lblidLibro = new Label ("id del libro");
	Label lblISBN = new Label ("ISBN del libro");
	Label lblNombre = new Label ("Nombre del libro");
	Label lblAnyo = new Label ("Año de publicacion");
	TextField idLibro = new TextField(20);
	TextField ISBN = new TextField(13);
	TextField nombreLibro = new TextField(255);
	TextField anyoLibro = new TextField(4);
	Button first = new Button("<<");
	Button last = new Button(">>");
	Button next = new Button(">");
	Button previous = new Button("<");
	String sentencia = "SELECT * FROM libros";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	
	
	
	
	
	public RecorridoLibros() {
		setLayout(new GridLayout(6,3));
		setSize(450, 200);
		setResizable(true);
		setTitle("Tabla Libros");
		add(lblidLibro);
		add(idLibro);
		add(lblISBN);
		add(ISBN);
		add(lblNombre);
		add(nombreLibro);
		add(lblAnyo);
		add(anyoLibro);

		add(previous);
		add(next);
		add(first);
		add(last);
		
		
		
		
		idLibro.setEditable(false);
		ISBN.setEditable(false);
		nombreLibro.setEditable(false);
		anyoLibro.setEditable(false);
		
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
			idLibro.setText(rs.getString("idLibro"));
			ISBN.setText(rs.getString("ISBN"));
			nombreLibro.setText(rs.getString("nombreLibro"));
			anyoLibro.setText(rs.getString("anyoLibro"));
		} catch (SQLException e) {
			System.out.println("Error en la sentencia SQL");
		}
		
		setVisible(false);
	}

	public static void main(String[] args) {
		new RecorridoLibros();
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
					idLibro.setText(rs.getString("idLibro"));
					ISBN.setText(rs.getString("ISBN"));
					nombreLibro.setText(rs.getString("nombreLibro"));
					anyoLibro.setText(rs.getString("anyoLibro"));
				}
			} else if (ae.getSource().equals(previous)) {
				if (rs.previous()) {
					idLibro.setText(rs.getString("idLibro"));
					ISBN.setText(rs.getString("ISBN"));
					nombreLibro.setText(rs.getString("nombreLibro"));
					anyoLibro.setText(rs.getString("anyoLibro"));
				}
			}else if (ae.getSource().equals(first)) {
				if (rs.first()) {
					idLibro.setText(rs.getString("idLibro"));
					ISBN.setText(rs.getString("ISBN"));
					nombreLibro.setText(rs.getString("nombreLibro"));
					anyoLibro.setText(rs.getString("anyoLibro"));
				}
			}
			else if (ae.getSource().equals(last)) {
				if (rs.last()) {
					idLibro.setText(rs.getString("idLibro"));
					ISBN.setText(rs.getString("ISBN"));
					nombreLibro.setText(rs.getString("nombreLibro"));
					anyoLibro.setText(rs.getString("anyoLibro"));
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
