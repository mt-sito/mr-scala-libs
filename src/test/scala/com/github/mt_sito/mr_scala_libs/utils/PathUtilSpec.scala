package com.github.mt_sito.mr_scala_libs.utils

import java.io.File

import com.github.mt_sito.mr_scala_libs.MrScalaLibsFactoryImpl
import org.scalatest.FlatSpec


/**
 * PathUtil テストスペッククラス。
 */
class PathUtilSpec extends FlatSpec {
	val pathUtil = new MrScalaLibsFactoryImpl().pathUtil


	"fileName" should "ファイル名を返す" in {
		assert(pathUtil.fileName("""D:\data\aaa.txt""", "\\") === "aaa.txt")
	}

	it should "Linux の場合ファイル名を返す" in {
		assert(pathUtil.fileName("""/data/aaa.txt""", "/") === "aaa.txt")
	}

	it should "セパレータで終わる場合ディレクトリ名を返す" in {
		assert(pathUtil.fileName("""D:\data\""", "\\") === "data")
	}

	it should "ルートの場合空文字を返す" in {
		assert(pathUtil.fileName("/", "/") === "")
	}

	it should "ドライブ文字の場合空文字を返す" in {
		assert(pathUtil.fileName("D:\\", "\\") === "")
	}

	it should "UNC でルートの場合ホスト名を返す" in {
		assert(pathUtil.fileName("""\\nas01""", "\\") === "nas01")
	}

	it should "UNC でルートでセパレータで終わる場合ホスト名を返す" in {
		assert(pathUtil.fileName("""\\nas01\""", "\\") === "nas01")
	}

	it should "空文字の場合空文字を返す" in {
		assert(pathUtil.fileName("", "\\") === "")
	}

	it should "パス null の場合例外" in {
		intercept[IllegalArgumentException] {
			val path: String = null
			pathUtil.fileName(path, "\\")
		}
	}

	it should "セパレータが null の場合例外" in {
		intercept[IllegalArgumentException] {
			pathUtil.fileName("""D:\data\aaa.txt""", null)
		}
	}

	it should "Windows でドライブレターがなく UTC でもない場合からもw時を返す" in {
		assert(pathUtil.fileName("D\\a", "\\") === "")
	}

	"fileName(File)" should "ファイル名を返す" in {
		assert(pathUtil.fileName(new File("""D:\data\aaa.txt"""), "\\") === "aaa.txt")
	}

	"parentPath" should "親ディレクトリパスを返す" in {
		assert(pathUtil.parentPath("""D:\data\aaa.txt""", "\\") === """D:\data""")
	}

	it should "Linux の場合親ディレクトリパスを返す" in {
		assert(pathUtil.parentPath("""/data/aaa.txt""", "/") === """/data""")
	}

	it should "セパレータで終わる場合親ディレクトリパスを返す" in {
		assert(pathUtil.parentPath("""D:\data\data2""", "\\") === """D:\data""")
	}

	it should "ルートの場合空文字を返す" in {
		assert(pathUtil.parentPath("/", "/") === "")
	}

	it should "ドライブ文字の場合空文字を返す" in {
		assert(pathUtil.parentPath("D:\\", "\\") === "")
	}

	it should "UNC でルートの場合空文字を返す" in {
		assert(pathUtil.parentPath("""\\nas01""", "\\") === "")
	}

	it should "UNC でルートでセパレータで終わる場合空文字を返す" in {
		assert(pathUtil.parentPath("""\\nas01\""", "\\") === "")
	}

	it should "空文字の場合空文字を返す" in {
		assert(pathUtil.parentPath("", "\\") === "")
	}

	it should "パス null の場合例外" in {
		intercept[IllegalArgumentException] {
			val path: String = null
			pathUtil.parentPath(path, "\\")
		}
	}

	it should "セパレータが null の場合例外" in {
		intercept[IllegalArgumentException] {
			pathUtil.parentPath("""D:\data\aaa.txt""", null)
		}
	}

	"removeSeparator" should "末尾が区切り文字の場合削除したものを返す" in {
		assert(pathUtil.removeSeparator("""D:\data\data1\\\\""", "\\") === """D:\data\data1""")
	}

	it should "末尾が区切り文字でない場合そのまま返す" in {
		assert(pathUtil.removeSeparator("""D:\data\data2""", "\\") === """D:\data\data2""")
	}

	it should "空文字の場合空文字を返す" in {
		assert(pathUtil.removeSeparator("", "\\") === "")
	}

	"nameCount" should "名前要素数を返す" in {
		assert(pathUtil.nameCount("""D:\data\aaa.txt""", "\\") === 2)
	}

	it should "セパレータで終わる場合それを含めた数を返す" in {
		assert(pathUtil.nameCount("""D:\data\data2""", "\\") === 2)
	}

	it should "ルートの場合 0 を返す" in {
		assert(pathUtil.nameCount("/", "/") === 0)
	}

	it should "ドライブ文字の場合 0 を返す" in {
		assert(pathUtil.nameCount("D:\\", "\\") === 0)
	}

	it should "UNC でルートの場合 1 を返す" in {
		assert(pathUtil.nameCount("""\\nas01""", "\\") === 1)
	}

	it should "UNC でルートでセパレータで終わる場合 1 を返す" in {
		assert(pathUtil.nameCount("""\\nas01\""", "\\") === 1)
	}

	it should "UNC で開始記号のみの場合 0 を返す" in {
		assert(pathUtil.nameCount("""\\""", "\\") === 0)
	}

	it should "空文字の場合 0 を返す" in {
		assert(pathUtil.nameCount("", "\\") === 0)
	}

	it should "パス null の場合例外" in {
		intercept[IllegalArgumentException] {
			val path: String = null
			pathUtil.nameCount(path, "\\")
		}
	}

	it should "セパレータが null の場合例外" in {
		intercept[IllegalArgumentException] {
			pathUtil.nameCount("""D:\data\aaa.txt""", null)
		}
	}

	"nameCount(File)" should "名前要素数を返す" in {
		assert(pathUtil.nameCount(new File("""D:\data\aaa.txt"""), "\\") === 2)
	}
}
