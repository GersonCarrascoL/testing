package pe.edu.unmsm.quipucamayoc.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import pe.edu.unmsm.quipucamayoc.model.TipoResolucionModel;


public interface TipoResolucionPersistence {
	
	@Select(value = " SELECT " + 
					" COD_TIPO_RESOL AS codTipoResol,  " +
					" NAME_TIPO_RESOL AS nameTipoResol,  " +
					" NIVEL AS nivel " +
					" FROM WEBQPROTESORERIA.TIPO_RESOLUCION " +
					" ORDER BY nivel ASC")
	@Results(value = {@Result(javaType = TipoResolucionModel.class),
	    @Result(property = "codTipoResol", column = "codTipoResol"),
	    @Result(property = "nameTipoResol", column = "nameTipoResol"),
	    @Result(property = "nivel", column = "nivel")
	})
	public List<TipoResolucionModel> listarTiposResoluciones();

}
