package operativos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.mysql.fabric.Response;

import datos.GestorBD;
import modelado.Usuario;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//pinta login normal.
		pintarLogin(response, "");
	}

	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

		String usuario = request.getParameter("user");
		String pass = request.getParameter("pass");
		Usuario user = null;

			boolean ok=true;
			Connection conexion=null;
			
			try {
				conexion = ((DataSource) getServletContext().getAttribute("fuenteDeDatos")).getConnection();

			
			user= GestorBD.getInstance().obtenerUsuario(conexion, usuario);
				
			if (user!=null) {
				
				if (pass.equals(user.getPass())) {
					request.getSession().setAttribute("usuario", user.getId());
				} else {
					// la contraseña no coincide, pinta login con error de pass
					pintarLogin(resp, "¡Password incorrecto!");
					ok=false;
				}
				
			}else {
				// En caso de que no se encuentre ese usuario
				pintarLogin(resp, "¡Usuario no encontrado!");
				ok=false;
			}
			
			if (ok) {
				request.getSession().setAttribute("usuario", user);
				pintarBienvenida(resp, user);
			}
			conexion.close();
			} catch (Exception e) {
				// En caso de que no se encuentre ese usuario
				pintarLogin(resp, "¡Usuario no encontrado!");
				ok=false;
				try {
					conexion.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	}
	
	
	private void pintarLogin(HttpServletResponse response, String msg) throws IOException{
		
		//imprime formulario de login
		response.getWriter().println(""
				+ "		<div class=\"col-sm-4 col-lg-4 col-md-4\" id=\"ficha\">\n" +
				"				<div class=\"thumbnail\">\n" +
				"					<center>\n" +
				"						<div class=\"caption\" id=\"login\">\n" +
				"							<h2>"+msg+"</h2>"+
				"							<h4>INICIAR SESI&Oacute;N</h4>\n" +
				"							<br>\n" +
				"							<div class=\"span5\" id=\"form\">\n" +
				"							<form method=\"post\" action=\"/ALotOfCupcakes/index.jsp?accion=login\">\n" +
				"							<table>\n" +
				"							<tr><td align=\"right\">Usuario:&nbsp;</td><td><input type=\"text\" name=\"user\"></td></tr>\n" +
				"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
				"							<tr><td>Password:&nbsp;</td><td><input type=\"password\" name=\"pass\"></td></tr>\n" +
				"							<tr><td colspan=\"2\" align=\"center\"><a href=\"/ALotOfCupcakes/index.jsp?accion=registrar\">&iquest;No tienes cuenta?,&iexcl;Registrate!.</a></td></tr>\n" +
				"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
				"							<tr><td colspan=\"2\" align=\"center\"><input type=\"submit\" value=\"ENTRAR\"></td></tr>\n" +
				"							</table>\n" +
				"							<input type=\"hidden\" name=\"ultimaurl\" value=\"<%=request.getRequestURI()%>\">"+
				"							</form>\n" +
				"							<br>\n" +
				"							<br>\n" +
				"							</div>\n" +
				"						</div>\n" +
				"					</center>\n" +
				"				</div>\n" +
				"		</div>");
		
	}
	
	private void pintarBienvenida(HttpServletResponse resp, Usuario user) throws IOException{
		
		resp.getWriter().println(""
				+ "		<div class=\"col-sm-4 col-lg-4 col-md-4\" id=\"ficha\">\n" +
				"				<div class=\"thumbnail\">\n" +
				"					<center>\n" +
				"						<div class=\"caption\" id=\"login\">\n" +
				"							<h2>Bienvenido "+user.getUser()+"</h2>"+
				"							<h4>Tu &uacute;ltima conexi&oacute;n fue: "+user.getUltima_conec()+"</h4>\n" +
				"							<br>\n" +
				"							<div class=\"span5\" id=\"form\">\n" +
				"							<form method=\"get\" action=\"/ALotOfCupcakes/index.jsp?accion=index\">\n" +
				"							<input type=\"submit\" value=\"Hola, llevame a la A Lot of Cupcakes\">"+
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
