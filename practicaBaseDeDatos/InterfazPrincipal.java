package practicaBaseDeDatos;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import practicaBaseDeDatos.Centros.BorradoCentros;
import practicaBaseDeDatos.Centros.InsertarCentros;
import practicaBaseDeDatos.Centros.ModificarCentros;
import practicaBaseDeDatos.Centros.RecorridoCentros;
import practicaBaseDeDatos.CentrosCursos.BorradoCentrosCursos;
import practicaBaseDeDatos.CentrosCursos.InsertarCentrosCursos;
import practicaBaseDeDatos.CentrosCursos.ModificarCentrosCursos;
import practicaBaseDeDatos.CentrosCursos.RecorridoCentrosCursos;
import practicaBaseDeDatos.Cursos.BorradoCursos;
import practicaBaseDeDatos.Cursos.InsertarCursos;
import practicaBaseDeDatos.Cursos.ModificarCursos;
import practicaBaseDeDatos.Cursos.RecorridoCursos;
import practicaBaseDeDatos.CursosEstudiantes.BorradoCursosEstudiantes;
import practicaBaseDeDatos.CursosEstudiantes.InsertarCursosEstudiantes;
import practicaBaseDeDatos.CursosEstudiantes.ModificarCursosEstudiantes;
import practicaBaseDeDatos.CursosEstudiantes.RecorridoCursosEstudiantes;
import practicaBaseDeDatos.CursosLibros.BorradoCursosLibros;
import practicaBaseDeDatos.CursosLibros.InsertarCursosLibros;
import practicaBaseDeDatos.CursosLibros.ModificarCursosLibros;
import practicaBaseDeDatos.CursosLibros.RecorridoCursosLibros;
import practicaBaseDeDatos.Estudiantes.BorradoEstudiantes;
import practicaBaseDeDatos.Estudiantes.InsertarEstudiantes;
import practicaBaseDeDatos.Estudiantes.ModificarEstudiantes;
import practicaBaseDeDatos.Estudiantes.RecorridoEstudiantes;
import practicaBaseDeDatos.Libros.BorradoLibros;
import practicaBaseDeDatos.Libros.InsertarLibros;
import practicaBaseDeDatos.Libros.ModificarLibros;
import practicaBaseDeDatos.Libros.RecorridoLibros;
import practicaBaseDeDatos.Profesores.BorradoProfesores;
import practicaBaseDeDatos.Profesores.InsertarProfesores;
import practicaBaseDeDatos.Profesores.ModificarProfesores;
import practicaBaseDeDatos.Profesores.RecorridoProfesores;

public class InterfazPrincipal extends Frame implements WindowListener, ActionListener {

	private static final long serialVersionUID = 1L;
	
	Image logo;
	
	Toolkit herramienta;

	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/academia";
	String login = "root";
	String password = "studium";

	MenuBar barraBD = new MenuBar();

	Menu datos = new Menu("Datos");
	Menu metaD = new Menu("MetaDatos");

	Menu profesores = new Menu("Profesores");
	Menu centros = new Menu("Centros");
	Menu cursos = new Menu("Cursos");
	Menu estudiantes = new Menu("Estudiantes");
	Menu libros = new Menu("Libros");
	Menu cursosEstudiantes = new Menu("Cursos-Estudiantes");
	Menu centrosCursos = new Menu("Centros-Cursos");
	Menu cursosLibros = new Menu("Cursos-Libros");

	MenuItem generica = new MenuItem("Genérica");

	//Profesores
	MenuItem recorridoProfesores = new MenuItem("Mostrar clase");
	MenuItem insercionProfesores = new MenuItem("Insertar datos");
	MenuItem borradoIdProfesores = new MenuItem("Borrado con ID");
	MenuItem modfIdProfesores = new MenuItem("Modificaciones con ID");

	//Centros
	MenuItem recorridoCentros = new MenuItem("Mostrar clase");
	MenuItem insercionCentros = new MenuItem("Insertar datos");
	MenuItem borradoIdCentros = new MenuItem("Borrado con ID");
	MenuItem modfIdCentros = new MenuItem("Modificaciones con ID");

