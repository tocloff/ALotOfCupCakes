package operativos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import modelado.Usuario;

/**
 * Servlet implementation class Registrar
 */
public class Registrar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getSession().getAttribute("usuario")==null) {
			formularioDatos(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//recuperación de datos
				String nombre= request.getParameter("user");
				String pass1= request.getParameter("pass");
				String pass2= request.getParameter("pass2");
				String email= request.getParameter("email");
				String dir= request.getParameter("dir");
				String ciudad= request.getParameter("ciudad");
				String estado= request.getParameter("estado");
				String pais= request.getParameter("pais");
				String cp= request.getParameter("cp");
				String tel= request.getParameter("tel");
		//fin de recuperación
				
				if (pass1.equals(pass2)) {
					
					try {
						Connection conexion = ((DataSource) getServletContext().getAttribute("fuenteDeDatos")).getConnection();
						Statement st = conexion.createStatement();
					    st.execute("INSERT INTO `TIENDAONLINE`.`USUARIOS`(USER,PASSWORD,EMAIL,DIRECCION,CIUDAD,ESTADO,CP,PAIS,telefono,ULTIMA_CONEC,NIVEL_PRIVILEGIO)"+
						"VALUES ('"+nombre+"','"+pass1+"','"+email+"','"+dir+"','"+ciudad+"','"+estado+"','"+cp+"','"+pais+"','"+tel+"','"+new SimpleDateFormat("YYYY-MM-dd").format(new Date())+"',"+"'cliente')");
							
							Statement st2 = conexion.createStatement();
							String query = "SELECT * FROM `TIENDAONLINE`.`USUARIOS` WHERE `USER`='"+nombre+"' and `PASSWORD`='"+pass1+"'";
							ResultSet resultado = st.executeQuery(query);
							resultado.next();
							
							Usuario user = new Usuario(resultado.getInt(1), resultado.getString(2), resultado.getString(3),
										resultado.getString(4), resultado.getString(5), resultado.getString(6),
										resultado.getString(7),resultado.getString(8),resultado.getString(9),
										resultado.getString(10),resultado.getString(12),resultado.getDate(11));
							request.getSession().setAttribute("usuario", user);
							
							response.getWriter().println(
									"		<div class=\"col-sm-4 col-lg-4 col-md-4\" id=\"ficha\">\n" +
									"				<div class=\"thumbnail\">\n" +
									"					<center>\n" +
									"						<div class=\"caption\" id=\"login\">\n" +
									"							<h2>¡Bienvenido a ALotOfCupcakes</h2>"+
									"							<br>\n" +
									"							<div class=\"span5\" id=\"form\">\n" +
									"							<form method=\"get\" action=\"/ALotOfCupcakes/index.jsp\">\n" +
									"							<input type=\"submit\" value=\"Ok, Proceder a la compra\">"+
									"							</form>\n" +
									"							<br>\n" +
									"							<br>\n" +
									"							</div>\n" +
									"						</div>\n" +
									"					</center>\n" +
									"				</div>\n" +
									"		</div>");
						
						
						conexion.close();

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}else {
					//las contraseñas no coinciden
					response.getWriter().println(
							"		<div class=\"col-sm-4 col-lg-4 col-md-4\" id=\"ficha\">\n" +
							"				<div class=\"thumbnail\">\n" +
							"					<center>\n" +
							"						<div class=\"caption\" id=\"login\">\n" +
							"							<h2>¡Las contraseñas no coinciden!</h2>"+
							"							<br>\n" +
							"							<div class=\"span5\" id=\"form\">\n" +
							"							<form method=\"get\" action=\"/ALotOfCupcakes/index.jsp?accion=config\">\n" +
							"							<input type=\"submit\" value=\"Volver\">"+
							"							</form>\n" +
							"							<br>\n" +
							"							<br>\n" +
							"							</div>\n" +
							"						</div>\n" +
							"					</center>\n" +
							"				</div>\n" +
							"		</div>");
				}
	}
	
	private void formularioDatos(HttpServletRequest req, HttpServletResponse resp)throws IOException{
		
		resp.getWriter().println(""
				+ "		<div class=\"col-sm-4 col-lg-4 col-md-4\" id=\"ficha\">\n" +
				"				<div class=\"thumbnail\">\n" +
				"					<center>\n" +
				"						<div class=\"caption\" id=\"login\">\n" +
				"							<h2>--DATOS DE REGISRO--</h2>"+
				"							<br>\n" +
				"							<div class=\"span5\" id=\"form\">\n" +
				"							<form method=\"post\" action=\"/ALotOfCupcakes/index.jsp?accion=registrar\">\n" +
				"							<table id=\"datos\">\n" +
				"							<tr><td>Nick:&nbsp;</td><td><input type=\"text\" name=\"user\" required=\"true\"></td></tr>\n" +
				"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
				"							<tr><td>Contraseña:&nbsp;</td><td><input type=\"password\" name=\"pass\" required=\"true\"></td></tr>\n" +
				"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
				"							<tr><td>Repite la contraseña:&nbsp;</td><td><input type=\"password\" name=\"pass2\" required=\"true\"></td></tr>\n" +
				"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
				"							<tr><td>E-mail:&nbsp;</td><td><input type=\"email\" name=\"email\" required=\"true\"></td></tr>\n" +
				"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
				"							<tr><td>Direcci&oacute;n: </td><td><input type=\"text\" name=\"dir\" required=\"true\"></td></tr>\n" +
				"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
				"							<tr><td>Ciudad: </td><td><input type=\"text\" name=\"ciudad\" required=\"true\"></td></tr>\n" +
				"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
				"							<tr><td>Estado: </td><td><input type=\"text\" name=\"estado\" required=\"true\"></td></tr>\n" +
				"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
				"							<tr><td>Pa&iacute;s: </td><td><input type=\"text\" name=\"pais\" required=\"true\"></td></tr>\n" +
				"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
				"							<tr><td>C&oacute;digo postal:&nbsp;</td><td><input type=\"text\" name=\"cp\" required=\"true\"></td></tr>\n" +
				"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
				"							<tr><td>Tel&eacute;fono:&nbsp;</td><td><input type=\"text\" name=\"tel\" required=\"true\"></td></tr>\n" +
				"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
				"							<tr><td colspan=\"2\" align=\"center\"><input type=\"submit\" value=\"REGISTRARSE\"></td></tr>\n" +
				"							</table>\n" +
				"							</form>\n" +
				"							<br>\n" +
				"							<br>\n" +
				"							</div>\n" +
				"						</div>\n" +
				"					</center>\n" +
				"				</div>\n" +
				"		</div>");
	}

}
