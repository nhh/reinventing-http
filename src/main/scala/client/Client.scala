package client

import java.net._
import java.io._
import scala.io._
import utilities.{ HTTPRequest, HTTPResponse }

object Client extends App {

  def sendRequest(method : String): Unit = {
    // Todo implement socket send here
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

    output.println(s"${request.method} ${request.path}")
    output.println("GET /wiki/Spezial:Search?search=Katzen&go=Artikel HTTP/1.1")
    output.println("Host: de.wikipedia.org")

    output.flush()

    println("Received: " + input.next())

    socket.close()
  }

  sendRequest("GET")

}
