package com.github.mt_sito.mr_scala_libs.pagination


/**
 * ページクラス。
 *
 * @param no ページ番号
 * @param limit 最大件数
 * @param list リスト
 * @param allCount 全件数
 */
case class Page[T](no: Int, limit: Int, list: Seq[T] = Nil, allCount: Long = 0L) {
	require(no > 0, "no <= 0")
	require(limit > 0, "limit <= 0")

	/** OFFSET */
	lazy val offset = Page.offset(no, limit)

	/** 前ページがあるか */
	lazy val hasPrev: Boolean = no > 1
	/** 次ページがあるか */
	lazy val hasNext: Boolean = offset + limit < allCount

	/** 前ページ番号 */
	lazy val prevNo: Int = if (hasPrev) no - 1 else no
	/** 次ページ番号 */
	lazy val nextNo: Int = if (hasNext) no + 1 else no

	/** 最大ページ番号 */
	lazy val maxPage: Int = ((allCount - 1) / limit + 1).toInt


	/**
	 * ページング幅取得。
	 *
	 * @param maxRange 最大幅数
	 * @return 幅
	 */
	def ranges(maxRange: Int = 10): Seq[Int] = {
		val padding = maxRange / 2
		val shift = if (maxRange % 2 == 0) 1 else 0

		val tmp = maxPage - padding * 2 + shift
		val minTmp = if (tmp <= 0) 1 else tmp
		val min = if (no <= padding) 1 else if (no + padding <= maxPage) no - padding + shift else minTmp
		val tmp2 = min + padding * 2 - shift
		val max = if (tmp2 <= maxPage) tmp2 else maxPage
		Range.inclusive(min, max).toSeq
	}
}


/**
 * ページオブジェクト。
 */
object Page {
	/**
	 * オフセット取得。
	 *
	 * @param no ページ番号
	 * @param limit 最大件数
	 * @return オフセット
	 */
	def offset(no: Int, limit: Int): Long = (no - 1) * limit.toLong
}