	//Cursos
	MenuItem recorridoCursos = new MenuItem("Mostrar clase");
	MenuItem insercionCursos = new MenuItem("Insertar datos");
	MenuItem borradoIdCursos = new MenuItem("Borrado con ID");
	MenuItem modfIdCursos = new MenuItem("Modificaciones con ID");

	//Alumnos
	MenuItem recorridoEstudiantes = new MenuItem("Mostrar clase");
	MenuItem insercionEstudiantes = new MenuItem("Insertar datos");
	MenuItem borradoIdEstudiantes = new MenuItem("Borrado con ID");
	MenuItem modfIdEstudiantes = new MenuItem("Modificaciones con ID");

	//Libros
	MenuItem recorridoLibros = new MenuItem("Mostrar clase");
	MenuItem insercionLibros = new MenuItem("Insertar datos");
	MenuItem borradoIdLibros = new MenuItem("Borrado con ID");
	MenuItem modfIdLibros = new MenuItem("Modificaciones con ID");

	//Tablas FK
	MenuItem recorridoCE = new MenuItem("Mostrar clase");
	MenuItem insercionCE = new MenuItem("Insertar datos");
	MenuItem borradoIdCE = new MenuItem("Borrado con ID");
	MenuItem modfIdCE = new MenuItem("Modificaciones con ID");

	MenuItem recorridoCC = new MenuItem("Mostrar clase");
	MenuItem insercionCC = new MenuItem("Insertar datos");
	MenuItem borradoIdCC = new MenuItem("Borrado con ID");
	MenuItem modfIdCC = new MenuItem("Modificaciones con ID");

	MenuItem recorridoCL = new MenuItem("Mostrar clase");
	MenuItem insercionCL = new MenuItem("Insertar datos");
	MenuItem borradoIdCL = new MenuItem("Borrado con ID");
	MenuItem modfIdCL = new MenuItem("Modificaciones con ID");
	
	RecorridoCentros recCent = new RecorridoCentros();
	ModificarCentros modIDCent = new ModificarCentros();
	BorradoCentros borradoCent = new BorradoCentros();
	InsertarCentros insertCent = new InsertarCentros();

	RecorridoProfesores recProf = new RecorridoProfesores();
	ModificarProfesores modIDProf = new ModificarProfesores();
	BorradoProfesores borradoProf = new BorradoProfesores();
	InsertarProfesores insertProf = new InsertarProfesores();

	RecorridoCursos recCursos = new RecorridoCursos();
	ModificarCursos modCursos = new ModificarCursos();
	BorradoCursos borradoCurs = new BorradoCursos();
	InsertarCursos insertCurs = new InsertarCursos();

	RecorridoEstudiantes recEstud = new RecorridoEstudiantes();
	ModificarEstudiantes modIDEstud = new ModificarEstudiantes();
	BorradoEstudiantes borradoEst = new BorradoEstudiantes();
	InsertarEstudiantes insertEst = new InsertarEstudiantes();

	RecorridoLibros recLibros = new RecorridoLibros();
	ModificarLibros modLibros = new ModificarLibros();
	BorradoLibros borradoLib = new BorradoLibros();
	InsertarLibros insertLib = new InsertarLibros();
	
	RecorridoCentrosCursos recCenCur = new RecorridoCentrosCursos();
	ModificarCentrosCursos modCenCur = new ModificarCentrosCursos();
	BorradoCentrosCursos borradoCenCur = new BorradoCentrosCursos();
	InsertarCentrosCursos insertCenCur = new InsertarCentrosCursos();
	
	RecorridoCursosEstudiantes recCurEst = new RecorridoCursosEstudiantes();
	ModificarCursosEstudiantes modCurEst = new ModificarCursosEstudiantes();
	BorradoCursosEstudiantes borradoCurEst = new BorradoCursosEstudiantes();
	InsertarCursosEstudiantes insertCurEst = new InsertarCursosEstudiantes();
	
