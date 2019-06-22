package pe.edu.unmsm.quipucamayoc.persistence;

import pe.edu.unmsm.quipucamayoc.model.ClienteRucModel;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

public interface ClienteRucPersistence {
	
	String PROP_RUC = "ruc";
	String PROP_RAZONSOCIAL = "razonSocial";
	String PROP_DIRECCION = "direccion";
	String PROP_NOMBRE = "nombre";
	String PROP_APPATERNO = "apPat";
	String PROP_APMATERNO = "apMat";
	String PROP_EMAIL = "email";
	String PROP_FECHAREG = "fechaReg";
	String PROP_TELF = "telf";
	String PROP_FECHANAC = "fechaNacimiento";
	String PROP_SEXO = "sexo";
	String PROP_ESTADO = "estado";	
	String PROP_TIPOPERSONA = "tipoPersona";
	String PROP_TIPOPERSONADESC = "tipoPersonaDesc";
	String PROP_SEXODESCR = "sexoDescr";
	String PROP_EMAIL2 = "email2";
	String PROP_NOMBRECOMERCIAL = "nombreComercial";
	String PROP_CODUBIGEO = "codUbigeo";
	String PROP_NOMBREUBIGEO = "nombreUbigeo";
	String PROP_CODNIVEL1 = "codNivel1";
	String PROP_NOMBDEPARTAMENTO = "nombDepartamento";
	String PROP_CODNIVEL2 = "codNivel2";
	String PROP_NOMBPROVINCIA = "nombProvincia";
	String PROP_ESTADOSUNAT = "estadoSunat";
	String PROP_ESTADOSUNATDESCR = "estadoSunatDescr";
	String PROP_SONDICIONSUNAT = "condicionSunat";
	String PROP_SONDICIONSUNATDESCR = "condicionSunatDescr";
	
	String COL_RUC = "RUC";
	String COL_RAZONSOCIAL = "RAZON_SOCIAL";
	String COL_DIRECCION = "DIRECCION";
	String COL_NOMBRE = "NOMBRE";
	String COL_APPATERNO = "APE_PAT";
	String COL_APMATERNO = "APE_MAT";
	String COL_EMAIL = "EMAIL";
	String COL_FECHAREG = "FECHA_REGISTRO";
	String COL_TELF = "TELF";
	String COL_FECHANAC = "FECHA_NACIMIENTO";
	String COL_SEXO = "SEXO";
	String COL_ESTADO = "EST";	
	String COL_TIPOPERSONA = "tipo_persona";
	String COL_TIPOPERSONADESC = "tipo_persona_desc";
	String COL_SEXODESCR = "sexo_descr";
	String COL_EMAIL2 = "EMAIL2";
	String COL_NOMBRECOMERCIAL = "NOM_COMERCIAL";
	String COL_CODUBIGEO = "COD_UBIGEO";
	String COL_NOMBREUBIGEO = "NOMBRE_UBIGEO";
	String COL_CODNIVEL1 = "COD_NIVEL1";
	String COL_NOMBDEPARTAMENTO = "NOMB_DEPARTAMENTO";
	String COL_CODNIVEL2 = "COD_NIVEL2";
	String COL_NOMBPROVINCIA = "NOMB_PROVINCIA";
	String COL_ESTADOSUNAT = "estado_sunat";
	String COL_ESTADOSUNATDESCR = "estado_sunat_descr";
	String COL_CONDICIONSUNAT = "condicion_sunat";
	String COL_CONDICIONSUNATDESCR = "condicion_sunat_descr";
	
