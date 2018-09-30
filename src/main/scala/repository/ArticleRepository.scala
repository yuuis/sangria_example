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
    Article("2", "about scala", Some("2"), List("scala", "functional", "oop")),
    Article("3", "about scala_2", Some("2"), List("scala", "functional", "oop")),
    Article("4", "about scala_3", Some("2"), List("scala", "functional", "oop")),
    Article("5", "about TaPL", Some("3"), List("TaPL")),
    Article("6", "I found dynamic typing lanuguage is awesome", Some("4"), List("ruby")),
    Article("7", "I came back static typing language", Some("4"), List("scala", "Haskell")),
    Article("8", "about Haskell", Some("5"), List("Haskell", "functional")),
    Article("9", "about Haskell_2", Some("5"), List("Haskell", "functional")),
    Article("10", "about F#", Some("6"), List("F#")),
    Article("11", "rejected", None, List()),
    Article("12", "about Typescript", Some("7"), List("js", "ts", "front-end")),
    Article("13", "about Typescript2", Some("7"), List("js", "ts", "front-end")),
    Article("14", "rejected", None, List()))
}
