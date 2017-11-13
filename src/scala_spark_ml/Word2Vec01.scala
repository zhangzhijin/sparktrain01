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


object Word2Vec01 { 
  
  case class RawDataRecord(category: String, text: String);
  
   def main(args:Array[String]):Unit={
     
     var conf=new SparkConf().setMaster("local[4]").setAppName("Word2Vec01");
     var sc=new SparkContext(conf);
     sc.setLogLevel("INFO")
 
     
     val sparkSession = SparkSession
  .builder()
  .config(conf)
  .getOrCreate()
  
   import  sparkSession.implicits._
    
     var file="D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\ml\\CountVectorize01.txt"

  val documentDF = sparkSession.createDataFrame(Seq(
    "Hi I heard about Spark".split(" "),
        "I wish Java could use case classes".split(" "),
       "Logistic regression models are neat".split(" ")
      ).map(Tuple1.apply)).toDF("text")
 

      documentDF.show(5,false)
      
      val word2Vec = new Word2Vec().
       setInputCol("text").
       setOutputCol("result").
         setVectorSize(4).
        setMinCount(1)
    word2Vec.fit(documentDF).transform(documentDF).show(5,false)
}
}