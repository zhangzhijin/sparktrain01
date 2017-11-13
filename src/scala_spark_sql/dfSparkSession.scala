package scala_spark_sql

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.SparkSession
 import org.apache.spark.sql.types._
 import org.apache.spark.sql.Row
import org.apache.spark.sql.catalyst.encoders.ExpressionEncoder
import org.apache.spark.sql.Encoder
 
 

object dfSparkSession {
  def main(args:Array[String]):Unit={
    
    case class prd(cust_code:String,chinnel_id:String,chnnel_id_line:String,prd_code:String);

 var testfile="D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\df\\sparksession02.txt";
    
val sparkConf=new SparkConf().setAppName("sparkSession").setMaster("local[4]")

val sparkSession=SparkSession.builder().config(sparkConf).getOrCreate();

sparkSession.sparkContext.setLogLevel("WARN");//OFF、FATAL、ERROR、WARN、INFO、DEBUG、ALL

  import sparkSession.implicits._
  
 
   
 
   //val df = sparkSession.read.format("textfile").load(testfile);
  
  val textFileRDD=sparkSession.sparkContext.textFile(testfile).map(_.split("\t")).map(word=>prd(word(0),word(1),word(2),word(3)))
  
 
 
 
     
sparkSession.stop();

 

  
}
}