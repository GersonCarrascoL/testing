package pe.edu.unmsm.quipucamayoc.persistence;

import pe.edu.unmsm.quipucamayoc.model.ClienteSinRucModel;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

public interface ClienteSinRucPersistence {
	
	String PROP_NOMBRES = "nombres";
	String PROP_APPAT = "apePat";
	String PROP_APMAT = "apeMat";
	String PROP_DIRECCION = "direccion";
	String PROP_ABREV = "abrev";
	String PROP_IDCLIENTE = "idCliente";
	String PROP_TIPODOC = "tipoDoc";
	String PROP_NUMDOC = "numDoc";
	String PROP_FECHANAC = "fechaNac";
	String PROP_SEXO = "sexo";
	String PROP_EMAIL = "email";
	String PROP_NOMCOMPLETO = "nomCompleto";
	String PROP_ESTADO = "estado";
	String PROP_NOMBREDOC= "nombreDoc";
	String PROP_FECHAREG= "fechaReg";
	String PROP_EMAIL2 = "email2";
	String PROP_TELF = "telefono";
	String PROP_CODUBIGEO = "codUbigeo";
	String PROP_NOMBREUBIGEO = "nombreUbigeo";
	String PROP_CODNIVEL1 = "codNivel1";
	String PROP_NOMBDEPARTAMENTO = "nombDepartamento";
	String PROP_CODNIVEL2 = "codNivel2";
	String PROP_NOMBPROVINCIA = "nombProvincia";
	
	String COL_NOMBRES = "nombres";
	String COL_APPAT = "apePat";
	String COL_APMAT = "apeMat";
	String COL_DIRECCION = "direccion";
	String COL_ABREV = "abrev";
	String COL_IDCLIENTE = "idCliente";
	String COL_TIPODOC = "tipoDoc";
	String COL_NUMDOC = "numDoc";
	String COL_FECHANAC = "fechaNac";
	String COL_SEXO = "sexo";
	String COL_EMAIL = "email";
	String COL_NOMCOMPLETO = "nomCompleto";
	String COL_ESTADO = "estado";
	String COL_NOMBREDOC= "nombreDoc";
	String COL_FECHAREG= "FECHA_REGISTRO";
	String COL_EMAIL2 = "email2";
	String COL_TELF = "telefono";
	String COL_CODUBIGEO = "COD_UBIGEO";
	String COL_NOMBREUBIGEO = "NOMBRE_UBIGEO";
	String COL_CODNIVEL1 = "COD_NIVEL1";
	String COL_NOMBDEPARTAMENTO = "NOMB_DEPARTAMENTO";
	String COL_CODNIVEL2 = "COD_NIVEL2";
	String COL_NOMBPROVINCIA = "NOMB_PROVINCIA";
	
	String GET_CLIENTES_SINRUC_QUERY = "SELECT " +
										" CLIE.ID_CLIENTE AS idCliente, " +
										" CLIE.TIPO_DOC AS tipoDoc, " +
										" CLIE.NUM_DOC AS numDoc, " +
										" CLIE.APE_PAT AS apePat, " +
										" CLIE.APE_MAT AS apeMat, " +
										" CLIE.NOMBRES AS nombres, " +
										" TO_CHAR(CLIE.FECHA_NAC,'DD/MM/YYYY') AS fechaNac, " +
										" CLIE.SEXO AS sexo, " +
										" CLIE.EMAIL AS email, " +
										" CLIE.NOM_COMP AS nomCompleto, " +
										" CLIE.DIRECCION AS direccion, " +
										" CLIE.ESTADO AS estado, " +
										" DOC.ABREV AS abrev, " +
										" DOC.NOMBRE AS nombreDoc, " +
										" TO_CHAR(CLIE.FECHA_REG,'DD/MM/YYYY') AS FECHA_REGISTRO," +
										" CLIE.EMAIL2 AS email2, " +
										" CLIE.TELEFONO AS telefono, " +
										" CLIE.COD_UBIGEO AS COD_UBIGEO, " +
										" UB.NOMBRE AS NOMBRE_UBIGEO, " +
										" UB.NIV1 AS COD_NIVEL1, " +
										" UB2.NOMBRE AS NOMB_DEPARTAMENTO, " +
										" UB.NIV2 AS COD_NIVEL2, " +
										" UB3.NOMBRE AS NOMB_PROVINCIA " +
										" FROM WEBQPROTESORERIA.CLIENTE_SIN_RUC CLIE " +
										" INNER JOIN WEBQPROTESORERIA.DOCUMENTO_IDENTIDAD DOC ON CLIE.TIPO_DOC = DOC.ID_DOC_IDENT " +
										" LEFT JOIN WEBQPROTESORERIA.UBIGEO UB ON CLIE.COD_UBIGEO = UB.CODIGO " +
										" LEFT JOIN WEBQPROTESORERIA.UBIGEO UB2 ON UB2.CODIGO = UB.NIV1 " +
										" LEFT JOIN WEBQPROTESORERIA.UBIGEO UB3 ON UB3.CODIGO = UB.NIV2 ";
	
