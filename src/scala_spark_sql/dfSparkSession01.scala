package scala_spark_sql

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.SparkSession
 import org.apache.spark.sql.types._
 import org.apache.spark.sql.Row
import org.apache.spark.sql.catalyst.encoders.ExpressionEncoder
import org.apache.spark.sql.Encoder
 
 

object dfSparkSession01 {
  def main(args:Array[String]):Unit={
    
    case class prd(cust_code:String,chinnel_id:String,chnnel_id_line:String,prd_code:String);


    
val sparkConf=new SparkConf().setAppName("dfSparkSession01").setMaster("local[4]")

val sparkSession=SparkSession.builder().config(sparkConf).getOrCreate();

sparkSession.sparkContext.setLogLevel("WARN");//OFF、FATAL、ERROR、WARN、INFO、DEBUG、ALL

  import sparkSession.implicits._
  
  
   var file="D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\df\\sparksession.json";
 val jsonRDD=sparkSession.read.format("json").load(file);
 
 
 
 val jsonDF=jsonRDD.toDF()
 jsonDF.printSchema()
 jsonDF.show();
// jsonDF.select($"age"+1,$"age",$"name").show();
 //jsonDF.filter($"age">21).show()
 //println( jsonDF.first())
 
//  jsonDF.groupBy("age").count().show()
 
 //jsonDF.createOrReplaceTempView("t_json"); 
 
 //val sql=sparkSession.sql("select * from t_json");
 
 //sql.show()
 
 jsonDF.createGlobalTempView("t_global_json");
    
 val sql=sparkSession.sql("select * from global_temp.t_global_json");
 sql.show();
 
sparkSession.stop();

 

  
}
}