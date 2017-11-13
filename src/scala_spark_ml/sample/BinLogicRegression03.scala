package scala_spark_ml.sample
 
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

 import org.apache.spark.ml.classification.LogisticRegression;
  import org.apache.spark.ml.classification.BinaryLogisticRegressionSummary;
 

import org.apache.spark.rdd.RDD


object BinLogicRegression03 { 
  
   
   def main(args:Array[String]):Unit={

   var conf=new SparkConf().setMaster("local[4]").setAppName("BinLogicRegression03");
 
     
    val sparkSession = SparkSession
  .builder()
  .config(conf)
  .getOrCreate()
  
   import  sparkSession.implicits._
   
      var file="D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\ml\\BinLogicRegression03.txt"
           
     var training =sparkSession.read.format("libsvm").load(file);
  
 val lr = new LogisticRegression()
  .setMaxIter(10)
  .setRegParam(0.3)
  .setElasticNetParam(0.8)
  
  val lrModel = lr.fit(training);
   
   var numberCls= lrModel.numClasses
   var numFeatures=lrModel.numFeatures
 
   println(numberCls)
      println(numFeatures)
   
    
}
}