package com.github.mt_sito.mr_scala_libs.utils


/**
 * リソースユーティリティオブジェクト。
 */
object ResourceUtil {
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
