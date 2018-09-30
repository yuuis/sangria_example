package repository

import model.User
import sangria.relay.{Connection, ConnectionArgs}

class UserRepository {
  import UserRepository._

  def findUserById(id: String): Option[User] = UserRepository.users.find(_.id == id)

  def userConnection(authorId: String, connectionArgs: ConnectionArgs): Connection[User] =
    Connection.connectionFromSeq(users.filter(_.id == authorId), connectionArgs)
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
