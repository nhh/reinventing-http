package utilities

import java.io.PrintStream
import java.net.{InetAddress, Socket}

import scala.io.BufferedSource

class HTTPRequest(header : Map[String,String],
                  body :String,
                  method : String,
                  path : String,
                  version : String,
                  host: String,
                  port: Integer
)
{

  def send(): Unit = {

    val socket = new Socket(InetAddress.getByName(this.host), this.port)
    lazy val input = new BufferedSource(socket.getInputStream()).getLines()
    val output = new PrintStream(socket.getOutputStream())

    output.println(s"${this.method} ${this.path} ${this.version}")
    output.flush()

    if (method == "POST" || method == "PUT"){
      output.println(body)
      output.flush()
    }

    while(input.hasNext){
      println(input.next())
    }

    socket.close()
  }

}
