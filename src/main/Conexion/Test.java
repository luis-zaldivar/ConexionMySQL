package Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Test {

	public static void main(String[] args) throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
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
			//System.out.println("ID - usuario - clave");
			// Creo una instancia de DocumentBuilderFactory
	         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	         // Creo un documentBuilder
	         DocumentBuilder builder = factory.newDocumentBuilder();
	         // Creo un DOMImplementation
	         DOMImplementation implementation = builder.getDOMImplementation();

	         // Creo un documento con un elemento raiz
	         Document documento = implementation.createDocument(null, "Usuarios", null);
	         documento.setXmlVersion("1.0");
			while (rs.next()) {
				int ID=rs.getInt(1);
				String usuario=rs.getString(2);
				String clave=rs.getString(3);
				//System.out.println(ID +" - "+usuario+" - "+clave);
				String IDstr=""+ID;
				Element Usuario=documento.createElement("Usuario"+IDstr);
				//ID
				Element IDT=documento.createElement("IDUsuario");
				Text TxtID=documento.createTextNode(IDstr);
				IDT.appendChild(TxtID);
				Usuario.appendChild(IDT);
				
				Element NUsuario=documento.createElement(usuario);
				Text TxtUsuario=documento.createTextNode(usuario);
				NUsuario.appendChild(TxtUsuario);
				Usuario.appendChild(NUsuario);
				
				Element NClave=documento.createElement("clave");
				Text TxtClave=documento.createTextNode(clave);
				NClave.appendChild(TxtClave);
				Usuario.appendChild(NClave);
				
				documento.getDocumentElement().appendChild(Usuario);
			}
			
			
			Source source =new DOMSource(documento);
			
	        Result result = new StreamResult(new File("C:\\Users\\laloz\\eclipse-workspace\\test2\\GatoMalo\\src\\main\\Usuarios.xml"));
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);
		} catch (SQLException e) {
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