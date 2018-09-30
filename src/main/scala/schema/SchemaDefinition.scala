package schema

import model.{Article, Comment, User}
import repository.{ArticleRepository, CommentRepository, UserRepository}
import sangria.relay.{Connection, ConnectionArgs, ConnectionDefinition}
import sangria.schema.{Argument, Field, ListType, ObjectType, OptionType, Schema, StringType, fields}

object SchemaDefinition {


  val UserType =
    ObjectType(
      "User",
      fields[Container, User] (
        Field("id", StringType, Some("id of user"), resolve = _.value.id),
        Field("name", StringType, Some("name of user"), resolve = _.value.name)
      )
    )

  val ConnectionDefinition(_, userConnection) =
  Connection.definition[UserRepository, Connection, User]("User", UserType)


  val CommentType =
    ObjectType(
      "Comment",
      fields[Container, Comment] (
        Field("id", StringType, Some("id of comment"), resolve = _.value.id),
        Field("articleId", StringType, Some("article id of commented"), resolve = _.value.articleId),
        Field("author", OptionType(userConnection), Some("author of comment"),
          arguments = Connection.Args.All, resolve = ctx => ctx.ctx.userRepository.userConnection(ctx.value.author, ConnectionArgs(ctx))),
        Field("body", StringType, Some("body of comment"), resolve = _.value.body)
      )
    )

  val ConnectionDefinition(_, commentConnection) =
    Connection.definition[CommentRepository, Connection, Comment]("Comments", CommentType)

  // mapping of ariticle type on scala code (_.value is Article type)
  val ArticleType =
    ObjectType(
      "Article",
        fields[Container, Article](
        Field("id", StringType, Some("id of article"), resolve = _.value.id),
        Field("title", StringType, Some("title of article"), resolve = _.value.title),
        Field("author", OptionType(userConnection), Some("author of article"),
          arguments = Connection.Args.All, resolve = ctx => ctx.ctx.userRepository.userConnection(ctx.value.author, ConnectionArgs(ctx))),
        Field("comments", OptionType(commentConnection), Some("comments of article"),
          arguments = Connection.Args.All, resolve = ctx => ctx.ctx.commentRepository.commentConnection(ctx.value.id, ConnectionArgs(ctx))),
        Field("tags", ListType(StringType), Some("tags of article"), resolve = _.value.tags)
      )
    )

  val ConnectionDefinition(_, articleConnection) =
  Connection.definition[ArticleRepository, Connection, Article]("Article", ArticleType)



  // argument used for query name id of type String
  val idArgument = Argument("id", StringType, description = "id")

  // Query. define query operation here
  // and how to get articles from repository.ArticleRepository (ctx.ctx is repository.ArticleRepository type)
  val QueryType = ObjectType(
    "Query",
    fields[Container, Unit] (
      Field("article", OptionType(ArticleType), arguments = idArgument :: Nil,
        resolve = ctx => ctx.ctx.articleRepository.findArticleById(ctx.arg(idArgument))),
      Field("articles", OptionType(articleConnection), arguments = Connection.Args.All,
        resolve = ctx => ctx.ctx.articleRepository.articleConnection(ConnectionArgs(ctx)))
    )
  )

  // definition of Schema
  val ArticleSchema = Schema(QueryType)
}
