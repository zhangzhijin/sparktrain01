package scala_spark_sql

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.SparkSession
 import org.apache.spark.sql.types._
 import org.apache.spark.sql.Row
import org.apache.spark.sql.catalyst.encoders.ExpressionEncoder
import org.apache.spark.sql.Encoder
 
 

object dfSparkDatasource {
  def main(args:Array[String]):Unit={
    

 var testfile="D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\df\\sparkdatasource.json";
 var outfile="D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\df\\sparkdatasourceout";
 var paquetfile="D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\df\\parquet.parquet";   

 
 val conf =new SparkConf().setAppName("sparksession").setMaster("local[2]");
 val sc=new SparkContext(conf);
 
     val spark = SparkSession
      .builder()
      .appName("Spark SQL basic example")
      .config(conf)
      .getOrCreate()
      
  spark.sparkContext.setLogLevel("WARN");//OFF、FATAL、ERROR、WARN、INFO、DEBUG、ALL
  
  import spark.implicits._
   
 
  //val df = spark.read.load(paquetfile)
  var df=spark.read.parquet(paquetfile);
    //val df = spark.read.format("json").load(testfile);
 
  //df.select("name", "age").write.format("parquet").save(outfile)
  //df.write.parquet(outfile)
   
    df.show()
 df.printSchema()

spark.stop();

//sc.stop();

  
}
}