package me.shengmin.graph

case class NodeReference private (val id: Int, val name: String) {
  override def hashCode() = id
}

object NodeReference {
  private [this] var nextId = 0

  def apply(name: String = "") = {
    val reference = new NodeReference(nextId, name)
    nextId += 1
    reference
  }
}
