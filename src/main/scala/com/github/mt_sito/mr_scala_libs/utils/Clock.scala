package com.github.mt_sito.mr_scala_libs.utils

import java.time.{ZoneId, ZonedDateTime}

import com.github.mt_sito.mr_scala_libs.MrScalaLibsFactory


/**
 * 日時トレイト。
 */
trait Clock {
	/**
	 * UTC 現在日時取得。
	 *
	 * @return 現在日時
	 */
	def now(): ZonedDateTime

	/**
	 * ローカル現在日時取得。
	 *
	 * @return 現在日時
	 */
	def nowLocal(): ZonedDateTime
}


/**
 * 日時実装クラス。
 *
 * @param factory ファクトリクラス
 */
class ClockImpl(factory: MrScalaLibsFactory) extends Clock {
	/** {@inheritDoc} */
	override def now(): ZonedDateTime = ZonedDateTime.now(ZoneId.of("UTC"))

	/** {@inheritDoc} */
	override def nowLocal(): ZonedDateTime = ZonedDateTime.now(ZoneId.systemDefault)
}
