package com.github.mt_sito.mr_scala_libs

import org.scalatest.FlatSpec


/**
 * mr_scala_libs パッケージオブジェクトテストスペッククラス。
 */
class PackageSpec extends FlatSpec {
	/**
	 * テスト用 close を実装するクラス。
	 */
	class Closeable {
		var closed = false

		def close(): Unit = { closed = true }
	}


	"using" should "スコープを抜けた際に close が呼ばれる" in {
		val closeable = new Closeable

		assert(!closeable.closed)
		using(closeable) { r =>
			assert(!closeable.closed)
		}
		assert(closeable.closed)
	}

	it should "例外発生時スコープを抜けた際に close が呼ばれ例外をスローされる" in {
		val closeable = new Closeable

		intercept[Exception] {
			using(closeable) { r =>
				assert(!closeable.closed)
				throw new Exception
			}
		}
		assert(closeable.closed)
	}

	it should "リソースが null の場合何もしない" in {
		val resource: Closeable = null
		using(resource) { r =>
			assert(r == null)
		}
	}
}
