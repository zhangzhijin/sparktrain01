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

object Hypothesistest01 {
  
   def main(args:Array[String]):Unit={
     
     val conf=new SparkConf().setMaster("local[2]").setAppName("sparkml");
     val sc=new SparkContext(conf); 
     
     val vec: Vector = Vectors.dense(0.1, 0.15, 0.2, 0.3, 0.25)

// compute the goodness of fit. If a second vector to test against is not supplied
// as a parameter, the test runs against a uniform distribution.
val goodnessOfFitTestResult = Statistics.chiSqTest(vec)
// summary of the test including the p-value, degrees of freedom, test statistic, the method
// used, and the null hypothesis.
println(goodnessOfFitTestResult)
    
}
}