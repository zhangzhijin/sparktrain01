package scala_spark_ml
 
import org.apache.spark.SparkContext;
import org.apache.spark.sql.SQLContext;
import org.apache.spark._;

 
import org.apache.spark.mllib.util.MLUtils;

import org.apache.spark.rdd.RDD
 
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types._;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql._;
import org.apache.spark.ml.feature.Tokenizer;
import org.apache.spark.ml.feature.HashingTF;
import org.apache.spark.ml.feature.IDF;
import org.apache.spark.ml.feature._;
import org.apache.spark.ml.linalg.{Vector, Vectors}
import org.apache.spark.ml._;
import org.apache.spark.ml.classification.NaiveBayes;
import org.apache.spark.ml.classification._;
import java.util._;


object ChiSqSelector01 { 
  
  case class RawDataRecord(category: String, text: String);
  
   def main(args:Array[String]):Unit={
     
     var conf=new SparkConf().setMaster("local[4]").setAppName("CountVectorize01");
     var sc=new SparkContext(conf);
     sc.setLogLevel("INFO")
     var sqlsc=new SQLContext(sc);
     
     val sparkSession = SparkSession
  .builder()
  .config(conf)
  .getOrCreate()
  
   import  sparkSession.implicits._
    
     var file="D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\ml\\CountVectorize01.txt"
 val dataFrame = sparkSession.sparkContext.textFile(file).map(_.split(",")).map(f=>RawDataRecord(f(0),f(1))).toDF() ;
 
val df = sparkSession.createDataFrame(Seq(
       (1, Vectors.dense(0.0, 0.0, 18.0, 1.0), 1),
     (2, Vectors.dense(0.0, 1.0, 12.0, 0.0), 0),
       (3, Vectors.dense(1.0, 0.0, 15.0, 0.1), 0)
      )).toDF("id", "features", "label")
      
     val selector = new ChiSqSelector().
       setNumTopFeatures(2).
       setFeaturesCol("features").
     setLabelCol("label").
       setOutputCol("selected-feature")
       
        val selector_model = selector.fit(df)
        
       val result = selector_model.transform(df)
       
       result.show(5,false)
}
}