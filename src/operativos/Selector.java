/**
 * MA-ITG ALotOfCupcakes Web Application
 * Author: Thomas Enrique Mejia Anaya
 */
package operativos;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.regexp.internal.RE;

/**
 * Servlet implementation class Selector
 */
public class Selector extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String tmp = request.getParameter("accion");
		String accion = (tmp != null) ? tmp : "index";
		response.setContentType("text/html");
		String destino=null;
		RequestDispatcher despachador;
		
		switch (accion) {

		case "index":
		case "productos":
			destino = "CargadorProductos";
			break;
			
		case "ficha":
			destino="CargadorProducto";
			break;
		
		case "login":
			destino="Login";
			break;
		
		case "salir":
			destino="CerrarSesion";
			break;
		
		case "config":
			destino="Configuracion";
			break;
			
		case "registrar":
			destino="Registrar";
			break;	
		
		case "carrito":
			destino="Carrito";
			break;
		
		case "Pagar":
			destino="Pagar";
			break;	
		
		case "cobrar":
			destino="Cobrador";
			break;
			
		case "admin":
			destino="PanelAdmin";
			break;
			
		case "gestorProductos":
			destino="GestorProductos";
			break;
		
		case "gestorFotos":
			destino="GestorFotos";
			break;
			
		}
		

		if(destino!=null){
		despachador = request.getRequestDispatcher(destino);
		despachador.include(request, response);
		
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tmp = request.getParameter("accion");
		String accion = (tmp != null) ? tmp : "index";
		response.setContentType("text/html");
		String destino=null;
		RequestDispatcher despachador;
		
		//por aqui van las llamadas protegidas
		switch (accion) {

		case "login":
			destino="Login";
			break;
			
		case "config":
			destino="Configuracion";
			break;
			
		case "registrar":
			destino="Registrar";
			break;	
			
		case "Pagar":
			destino="Pagar";
			break;	
			
		case "cobrar":
			destino="Cobrador";
			break;	
		
		case "gestorProductos":
			destino="GestorProductos";
			break;
			
		case "gestorFotos":
			destino="GestorFotos";
			break;
		}

		if(destino!=null){
		despachador = request.getRequestDispatcher(destino);
		despachador.include(request, response);
		}
		
	}
	
	

}
