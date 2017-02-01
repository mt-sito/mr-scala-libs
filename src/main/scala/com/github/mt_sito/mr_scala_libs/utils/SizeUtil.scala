package com.github.mt_sito.mr_scala_libs.utils

import java.text.DecimalFormat

import scala.annotation.tailrec


/**
 * サイズユーティリティオブジェクト。
 */
trait SizeUtil {
	/**
	 * SI 表記文字列取得。
	 *
	 * @param size サイズ
	 * @return サイズ文字列
	 */
	def siString(size: Long): String

	/**
	 * 2 進数表記文字列取得。
	 *
	 * @param size サイズ
	 * @return サイズ文字列
	 */
	def binString(size: Long): String
}


/**
 * サイズユーティリティ実装クラス。
 */
class SizeUtilImpl extends SizeUtil {
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


	/** {@inheritDoc} */
	def siString(size: Long): String = mkString(size, SI_DENOMINATOR, SI)

	/** {@inheritDoc} */
	def binString(size: Long): String = mkString(size, BIN_DENOMINATOR, BIN)


	/** {@inheritDoc} */
	private def mkString(size: Long, denominator: Int, units: List[String]): String =
		_mkString(size, denominator, units, 0)

	/** {@inheritDoc} */
	@tailrec
	private def _mkString(size: Double, denominator: Int, units: List[String], count: Int): String = {
		if (size.toLong < denominator) new DecimalFormat(FORMAT).format(size) + " " + units(count)
		else _mkString(size / denominator, denominator, units, count + 1)
	}
}


/**
 * サイズユーティリティコンポーネントトレイト。
 */
trait SizeUtilComponent {
	/** サイズユーティリティ */
	val sizeUtil: SizeUtil
}


/**
 * サイズユーティリティコンポーネント実装トレイト。
 */
trait SizeUtilComponentImpl {
	/** サイズユーティリティ */
	val sizeUtil: SizeUtil = new SizeUtilImpl
}
