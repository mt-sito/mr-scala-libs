package com.github.mt_sito.mr_scala_libs.utils


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
 */
class ClassUtilImpl extends ClassUtil {
	/** {@inheritDoc} */
	override def classLoader: ClassLoader = {
		val t = Thread.currentThread
		val c = t.getContextClassLoader
		if (c != null) c else t.getClass.getClassLoader
	}
}


/**
 * クラスユーティリティコンポーネントトレイト。
 */
trait ClassUtilComponent {
	/** クラスユーティリティ */
	val classUtil: ClassUtil
}


/**
 * クラスユーティリティコンポーネント実装トレイト。
 */
trait ClassUtilComponentImpl {
	/** クラスユーティリティ */
	val classUtil: ClassUtil = new ClassUtilImpl
}
