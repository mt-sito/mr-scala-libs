package com.github.mt_sito.mr_scala_libs.utils

import java.io.File

import scala.annotation.tailrec

import com.github.mt_sito.mr_scala_libs.MrScalaLibsFactory
import com.github.mt_sito.mr_scala_libs.platforms.OS.WINDOWS


/**
 * パスユーティリティトレイト。
 */
trait PathUtil {
	/**
	 * ファイル名取得。
	 *
	 * @param path パス
	 * @param separator 区切り文字
	 * @return ファイル名
	 */
	def fileName(path: String, separator: String): String

	/**
	 * ファイル名取得。
	 *
	 * @param file ファイル
	 * @param separator 区切り文字
	 * @return ファイル名
	 */
	def fileName(file: File, separator: String): String

	/**
	 * 親ディレクトリパス取得。
	 *
	 * @param path パス
	 * @param separator 区切り文字
	 * @return 親ディレクトリパス
	 */
	def parentPath(path: String, separator: String): String

	/**
	 * 末尾の区切り文字削除。
	 *
	 * @param path パス
	 * @param separator 区切り文字
	 * @return 末尾の区切り文字を削除したパス
	 */
	def removeSeparator(path: String, separator: String): String

	/**
	 * 名前要素数取得。
	 *
	 * @param path パス
	 * @param separator 区切り文字
	 * @return 名前要素数
	 */
	def nameCount(path: String, separator: String): Int

	/**
	 * 名前要素数取得。
	 *
	 * @param file ファイル
	 * @param separator 区切り文字
	 * @return 名前要素数
	 */
	def nameCount(file: File, separator: String): Int
}


/**
 * パスユーティリティ実装クラス。
 *
 * @param factory ファクトリクラス
 */
class PathUtilImpl(factory: MrScalaLibsFactory) extends PathUtil {
	/** {@inheritDoc} */
	override def fileName(path: String, separator: String): String = {
		require(path != null)
		require(separator != null)

		val p = removeSeparator(path, separator)
		val pos = p.lastIndexOf(separator)
		if (pos == -1) return ""

		if (separator == WINDOWS.pathSeparator) {
			val prefixLength = WINDOWS.pathPrefixLength(p)
			if (prefixLength >= p.length) return ""
			else if (pos < prefixLength) return p.substring(prefixLength)
		}

		p.substring(pos + 1)
	}

	/** {@inheritDoc} */
	override def fileName(file: File, separator: String): String = fileName(file.getPath, separator)

	/** {@inheritDoc} */
	override def parentPath(path: String, separator: String): String = {
		require(path != null)
		require(separator != null)

		val p = removeSeparator(path, separator)
		val pos = p.lastIndexOf(separator)
		if (pos == -1) return ""

		if (separator == WINDOWS.pathSeparator) {
			val prefixLength = WINDOWS.pathPrefixLength(p)
			if (pos < prefixLength) return ""
		}

		p.substring(0, pos)
	}

	/** {@inheritDoc} */
	override def removeSeparator(path: String, separator: String): String = _removeSeparator(path, separator)

	/** {@inheritDoc} */
	override def nameCount(path: String, separator: String): Int = _nameCount(path, separator, 0)

	/** {@inheritDoc} */
	override def nameCount(file: File, separator: String): Int = nameCount(file.getPath, separator)


	/**
	 * 末尾の区切り文字削除。
	 *
	 * @param path パス
	 * @param separator 区切り文字
	 * @return 末尾の区切り文字を削除したパス
	 */
	@tailrec
	private def _removeSeparator(path: String, separator: String): String = {
		require(path != null)
		require(separator != null)

		val len = path.length

		if (!path.endsWith(separator) || len == 0) path
		else _removeSeparator(path.substring(0, len - 1), separator)
	}

	/**
	 * 名前要素数取得。
	 *
	 * @param path パス
	 * @param separator 区切り文字
	 * @return 名前要素数
	 */
	@tailrec
	private def _nameCount(path: String, separator: String, count: Int): Int = {
		val name = fileName(path, separator)

		if (name.isEmpty) count
		else _nameCount(parentPath(path, separator), separator, count + 1)
	}
}
