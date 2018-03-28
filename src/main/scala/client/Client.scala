package client

import java.net._
import java.io._
import scala.io._
import utilities.{ HTTPRequest, HTTPResponse }

object Client extends App {

  def sendRequest(): Unit = {
    val request = new HTTPRequest()
    request.header += "Accept" -> "text/html"
    request.path = "/translation"
    request.host = "localhost"
    request.port = 9999
    sendSocket(request)
  }

  private def sendSocket(request: HTTPRequest): Unit = {

    val socket = new Socket(InetAddress.getByName(request.host), request.port)

    lazy val input = new BufferedSource(socket.getInputStream()).getLines()

    val output = new PrintStream(socket.getOutputStream())

    output.println(s"${request.method} ${request.path} ${request.version}")
    output.flush()

    while(input.hasNext) {
      println("Response: " + input.next())
    }

    socket.close()
  }

  sendRequest()

}
