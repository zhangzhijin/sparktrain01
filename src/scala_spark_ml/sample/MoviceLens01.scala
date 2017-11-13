package scala_spark_ml.sample
 
import org.apache.spark.SparkContext;
import org.apache.spark._;
import org.apache.spark.mllib.classification.SVMModel;
import org.apache.spark.mllib.classification.SVMWithSGD;
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics;
import org.apache.spark.mllib.util.MLUtils;

import org.apache.spark.rdd.RDD


object MoviceLens01 { 
  
   def main(args:Array[String]):Unit={
     
     var conf=new SparkConf().setMaster("local[4]").setAppName("MLAPP");
     var sc=new SparkContext(conf);
     
      var file="D:\\zzj\\11_programfile\\eclipse_workspace\\ml_test_data\\ml100k\\u.user"
    
     var user_data=sc.textFile(file);
      
    var user_files= user_data.map(x=>x.split('|')); 
    
    var  aa=user_files.map(fileds=>fileds(0)).count()
    //println(aa)
   var num_genders = user_files.map( fields=>fields(2)).distinct().count()  
     var num_occ = user_files.map( fields=>fields(3)).distinct().count()  
         var num_zipcode = user_files.map( fields=>fields(4)).distinct().count()  
    println(num_occ)
      println(num_zipcode)
    
 //user.foreach( f=> f.foreach(a=>print(a)) )
 
    
    

}
}