	String IN_RUC = "#{cliente.ruc, mode=IN, jdbcType=VARCHAR}";
	String IN_RAZONSOCIAL = "#{cliente.razonSocial, mode=IN, jdbcType=VARCHAR}";
	String IN_NOMBRE = "#{cliente.nombre, mode=IN, jdbcType=VARCHAR}";
	String IN_APPAT = "#{cliente.apPat, mode=IN, jdbcType=VARCHAR}";
	String IN_APMAT = "#{cliente.apMat, mode=IN, jdbcType=VARCHAR}";
	String IN_EMAIL = "#{cliente.email, mode=IN, jdbcType=VARCHAR}";
	String IN_TELF = "#{cliente.telf, mode=IN, jdbcType=VARCHAR}";
	String IN_DIRECCION = "#{cliente.direccion, mode=IN, jdbcType=VARCHAR}";
	String IN_FECHANAC = "#{cliente.fechaNacimiento, mode=IN, jdbcType=VARCHAR}";
	String IN_SEXO = "#{cliente.sexo, mode=IN, jdbcType=VARCHAR}";
	String IN_EMAIL2 = "#{cliente.email2, mode=IN, jdbcType=VARCHAR}";
	String IN_NOMBCOMERCIAL = "#{cliente.nombreComercial, mode=IN, jdbcType=VARCHAR}";
	String IN_USUCREA = "#{cliente.usuCrea, mode=IN, jdbcType=VARCHAR}";
	String IN_TIPOPERSONA = "#{cliente.tipoPersona, mode=IN, jdbcType=VARCHAR}";
	String IN_CODUBIGEO = "#{cliente.codUbigeo, mode=IN, jdbcType=VARCHAR}";
	String IN_ESTADOSUNAT = "#{cliente.estadoSunat, mode=IN, jdbcType=VARCHAR}";
	String IN_CONDICIONSUNAT = "#{cliente.condicionSunat, mode=IN, jdbcType=VARCHAR}";
	String OUT_STATUS = "#{cliente.status, mode=OUT, jdbcType=INTEGER}";
	
	String GET_CLIENTES_QUERY = "SELECT " +
								"CL.RUC AS RUC, " +
								"CL.RAZON_SOCIAL AS RAZON_SOCIAL, " +
								"CL.TIPO_PERSONA AS tipo_persona, " +
								"CL.DIRECCION AS DIRECCION, " +
								"CL.EMAIL AS EMAIL, " +
								"TO_CHAR(CL.FECHA_REG,'DD/MM/YYYY') AS FECHA_REGISTRO, " +
								"CL.TELF AS TELF, " +
								"TO_CHAR(CL.NACIMIENTO,'DD/MM/YYYY') AS FECHA_NACIMIENTO, " +
								"CL.SEXO AS SEXO, " +
								"CL.EST AS EST, " +
								"CL.EMAIL2 AS EMAIL2,  " +
								"CL.NOM_COMERCIAL AS NOM_COMERCIAL, " +
								"CL.COD_UBIGEO AS COD_UBIGEO, " +
								"UB.NOMBRE AS NOMBRE_UBIGEO, " +
								"UB.NIV1 AS COD_NIVEL1, " +
								"UB2.NOMBRE AS NOMB_DEPARTAMENTO, " +
								"UB.NIV2 AS COD_NIVEL2, " +
								"UB3.NOMBRE AS NOMB_PROVINCIA, " +
								"CL.ESTADO_SUNAT AS estado_sunat, " +
								"CL.CONDICION_SUNAT AS condicion_sunat, " +
								"SSEX.DESC_SEXO AS sexo_descr, " +
								"STP.DESC_CONTRIBUYENTE AS tipo_persona_desc, " +
								"SESTC.DESC_ESTADO_CONTR AS estado_sunat_descr, " +
								"SCONDC.DESC_CONDICION_CONTR AS condicion_sunat_descr " +
								"FROM " +
								"WEBQPROTESORERIA.CLIENTE_CON_RUC CL " +
								"INNER JOIN WEBQPROTESORERIA.SUNAT_TIPO_CONTRIBUYENTE STP ON CL.TIPO_PERSONA = STP.COD_CONTRIBUYENTE " +
								"INNER JOIN WEBQPROTESORERIA.SUNAT_ESTADO_CONTRIBUYENTE SESTC ON CL.ESTADO_SUNAT = SESTC.COD_ESTADO_CONTR " +
								"INNER JOIN WEBQPROTESORERIA.SUNAT_CONDICION_CONTRIBUYENTE SCONDC ON CL.CONDICION_SUNAT = SCONDC.COD_CONDICION_CONTR " +
								"LEFT JOIN WEBQPROTESORERIA.SUNAT_SEXO SSEX ON CL.SEXO = SSEX.COD_SEXO " +
								"LEFT JOIN WEBQPROTESORERIA.UBIGEO UB ON CL.COD_UBIGEO = UB.CODIGO " +
								"LEFT JOIN WEBQPROTESORERIA.UBIGEO UB2 ON UB2.CODIGO = UB.NIV1 " +
								"LEFT JOIN WEBQPROTESORERIA.UBIGEO UB3 ON UB3.CODIGO = UB.NIV2 ";
	
	String WHERE_SENTENCE = " WHERE ";
	
