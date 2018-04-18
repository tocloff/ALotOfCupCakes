package operativos;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import datos.GestorBD;
import jdk.nashorn.internal.ir.ContinueNode;
import modelado.Producto;
import modelado.Usuario;

/**
 * Servlet implementation class panelAdmin
 */
public class PanelAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String seccion = request.getParameter("seccion");
		String hacer= request.getParameter("hacer");
		
		//comprobacion de acceso al panel, que haya un login en sesion y ademas que el usuario sea admin
		if (request.getSession().getAttribute("usuario")!=null && ((Usuario)request.getSession().getAttribute("usuario")).getPrivilegio().equals("admin")) {
			
			if (seccion==null) {
				//menu por defecto del panel
				response.getWriter().println(""
						+ "<div class=\"col-sm-4 col-lg-4 col-md-4\" id=\"ficha\" style=\"width: 100%;\">\n" +
						"				<div class=\"thumbnail\" >\n" +
						"						<div class=\"caption\" id=\"login\" style=\"margin-top: 0%\">" +
						"						<center>\n" +
						"							<h1 >PANEL DE ADMINISTRACI&Oacute;N</h1>\n" +
						"							<hr>\n" +
						"						</center>\n" +
						"							<h3>&nbsp;&nbsp;Productos...</h3>\n" +
						"							<div class=\"span5\" id=\"form\">\n" +
						"							<center>\n" +
						"							<strong><a href=\"/ALotOfCupcakes/index.jsp?accion=gestorProductos&hacer=add\">A&ntilde;adir producto</a>&nbsp;&nbsp;\n" +
						"							<a href=\"/ALotOfCupcakes/index.jsp?accion=admin&seccion=productos&hacer=mod\">Modificar producto</a>&nbsp;&nbsp;\n" +
						"							<a href=\"/ALotOfCupcakes/index.jsp?accion=admin&seccion=productos&hacer=del\">Eliminar producto&nbsp;&nbsp;</a>\n" +
						"							<a href=\"/ALotOfCupcakes/index.jsp?accion=gestorFotos\" >Subir foto</a></strong>\n" +
						"							</center>\n" +
						"							<br>\n" +
						"							<br>\n" +
						"							</div>\n" +
						"						</div>\n" +
						"				</div>\n" +
						"		</div>");
				
			}else if (seccion!=null) { //BUSCAR (Prductos, usuarios, etc...)
				
				if (seccion.contains("produc")) {
					//imprime menu de bsucar un producto
					
					Connection c;
					try {
						c = ((DataSource) getServletContext().getAttribute("fuenteDeDatos")).getConnection();
						ArrayList<Producto> todos= GestorBD.getInstance().obtenerProductos(c);
						
						imprimirTablaProdcutos(response.getWriter(), todos,hacer);
						
						
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
					

					
				}// SE DEJA ABIERTO POR SI SE QUIERE AÑADIR FUNCIONALIDAD EN EL FUTURO simplemente seria ir poneindo condiciones para cada seccion
				
			}
				
		}
		
	}
	
	private void imprimirTablaProdcutos(PrintWriter pintor, ArrayList<Producto> prodcutos,String accion){
		
		
		pintor.println(""
				+ "<div class=\"col-sm-4 col-lg-4 col-md-4\" id=\"ficha\" style=\"width: 100%;\">\n" +
				"				<div class=\"thumbnail\" >\n" +
				"						<div class=\"caption\" id=\"login\" style=\"margin-top: 0%\">\n" +
				"						<center>\n" +
				"							<h1 >PANEL DE ADMINISTRACI&Oacute;N</h1>\n" +
				"							<hr>\n" +
				"							<br>\n" +
				"							<br>\n" +
				"							<div class=\"span5\" id=\"form\">\n" +
				"							<form action=\"/ALotOfCupcakes/index.jsp\" >\n" +
				"							<input type=hidden name=accion value=gestorProductos>\n" +
				"							<input type=hidden name=hacer value="+accion+">"+	
				"							<strong>ID producto:&nbsp;&nbsp;</strong><input type=\"number\" name=\"id\">\n" +
				"							<input type=\"submit\" value=\"BUSCAR\">\n" +
				"							</form>\n" +
				"							<br><br>\n"+
				"							Pulsa sobre el nombre del producto para seleccionarlo o buscalo por ID arriba" +
				"							<table class=\"tg6\">\n" +
				"  							<tr>\n" +
				"    						<th class=\"tg-baqh\" align=\"center\">ID</th>\n" +
				"    						<th class=\"tg-yw4l\" align=\"center\">NOMBRE</th>\n" +
				"    						<th class=\"tg-yw4l\" align=\"center\">STOCK</th>\n" +
				"  							</tr>\n");

									//rellenado de la tabla:
									for (Producto producto : prodcutos) {
		pintor.println(
				"	  						<tr>\n"+
				"   						<td class=\"tg-yw4l\" align=\"center\"><a href=\"/ALotOfCupcakes/index.jsp?accion=gestorProductos&hacer="+accion+"&id="+producto.getId()+"\">"+producto.getId()+"</a></td>\n" +
				"    						<td class=\"tg-yw4l\" align=\"center\"><a href=\"/ALotOfCupcakes/index.jsp?accion=gestorProductos&hacer="+accion+"&id="+producto.getId()+"\">"+producto.getNombre()+"</a></td>\n" +
				"    						<td class=\"tg-yw4l\" align=\"center\">"+producto.getStock()+"</td>\n" +
				"  							</tr>\n");
											}
		
		
		pintor.println(
				"							</table>\n" +
				"							</center>\n" +
				"							<br>\n" +
				"							</div>\n" +
				"						</div>\n" +
				"\n" +
				"				</div>");
		
		
		
	}
}
