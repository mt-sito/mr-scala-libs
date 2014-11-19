package com.github.mt_sito.mr_scala_libs.utils

import org.scalatest.FlatSpec


/**
 * SecureUtil テストスペッククラス。
 */
class SecureUtilSpec extends FlatSpec {
	"randomToken32" should "毎回違う識別子を返す" in {
		assert(SecureUtil.randomToken32() != SecureUtil.randomToken32())
	}

	it should "32 文字識別子を返す" in {
		assert(SecureUtil.randomToken32().length === 32)
	}

	"randomToken64" should "毎回違う識別子を返す" in {
		assert(SecureUtil.randomToken64() != SecureUtil.randomToken64())
	}

	it should "64 文字識別子を返す" in {
		assert(SecureUtil.randomToken64().length === 64)
	}

	"hashPassword" should "パスワードをハッシュ化する" in {
		val password = "123456"
		val salt = SecureUtil.randomToken32
		val actual = SecureUtil.hashPassword(password, salt, version = 2)
		assert(actual != password)
		assert(actual.startsWith(s"2@$salt@"))
	}

	it should "パスワード、ソルト、ストレッチ回数が同じ場合同じハッシュを返す" in {
		val password = "123456"
		val salt = SecureUtil.randomToken64
		val stretchCount = 9999
		assert(SecureUtil.hashPassword(password, salt, stretchCount) ===
			SecureUtil.hashPassword(password, salt, stretchCount))
	}

	it should "ソルトが違う場合異なるハッシュを返す" in {
		val password = "123456"
		assert(SecureUtil.hashPassword(password, SecureUtil.randomToken32) !=
			SecureUtil.hashPassword(password, SecureUtil.randomToken32))
	}

	it should "ストレッチ回数が違う場合異なるハッシュを返す" in {
		val password = "123456"
		val salt = SecureUtil.randomToken32
		assert(SecureUtil.hashPassword(password, salt, 9999) !=
			SecureUtil.hashPassword(password, salt, 10000))
	}


	"verifyPassword" should "パスワードが一致すれば true" in {
		val password = "1"
		val hashPassword = "1@30c3fa14defc4d0cb94d660063a46d1f@BZaHD+bkEIRw7Fg7k4thsxP8epys6m2c5ervFAgBIegnuF5uFzCttIqW3Azzc5Kk1rEvRN4apO4eUcLs+AdpQA=="

		assert(SecureUtil.verifyPassword(password, hashPassword, 9999, "SHA-512", 1))
	}

	it should "バージョンが異なれば false" in {
		val password = "1"
		val hashPassword = "1@30c3fa14defc4d0cb94d660063a46d1f@BZaHD+bkEIRw7Fg7k4thsxP8epys6m2c5ervFAgBIegnuF5uFzCttIqW3Azzc5Kk1rEvRN4apO4eUcLs+AdpQA=="

		assert(!SecureUtil.verifyPassword(password, hashPassword, 9999, "SHA-512", 2))
	}

	it should "ソルトが異なれば false" in {
		val password = "1"
		val hashPassword = "1@salt@BZaHD+bkEIRw7Fg7k4thsxP8epys6m2c5ervFAgBIegnuF5uFzCttIqW3Azzc5Kk1rEvRN4apO4eUcLs+AdpQA=="

		assert(!SecureUtil.verifyPassword(password, hashPassword, 9999, "SHA-512", 2))
	}

	it should "引数省略した場合デフォルト値を使用" in {
		val password = "1"
		val hashPassword = "1@30c3fa14defc4d0cb94d660063a46d1f@BZaHD+bkEIRw7Fg7k4thsxP8epys6m2c5ervFAgBIegnuF5uFzCttIqW3Azzc5Kk1rEvRN4apO4eUcLs+AdpQA=="

		assert(SecureUtil.verifyPassword(password, hashPassword))
	}
}
