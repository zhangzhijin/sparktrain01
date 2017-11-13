package scala_spark_sql

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.SparkSession
 import org.apache.spark.sql.types._
 import org.apache.spark.sql.Row
import org.apache.spark.sql.catalyst.encoders.ExpressionEncoder
import org.apache.spark.sql.Encoder
import org.apache.spark.sql.SQLContext

import collection.mutable.Seq;
 


object dfSparkSpecifyingSchema {
   
  
   
  def main(args:Array[String]):Unit={
    

 var testfile="D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\df\\sparksession.txt";
   
val conf =new SparkConf().setAppName("sparksession").setMaster("local[2]");
 val sc=new SparkContext(conf);
 
  val sparksession = SparkSession.builder()
  //sparkBuilder.master("local[2]");
  .config(conf)
  //sparkBuilder.appName("sparkSession");
 .getOrCreate();
  sparksession.sparkContext.setLogLevel("WARN");//OFF、FATAL、ERROR、WARN、INFO、DEBUG、ALL
  
   
 import  sparksession.implicits._

 
var textfileRDD=sparksession.sparkContext.textFile(testfile);
var mapSplitRDD=textfileRDD.map(_.split(","))
var mapRowRDD= mapSplitRDD.map(attributes => Row(attributes(0), attributes(1).trim));

var schema=StructType(
   StructField("name", StringType, nullable = true)::
    StructField("age", StringType, nullable = true)::Nil
);

 var df=sparksession.createDataFrame(mapRowRDD, schema);
 
//df.show()
 df.select($"name", $"age">2).show()

 df.createOrReplaceTempView("t");

//sparksession.sql("select * from t").show()
df.map(teenager => "Name: " + teenager(0)).show()
 
/* val schema = StructType(StructField("name", StringType,  true)::StructField("age", StringType,  true)::Nil);

 var df=sparksession.createDataFrame(mapRowRDD, schema);*/
 
  

sparksession.stop();

//sc.stop();

  
}
}