	@Select(value=	"SELECT\n" +
					"WEBQPROTESORERIA.ALUMNOSUM.NUMERO_DOCUMENTO,\n" +
					"WEBQPROTESORERIA.ALUMNOSUM.NOMBRES,\n" +
					"WEBQPROTESORERIA.ALUMNOSUM.APELLIDO_PATERNO,\n" +
					"WEBQPROTESORERIA.ALUMNOSUM.APELLIDO_MATERNO,\n" +
					"WEBQPROTESORERIA.ALUMNOSUM.DIRECCION,\n" +
					"WEBQPROTESORERIA.DOCUMENTO_IDENTIDAD.ABREV\n" +
					"FROM\n" +
					"WEBQPROTESORERIA.ALUMNOSUM\n" +
					"INNER JOIN WEBQPROTESORERIA.DOCUMENTO_IDENTIDAD ON WEBQPROTESORERIA.ALUMNOSUM.TIPO_DOCUMENTO = WEBQPROTESORERIA.DOCUMENTO_IDENTIDAD.ID_DOC_IDENT\n" +
					"UNION\n" +
					"SELECT\n" +
					"WEBQPROTESORERIA.CLIENTE_SIN_RUC.NUM_DOC,\n" +
					"WEBQPROTESORERIA.CLIENTE_SIN_RUC.NOMBRES,\n" +
					"WEBQPROTESORERIA.CLIENTE_SIN_RUC.APE_PAT,\n" +
					"WEBQPROTESORERIA.CLIENTE_SIN_RUC.APE_MAT,\n" +
					"WEBQPROTESORERIA.CLIENTE_SIN_RUC.DIRECCION,\n" +
					"WEBQPROTESORERIA.DOCUMENTO_IDENTIDAD.ABREV\n" +
					"FROM\n" +
					"WEBQPROTESORERIA.CLIENTE_SIN_RUC\n" +
					"INNER JOIN WEBQPROTESORERIA.DOCUMENTO_IDENTIDAD ON WEBQPROTESORERIA.CLIENTE_SIN_RUC.COD_DOC = WEBQPROTESORERIA.DOCUMENTO_IDENTIDAD.ID_DOC_IDENT")
	@Results(value={
			@Result(javaType=ClienteSinRucModel.class),
			@Result(property=PROP_NUMDOC,column="NUM_DOC"),
			@Result(property=PROP_NOMBRES,column="NOMBRES"),
			@Result(property=PROP_APPAT,column="APE_PAT"),
			@Result(property=PROP_APMAT,column="APE_MAT"),
			@Result(property=PROP_DIRECCION,column="DIRECCION"),
			@Result(property=PROP_ABREV,column="ABREV")})
	public List<ClienteSinRucModel> listar();
	
	
	@Select(value=	GET_CLIENTES_SINRUC_QUERY
					+ " WHERE DOC.EST = 1 AND CLIE.TIPO_DOC = #{tipoDocumen} AND trim(CLIE.NUM_DOC) like trim(#{numDocumen}||'%') AND rownum<100"
					)
	@Results(value={
		@Result(javaType=ClienteSinRucModel.class),
		@Result(property=PROP_IDCLIENTE,column=COL_IDCLIENTE),
		@Result(property=PROP_TIPODOC,column=COL_TIPODOC),
		@Result(property=PROP_NUMDOC,column=COL_NUMDOC),
		@Result(property=PROP_APPAT,column=COL_APPAT),
		@Result(property=PROP_APMAT,column=COL_APMAT),
		@Result(property=PROP_NOMBRES,column=COL_NOMBRES),
		@Result(property=PROP_FECHANAC,column=COL_FECHANAC),
		@Result(property=PROP_SEXO,column=COL_SEXO),
		@Result(property=PROP_EMAIL,column=COL_EMAIL),
		@Result(property=PROP_NOMCOMPLETO,column=COL_NOMCOMPLETO),
		@Result(property=PROP_DIRECCION,column=COL_DIRECCION),
		@Result(property=PROP_ABREV,column=COL_ABREV),
		@Result(property=PROP_ESTADO,column=COL_ESTADO),
		@Result(property=PROP_NOMBREDOC,column=COL_NOMBREDOC),
		@Result(property=PROP_FECHAREG,column=COL_FECHAREG),
		@Result(property=PROP_EMAIL2,column=COL_EMAIL2),
		@Result(property=PROP_TELF,column=COL_TELF),
		@Result(property=PROP_CODUBIGEO,column=COL_CODUBIGEO),
		@Result(property=PROP_NOMBREUBIGEO,column=COL_NOMBREUBIGEO),
		@Result(property=PROP_CODNIVEL1,column=COL_CODNIVEL1),
		@Result(property=PROP_NOMBDEPARTAMENTO,column=COL_NOMBDEPARTAMENTO),
		@Result(property=PROP_CODNIVEL2,column=COL_CODNIVEL2),
		@Result(property=PROP_NOMBPROVINCIA,column=COL_NOMBPROVINCIA)
	})
	public List<ClienteSinRucModel> getClienteXNumDocumentoYTipoTodos(@Param("tipoDocumen") Integer tipoDocumen,@Param("numDocumen") String numDocumen);
	
