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


object OneHotEncoder01 { 
  
  case class RawDataRecord(category: String, text: String);
  
   def main(args:Array[String]):Unit={
     
     var conf=new SparkConf().setMaster("local[4]").setAppName("OneHotEncoder01");
     var sc=new SparkContext(conf);
     sc.setLogLevel("INFO")
 
     
     val sparkSession = SparkSession
  .builder()
  .config(conf)
  .getOrCreate()
  
   import  sparkSession.implicits._
    
     var file="D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\ml\\CountVectorize01.txt"

 val df = sparkSession.createDataFrame(Seq(
        (0, "a"),
        (1, "b"),
        (2, "c"),
        (3, "a"),
        (4, "a"),
        (5, "c"),
        (6, "d"),
        (7, "d"),
        (8, "d"),
        (9, "d"),
        (10, "e"),
        (11, "e"),
        (12, "e"),
        (13, "e"),
        (14, "e")
      )).toDF("id", "category")
 

    val indexer = new StringIndexer().
       setInputCol("category").
       setOutputCol("categoryIndex").
         fit(df)
         
         val indexed = indexer.transform(df)
      
       val encoder = new OneHotEncoder().
       setInputCol("categoryIndex").
         setOutputCol("categoryVec")
         
         val encoded = encoder.transform(indexed)
         
         encoded.show()
}
}