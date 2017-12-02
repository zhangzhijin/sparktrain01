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

 import org.apache.spark.ml.feature._;
  

import org.apache.spark.rdd.RDD


object StringToIndex01 { 
  
  case class  indexToString(indexId:Double,zh_name:String);   
 case class  strToIndexRecord(id:String,en_name:String,zh_name:String); 

  
   def main(args:Array[String]):Unit={

   var conf=new SparkConf().setMaster("local[4]").setAppName("StringToIndex01");
 
   val sparkSession = SparkSession
  .builder()
  .config(conf) 
  .getOrCreate() 
  
   import  sparkSession.implicits._
   
   var file="D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\ml\\StringToIndex01.txt"
           
 val lineRDD=sparkSession.sparkContext.textFile(file).map(line=>line.split(","))
  val df= lineRDD.map(line=>strToIndexRecord(line(0),line(1),line(2))).toDF();
   
val stringIndexModel=new StringIndexer().setInputCol("zh_name").setOutputCol("indexFromString").fit(df);
 var stringIndexModelDF=stringIndexModel.transform(df)
 
 
 
  //var label=stringIndexModel.labels ;
   
  stringIndexModelDF.show();
  //  println(label.mkString(","))
 
   var file1="D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\ml\\IndexToString01.txt"
           
 val lineRDD1=sparkSession.sparkContext.textFile(file1).map(line=>line.split(",")).map(f=>indexToString(f(0).toDouble,f(1).toString())).toDF();
 
   
var indexToString1=new IndexToString().setInputCol("indexId").setOutputCol("zh_name1").setLabels(stringIndexModel.labels).transform(lineRDD1)
 indexToString1.show()   
    
}
}