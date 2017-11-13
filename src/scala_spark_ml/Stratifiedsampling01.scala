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

object Stratifiedsampling01 {
  
   def main(args:Array[String]):Unit={
     
     val conf=new SparkConf().setMaster("local[2]").setAppName("sparkml");
     val sc=new SparkContext(conf);
     
 val data = sc.parallelize(
  Seq((1, 'a'), (1, 'b'), (2, 'c'), (2, 'd'), (2, 'e'), (3, 'f')))

// specify the exact fraction desired from each key
val fractions = Map(1 -> 0.1, 2 -> 0.6, 3 -> 0.3)

// Get an approximate sample from each stratum
val approxSample = data.sampleByKey(withReplacement = false, fractions = fractions)
// Get an exact sample from each stratum
val exactSample = data.sampleByKeyExact(withReplacement = false, fractions = fractions)

println(approxSample.collect().mkString(","));
 println(exactSample.collect().mkString(","));

}
}