package pe.edu.unmsm.quipucamayoc.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import pe.edu.unmsm.quipucamayoc.model.CatalogoCodigoProducto;

public interface CatalogoCodigoProductoPersistence {
	
	@Select(value = " SELECT \n " +
			" CCP.CODIGO AS codigo, \n " +
			" CCP.NOMBRE AS nombre, \n " +
			" CCP.PARTIDA_REFERENCIAL AS partida_referencial \n " +
			" FROM \n " +
			" WEBQPROTESORERIA.CATALOGO_CODIGO_PRODUCTO CCP \n " +
			" order by decode( CCP.APARECE_AL_INICIO, 1 , '  ' , CCP.NOMBRE )")
	@Results(value = {@Result(javaType = CatalogoCodigoProducto.class),
	@Result(property = "codigo", column = "codigo"),
	@Result(property = "nombre", column = "nombre"),
	@Result(property = "partidaReferencial", column = "partida_referencial")
	})
	public List<CatalogoCodigoProducto> listarCatalogoCodigoProductos();
	
}
