package utilities

class HTTPRequest {

  val version = "HTTP/1.1"
  var header = Map("client" -> "reinventing-http-client")
  var method = "GET"
  var path = ""
  var host = ""
  var port = 80
  var body = ""

}