	@Select(value=	"SELECT\n" +
					"WEBQPROTESORERIA.CLIENTE_CON_RUC.RUC, " +
					"WEBQPROTESORERIA.CLIENTE_CON_RUC.RAZON_SOCIAL, " +
					"WEBQPROTESORERIA.CLIENTE_CON_RUC.DIRECCION, " +
					"WEBQPROTESORERIA.CLIENTE_CON_RUC.NOMBRE, " +
					"WEBQPROTESORERIA.CLIENTE_CON_RUC.APE_PAT, " +
					"WEBQPROTESORERIA.CLIENTE_CON_RUC.APE_MAT, " +
					"WEBQPROTESORERIA.CLIENTE_CON_RUC.EMAIL, " +
					"TO_CHAR(WEBQPROTESORERIA.CLIENTE_CON_RUC.FECHA_REG,'DD/MM/YYYY') AS FECHA_REGISTRO, " +
					"WEBQPROTESORERIA.CLIENTE_CON_RUC.TELF,\n" +
					"TO_CHAR(WEBQPROTESORERIA.CLIENTE_CON_RUC.NACIMIENTO,'DD/MM/YYYY') AS FECHA_NACIMIENTO, " +
					"WEBQPROTESORERIA.CLIENTE_CON_RUC.SEXO, " +
					"WEBQPROTESORERIA.CLIENTE_CON_RUC.EST  " +
					"FROM " +
					"WEBQPROTESORERIA.CLIENTE_CON_RUC " +
					"WHERE " +
					"TRIM(WEBQPROTESORERIA.CLIENTE_CON_RUC.RUC) LIKE TRIM(#{id})")
	@Results(value={
			@Result(javaType=ClienteRucModel.class),
			@Result(property=PROP_RUC,column=COL_RUC),
			@Result(property=PROP_RAZONSOCIAL,column=COL_RAZONSOCIAL),
			@Result(property=PROP_DIRECCION,column=COL_DIRECCION),
			@Result(property=PROP_NOMBRE,column=COL_NOMBRE),
			@Result(property=PROP_APPATERNO,column=COL_APPATERNO),
			@Result(property=PROP_APMATERNO,column=COL_APMATERNO),
			@Result(property=PROP_EMAIL,column=COL_EMAIL),
			@Result(property=PROP_FECHAREG,column=COL_FECHAREG),
			@Result(property=PROP_TELF,column=COL_TELF),
			@Result(property=PROP_FECHANAC,column=COL_FECHANAC),
			@Result(property=PROP_SEXO,column=COL_SEXO),
			@Result(property=PROP_ESTADO,column=COL_ESTADO)})
	public ClienteRucModel buscarRuc(@Param("id") String id);
	
	@Select(value=	"SELECT\n" +
					"WEBQPROTESORERIA.CLIENTE_CON_RUC.RUC, " +
					"WEBQPROTESORERIA.CLIENTE_CON_RUC.RAZON_SOCIAL, " +
					"WEBQPROTESORERIA.CLIENTE_CON_RUC.DIRECCION, " +
					"ROWNUM-1 " +
					"FROM " +
					"WEBQPROTESORERIA.CLIENTE_CON_RUC " +
					"WHERE " +
					"UPPER(WEBQPROTESORERIA.CLIENTE_CON_RUC.RAZON_SOCIAL) LIKE UPPER(#{id}) AND ROWNUM <8")
	@Results(value={
			@Result(javaType=ClienteRucModel.class),
			@Result(property=PROP_RUC,column=COL_RUC),
			@Result(property=PROP_RAZONSOCIAL,column=COL_RAZONSOCIAL),
			@Result(property=PROP_DIRECCION,column=COL_DIRECCION),
			@Result(property="posicion",column="ROWNUM-1")})
	public List<ClienteRucModel> buscarRazon(@Param("id") String id);
	
	
	@Select(value=	GET_CLIENTES_QUERY
					+ WHERE_SENTENCE
					+ " TRIM(CL.RUC) LIKE TRIM(#{ruc}||'%') AND rownum<100")
@Results(value={
	@Result(javaType=ClienteRucModel.class),
	@Result(property=PROP_RUC,column=COL_RUC),
	@Result(property=PROP_RAZONSOCIAL,column=COL_RAZONSOCIAL),
	@Result(property=PROP_TIPOPERSONA,column=COL_TIPOPERSONA),
	@Result(property=PROP_TIPOPERSONADESC,column=COL_TIPOPERSONADESC),
	@Result(property=PROP_DIRECCION,column=COL_DIRECCION),
	@Result(property=PROP_EMAIL,column=COL_EMAIL),
	@Result(property=PROP_FECHAREG,column=COL_FECHAREG),
	@Result(property=PROP_TELF,column=COL_TELF),
	@Result(property=PROP_FECHANAC,column=COL_FECHANAC),
	@Result(property=PROP_SEXO,column=COL_SEXO),
	@Result(property=PROP_SEXODESCR,column=COL_SEXODESCR),
	@Result(property=PROP_ESTADO,column=COL_ESTADO),
	@Result(property=PROP_EMAIL2,column=COL_EMAIL2),
	@Result(property=PROP_NOMBRECOMERCIAL,column=COL_NOMBRECOMERCIAL),
	@Result(property=PROP_CODUBIGEO,column=COL_CODUBIGEO),
	@Result(property=PROP_NOMBREUBIGEO,column=COL_NOMBREUBIGEO),
	@Result(property=PROP_CODNIVEL1,column=COL_CODNIVEL1),
	@Result(property=PROP_NOMBDEPARTAMENTO,column=COL_NOMBDEPARTAMENTO),
	@Result(property=PROP_CODNIVEL2,column=COL_CODNIVEL2),
	@Result(property=PROP_NOMBPROVINCIA,column=COL_NOMBPROVINCIA),
	@Result(property=PROP_ESTADOSUNAT,column=COL_ESTADOSUNAT),
	@Result(property=PROP_ESTADOSUNATDESCR,column=COL_ESTADOSUNATDESCR),
	@Result(property=PROP_SONDICIONSUNAT,column=COL_CONDICIONSUNAT),
	@Result(property=PROP_SONDICIONSUNATDESCR,column=COL_CONDICIONSUNATDESCR)
})
public List<ClienteRucModel> buscarxRUCTodos(@Param("ruc") String ruc);
	
