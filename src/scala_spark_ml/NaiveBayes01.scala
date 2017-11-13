package scala_spark_ml
 
import org.apache.spark.SparkContext;
import org.apache.spark._;
import org.apache.spark.mllib.classification.LogisticRegressionModel;
import org.apache.spark.mllib.classification.NaiveBayes;
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics;
import org.apache.spark.mllib.evaluation.MulticlassMetrics;
import org.apache.spark.mllib.util.MLUtils;
import org.apache.spark.mllib.linalg.{Vector, Vectors}
import org.apache.spark.mllib.regression.LabeledPoint

object NaiveBayes01{
  
   def main(args:Array[String]):Unit={
     
     var conf=new SparkConf().setMaster("local[4]").setAppName("MLAPP");
     var sc=new SparkContext(conf);
     
     var svmfile="D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\ml\\svmlib01.txt"
val data = MLUtils.loadLibSVMFile(sc, svmfile)

val splits = data.randomSplit(Array(0.6, 0.4), seed = 11L)
//val training = splits(0).cache()
//val test = splits(1)
 
// Split data into training (60%) and test (40%).
val Array(training, test) = data.randomSplit(Array(0.6, 0.4))

val model = NaiveBayes.train(training, lambda = 1.0, modelType = "multinomial")
  

val predictionAndLabel = test.map(p => (model.predict(p.features), p.label))
val accuracy = 1.0 * predictionAndLabel.filter(x => x._1 == x._2).count() / test.count()

}
}