package scala_grammar

class Person01(private var firstName:String,var lastName:String) {
  
    var a="c"
  println(firstName)
  println(lastName)
  
  def this(  firstName:String)
    {
      this(firstName,"lisi")
      
    }
  
}

object Person {
  def main(arg:Array[String])={
    
    var p=new Person01("a","b")
    p.a="d"
    println(p.a)
    
    var p1=new Person01("zhangsan")
  }
  }
 