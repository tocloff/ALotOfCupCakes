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

import datos.GestorBD;
import modelado.Usuario;

/**
 * Servlet implementation class Config
 */
public class Configuracion extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		formularioDatos(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Usuario user= (Usuario) request.getSession().getAttribute("usuario");
		
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

				Usuario modificado= GestorBD.getInstance().modificarDatosUsuario(conexion, user.getId(),
						nombre,pass1,pass2,email,dir,ciudad,estado,cp,pais,tel);

				if (modificado!=null && modificado.getId()==user.getId()) {
					
					request.getSession().setAttribute("usuario", modificado);
					
					response.getWriter().println(
							"		<div class=\"col-sm-4 col-lg-4 col-md-4\" id=\"ficha\">\n" +
							"				<div class=\"thumbnail\">\n" +
							"					<center>\n" +
							"						<div class=\"caption\" id=\"login\">\n" +
							"							<h2>¡Cambios guardados!</h2>"+
							"							<br>\n" +
							"							<div class=\"span5\" id=\"form\">\n" +
							"							<form method=\"get\" action=\"/ALotOfCupcakes/index.jsp\">\n" +
							"							<input type=\"submit\" value=\"Volver\">"+
							"							</form>\n" +
							"							<br>\n" +
							"							<br>\n" +
							"							</div>\n" +
							"						</div>\n" +
							"					</center>\n" +
							"				</div>\n" +
							"		</div>");
					
				}else{
					response.getWriter().println("ERROR!");
				}
				
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
		
		Usuario user= (Usuario) req.getSession().getAttribute("usuario");
		
		if (user!=null) {
			resp.getWriter().println(""
					+ "		<div class=\"col-sm-4 col-lg-4 col-md-4\" id=\"ficha\">\n" +
					"				<div class=\"thumbnail\">\n" +
					"					<center>\n" +
					"						<div class=\"caption\" id=\"login\">\n" +
					"							<h2>--Datos de la cuenta--</h2>"+
					"							<h4>Cambia los que desees modificar:</h4>\n" +
					"							<br>\n" +
					"							<div class=\"span5\" id=\"form\">\n" +
					"							<form method=\"post\" action=\"/ALotOfCupcakes/index.jsp?accion=config\">\n" +
					"							<table id=\"datos\">\n" +
					"							<tr><td>Nick:&nbsp;</td><td><input type=\"text\" name=\"user\" value=\""+user.getUser()+"\"></td></tr>\n" +
					"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
					"							<tr><td>Contraseña:&nbsp;</td><td><input type=\"password\" name=\"pass\" value=\""+user.getPass()+"\"></td></tr>\n" +
					"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
					"							<tr><td>Repite la contraseña:&nbsp;</td><td><input type=\"password\" name=\"pass2\" value=\""+user.getPass()+"\"></td></tr>\n" +
					"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
					"							<tr><td>E-mail:&nbsp;</td><td><input type=\"email\" name=\"email\" value=\""+user.getEmail()+"\" required=\"true\"></td></tr>\n" +
					"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
					"							<tr><td>Direcci&oacute;n: </td><td><input type=\"text\" name=\"dir\" value=\""+user.getCalle()+"\"></td></tr>\n" +
					"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
					"							<tr><td>Ciudad: </td><td><input type=\"text\" name=\"ciudad\" value=\""+user.getCiudad()+"\"></td></tr>\n" +
					"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
					"							<tr><td>Estado: </td><td><input type=\"text\" name=\"estado\" value=\""+user.getEstado()+"\"></td></tr>\n" +
					"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
					"							<tr><td>Pa&iacute;s: </td><td><input type=\"text\" name=\"pais\" value=\""+user.getPais()+"\"></td></tr>\n" +
					"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
					"							<tr><td>C&oacute;digo postal:&nbsp;</td><td><input type=\"text\" name=\"cp\" value=\""+user.getCp()+"\"></td></tr>\n" +
					"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
					"							<tr><td>Tel&eacute;fono:&nbsp;</td><td><input type=\"text\" name=\"tel\" value=\""+user.getTelefono()+"\"></td></tr>\n" +
					"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
					"							<tr><td colspan=\"2\" align=\"center\"><input type=\"submit\" value=\"GUARDAR\"></td></tr>\n" +
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
}
