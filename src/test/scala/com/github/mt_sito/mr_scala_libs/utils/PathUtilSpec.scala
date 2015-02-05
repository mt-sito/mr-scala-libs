package com.github.mt_sito.mr_scala_libs.utils

import org.scalatest.FlatSpec


/**
 * PathUtil テストスペッククラス。
 */
class PathUtilSpec extends FlatSpec {
	"fileName" should "ファイル名を返す" in {
		assert(PathUtil.fileName("""D:\data\aaa.txt""", "\\") === "aaa.txt")
	}

	it should "セパレータで終わる場合ディレクトリ名を返す" in {
		assert(PathUtil.fileName("""D:\data\""", "\\") === "data")
	}

	it should "ルートの場合空文字を返す" in {
		assert(PathUtil.fileName("/", "/") === "")
	}

	it should "ドライブ文字の場合空文字を返す" in {
		assert(PathUtil.fileName("D:\\", "\\") === "")
	}

	it should "UNC でルートの場合ホスト名を返す" in {
		assert(PathUtil.fileName("""\\nas01""", "\\") === "nas01")
	}

	it should "UNC でルートでセパレータで終わる場合ホスト名を返す" in {
		assert(PathUtil.fileName("""\\nas01\""", "\\") === "nas01")
	}

	it should "空文字の場合空文字を返す" in {
		assert(PathUtil.fileName("", "\\") === "")
	}

	it should "パス null の場合例外" in {
		intercept[IllegalArgumentException] {
			val path: String = null
			PathUtil.fileName(path, "\\")
		}
	}

	it should "セパレータが null の場合例外" in {
		intercept[IllegalArgumentException] {
			PathUtil.fileName("""D:\data\aaa.txt""", null)
		}
	}

	"fileName(File)" should "ファイル名を返す" in {
		assert(PathUtil.fileName("""D:\data\aaa.txt""", "\\") === "aaa.txt")
	}

	"parentPath" should "親ディレクトリパスを返す" in {
		assert(PathUtil.parentPath("""D:\data\aaa.txt""", "\\") === """D:\data""")
	}

	it should "セパレータで終わる場合親ディレクトリパスを返す" in {
		assert(PathUtil.parentPath("""D:\data\data2""", "\\") === """D:\data""")
	}

	it should "ルートの場合空文字を返す" in {
		assert(PathUtil.parentPath("/", "/") === "")
	}

	it should "ドライブ文字の場合空文字を返す" in {
		assert(PathUtil.parentPath("D:\\", "\\") === "")
	}

	it should "UNC でルートの場合空文字を返す" in {
		assert(PathUtil.parentPath("""\\nas01""", "\\") === "")
	}

	it should "UNC でルートでセパレータで終わる場合空文字を返す" in {
		assert(PathUtil.parentPath("""\\nas01\""", "\\") === "")
	}

	it should "空文字の場合空文字を返す" in {
		assert(PathUtil.parentPath("", "\\") === "")
	}

	it should "パス null の場合例外" in {
		intercept[IllegalArgumentException] {
			val path: String = null
			PathUtil.parentPath(path, "\\")
		}
	}

	it should "セパレータが null の場合例外" in {
		intercept[IllegalArgumentException] {
			PathUtil.parentPath("""D:\data\aaa.txt""", null)
		}
	}

	"nameCount" should "名前要素数を返す" in {
		assert(PathUtil.nameCount("""D:\data\aaa.txt""", "\\") === 2)
	}

	it should "セパレータで終わる場合それを含めた数を返す" in {
		assert(PathUtil.nameCount("""D:\data\data2""", "\\") === 2)
	}

	it should "ルートの場合 0 を返す" in {
		assert(PathUtil.nameCount("/", "/") === 0)
	}

	it should "ドライブ文字の場合 0 を返す" in {
		assert(PathUtil.nameCount("D:\\", "\\") === 0)
	}

	it should "UNC でルートの場合 1 を返す" in {
		assert(PathUtil.nameCount("""\\nas01""", "\\") === 1)
	}

	it should "UNC でルートでセパレータで終わる場合 1 を返す" in {
		assert(PathUtil.nameCount("""\\nas01\""", "\\") === 1)
	}

	it should "UNC で開始記号のみの場合 0 を返す" in {
		assert(PathUtil.nameCount("""\\""", "\\") === 0)
	}

	it should "空文字の場合 0 を返す" in {
		assert(PathUtil.nameCount("", "\\") === 0)
	}

	it should "パス null の場合例外" in {
		intercept[IllegalArgumentException] {
			val path: String = null
			PathUtil.nameCount(path, "\\")
		}
	}

	it should "セパレータが null の場合例外" in {
		intercept[IllegalArgumentException] {
			PathUtil.nameCount("""D:\data\aaa.txt""", null)
		}
	}
}
