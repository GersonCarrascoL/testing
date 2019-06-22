package pe.edu.unmsm.quipucamayoc.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import pe.edu.unmsm.quipucamayoc.model.TipoOperacionIGVModel;

public interface SunatGeneralPersistence {

	@Select(value = " SELECT COD_TIPO AS codTipo, DESC_TIPO AS descTipo " +
					" FROM WEBQPROTESORERIA.TIPO_OPERACION_IGV " +
					" ORDER BY codTipo asc ")
	@Results(value = {@Result(javaType = TipoOperacionIGVModel.class),
	@Result(property = "codTipo", column = "codTipo"),
	@Result(property = "descTipo", column = "descTipo")
	})
	public List<TipoOperacionIGVModel> listarTiposOperacionIgv();
}
