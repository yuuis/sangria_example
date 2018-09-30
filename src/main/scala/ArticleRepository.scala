import sangria.relay.{ Connection, ConnectionArgs }

class ArticleRepository {
  import ArticleRepository._
  def findArticleById(id: String): Option[Article] = ArticleRepository.articles.find(_.id == id)

  // implementation using collection in mmemory
  // Actually, after, before, first, and last are used to decide what kind of query to access to the DB etc. to get the data
  // when data can be got, return data with paging info
  def articleConnection(connectionArgs: ConnectionArgs): Connection[Article] =
    Connection.connectionFromSeq(articles, connectionArgs)
}

object ArticleRepository {
  val articles = List(
    Article("1", "about AWS", Some("yamasaki"), List("aws", "infla")),
    Article("2", "about scala", None, List("null", "unforgivable")),
    Article("3", "about scala_2", None, List("null", "unforgivable")),
    Article("4", "about scala_3", None, List("null", "unforgivable")),
    Article("5", "about TaPL", None, List()),
    Article("6", "I found dynamic typing lanuguage is awesome", None, List()),
    Article("7", "I came back static typing language", None, List()),
    Article("8", "about Haskell", None, List()),
    Article("9", "about Haskell_2", None, List()),
    Article("10", "about F#", None, List()),
    Article("11", "rejected", None, List()),
    Article("12", "about Typescript", None, List()),
    Article("13", "about Typescript2", None, List()),
    Article("14", "rejected", None, List()))
}
