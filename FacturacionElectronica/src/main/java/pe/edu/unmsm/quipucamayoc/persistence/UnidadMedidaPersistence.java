package pe.edu.unmsm.quipucamayoc.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import pe.edu.unmsm.quipucamayoc.model.UnidadMedidaModel;


public interface UnidadMedidaPersistence {

	@Select(value = "SELECT \n " +
			"UNIMED.UNIMEDCOD AS CODIGO, \n " +
			"UNIMED.UNIMEDABR AS ABRV, \n " +
			"UNIMED.UNIMEDDES AS DESCR, \n " +
			"UNIMED.UNIMEDEST AS ESTADO \n " +
			"FROM \n " +
			"WEBQPROTESORERIA.UNI_MED_ART UNIMED \n " +
			"WHERE \n " +
			"UNIMED.UNIMEDEST = 'A' \n " +
			" order by decode( UNIMED.APARECE_AL_INICIO, 1 , '  ' , UNIMED.UNIMEDDES )")
	@Results(value = {@Result(javaType = UnidadMedidaModel.class),
	@Result(property = "unimedcod", column = "CODIGO"),
	@Result(property = "unimedabr", column = "ABRV"),
	@Result(property = "unimeddes", column = "DESCR"),
	@Result(property = "unimedest", column = "ESTADO")
	})
	public List<UnidadMedidaModel> listarUnidadesMedida();
}
