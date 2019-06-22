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

import pe.edu.unmsm.quipucamayoc.model.MaquinaRegistradoraModel;

public interface MaquinaRegistradoraPersistence {
	
	@Select(value = " SELECT " +
			" UD_ID AS ud_id, " +
			" CODIGO_ESTAB AS cod_estab, " +   
			" SERIE_FAB AS serie_fab, " +
			" MARCA AS marca, " + 
			" MODELO AS modelo, "+
			" NRO_AUTORIZACION AS nro_autorizacion,"+
			" ID_MAQ_REG AS id_maq_reg, "+
			" ID_MAQ_LOCAL AS id_maq_local "+
			" FROM WEBQPROTESORERIA.MAQ_REGISTRADORA " +
			" where UD_ID = #{ud_id_maq} " +
			" ORDER BY MODELO ASC ")
	@Results(value = {@Result(javaType = MaquinaRegistradoraModel.class),
	@Result(property = "codigoFacultad", column = "ud_id"),
	@Result(property = "codigoEstablecimiento", column = "cod_estab"),
	@Result(property = "serieFabricacion", column = "serie_fab"),
	@Result(property = "marca", column = "marca"),
	@Result(property = "modelo", column = "modelo"),
	@Result(property = "numeroAutorizacion", column = "nro_autorizacion"),
	@Result(property = "idMaquinaRegistradora", column = "id_maq_reg"),
	@Result(property = "idMaqLocal", column = "id_maq_local")
	
	})
	public List<MaquinaRegistradoraModel> getMaquinaRegistradora(@Param("ud_id_maq") Integer udIdMaq);
	
	@Insert(value="{CALL REGISTRAR_MAQ_REGISTRADORA ("
			+"#{maqReg.codigoFacultad, mode=IN, jdbcType=INTEGER},"
			+"#{maqReg.codigoEstablecimiento, mode=IN, jdbcType=VARCHAR},"
			+"#{maqReg.serieFabricacion, mode=IN, jdbcType=VARCHAR},"
			+"#{maqReg.marca, mode=IN, jdbcType=VARCHAR},"
			+"#{maqReg.modelo, mode=IN, jdbcType=VARCHAR},"
			+"#{maqReg.numeroAutorizacion, mode=IN, jdbcType=VARCHAR},"
			+"#{maqReg.idMaqLocal, mode=IN, jdbcType=INTEGER},"
			+"#{maqReg.status, mode=OUT, jdbcType=INTEGER}"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void registrarMaquinaRegistradora(@Param("maqReg") MaquinaRegistradoraModel maquinaRegistradora);
	
	
	@Update(value="{CALL UPDATE_MAQ_REGISTRADORA ("
			+"#{maqReg.codigoFacultad, mode=IN, jdbcType=INTEGER},"
			+"#{maqReg.codigoEstablecimiento, mode=IN, jdbcType=VARCHAR},"
			+"#{maqReg.serieFabricacion, mode=IN, jdbcType=VARCHAR},"
			+"#{maqReg.marca, mode=IN, jdbcType=VARCHAR},"
			+"#{maqReg.modelo, mode=IN, jdbcType=VARCHAR},"
			+"#{maqReg.numeroAutorizacion, mode=IN, jdbcType=VARCHAR},"
			+"#{maqReg.idMaquinaRegistradora, mode=IN, jdbcType=INTEGER},"
			+"#{maqReg.status, mode=OUT, jdbcType=INTEGER}"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void actualizarMaquinaRegistradora(@Param("maqReg") MaquinaRegistradoraModel maquinaRegistradora);	
	
	@Delete(value="{CALL DELETE_MAQ_REGISTRADORA ("
			+"#{maqReg.idMaquinaRegistradora, mode=IN, jdbcType=INTEGER},"			
			+"#{maqReg.status, mode=OUT, jdbcType=INTEGER}"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void deleteMaquinaRegistradora(@Param("maqReg") MaquinaRegistradoraModel maquinaRegistradora);
	
}
