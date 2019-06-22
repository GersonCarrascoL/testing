package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.quipucamayoc.model.TipoCambioModel;
import pe.edu.unmsm.quipucamayoc.persistence.TipoCambioPersistence;
import pe.edu.unmsm.quipucamayoc.service.TipoCambioService;

@Service
public class TipoCambioServiceImpl implements TipoCambioService {
	@Autowired
	private TipoCambioPersistence tipoCambioPersistence;

	public TipoCambioPersistence getTipoCambioPersistence() {
		return tipoCambioPersistence;
	}

	public void setTipoCambioPersistence(TipoCambioPersistence tipoCambioPersistence) {
		this.tipoCambioPersistence = tipoCambioPersistence;
	}

	@Override
	public List<TipoCambioModel> getTipoCambioActual() {
		return tipoCambioPersistence.getTipoCambioActual();
	}

}
