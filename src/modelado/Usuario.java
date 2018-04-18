/**
 * MA-ITG ALotOfCupcakes Web ApplicationException
 * Author: Thomas Enrique Mejia Anaya
 */
package modelado;

import java.sql.Date;

public class Usuario {
	
	private int id;
	private String user,pass,email,calle,ciudad,estado,cp,pais,telefono,privilegio;
	private Date ultima_conec;
	
	public Usuario(int id, String user, String pass, String email, String calle, String ciudad, String estado,
			String cp, String pais, String telefono, String privilegio, Date ultima_conec) {
		super();
		this.id = id;
		this.user = user;
		this.pass = pass;
		this.email = email;
		this.calle = calle;
		this.ciudad = ciudad;
		this.estado = estado;
		this.cp = cp;
		this.pais = pais;
		this.telefono = telefono;
		this.privilegio = privilegio;
		this.ultima_conec = ultima_conec;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getPrivilegio() {
		return privilegio;
	}
	public void setPrivilegio(String privilegio) {
		this.privilegio = privilegio;
	}
	public Date getUltima_conec() {
		return ultima_conec;
	}
	public void setUltima_conec(Date ultima_conec) {
		this.ultima_conec = ultima_conec;
	}
}
