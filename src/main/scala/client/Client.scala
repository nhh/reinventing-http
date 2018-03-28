package client

import utilities.HTTPRequest

object Client extends App {

  val request = new HTTPRequest(body = "",
                                method = "GET",
                                header = Map("key" -> "value"),
                                path = "/hola",
                                host = "localhost",
                                version = "HTTP/1.1",
                                port = 9999
  )

  println("Sending request")
  request.send()

}
