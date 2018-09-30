package models

case class Article(id: String, title: String, author: Option[String], tags: List[String])