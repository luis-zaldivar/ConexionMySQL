package Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	public static final String Control="com.mysql.jdbc.Driver";
	public static final String Direccion="jdbc:mysql://localhost:3306/DBEjemplo";
	public static final String Usuario="root";
	public static final String Contraseña="root";
	static{
		try{
			Class.forName(Control);
		}catch(ClassNotFoundException e){
			System.out.println("error de conexion");
			e.printStackTrace();
		}
	}
	public Connection conectar() {
		Connection conexion=null;
		try{
			conexion=DriverManager.getConnection(Direccion,Usuario,Contraseña);
			System.out.println("Conexion ok");
		}catch(SQLException e) {
			System.out.println("error de conexion");
			e.printStackTrace();
		}
		return conexion;
	}
}
