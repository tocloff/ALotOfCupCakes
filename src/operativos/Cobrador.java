package operativos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import datos.GestorBD;
import modelado.LineaCompra;
import modelado.Producto;
import modelado.Usuario;

/**
 * Servlet implementation class Cobrador
 */
public class Cobrador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean pagoOK=false;
		
		//aqui ira el codigo correspondiente a cada forma de pago que tenga para vaalidar
		//
		//
		//------------------------->AQUI SE COBRA LA PASTA<-----------------------------//
										pagoOK=true;
		//
		//FIN DEL COBRO
		
		if (pagoOK) { // Se da las gracias, se vacia el carrito y se registra la compra en la base de datos.
			
			response.getWriter().println(""
					+ "		<div class=\"col-sm-4 col-lg-4 col-md-4\" id=\"ficha\">\n" +
					"				<div class=\"thumbnail\">\n" +
					"					<center>\n" +
					"						<div class=\"caption\" id=\"login\">\n" +
					"							<h2>&iexcl;GRACIAS POR TU COMPRA!</h2>"+
					"							<h4>Tu compra se ha efectuado en breve recibiras tu pedido.</h4>\n" +
					"							<h4>Se te enviará un correo con el n&uacute;mero de seguimiento.</h4>\n" +
					"							<br>\n" +
					"							<div class=\"span5\" id=\"form\">\n" +
					"							<form method=\"get\" action=\"/ALotOfCupcakes/index.jsp?accion=index\">\n" +
					"							<input type=\"submit\" value=\"OK\">"+
					"							</form>\n" +
					"							<br>\n" +
					"							<br>\n" +
					"							</div>\n" +
					"						</div>\n" +
					"					</center>\n" +
					"				</div>\n" +
					"		</div>");
			
			borrarCarrito(request);
			registrarCompra(request);
		
		}else{
			
			//aqui el codigo en caso de que el banco o paypal rechacen la compra
		}
	}
	
	public void registrarCompra(HttpServletRequest request){
		
		Connection c=null;
		try {
			c = ((DataSource) getServletContext().getAttribute("fuenteDeDatos")).getConnection();

		
		Usuario propietario= (Usuario)request.getSession().getAttribute("usuario");
		ArrayList<LineaCompra> lineas= new ArrayList<>();
		Object[] productos= (Object[])request.getSession().getAttribute("productos");
		Collection<Integer> cantidades= (Collection<Integer>)request.getSession().getAttribute("cantidades");
		
		
		//obtencion de lineas
		int contador=0;
		for (Integer cantidad : cantidades) {
			lineas.add(new LineaCompra(((Producto)productos[contador]).getId(), cantidad));
			contador++;
		}
		
		GestorBD.getInstance().insertarCompras(c, propietario, lineas);
		
		c.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void borrarCarrito(HttpServletRequest request){
		
		//usar este response que parece ser que es el unico que peude escribir cookies
		HttpServletResponse responseValido= ((HttpServletResponse)request.getSession().getAttribute("response"));
		
		//recuperacion de las cookies y cambio de su tiempo de vida a cero con lo que se autoborran
		Cookie[] galletas= request.getCookies();
		for (Cookie cookie : galletas) {
			if (!cookie.getName().equals("JSESSIONID")) {
				cookie.setMaxAge(0);
				responseValido.addCookie(cookie);
			}
			
		}
	}

}
