package com.github.mt_sito.mr_scala_libs.utils

import com.github.mt_sito.mr_scala_libs.MrScalaLibsFactory


/**
 * 文字列ユーティリティトレイト。
 */
trait StringUtil {
	/**
	 * 2 バイトスペースを含めてトリム。
	 *
	 * @param str 文字列
	 * @return トリムされた文字列
	 */
	def trimWide(str: String): String

	/**
	 * 全角カタカナを全角ひらがなへ変換。
	 *
	 * @param str 文字列
	 * @return 変換された文字列
	 */
	def katakanaToHiragana(str: String) : String
}


/**
 * 文字列ユーティリティ実装クラス。
 *
 * @param factory ファクトリクラス
 */
class StringUtilImpl(factory: MrScalaLibsFactory) extends StringUtil {
	/** {@inheritDoc} */
	override def trimWide(str: String): String = {
		assert(str != null, "str is null")

		var start = 0
		var end = str.length
		val chars = str.toCharArray()

		// 制御文字 + スペース (文字コード順) + 2 バイトスペース
		def isTrimChar(ch: Char): Boolean = ch <= ' ' || ch == '　'

		while (start < end && isTrimChar(chars(start))) start += 1
		while (start < end && isTrimChar(chars(end - 1))) end -= 1

		str.substring(start, end)
	}

	/** {@inheritDoc} */
	override def katakanaToHiragana(str: String) : String = {
		val sb = new StringBuffer(str.length)

		str.foreach { ch =>
			if (ch >= 'ァ' && ch <= 'ン') sb.append((ch - 'ァ' + 'ぁ').toChar)
			else if (ch == 'ヵ') sb.append('か')
			else if (ch == 'ヶ') sb.append('け')
			else if (ch == 'ヴ') sb.append('う')
			else sb.append(ch)
		}
		return sb.toString;
	}
}
