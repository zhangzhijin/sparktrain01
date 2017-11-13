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

 import org.apache.spark.ml.recommendation.ALS;
   import org.apache.spark.ml.recommendation.ALSModel
  import org.apache.spark.ml.recommendation._;

import org.apache.spark.rdd.RDD


object MoviceLens02 { 
  
    case class rateRecord(userID: Int, itemID: Int,rating: Double);
   def main(args:Array[String]):Unit={

           var conf=new SparkConf().setMaster("local[4]").setAppName("MLAPP");
 
     
    val sparkSession = SparkSession
  .builder()
  .config(conf)
  .getOrCreate()
  
   import  sparkSession.implicits._
   
      var file="D:\\zzj\\11_programfile\\eclipse_workspace\\ml_test_data\\ml100k\\u.data"
    
     var rateDf=sparkSession.sparkContext.textFile(file).map(f=>f.split("\t")).map(f=>rateRecord(f(0).toInt,f(1).toInt,f(2).toDouble)).toDF("userID","itemID","rating");
    
   
    val Array(training, test) = rateDf.randomSplit(Array(0.8, 0.2))
    
    val als=new ALS().setUserCol("userID").setItemCol("itemID").setRatingCol("rating").setRank(10).setMaxIter(5);
    
    val alsModel=als.fit(training);
     //alsModel.setColdStartStrategy("drop");
  
    val predictions = alsModel.transform(test)
  
   // var regressionEvaluator =new RegressionEvaluator().setLabelCol("rating").setPredictionCol("prediction").setMetricName("mse")
    
   // val mse = regressionEvaluator.evaluate(predictions) 
    predictions.show()
    //println(mse) 
    

}
}