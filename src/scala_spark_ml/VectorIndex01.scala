package scala_spark_ml
 
import org.apache.spark.SparkContext;
import org.apache.spark._;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types._;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql._;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.ml.evaluation.RegressionEvaluator;
import org.apache.spark.ml.feature.VectorAssembler;
import org.apache.spark.ml.linalg._;

 import org.apache.spark.ml.feature._;
  

import org.apache.spark.rdd.RDD


object VectorIndex01 { 
  
   
  case class vectorCol(id:Vector)
  
   def main(args:Array[String]):Unit={

   var conf=new SparkConf().setMaster("local[4]").setAppName("VectorIndex01");
 
   val sparkSession = SparkSession
  .builder()
  .config(conf)
  .getOrCreate() 
  
   import  sparkSession.implicits._
   
   var file="D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\ml\\VectorIndex01.txt"
           
 val lineRDD=sparkSession.sparkContext.textFile(file).map(line=>line.split(",")) 
 
 var vectorRDD=lineRDD.map{file=>
   var arr=new Array[Double](6)
   for(i<- 0 to (file.length-1)){
     var a=file(i).toDouble;
     arr(i)=a;
   }
     arr
   }.map(line=>Vectors.dense(line)).map(line=>vectorCol(line)).toDF()
  
val featureIndexer = new VectorIndexer()
  .setInputCol("id")
  .setOutputCol("indexedFeatures")
  .setMaxCategories(2) // features with > 4 distinct values are treated as continuous.
   .fit(vectorRDD)
   
   featureIndexer.transform(vectorRDD).show(20,false)
    
}
}