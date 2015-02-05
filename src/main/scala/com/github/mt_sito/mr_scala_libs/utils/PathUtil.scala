package com.github.mt_sito.mr_scala_libs.utils

import com.github.mt_sito.mr_scala_libs.platforms.OS.WINDOWS
import java.io.File
import scala.annotation.tailrec


/**
 * パスユーティリティオブジェクト。
 */
object PathUtil {
	/**
	 * ファイル名取得。
	 *
	 * @param path パス
	 * @param separator 区切り文字
	 * @return ファイル名
	 */
	@tailrec
	def fileName(path: String, separator: String): String = {
		require(path != null)
		require(separator != null)

		val len = path.length

		if (path.endsWith(separator) && len > 1) fileName(path.substring(0, len - 1), separator)
		else {
			val pos = path.lastIndexOf(separator)
			if (pos == -1) return ""

			if (separator == WINDOWS.pathSeparator) {
				val prefixLength = WINDOWS.pathPrefixLength(path)
				if (prefixLength >= len) return ""
				else if (pos < prefixLength) return path.substring(prefixLength)
			}

			path.substring(pos + 1)
		}
	}

	/**
	 * ファイル名取得。
	 *
	 * @param file ファイル
	 * @param separator 区切り文字
	 * @return ファイル名
	 */
	def fileName(file: File, separator: String): String = fileName(file.getPath, separator)

	/**
	 * 親ディレクトリパス取得。
	 *
	 * @param path パス
	 * @param separator 区切り文字
	 * @return 親ディレクトリパス
	 */
	@tailrec
	def parentPath(path: String, separator: String): String = {
		require(path != null)
		require(separator != null)

		val len = path.length

		if (path.endsWith(separator) && len > 1) parentPath(path.substring(0, len - 1), separator)
		else {
			val pos = path.lastIndexOf(separator)
			if (pos == -1) return ""

			if (separator == WINDOWS.pathSeparator) {
				val prefixLength = WINDOWS.pathPrefixLength(path)
				if (pos < prefixLength) return ""
			}

			path.substring(0, pos)
		}
	}

	/**
	 * 名前要素数取得。
	 *
	 * @param path パス
	 * @param separator 区切り文字
	 * @return 名前要素数
	 */
	def nameCount(path: String, separator: String): Int = _nameCount(path, separator, 0)

	/**
	 * 名前要素数取得。
	 *
	 * @param file ファイル
	 * @param separator 区切り文字
	 * @return 名前要素数
	 */
	def nameCount(file: File, separator: String): Int = nameCount(file.getPath, separator)


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
