package repository

import model.User
import sangria.relay.{Connection, ConnectionArgs}

class UserRepository {
  import UserRepository._

  def findUserById(id: String): Option[User] = UserRepository.users.find(_.id == id)

  def userConnection(authorId: Option[String], connectionArgs: ConnectionArgs): Connection[User] =
    authorId.fold(Connection.connectionFromSeq(users.filter(_.id == -1), connectionArgs)) { aId =>
      Connection.connectionFromSeq(users.filter(_.id == aId), connectionArgs)
    }
}

object UserRepository {
  val users = List(
    User("1", "alex"),
    User("2", "bob"),
    User("3", "clive"),
    User("4", "daniel"),
    User("5", "edward"),
    User("6", "freddie"),
    User("7", "gillbert")
  )
}
