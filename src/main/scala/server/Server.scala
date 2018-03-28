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

    val response = new HTTPResponse
    response.header += "status" -> "200"
    response.body = "<!DOCTYPE html><h1>Hello World></h1></html>"

    output.println("HTTP/1.1 200 OK")
    output.flush()

    response.header.foreach((header) => {
      output.println(s"${header._1}:${header._2}")
    })

    output.flush()

    output.println(response.body)
    output.flush()

    socket.close()

  }
  
}
