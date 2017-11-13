package scala_spark_ml
 
import org.apache.spark.SparkContext;
import org.apache.spark._;
import org.apache.spark.mllib.classification.LogisticRegressionModel;
import org.apache.spark.mllib.classification.LogisticRegressionWithLBFGS;
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics;
import org.apache.spark.mllib.evaluation.MulticlassMetrics;
import org.apache.spark.mllib.util.MLUtils;
import org.apache.spark.mllib.linalg.{Vector, Vectors}
import org.apache.spark.mllib.regression.LabeledPoint

object LogistRegressionWithLBFGS01{
  
   def main(args:Array[String]):Unit={
     
     var conf=new SparkConf().setMaster("local[4]").setAppName("MLAPP");
     var sc=new SparkContext(conf);
     
     var svmfile="D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\ml\\svmlib01.txt"
val data = MLUtils.loadLibSVMFile(sc, svmfile)

val splits = data.randomSplit(Array(0.6, 0.4), seed = 11L)
val training = splits(0).cache()
val test = splits(1)
// Run training algorithm to build the model 
val numIterations = 100 


// Run training algorithm to build the model
val model = new LogisticRegressionWithLBFGS()
  .setNumClasses(10)
  .run(training)
  

// Clear the default threshold.
model.clearThreshold()


// Compute raw scores on the test set.
val predictionAndLabels = test.map { case LabeledPoint(label, features) =>
  val prediction = model.predict(features)
  (prediction, label)
}
 

// Get evaluation metrics.
val metrics = new MulticlassMetrics(predictionAndLabels)
val accuracy = metrics.accuracy

println(" accuracy = " + accuracy)

// Save and load model
//model.save(sc, "target/tmp/scalaSVMWithSGDModel")
//val sameModel = SVMModel.load(sc, "target/tmp/scalaSVMWithSGDModel")



}
}