package operativos;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import modelado.Usuario;

/**
 * Servlet implementation class Pagar
 */
public class Pagar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//comprueba que el usuario esta logueado y no esta intentado acceder con un carrito vacio
		if (request.getSession().getAttribute("usuario")!=null && ((Collection<Integer>)request.getSession().getAttribute("cantidades")).size()>0 ) {
		
			String modo= request.getParameter("modo");
			
			if (modo==null) {
				
			response.getWriter().println(
			"<div class=\"col-sm-4 col-lg-4 col-md-4\" id=\"ficha\">\n" +
			"				<div class=\"thumbnail\">\n" +
			"					<center>\n" +
			"						<div class=\"caption\" id=\"login\">\n" +
			"							<h2>¿M&eacute;todo de pago?</h2>\n" +
			"							<br>\n" +
			"							<div class=\"span5\" id=\"form\">\n" +
			"							<form method=\"get\" action=\"/ALotOfCupcakes/index.jsp?\">\n" +
			"							<input type=\"hidden\" name=\"accion\" value=\"Pagar\">\n" +
			"							<table>\n" +
			"							<tr><td>Tarjeta de cr&eacute;dito:&nbsp;&nbsp;&nbsp;</td><td align=\"center\"><input type=\"radio\" name=\"modo\" value=tarjeta checked=\"checked\"></td></tr>\n" +
			"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
			"							<tr><td>PayPal:&nbsp;&nbsp;&nbsp;</td><td align=\"center\"><input type=\"radio\" name=\"modo\" value=paypal></td></tr>\n" +
			"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
			"							<tr><td>Transferencia:&nbsp;&nbsp;&nbsp;</td><td align=\"center\"><input type=\"radio\" name=\"modo\" value=banco></td></tr>\n" +
			"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
			"							<tr><td colspan=\"2\" align=\"center\"><input type=\"submit\" value=\"CONTINUAR\"></td></tr>\n" +
			"							</table>\n" +
			"							</form>\n" +
			"							<br>\n" +
			"							<br>\n" +
			"							</div>\n" +
			"						</div>\n" +
			"					</center>\n" +
			"				</div>\n" +
			"		</div>");
			
			}else if(modo.equals("tarjeta")){
				
			response.getWriter().println(
						"<div class=\"col-sm-4 col-lg-4 col-md-4\" id=\"ficha\">\n" +
			"				<div class=\"thumbnail\">\n" +
			"					<center>\n" +
			"						<div class=\"caption\" id=\"login\">\n" +
			"							<h2>Datos de la tarjeta</h2>\n" +
			"							<h4>No guardamos tus datos nunca por seguridad</h4>\n" +
			"							<br>\n" +
			"							<div class=\"span5\" id=\"form\">\n" +
			"							<form method=\"post\" action=\"/ALotOfCupcakes/index.jsp\">\n" +
			"							<input type=\"hidden\" name=\"accion\" value=\"Pagar\">\n" +
			"							<input type=\"hidden\" name=\"metodo\" value=\"tarjeta\">\n" +
			"							<table>\n" +
			"							<tr><td>N&uacute;mero de tarjeta:&nbsp;&nbsp;&nbsp;</td><td align=\"center\"><input type=\"text\" name=\"num_tarjeta\" \"></td></tr>\n" +
			"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
			"							<tr><td align=\"right\">CVS:&nbsp;&nbsp;&nbsp;</td><td align=\"center\"><input type=\"text\" name=\"cvs\" maxlength=\"3\"></td></tr>\n" +
			"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
			"							<tr><td colspan=\"2\" align=\"center\"><input type=\"submit\" value=\"CONTINUAR\"></td></tr>\n" +
			"							</table>\n" +
			"							</form>\n" +
			"							<br>\n" +
			"							<br>\n" +
			"							</div>\n" +
			"						</div>\n" +
			"					</center>\n" +
			"				</div>\n" +
			"		</div>");
			}else if (modo.equals("paypal")) {
				
			response.getWriter().println(
						"<div class=\"col-sm-4 col-lg-4 col-md-4\" id=\"ficha\">\n" +
			"				<div class=\"thumbnail\">\n" +
			"					<center>\n" +
			"						<div class=\"caption\" id=\"login\">\n" +
			"							<h2>Datos de PayPal</h2>\n" +
			"							<h4>Es posible que se le rediriga a la web de Paypal</h4>\n" +
			"							<br>\n" +
			"							<div class=\"span5\" id=\"form\">\n" +
			"							<form method=\"post\" action=\"/ALotOfCupcakes/index.jsp\">\n" +
			"							<input type=\"hidden\" name=\"accion\" value=\"Pagar\">\n" +
			"							<input type=\"hidden\" name=\"metodo\" value=\"paypal\">\n" +
			"							<table>\n" +
			"							<tr><td>Email:&nbsp;&nbsp;&nbsp;</td><td align=\"center\"><input type=\"email\" name=\"correo_paypal\" \"></td></tr>\n" +
			"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
			"							<tr><td align=\"right\">Password:&nbsp;&nbsp;&nbsp;</td><td align=\"center\"><input type=\"password\" name=\"pass_paypal\" ></td></tr>\n" +
			"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
			"							<tr><td colspan=\"2\" align=\"center\"><input type=\"submit\" value=\"CONTINUAR\"></td></tr>\n" +
			"							</table>\n" +
			"							</form>\n" +
			"							<br>\n" +
			"							<br>\n" +
			"							</div>\n" +
			"						</div>\n" +
			"					</center>\n" +
			"				</div>\n" +
			"		</div>");
			}else if(modo.equals("banco")){
				
			response.getWriter().println(
						"<div class=\"col-sm-4 col-lg-4 col-md-4\" id=\"ficha\">\n" +
			"				<div class=\"thumbnail\">\n" +
			"					<center>\n" +
			"						<div class=\"caption\" id=\"login\">\n" +
			"							<h2>Transferencia:</h2>\n" +
			"							<h4>En un plazo de 48h debe hacer un ingreso</h4>\n" +
			"							<h4>indicando su id de cliente(id="+((Usuario)request.getSession().getAttribute("usuario")).getId()+")</h4>\n" +
			"							<h4>al siguiente n&uacute;mero de cuenta:</h4>\n" +
			"							<br>\n" +
			"							<div class=\"span5\" id=\"form\">\n" +
			"							<form method=\"post\" action=\"/ALotOfCupcakes/index.jsp\">\n" +
			"							<input type=\"hidden\" name=\"accion\" value=\"Pagar\">\n" +
			"							<input type=\"hidden\" name=\"metodo\" value=\"banco\">\n" +
			"							<table>\n" +
			"							<tr><td>Concepto:&nbsp;&nbsp;&nbsp;</td><td align=\"center\"><strong>"+((Usuario)request.getSession().getAttribute("usuario")).getId()+"</strong></td></tr>\n" +
			"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
			"							<tr><td>Beneficiario:&nbsp;&nbsp;&nbsp;</td><td align=\"center\"><strong>Santi Developers S.L</strong></td></tr>\n" +
			"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
			"							<tr><td>N&uacute;mero cuenta:&nbsp;&nbsp;&nbsp;</td><td align=\"center\"><strong>1472 56 7895 145698742</strong></td></tr>\n" +
			"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
			"							<tr><td>Banco:&nbsp;&nbsp;&nbsp;</td><td align=\"center\"><strong>HSBC</strong></td></tr>\n" +
			"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
			"							<tr><td colspan=\"2\" align=\"center\"><input type=\"submit\" value=\"CONTINUAR\"></td></tr>\n" +
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String metodo= request.getParameter("metodo");
		
		switch (metodo) {
		
		case "tarjeta":
			request.getSession().setAttribute("num_tarjeta", request.getParameter("num_tarjeta"));
			request.getSession().setAttribute("cvs", request.getParameter("cvs"));
			break;
		case "paypal":
			request.getSession().setAttribute("email_paypal", request.getParameter("correo_paypal"));
			request.getSession().setAttribute("cvs", request.getParameter("pass_paypal"));
			break;
		case "banco":
			break;
		}
		
		response.getWriter().println(
				"<div class=\"col-sm-4 col-lg-4 col-md-4\" id=\"ficha\">\n" +
	"				<div class=\"thumbnail\">\n" +
	"					<center>\n" +
	"						<div class=\"caption\" id=\"login\">\n" +
	"							<h2>Direcci&oacute;n de envio:</h2>\n" +
	"							<h4>¿Es correcta esta direcci&oacute;?</h4>\n" +
	"							<br>\n" +
	"							<div class=\"span5\" id=\"form\">\n" +
	"							<form method=\"post\" action=\"/ALotOfCupcakes/index.jsp\">\n" +
	"							<table>\n" +
	"							<tr><td>V&iacute;a:</td><td align=\"center\"><strong>"+((Usuario)request.getSession().getAttribute("usuario")).getCalle()+"</strong></td></tr>\n" +
	"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
	"							<tr><td>CP:</td><td align=\"center\"><strong>"+((Usuario)request.getSession().getAttribute("usuario")).getCp()+"</strong></td></tr>\n" +
	"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
	"							<tr><td>Ciudad:</td><td align=\"center\"><strong>"+((Usuario)request.getSession().getAttribute("usuario")).getCiudad()+"</strong></td></tr>\n" +
	"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
	"							<tr><td>Estado:</td><td align=\"center\"><strong>"+((Usuario)request.getSession().getAttribute("usuario")).getEstado()+"</strong></td></tr>\n" +
	"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
	"							<tr><td>Pais:</td><td align=\"center\"><strong>"+((Usuario)request.getSession().getAttribute("usuario")).getPais()+"</strong></td></tr>\n" +
	"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
	"							<tr><td>Contacto:</td><td align=\"center\"><strong>"+((Usuario)request.getSession().getAttribute("usuario")).getTelefono()+"</strong></td></tr>\n" +
	"							<tr><td colspan=\"2\">&nbsp;</td></tr>\n" +
	"							<tr><td colspan=\"2\" align=\"center\"><input type=\"hidden\" name=\"metodo\" value=\""+metodo+"\"><input type=\"hidden\" name=\"accion\" value=\"cobrar\"><input type=\"submit\" value=\"TODO CORRECTO, PAGAR\"></td></tr>\n" +
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
