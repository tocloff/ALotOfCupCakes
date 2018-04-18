package operativos;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelado.Usuario;

/**
 * Servlet implementation class CerrarSesion
 */
public class CerrarSesion extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Usuario user=(Usuario)request.getSession().getAttribute("usuario");
		
		if (user!=null) {
		response.getWriter().println(""
				+ "		<div class=\"col-sm-4 col-lg-4 col-md-4\" id=\"ficha\">\n" +
				"				<div class=\"thumbnail\">\n" +
				"					<center>\n" +
				"						<div class=\"caption\" id=\"login\">\n" +
				"							<h2>&iexcl;Hasta Luego! "+user.getUser()+"</h2>"+
				"							<div class=\"span5\" id=\"form\">\n" +
				"							<form method=\"get\" action=\"/ALotOfCupcakes/index.jsp?accion=index\">\n" +
				"							<input type=\"submit\" value=\"Volver\">"+
				"							</form>\n" +
				"							<br>\n" +
				"							<br>\n" +
				"							</div>\n" +
				"						</div>\n" +
				"					</center>\n" +
				"				</div>\n");
		request.getSession().setAttribute("usuario", null);
		request.getSession().invalidate();
		}
	}

}
