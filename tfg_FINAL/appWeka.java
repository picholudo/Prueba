import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.opencsv.exceptions.CsvValidationException;

import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;
import weka.experiment.InstanceQuery;

public class appWeka {    
    public static void main(String[] args) throws IOException, CsvValidationException {
        Properties properties = new Properties();
        //ConverterUtils.DataSource source = null;
        try {
            properties.load(new FileInputStream(new File("tfg_FINAL/src/main/java\com\mycompany\myapp\domain\config.properties")));
            InstanceQuery query = new InstanceQuery();
            query.setDatabaseURL(properties.getProperty("jdbc"));
            query.setUsername(properties.getProperty("USER"));
            query.setPassword(properties.getProperty("PASS"));
            query.setQuery(" SELECT fecha_evaluacion, paciente_id, informe_id,Digit_Span_Forward,Digit_Span_Backward,\n"+
            "TMT_A,TMT_B,Verbal_fluency_P,Verbal_fluency_animales,Rey_Osterrieth_Memoria,\n"+
            "Rey_Osterrieth_Copia,BNT,V3,V8,V9,V10,V11,V25,TBA_orden_D,TBA_bimanuales,\n"+
            "TBA_comprensión,Hanoi_tres_piezas,Hanoi_cuatro_piezas,FBI,\n"+
            "Reloj_Orden,Reloj_Copia,BNT_Abreviado,\n"+
            "GDS,MMSE,MMSE_2,FAB,FAB_2  FROM informe T\n"+
            "INNER JOIN (SELECT\n"+
            "informe_id,id,\n"+
            "MAX(CASE WHEN prueba_id = 1 THEN pd ELSE '' END) AS Digit_Span_Forward,\n"+
            "MAX(CASE WHEN prueba_id = 2 THEN pd ELSE '' END) AS Digit_Span_Backward,\n"
    +"MAX(CASE WHEN prueba_id = 3 THEN pd ELSE '' END) AS TMT_A,\n"
	 +"MAX(CASE WHEN prueba_id = 4 THEN pd ELSE '' END) AS TMT_B,\n"
    +"MAX(CASE WHEN prueba_id = 5 THEN pd ELSE '' END) AS Verbal_fluency_P,\n"
	 +"MAX(CASE WHEN prueba_id = 6 THEN pd ELSE '' END) AS Verbal_fluency_animales,\n"
    +"MAX(CASE WHEN prueba_id = 7 THEN pd ELSE '' END) AS Rey_Osterrieth_Memoria,\n"
	 +"MAX(CASE WHEN prueba_id = 8 THEN pd ELSE '' END) AS Rey_Osterrieth_Copia,\n"
    +"MAX(CASE WHEN prueba_id = 9 THEN pd ELSE '' END) AS BNT,\n"
	 +"MAX(CASE WHEN prueba_id = 10 THEN pd ELSE '' END) AS V3,\n"
    +"MAX(CASE WHEN prueba_id = 11 THEN pd ELSE '' END) AS V8,\n"
	 +"MAX(CASE WHEN prueba_id = 12 THEN pd ELSE '' END) AS V9,\n"
    +"MAX(CASE WHEN prueba_id = 13 THEN pd ELSE '' END) AS V10,\n"
	 +"MAX(CASE WHEN prueba_id = 14 THEN pd ELSE '' END) AS V11,\n"
    +"MAX(CASE WHEN prueba_id = 15 THEN pd ELSE '' END) AS V25,\n"
	 +"MAX(CASE WHEN prueba_id = 16 THEN pd ELSE '' END) AS TBA_orden_D,\n"
    +"MAX(CASE WHEN prueba_id = 17 THEN pd ELSE '' END) AS TBA_bimanuales,\n" 
	+"MAX(CASE WHEN prueba_id = 18 THEN pd ELSE '' END) AS TBA_comprensión,\n"
    +"MAX(CASE WHEN prueba_id = 19 THEN pd ELSE '' END) AS Hanoi_tres_piezas,\n"
	 +"MAX(CASE WHEN prueba_id = 20 THEN pd ELSE '' END) AS Hanoi_cuatro_piezas,\n"
    +"MAX(CASE WHEN prueba_id = 21 THEN pd ELSE '' END) AS FBI,\n"
	 +"MAX(CASE WHEN prueba_id = 22 THEN pd ELSE '' END) AS Reloj_Orden,\n"
    +"MAX(CASE WHEN prueba_id = 23 THEN pd ELSE '' END) AS Reloj_Copia,\n"
	 +"MAX(CASE WHEN prueba_id = 24 THEN pd ELSE '' END) AS BNT_Abreviado,\n"
    +"MAX(CASE WHEN prueba_id = 25 THEN pd ELSE '' END) AS GDS,\n"
	 +"MAX(CASE WHEN prueba_id = 26 THEN pd ELSE '' END) AS MMSE,\n"
    +"MAX(CASE WHEN prueba_id = 27 THEN pd ELSE '' END) AS MMSE_2,\n"
	 +"MAX(CASE WHEN prueba_id = 28 THEN pd ELSE '' END) AS FAB,\n"
    +"MAX(CASE WHEN prueba_id = 29 THEN pd ELSE '' END) AS FAB_2\n"
+"FROM\n"
	+"resultado_prueba\n"
	+"GROUP BY informe_id) A ON A.informe_id = T.id");
            Instances data = query.retrieveInstances();
            System.out.println(data);
        
        } catch (Exception e) {
            e.printStackTrace();
        }

        
}


}
