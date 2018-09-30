package repository

import model.Comment
import sangria.relay.{Connection, ConnectionArgs}

class CommentRepository {
  import CommentRepository._

//  def findUserById(id: String): Option[Comment] = CommentRepository.comments.find(_.id == id)

  def commentConnection(articleId: String, connectionArgs: ConnectionArgs): Connection[Comment] =
    Connection.connectionFromSeq(comments.filter(_.articleId == articleId), connectionArgs)
}

object CommentRepository {
  val comments = List(
    Comment("1", "1", "someone talk about OCaml"),
    Comment("2", "1", "From that I want to talk about Haskell"),
    Comment("3", "2", "someone talk about OCaml"),
    Comment("4", "2", "From that I want to talk about Haskell")
  )
}
