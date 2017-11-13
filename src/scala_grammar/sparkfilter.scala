package scala_grammar
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkContext._;


object sparfilter{
  
  def main(args:Array[String]):Unit={
    
   
   val conf=new SparkConf().setAppName("sparkApp01").setMaster("local[1]");
     val sc = new SparkContext(conf);
    
     var testfile="D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\test01.txt";
   val textFile = sc.textFile(testfile);
    
    var words=textFile.flatMap(s=>s.split(","));
    var filter=words.filter(s=>s=="java1");
    var lines=filter.map(s=>(s,1))
    
   var worldCount=lines.reduceByKey((a,b)=>a+b);
    worldCount.saveAsTextFile("D:\\zzj\\11_programfile\\eclipse_workspace\\scala_lc\\testfolder\\test01out");
    
    
      
  }
}