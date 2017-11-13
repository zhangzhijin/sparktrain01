package scala_grammar
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkContext._;


object sparkmap {
  
  def main(args:Array[String]):Unit={
    
   
   val conf=new SparkConf().setAppName("sparkApp01").setMaster("local[1]");
     val sc = new SparkContext(conf);
    
     var testfile="D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\test01.txt";
   val textFile = sc.textFile(testfile);
    
    var words=textFile.map(s=>s.split(","));
    //var lines=words.map(s=>(s,1))
   val wordsCollect= words.collect();
   var it=wordsCollect.iterator;
   
   while (it.hasNext)
   {
     
     var itt=it.next().iterator;
     while(itt.hasNext){
      print( itt.next()+",");
     }
     println("\n");
   }
   
  // val text= wordsCollect.mkString(",");
  // println(text);
  // var worldCount=lines.reduceByKey((a,b)=>a+b);
  //  worldCount.saveAsTextFile("D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\test01out");
    
    
      
  }
}