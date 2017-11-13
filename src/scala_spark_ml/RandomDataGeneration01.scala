package scala_spark_ml
import org.apache.spark.mllib.linalg._;
import org.apache.spark.mllib.linalg.Matrices;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.distributed.IndexedRowMatrix;
import org.apache.spark.mllib.linalg.distributed.MatrixEntry;
import org.apache.spark.mllib.linalg.distributed._;
import org.apache.spark.SparkContext;
import org.apache.spark._;
import org.apache.spark.mllib.stat.MultivariateOnlineSummarizer
import org.apache.spark.mllib.stat.MultivariateStatisticalSummary
import org.apache.spark.mllib.stat.Statistics
import org.apache.spark.rdd.RDD;
import org.apache.spark.mllib.random.RandomRDDs.normalRDD;

object RandomDataGeneration01 {
  
   def main(args:Array[String]):Unit={
     
     val conf=new SparkConf().setMaster("local[2]").setAppName("sparkml");
     val sc=new SparkContext(conf); 
     val normalRdd= normalRDD(sc, 1000000L, 10);
     
    println(normalRdd.collect().mkString(","))
     
     
    
}
}