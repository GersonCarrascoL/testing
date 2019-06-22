package pe.edu.unmsm.quipucamayoc.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import pe.edu.unmsm.quipucamayoc.model.MonedaModel;

public interface MonedaPersistence {
	
	@Select(value = "SELECT MON.ID_MONEDA AS idMoneda, \n " +
			 " MON.MONEDA AS monedaDesc, \n " +
			 " MON.SIMB AS monedaSimb, \n " +
			 " MON.EST AS estado \n " +
			 " FROM WEBQPROTESORERIA.MONEDA MON \n " +
			 " WHERE MON.EST = 1 \n " +
			 " ORDER BY MON.ID_MONEDA ASC")
	@Results(value = {@Result(javaType = MonedaModel.class),
	    @Result(property = "idMoneda", column = "idMoneda"),
	    @Result(property = "monedaDesc", column = "monedaDesc"),
	    @Result(property = "monedaSimb", column = "monedaSimb"),
	    @Result(property = "estado", column = "estado")
	})
	public List<MonedaModel> listaMonedas();

}
