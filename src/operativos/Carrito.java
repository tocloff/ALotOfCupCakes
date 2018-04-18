package operativos;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import datos.GestorBD;
import modelado.Producto;
import modelado.Usuario;

/**
 * Servlet implementation class Carrito
 */
public class Carrito extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//comprobacion de sesion iniciada
		if (request.getSession().getAttribute("usuario")!=null) {
			
				String id_producto= request.getParameter("p");
				String cantidad= request.getParameter("c");
				HashMap<Integer, Integer> carrito= new HashMap<>();
			
				//recuperacion de las cookies
				Cookie[] galletas= request.getCookies();
				for (Cookie cookie : galletas) {
					if (!cookie.getName().equals("JSESSIONID")) {
						carrito.put(Integer.parseInt(cookie.getName()), Integer.parseInt(cookie.getValue()));
					}
					
				}
			
			if(id_producto!=null){
				
				//si la cantiadad es distinto de null es que se quiere cambiar
				if (cantidad!=null) {
					
					//AÑADIR\\
					Cookie galleta= new Cookie(id_producto, cantidad);
					
					//este response es el del jsp si se usa el local no guarda despeus de 4 horas es lo unico que funciona
					// y sino que baje Jesucristo y lo vea.
					((HttpServletResponse)request.getSession().getAttribute("response")).addCookie(galleta); 
					
					carrito.put(Integer.parseInt(galleta.getName()), Integer.parseInt(galleta.getValue()));

				}else{ //si no se especfica cantidad es que se quiere borrar
							
					///ELIMINAR\\\
					Cookie galleta= new Cookie(id_producto, "");
					galleta.setMaxAge(0);
					((HttpServletResponse)request.getSession().getAttribute("response")).addCookie(galleta);
					carrito.remove(Integer.parseInt(id_producto));
				}
				
			}
			
			pintarCarrrito(request, response.getWriter(), carrito);
			
		}else{
			//imprime ACCESO DENEGADO
			response.getWriter().println(
					"<div class=\"col-sm-4 col-lg-4 col-md-4\" id=\"ficha\">\n" +
					"				<div class=\"thumbnail\">\n" +
					"					<center>\n" +
					"						<div class=\"caption\" id=\"login\">\n" +
					"							<h4>&iexcl;ACCESO DENEGADO!</h4>\n" +
					"							<br>\n" +
					"							<br>\n" +
					"							<a href=\"/ALotOfCupcakes/index.jsp?accion=login\"\"><button>INICIAR SESI&Oacute;N</button></a>\n" +
					"						</div>\n" +
					"					</center>\n" +
					"				</div>\n" +
					"			</div>");
		}
		
	}

	private void pintarCarrrito(HttpServletRequest req, PrintWriter pintor,  HashMap<Integer, Integer> carrito) {
		
		ArrayList<Producto> productos_carritoList= new ArrayList<>();
		
		if (carrito.size()>0) {
			double subtotal=0.0,total=0.0,iva=0.0;
			
			Connection conexion;
			
			try {		
			conexion = ((DataSource) getServletContext().getAttribute("fuenteDeDatos")).getConnection();	
			
			pintor.println("<div class=\"col-sm-4 col-lg-4 col-md-4\" id=\"ficha\">\n" +
					"<div class=\"thumbnail\">\n" +
					"<center>\n" +
					"<div class=\"caption\" id=\"login\">\n" +
					"<h2>TU CARRITO</h2>\n" +
					"<div class=\"span5\" id=\"form\">\n" +
					"<form method=\"get\" action=\"/ALotOfCupcakes/index.jsp\">"
					+ "<input type=\"hidden\" name=\"accion\" value=\"Pagar\">"
					+ "<table class=\"tg\" style=\"undefined;table-layout: fixed;\">\n" +
					"<colgroup>\n" +
					"<col style=\"width: 40px\">\n" +
					"<col style=\"width: 160.2px\">\n" +
					"<col style=\"width: 98.2px\">\n" +
					"<col style=\"width: 110.2px\">\n" +
					"</colgroup>\n" +
					"  <tr>\n" +
					"    <th class=\"tg-myfg\"></th>\n" +
					"    <th class=\"tg-myfg\">PRODUCTO</th>\n" +
					"    <th class=\"tg-myfg\">CANTIDAD</th>\n" +
					"    <th class=\"tg-myfg\">PRECIO</th>\n" +
					"  </tr>");
			
			DecimalFormat df= new DecimalFormat("00.00");
			
			//impresion de productos
			for (int id_producto: carrito.keySet()) {
				
				//busca el producto en cuestion en la bd
				Producto p= GestorBD.getInstance().obtenerProducto(conexion, (Integer)id_producto).get(0);
				double totalXcantidad=(p.getPrecio()-p.getDescuento())*carrito.get(id_producto);
				productos_carritoList.add(p);
				
				pintor.println(
						"	<tr>\n" +
						"    <td class=\"tg-k9ij\"><a href=\"/ALotOfCupcakes/index.jsp?accion=carrito&p="+p.getId()+"\" />"
						+ "<img src=\"imagenesWeb/ico_delete.png\" /></td>\n" +
						"    <td class=\"tg-krq5\">"+p.getNombre()+"</td>\n" +
						"    <td class=\"tg-krq5\">"+carrito.get(id_producto)+
						"    <td class=\"tg-krq5\">"+df.format(totalXcantidad)+"€</td>\n" +
						"  </tr>");
				
				total+=totalXcantidad;
			}
			
			iva=total-(total*0.79);
			subtotal=total-iva;
			
			pintor.println("  <tr>\n" +
					"    <td class=\"tg-k9ij\"></td>\n" +
					"    <td class=\"tg-k9ij\"></td>\n" +
					"    <td class=\"tg-k9ij\"></td>\n" +
					"    <td class=\"tg-k9ij\"></td>\n" +
					"  </tr>\n" +
					"  <tr>\n" +
					"    <td class=\"tg-k9ij\"></td>\n" +
					"    <td class=\"tg-txeu\"></td>\n" +
					"    <td class=\"tg-lpja\">Subtotal</td>\n" +
					"    <td class=\"tg-krq5\">"+df.format(subtotal)+"€</td>\n" +
					"  </tr>\n" +
					"  <tr>\n" +
					"    <td class=\"tg-k9ij\"></td>\n" +
					"    <td class=\"tg-txeu\"></td>\n" +
					"    <td class=\"tg-lpja\">IVA</td>\n" +
					"    <td class=\"tg-krq5\">"+df.format(iva)+"€</td>\n" +
					"  </tr>\n" +
					"  <tr>\n" +
					"    <td class=\"tg-k9ij\"></td>\n" +
					"    <td class=\"tg-txeu\"></td>\n" +
					"    <td class=\"tg-lpja\">TOTAL</td>\n" +
					"    <td class=\"tg-krq5\">"+df.format(total)+"€</td>\n" +
					"  </tr>\n" +
					"</table>" +
					"<br>"+
					"<table class=\"tg2\" style=\"undefined;table-layout: fixed;\">\n" +
					"\n" +
					"		 <tr>\n" +
					"		    <th class=\"tg2-5xks\" align=\"right\"><button>FINALIZAR COMPRA</button></th>\n" +
					"		  </tr>\n" +
					"		</table>"+
					" </form><br>"
					+ "</div></div></center></div></div>");	
			
			conexion.close();
			
			req.getSession().setAttribute("cantidades", carrito.values());
			req.getSession().setAttribute("productos", productos_carritoList.toArray());
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			//IMPRESION PARA CARRITO VACIO
			pintor.println(
					"<div class=\"col-sm-4 col-lg-4 col-md-4\" id=\"ficha\">\n" +
					"				<div class=\"thumbnail\">\n" +
					"					<center>\n" +
					"						<div class=\"caption\" id=\"login\">\n" +
					"							<h4>NO HAS COMPRADO NADA AÚN...</h4>\n" +
					"							<br>\n" +
					"							<img src=\"imagenesWeb/bolsa-vacia.jpg\""+
					"							<br>\n" +
					"							<a href=\"/ALotOfCupcakes/index.jsp?accion=index\"\"><button>IR A COMPRAR ALGO</button></a>\n" +
					"						</div>\n" +
					"					</center>\n" +
					"				</div>\n" +
					"			</div>");
		}
		
	}
	
}
