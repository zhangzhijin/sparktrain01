package scala_spark_rdd
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._;
import org.apache.spark.rdd.RDD.rddToPairRDDFunctions

object groupbykey01 {
    def main(args:Array[String]):Unit={
  
     val conf=new SparkConf().setAppName("sparkApp01").setMaster("local[1]");
     val sc = new SparkContext(conf);
    
     var testfile="D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\groupbykey.txt";
   val textFileRDD = sc.textFile(testfile,2);
    
    var mapRDD=textFileRDD.map(s=>(s.split(",")(0),s.split(",")(1)));
    var groupbykeyRDD=mapRDD.groupByKey(2);
    
    println(mapRDD.collect().mkString);
    println(groupbykeyRDD.collect().mkString);
   

    
    }
}