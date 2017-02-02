package com.github.mt_sito.mr_scala_libs.utils

import com.github.mt_sito.mr_scala_libs.MrScalaLibsFactory


/**
 * クラスユーティリティトレイト。
 */
trait ClassUtil {
	/**
	 * クラスローダ取得。
	 *
	 * @return クラスローダ
	 */
	def classLoader: ClassLoader
}


/**
 * クラスユーティリティ実装クラス。
 *
 * @param factory ファクトリクラス
 */
class ClassUtilImpl(factory: MrScalaLibsFactory) extends ClassUtil {
	/** {@inheritDoc} */
	override def classLoader: ClassLoader = {
		val t = Thread.currentThread
		val c = t.getContextClassLoader
		if (c != null) c else t.getClass.getClassLoader
	}
}
