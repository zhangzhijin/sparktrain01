package scala_spark_ml
 
import org.apache.spark.SparkContext;
import org.apache.spark._;
import org.apache.spark.mllib.classification.SVMModel;
import org.apache.spark.mllib.classification.SVMWithSGD;
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics;
import org.apache.spark.mllib.util.MLUtils;
import org.apache.spark.mllib.linalg.{Vector, Vectors}

object SVMwithSGD01 {
  
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
val model = SVMWithSGD.train(training, numIterations)

// Clear the default threshold.
model.clearThreshold()

var clearThreshold=model.getThreshold;
     println("clearThreshold=:"+clearThreshold);
       
     
  var willTestVector = Vectors.dense(0.4, 0.1, 0.5)
  
 var testVector= model.predict(willTestVector);
   println("testVector=:"+testVector);
  
// Compute raw scores on the test set.

val scoreAndLabels = test.map { point =>
  val score = model.predict(point.features)
  (score, point.label)
}

// Get evaluation metrics.
val metrics = new BinaryClassificationMetrics(scoreAndLabels)
val auROC = metrics.areaUnderROC()

println("Area under ROC = " + auROC)

// Save and load model
//model.save(sc, "target/tmp/scalaSVMWithSGDModel")
//val sameModel = SVMModel.load(sc, "target/tmp/scalaSVMWithSGDModel")



}
}