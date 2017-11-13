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
 


object dfSparkDataset02 {
  
  case class Person(name:String,age:Long);
  
  def main(arg:Array[String])={
    
      val warehouseLocation = "spark-warehouse"
    
    val conf=new SparkConf().setAppName("dfSparkDataset02").setMaster("local[4]");
    val spark=SparkSession.builder().config(conf).config("spark.sql.warehouse.dir", warehouseLocation).enableHiveSupport().getOrCreate();
    
    import spark.implicits._;
    import spark.sql
   
    
   
 
    
    
    
   val file="D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\df\\kv1.txt"

sql("CREATE TABLE IF NOT EXISTS src (key INT, value STRING)")
sql("LOAD DATA LOCAL INPATH '"+file+"' INTO TABLE src")

sql("SELECT * FROM src").show()


  }
   
}
   
  