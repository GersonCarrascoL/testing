package pe.edu.unmsm.quipucamayoc.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import pe.edu.unmsm.quipucamayoc.model.AlumnoSUMModel;


public interface AlumnoSumPersistence {
	
	String PROP_NOMBFACULTAD = "nombFacultad";
	String PROP_NOMBRES = "nombres";
	String PROP_NUMDOC = "numDoc";
	String PROP_TIPODOC = "tipoDoc";
	String PROP_FECHANAC = "fechaNac";
	String PROP_SEXO = "sexo";
	String PROP_TCRED = "tCred";
	String PROP_SITACADEMICA = "sitAcademica";
	String PROP_ESTPERMANENCIA = "estPermanencia";
	String PROP_PROMEDIO = "promedio";
	String PROP_EMAIL = "email";
	String PROP_CICLOESTUDIOS = "cicloEstudios";
	String PROP_ULTIPERIODMAT = "ultiPeriodMat";
	String PROP_NOMCOMPLETO= "nomCompleto";
	String PROP_DIRECCION= "direccion";
	String PROP_NOMBREDOC= "nombreDoc";
	String PROP_ABREV= "abrev";
	
	String COL_NOMBFACULTAD = "nombFacultad";
	String COL_NOMBRES = "nombres";
	String COL_NUMDOC = "numDoc";
	String COL_TIPODOC = "tipoDoc";
	String COLS_FECHANAC = "fechaNac";
	String COL_SEXO = "sexo";
	String COL_TCRED = "tCred";
	String COL_SITACADEMICA = "sitAcademica";
	String COL_ESTPERMANENCIA = "estPermanencia";
	String COL_PROMEDIO = "promedio";
	String COL_EMAIL = "email";
	String COL_CICLOESTUDIOS = "cicloEstudios";
	String COL_ULTIPERIODMAT = "ultiPeriodMat";
	String COL_NOMCOMPLETO= "nomCompleto";
	String COL_DIRECCION= "direccion";
	String COL_NOMBREDOC= "nombreDoc";
	String COL_ABREV= "abrev";

	@Select(value=	"SELECT \n" +
					" ALU.CODIGO_MATRICULA AS codigo_matricula, \n" +
					" FAC.UD_DSC AS nombFacultad, \n" +
					" ALU.NUMERO_DOCUMENTO AS numDoc, \n" +
					" ALU.EMAIL AS email, \n" +
					" ALU.NOM_COMP AS nomCompleto, \n" +
					" ALU.DIRECCION AS direccion, \n" +
					" DOC.NOMBRE AS nombreDoc, \n" +
					" DOC.ABREV AS abrev \n" +
					" FROM WEBQPROTESORERIA.ALUMNOSUM ALU \n" +
					" INNER JOIN WEBQPROTESORERIA.DOCUMENTO_IDENTIDAD DOC ON ALU.TIPO_DOCUMENTO = DOC.ID_DOC_IDENT \n" +
					" INNER JOIN WEBQPROTESORERIA.FACULTAD FAC ON ALU.CODIGO_FACULTAD = FAC.CODIGO \n" +
					" WHERE DOC.EST = 1 AND UPPER(TRIM(ALU.NOM_COMP)) LIKE UPPER(TRIM(#{nomCompleto}||'%')) and rownum<20")
	@Results(value={
	@Result(javaType=AlumnoSUMModel.class),
	@Result(property="codigoMatricula",column="codigo_matricula"),
	@Result(property=PROP_NOMBFACULTAD,column=COL_NOMBFACULTAD),
	@Result(property=PROP_NUMDOC,column=COL_NUMDOC),
	@Result(property=PROP_EMAIL,column=COL_EMAIL),
	@Result(property=PROP_NOMCOMPLETO,column=COL_NOMCOMPLETO),
	@Result(property=PROP_DIRECCION,column=COL_DIRECCION),
	@Result(property=PROP_NOMBREDOC,column=COL_NOMBREDOC),
	@Result(property=PROP_ABREV,column=COL_ABREV)})
	public List<AlumnoSUMModel> getAlumnoXNombreCompleto(@Param("nomCompleto") String nomCompleto);
	
