package com.github.mt_sito.mr_scala_libs.utils

import java.text.DecimalFormat
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

	/** フォーマット */
	private val FORMAT = "0.##"


	/**
	 * SI 表記文字列取得。
	 *
	 * @param size サイズ
	 * @return サイズ文字列
	 */
	def siString(size: Long): String = mkString(size, SI_DENOMINATOR, SI)

	/**
	 * 2 進数表記文字列取得。
	 *
	 * @param size サイズ
	 * @return サイズ文字列
	 */
	def binString(size: Long): String = mkString(size, BIN_DENOMINATOR, BIN)


	/**
	 * 文字列作成。
	 *
	 * @param size サイズ
	 * @param unit 単位
	 * @return サイズ文字列
	 */
	private def mkString(size: Long, denominator: Int, units: List[String]): String =
		_mkString(size, denominator, units, 0)

	/**
	 * 文字列作成。
	 *
	 * @param size サイズ
	 * @param unit 単位
	 * @param count 階数
	 * @return サイズ文字列
	 */
	@tailrec
	private def _mkString(size: Double, denominator: Int, units: List[String], count: Int): String = {
		if (size.toLong < denominator) new DecimalFormat(FORMAT).format(size) + " " + units(count)
		else _mkString(size / denominator, denominator, units, count + 1)
	}
}
