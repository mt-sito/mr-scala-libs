package com.github.mt_sito.mr_scala_libs.utils

import java.io.{File, FileNotFoundException}

import scala.io.Source


/**
 * リソースユーティリティトレイト。
 */
trait ResourceUtil {
	/**
	 * ファイルソース取得。<br />
	 * 実ファイルがなければクラスパスより探索。
	 *
	 * @param file ファイル
	 * @return ファイルソース
	 */
	def getSource(file: File): Source
}


/**
 * リソースユーティリティ実装クラス。
 */
trait ResourceUtilImpl extends ResourceUtil with ClassUtilComponent {
	/** {@inheritDoc} */
	override def getSource(file: File): Source = {
		assert(file != null, "file is null")

		if (file.exists) return Source.fromFile(file)

		val stream = classUtil.classLoader.getResourceAsStream(file.getPath.replace("\\", "/").replaceAll("^./", ""))
		if (stream == null) throw new FileNotFoundException(file.getPath)
		Source.fromInputStream(stream)
	}
}


/**
 * リソースユーティリティコンポーネントトレイト。
 */
trait ResourceUtilComponent {
	/** リソースユーティリティ */
	val resourceUtil: ResourceUtil
}


/**
 * リソースユーティリティコンポーネント実装トレイト。
 */
trait ResourceUtilComponentImpl {
	/** リソースユーティリティ */
	val resourceUtil: ResourceUtil = new ResourceUtilImpl with ClassUtilComponentImpl
}