	@Select(value=	GET_CLIENTES_SINRUC_QUERY
					+ " WHERE DOC.EST = 1 AND CLIE.TIPO_DOC = #{tipoDocumen} AND trim(CLIE.NUM_DOC) like trim(#{numDocumen}||'%') AND CLIE.ESTADO = #{estado} AND rownum<100")
	@Results(value={
		@Result(javaType=ClienteSinRucModel.class),
		@Result(property=PROP_IDCLIENTE,column=COL_IDCLIENTE),
		@Result(property=PROP_TIPODOC,column=COL_TIPODOC),
		@Result(property=PROP_NUMDOC,column=COL_NUMDOC),
		@Result(property=PROP_APPAT,column=COL_APPAT),
		@Result(property=PROP_APMAT,column=COL_APMAT),
		@Result(property=PROP_NOMBRES,column=COL_NOMBRES),
		@Result(property=PROP_FECHANAC,column=COL_FECHANAC),
		@Result(property=PROP_SEXO,column=COL_SEXO),
		@Result(property=PROP_EMAIL,column=COL_EMAIL),
		@Result(property=PROP_NOMCOMPLETO,column=COL_NOMCOMPLETO),
		@Result(property=PROP_DIRECCION,column=COL_DIRECCION),
		@Result(property=PROP_ABREV,column=COL_ABREV),
		@Result(property=PROP_ESTADO,column=COL_ESTADO),
		@Result(property=PROP_NOMBREDOC,column=COL_NOMBREDOC),
		@Result(property=PROP_FECHAREG,column=COL_FECHAREG),
		@Result(property=PROP_EMAIL2,column=COL_EMAIL2),
		@Result(property=PROP_TELF,column=COL_TELF),
		@Result(property=PROP_CODUBIGEO,column=COL_CODUBIGEO),
		@Result(property=PROP_NOMBREUBIGEO,column=COL_NOMBREUBIGEO),
		@Result(property=PROP_CODNIVEL1,column=COL_CODNIVEL1),
		@Result(property=PROP_NOMBDEPARTAMENTO,column=COL_NOMBDEPARTAMENTO),
		@Result(property=PROP_CODNIVEL2,column=COL_CODNIVEL2),
		@Result(property=PROP_NOMBPROVINCIA,column=COL_NOMBPROVINCIA)
	})
	public List<ClienteSinRucModel> getClienteXNumDocumentoYTipoxEstado(@Param("tipoDocumen") Integer tipoDocumen,@Param("numDocumen") String numDocumen, @Param("estado") Integer estado);

	
	
