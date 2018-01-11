package com.github.mt_sito.mr_scala_libs.utils

import java.time.{LocalDateTime, ZoneId, ZonedDateTime}

import com.github.mt_sito.mr_scala_libs.MrScalaLibsFactoryImpl
import org.scalatest.FlatSpec


/**
 * Clock テストスペッククラス。
 */
class ClockSpec extends FlatSpec {
	val clock = MrScalaLibsFactoryImpl.clock


	"now" should "UTC の現在日時を返す" in {
		val dt1 = ZonedDateTime.now(ZoneId.of("UTC"))
		val actual = clock.now()
		val dt2 = ZonedDateTime.now(ZoneId.of("UTC"))

		assert(dt1.compareTo(actual) <= 0)
		assert(dt2.compareTo(actual) >= 0)
	}

	"nowLocal" should "ローカル現在日時を返す" in {
		val dt1 = LocalDateTime.now().atZone(ZoneId.systemDefault())
		val actual = clock.nowLocal()
		val dt2 = LocalDateTime.now().atZone(ZoneId.systemDefault())

		assert(dt1.compareTo(actual) <= 0)
		assert(dt2.compareTo(actual) >= 0)
	}
}
