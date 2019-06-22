package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.quipucamayoc.model.MaqLocalModel;
import pe.edu.unmsm.quipucamayoc.persistence.MaqLocalPersistence;
import pe.edu.unmsm.quipucamayoc.service.MaqLocalService;

@Service
public class MaqLocalServiceImpl implements MaqLocalService{
	
	@Autowired private MaqLocalPersistence maqLocalPersistence;

	@Override
	public List<MaqLocalModel> getLocalesTodos(){
		return maqLocalPersistence.getLocalesTodos();
	}
	
	@Override
	public List<MaqLocalModel> getLocalesXUdId(Integer udId){
		return maqLocalPersistence.getLocalesXUdId(udId);
	}
}
