
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.*;
import java.applet.*;
import java.net.*;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Conexion conexion=new Conexion();
		Connection cm=null;
		Statement stm=null;
		ResultSet rs=null;
		try {
			cm=conexion.conectar();
			stm=cm.createStatement();
			//stm.executeUpdate("INSERT INTO `dbejemplo`.`usuario` (`Usuario`, `Clave`) VALUES ('Pepe', '23');");
			rs=stm.executeQuery("SELECT * FROM dbejemplo.usuario;");
			System.out.println("ID - usuario - clave");
			while (rs.next()) {
				int ID=rs.getInt(1);
				String usuario=rs.getString(2);
				String clave=rs.getString(3);
				System.out.println(ID +" - "+usuario+" - "+clave);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (rs!=null) {
					rs.close();
				}
				if (stm!=null) {
					stm.close();
				}
			}catch(Exception e) {
				System.out.println("error");
		}
	}
		}

}