	@Select(value=	GET_CLIENTES_SINRUC_QUERY
					+ " WHERE DOC.EST = 1 AND UPPER(trim(CLIE.NOM_COMP)) like UPPER(trim('%'||#{nomCompleto}||'%')) AND rownum<100"
					)
	@Results(value={
		@Result(javaType=ClienteSinRucModel.class),
		@Result(property=PROP_IDCLIENTE,column=COL_IDCLIENTE),
		@Result(property=PROP_TIPODOC,column=COL_TIPODOC),
		@Result(property=PROP_NUMDOC,column=COL_NUMDOC),
		@Result(property=PROP_APPAT,column=COL_APPAT),
		@Result(property=PROP_APMAT,column=COL_APMAT),
		@Result(property=PROP_NOMBRES,column=COL_NOMBRES),
		@Result(property=PROP_FECHANAC,column=COL_FECHANAC),
		@Result(property=PROP_SEXO,column=COL_SEXO),
		@Result(property=PROP_EMAIL,column=COL_EMAIL),
		@Result(property=PROP_NOMCOMPLETO,column=COL_NOMCOMPLETO),
		@Result(property=PROP_DIRECCION,column=COL_DIRECCION),
		@Result(property=PROP_ABREV,column=COL_ABREV),
		@Result(property=PROP_ESTADO,column=COL_ESTADO),
		@Result(property=PROP_NOMBREDOC,column=COL_NOMBREDOC),
		@Result(property=PROP_FECHAREG,column=COL_FECHAREG),
		@Result(property=PROP_EMAIL2,column=COL_EMAIL2),
		@Result(property=PROP_TELF,column=COL_TELF),
		@Result(property=PROP_CODUBIGEO,column=COL_CODUBIGEO),
		@Result(property=PROP_NOMBREUBIGEO,column=COL_NOMBREUBIGEO),
		@Result(property=PROP_CODNIVEL1,column=COL_CODNIVEL1),
		@Result(property=PROP_NOMBDEPARTAMENTO,column=COL_NOMBDEPARTAMENTO),
		@Result(property=PROP_CODNIVEL2,column=COL_CODNIVEL2),
		@Result(property=PROP_NOMBPROVINCIA,column=COL_NOMBPROVINCIA)
	})
	public List<ClienteSinRucModel> getClienteXNombreCompletoTodos(@Param("nomCompleto") String nomCompleto);
	
	@Select(value=	GET_CLIENTES_SINRUC_QUERY
					+ " WHERE DOC.EST = 1 AND UPPER(trim(CLIE.NOM_COMP)) like UPPER(trim('%'||#{nomCompleto}||'%')) AND CLIE.ESTADO = #{estado} AND rownum<100"
					)
	@Results(value={
		@Result(javaType=ClienteSinRucModel.class),
		@Result(property=PROP_IDCLIENTE,column=COL_IDCLIENTE),
		@Result(property=PROP_TIPODOC,column=COL_TIPODOC),
		@Result(property=PROP_NUMDOC,column=COL_NUMDOC),
		@Result(property=PROP_APPAT,column=COL_APPAT),
		@Result(property=PROP_APMAT,column=COL_APMAT),
		@Result(property=PROP_NOMBRES,column=COL_NOMBRES),
		@Result(property=PROP_FECHANAC,column=COL_FECHANAC),
		@Result(property=PROP_SEXO,column=COL_SEXO),
		@Result(property=PROP_EMAIL,column=COL_EMAIL),
		@Result(property=PROP_NOMCOMPLETO,column=COL_NOMCOMPLETO),
		@Result(property=PROP_DIRECCION,column=COL_DIRECCION),
		@Result(property=PROP_ABREV,column=COL_ABREV),
		@Result(property=PROP_ESTADO,column=COL_ESTADO),
		@Result(property=PROP_NOMBREDOC,column=COL_NOMBREDOC),
		@Result(property=PROP_FECHAREG,column=COL_FECHAREG),
		@Result(property=PROP_EMAIL2,column=COL_EMAIL2),
		@Result(property=PROP_TELF,column=COL_TELF),
		@Result(property=PROP_CODUBIGEO,column=COL_CODUBIGEO),
		@Result(property=PROP_NOMBREUBIGEO,column=COL_NOMBREUBIGEO),
		@Result(property=PROP_CODNIVEL1,column=COL_CODNIVEL1),
		@Result(property=PROP_NOMBDEPARTAMENTO,column=COL_NOMBDEPARTAMENTO),
		@Result(property=PROP_CODNIVEL2,column=COL_CODNIVEL2),
		@Result(property=PROP_NOMBPROVINCIA,column=COL_NOMBPROVINCIA)
	})
	public List<ClienteSinRucModel> getClienteXNombreCompletoxEstado(@Param("nomCompleto") String nomCompleto, @Param("estado") Integer estado);
	
