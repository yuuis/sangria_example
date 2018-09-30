import akka.http.scaladsl.Http
import http.Routes

object Server extends App with Routes{

  Http().bindAndHandle(route, "0.0.0.0", sys.props.get("http.port").fold(9000)(_.toInt))
}
