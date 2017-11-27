package scala_grammar

object lazyParamer01 {
  
  def setProperTi():String={
    
    println("init")
    return "a";
  }
  
  def main(args:Array[String]){
    
    var a=setProperTi();
    println("after call")
     println(a)
     println(a)
    
    lazy   val ab=setProperTi();
    println(" lazy after call")
    
    println(ab)
     println(ab)
    
  }
}