package scala_spark_ml
import org.apache.spark.mllib.linalg._;
import org.apache.spark.mllib.linalg.Matrices;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.distributed._;
import org.apache.spark.SparkContext;
import org.apache.spark._;

object RowMatrix01 {
  
   def main(args:Array[String]):Unit={
     
     val conf=new SparkConf().setMaster("local[2]").setAppName("sparkml");
     val sc=new SparkContext(conf);
     var a:Array[Double]=Array(1.0,3.0,5.0,2.0,4.0,6.0);
     
     val dv: Vector = Vectors.dense(1.0, 0.0, 3.0)
     
     val dm: Matrix = Matrices.dense(3, 2, Array(1.0, 3.0, 5.0, 2.0, 4.0, 6.0));
     
     var rdd= sc.parallelize(a);
     
     var rddVector=rdd.map(word=>Vectors.dense(word));
     
     val rowMatrix: RowMatrix = new RowMatrix(rddVector)
     println(rowMatrix.numCols()+";"+rowMatrix.numRows())
    rowMatrix.rows.foreach(println)  
     
     val qrResult = rowMatrix.tallSkinnyQR(true)
}
}