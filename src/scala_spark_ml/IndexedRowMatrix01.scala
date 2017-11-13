package scala_spark_ml
import org.apache.spark.mllib.linalg._;
import org.apache.spark.mllib.linalg.Matrices;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.distributed.IndexedRowMatrix;
import org.apache.spark.mllib.linalg.distributed._;
import org.apache.spark.SparkContext;
import org.apache.spark._;

object IndexedRowMatrix01 {
  
   def main(args:Array[String]):Unit={
     
     val conf=new SparkConf().setMaster("local[2]").setAppName("sparkml");
     val sc=new SparkContext(conf);
     
     var a:Array[Double]=Array(1.0,3.0,5.0,2.0,4.0,6.0);
     
     var map:Map[Long,Double]=Map(1.toLong->1,2.toLong->2);
     var map1:Map[Long,Double]=Map(1.toLong->1,2.toLong->2);
   
     
    var s1:Seq[Map[Long,Double]]=Seq(map,map1);
     
     var rdd= sc.parallelize(s1);
     
     var indexedRowRDD=rdd.map(word=>  new IndexedRow(word(1).toLong, Vectors.dense(Array(1.0,2.0,3.0))))
     
  var indexedRowMatrix=new IndexedRowMatrix(indexedRowRDD)
 
     println("numCols:"+indexedRowMatrix.numCols())
       println("numRows:"+indexedRowMatrix.numRows())
       
      
   
      
    
}
}