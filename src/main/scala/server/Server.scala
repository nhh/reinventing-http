package server

import java.net._
import java.io._

import utilities.HTTPResponse

import scala.io._

object Server extends App {

  val server = new ServerSocket(9999)

  println("Listening on :9999")

  while (true) {

    val socket = server.accept()

    val input = new BufferedSource(socket.getInputStream()).getLines()
    val output = new PrintStream(socket.getOutputStream())

    lazy val rawRequest = input.toList

    lazy val method = rawRequest.head.split(" ").head
    lazy val httpVersion = rawRequest.head.split(" ").tail
    lazy val path = rawRequest.head.split(" ")(1)

    method match {
      case "GET" => {
        val response = new HTTPResponse(body = Storage.get(path),
                                        header = Map("key" -> "value"),
                                        status = "OK",
                                        statusCode = 200)
        response.send(output)
      }
      case "POST" => {
        Storage.create("","")
        val response = new HTTPResponse(body = "Hello", header = Map("key" -> "value"), status = "CREATED", statusCode = 201)
        response.send(output)
      }
      case _ =>
    }

    socket.close()

  }
  
}
