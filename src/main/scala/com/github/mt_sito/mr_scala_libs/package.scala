package com.github.mt_sito

import scala.language.reflectiveCalls


package object mr_scala_libs {
	/**
	 * リソース管理。<br />
	 * ローンパターン。
	 *
	 * @param resource close() を実装するリソースオブジェクト。
	 * @param func リソース処理
	 */
	def using[Result, Resource <% { def close(): Unit }](resource: Resource)(func: Resource => Result): Result =
		try func(resource) finally if (resource != null) resource.close()
}
