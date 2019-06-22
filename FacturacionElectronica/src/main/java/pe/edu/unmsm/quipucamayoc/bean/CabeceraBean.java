package pe.edu.unmsm.quipucamayoc.bean;

import java.util.List;

import pe.edu.unmsm.quipucamayoc.model.NotificationModel;
import pe.edu.unmsm.quipucamayoc.model.TipoCambioModel;

public class CabeceraBean {
	private List<NotificationModel> notificacion;
	private List<TipoCambioModel> tipoCambio;
	
	public List<NotificationModel> getNotificacion() {
		return notificacion;
	}
	public void setNotificacion(List<NotificationModel> notificacion) {
		this.notificacion = notificacion;
	}
	public List<TipoCambioModel> getTipoCambio() {
		return tipoCambio;
	}
	public void setTipoCambio(List<TipoCambioModel> tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	
}
