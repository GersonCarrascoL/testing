package pe.edu.unmsm.quipucamayoc.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import pe.edu.unmsm.quipucamayoc.model.UbigeoModel;


public interface UbigeoPersistence {
	
	@Select(value = "  SELECT " +
					" UB.CODIGO AS cod_ubigeo, " +
					" UB.NOMBRE AS nombre, " +
					" UB.NIVEL AS nivel, " +
					" UB.NIV1 AS cod_nivel1, " +
					" UB.NIV2 AS cod_nivel2 " +
					" FROM " +
					" WEBQPROTESORERIA.UBIGEO UB " +
					" ORDER BY  " +
					" nombre ASC")
    @Results(value = {@Result(javaType = UbigeoModel.class),
            @Result(property = "codUbigeo", column = "cod_ubigeo"),
            @Result(property = "nombre", column = "nombre"),
            @Result(property = "nivel", column = "nivel"),
            @Result(property = "codNivel1", column = "cod_nivel1"),
            @Result(property = "codNivel2", column = "cod_nivel2")
    })
    public List<UbigeoModel> listarDataUbigeo();

}