	@Select(value=	GET_CLIENTES_QUERY
					+ WHERE_SENTENCE
					+ " TRIM(CL.RUC) LIKE TRIM(#{ruc}||'%') " +
					" AND CL.EST=#{estado} " + 
					" AND rownum<100"
					)
@Results(value={
	@Result(javaType=ClienteRucModel.class),
	@Result(property=PROP_RUC,column=COL_RUC),
	@Result(property=PROP_RAZONSOCIAL,column=COL_RAZONSOCIAL),
	@Result(property=PROP_TIPOPERSONA,column=COL_TIPOPERSONA),
	@Result(property=PROP_TIPOPERSONADESC,column=COL_TIPOPERSONADESC),
	@Result(property=PROP_DIRECCION,column=COL_DIRECCION),
	@Result(property=PROP_EMAIL,column=COL_EMAIL),
	@Result(property=PROP_FECHAREG,column=COL_FECHAREG),
	@Result(property=PROP_TELF,column=COL_TELF),
	@Result(property=PROP_FECHANAC,column=COL_FECHANAC),
	@Result(property=PROP_SEXO,column=COL_SEXO),
	@Result(property=PROP_SEXODESCR,column=COL_SEXODESCR),
	@Result(property=PROP_ESTADO,column=COL_ESTADO),
	@Result(property=PROP_EMAIL2,column=COL_EMAIL2),
	@Result(property=PROP_NOMBRECOMERCIAL,column=COL_NOMBRECOMERCIAL),
	@Result(property=PROP_CODUBIGEO,column=COL_CODUBIGEO),
	@Result(property=PROP_NOMBREUBIGEO,column=COL_NOMBREUBIGEO),
	@Result(property=PROP_CODNIVEL1,column=COL_CODNIVEL1),
	@Result(property=PROP_NOMBDEPARTAMENTO,column=COL_NOMBDEPARTAMENTO),
	@Result(property=PROP_CODNIVEL2,column=COL_CODNIVEL2),
	@Result(property=PROP_NOMBPROVINCIA,column=COL_NOMBPROVINCIA),
	@Result(property=PROP_ESTADOSUNAT,column=COL_ESTADOSUNAT),
	@Result(property=PROP_ESTADOSUNATDESCR,column=COL_ESTADOSUNATDESCR),
	@Result(property=PROP_SONDICIONSUNAT,column=COL_CONDICIONSUNAT),
	@Result(property=PROP_SONDICIONSUNATDESCR,column=COL_CONDICIONSUNATDESCR)
})
public List<ClienteRucModel> buscarxRUCxEstado(@Param("ruc") String ruc, @Param("estado") Integer estado);
	
