package scala_spark_streaming.kafka
import org.apache.spark.SparkContext;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.Seconds;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent;
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe;
import org.apache.spark.streaming.kafka010.HasOffsetRanges;
import org.apache.spark.streaming.kafka010.OffsetRange;


object kafkaDirectStream {
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
val conf=new SparkConf().setMaster("localhost[2]").setAppName("DSstream");
val ssc=new StreamingContext(conf,Seconds(5));

val stream = KafkaUtils.createDirectStream[String, String](
  ssc,
  PreferConsistent,
  Subscribe[String, String](topics, kafkaParams)
)

stream.map(record => (record.key, record.value));


stream.foreachRDD { rdd =>
  val offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges;
  rdd.foreachPartition { iter =>
    val o: OffsetRange = offsetRanges(1)
    println(s"${o.topic} ${o.partition} ${o.fromOffset} ${o.untilOffset}")
  }
}
    
  }
  
}