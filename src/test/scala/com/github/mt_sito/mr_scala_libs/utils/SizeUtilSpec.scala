package com.github.mt_sito.mr_scala_libs.utils

import org.scalatest.FlatSpec


/**
 * SizeUtil テストスペッククラス。
 */
class SizeUtilSpec extends FlatSpec {
	"siString" should "0 を与えた場合バイト表記を返す" in {
		assert(SizeUtil.siString(0) === "0 B")
	}

	it should "5 を与えた場合バイト表記を返す" in {
		assert(SizeUtil.siString(5) === "5 B")
	}

	it should "1000 を与えた場合キロバイト表記を返す" in {
		assert(SizeUtil.siString(1000) === "1 kB")
	}

	it should "1024 を与えた場合キロバイト表記を返す" in {
		assert(SizeUtil.siString(1024) === "1.02 kB")
	}

	it should "1000000 を与えた場合メガバイト表記を返す" in {
		assert(SizeUtil.siString(1000000) === "1 MB")
	}

	it should "1048576 を与えた場合メガバイト表記を返す" in {
		assert(SizeUtil.siString(1048576) === "1.05 MB")
	}

	it should "1000000000 を与えた場合ギガバイト表記を返す" in {
		assert(SizeUtil.siString(1000000000) === "1 GB")
	}

	it should "1073741824 を与えた場合ギガバイト表記を返す" in {
		assert(SizeUtil.siString(1073741824) === "1.07 GB")
	}

	it should "1000000000000 を与えた場合テラバイト表記を返す" in {
		assert(SizeUtil.siString(1000000000000L) === "1 TB")
	}

	it should "1099511627776 を与えた場合テラバイト表記を返す" in {
		assert(SizeUtil.siString(1099511627776L) === "1.1 TB")
	}

	it should "1000000000000000 を与えた場合ペタバイト表記を返す" in {
		assert(SizeUtil.siString(1000000000000000L) === "1 PB")
	}

	it should "1125899906842624 を与えた場合テラバイト表記を返す" in {
		assert(SizeUtil.siString(1125899906842624L) === "1.13 PB")
	}

	it should "1000000000000000000 を与えた場合エクサバイト表記を返す" in {
		assert(SizeUtil.siString(1000000000000000000L) === "1 EB")
	}

	it should "1152921504606846976 を与えた場合エクサバイト表記を返す" in {
		assert(SizeUtil.siString(1152921504606846976L) === "1.15 EB")
	}

	it should "Long の最大値を与えた場合エクサバイト表記を返す" in {
		assert(SizeUtil.siString(Long.MaxValue) === "9.22 EB")
	}

	"binString" should "0 を与えた場合バイト表記を返す" in {
		assert(SizeUtil.binString(0) === "0 B")
	}

	it should "5 を与えた場合バイト表記を返す" in {
		assert(SizeUtil.binString(5) === "5 B")
	}

	it should "1000 を与えた場合バイト表記を返す" in {
		assert(SizeUtil.binString(1000) === "1000 B")
	}

	it should "1024 を与えた場合キビバイト表記を返す" in {
		assert(SizeUtil.binString(1024) === "1 KiB")
	}

	it should "1000000 を与えた場合キビバイト表記を返す" in {
		assert(SizeUtil.binString(1000000) === "976.56 KiB")
	}

	it should "1048576 を与えた場合メビバイト表記を返す" in {
		assert(SizeUtil.binString(1048576) === "1 MiB")
	}

	it should "1000000000 を与えた場合メビバイト表記を返す" in {
		assert(SizeUtil.binString(1000000000) === "953.67 MiB")
	}

	it should "1073741824 を与えた場合ギビバイト表記を返す" in {
		assert(SizeUtil.binString(1073741824) === "1 GiB")
	}

	it should "1000000000000 を与えた場合ギビバイト表記を返す" in {
		assert(SizeUtil.binString(1000000000000L) === "931.32 GiB")
	}

	it should "1099511627776 を与えた場合テビバイト表記を返す" in {
		assert(SizeUtil.binString(1099511627776L) === "1 TiB")
	}

	it should "1000000000000000 を与えた場合テビバイト表記を返す" in {
		assert(SizeUtil.binString(1000000000000000L) === "909.49 TiB")
	}

	it should "1125899906842624 を与えた場合ペビバイト表記を返す" in {
		assert(SizeUtil.binString(1125899906842624L) === "1 PiB")
	}

	it should "1000000000000000000 を与えた場合ペビバイト表記を返す" in {
		assert(SizeUtil.binString(1000000000000000000L) === "888.18 PiB")
	}

	it should "1152921504606846976 を与えた場合エクスビバイト表記を返す" in {
		assert(SizeUtil.binString(1152921504606846976L) === "1 EiB")
	}

	it should "Long の最大値を与えた場合エクサバイト表記を返す" in {
		assert(SizeUtil.binString(Long.MaxValue) === "8 EiB")
	}
}
