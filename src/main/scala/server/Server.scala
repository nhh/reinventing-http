package server

import java.net._
import java.io._
import scala.io._

object Server extends App {

  val server = new ServerSocket(9999)

  println("Listening on :9999")

  while (true) {
    val socket = server.accept()
    val input = new BufferedSource(socket.getInputStream()).getLines()
    val output = new PrintStream(socket.getOutputStream())

    output.println(input.next())
    output.flush()
    socket.close()
  }
  
}
