package com.github.mt_sito.mr_scala_libs

import com.github.mt_sito.mr_scala_libs.utils._


/**
 * Mr scala libs ファクトリトレイト。
 */
trait MrScalaLibsFactory {
	/** クラスユーティリティ */
	val classUtil: ClassUtil
	/** 数値ユーティリティ */
	val numberUtil: NumberUtil
	/** パスユーティリティ */
	val pathUtil: PathUtil
	/** リソースユーティリティ */
	val resourceUtil: ResourceUtil
	/** セキュアユーティリティ */
	val secureUtil: SecureUtil
	/** サイズユーティリティ */
	val sizeUtil: SizeUtil
}

object MrScalaLibsFactoryImpl extends MrScalaLibsFactory {
	/** {@inheritDoc} */
	val classUtil: ClassUtil = new ClassUtilImpl(this)
	/** {@inheritDoc} */
	val numberUtil: NumberUtil = new NumberUtilImpl(this)
	/** {@inheritDoc} */
	val pathUtil: PathUtil = new PathUtilImpl(this)
	/** {@inheritDoc} */
	val resourceUtil: ResourceUtil = new ResourceUtilImpl(this)
	/** {@inheritDoc} */
	val secureUtil: SecureUtil = new SecureUtilImpl(this)
	/** {@inheritDoc} */
	val sizeUtil: SizeUtil = new SizeUtilImpl(this)
}
