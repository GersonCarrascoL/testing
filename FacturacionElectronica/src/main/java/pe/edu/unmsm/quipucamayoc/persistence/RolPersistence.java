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

import pe.edu.unmsm.quipucamayoc.model.RolModel;

public interface RolPersistence {
	
	String USU_NOM = "nombres";
	String USU_APE = "apellidos";
	String T_MAIL = "email";
	String PERF_DESC = "perfil";
	String PERF_COD = "codPerfil";
	String C_USUID = "dni";
	String EST = "estado"; 
	String HIST_ID = "id_hist";
	String UD_ID = "ud_id";
	
	@Select(value= "select "+
					" thup.HIST_ID as id_hist,"+
					" thup.UD_ID as ud_id,"+
					" usu.USU_NOM as nombre,"+
					" usu.USU_APE as apellido,"+
					" thup.T_MAIL as email,"+
					" per.DESC_GENERAL as perfil,"+
					" per.PERF_COD as codPerfil,"+
					" thup.C_USUID as dni,"+
					" thup.EST as estado "+
					" from QPRODATAQUIPU.TB_HIST_USU_PERF thup "+
					" INNER JOIN QPRODATAQUIPU.TB_PERFIL PER ON PER.MODU_COD=THUP.MODCOD"+
					" inner join QPRODATAQUIPU.TB_ERP_USUARIO usu on usu.C_USUID=thup.C_USUID"+
					" where thup.UD_ID=#{dependencia} "+
					" AND PER.PERF_COD=THUP.PERF_COD "+
					" AND THUP.MODCOD='3' "+
					" AND PER.TIPO_PERFIL='UNICO' ")
	@Results(value={
	@Result(javaType=RolModel.class),
	@Result(property=UD_ID,column="ud_id"),
	@Result(property=HIST_ID,column="id_hist"),
	@Result(property=USU_NOM,column="nombre"),
	@Result(property=USU_APE,column="apellido"),
	@Result(property=T_MAIL,column="email"),
	@Result(property=PERF_DESC,column="perfil"),
	@Result(property=PERF_COD,column="codPerfil"),
	@Result(property=C_USUID,column="dni"),
	@Result(property=EST,column="estado")})
	public List<RolModel> getRolXdependencia(@Param("dependencia") String dependencia);
	
	@Select(value = " SELECT " +
			" C_USUID as vdni, " +
			" T_MAIL as vemail, " +   
			" USU_NOM as vnombres, " +
			" USU_APE as vapellidos " + 
			" FROM QPRODATAQUIPU.TB_ERP_USUARIO " +
			" where C_USUID = #{pdni} " +
			" ORDER BY USU_NOM ASC ")
	@Results(value = {@Result(javaType = RolModel.class),
	@Result(property = "dni", column = "vdni"),
	@Result(property = "email", column = "vemail"),
	@Result(property = "nombres", column = "vnombres"),
	@Result(property = "apellidos", column = "vapellidos")
	})
	public List<RolModel> listarRolDni(@Param("pdni") String dni);
	
	
	@Insert(value = "{CALL REG_USUARIO_ROL (" 
			+ "#{usuarioRol.dni, mode=IN, jdbcType=VARCHAR},"
			+ "#{usuarioRol.nombres, mode=IN, jdbcType=VARCHAR}," 
			+ "#{usuarioRol.apellidos, mode=IN, jdbcType=VARCHAR},"
			+ "#{usuarioRol.email, mode=IN, jdbcType=VARCHAR}," 
			+ "#{usuarioRol.ud_id, mode=IN, jdbcType=INTEGER},"
			+ "#{usuarioRol.codPerfil, mode=IN, jdbcType=INTEGER},"
			+ "#{usuarioRol.status, mode=OUT, jdbcType=INTEGER})}")
			
	@Options(statementType = StatementType.CALLABLE)
	public void registrarUsuarioRol(@Param("usuarioRol") RolModel usuarioRol);
	
	@Update(value="{CALL UPD_USUARIO_ROL ("
			+"#{usuarioRol.id_hist, mode=IN, jdbcType=INTEGER},"
			+"#{usuarioRol.dni, mode=IN, jdbcType=VARCHAR},"
			+"#{usuarioRol.nombres, mode=IN, jdbcType=VARCHAR},"
			+"#{usuarioRol.apellidos, mode=IN, jdbcType=VARCHAR},"
			+"#{usuarioRol.email, mode=IN, jdbcType=VARCHAR},"
			+"#{usuarioRol.ud_id, mode=IN, jdbcType=INTEGER},"
			+"#{usuarioRol.estado, mode=IN, jdbcType=INTEGER},"
			+"#{usuarioRol.codPerfil, mode=IN, jdbcType=INTEGER},"
			+"#{usuarioRol.status, mode=OUT, jdbcType=INTEGER}"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void actualizarUsuarioRol(@Param("usuarioRol") RolModel usuarioRol);
	
	@Delete(value="{CALL DELETE_USUARIO_ROL("
			+"#{usuarioRol.id_hist, mode=IN, jdbcType=INTEGER},"			
			+"#{usuarioRol.status, mode=OUT, jdbcType=INTEGER}"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void deleteUsuarioRol(@Param("usuarioRol") RolModel usuarioRol);
	
	@Update(value = "UPDATE QPRODATAQUIPU.TB_HIST_USU_PERF \n " +
			" SET EST = #{estado} \n " +
			" WHERE  HIST_ID = #{id_hist}")
	public void actualizarEstado(@Param("id_hist") Integer id_hist, @Param("estado") Integer estado);
}
