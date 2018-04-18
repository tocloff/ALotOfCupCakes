package listeners;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

public class ListenerContexto implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		try {
			System.out.println("Inicializando contexto.....[ OK ]");

			// INICIALIZANDO POOL DE CONEXIONES
			Context contexto = new InitialContext();
			DataSource fuenteDeDatos = (DataSource) contexto.lookup("java:comp/env/BDtienda");
			arg0.getServletContext().setAttribute("fuenteDeDatos", fuenteDeDatos);
			// FIN DE LA INICIACION

			//inicialicacion de las imagenes del SLIDER de la pagina principal
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

		System.out.println("Deteniendo contexto.....[ OK ]");

	}

}
