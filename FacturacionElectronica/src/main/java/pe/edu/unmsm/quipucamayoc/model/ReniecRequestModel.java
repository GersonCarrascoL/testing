package pe.edu.unmsm.quipucamayoc.model;

public class ReniecRequestModel {
	
	private String dni;
	private String user;
	private String passw;
	
	public ReniecRequestModel(){		
	}
		
	public ReniecRequestModel(String dni, String user, String passw) {
		this.dni = dni;
		this.user = user;
		this.passw = passw;
	}

	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassw() {
		return passw;
	}
	public void setPassw(String passw) {
		this.passw = passw;
	}
	
}
