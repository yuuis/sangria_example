object ContainerImpl extends Container {
  override val articleRepository = new ArticleRepository
  override val commentRepository = new CommentRepository
}
