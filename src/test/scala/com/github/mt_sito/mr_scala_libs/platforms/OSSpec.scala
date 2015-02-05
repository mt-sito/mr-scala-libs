package com.github.mt_sito.mr_scala_libs.platforms

import org.scalatest.FlatSpec


/**
 * OS テストスペッククラス。
 */
class OSSpec extends FlatSpec {
	"apply" should "1 の場合 WINDOWS を返す" in {
		assert(OS(1) === OS.WINDOWS)
	}

	it should "2 の場合 LINUX を返す" in {
		assert(OS(2) === OS.LINUX)
	}

	it should "3 の場合 UNIX を返す" in {
		assert(OS(3) === OS.UNIX)
	}

	it should "4 の場合 MAC_OS を返す" in {
		assert(OS(4) === OS.MAC_OS)
	}

	"values" should "全 OS を返す" in {
		assert(OS.values === Seq(OS.WINDOWS, OS.LINUX, OS.UNIX, OS.MAC_OS))
	}

	"pathSeparator" should "WINDOWS の場合 \\ を返す" in {
		assert(OS.WINDOWS.pathSeparator === "\\")
	}

	it should "WINDOWS 以外 の場合 / を返す" in {
		OS.values.collect { case os: OS if (os != OS.WINDOWS) => assert(os.pathSeparator === "/", "OS is " + os) }
	}

	"pathPrefixLength" should "WINDOWS でドライブの場合 3 を返す" in {
		assert(OS.WINDOWS.pathPrefixLength("""C:\""") === 3)
	}

	it should "WINDOWS で UNC の場合 3 を返す" in {
		assert(OS.WINDOWS.pathPrefixLength("""\\MYPC\C$\""") === 2)
	}

	it should "WINDOWS 以外 の場合 / を返す" in {
		OS.values.collect { case os: OS if (os != OS.WINDOWS) => assert(os.pathPrefixLength("/tmp") === 1, "OS is " + os) }
	}
}
