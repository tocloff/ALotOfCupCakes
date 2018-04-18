package operativos;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import com.sun.istack.internal.Nullable;
import datos.GestorBD;
import modelado.Producto;


public class GestorProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String hacer= request.getParameter("hacer");
		String id= request.getParameter("id");
		Connection c=null;
		
		try {
			c=((DataSource) getServletContext().getAttribute("fuenteDeDatos")).getConnection();

		
		switch (hacer) {
		
		case "add":
			printFormularioProductos(response.getWriter(),null, "NUEVO PRODUCTO",hacer);
			break;
			
		case "mod":
			Producto p= GestorBD.getInstance().obtenerProducto(c, Integer.parseInt(id)).get(0);
			printFormularioProductos(response.getWriter(),p, "MODIFICACION DE PRODUCTO",hacer);
			break;
			
		case "del":
			GestorBD.getInstance().eliminarProdcuto(c, Integer.parseInt(id));
			pintarOk(response.getWriter());
			break;

		}

		c.close();
		
		} catch (Exception e) {
			
			//Error al hacer operaciones sobre los prodcutos
			response.getWriter().println(
					"<div class=\"col-sm-4 col-lg-4 col-md-4\" id=\"ficha\">\n" +
					"				<div class=\"thumbnail\">\n" +
					"					<center>\n" +
					"						<div class=\"caption\" id=\"login\">\n" +
					"							<h4>&iexcl;¡ERROR, NO SE ENCUENTRA EL PRODUCTO!</h4>\n" +
					"							<br>\n" +
					"							<br>\n" +
					"							<a href=\"/ALotOfCupcakes/index.jsp?accion=admin\"\"><button>VOLVER</button></a>\n" +
					"						</div>\n" +
					"					</center>\n" +
					"				</div>\n" +
					"			</div>");
			e.printStackTrace();
		}
		
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		 Enumeration<String> nombre= req.getParameterNames();
		 while (nombre.hasMoreElements()) {
			String string = (String) nombre.nextElement();
			
		}
		 
		
		Connection c;
		Producto p= new Producto(0
				, req.getParameter("nombre")
				, req.getParameter("descri")
				, req.getParameter("categoria")
				, Double.parseDouble(req.getParameter("precio"))
				, Double.parseDouble(req.getParameter("descuento"))
				, Integer.parseInt(req.getParameter("stock"))
				, req.getParameter("foto"));
		
		
		try {
		
			c = ((DataSource) getServletContext().getAttribute("fuenteDeDatos")).getConnection();
			
			String confirma= req.getParameter("confirma");
		
			switch (confirma) {
			case "add":
				GestorBD.getInstance().insertarProducto(c, p);
				pintarOk(resp.getWriter());
				break;
			
			case "mod":
				GestorBD.getInstance().modificarProducto(c, p, Integer.parseInt(req.getParameter("id")));
				pintarOk(resp.getWriter());
				break;
			}
		
		c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	private void printFormularioProductos(PrintWriter pintor,@Nullable Producto p, String titulo, String accion){
		
		
		pintor.println(
				"<div class=\"col-sm-4 col-lg-4 col-md-4\" id=\"ficha\" style=\"width: 100%;\">\n" +
"				<div class=\"thumbnail\" >\n" +
"						<div class=\"caption\" id=\"login\" style=\"margin-top: 0%\">\n" +
"						<center>\n" +
"							<h1 >PANEL DE ADMINISTRACION</h1>\n" +
"							<hr>\n" +
"							<br>\n" +
"							<h4>"+titulo+"</h4>\n" +
"							<div class=\"span5\" id=\"form\">\n" +
"							<form action=\"/ALotOfCupcakes\" method=\"post\" >\n" +
"							<input type=hidden name=accion value=gestorProductos>"+		
"							<input type=hidden name=confirma value="+accion+">"+ //pas la accion para que sepa dopost que hacer
"							<input type=hidden name=id value="+String.valueOf((p!=null)?p.getId():0)+">"+ //pasa el ide para que dopost sepa el prodcuto a modificar
"							<br>\n" +
"							<table>\n" +
"							<tr><td align=\"right\">Nombre:&nbsp;&nbsp;</td><td><input type=\"text\" name=\"nombre\" value=\""+((p!=null)?p.getNombre():"")+"\" required=\"required\"></td></tr>\n" +
"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
"							<tr><td align=\"right\">Descripci&oacute;n:&nbsp;&nbsp;</td><td>​<textarea name=\"descri\" rows=\"6\" cols=\"35\">"+((p!=null)?p.getDescripcion():"")+"</textarea></td></tr>\n" +
"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
"							<tr><td align=\"right\">Categoria:&nbsp;&nbsp;</td><td align=\"justify\"><select name=\"categoria\" required=\"required\">\n" +
"									  <option value=\"cupcakes\" "+((p!=null && p.getCategoria().equals("cupcakes")?"selected=\"selected\"":""))+">Cupcakes</option>\n" +
"									  <option value=\"empapados\" "+((p!=null && p.getCategoria().equals("empapados")?"selected=\"selected\"":""))+">Empapados</option>\n" +
"									  <option value=\"pasteles\" "+((p!=null && p.getCategoria().equals("pasteles")?"selected=\"selected\"":""))+">Pasteles</option>\n" +
"									  <option value=\"brownies\" "+((p!=null && p.getCategoria().equals("brownies")?"selected=\"selected\"":""))+">Brownies</option>\n" +
"									  <option value=\"pays\" "+((p!=null && p.getCategoria().equals("pays")?"selected=\"selected\"":""))+">Pays</option>\n" +
"									  </select></td></tr>\n" +
"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
"							<tr><td align=\"right\">Precio:&nbsp;&nbsp;</td><td><input type=\"number\" step=\"any\" name=\"precio\" value=\""+((p!=null)?p.getPrecio():"0")+"\" required=\"required\"></td></tr>\n" +
"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
"							<tr><td align=\"right\">Descuento:&nbsp;&nbsp;</td><td><input type=\"number\" step=\"any\" name=\"descuento\" value=\""+((p!=null)?p.getDescuento():"0")+"\"></td></tr>\n" +
"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
"							<tr><td align=\"right\">Stock:&nbsp;&nbsp;</td><td><input type=\"number\" name=\"stock\" value=\""+((p!=null)?p.getStock():"0")+"\" required=\"required\"></td></tr>\n" +
"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
"							<tr><td align=\"right\">Foto:&nbsp;&nbsp;</td><td><input type=\"text\" name=\"foto\" value=\""+((p!=null)?p.getFoto():"ninguna.png")+"\" required=\"required\"></td></tr>\n" +
"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
"							<tr><td align=\"center\"><input type=\"reset\" value=\"RESET\"></td><td align=\"center\"><input type=\"submit\" value=\"GUARDAR\"></td></tr>\n" +
"							</table>\n" +
"							</form>\n" +
"							</center>\n" +
"							<br>\n" +
"							<br>\n" +
"							</div>\n" +
"						</div>\n" +
"				</div>");
		
		
	}
	
	private void pintarOk(PrintWriter pintor){
		
		pintor.println(
				"<div class=\"col-sm-4 col-lg-4 col-md-4\" id=\"ficha\" style=\"width: 100%;\">\n" +
				"				<div class=\"thumbnail\">\n" +
				"					<center>\n" +
				"						<div class=\"caption\" id=\"login\">\n" +
				"							<h4>&iexcl;¡OPERACION REALIZADA!</h4>\n" +
				"							<br>\n" +
				"							<br>\n" +
				"							<a href=\"/ALotOfCupcakes/index.jsp?accion=admin\"\"><button>VOLVER</button></a>\n" +
				"						</div>\n" +
				"					</center>\n" +
				"				</div>\n" +
				"			</div>");
		
	}

}
