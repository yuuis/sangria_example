package repository

import model.Comment
import sangria.relay.{Connection, ConnectionArgs}

class CommentRepository {
  import CommentRepository._

  def findUserById(id: String): Option[Comment] = CommentRepository.comments.find(_.id == id)

  def commentConnection(articleId: String, connectionArgs: ConnectionArgs): Connection[Comment] =
    Connection.connectionFromSeq(comments.filter(_.articleId == articleId), connectionArgs)
}

object CommentRepository {
  val comments = List(
    Comment("1", "1", Some("2"), "someone talk about OCaml"),
    Comment("3", "1", Some("6"), "Why don't you use GCP?"),
    Comment("4", "2", Some("2"), "someone talk about OCaml"),
    Comment("5", "2", Some("5"), "From that I want to talk about Haskell"),
    Comment("6", "3", Some("5"), "From that I want to talk about Haskell"),
    Comment("6", "6", Some("2"), "dynamic type language is ass"),
    Comment("2", "10", Some("5"), "From that I want to talk about Haskell"),
    Comment("2", "12", Some("5"), "i love tyepscript"),
  )
}
