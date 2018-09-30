package schema

import repository.{ArticleRepository, CommentRepository, UserRepository}

trait Container {
  def articleRepository: ArticleRepository
  def commentRepository: CommentRepository
  def userRepository: UserRepository
}

object ContainerImpl extends Container {
  override val articleRepository = new ArticleRepository
  override val commentRepository = new CommentRepository
  override val userRepository = new UserRepository
}
