package pe.edu.unmsm.quipucamayoc.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import pe.edu.unmsm.quipucamayoc.model.ListaServiciosHistorialModel;

public interface ListaServiciosHistorialPersistence {
	
	@Select(value = "SELECT " +
			" LPH.ID_HIST AS id_hist, " +
			" LPH.UD_ID AS ud_id, " +
			" LPH.COD_ITEM AS cod_item, " +
			" LPH.PRECIO AS precio, " +
			" LPH.MONEDA AS moneda, " +
			" MON.SIMB AS monedaSimb, " +
			" LPH.FECHA AS FECHA_DATE, " +
			" TO_char(LPH.FECHA, 'DD/MM/YYYY HH24:MI:SS') AS fecha, " +
			" LPH.USUARIO AS usuario, \n " +
			" LPH.UNIDAD_MEDIDA AS UNIMEDCOD, \n " +
			" UNIMED.UNIMEDDES AS UNIMEDDES, \n" +
			" LPH.NUM_RESOLUCION AS numResolucion, \n" +
			" TRS.NAME_TIPO_RESOL AS nameTipoResolucion \n" +
			" FROM \n " +
			" WEBQPROTESORERIA.LISTA_SERVICIOS_HISTORIAL LPH \n " +
			" INNER JOIN WEBQPROTESORERIA.MONEDA MON ON MON.ID_MONEDA = LPH.MONEDA \n " +
			" LEFT JOIN WEBQPROTESORERIA.UNI_MED_ART UNIMED ON UNIMED.UNIMEDCOD = LPH.UNIDAD_MEDIDA \n" +
			" LEFT JOIN WEBQPROTESORERIA.TIPO_RESOLUCION TRS ON LPH.TIPO_RESOLUCION = TRS.COD_TIPO_RESOL \n" +
			" WHERE \n " +
			" ud_id = #{ud_id} AND	cod_item like #{cod_item} \n " +
			" ORDER BY FECHA_DATE DESC")
	@Results(value = {
	@Result(javaType = ListaServiciosHistorialModel.class),
	@Result(property = "idHist",column = "id_hist"),
	@Result(property = "udId",column = "ud_id"),
	@Result(property = "codItem",column = "cod_item"),
	@Result(property = "precio",column = "precio"),
	@Result(property = "moneda",column = "moneda"),
	@Result(property = "monedaSimb",column = "monedaSimb"),
	@Result(property = "fecha",column = "fecha"),
	@Result(property = "usuario",column = "usuario"),
	@Result(property = "unidadMedidaCod",column = "UNIMEDCOD"),
	@Result(property = "unidadMedidaDes",column = "UNIMEDDES"),
	@Result(property = "numResolucion",column = "numResolucion"),
	@Result(property = "nameTipoResolucion",column = "nameTipoResolucion")
	})
	public List<ListaServiciosHistorialModel> getListaServicioHistorial(@Param("ud_id") Integer udId, @Param("cod_item") String codItem);

}