	@Select(value=	"SELECT \n" +
			" ALU.CODIGO_MATRICULA AS codigo_matricula, \n" +
			" FAC.UD_DSC AS nombFacultad, \n" +
			" ALU.NUMERO_DOCUMENTO AS numDoc, \n" +
			" ALU.EMAIL AS email, \n" +
			" ALU.NOM_COMP AS nomCompleto, \n" +
			" ALU.DIRECCION AS direccion, \n" +
			" DOC.NOMBRE AS nombreDoc, \n" +
			" DOC.ABREV AS abrev \n" +
			" FROM WEBQPROTESORERIA.ALUMNOSUM ALU \n" +
			" INNER JOIN WEBQPROTESORERIA.DOCUMENTO_IDENTIDAD DOC ON ALU.TIPO_DOCUMENTO = DOC.ID_DOC_IDENT \n" +
			" INNER JOIN WEBQPROTESORERIA.FACULTAD FAC ON ALU.CODIGO_FACULTAD = FAC.CODIGO \n" +
			" WHERE DOC.EST = 1 AND trim(ALU.CODIGO_MATRICULA) like trim(#{codMatricula}||'%') and rownum<10")
	@Results(value={
	@Result(javaType=AlumnoSUMModel.class),
	@Result(property="codigoMatricula",column="codigo_matricula"),
	@Result(property=PROP_NOMBFACULTAD,column=COL_NOMBFACULTAD),
	@Result(property=PROP_NUMDOC,column=COL_NUMDOC),
	@Result(property=PROP_EMAIL,column=COL_EMAIL),
	@Result(property=PROP_NOMCOMPLETO,column=COL_NOMCOMPLETO),
	@Result(property=PROP_DIRECCION,column=COL_DIRECCION),
	@Result(property=PROP_NOMBREDOC,column=COL_NOMBREDOC),
	@Result(property=PROP_ABREV,column=COL_ABREV)})
	public List<AlumnoSUMModel> getAlumnoXcodigoMatricula(@Param("codMatricula") String codMatricula);
	
	
	@Select(value=	"SELECT \n" +
			" ALU.CODIGO_MATRICULA AS codigo_matricula, \n" +
			" FAC.UD_DSC AS nombFacultad, \n" +
			" ALU.NUMERO_DOCUMENTO AS numDoc, \n" +
			" ALU.EMAIL AS email, \n" +
			" ALU.NOM_COMP AS nomCompleto, \n" +
			" ALU.DIRECCION AS direccion, \n" +
			" DOC.NOMBRE AS nombreDoc, \n" +
			" DOC.ABREV AS abrev \n" +
			" FROM WEBQPROTESORERIA.ALUMNOSUM ALU \n" +
			" INNER JOIN WEBQPROTESORERIA.DOCUMENTO_IDENTIDAD DOC ON ALU.TIPO_DOCUMENTO = DOC.ID_DOC_IDENT \n" +
			" INNER JOIN WEBQPROTESORERIA.FACULTAD FAC ON ALU.CODIGO_FACULTAD = FAC.CODIGO \n" +
			" WHERE DOC.EST = 1 AND ALU.TIPO_DOCUMENTO = #{tipoDocumen} AND trim(ALU.NUMERO_DOCUMENTO) like trim(#{numDocumen}||'%') AND rownum<10")
	@Results(value={
	@Result(javaType=AlumnoSUMModel.class),
	@Result(property="codigoMatricula",column="codigo_matricula"),
	@Result(property=PROP_NOMBFACULTAD,column=COL_NOMBFACULTAD),
	@Result(property=PROP_NUMDOC,column=COL_NUMDOC),
	@Result(property=PROP_EMAIL,column=COL_EMAIL),
	@Result(property=PROP_NOMCOMPLETO,column=COL_NOMCOMPLETO),
	@Result(property=PROP_DIRECCION,column=COL_DIRECCION),
	@Result(property=PROP_NOMBREDOC,column=COL_NOMBREDOC),
	@Result(property=PROP_ABREV,column=COL_ABREV)})
	public List<AlumnoSUMModel> getAlumnoXtipoDocNumDoc(@Param("tipoDocumen") Integer tipoDocumen,@Param("numDocumen") String numDocumen);
	
}
