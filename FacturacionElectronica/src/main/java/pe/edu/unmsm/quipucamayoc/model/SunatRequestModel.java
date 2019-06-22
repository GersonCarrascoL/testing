package pe.edu.unmsm.quipucamayoc.model;

public class SunatRequestModel {
	
	private String ruc;
	private String user;
	private String passw;
			
	public SunatRequestModel(){
		
	}
	public SunatRequestModel(String ruc, String user, String passw) {
		this.ruc = ruc;
		this.user = user;
		this.passw = passw;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
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
