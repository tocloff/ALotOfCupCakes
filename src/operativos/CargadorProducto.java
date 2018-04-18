package operativos;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import datos.GestorBD;
import modelado.Producto;

/**
 * Servlet implementation class CargadorProducto
 */
public class CargadorProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idProducto = Integer.parseInt(request.getParameter("id"));
		Producto p=null;
		
		try {
			Connection conexion = ((DataSource) getServletContext().getAttribute("fuenteDeDatos")).getConnection();
			p= GestorBD.getInstance().obtenerProducto(conexion, idProducto).get(0);


		PrintWriter pintor= response.getWriter();
		
		pintor.println("<div class=\"col-sm-4 col-lg-4 col-md-4\" id=\"ficha\">\n" +
					"				<div class=\"thumbnail\">\n" +
					"					<img\n" +
					"						src=\"/ALotOfCupcakes/imagenesProductos/"+p.getFoto()+"\"\n" +
					"						alt=\"\">\n" +
					"					<div class=\"caption\">\n" +
					"						<h4 class=\"pull-right\">"+"$"+p.getPrecio()+"\n" +
					"						<h4>\n"+p.getNombre()+"\n" +
					"						</h4>\n" +
					"						<p>"+p.getDescripcion()+"</p>\n" +
					"					</div>\n" +
					"					<center>\n" +
					"					<div class=\"span5\" id=\"form\">\n" +
					"						<form method=\"get\" action=\"/ALotOfCupcakes/index.jsp\">\n" +
					"							<input type=\"hidden\" name=accion value=\"carrito\">"+
					"							<p>&nbsp;</p>\n" +
					"							<label>Cantidad:</label> <input type=\"text\" name=\"c\" class=\"span1\" value=\"1\">\n" +
					"							<button class=\"btn btn-inverse\" type=\"submit\">Add to cart</button>\n" +
					"							<input type=\"hidden\" name=p value=\""+p.getId()+"\">"+
					"						</form>\n" +
					"					</div>\n" +
					"					</center>\n" +
					"				</div>\n" +
					"			</div>\n" +
					"		</div>");
		conexion.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		}
	}

}
