package com.github.mt_sito.mr_scala_libs.utils

import com.github.mt_sito.mr_scala_libs.MrScalaLibsFactoryImpl
import org.scalatest.FlatSpec


/**
 * StringUtil テストスペッククラス。
 */
class StringUtilSpec extends FlatSpec {
	val stringUtil = new MrScalaLibsFactoryImpl().stringUtil


	"trimWide" should "前後の全角スペースを除外する" in {
		assert(stringUtil.trimWide(" \t\r　abc\t\n 　") === "abc")
	}

	it should "除外ファイルのみの場合空文字を返す" in {
		assert(stringUtil.trimWide(" \t\r　\t\n 　") === "")
	}

	"katakanaToHiragana" should "カタカナがひらがなへ変換される" in {
		assert(stringUtil.katakanaToHiragana("カタカナヴヵヶあ") === "かたかなうかけあ")
	}
}
