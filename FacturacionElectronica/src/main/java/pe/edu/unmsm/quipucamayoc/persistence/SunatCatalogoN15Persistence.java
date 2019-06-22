package pe.edu.unmsm.quipucamayoc.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import pe.edu.unmsm.quipucamayoc.model.SunatCatalogoN15Model;


public interface SunatCatalogoN15Persistence {
	
	String PROP_CODCATALOGO= "codCatalogo";
	
	String COL_CODCATALOGO = "codCatalogo";
	
	@Select(value = "SELECT " +
					" CATN15.COD_CATA AS codCatalogo, " +
					" CATN15.DESCRIPCION AS descr, " +
					" CATN15.LEYENDA AS leyenda, " +
					" CATN15.GRUPO AS grupo " +
					" FROM " +
					" WEBQPROTESORERIA.SUNAT_CATALOGO_N15 CATN15 " +
					" WHERE " +
					" CATN15.GRUPO = 30 " +
					" ORDER BY " +
					" codCatalogo ASC")
	@Results(value = {@Result(javaType = SunatCatalogoN15Model.class),
	    @Result(property = PROP_CODCATALOGO, column = COL_CODCATALOGO),
	    @Result(property = "descr", column = "descr"),
	    @Result(property = "leyenda", column = "leyenda"),
	    @Result(property = "grupo", column = "grupo")
	})
	public List<SunatCatalogoN15Model> listarDataDetracciones();
	
	
	@Select(value = "SELECT " +
			" CATN15.COD_CATA AS codCatalogo " +
			" FROM " +
			" WEBQPROTESORERIA.SUNAT_CATALOGO_N15 CATN15 " +
			" WHERE " +
			" CATN15.GRUPO = 30 " +
			" AND CATN15.USO_GENERAL = 1 ")
	@Results(value = {@Result(javaType = SunatCatalogoN15Model.class),
	@Result(property = PROP_CODCATALOGO, column = COL_CODCATALOGO)
	})
	public List<SunatCatalogoN15Model> getCodGeneralCatalogoDetraccion();

}
