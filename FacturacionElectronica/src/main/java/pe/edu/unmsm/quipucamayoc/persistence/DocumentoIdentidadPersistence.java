package pe.edu.unmsm.quipucamayoc.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import pe.edu.unmsm.quipucamayoc.model.DocumentoIdentidadModel;

public interface DocumentoIdentidadPersistence {
	
	@Select(value=	"SELECT \n" +
					" DOCU.ID_DOC_IDENT AS idDoc, \n" +
					" DOCU.NOMBRE AS name, \n" +
					" DOCU.ABREV AS abrev, \n" +
					" DOCU.EST AS estado \n" +
					" FROM WEBQPROTESORERIA.DOCUMENTO_IDENTIDAD DOCU \n" +
					" WHERE DOCU.ID_DOC_IDENT IN (1, 4, 7, 9) \n" +
					" AND DOCU.EST = 1 \n" +
					" ORDER BY \n" +
					" DOCU.ID_DOC_IDENT ASC \n")
	@Results(value={
		@Result(javaType=DocumentoIdentidadModel.class),
		@Result(property="idDoc",column="idDoc"),
		@Result(property="name",column="name"),
		@Result(property="abrev",column="abrev"),
		@Result(property="estado",column="estado")})
	public List<DocumentoIdentidadModel> listarTiposDocIdentidad();

}
