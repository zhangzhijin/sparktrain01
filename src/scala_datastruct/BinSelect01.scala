package scala_datastruct

object BinSelect01 {
  def main(arg:Array[String])={
    
    
    var arr=Array(1,2,3,4,5,6);
     
    // println(middle)
     
     var v_s=2;
    
    var a= binarySearch(arr,v_s)
    println(a)
      
     
  }
  
  def binarySearch(arr:Array[Int],search:Int):Int={
    
    var low=0;
    var high=arr.length-1;
    var middle=(high+1-low)/2+low;
    
   
     while(low<high){
      
      
      search match {
        case search if search>arr(middle) =>{low=middle; middle=(high+1-low)/2+low;}
        case search if search<arr(middle) =>{high=middle; middle=(high+1-low)/2+low;}
        case search if search==arr(middle) =>return middle+1
        
      }
      
    } 
    return -1
    
  }
  
}