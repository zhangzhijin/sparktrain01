package scala_spark_sql

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.SparkSession
 import org.apache.spark.sql.types._
 import org.apache.spark.sql.Row
import org.apache.spark.sql.catalyst.encoders.ExpressionEncoder
import org.apache.spark.sql.Encoder
 
 

object dfSparkHiveTable {
  
  case class Record(key: Int, value: String)
  
  def main(args:Array[String]):Unit={
    

 var testfile="D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\df\\kv1.txt";
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
     
     val warehouseLocation = "spark-warehouse"
     
  
  import spark.implicits._
  import spark.sql

sql("CREATE TABLE IF NOT EXISTS src (key INT, value STRING)")
 sql("LOAD DATA LOCAL INPATH "+testfile+" INTO TABLE src")
 

sql("SELECT * FROM src").show()

spark.stop();

//sc.stop();

  
}
}