package scala_grammar
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkContext._;


object spark01 {
  
  def main(args:Array[String]):Unit={
    
   
   val conf=new SparkConf().setAppName("sparkApp01").setMaster("local[50]");
     val sc = new SparkContext(conf); 
    
     var testfile="D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\test01.txt";
   val textFile = sc.textFile(testfile);
    
    var lines=textFile.map(s=>s.length);
   var a=lines.collect();
   // a.foreach(s=>print(s));
    
    val rdd = sc.parallelize(List("coffee panda","happy panda","happiest panda party"));
  
    //a.foreach(i=>println(i)); 
    
 
    
 val broadcastVar = sc.broadcast(Array(1, 2, 3));
 var v=broadcastVar.value;
 //v.foreach(i=>println(i));
 
 //
 val accum = sc.longAccumulator("myac");
 var ac=sc.parallelize(Array(1, 2, 3, 4));
 ac.foreach(x =>accum.add(x))
 
 var d=accum.value;
 println(d);

 
 
      
  }
}