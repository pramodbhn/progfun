abstract class IntSet {
  def contains(x: Int): Boolean
  def incl(x: Int): IntSet
  def union(other: IntSet): IntSet
}

object Empty extends IntSet {
  override def incl(x: Int): IntSet = new NonEmpty(x,  Empty,  Empty)
  override def contains(x: Int): Boolean = false
  override def toString = "."
  override def union(other: IntSet): IntSet = other
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  override def contains(x: Int): Boolean = {
    if (x < elem) left.contains(x)
    else if (x > elem) right contains x
    else true
  }

  override def incl(x: Int): IntSet = {
    if (x < elem) new NonEmpty(elem, left.incl(x), right)
    else if (x > elem) new NonEmpty(elem, left, right.incl(x))
    else this
  }
  override def toString = "{" + left + elem + right + "}"

  override def union(other: IntSet): IntSet = {
    ((left union right) union other) incl elem
  }
}


val t1 = new NonEmpty(3,  Empty,  Empty)
val t2 = t1 incl 4
