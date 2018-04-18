package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import modelado.Compra;
import modelado.LineaCompra;
import modelado.Producto;
import modelado.Usuario;

public class GestorBD {

	private static GestorBD instance = new GestorBD();

	private GestorBD() {
	}

	public static GestorBD getInstance() {
		return instance;
	}

	//
	//
	//
	//------------------------------OPERACIONES SOBRE USUARIOS------------------------------
	//
	//
	//
	
	// obtiene datos de usuario por ID
	public Usuario obtenerUsuario(Connection c, int id) {

		String query = "SELECT * FROM `TIENDAONLINE`.`USUARIOS` WHERE `ID`="+id;
		return listarUsuarios(c, query).get(0);
	}

	// obtiene datos de usuario por username
	public Usuario obtenerUsuario(Connection c, String nick) {
		
		String query = "SELECT * FROM `TIENDAONLINE`.`USUARIOS` WHERE `USER`='"+ nick + "'";
		
		ArrayList<Usuario> respu=listarUsuarios(c, query);
		
		Usuario user= (respu!=null)?respu.get(0):null;
		return user;
	}

	/**Registra un usuario en la base de datos con los parametros dados
	 * @param Conexion a la base de datos
	 * @param parametros en este orden: nombre,pass1,pass2,email,dir,ciudad,estado,cp,pais,tel
	 * @return Usuario registrado nuevo leido desde la base de datos
	 * @throws SQLException
	 */
	public synchronized Usuario registrarUsuario(Connection c, String... parametros) throws SQLException {
		Usuario respu = null;
		String insert = "INSERT INTO `TIENDAONLINE`.`USUARIOS`(USER,PASSWORD,EMAIL,DIRECCION,CIUDAD,ESTADO,CP,PAIS,telefono,ULTIMA_CONEC,NIVEL_PRIVILEGIO)"
				+ "VALUES ('" + parametros[0] + "','" + parametros[1] + "','" + parametros[2] + "','" + parametros[3]
				+ "','" + parametros[4] + "','" + parametros[5] + "','" + parametros[6] + "','" + parametros[7] + "','"
				+ parametros[8] + "','" + new SimpleDateFormat("YYYY-MM-dd").format(new Date()) + "'," + "'cliente')";

		c.createStatement().execute(insert);
		respu = obtenerUsuario(c, parametros[0]);
		return respu;
	}

	/**Modifica un usuario pasando por entero su id, y los nuevos parametros
	 * @param Conexion a la base de datos.
	 * @param id del usuario a modificar
	 * @param parametros en este orden: 
	 * @return Usuario modificado.
	 * @throws SQLException
	 */
	public Usuario modificarDatosUsuario(Connection c, int id_actual, String... parametros) throws SQLException {
		Usuario respuesta = null;

		if (parametros[1].equals(parametros[2])) {
			Connection conexion = c;
			Statement st = conexion.createStatement();
			int filas_afectadas = st.executeUpdate("UPDATE `TIENDAONLINE`.`USUARIOS` set" + " USER='" + parametros[0]
					+ "',PASSWORD='" + parametros[1] + "',EMAIL='" + parametros[3] + "',DIRECCION='" + parametros[4]
					+ "',CIUDAD='" + parametros[5] + "',ESTADO='" + parametros[6] + "',CP='" + parametros[7]
					+ "',PAIS='" + parametros[8] + "',telefono='" + parametros[9] + "',ULTIMA_CONEC='"
					+ new SimpleDateFormat("YYYY-MM-dd").format(new Date()) + "' where ID=" + id_actual);
		}
		return obtenerUsuario(c, id_actual);
	}
	
