package scala_spark_ml
 
import org.apache.spark.SparkContext;
import org.apache.spark.sql.SQLContext;
import org.apache.spark._;

 
import org.apache.spark.mllib.util.MLUtils;

import org.apache.spark.rdd.RDD
 
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types._;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql._;
import org.apache.spark.ml.feature.Tokenizer;
import org.apache.spark.ml.feature.HashingTF;
import org.apache.spark.ml.feature.IDF;
import org.apache.spark.ml.feature._;
import org.apache.spark.ml.linalg.{Vector, Vectors}
import org.apache.spark.ml._;
import org.apache.spark.ml.classification.NaiveBayes;
import org.apache.spark.ml.classification._;
import java.util._;


object TFIDF01 { 
  
  case class RawDataRecord(category: String, text: String);
  
   def main(args:Array[String]):Unit={
     
     var conf=new SparkConf().setMaster("local[4]").setAppName("MLAPP");
     var sc=new SparkContext(conf);
     sc.setLogLevel("INFO")
     var sqlsc=new SQLContext(sc);
     
     val sparkSession = SparkSession
  .builder()
  .config(conf)
  .getOrCreate()
  
   import  sparkSession.implicits._
    
     var file="D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\ml\\TFIDF01.txt"
 val dataFrame = sparkSession.sparkContext.textFile(file).map(_.split(",")).map(f=>RawDataRecord(f(0),f(1))).toDF() ;
 

     var tokenizerDF =new Tokenizer().setInputCol("text").setOutputCol("words").transform(dataFrame);

 tokenizerDF.show();
//将每个词转换成Int型，并计算其在文档中的词频（TF）
var hashingTF=new HashingTF().setInputCol("words").setOutputCol("rawFeatures").setNumFeatures(100);
var featurizedDF =hashingTF.transform(tokenizerDF);

featurizedDF.show(3,false)

 var idf=new IDF().setInputCol("rawFeatures").setOutputCol("features");
var idfModel=idf.fit(featurizedDF) //计算IDF
var TFIDFDF=idfModel.transform(featurizedDF) //计算TF-IDF
 
TFIDFDF.show(3,false)
var traiDataDS=TFIDFDF.select($"category", $"features").map{case Row(label:String,features: Vector)=> LabeledPoint(label.toDouble,Vectors.dense(features.toArray))
   }
var naiveBayes =new  NaiveBayes().setFeaturesCol("features").setLabelCol("label");
val naiveBayesmodel = new NaiveBayes().fit(traiDataDS)
var predit=naiveBayesmodel.transform(traiDataDS);
predit.show()
}
}