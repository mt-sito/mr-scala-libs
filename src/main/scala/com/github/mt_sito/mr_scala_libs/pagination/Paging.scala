package com.github.mt_sito.mr_scala_libs.pagination


/**
 * ページングトレイト。
 */
trait Paging[Syntax] {
	/**
	 * 全件数を計算してページオブジェクト作成。
	 *
	 * @param no ページ番号
	 * @param limit 最大件数
	 * @param list リスト
	 * @param proc 全件数取得関数
	 * @return ページングオブジェクト
	 */
	def create[T](no: Int, limit: Int, list: Seq[T])(proc: => Long): Page[T] = {
		val size = list.size
		if (size < limit) Page(no, limit, list, Page.offset(no, limit) + size)
		else Page(no, limit, list, proc)
	}

	/**
	 * SQL シンタックス取得。
	 *
	 * @param no ページ番号
	 * @param limit 最大件数
	 * @return SQL シンタックス
	 */
	def sqlSyntax(no: Int, limit: Int): Syntax
}
