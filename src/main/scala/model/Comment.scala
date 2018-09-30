package model

case class Comment(id: String, articleId: String, author: Option[String], body: String)
