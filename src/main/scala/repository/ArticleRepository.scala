package repository

import model.Article
import sangria.relay.{Connection, ConnectionArgs}

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
    Article("1", "about AWS", Some("1"), List("aws", "infla")),
    Article("2", "about scala", Some("2"), List("null", "unforgivable")),
    Article("3", "about scala_2", Some("2"), List("null", "unforgivable")),
    Article("4", "about scala_3", Some("2"), List("null", "unforgivable")),
    Article("5", "about TaPL", Some("3"), List()),
    Article("6", "I found dynamic typing lanuguage is awesome", Some("4"), List()),
    Article("7", "I came back static typing language", Some("4"), List()),
    Article("8", "about Haskell", Some("5"), List()),
    Article("9", "about Haskell_2", Some("5"), List()),
    Article("10", "about F#", Some("6"), List()),
    Article("11", "rejected", None, List()),
    Article("12", "about Typescript", Some("7"), List()),
    Article("13", "about Typescript2", Some("7"), List()),
    Article("14", "rejected", None, List()))
}
