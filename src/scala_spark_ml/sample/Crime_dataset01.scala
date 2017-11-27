package scala_spark_ml.sample
import java.io._;
import org.apache.spark._;
import org.apache.spark.sql._;

object Crime_dataset01 {

  case class crimeInfo(
    id: String, //unique id
    Case_Number: String, //Case number
    Date: String, //Date of the Crime
    Block: String, //Block address of the Crime location
    IUCR: String, //IUCR id of the case
    Primary_type: String, //Crime Type
    Description: String, //Description of the crime
    Location_description: String, //location description of the crime
    Arrest: String, //True if arrested or false
    Domestic: String, //Sub Crime Type
    Beat: String, //Police beat
    District: String, //Code of the district
    Ward: String, //ward of the crime location
    community: String, // comunity service
    Fbicode: String, //fbi code for the crime
    XCo: String, //X-Co ordinate
    YCor: String, //Y-Co ordinate
    Year: String, //year of the crime
    Updated: String, //date of the last updation
    lattitude: String, //lattitude
    longititude: String, //longititude
    loctation: String //full location with lattitide and longitude
    )

  def main(args: Array[String]) = {
    var file = "testfolder/ml/ml_sample/Crime_dataset"

    val conf = new SparkConf().setMaster("local[4]").setAppName("Crime_dataset01");

    val spark = SparkSession.builder().config(conf).getOrCreate();

    import spark.implicits._;

    val textRDD = spark.sparkContext.textFile(file).map(_.split(","));
    
    /* var mx=textRDD.map(f=>f.length) 
     println(mx.max())
      println(mx.min())*/

   val df = textRDD.map(line => crimeInfo(line(0),
      line(1),
      line(2),
      line(3),
      line(4),
      line(5),
      line(6),
      line(7),
      line(8),
      line(9),
      line(10),
      line(11),
      line(12),
      line(13),
      line(14),
      line(15),
      line(16),
      line(17),
      line(18),
      
     "",
      "",
    "")).toDF();
     
     df.createOrReplaceTempView("t_crime_info")
    
   val sql=spark.sql("select * from t_crime_info")
   
    sql.show(false)
 
    
    
  }
}