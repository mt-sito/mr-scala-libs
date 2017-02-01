package com.github.mt_sito.mr_scala_libs.utils


/**
 * 数値ユーティリティトレイト。
 */
trait NumberUtil {
	/**
	 * 指定された文字列が数字か。<br />
	 * <pre>
	 *  isDigit(null) == false
	 *  isDigit("") == false
	 *  isDigit("1.5") == false
	 *  isDigit("5L") == false
	 *  isDigit("3") == true
	 */
	def isDigit(str: String): Boolean

	/**
	 * 指定された文字列が数字か。<br />
	 * <pre>
	 *  isDigit(None) == false
	 *  isDigit(Some(null)) == false
	 *  isDigit(Some("")) == false
	 *  isDigit(Some("1.5")) == false
	 *  isDigit(Some("5L")) == false
	 *  isDigit(Some("3")) == true
	 */
	def isDigit(str: Option[String]): Boolean
}


/**
 * 数値ユーティリティ実装クラス。
 */
class NumberUtilImpl extends NumberUtil {
	/** {@inheritDoc} */
	override def isDigit(str: String): Boolean = if (str == null || str.isEmpty) false else str.forall(_.isDigit)

	/** {@inheritDoc} */
	override def isDigit(str: Option[String]): Boolean = str.map(isDigit(_)).getOrElse(false)
}


/**
 * 数値ユーティリティコンポーネントトレイト。
 */
trait NumberUtilComponent {
	/** 数値ユーティリティ */
	val numberUtil: NumberUtil
}


/**
 * 数値ユーティリティコンポーネント実装トレイト。
 */
trait NumberUtilComponentImpl {
	/** 数値ユーティリティ */
	val numberUtil: NumberUtil = new NumberUtilImpl
}