	@Select(value=	GET_CLIENTES_QUERY
					+ WHERE_SENTENCE
					+ " UPPER(TRIM(CL.RAZON_SOCIAL)) like UPPER(TRIM('%'||#{razonSocial}||'%')) AND rownum<100")
	@Results(value={
		@Result(javaType=ClienteRucModel.class),
		@Result(property=PROP_RUC,column=COL_RUC),
		@Result(property=PROP_RAZONSOCIAL,column=COL_RAZONSOCIAL),
		@Result(property=PROP_TIPOPERSONA,column=COL_TIPOPERSONA),
		@Result(property=PROP_TIPOPERSONADESC,column=COL_TIPOPERSONADESC),
		@Result(property=PROP_DIRECCION,column=COL_DIRECCION),
		@Result(property=PROP_EMAIL,column=COL_EMAIL),
		@Result(property=PROP_FECHAREG,column=COL_FECHAREG),
		@Result(property=PROP_TELF,column=COL_TELF),
		@Result(property=PROP_FECHANAC,column=COL_FECHANAC),
		@Result(property=PROP_SEXO,column=COL_SEXO),
		@Result(property=PROP_SEXODESCR,column=COL_SEXODESCR),
		@Result(property=PROP_ESTADO,column=COL_ESTADO),
		@Result(property=PROP_EMAIL2,column=COL_EMAIL2),
		@Result(property=PROP_NOMBRECOMERCIAL,column=COL_NOMBRECOMERCIAL),
		@Result(property=PROP_CODUBIGEO,column=COL_CODUBIGEO),
		@Result(property=PROP_NOMBREUBIGEO,column=COL_NOMBREUBIGEO),
		@Result(property=PROP_CODNIVEL1,column=COL_CODNIVEL1),
		@Result(property=PROP_NOMBDEPARTAMENTO,column=COL_NOMBDEPARTAMENTO),
		@Result(property=PROP_CODNIVEL2,column=COL_CODNIVEL2),
		@Result(property=PROP_NOMBPROVINCIA,column=COL_NOMBPROVINCIA),
		@Result(property=PROP_ESTADOSUNAT,column=COL_ESTADOSUNAT),
		@Result(property=PROP_ESTADOSUNATDESCR,column=COL_ESTADOSUNATDESCR),
		@Result(property=PROP_SONDICIONSUNAT,column=COL_CONDICIONSUNAT),
		@Result(property=PROP_SONDICIONSUNATDESCR,column=COL_CONDICIONSUNATDESCR)
	})
	public List<ClienteRucModel> buscarRazonSocialTodos(@Param("razonSocial") String razonSocial);
	
