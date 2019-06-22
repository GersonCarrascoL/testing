package pe.edu.unmsm.quipucamayoc.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import pe.edu.unmsm.quipucamayoc.model.MaqLocalModel;

public interface MaqLocalPersistence {
	
	@Select(value = " SELECT " 
					+ " ID_MAQ_LOCAL as idMaqLocal, "
					+ " UD_ID_ADMINISTRATIVA as udId, "
					+ " DESC_LOCAL as udIdAdministrativa, "
					+ " UD_ID as udId, "
					+ " NOMB_CORTO as nombCorto, "
					+ " DIRECCION as direccion, "
					+ " CODIGO_ESTAB as codigoEstab "
					+ " FROM WEBQPROTESORERIA.MAQ_LOCAL")
	@Results(value = {@Result(javaType = MaqLocalModel.class),
	@Result(property = "idMaqLocal", column = "idMaqLocal"),
	@Result(property = "udId", column = "udId"),
	@Result(property = "udIdAdministrativa", column = "udIdAdministrativa"),
	@Result(property = "descLocal", column = "descLocal"),
	@Result(property = "nombCorto", column = "nombCorto"),
	@Result(property = "direccion", column = "direccion"),
	@Result(property = "codigoEstab", column = "codigoEstab")
	})
	public List<MaqLocalModel> getLocalesTodos();
	
	@Select(value = " SELECT " 
			+ " ID_MAQ_LOCAL as idMaqLocal, "
			+ " UD_ID_ADMINISTRATIVA as udId, "
			+ " DESC_LOCAL as udIdAdministrativa, "
			+ " UD_ID as udId, "
			+ " NOMB_CORTO as nombCorto, "
			+ " DIRECCION as direccion, "
			+ " CODIGO_ESTAB as codigoEstab "
			+ " FROM WEBQPROTESORERIA.MAQ_LOCAL"
			+ " WHERE UD_ID = #{udId}")
	@Results(value = {@Result(javaType = MaqLocalModel.class),
	@Result(property = "idMaqLocal", column = "idMaqLocal"),
	@Result(property = "udId", column = "udId"),
	@Result(property = "udIdAdministrativa", column = "udIdAdministrativa"),
	@Result(property = "descLocal", column = "descLocal"),
	@Result(property = "nombCorto", column = "nombCorto"),
	@Result(property = "direccion", column = "direccion"),
	@Result(property = "codigoEstab", column = "codigoEstab")
	})
	public List<MaqLocalModel> getLocalesXUdId(@Param("udId") Integer udId);

}
