package scala_grammar
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkContext._;


object sparksortByKey {
  
  def main(args:Array[String]):Unit={
    
   
   val conf=new SparkConf().setAppName("sparkApp01").setMaster("local[1]");
     val sc = new SparkContext(conf);
    sc.setLogLevel("ERROR");
    
     var testfile="D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\sortByKey.txt";
   val textFileRDD = sc.textFile(testfile,2);
    
  
    
     val mapRDD=textFileRDD.map(s=>(s.split(",")(0),s.split(",")(1)));
   
    val sortRDD=mapRDD.sortByKey(true);
     
    println(mapRDD.collect().mkString);
     println(sortRDD.collect().mkString);
   
   //c.saveAsTextFile("D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\test01out");
    
    
      
  }
}