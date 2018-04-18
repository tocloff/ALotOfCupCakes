package operativos;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.mysql.jdbc.Statement;

import datos.GestorBD;
import modelado.Producto;

/**
 * Servlet implementation class CargadorProductos
 */
public class CargadorProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection conexion = null;
		try {
			conexion = ((DataSource) getServletContext().getAttribute("fuenteDeDatos")).getConnection();
		

		response.setContentType("text/html");
		String productos_requeridos = request.getParameter("productos");
		ArrayList<Producto> lista;
		PrintWriter pintor = response.getWriter();
		ArrayList<Producto> productos=null; //cargarListaProductos(conexion, productos_requeridos);

		
		if (request.getParameter("accion")==null || (request.getParameter("accion").equals("index") && productos_requeridos==null)) {
			
			productos=GestorBD.getInstance().obtenerProductos(conexion,(double)0);
		}else if(productos_requeridos==null || productos_requeridos.equals("todos")){
			
			productos=GestorBD.getInstance().obtenerProductos(conexion);
		}else{
			productos=GestorBD.getInstance().obtenerProductos(conexion,productos_requeridos);
		}
		
		pintorProductos(pintor, productos);
		conexion.close();
		}catch (SQLException e) {
			System.out.println("ERROR AL INTERTAR CONEXION EN EL SERVLET CARGADORDEPRODUCTOS");
			e.printStackTrace();
		}
	}

	private void pintorProductos(PrintWriter pintor, ArrayList<Producto> productos) {

		int contador = 0;

		for (Producto p : productos) {

			String nombre = (p.getNombre().length() > 24) ? p.getNombre().substring(0, 21) + "..." : p.getNombre();
			
			pintor.println("<div class=\"col-sm-4 col-lg-4 col-md-4\">\n" +
					"				<div class=\"thumbnail\">\n" +
					" 				<a href=\"/ALotOfCupcakes/index.jsp?accion=ficha&id="+p.getId()+"\">\n"+
					"					<img src=\"/ALotOfCupcakes/imagenesProductos/"+p.getFoto()+"\" alt=\"\">\n" +
					"					<div class=\"caption\">\n" +
					"						<h4 class=\"pull-right\">"+"$"+p.getPrecio()+"\n" +
					"						<h4>\n" +nombre+"</h4></a>\n" +
					"						<p>"+p.getDescripcion()+"</p>\n" +
					"					</div>\n" +
					"					<div class=\"ratings\">\n" +
					"						<p class=\"pull-right\">30 reviews</p>\n" +
					"						<p>\n" +
					"							<span class=\"glyphicon glyphicon-star\"></span> <span\n" +
					"								class=\"glyphicon glyphicon-star\"></span> <span\n" +
					"								class=\"glyphicon glyphicon-star\"></span> <span\n" +
					"								class=\"glyphicon glyphicon-star-empty\"></span> <span\n" +
					"								class=\"glyphicon glyphicon-star-empty\"></span>\n" +
					"						</p>\n" +
					"					</div>\n" +
					"				</div>\n" +
					"		</div>");
		}
	}
}