	RecorridoCursosLibros recCurLib = new RecorridoCursosLibros();
	ModificarCursosLibros modCurLib = new ModificarCursosLibros();
	BorradoCursosLibros borradoCurLib = new BorradoCursosLibros();
	InsertarCursosLibros insertCurLib = new InsertarCursosLibros();
	
	GUIMetaDatos metDat = new GUIMetaDatos();
	
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	public InterfazPrincipal() {
		// TODO Auto-generated constructor stub
		setLayout(new FlowLayout());
		setTitle("Academia Raspata");

		setMenuBar(barraBD);

		barraBD.add(datos);
		barraBD.add(metaD);
		datos.add(profesores);
		datos.add(centros);
		datos.add(estudiantes);
		datos.add(cursos);
		datos.add(libros);
		datos.add(cursosEstudiantes);
		datos.add(centrosCursos);
		datos.add(cursosLibros);
		metaD.add(generica);
		profesores.add(recorridoProfesores);
		profesores.add(insercionProfesores);
		profesores.add(borradoIdProfesores);
		profesores.add(modfIdProfesores);
		centros.add(recorridoCentros);
		centros.add(insercionCentros);
		centros.add(borradoIdCentros);
		centros.add(modfIdCentros);
		estudiantes.add(recorridoEstudiantes);
		estudiantes.add(insercionEstudiantes);
		estudiantes.add(borradoIdEstudiantes);
		estudiantes.add(modfIdEstudiantes);
		cursos.add(recorridoCursos);
		cursos.add(insercionCursos);
		cursos.add(borradoIdCursos);
		cursos.add(modfIdCursos);
		libros.add(recorridoLibros);
		libros.add(insercionLibros);
		libros.add(borradoIdLibros);
		libros.add(modfIdLibros);
		cursosEstudiantes.add(recorridoCE);
		cursosEstudiantes.add(insercionCE);
		cursosEstudiantes.add(borradoIdCE);
		cursosEstudiantes.add(modfIdCE);
		centrosCursos.add(recorridoCC);
		centrosCursos.add(insercionCC);
		centrosCursos.add(borradoIdCC);
		centrosCursos.add(modfIdCC);
		cursosLibros.add(recorridoCL);
		cursosLibros.add(insercionCL);
		cursosLibros.add(borradoIdCL);
		cursosLibros.add(modfIdCL);

		generica.addActionListener(this);
		recorridoProfesores.addActionListener(this);
		insercionProfesores.addActionListener(this);
		borradoIdProfesores.addActionListener(this);
		modfIdProfesores.addActionListener(this);
		recorridoCentros.addActionListener(this);
		insercionCentros.addActionListener(this);
		borradoIdCentros.addActionListener(this);
		modfIdCentros.addActionListener(this);
		recorridoEstudiantes.addActionListener(this);
		insercionEstudiantes.addActionListener(this);
		borradoIdEstudiantes.addActionListener(this);
		modfIdEstudiantes.addActionListener(this);
		recorridoCursos.addActionListener(this);
		insercionCursos.addActionListener(this);
		borradoIdCursos.addActionListener(this);
		modfIdCursos.addActionListener(this);
		recorridoLibros.addActionListener(this);
		insercionLibros.addActionListener(this);
		borradoIdLibros.addActionListener(this);
		modfIdLibros.addActionListener(this);
		recorridoCE.addActionListener(this);
		insercionCE.addActionListener(this);
		borradoIdCE.addActionListener(this);
		modfIdCE.addActionListener(this);
		recorridoCC.addActionListener(this);
		insercionCC.addActionListener(this);
		borradoIdCC.addActionListener(this);
		modfIdCC.addActionListener(this);
		recorridoCL.addActionListener(this);
		insercionCL.addActionListener(this);
		borradoIdCL.addActionListener(this);
		modfIdCL.addActionListener(this);

		addWindowListener(this);
		setSize(525, 435);
		setResizable(false);
		
		
		//carga de la imagen y la herramienta
		herramienta = getToolkit();
		logo = herramienta.getImage("logo_tumblr_R.png");
		setVisible(true);
	}
		
	

