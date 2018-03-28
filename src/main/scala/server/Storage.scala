package server

object Storage {

  var STORAGE = Map("Genesis" -> "Block")

  def get(key : String): String = {
    return STORAGE.getOrElse(key, "Not found!")
  }

  def create(key : String, value : String): String = {
    STORAGE += key -> value
    return value
  }

  def delete(key : String): String = {
    STORAGE -= key
    return key
  }

  def update(key : String, value : String): String = {
    STORAGE(key).replaceAll("*",value)
  }

}
