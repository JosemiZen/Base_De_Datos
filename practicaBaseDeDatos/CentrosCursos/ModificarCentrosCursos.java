package practicaBaseDeDatos.CentrosCursos;

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

public class ModificarCentrosCursos extends Frame implements WindowListener, ActionListener, ItemListener {
	private static final long serialVersionUID = 1L;

	TextField idCursoFK = new TextField(20);
	TextField turnos = new TextField(20);
	TextField idCentroFK = new TextField(20);

	Button aceptar = new Button("Aceptar");
	Button cancelar = new Button("Cancelar");
	Button aceptar1 = new Button("Aceptar");
	Button cancelar1 = new Button("Cancelar");
	Dialog dialogo = new Dialog(this, "Datos a actualizar", true);
	Dialog exito = new Dialog(this, "Exito", true);
	Dialog confirm = new Dialog(this, "Cuidado", true);
	Label lblidCursoFK = new Label("id del curso");
	Label lblturnos = new Label("turnos del curso");
	Label expTurnos = new Label("1 = Mañana \t 0 = Tarde");
	Label lblidCentroFK = new Label("centro donde se imparte");

	Label mensaje = new Label("Seleccionar curso a modificar:");
	Label e = new Label("Operación realizada correctamente!");
	Label lblConfirm = new Label("¿Esta usted seguro de realizar estos cambios?");
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
	
	
	public ModificarCentrosCursos() {
		setLayout(new FlowLayout());
		setTitle("Modificacion Cursos");
		dialogo.setLayout(new FlowLayout());
		dialogo.setSize(200, 250);
		setSize(500, 100);
		dialogo.setResizable(false);
		add(mensaje);
		add(lista);
		idCursoFK.setEditable(false);
		turnos.setEditable(false);
		dialogo.setLayout(new GridLayout(9, 1));
		dialogo.add(lblidCursoFK);
		dialogo.add(idCursoFK);
		dialogo.add(lblturnos);
		dialogo.add(expTurnos);
		dialogo.add(turnos);
		dialogo.add(lblidCentroFK);
		dialogo.add(idCentroFK);

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
		recargaCC(lista, connection);
	}
	//creamos un metodo para que sea mas rapido la carga de los datos en la lista
	public Choice recargaCC(Choice lista, Connection connection) {
		lista.removeAll();
		lista.add("Seleccione el curso a modificar la ubicacion");
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery("SELECT * FROM centroscursos");
			while (rs.next()) {
				s = rs.getString("idCursoFK");
				s = s + "-" + rs.getString("turnos");

				lista.add(s);

			}

		} catch (SQLException e) {
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		return lista;

	}

	public static void main(String[] args) {
		new ModificarCentrosCursos();
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
			rs = statement.executeQuery("SELECT * FROM centroscursos WHERE idCursoFK = '" + array[0] + "'");

			while (rs.next()) {
				idCursoFK.setText(rs.getString("idCursoFK"));
				turnos.setText(rs.getString("turnos"));
				idCentroFK.setText(rs.getString("idCentroFK"));

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

				statement.executeUpdate("UPDATE CentrosCursos SET turnos ='" + turnos.getText() + "', idCentroFK ='"
						+ idCentroFK.getText() + "' WHERE idCursoFK ='" + idCursoFK.getText() + "'");

				cerrar = 1;
				confirm.setVisible(false);
				exito.setVisible(true);
				recargaCC(lista, connection);
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
