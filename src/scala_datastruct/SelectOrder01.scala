package scala_datastruct

object SelectOrder01 {
  def main(arg:Array[String])={
  
      val arr = Array(6,4,2,1,3,0,5)
       
     orderSelectSort(arr)
  }
  
  def orderSelectSort(arr:Array[Int])={
    
    for(i<-0 to arr.length-1 ){
      
      var j=i+1;
      var temp=arr(i);
      
      while(j< arr.length)
      {
        if(arr(i)>arr(i))
        {
          temp=arr(i)
          arr(i)=arr(j)
          arr(j)=temp
        }
        j=j+1
      }
         
   }
    
    arr.foreach(f=>println(f))
    
  }
  

  
}