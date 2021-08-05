package com.mycompany.myapp.domain;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.converters.DatabaseSaver;
import weka.experiment.InstanceQuery;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AddClassification;

public class naiveBayesDiagnosis {
    public static String getPrediction() throws Exception {


        DataSource source = new DataSource("src/main/java/com/mycompany/myapp/domain/data/PacientesArff_Bayes.arff");
        Instances train = source.getDataSet();
        train.setClassIndex(30); // Setting Diagnosis
        //build model
        NaiveBayes model=new NaiveBayes();
        model.buildClassifier(train);
        //System.out.println(model);

        try {

            InstanceQuery query = new InstanceQuery();
            query.setDatabaseURL("jdbc:mariadb://localhost:3307/jhipster13");
            query.setUsername("root");
            query.setPassword("Picholudo2202");
            query.setQuery("SELECT sexo, estudios, edad,Digit_Span_Forward,Digit_Span_Backward, \n"+
                "TMT_A,TMT_B,Verbal_fluency_P,Verbal_fluency_animales,Rey_Osterrieth_Memoria, \n"+
                "Rey_Osterrieth_Copia,BNT,V3,V8,V9,V10,V11,V25,TBA_orden_D,TBA_bimanuales,\n"+
                "TBA_comprensión,Hanoi_tres_piezas,Hanoi_cuatro_piezas,FBI,\n"+
                "Reloj_Orden,Reloj_Copia,BNT_Abreviado,\n"+
                "GDS,MMSE,FAB,sospecha_clinica_sugerida FROM paciente P INNER JOIN (SELECT fecha_evaluacion, paciente_id, informe_id,Digit_Span_Forward,Digit_Span_Backward,\n"+
                "TMT_A,TMT_B,Verbal_fluency_P,Verbal_fluency_animales,Rey_Osterrieth_Memoria,\n"+
                "Rey_Osterrieth_Copia,BNT,V3,V8,V9,V10,V11,V25,TBA_orden_D,TBA_bimanuales,\n"+
                "TBA_comprensión,Hanoi_tres_piezas,Hanoi_cuatro_piezas,FBI,\n"+
                "Reloj_Orden,Reloj_Copia,BNT_Abreviado,\n"+
                "GDS,MMSE,FAB, sospecha_clinica_sugerida FROM informe T\n"+
                "INNER JOIN (SELECT\n"+
                "informe_id,id,\n"+
                "MAX(CASE WHEN prueba_id = 1 THEN pd ELSE NULL END) AS Digit_Span_Forward,\n"+
                "MAX(CASE WHEN prueba_id = 2 THEN pd ELSE NULL END) AS Digit_Span_Backward,\n"+
                "MAX(CASE WHEN prueba_id = 3 THEN pd ELSE NULL END) AS TMT_A,\n"+
                "MAX(CASE WHEN prueba_id = 4 THEN pd ELSE NULL END) AS TMT_B,\n"+
                "MAX(CASE WHEN prueba_id = 5 THEN pd ELSE NULL END) AS Verbal_fluency_P,\n"+
                "MAX(CASE WHEN prueba_id = 6 THEN pd ELSE NULL END) AS Verbal_fluency_animales,\n"+
                "MAX(CASE WHEN prueba_id = 7 THEN pd ELSE NULL END) AS Rey_Osterrieth_Memoria,\n"+
                "MAX(CASE WHEN prueba_id = 8 THEN pd ELSE NULL END) AS Rey_Osterrieth_Copia,\n"+
                "MAX(CASE WHEN prueba_id = 9 THEN pd ELSE NULL END) AS BNT,\n"+
                "MAX(CASE WHEN prueba_id = 10 THEN pd ELSE NULL END) AS V3,\n"+
                "MAX(CASE WHEN prueba_id = 11 THEN pd ELSE NULL END) AS V8,\n"+
                "MAX(CASE WHEN prueba_id = 12 THEN pd ELSE NULL END) AS V9,\n"+
                "MAX(CASE WHEN prueba_id = 13 THEN pd ELSE NULL END) AS V10,\n"+
                "MAX(CASE WHEN prueba_id = 14 THEN pd ELSE NULL END) AS V11,\n"+
                " MAX(CASE WHEN prueba_id = 15 THEN pd ELSE NULL END) AS V25,\n"+
                "MAX(CASE WHEN prueba_id = 16 THEN pd ELSE NULL END) AS TBA_orden_D,\n"+
                "MAX(CASE WHEN prueba_id = 17 THEN pd ELSE NULL END) AS TBA_bimanuales,\n"+
                "MAX(CASE WHEN prueba_id = 18 THEN pd ELSE NULL END) AS TBA_comprensión,\n"+
                "MAX(CASE WHEN prueba_id = 19 THEN pd ELSE NULL END) AS Hanoi_tres_piezas,\n"+
                "MAX(CASE WHEN prueba_id = 20 THEN pd ELSE NULL END) AS Hanoi_cuatro_piezas,\n"+
                "MAX(CASE WHEN prueba_id = 21 THEN pd ELSE NULL END) AS FBI,\n"+
                "MAX(CASE WHEN prueba_id = 22 THEN pd ELSE NULL END) AS Reloj_Orden,\n"+
                "MAX(CASE WHEN prueba_id = 23 THEN pd ELSE NULL END) AS Reloj_Copia,\n"+
                "MAX(CASE WHEN prueba_id = 24 THEN pd ELSE NULL END) AS BNT_Abreviado,\n"+
                "MAX(CASE WHEN prueba_id = 25 THEN pd ELSE NULL END) AS GDS,\n"+
                "MAX(CASE WHEN prueba_id = 26 THEN pd ELSE NULL END) AS MMSE,\n"+
                "MAX(CASE WHEN prueba_id = 28 THEN pd ELSE NULL END) AS FAB\n"+
                "FROM \n"+
                "resultado_prueba\n"+
                "GROUP BY informe_id) A ON A.informe_id = T.id) D ON D.paciente_id = P.id");
            Instances test = query.retrieveInstances();


            //Instances test = train;
            test.setClassIndex(30);
            System.out.println(test);
            Evaluation eval = new Evaluation(test);
            System.out.println(eval);

            eval.evaluateModel(model, test);
            // COMPARING TEST NAIVES BAYES WITH REAL DATA
            for (int k = 0; k < test.numInstances();++k){
                Instance instance = test.instance(k);
                double actual = instance.classValue();
                double prediction = eval.evaluateModelOnce(model, instance);
                //System.out.printf("%2d.%4.0f%4.0f", k, actual, prediction);
                //System.out.println(prediction != actual? "  Falso Positivo": "");

                AddClassification addClass = new AddClassification();
                addClass.setClassifier(model);
                addClass.setRemoveOldClass(true);
                addClass.setOutputClassification(true);
                addClass.setInputFormat(train);
                Filter.useFilter(train, addClass);
                Instances newtestdata = Filter.useFilter(test, addClass);
                ArffSaver saver = new ArffSaver();
                saver.setInstances(newtestdata);
                saver.setFile(new File("src/main/java/com/mycompany/myapp/domain/data/Pacientes_nuevo.arff"));
                saver.writeBatch();

                DatabaseSaver save = new DatabaseSaver();
                save.setUrl("jdbc:mariadb://localhost:3307/jhipster13");
                save.setUser("root");
                save.setPassword("Picholudo2202");
                save.setInstances(newtestdata);
                save.setRelationForTableName(false);
                save.setTruncate(true);
                save.setTableName("naiveBayes");
                save.connectToDatabase();
                save.writeBatch();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        String classification = null;
        //System.out.println("Prediction succeeded!");
            try {
                String url = "jdbc:mariadb://localhost:3307/jhipster13";
                Connection conn;
                conn = DriverManager.getConnection(url,"root","Picholudo2202");
                Statement stmt = conn.createStatement();
                ResultSet rs;

                stmt.executeQuery("DROP TABLE IF EXISTS temp;");
                stmt.executeQuery("CREATE TEMPORARY TABLE temp (row int, classification VARCHAR(80));");
                stmt.executeQuery("INSERT INTO temp (SELECT @row := @row + 1 as row, n.classification FROM naivebayes n, (SELECT @row :=0) r);");
                rs = stmt.executeQuery("SELECT classification FROM temp WHERE row=(SELECT MAX(row) FROM temp);");
                 while ( rs.next() ) {
                    classification = rs.getString("classification");
                    System.out.println(classification);
                }
                //String classification = rs.getString("classification");
                conn.close();
            }catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return classification;
        }











    public static void main(String[] args) throws Exception {
        DataSource source = new DataSource("src/main/java/com/mycompany/myapp/domain/data/PacientesArff_Bayes.arff");
        Instances train = source.getDataSet();
        train.setClassIndex(30); // Setting Diagnosis
        //build model
        NaiveBayes model=new NaiveBayes();
        model.buildClassifier(train);
        //System.out.println(model);

        try {

            InstanceQuery query = new InstanceQuery();
            query.setDatabaseURL("jdbc:mariadb://localhost:3307/jhipster13");
            query.setUsername("root");
            query.setPassword("Picholudo2202");
            query.setQuery("SELECT sexo, estudios, edad,Digit_Span_Forward,Digit_Span_Backward, \n"+
            "TMT_A,TMT_B,Verbal_fluency_P,Verbal_fluency_animales,Rey_Osterrieth_Memoria, \n"+
            "Rey_Osterrieth_Copia,BNT,V3,V8,V9,V10,V11,V25,TBA_orden_D,TBA_bimanuales,\n"+
            "TBA_comprensión,Hanoi_tres_piezas,Hanoi_cuatro_piezas,FBI,\n"+
            "Reloj_Orden,Reloj_Copia,BNT_Abreviado,\n"+
            "GDS,MMSE,FAB,sospecha_clinica_sugerida FROM paciente P INNER JOIN (SELECT fecha_evaluacion, paciente_id, informe_id,Digit_Span_Forward,Digit_Span_Backward,\n"+
            "TMT_A,TMT_B,Verbal_fluency_P,Verbal_fluency_animales,Rey_Osterrieth_Memoria,\n"+
            "Rey_Osterrieth_Copia,BNT,V3,V8,V9,V10,V11,V25,TBA_orden_D,TBA_bimanuales,\n"+
            "TBA_comprensión,Hanoi_tres_piezas,Hanoi_cuatro_piezas,FBI,\n"+
        "Reloj_Orden,Reloj_Copia,BNT_Abreviado,\n"+
            "GDS,MMSE,FAB, sospecha_clinica_sugerida FROM informe T\n"+
            "INNER JOIN (SELECT\n"+
            "informe_id,id,\n"+
                "MAX(CASE WHEN prueba_id = 1 THEN pd ELSE NULL END) AS Digit_Span_Forward,\n"+
                 "MAX(CASE WHEN prueba_id = 2 THEN pd ELSE NULL END) AS Digit_Span_Backward,\n"+
                "MAX(CASE WHEN prueba_id = 3 THEN pd ELSE NULL END) AS TMT_A,\n"+
                 "MAX(CASE WHEN prueba_id = 4 THEN pd ELSE NULL END) AS TMT_B,\n"+
                "MAX(CASE WHEN prueba_id = 5 THEN pd ELSE NULL END) AS Verbal_fluency_P,\n"+
                 "MAX(CASE WHEN prueba_id = 6 THEN pd ELSE NULL END) AS Verbal_fluency_animales,\n"+
               "MAX(CASE WHEN prueba_id = 7 THEN pd ELSE NULL END) AS Rey_Osterrieth_Memoria,\n"+
                 "MAX(CASE WHEN prueba_id = 8 THEN pd ELSE NULL END) AS Rey_Osterrieth_Copia,\n"+
            "MAX(CASE WHEN prueba_id = 9 THEN pd ELSE NULL END) AS BNT,\n"+
                 "MAX(CASE WHEN prueba_id = 10 THEN pd ELSE NULL END) AS V3,\n"+
                "MAX(CASE WHEN prueba_id = 11 THEN pd ELSE NULL END) AS V8,\n"+
                 "MAX(CASE WHEN prueba_id = 12 THEN pd ELSE NULL END) AS V9,\n"+
                "MAX(CASE WHEN prueba_id = 13 THEN pd ELSE NULL END) AS V10,\n"+
                 "MAX(CASE WHEN prueba_id = 14 THEN pd ELSE NULL END) AS V11,\n"+
               " MAX(CASE WHEN prueba_id = 15 THEN pd ELSE NULL END) AS V25,\n"+
                 "MAX(CASE WHEN prueba_id = 16 THEN pd ELSE NULL END) AS TBA_orden_D,\n"+
                "MAX(CASE WHEN prueba_id = 17 THEN pd ELSE NULL END) AS TBA_bimanuales,\n"+
                 "MAX(CASE WHEN prueba_id = 18 THEN pd ELSE NULL END) AS TBA_comprensión,\n"+
            "MAX(CASE WHEN prueba_id = 19 THEN pd ELSE NULL END) AS Hanoi_tres_piezas,\n"+
                 "MAX(CASE WHEN prueba_id = 20 THEN pd ELSE NULL END) AS Hanoi_cuatro_piezas,\n"+
                "MAX(CASE WHEN prueba_id = 21 THEN pd ELSE NULL END) AS FBI,\n"+
                 "MAX(CASE WHEN prueba_id = 22 THEN pd ELSE NULL END) AS Reloj_Orden,\n"+
            "MAX(CASE WHEN prueba_id = 23 THEN pd ELSE NULL END) AS Reloj_Copia,\n"+
                 "MAX(CASE WHEN prueba_id = 24 THEN pd ELSE NULL END) AS BNT_Abreviado,\n"+
                "MAX(CASE WHEN prueba_id = 25 THEN pd ELSE NULL END) AS GDS,\n"+
                 "MAX(CASE WHEN prueba_id = 26 THEN pd ELSE NULL END) AS MMSE,\n"+
                 "MAX(CASE WHEN prueba_id = 28 THEN pd ELSE NULL END) AS FAB\n"+
            "FROM \n"+
                "resultado_prueba\n"+
                "GROUP BY informe_id) A ON A.informe_id = T.id) D ON D.paciente_id = P.id");
                Instances test = query.retrieveInstances();


                //Instances test = train;
                test.setClassIndex(30);
            System.out.println(test);
            Evaluation eval = new Evaluation(test);
            System.out.println(eval);

       eval.evaluateModel(model, test);
        // COMPARING TEST NAIVES BAYES WITH REAL DATA
        for (int k = 0; k < test.numInstances();++k){
            Instance instance = test.instance(k);
            double actual = instance.classValue();
            double prediction = eval.evaluateModelOnce(model, instance);
            //System.out.printf("%2d.%4.0f%4.0f", k, actual, prediction);
            //System.out.println(prediction != actual? "  Falso Positivo": "");

            AddClassification addClass = new AddClassification();
            addClass.setClassifier(model);
            addClass.setRemoveOldClass(true);
            addClass.setOutputClassification(true);
            addClass.setInputFormat(train);
            Filter.useFilter(train, addClass);
            Instances newtestdata = Filter.useFilter(test, addClass);
            ArffSaver saver = new ArffSaver();
            saver.setInstances(newtestdata);
            saver.setFile(new File("src/main/java/com/mycompany/myapp/domain/data/Pacientes_nuevo.arff"));
            saver.writeBatch();

            DatabaseSaver save = new DatabaseSaver();
            save.setUrl("jdbc:mariadb://localhost:3307/jhipster13");
            save.setUser("root");
            save.setPassword("Picholudo2202");
            save.setInstances(newtestdata);
            save.setRelationForTableName(false);
            save.setTruncate(true);
            save.setTableName("naiveBayes");
            save.connectToDatabase();
            save.writeBatch();

        }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //CALLING METHOD FROM NAIVEBAYES SERVICE
       // naiveBayesDiagnosis nb = new naiveBayesDiagnosis();
      //  naiveBayesDiagnosis.getPrediction();

    }
}

        // DRAWING ROC CURVE

        /*ThresholdCurve tc = new ThresholdCurve();
        int classIndex = 0;
        Instances result = tc.getCurve(eval.predictions(),classIndex);

        ThresholdVisualizePanel vmc = new ThresholdVisualizePanel();

        vmc.setROCString("(Área bajo la curva ROC = " + Utils.doubleToString(tc.getROCArea(result),5) + ")");

        vmc.setName(result.relationName());
        PlotData2D tempd = new PlotData2D(result);
        tempd.setPlotName(result.relationName());
        tempd.addInstanceNumberAttribute();

        boolean[] cp = new boolean [result.numInstances()];
        for (int k = 1; k < cp.length; k++) {
            cp[k] = true;
        }

        tempd.setConnectPoints(cp);
        vmc.addPlot(tempd);

        String plotName = vmc.getName();
        final javax.swing.JFrame jf = new javax.swing.JFrame("Clasificación Visualizada: "+plotName);

        jf.setSize(500,400);
        jf.getContentPane().setLayout(new BorderLayout());
        jf.getContentPane().add(vmc, BorderLayout.CENTER);
        jf.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                jf.dispose();
                }
        });
        jf.setVisible(false);
        //filter Train data and output the prediction to test file in the future


    }

    }*/




