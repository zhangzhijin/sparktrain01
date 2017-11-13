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


object MinMaxScaler01 { 
  
  case class RawDataRecord(category: String, text: String);
  
   def main(args:Array[String]):Unit={
     
     var conf=new SparkConf().setMaster("local[4]").setAppName("MinMaxScaler01");
     var sc=new SparkContext(conf);
     sc.setLogLevel("INFO")
 
     val sparkSession = SparkSession
  .builder()
  .config(conf)
  .getOrCreate()
  
   import  sparkSession.implicits._
    
     val dataFrame = sparkSession.createDataFrame(Seq(
 (0, Vectors.dense(1.0, 0.1, -1.0)),
  (1, Vectors.dense(2.0, 1.1, 1.0)),
  (2, Vectors.dense(3.0, 10, 1.0))
)).toDF("id", "features")

dataFrame.show();
     
val scaler = new MinMaxScaler()
  .setInputCol("features")
  .setOutputCol("scaledFeatures")

// Compute summary statistics and generate MinMaxScalerModel
val scalerModel = scaler.fit(dataFrame)

// rescale each feature to range [min, max].
val scaledData = scalerModel.transform(dataFrame)
println(s"Features scaled to range: [${scaler.getMin}, ${scaler.getMax}]")
scaledData.show(false)
}
}