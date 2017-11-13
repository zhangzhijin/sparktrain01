package scala_spark_ml.sample
 
import org.apache.spark.SparkContext;
import org.apache.spark._;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types._;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql._;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.ml.evaluation.RegressionEvaluator;
import org.apache.spark.ml.feature.VectorAssembler;

 import org.apache.spark.ml.feature._;
  import org.apache.spark.ml.classification._;
  import org.apache.spark.ml.Pipeline;
    import org.apache.spark.ml.evaluation._;
    
    import org.apache.log4j.Level; 
import org.apache.log4j.Logger; 
  

import org.apache.spark.rdd.RDD


object DecisionTree01 { 
 
    
  
   def main(args:Array[String]):Unit={
     
      Logger.getLogger("org.apache.spark").setLevel(Level.OFF);
      Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF); 

   var conf=new SparkConf().setMaster("local[4]").setAppName("DecisionTree01");
 
     
    val sparkSession = SparkSession
  .builder()
  .config(conf)
  .getOrCreate()
  
   import  sparkSession.implicits._
   
      var file="D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\ml\\DecisionTree01.txt"
           
   val decisionDF = sparkSession.read.format("libsvm").load(file)
    
    //decisionDF.show(30, false)
    
   //×ª»»lable 
  val labelIndexer = new StringIndexer()
  .setInputCol("label")
  .setOutputCol("indexedLabel")
  .fit(decisionDF)
  
  //×ª»»feature
 val featureIndexer = new VectorIndexer()
  .setInputCol("features")
  .setOutputCol("indexedFeatures")
  .setMaxCategories(4) // features with > 4 distinct values are treated as continuous.
  .fit(decisionDF)
  
 
  
  val Array(trainingData, testData) = decisionDF.randomSplit(Array(0.7, 0.3))

// Train a DecisionTree model.
val dt = new DecisionTreeClassifier()
  .setLabelCol("indexedLabel")
  .setFeaturesCol("indexedFeatures")
  
  // Convert indexed labels back to original labels.
val labelConverter = new IndexToString()
  .setInputCol("prediction")
  .setOutputCol("predictedLabel")
  .setLabels(labelIndexer.labels)

// Chain indexers and tree in a Pipeline.
val pipeline = new Pipeline()
  .setStages(Array(labelIndexer, featureIndexer, dt, labelConverter))
  
  val model = pipeline.fit(trainingData)

// Make predictions.
val predictions = model.transform(testData)

//predictions.show(5,false)
predictions.select("predictedLabel", "label", "features").show(5,false)

val evaluator = new MulticlassClassificationEvaluator()
  .setLabelCol("indexedLabel")
  .setPredictionCol("prediction")
  .setMetricName("accuracy")
val accuracy = evaluator.evaluate(predictions)
println("Test Error = " + (1.0 - accuracy))
}
}