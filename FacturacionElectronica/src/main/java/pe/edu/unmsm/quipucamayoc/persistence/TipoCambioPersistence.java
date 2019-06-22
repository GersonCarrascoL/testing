package pe.edu.unmsm.quipucamayoc.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import pe.edu.unmsm.quipucamayoc.model.TipoCambioModel;


public interface TipoCambioPersistence {
	
	@Select(value = " select * from \n" +
					" ( \n" +
					"   SELECT \n" +
					"   TIPCAFEC AS fecha_tc, MONTOC AS factor_tccompra, MONTOV AS factor_tcventa \n" +
					"   FROM WEBQPROTESORERIA.TIPO_CAMBIO \n" +
					"   where  TIPCAFEC <= sysdate \n" +
					"   order by FECHA_TC desc \n" +
					" ) where rownum = 1")
	@Results(value = {@Result(javaType = TipoCambioModel.class),
	    @Result(property = "fechaTc", column = "fecha_tc"),
	    @Result(property = "factorTccompra", column = "factor_tccompra"),
	    @Result(property = "factorTcventa", column = "factor_tcventa")
	})
	public List<TipoCambioModel> getTipoCambioActual();

}
