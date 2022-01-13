package com.idalytec.cfdidesk;

public class Usuario {
	private int id,nivel,status, idTerminal, idSucursal, usuarioLocal, tipoAcceso, tipo;
	private String nick,nombre,usrBD,passBD,nombreBD, empresaIP, local, ip, urlImagen;
	//private Empresa empresa;
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUsrBD() {
		return usrBD;
	}
	public void setUsrBD(String usrBD) {
		this.usrBD = usrBD;
	}
	public String getPassBD() {
		return passBD;
	}
	public void setPassBD(String passBD) {
		this.passBD = passBD;
	}
	public String getNombreBD() {
		return nombreBD;
	}
	public void setNombreBD(String nombreBD) {
		this.nombreBD = nombreBD;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	/*
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}*/
	public String getEmpresaIP() {
		return empresaIP;
	}
	public void setEmpresaIP(String empresaIP) {
		this.empresaIP = empresaIP;
	}
	/*
	public Empresa getEmpresa() {
		return empresa;
	}*/
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getIdTerminal() {
		return idTerminal;
	}
	public void setIdTerminal(int idTerminal) {
		this.idTerminal = idTerminal;
	}
	public int getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}
	public int getUsuarioLocal() {
		return usuarioLocal;
	}
	public void setUsuarioLocal(int usuarioLocal) {
		this.usuarioLocal = usuarioLocal;
	}
	public int getTipoAcceso() {
		return tipoAcceso;
	}
	public void setTipoAcceso(int tipoAcceso) {
		this.tipoAcceso = tipoAcceso;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public String getNombreTipoUsuario(){
		
		System.out.println("tipo " + tipo);
		
		String nombre = "";
		switch (tipo){
		case 1:
			nombre = "Administrador";
			break;
		case 2:
			nombre = "Administrador";
			break;
		case 3:
			nombre = "Vendedor";
			break;
		case 4:
			nombre = "Contador";
			break;
		}
		
		return nombre;
	}
	public String getUrlImagen() {
		return urlImagen;
	}
	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}
	
}
