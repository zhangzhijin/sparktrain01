package scala_grammar
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkContext._;


object sparkaggregateByKey {
  
    def seq(a:Int,b:Int):Int={
      print("seq :"+a+" "+b);
   return math.max(a, b);
 }
    
        def com(a:Int,b:Int):Int={
   return a+b;
 }
  
  def main(args:Array[String]):Unit={
    
   val conf=new SparkConf().setAppName("sparkApp01").setMaster("local[1]");
     val sc = new SparkContext(conf);
    
   //  var testfile="D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\groupbykey.txt";
  
     var list=List[(Int,Int)]((1,2),(1,3),(1,4),(2,1),(2,2));
     val textFileRDD = sc.parallelize(list);
    
    var a=textFileRDD.aggregateByKey(3)(seq, com);
    println(a.collect().mkString)
   
   //c.saveAsTextFile("D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\test01out");
  }
}