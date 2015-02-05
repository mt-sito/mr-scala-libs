package com.github.mt_sito.mr_scala_libs.platforms


/**
 * OS ベースクラス。
 */
sealed abstract class OS(val id: Int, val name: String, val pathSeparator: String) {
	/**
	 * 文字列化。
	 *
	 * @return 文字列表現
	 */
	override def toString: String = name

	/** パスプレフィックス長 */
	def pathPrefixLength(path: String): Int = 1
}


/**
 * OS。
 */
object OS {
	case object WINDOWS extends OS(1, "Windows", "\\") {
		/** {@inheritDoc} */
		override def pathPrefixLength(path: String): Int = if (path.startsWith("""\\""")) 2 else 3
	}
	case object LINUX extends OS(2, "Linux", "/")
	case object UNIX extends OS(3, "Unix", "/")
	case object MAC_OS extends OS(4, "Mac OS", "/")


	/** 全 OS */
	val values = Seq(WINDOWS, LINUX, UNIX, MAC_OS)


	/**
	 * ID から OS オブジェクトを取得。
	 *
	 * @param id ID
	 * @return OS オブジェクト
	 */
	def apply(id: Int): OS = id match {
		case WINDOWS.id => WINDOWS
		case LINUX.id => LINUX
		case UNIX.id => UNIX
		case MAC_OS.id => MAC_OS
	}
}
