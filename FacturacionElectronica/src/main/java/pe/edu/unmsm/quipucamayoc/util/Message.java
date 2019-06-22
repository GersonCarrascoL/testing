package pe.edu.unmsm.quipucamayoc.util;

import java.util.ArrayList;
import java.util.List;

public class Message {
	private Status status;
	private String statusMsg = "";
	private List<Integer> errorKys = new ArrayList<>();
	private String error = "";

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	public List<Integer> getErrorKys() {
		return errorKys;
	}

	public void setErrorKys(List<Integer> errorKys) {
		this.errorKys = errorKys;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
