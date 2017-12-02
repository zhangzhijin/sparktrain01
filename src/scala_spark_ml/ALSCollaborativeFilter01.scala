package scala_spark_ml
import org.apache.spark._;

import org.apache.spark.sql._;
import org.apache.spark.ml.recommendation._;
import org.apache.spark.ml.feature._;
import org.apache.spark.ml.evaluation._;

object ALSCollaborativeFilter01 {
  
  case class stock_rate(userid:String,stock:String,rate: Double);
  
  def main(args:Array[String])={
    
    
    val conf=new SparkConf().setMaster("local[4]").setAppName("reco");
    
    val spark=SparkSession.builder().config(conf).getOrCreate();
   import spark.implicits._;
   
   
    val train="D:\\zzj\\11_programfile\\eclipse_workspace\\sparktrain01\\testfolder\\ml\\stock_rec_train.txt";
    
    
   
   var df=spark.sparkContext.textFile(train, 1).map(line=>line.split("\t")).map(arr=>stock_rate(arr(0),arr(1),arr(2).toDouble)).toDF();
 
   //轉換stock到ID
val itemStringIndexModdel=new StringIndexer() .setInputCol("stock").setOutputCol("indexStock").fit(df);
 val itemStringIndexDF= itemStringIndexModdel.transform(df);
 
 //轉換userid
 val userIdStringIndexModdel=new StringIndexer() .setInputCol("userid").setOutputCol("indexUserid").fit(itemStringIndexDF);
 val userStringIndexDF= userIdStringIndexModdel.transform(itemStringIndexDF);
  

 
val stringIndexDF=userStringIndexDF.toDF();

 stringIndexDF.show(500)
 
  //val indexStringDF=new IndexToString().setInputCol("indexStock").setOutputCol("stock1").setLabels( itemStringIndexModdel.labels).transform(stringIndexDF);
 
  


   
  var trainDF=stringIndexDF.filter(df("rate")<=2.toDouble);
    var testDF=stringIndexDF.filter(df("rate")>2.toDouble);
   
 val als=new ALS().setUserCol("indexUserid").setItemCol("indexStock").setRatingCol("rate").setRegParam(0.01);
  val alsModel= als.fit(trainDF).setPredictionCol("preCol");
  
  //alsModel.setColdStartStrategy("drop");
 //  val preDF=  alsModel.transform(testDF)
 
 val preDF=  alsModel.transform(testDF).orderBy($"preCol".desc).where($"userid">"10756197")
   
 //preDF.show(1000,false);
 
  
 
      
 val evaluator=new RegressionEvaluator().setLabelCol("rate").setPredictionCol("preCol");
   

 //均方根差
//var rmse=  evaluator.setMetricName("rmse").evaluate(preDF);

 //均方差
//var mse=  evaluator.setMetricName("mse").evaluate(preDF);
//println(rmse);
//println(mse)
    
  }
  
}