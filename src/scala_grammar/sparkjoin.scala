package scala_grammar
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkContext._;


object sparkjoin {
  
  def main(args:Array[String]):Unit={
    
   
   val conf=new SparkConf().setAppName("sparkApp01").setMaster("local[1]");
     val sc = new SparkContext(conf);
    sc.setLogLevel("WARN");//ERROR
    
     //var testfile="D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\sortByKey.txt";
 val idName = sc.parallelize(Array((1, "zhangsan"), (2, "lisi"), (3, "wangwu")))
    
  val idAge = sc.parallelize(Array((1, 30), (2, 29), (4, 21)))
  idName.join(idAge).collect().foreach(println)
  println(" ")
   idName.leftOuterJoin(idAge).collect().foreach(println)
    println(" ")
   idName.rightOuterJoin(idAge).collect().foreach(println)
   
     
   
   //c.saveAsTextFile("D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\test01out");
    
    
      
  }
}