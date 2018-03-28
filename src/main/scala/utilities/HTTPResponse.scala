package utilities

import java.io._

class HTTPResponse(header : Map[String,String], body :String, status: String, statusCode: Integer) {

  def send(output : PrintStream): Unit = {

    output.println(s"HTTP/1.1 ${this.statusCode} ${this.status}")
    output.flush()

    this.header.foreach((header) => {
      output.println(s"${header._1}:${header._2}")
      output.flush()
    })

    output.println(this.body)
    output.flush()
  }

}
