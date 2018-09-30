import sangria.relay._
import sangria.schema._

object SchemaDefinition {

  // definition of Comment type on GraphQL
  val CommentType =
    ObjectType(
      "Comment",
      fields[Container, Comment] (
        Field("id", StringType, Some("id of comment"), resolve = _.value.id),
        Field("articleId", StringType, Some("article id of commented"), resolve = _.value.articleId),
        Field("body", StringType, Some("body of comment"), resolve = _.value.body)
      )
    )

  // definition of CommentConnection type on GraphQL
  val ConnectionDefinition(_, commentConnection) =
    Connection.definition[CommentRepository, Connection, Comment]("Comments", CommentType)

  // definition of Article type on GraphQL
  // and mapping of ariticle type on scala code (_.value is Article type)
  val ArticleType =
    ObjectType(
      "Article",
      fields[Container, Article](
        Field("id", StringType, Some("id of article"), resolve = _.value.id),
        Field("title", StringType, Some("title of article"), resolve = _.value.title),
        Field("author", OptionType(StringType), Some("author of article"), resolve = _.value.author),
        Field("comments", OptionType(commentConnection), Some("comments of article"), 
          arguments = Connection.Args.All, resolve = ctx => ctx.ctx.commentRepository.commentConnection(ctx.value.id, ConnectionArgs(ctx))),
        Field("tags", ListType(StringType), Some("tags of article"), resolve = _.value.tags)
      ))

  // definition of ArticleConnection type on GraphQL
  val ConnectionDefinition(_, articleConnection) =
    Connection.definition[ArticleRepository, Connection, Article]("Article", ArticleType)

  // argument used for query name id of type String
  val idArgument = Argument("id", StringType, description = "id")

  // Query. define query operation here
  // and how to get articles from ArticleRepository (ctx.ctx is ArticleRepository type)
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
