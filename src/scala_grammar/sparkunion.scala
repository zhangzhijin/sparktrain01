package scala_grammar
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkContext._;


object sparkunion {
  
  def main(args:Array[String]):Unit={
    
   
   val conf=new SparkConf().setAppName("sparkApp01").setMaster("local[1]");
     val sc = new SparkContext(conf);
    
     var testfile="D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\test01.txt";
   val textFileRDD = sc.textFile(testfile,2);
    
    val arrayRDD=sc.parallelize(Array("1","c","a","b"));
    val unionRDD=textFileRDD.union(arrayRDD);
   
    var c=unionRDD.map(s=>s);
    println(c.collect().mkString);
   
   //c.saveAsTextFile("D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\test01out");
    
    
      
  }
}