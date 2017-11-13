package scala_spark_ml
import org.apache.spark.mllib.linalg._;
import org.apache.spark.mllib.linalg.Matrices;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.distributed.IndexedRowMatrix;
import org.apache.spark.mllib.linalg.distributed.MatrixEntry;
import org.apache.spark.mllib.linalg.distributed._;
import org.apache.spark.SparkContext;
import org.apache.spark._;

object CoordinateMatrix01 { 
   
   def main(args:Array[String]):Unit={
     
     val conf=new SparkConf().setMaster("local[2]").setAppName("sparkml");
     val sc=new SparkContext(conf);
     
 
   var a0=MatrixEntry(0.toLong, 0.toLong, 1.0);
   var a1=MatrixEntry(0.toLong, 1.toLong, 2.0);   
    var a2=MatrixEntry(0.toLong, 2.toLong, 3.0);   
   
    
   var b0=MatrixEntry(1.toLong, 0.toLong, 1.0);
   var b1=MatrixEntry(2.toLong, 1.toLong, 2.0);    
    var b2=MatrixEntry(1.toLong, 2.toLong, 3.0); 
    
    var rdd  =sc.parallelize(Array(a0,a1));
    
    var coordinateMatrix=new CoordinateMatrix(rdd);  
      
    
}
}