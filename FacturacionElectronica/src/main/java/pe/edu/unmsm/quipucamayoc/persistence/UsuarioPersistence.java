package pe.edu.unmsm.quipucamayoc.persistence;

import java.util.List;
import org.apache.ibatis.annotations.*;
import pe.edu.unmsm.quipucamayoc.model.UsuarioModel;

public interface UsuarioPersistence {

    @Select(value = " SELECT   TB_HIST.T_MAIL AS USERNAME,\n"+
            " TB_HIST.UD_ID AS ID_DEPENDENCIA,\n"+
            " TB_DEP.UD_COD AS COD_DEPENDENCIA,\n"+
            " TB_DEP2.UD_DSC AS DSC_DEPENDENCIAPAD,\n"+
            " TB_DEP.UD_DSC AS DSC_DEPENDENCIA,\n"+
            " TB_DEP.UNIDEPCALDEP AS ID_UNIDAD_ADMINISTRATIVA,\n"+
            " TB_PER.PERF_DESC AS PERFIL,\n"+
            " ERP_USU.USU_NOM||' '||ERP_USU.USU_APE  AS NOMBRE,\n"+
            " TB_DEP2.UD_ID AS ID_DEP_PADRE,\n"+
            " TB_DEP3.UD_DSC AS DSC_UNIDAD_ADMINISTRATIVA\n"+
            " FROM QPRODATAQUIPU.TB_HIST_USU_PERF TB_HIST \n"+
            " INNER JOIN (SELECT UD_ID, decode(UD_COD, '00', 'UNMSM', UD_COD ) UD_COD, DECODE(UNINIV2, NULL, 10000, UNINIV2  ) UNINIV2 , UD_DSC, UNIDEPCALDEP FROM QPRODATAQUIPU.UNI_DEP) TB_DEP  ON (TB_DEP.UD_ID=TB_HIST.UD_ID)\n"+
            " INNER JOIN QPRODATAQUIPU.UNI_DEP TB_DEP2  ON (TB_DEP.UNINIV2 = TB_DEP2.UD_ID)\n"+
            " LEFT JOIN QPRODATAQUIPU.UNI_DEP TB_DEP3 ON (TB_DEP.UNIDEPCALDEP = TB_DEP3.UD_ID)\n"+
            " INNER JOIN QPRODATAQUIPU.TB_PERFIL TB_PER  ON (TB_PER.PERF_COD=TB_HIST.PERF_COD)\n"+
            " inner join QPRODATAQUIPU.TB_ERP_USUARIO ERP_USU ON (ERP_USU.C_USUID =TB_HIST.C_USUID  )\n"+
            " WHERE (TB_HIST.MODCOD=3 AND TB_HIST.EST=1 AND TB_HIST.T_MAIL LIKE #{usuario})")
    @Results(value = {
            @Result(javaType = UsuarioModel.class),
            @Result(property = "userName",column = "USERNAME"),
            @Result(property = "perfil",column = "PERFIL"),
            @Result(property = "idDependencia",column = "ID_DEPENDENCIA"),
            @Result(property = "codDependencia",column = "COD_DEPENDENCIA"),
            @Result(property = "dscDependencian2",column = "DSC_DEPENDENCIAPAD"),
            @Result(property = "dscDependencia",column = "DSC_DEPENDENCIA"),
            @Result(property = "idUnidadAdministrativa",column = "ID_UNIDAD_ADMINISTRATIVA"),
            @Result(property = "dscUnidadAdministrativa",column = "DSC_UNIDAD_ADMINISTRATIVA"),
            @Result(property = "nombre",column = "NOMBRE"),
            @Result(property = "idDepPadre",column = "ID_DEP_PADRE")
    })
    public UsuarioModel datosDeUsuario(@Param("usuario") String userName);

    @Select(value = "SELECT\n" +
            "        TRE.ROL ROL\n" +
            "    FROM QPRODATAQUIPU.TB_ERP_USUARIO USU\n" +
            "    INNER JOIN QPRODATAQUIPU.TB_HIST_USU_PERF TH\n" +
            "    ON USU.C_USUID = TH.C_USUID\n" +
            "    INNER JOIN QPRODATAQUIPU.TB_PERFIL TP\n" +
            "    ON TP.PERF_COD = TH.PERF_COD\n" +
            "    INNER JOIN QPRODATAQUIPU.ROL_PERFIL RP\n" +
            "    ON TP.PERF_COD = RP.PERF_COD\n" +
            "    INNER JOIN QPRODATAQUIPU.TB_ROL_ERP TRE\n" +
            "    ON TRE.ROL_ID = RP.ROL_ID\n" +
            "    AND TRE.MODU_COD = TH.MODCOD\n" +
            "    WHERE TH.EST = '1'\n" +
            "    AND TH.MODCOD = 3\n" +
            "    AND TH.T_MAIL = #{username}")
    List<String> obtenerRoles(@Param("username") String username);

}
