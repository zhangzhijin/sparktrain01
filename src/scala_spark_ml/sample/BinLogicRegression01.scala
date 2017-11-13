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
import org.apache.spark.ml.feature.VectorAssembler;

 import org.apache.spark.ml.classification.LogisticRegression;
  

import org.apache.spark.rdd.RDD


object BinLogicRegression01 { 
  
    case class rateRecord(affairs:Double, 
        gender:String, 
        age:Double, 
        yearsmarried:Double,
        children:String,
        religiousness:Double,
        education:Double,
        occupation:Double,
        rating:Double);
    
  
   def main(args:Array[String]):Unit={

   var conf=new SparkConf().setMaster("local[4]").setAppName("BinLogicRegression01");
 
     
    val sparkSession = SparkSession
  .builder()
  .config(conf)
  .getOrCreate()
  
   import  sparkSession.implicits._
   
      var file="D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\ml\\BinLogicRegression01.txt"
           
     var rateDf=sparkSession.sparkContext.textFile(file).map(f=>f.split(",")).
     map(f=>rateRecord(f(0).toDouble,f(1).toString(),
         f(2).toDouble,
         f(3).toDouble,
         f(4).toString(),
         f(5).toDouble,
         f(6).toDouble,
         f(7).toDouble,
         f(8).toDouble
         
     )).toDF();
    
    rateDf.createOrReplaceTempView("User");
   var sql= sparkSession.sql("select * from User")
   sql.show()
   
   val affairs = "case when affairs>0 then 1 else 0 end as affairs,"
val gender = "case when gender='female' then 0 else 1 end as gender,"
val children = "case when children='yes' then 1 else 0 end as children,"

val sqlDF = sparkSession.sql("select " +
  affairs +
  gender +
  "age,yearsmarried," +
  children +
  "religiousness,education,occupation,rating" +
  " from User ");
   
   val colArray2 = Array("gender", "age", "yearsmarried", "children", "religiousness", "education", "occupation", "rating")
   
   var vecDF =new VectorAssembler().setInputCols(colArray2).setOutputCol("features").transform(sqlDF)
   
   val Array(training, test) = vecDF.randomSplit(Array(0.8, 0.2))
    
 val lrModel = new LogisticRegression().setLabelCol("affairs").setFeaturesCol("features").fit(training)
   
 //println(lrModel.numClasses)
 // println(lrModel.numFeatures)
    println(lrModel.getThreshold)
      println(lrModel.elasticNetParam)
        println(lrModel.regParam)
 var predit= lrModel.transform(test).select($"affairs",$"features", $"rawPrediction",$"probability",$"prediction")
 
predit.show(30, false)

}
}