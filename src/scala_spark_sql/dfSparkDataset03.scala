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
 


object dfSparkDataset03 {
  
  
  
  def main(arg:Array[String])={
    
    val conf=new SparkConf().setAppName("dfSparkDataset03").setMaster("local[4]");
    val spark=SparkSession.builder().config(conf).getOrCreate();
    

    import spark.implicits._;
    
    
     val seqRdd=   spark.sparkContext.parallelize(Seq(
        Array(1,2,3),Array(4,5,6)
    ))
    
   val rowRDD= seqRdd.map(f=>Row(f(0).toDouble,f(1).toDouble,f(2).toDouble))
    
    val schemaString="name age cource";
    
     val schemaFields=schemaString.split(" ").map(filed=>StructField(filed,DoubleType,true))
     
     val schema=StructType(schemaFields);
     
    val df= spark.createDataFrame(rowRDD,schema)
    
    df.createOrReplaceTempView("t")
    
    val dfSelect=spark.sql("select * from t")
    
    val file="D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\df\\dfwrite01.json"
    dfSelect.write.json(file)
     
df.show()
  }
   
}
   
  