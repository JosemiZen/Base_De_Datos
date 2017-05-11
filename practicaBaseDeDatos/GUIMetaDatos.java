package practicaBaseDeDatos;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GUIMetaDatos extends Frame implements WindowListener, ActionListener {
	private static final long serialVersionUID = 1L;
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/Academia";
	String login = "root";
	String password = "studium";
	String sentencia = "SELECT * FROM Estudiantes";
	String sentencia2 = "SELECT * FROM Profesores";
	String sentencia3 = "SELECT * FROM Cursos";
	String sentencia4 = "SELECT * FROM Centros";
	String sentencia5 = "SELECT * FROM Libros";
	String resultado ="";
	java.sql.Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
		
	
		TextArea muestra = new TextArea();
		Label team = new Label("© José Miguel González Ortiz");
		Button b1 = new Button("Estudiantes");
		Button b2 = new Button("Profesores");
		Button b3 = new Button("Cursos");
		Button b4 = new Button("Centros");
		Button b5 = new Button("libros");
		Button b6 = new Button("General");
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		
		public GUIMetaDatos(){
			muestra.setEditable(false);
		setLayout(gridbag);
		setTitle("Metadatos");
		// agregar las cosas en gridbag
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(b1, gbc);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(b2, gbc);
		gbc.weightx = 0.5;
		gbc.gridx = 2;
		gbc.gridy = 1;
	add(b3, gbc);
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(b4, gbc);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 2;
		add(b5, gbc);
		gbc.weightx = 0.5;
		gbc.gridx = 2;
		gbc.gridy = 2;
		add(b6, gbc);
	
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(muestra, gbc);
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 3;
		// hacemos que el textarea sea de 3x3
		gbc.gridwidth = 3;
		gbc.gridheight = 3;
		add(team, gbc);
		setVisible(false);
		//con pack se ajustan los componentes
		pack();
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		addWindowListener(this);
}



	public static void main(String[] args) {
		new GUIMetaDatos();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if (ae.getSource().equals(b1)) {
			
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
				java.sql.ResultSetMetaData rsm = rs.getMetaData();
				int n = rsm.getColumnCount();
				String s ="";
				s ="Número de columnas: " + n;
				for (int i = 1; i <= n; i++) {
					s+= " \n\n Campo: " + rsm.getColumnName(i);
					s+= " \n\nTipo: " + rsm.getColumnTypeName(i);
					s+= " \n\nTamaño: " + rsm.getColumnDisplaySize(i);
					s+= " \n\nPrecisión: " + rsm.getPrecision(i);
					
				}
				muestra.setText(s);
			} catch (SQLException e) {
				System.out.println("Error en la sentencia SQL" + e.toString());
			}

		
		}
		if (ae.getSource().equals(b2)) {
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
				rs = statement.executeQuery(sentencia2);
				java.sql.ResultSetMetaData rsm = rs.getMetaData();
				int n = rsm.getColumnCount();
				String s ="";
				s ="Número de columnas: " + n;
				for (int i = 1; i <= n; i++) {
					s+= " \n\n Campo: " + rsm.getColumnName(i);
					s+= " \n\nTipo: " + rsm.getColumnTypeName(i);
					s+= " \n\nTamaño: " + rsm.getColumnDisplaySize(i);
					s+= " \n\nPrecisión: " + rsm.getPrecision(i);
					
				}
				muestra.setText(s);
			} catch (SQLException e) {
				System.out.println("Error en la sentencia SQL" + e.toString());
			}

		}
		if (ae.getSource().equals(b3)) {
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
				rs = statement.executeQuery(sentencia3);
				java.sql.ResultSetMetaData rsm = rs.getMetaData();
				int n = rsm.getColumnCount();
				String s ="";
				s ="Número de columnas: " + n;
				for (int i = 1; i <= n; i++) {
					s+= " \n\n Campo: " + rsm.getColumnName(i);
					s+= " \n\nTipo: " + rsm.getColumnTypeName(i);
					s+= " \n\nTamaño: " + rsm.getColumnDisplaySize(i);
					s+= " \n\nPrecisión: " + rsm.getPrecision(i);
					
				}
				muestra.setText(s);
			} catch (SQLException e) {
				System.out.println("Error en la sentencia SQL" + e.toString());
			}

		}
		if (ae.getSource().equals(b4)) {
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
				rs = statement.executeQuery(sentencia4);
				java.sql.ResultSetMetaData rsm = rs.getMetaData();
				int n = rsm.getColumnCount();
				String s ="";
				s ="Número de columnas: " + n;
				for (int i = 1; i <= n; i++) {
					s+= " \n\n Campo: " + rsm.getColumnName(i);
					s+= " \n\nTipo: " + rsm.getColumnTypeName(i);
					s+= " \n\nTamaño: " + rsm.getColumnDisplaySize(i);
					s+= " \n\nPrecisión: " + rsm.getPrecision(i);
					
				}
				muestra.setText(s);
			} catch (SQLException e) {
				System.out.println("Error en la sentencia SQL" + e.toString());
			}

		}
		if (ae.getSource().equals(b5)) {
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
				rs = statement.executeQuery(sentencia5);
				java.sql.ResultSetMetaData rsm = rs.getMetaData();
				int n = rsm.getColumnCount();
				String s ="";
				s ="Número de columnas: " + n;
				for (int i = 1; i <= n; i++) {
					s+= " \n\n Campo: " + rsm.getColumnName(i);
					s+= " \n\nTipo: " + rsm.getColumnTypeName(i);
					s+= " \n\nTamaño: " + rsm.getColumnDisplaySize(i);
					s+= " \n\nPrecisión: " + rsm.getPrecision(i);
					
				}
				muestra.setText(s);
			} catch (SQLException e) {
				System.out.println("Error en la sentencia SQL" + e.toString());
			}

		}
		if (ae.getSource().equals(b6)) {
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
				// Trabajar con el Schema
				String s ="";
				java.sql.DatabaseMetaData dmd = connection.getMetaData();
				s = "Base de Datos:" + dmd.getDatabaseProductName();
				s += "\nVersión:" + dmd.getDatabaseProductVersion();
				s +="\nUsuario:" + dmd.getUserName();
					
				
				muestra.setText(s);
			} catch (SQLException e) {
				System.out.println("Error en la sentencia SQL" + e.toString());
			}
		}

		
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		setVisible(false);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