	@Insert(value="{CALL REGISTRAR_CLIENTE_SINRUC ("
			+"#{cliente.tipoDoc, mode=IN, jdbcType=INTEGER},"
			+"#{cliente.numDoc, mode=IN, jdbcType=VARCHAR},"
			+"#{cliente.nombres, mode=IN, jdbcType=VARCHAR},"
			+"#{cliente.apePat, mode=IN, jdbcType=VARCHAR},"
			+"#{cliente.apeMat, mode=IN, jdbcType=VARCHAR},"
			+"#{cliente.nomCompleto, mode=IN, jdbcType=VARCHAR},"
			+"#{cliente.email, mode=IN, jdbcType=VARCHAR},"
			+"#{cliente.direccion, mode=IN, jdbcType=VARCHAR},"
			+"TO_DATE(#{cliente.fechaNac},'DD/MM/YYYY'),"
			+"#{cliente.sexo, mode=IN, jdbcType=VARCHAR},"
			+"#{cliente.email2, mode=IN, jdbcType=VARCHAR},"
			+"#{cliente.telefono, mode=IN, jdbcType=VARCHAR},"
			+"#{cliente.usuCrea, mode=IN, jdbcType=VARCHAR},"
			+"#{cliente.codUbigeo, mode=IN, jdbcType=VARCHAR},"
			+"#{cliente.idCliente, mode=OUT, jdbcType=INTEGER},"
			+"#{cliente.status, mode=OUT, jdbcType=INTEGER}"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void registrarClienteSinRUC(@Param("cliente") ClienteSinRucModel cliente);
	
	@Insert(value="{CALL REGISTRAR_CLIENTE_SINRUC_EXTRA ("
			+"#{cliente.tipoDoc, mode=IN, jdbcType=INTEGER},"
			+"#{cliente.numDoc, mode=IN, jdbcType=VARCHAR},"
			+"#{cliente.nomCompleto, mode=IN, jdbcType=VARCHAR},"
			+"#{cliente.email, mode=IN, jdbcType=VARCHAR},"
			+"#{cliente.direccion, mode=IN, jdbcType=VARCHAR},"
			+"TO_DATE(#{cliente.fechaNac},'DD/MM/YYYY'),"
			+"#{cliente.sexo, mode=IN, jdbcType=VARCHAR},"
			+"#{cliente.telefono, mode=IN, jdbcType=VARCHAR},"
			+"#{cliente.usuCrea, mode=IN, jdbcType=VARCHAR},"
			+"#{cliente.codUbigeo, mode=IN, jdbcType=VARCHAR},"
			+"#{cliente.idCliente, mode=OUT, jdbcType=INTEGER},"
			+"#{cliente.status, mode=OUT, jdbcType=INTEGER}"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void registrarClienteSinRUCExtra(@Param("cliente") ClienteSinRucModel cliente);
	
	@Update(value = "UPDATE WEBQPROTESORERIA.CLIENTE_SIN_RUC \n " +
			" SET ESTADO = #{estado} \n " +
			" WHERE  ID_CLIENTE = #{idCliente}")
	public void actualizarEstado(@Param("idCliente") Integer idCliente, @Param("estado") Integer estado);
	
	@Update(value="{CALL UPDATE_CLIENTE_SINRUC ("
			+"#{cliente.idCliente, mode=IN, jdbcType=INTEGER},"
			+"#{cliente.tipoDoc, mode=IN, jdbcType=INTEGER},"
			+"#{cliente.numDoc, mode=IN, jdbcType=VARCHAR},"
			+"#{cliente.nombres, mode=IN, jdbcType=VARCHAR},"
			+"#{cliente.apePat, mode=IN, jdbcType=VARCHAR},"
			+"#{cliente.apeMat, mode=IN, jdbcType=VARCHAR},"
			+"#{cliente.nomCompleto, mode=IN, jdbcType=VARCHAR},"
			+"#{cliente.email, mode=IN, jdbcType=VARCHAR},"
			+"#{cliente.direccion, mode=IN, jdbcType=VARCHAR},"
			+"#{cliente.fechaNac, mode=IN, jdbcType=VARCHAR},"
			+"#{cliente.sexo, mode=IN, jdbcType=VARCHAR},"
			+"#{cliente.email2, mode=IN, jdbcType=VARCHAR},"
			+"#{cliente.telefono, mode=IN, jdbcType=VARCHAR},"
			+"#{cliente.codUbigeo, mode=IN, jdbcType=VARCHAR},"
			+"#{cliente.status, mode=OUT, jdbcType=INTEGER}"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void actualizarCliente(@Param("cliente") ClienteSinRucModel cliente);
	
}
