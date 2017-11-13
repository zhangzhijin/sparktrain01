package scala_spark_streaming.kafka
import org.apache.spark.SparkContext;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.Seconds;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent;
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe;
import org.apache.spark.streaming.kafka010.OffsetRange;


object kafkaDirectStreamRDD {
  
  def main(arg:Array[String]):Unit={
    
  val kafkaParams = Map[String, Object](
  "bootstrap.servers" -> "localhost:9092",
  "key.deserializer" -> classOf[StringDeserializer],
  "value.deserializer" -> classOf[StringDeserializer],
  "group.id" -> "use_a_separate_group_id_for_each_stream",
  "auto.offset.reset" -> "latest",
  "enable.auto.commit" -> (false: java.lang.Boolean)
);
  

val topics = Array("test");
val conf=new SparkConf().setMaster("localhost[2]").setAppName("DSstreamRDD");

val sc=new SparkContext(conf);
val ssc=new StreamingContext(conf,Seconds(5));

val offsetRanges = Array(
  // topic, partition, inclusive starting offset, exclusive ending offset
  OffsetRange("test", 0, 0, 100),
  OffsetRange("test", 0, 100, 200)
);

//val rdd = KafkaUtils.createRDD[String, String](sc,kafkaParams,offsetRanges,PreferConsistent);
//val rdd = KafkaUtils.createRDD[String,String](sc, kafkaParams, offsetRanges, PreferConsistent);

 
  
  }
  
}