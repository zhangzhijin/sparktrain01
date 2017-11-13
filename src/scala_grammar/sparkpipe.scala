package scala_grammar
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkContext._;


object sparkpipe {
  
  def main(args:Array[String]):Unit={
    
   
   val conf=new SparkConf().setAppName("sparkApp01").setMaster("local[1]");
     val sc = new SparkContext(conf);
    sc.setLogLevel("WARN");//ERROR
    
     //var testfile="D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\sortByKey.txt";
  val a = sc.parallelize(List(1,2,3))
  val b = sc.parallelize(List(4,5,6))
  
  val result = a.cartesian(b)
 
  result.collect().foreach(println)
  println(" ")
 
  
   
     
   
   //c.saveAsTextFile("D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\test01out");
    
    
      
  }
}