	@Select(value=	GET_CLIENTES_QUERY
					+ WHERE_SENTENCE
					+ " UPPER(TRIM(CL.RAZON_SOCIAL)) like UPPER(TRIM('%'||#{razonSocial}||'%')) AND CL.EST=#{estado} AND rownum<100")
	@Results(value={
		@Result(javaType=ClienteRucModel.class),
		@Result(property=PROP_RUC,column=COL_RUC),
		@Result(property=PROP_RAZONSOCIAL,column=COL_RAZONSOCIAL),
		@Result(property=PROP_TIPOPERSONA,column=COL_TIPOPERSONA),
		@Result(property=PROP_TIPOPERSONADESC,column=COL_TIPOPERSONADESC),
		@Result(property=PROP_DIRECCION,column=COL_DIRECCION),
		@Result(property=PROP_NOMBRE,column=COL_NOMBRE),
		@Result(property=PROP_APPATERNO,column=COL_APPATERNO),
		@Result(property=PROP_APMATERNO,column=COL_APMATERNO),
		@Result(property=PROP_EMAIL,column=COL_EMAIL),
		@Result(property=PROP_FECHAREG,column=COL_FECHAREG),
		@Result(property=PROP_TELF,column=COL_TELF),
		@Result(property=PROP_FECHANAC,column=COL_FECHANAC),
		@Result(property=PROP_SEXO,column=COL_SEXO),
		@Result(property=PROP_SEXODESCR,column=COL_SEXODESCR),
		@Result(property=PROP_ESTADO,column=COL_ESTADO),
		@Result(property=PROP_EMAIL2,column=COL_EMAIL2),
		@Result(property=PROP_NOMBRECOMERCIAL,column=COL_NOMBRECOMERCIAL),
		@Result(property=PROP_CODUBIGEO,column=COL_CODUBIGEO),
		@Result(property=PROP_NOMBREUBIGEO,column=COL_NOMBREUBIGEO),
		@Result(property=PROP_CODNIVEL1,column=COL_CODNIVEL1),
		@Result(property=PROP_NOMBDEPARTAMENTO,column=COL_NOMBDEPARTAMENTO),
		@Result(property=PROP_CODNIVEL2,column=COL_CODNIVEL2),
		@Result(property=PROP_NOMBPROVINCIA,column=COL_NOMBPROVINCIA),
		@Result(property=PROP_ESTADOSUNAT,column=COL_ESTADOSUNAT),
		@Result(property=PROP_ESTADOSUNATDESCR,column=COL_ESTADOSUNATDESCR),
		@Result(property=PROP_SONDICIONSUNAT,column=COL_CONDICIONSUNAT),
		@Result(property=PROP_SONDICIONSUNATDESCR,column=COL_CONDICIONSUNATDESCR)
	})
	public List<ClienteRucModel> buscarRazonSocialxEstado(@Param("razonSocial") String razonSocial, @Param("estado") Integer estado);

	
	@Insert(value="{CALL REGISTRAR_CLIENTE_RUC ("
					+ IN_RUC + ","
					+ IN_RAZONSOCIAL + ","
					+ IN_NOMBRE + ","
					+ IN_APPAT + ","
					+ IN_APMAT + ","
					+ IN_EMAIL + ","
					+ IN_TELF + ","
					+ IN_DIRECCION + ","
					+ IN_FECHANAC + ","
					+ IN_SEXO + ","
					+ IN_EMAIL2 + ","
					+ IN_NOMBCOMERCIAL + ","
					+ IN_USUCREA + ","
					+ IN_TIPOPERSONA + ","
					+ IN_CODUBIGEO + ","
					+ IN_ESTADOSUNAT + ","
					+ IN_CONDICIONSUNAT + ","		
					+ OUT_STATUS
					+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void registrarClienteRUC(@Param("cliente") ClienteRucModel cliente);
	
	@Insert(value="{CALL REGISTRAR_CLIENTE_RUC_EXTRA ("
					+ IN_RUC + ","
					+ IN_RAZONSOCIAL + ","
					+ IN_NOMBRE + ","
					+ IN_APPAT + ","
					+ IN_APMAT + ","
					+ IN_EMAIL + ","
					+ IN_TELF + ","
					+ IN_DIRECCION + ","
					+ IN_FECHANAC + ","
					+ IN_SEXO + ","
					+ IN_EMAIL2 + ","
					+ IN_NOMBCOMERCIAL + ","
					+ IN_USUCREA + ","
					+ IN_TIPOPERSONA + ","
					+ IN_CODUBIGEO + ","
					+ IN_ESTADOSUNAT + ","
					+ IN_CONDICIONSUNAT + ","		
					+ OUT_STATUS
					+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void registrarClienteRUCExtra(@Param("cliente") ClienteRucModel cliente);
	
	
	@Update(value = "UPDATE WEBQPROTESORERIA.CLIENTE_CON_RUC \n " +
			" SET EST = #{estado} \n " +
			" WHERE trim(RUC) like trim(#{ruc}) ")
	public void actualizarEstado(@Param("ruc") String ruc, @Param("estado") Integer estado);
	
	@Update(value="{CALL ACTUALIZAR_CLIENTE_RUC ("
					+ IN_RUC + ","
					+ IN_RAZONSOCIAL + ","
					+ IN_NOMBRE + ","
					+ IN_APPAT + ","
					+ IN_APMAT + ","
					+ IN_EMAIL + ","
					+ IN_TELF + ","
					+ IN_DIRECCION + ","
					+ IN_FECHANAC + ","
					+ IN_SEXO + ","
					+ IN_EMAIL2 + ","
					+ IN_NOMBCOMERCIAL + ","
					+ IN_CODUBIGEO + ","
					+ IN_ESTADOSUNAT + ","
					+ IN_CONDICIONSUNAT + ","		
					+ OUT_STATUS
					+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void actualizarClienteRUC(@Param("cliente") ClienteRucModel cliente);
	
	@Update(value="{CALL ACTUALIZAR_CLIENTE_RUC_EXTRA ("
					+ IN_RUC + ","
					+ IN_RAZONSOCIAL + ","
					+ IN_DIRECCION + ","
					+ IN_CODUBIGEO + ","
					+ IN_ESTADOSUNAT + ","
					+ IN_CONDICIONSUNAT + ","		
					+ OUT_STATUS
					+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void actualizarClienteRUCExtra(@Param("cliente") ClienteRucModel cliente);
	
}