	public void eliminarUsuario(Connection c, int idUsuario){
		
		try {
			c.createStatement().execute("DELETE FROM TIENDAONLINE.USUARIOS WHERE ID="+idUsuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	//
	//
	//
	//------------------------------OPERACIONES SOBRE PRODUCTOS--------------------------------------------
	//
	//
	//

	/**Obtiene los productos por categoria, si categoria es null devuelve todos.
	 * @param Conexión a la base de datos.
	 * @param Categoria
	 * @return ArrayList de productos.
	 */
	public ArrayList<Producto> obtenerProductos(Connection c, String categoria) {
		
		String query="SELECT `ID_PRODUCTO`, `NOMBRE_PRODUCTO`, `DESCRIPCION_PRODUCTO`, `CATEGORIA_PRODUCTO`, `PRECIO_PRODUCTO`,"+
		" `DESCUENTO`, `STOCK`, `FOTO` FROM `TIENDAONLINE`.`PRODUCTOS` WHERE CATEGORIA_PRODUCTO='"+ categoria + "'";

		return listarProductos(c, query);
	}

	
	/**Obtiene la lista de productos con un descuento mayor al indicado.
	 * @param Conexion a la base de datos.
	 * @param double con el total de descuento
	 * @return Arraylist de productos que cumplen un descuento mayor al indicado.
	 */
	public ArrayList<Producto> obtenerProductos(Connection c, double descuento) {
		
		String query="SELECT `ID_PRODUCTO`, `NOMBRE_PRODUCTO`, `DESCRIPCION_PRODUCTO`, `CATEGORIA_PRODUCTO`, `PRECIO_PRODUCTO`,"+
		" `DESCUENTO`, `STOCK`, `FOTO` FROM `TIENDAONLINE`.`PRODUCTOS` WHERE `DESCUENTO`>"+descuento;

		return listarProductos(c, query);
	}
	
	/**Obtiene la lista de todos los productos
	 * @param Conexionn a la base de datos.
	 * @return Arraylist de productos.
	 */
	public ArrayList<Producto> obtenerProductos(Connection c) {
		
		String query="SELECT `ID_PRODUCTO`, `NOMBRE_PRODUCTO`, `DESCRIPCION_PRODUCTO`, `CATEGORIA_PRODUCTO`, `PRECIO_PRODUCTO`,"+
		" `DESCUENTO`, `STOCK`, `FOTO` FROM `TIENDAONLINE`.`PRODUCTOS`";

		return listarProductos(c, query);
	}
	
	public ArrayList<Producto> obtenerProducto(Connection c, int id) {
		
		String query="SELECT `ID_PRODUCTO`, `NOMBRE_PRODUCTO`, `DESCRIPCION_PRODUCTO`, `CATEGORIA_PRODUCTO`, `PRECIO_PRODUCTO`,"+
		" `DESCUENTO`, `STOCK`, `FOTO` FROM `TIENDAONLINE`.`PRODUCTOS` WHERE `ID_PRODUCTO`="+id;

		return listarProductos(c, query);
	}
	
	public int insertarProducto(Connection c, Producto p){
		
		int respu=0;
		
		try {
			
			PreparedStatement ps= c.prepareStatement("INSERT INTO `TIENDAONLINE`.`PRODUCTOS` (`NOMBRE_PRODUCTO`, `DESCRIPCION_PRODUCTO`, `CATEGORIA_PRODUCTO`, `PRECIO_PRODUCTO`, `DESCUENTO`, `STOCK`, `FOTO`)"
					+"VALUES (? , ? , ? , ? , ? , ? , ?)");
			
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getDescripcion());
			ps.setString(3, p.getCategoria());
			ps.setDouble(4, p.getPrecio());
			ps.setDouble(5, p.getDescuento());
			ps.setInt	(6, p.getStock());
			ps.setString(7, p.getFoto());
			
			
			ps.execute();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return respu;
	}
	
	public Producto modificarProducto(Connection c,Producto modificado,int idProducto){
		
		try {
			
			String query="UPDATE `TIENDAONLINE`.`PRODUCTOS` SET `NOMBRE_PRODUCTO`='"+modificado.getNombre()+"' "
					+ ", `DESCRIPCION_PRODUCTO`='"+modificado.getDescripcion()+"' "
							+ ", `CATEGORIA_PRODUCTO`='"+modificado.getCategoria()+"' "
									+ ", `PRECIO_PRODUCTO`="+modificado.getPrecio()+" "
											+ ", `DESCUENTO`="+modificado.getDescuento()+" "
													+ ", `STOCK`="+modificado.getStock()+" "
															+ ", `FOTO`='"+modificado.getFoto()+"'  "
																	+ "WHERE `ID_PRODUCTO`="+idProducto;
			
			c.createStatement().executeUpdate(query);

			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return obtenerProducto(c, idProducto).get(0);
	}
	
	public void eliminarProdcuto(Connection c, int idProdcuto){
		
		try {
			c.createStatement().execute("DELETE FROM TIENDAONLINE.PRODUCTOS WHERE ID_PRODUCTO="+idProdcuto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	//
	//
	//
	///--------------------------OPERACIONES SOBRE COMPRAS--------------------------------------------
	//
	//
	//
	public void insertarCompras(Connection c, Usuario propietario,ArrayList<LineaCompra> lineas ){
	
		try {		
		//primero se inserta la compra
		String insert="INSERT INTO `TIENDAONLINE`.`COMPRAS` (`FK_ID_CLIENTE`) VALUES (?)";
		
		PreparedStatement ps= c.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setInt(1, propietario.getId());
		ps.execute();
		
		//obtiene la clave generada en el insert para poder añadir lineas posteriormente
		ResultSet claveGenerada=ps.getGeneratedKeys();
		claveGenerada.next();
		int claveCompra=claveGenerada.getInt(1);
		
		String insert_linea;
			//se insertan las lineas:
		for (LineaCompra lineaCompra : lineas) {
			insert_linea="INSERT INTO TIENDAONLINE.LINEAS_COMPRA (CANTIDAD, FK_ID_COMPRA, FK_ID_PRODUCTO) VALUES ("+lineaCompra.getCantidad()+","+claveCompra+","+lineaCompra.getProducto()+");";
			c.createStatement().execute(insert_linea);
		}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public ArrayList<Compra> obtenerCompras(Connection c, Usuario u){
		
		String query="SELECT * FROM TIENDAONLINE.COMPRAS WHERE FK_ID_CLIENTE="+u.getId();
		
		return listarCompras(c, query);
	}
	
	public ArrayList<Compra> obtenerCompras(Connection c, int idCompra){
		
		String query="SELECT * FROM TIENDAONLINE.COMPRAS WHERE ID_COMPRA="+idCompra;
		
		return listarCompras(c, query);
	
	}
	
	

	//
	//
	//
	///--------------------------LOGICA PRIVADA DE LA CLASE--------------------------------------------
	//
	//
	//
	
	private ArrayList<Producto> listarProductos(Connection conexion, String query){
		
		ArrayList<Producto> respuesta = new ArrayList<>();
		try {

			ResultSet resultado = conexion.createStatement().executeQuery(query);

			while (resultado.next()) {
				respuesta.add(new Producto(resultado.getInt(1), resultado.getString(2), resultado.getString(3),
						resultado.getString(4), resultado.getDouble(5), resultado.getDouble(6), resultado.getInt(7),
						resultado.getString(8)));
			}

			return respuesta;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	private ArrayList<Usuario> listarUsuarios(Connection c, String query){
		
		ArrayList<Usuario> respu = new ArrayList<>();
		Statement st;
		
		try {
			st = c.createStatement();
			ResultSet resultado = st.executeQuery(query);
		 
			while(resultado.next()) {		
			respu.add(new Usuario(resultado.getInt(1), resultado.getString(2), resultado.getString(3),
					resultado.getString(4), resultado.getString(5), resultado.getString(6),
					resultado.getString(7),resultado.getString(8),resultado.getString(9),
					resultado.getString(10),resultado.getString(12),resultado.getDate(11)));
			}
			c.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return respu;
		
	}
	
	private ArrayList<Compra> listarCompras(Connection c, String query){
		
		ArrayList<Compra> respu = new ArrayList<>();
		Statement st;
		
		try {
			st = c.createStatement();
			ResultSet resultado = st.executeQuery(query);
		 
			while(resultado.next()) {		
			
				Compra compra=new Compra(resultado.getInt(1), resultado.getInt(2), resultado.getString(3));
				
				//añadido de lineas de la compra en  la compra
				String query_lineas="SELECT * FROM TIENDAONLINE.LINEAS_COMPRA WHERE FK_ID_COMPRA="+compra.getId();
				ResultSet lineas= c.createStatement().executeQuery(query_lineas);
				ArrayList<LineaCompra> lineas_compra= new ArrayList<>();
				
				for (int i = 0; lineas.next(); i++) {
					compra.getLineasCompra().put(i, new LineaCompra(lineas.getInt(3), lineas.getInt(1)));
				}
				
				respu.add(compra);
			}
			
			c.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return respu;	
	}
	
	
	
	
	
	
	
	
	
	
	
	//			SIN USO PERO FUNCIONAL EN CASO DE QEU SE QUIERA GUARDAR CARRITOS EN LA BASE DE DATOS
	//
	//
	///--------------------------OPERACIONES SOBRE ULTIMO_CARRITO--------------------------------------------
	//
	//
	//
//	public ArrayList<Producto> obtenerCarrito(Connection c,int id_cliente){
//		
//		ArrayList<Producto> carrito=null;
//		
//		String query="SELECT `OBJ` FROM `TIENDAONLINE`.`ULTIMO_CARRITO` WHERE `USUARIO_ID`="+id_cliente;
//		try {
//			
//			ResultSet resultado= c.createStatement().executeQuery(query);
//			
//			if (resultado.next()) {
//				Object carrito_tmp=new ObjectInputStream(resultado.getBlob(1).getBinaryStream()).readObject();
//				if (carrito_tmp!=null) {
//					carrito= (ArrayList<Producto>)carrito_tmp;
//				}
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}catch (NullPointerException e) {
//			System.out.println("Carrito vacio");
//			return null;
//		}
//		
//		return carrito;
//	}
//	
//	public void inicializarCarrito(Connection c,int id_cliente){
//		
//		ArrayList<Producto> carrito=null;
//		
//		String insert = "INSERT INTO `TIENDAONLINE`.`ULTIMO_CARRITO` (USUARIO_ID) VALUES ('"+id_cliente+"')";
//		
//		try {
//			c.createStatement().execute(insert);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public ArrayList<Producto> actualizarCarrito(Connection c,int id_cliente,ArrayList<Producto> carrito){
//		
//		try {
//		ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
//		ObjectOutputStream oos = new ObjectOutputStream(byteArray);
//		oos.writeObject(carrito);
//		
//		PreparedStatement ps= c.prepareStatement("UPDATE `TIENDAONLINE`.`ULTIMO_CARRITO` set `OBJ`=? WHERE `USUARIO_ID`=? ");
//
//		ps.setBytes(1, byteArray.toByteArray());
//		ps.setInt(2, id_cliente);
//		ps.execute();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return carrito;
//	}

}
