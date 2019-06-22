package pe.edu.unmsm.quipucamayoc.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import pe.edu.unmsm.quipucamayoc.model.PerfilModel;

public interface PerfilServicePersistence {
	
	@Select(value=	"SELECT \n" +
			" tp.PERF_DESC AS pDescripcion, \n" +
			" tp.PERF_EST AS pEstado, \n" +
			" tp.PERF_COD AS pCodigo, \n" +
			" tp.MODU_COD AS pModulo, \n" +
			" tp.DESC_GENERAL AS desGeneral, \n" +
			" tp.TIPO_PERFIL AS pTipo \n" +
			" FROM QPRODATAQUIPU.TB_PERFIL tp	 \n" +
			" where tp.PERF_COD IN (603,604,606) \n" +
			" AND tp.PERF_EST = 1 \n" +
			" ORDER BY \n" +
			" tp.DESC_GENERAL ASC \n")
	@Results(value={
	@Result(javaType=PerfilModel.class),
	@Result(property="descripcionPerfil",column="pDescripcion"),
	@Result(property="estadoPefil",column="pEstado"),
	@Result(property="codigoPerfil",column="pCodigo"),
	@Result(property="codigoModulo",column="pModulo"),
	@Result(property="descripcionGeneral",column="desGeneral"),
	@Result(property="tipoPerfil",column="pTipo")})
	public List<PerfilModel> listarTiposPerfil();
}
