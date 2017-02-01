package com.github.mt_sito.mr_scala_libs.utils

import org.scalatest.FlatSpec


/**
 * NumberUtil テストスペッククラス。
 */
class NumberUtilSpec extends FlatSpec with NumberUtilComponentImpl {
	"isDigit" should "null の場合 false を返す" in {
		val str: String = null
		assert(!numberUtil.isDigit(str))
	}

	it should "空文字の場合 false を返す" in {
		assert(!numberUtil.isDigit(""))
	}

	it should "小数の場合 false を返す" in {
		assert(!numberUtil.isDigit("1.5"))
	}

	it should "文字を含む場合 false を返す" in {
		assert(!numberUtil.isDigit("5L"))
	}

	it should "数字のみ場合 true を返す" in {
		assert(numberUtil.isDigit("3"))
	}

	"isDigit(Option)" should "None の場合 false を返す" in {
		assert(!numberUtil.isDigit(None))
	}

	it should "Some(null) の場合 false を返す" in {
		val str: String = null
		assert(!numberUtil.isDigit(Some(str)))
	}

	it should "空文字の場合 false を返す" in {
		assert(!numberUtil.isDigit(Option("")))
	}

	it should "小数の場合 false を返す" in {
		assert(!numberUtil.isDigit(Option("1.5")))
	}

	it should "文字を含む場合 false を返す" in {
		assert(!numberUtil.isDigit(Option("5L")))
	}

	it should "数字のみ場合 true を返す" in {
		assert(numberUtil.isDigit(Option("3")))
	}
}
