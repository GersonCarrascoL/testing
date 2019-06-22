package pe.edu.unmsm.quipucamayoc.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import pe.edu.unmsm.quipucamayoc.model.TipoConceptoPagoModel;

public interface TipoConceptoPagoPersistence {
	
	@Select(value = "SELECT ID_TIPO_CPAGO, COD_TIPO_CPAGO, NOMBRE, DESCR, EST \n" +
					" FROM \n" +
					" WEBQPROTESORERIA.TIPO_CONCEPTO_PAGO \n " +
					" WHERE EST = 1 \n " +
			 		" ORDER BY NOMBRE ASC")
	@Results(value = {@Result(javaType = TipoConceptoPagoModel.class),
	    @Result(property = "idTipoCpago", column = "ID_TIPO_CPAGO"),
	    @Result(property = "codTipoCpago", column = "COD_TIPO_CPAGO"),
	    @Result(property = "nombre", column = "NOMBRE"),
	    @Result(property = "descr", column = "DESCR"),
	    @Result(property = "est", column = "EST")
	})
	public List<TipoConceptoPagoModel> listarTiposConceptoPago();

}
