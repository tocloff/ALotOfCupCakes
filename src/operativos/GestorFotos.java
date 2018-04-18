package operativos;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import modelado.Usuario;

/**
 * Servlet implementation class gestorFotos
 */
public class GestorFotos extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// comprobacion de sesion y privilegios
		if (request.getSession().getAttribute("usuario") != null && ((Usuario) request.getSession().getAttribute("usuario")).getPrivilegio().equals("admin")) {

			response.getWriter().println(
"			<div class=\"col-sm-4 col-lg-4 col-md-4\" id=\"ficha\" style=\"width: 100%;\">\n" +
"				<div class=\"thumbnail\" >\n" +
"						<div class=\"caption\" id=\"login\" style=\"margin-top: 0%\">\n" +
"						<center>\n" +
"							<h1 >PANEL DE ADMINISTRACI&Oacute;N</h1>\n" +
"							<hr>\n" +
"							<br>\n" +
"							<h4>Seleccione la foto de producto que desee subir y apunte el nombre para asignarlo posteriormente:</h4>\n" +
"							<div class=\"span5\" id=\"form\">\n" +
"							<form method=POST enctype=\"multipart/form-data\" action=\"/ALotOfCupcakes/index.jsp?accion=gestorFotos\" >\n" +
"							<br>\n" +
"							<br>\n" +
"							<input type=\"file\" name=\"foto\">\n" +
"							<br>\n" +
"							<br>\n" +
"							<input type=submit name=subir value=subir>\n" +
"							</form>\n" +
"							</center>\n" +
"							<br>\n" +
"							<br>\n" +
"							</div>\n" +
"						</div>\n" +
"				</div>");
		}else{
			//acceso denegado
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// subida de la foto
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		PrintWriter out = response.getWriter();

		try {
			// Extrar en una lista los datos del fichero (fileItem)
			ArrayList<FileItem> formItems = (ArrayList<FileItem>) upload.parseRequest(request);
			
			if (formItems != null && formItems.size() > 0) {
				
				for (FileItem item : formItems) {
				
					// Distinguimos campos de ficheros de campos normales
					if (!item.isFormField()) {
						String fileName = new File(item.getName()).getName();
						String filePath = getServletContext().getRealPath("")+"/imagenesProductos/"+fileName;
						//out.println(filePath);
						File storeFile = new File(filePath);
						// salvar el fichero en disco
						item.write(storeFile);

						out.println(
									"<div class=\"col-sm-4 col-lg-4 col-md-4\" id=\"ficha\" style=\"width: 100%;\">\n" +
									"				<div class=\"thumbnail\" style=<\"width: 100%;\">\n" +
									"					<center>\n" +
									"						<div class=\"caption\" id=\"login\">\n" +
									"							<h4>¡OPERACION REALIZADA!</h4>\n" +
									"							<br>\n" +
									"							<h6>Foto guardada con el nombre= "+fileName+"</h6>"+	
									"							<br>\n" +
									"							<a href=\"/ALotOfCupcakes/index.jsp?accion=admin\"\"><button>VOLVER</button></a>\n" +
									"						</div>\n" +
									"					</center>\n" +
									"				</div>\n" +
									"			</div>");		
					}
				}
			}
			
		} catch (Exception ex) {
			out.write("Error al subir archivo " + ex.getMessage());
		}

	}

}
