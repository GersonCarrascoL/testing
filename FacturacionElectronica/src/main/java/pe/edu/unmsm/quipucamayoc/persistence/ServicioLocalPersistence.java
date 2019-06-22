package pe.edu.unmsm.quipucamayoc.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import pe.edu.unmsm.quipucamayoc.model.ServicioLocalModel;

public interface ServicioLocalPersistence {

	@Select(value = " SELECT " +
			" ID_MAQ_LOCAL AS id_maq_local, " +
			" UD_ID_ADMINISTRATIVA AS ud_id_local"+
			" DESC_LOCAL AS desc_local, " +   
			" ID_MAQ_REG AS id_maq_reg " +
			" FROM WEBQPROTESORERIA.MAQ_LOCAL " +
			" where UD_ID_ADMINISTRATIVA = #{ud_id_adm} " +
			" ORDER BY DESC_LOCAL ASC ")
	@Results(value = {@Result(javaType = ServicioLocalModel.class),
	@Result(property = "idMaquinaLocal", column = "id_maq_local"),
	@Result(property = "udIdAdministrativa", column = "ud_id_local"),
	@Result(property = "descripcionLocal", column = "desc_local"),
	@Result(property = "idMaquinaRegistradora", column = "id_maq_reg")
	})
	public List<ServicioLocalModel> getLocalesServicios(@Param("ud_id_adm") Integer udIdAdm);
	
	@Insert(value="{CALL REGISTRAR_LOCAL ("
			+"#{local.udIdAdministrativa, mode=IN, jdbcType=INTEGER},"
			+"#{local.descripcionLocal, mode=IN, jdbcType=VARCHAR},"
			+"#{local.idMaquinaRegistradora, mode=IN, jdbcType=INTEGER}"
			+"#{categoria.status, mode=OUT, jdbcType=INTEGER}"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void registrarLocal(@Param("local") ServicioLocalModel local);
	
	@Update(value="{CALL UPDATE_LOCAL ("
			+"#{local.idMaquinaLocal, mode=IN, jdbcType=INTEGER},"
			+"#{local.udIdAdministrativa, mode=IN, jdbcType=INTEGER},"
			+"#{local.descripcionLocal, mode=IN, jdbcType=VARCHAR},"
			+"#{local.idMaquinaRegistradora, mode=IN, jdbcType=INTEGER}"
			+"#{local.status, mode=OUT, jdbcType=INTEGER}"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void actualizarLocal(@Param("local") ServicioLocalModel local);
	
	@Delete(value="{CALL DELETE_LOCAL ("
			+"#{local.idMaquinaLocal, mode=IN, jdbcType=INTEGER},"			
			+"#{local.status, mode=OUT, jdbcType=INTEGER}"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void deleteLocal(@Param("local") ServicioLocalModel local);
}
