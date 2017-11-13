package scala_datastruct

object BubbleSort01 {
  def main(arg:Array[String])={
  
      val arr = Array(6,4,2,1,3,0,5)
       
     orderBubbleSort(arr)
  }
  
  def orderBubbleSort(arr:Array[Int])={
    
    for(i<-0 to arr.length-1 ){
     
      
       for(j<-1 to arr.length-1-i){
         
        if(arr(j-1)>arr(j))  {
          var   temp=arr(j-1)
          arr(j-1)=arr(j);
          arr(j)=temp;
          
          
        }
       }
       
         
   }
    
    arr.foreach(f=>println(f))
    
  }
  

  
}