	public static void main(String[] args) {
		new InterfazPrincipal();

	}
	public void paint(Graphics g)
	{
	// Dibujar la imagen
	g.drawImage(logo,4,23,this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		if (arg0.getSource().equals(recorridoCC)) {
			recCenCur.setVisible(true);
		}
		if (arg0.getSource().equals(insercionCC)) {
			insertCenCur.setVisible(true);
		}
		if (arg0.getSource().equals(modfIdCC)) {
			modCenCur.setVisible(true);
		}
		if (arg0.getSource().equals(borradoIdCC)) {
			borradoCenCur.setVisible(true);
		}
		if (arg0.getSource().equals(recorridoCE)) {
			recCurEst.setVisible(true);
		}
		if (arg0.getSource().equals(insercionCE)) {
			insertCurEst.setVisible(true);
		}
		if (arg0.getSource().equals(modfIdCE)) {
			modCurEst.setVisible(true);
		}
		if (arg0.getSource().equals(borradoIdCE)) {
			borradoCurEst.setVisible(true);
		}
		if (arg0.getSource().equals(recorridoCL)) {
			recCurLib.setVisible(true);
		}
		if (arg0.getSource().equals(insercionCL)) {
			insertCurLib.setVisible(true);
		}
		if (arg0.getSource().equals(modfIdCL)) {
			modCurLib.setVisible(true);
		}
		if (arg0.getSource().equals(borradoIdCL)) {
			borradoCurLib.setVisible(true);
		}
		if (arg0.getSource().equals(recorridoCentros)) {
			recCent.setVisible(true);
		}
		if (arg0.getSource().equals(modfIdCentros)) {
			modIDCent.setVisible(true);
		}
		if (arg0.getSource().equals(borradoIdCentros)) {
			borradoCent.setVisible(true);
		}
		if (arg0.getSource().equals(insercionCentros)) {
			insertCent.setVisible(true);
		}
		if (arg0.getSource().equals(recorridoProfesores)) {
			recProf.setVisible(true);
		}
		if (arg0.getSource().equals(insercionProfesores)) {
			insertProf.setVisible(true);
		}
		if (arg0.getSource().equals(borradoIdProfesores)) {
			borradoProf.setVisible(true);
		}
		if (arg0.getSource().equals(modfIdProfesores)) {
			modIDProf.setVisible(true);
		}
		if (arg0.getSource().equals(recorridoCursos)) {
			recCursos.setVisible(true);
		}
		if (arg0.getSource().equals(insercionCursos)) {
			insertCurs.setVisible(true);
		}
		if (arg0.getSource().equals(borradoIdCursos)) {
			borradoCurs.setVisible(true);
		}
		if (arg0.getSource().equals(modfIdCursos)) {
			modCursos.setVisible(true);
		}
		if (arg0.getSource().equals(recorridoEstudiantes)) {
			recEstud.setVisible(true);
		}
		if (arg0.getSource().equals(insercionEstudiantes)) {
			insertEst.setVisible(true);
		}
		if (arg0.getSource().equals(borradoIdEstudiantes)) {
			borradoEst.setVisible(true);
		}
		if (arg0.getSource().equals(modfIdEstudiantes)) {
			modIDEstud.setVisible(true);
		}
		if (arg0.getSource().equals(recorridoLibros)) {
			recLibros.setVisible(true);
		}
		if (arg0.getSource().equals(insercionLibros)) {
			insertLib.setVisible(true);
		}
		if (arg0.getSource().equals(borradoIdLibros)) {
			borradoLib.setVisible(true);
		}
		if (arg0.getSource().equals(modfIdLibros)) {
			modLibros.setVisible(true);
		}
		if(arg0.getSource().equals(generica)){
			metDat.setVisible(true);
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
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		System.exit(0);

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
