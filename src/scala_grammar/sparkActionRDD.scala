package scala_grammar
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkContext._;


object sparkActionRDD {
  
  def main(args:Array[String]):Unit={
    
   
   val conf=new SparkConf().setAppName("sparkApp01").setMaster("local[5]");
     val sc = new SparkContext(conf);
    sc.setLogLevel("WARN");//ERROR
    
     //var testfile="D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\sortByKey.txt";
  var data = sc.parallelize(List(5,6,2,3,4))
   println(data.partitions.length)
 
//val result = data.coalesce(2, false)
   val result = data.repartition(1);
  
   println(data.partitions.length)
 
 
  //var reduceRDD=result.reduce(_+_);
   //var countRDD=result.count();
   //var firstRDD=result.first();
   //var takeRDD=result.take(2);
   
   var takeRDD=result.takeOrdered(2);
   
   
   println(takeRDD.mkString(","));
   
     
   
   //c.saveAsTextFile("D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\test01out");
    
   sc.stop(); 
      
  }
}