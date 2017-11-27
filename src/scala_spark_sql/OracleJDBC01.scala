package scala_spark_sql
import org.apache.spark._;
import org.apache.spark.sql._

object OracleJDBC01 {

  def main(args: Array[String]) = {

    val conf = new SparkConf().setMaster("local[4]").setAppName("OracleJDBC01");
    val spark = SparkSession.builder().config(conf).getOrCreate();

  
   var  url = "jdbc:oracle:thin:@10.25.154.160:1535:ommstg)"
   
    val jdbcDF = spark.read
      .format("jdbc")
      .option("url",url)
      .option("dbtable", "ommdata.omm_pss_fund_base_info")
      .option("user", "ommopr")
      .option("password", "paic1234")
      .load()
      
jdbcDF.show(100,false)
  }
}