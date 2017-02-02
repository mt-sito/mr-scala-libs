package com.github.mt_sito.mr_scala_libs.utils

import java.io.{File, FileNotFoundException}

import com.github.mt_sito.mr_scala_libs.using
import org.scalatest.FlatSpec


/**
 * ResourceUtil テストスペッククラス。
 */
class ResourceUtilSpec extends FlatSpec with ResourceUtilComponentImpl {
	"getSource" should "存在するファイルの場合、指定したファイルのソースを返す" in {
		using(resourceUtil.getSource(
			new File("./src/test/scala/com/github/mt_sito/mr_scala_libs/utils/ResourceUtilSpec.scala"))) { actual =>
			assert(actual != null)
		}
	}

	it should "存在しないファイルの場合、FileNotFoundException をスローする" in {
		intercept[FileNotFoundException] {
			resourceUtil.getSource(new File("./target/Nothing"))
		}
	}


	it should "クラスパス内に存在するファイルの場合、指定したファイルのソースを返す" in {
		using(resourceUtil.getSource(
			new File("com/github/mt_sito/mr_scala_libs/utils/resourceUtilSpec.class"))) { actual =>
			assert(actual != null)
		}
	}
}
