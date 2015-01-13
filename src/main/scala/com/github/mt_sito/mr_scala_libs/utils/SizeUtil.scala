package com.github.mt_sito.mr_scala_libs.utils

import scala.annotation.tailrec


/**
 * サイズユーティリティおブジェクト。
 */
object SizeUtil {
	/** SI 桁 */
	private val SI_DENOMINATOR = 1000
	/** 2 進数 桁 */
	private val BIN_DENOMINATOR = 1024

	/** SI 接頭辞 */
	private val SI = List("B", "kB", "MB", "GB", "TB", "PB", "EB")
	/** 2 進数接頭辞 */
	private val BIN = List("B", "KiB", "MiB", "GiB", "TiB", "PiB", "EiB")

	/** デフォルトフォーマット */
	private val DEFAULT_FORMAT = "%1$#.2f %2$s"


	/**
	 * SI 表記文字列取得。
	 *
	 * @param size サイズ
	 * @param format フォーマット。デフォルト - %1$#.2f %2$s
	 * @return サイズ文字列
	 */
	def siString(size: Long, format: String = DEFAULT_FORMAT): String = mkString(size, format, SI_DENOMINATOR, SI)

	/**
	 * 2 進数表記文字列取得。
	 *
	 * @param size サイズ
	 * @param format フォーマット。デフォルト - %1$#.2f %2$s
	 * @return サイズ文字列
	 */
	def binString(size: Long, format: String = DEFAULT_FORMAT): String = mkString(size, format, BIN_DENOMINATOR, BIN)


	/**
	 * 文字列作成。
	 *
	 * @param size サイズ
	 * @param unit 単位
	 * @param format フォーマット
	 * @return サイズ文字列
	 */
	private def mkString(size: Long, format: String, denominator: Int, units: List[String]): String =
		_mkString(size, format, denominator, units, 0)

	/**
	 * 文字列作成。
	 *
	 * @param size サイズ
	 * @param unit 単位
	 * @param format フォーマット
	 * @param count 階数
	 * @return サイズ文字列
	 */
	@tailrec
	private def _mkString(size: Double, format: String, denominator: Int, units: List[String], count: Int): String = {
		if (size.toLong < denominator) format.format(size, units(count))
		else _mkString(size / denominator, format, denominator, units, count + 1)
	}
}
