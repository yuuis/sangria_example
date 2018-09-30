trait Container {
  def articleRepository: ArticleRepository
  def commentRepository: CommentRepository
}

object ContainerImpl extends Container {
  override val articleRepository = new ArticleRepository
  override val commentRepository = new CommentRepository
}
