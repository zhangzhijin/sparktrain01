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

object dfSparkDataset01 {

  case class Person(name: String, age: Long);

  def main(arg: Array[String]) = {

    val conf = new SparkConf().setAppName("DataSet01").setMaster("local[4]").set("spark.eventLog.enabled", "false");

    val spark = SparkSession.builder().config(conf).getOrCreate();

    import spark.implicits._;

    val ds = Seq(Person("a", 32)).toDS()

    ds.show()

    val primitiveDS = Seq(1, 2, 3).toDS()

    primitiveDS.show()
  }

}
   
  