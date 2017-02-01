package com.github.mt_sito.mr_scala_libs.utils

import java.security.MessageDigest
import java.util.UUID

import scala.annotation.tailrec

import org.apache.commons.codec.binary.Base64


/**
 * セキュリティユーティリティートレイト。
 */
trait SecureUtil {
	/** デフォルトストレッチ回数 */
	val DEFAULT_STRETCH_COUNT = 9999
	/** デフォルトアルゴリズム */
	val DEFAULT_ALGORITHM = "SHA-512"


	/**
	 * ランダムな 32 文字識別子取得。
	 *
	 * @return ランダムな識別子
	 */
	def randomToken32(): String

	/**
	 * ランダムな 64 文字識別子取得。
	 *
	 * @return ランダムな識別子
	 */
	def randomToken64(): String

	/**
	 * パスワードハッシュ化。
	 *
	 * @param password パスワード
	 * @param salt ソルト
	 * @param stretchCount ストレッチ回数
	 * @param algorithm アルゴリズム
	 * @param version バージョン
	 * @return ハッシュ化されて Base64 化された文字列
	 */
	def hashPassword(password: String, salt: String, stretchCount: Int = DEFAULT_STRETCH_COUNT,
		algorithm: String = DEFAULT_ALGORITHM, version: Int = 1): String

	/**
	 * パスワード検証。
	 *
	 * @param password パスワード
	 * @param hash ハッシュ化されたパスワード
	 * @param stretchCount ストレッチ回数
	 * @param algorithm アルゴリズム
	 * @param version バージョン
	 * @return パスワードが一致ならば true
	 */
	def verifyPassword(password: String, hash: String, stretchCount: Int = DEFAULT_STRETCH_COUNT,
		algorithm: String = DEFAULT_ALGORITHM, version: Int = 1): Boolean
}


/**
 * セキュリティユーティリティー実装クラス。
 */
class SecureUtilImpl extends SecureUtil {
	/** パスワードセパレータ */
	val PASSWORD_SEP = "@"


	/** {@inheritDoc} */
	override def randomToken32(): String = UUID.randomUUID().toString.replaceAll("-", "")

	/** {@inheritDoc} */
	override def randomToken64(): String = new StringBuilder(randomToken32()).append(randomToken32()).toString

	/** {@inheritDoc} */
	override def hashPassword(password: String, salt: String, stretchCount: Int = DEFAULT_STRETCH_COUNT,
		algorithm: String = DEFAULT_ALGORITHM, version: Int = 1): String = {
		require(password != null)
		require(salt != null)
		require(stretchCount > 0)
		require(version > 0)

		val md = MessageDigest.getInstance(algorithm)
		new StringBuilder(version.toString).append(PASSWORD_SEP).append(salt).append(PASSWORD_SEP).
			append(Base64.encodeBase64String(doHashPassword(md, salt.getBytes ++ password.getBytes, stretchCount))).toString
	}

	/** {@inheritDoc} */
	override def verifyPassword(password: String, hash: String, stretchCount: Int = DEFAULT_STRETCH_COUNT,
		algorithm: String = DEFAULT_ALGORITHM, version: Int = 1): Boolean = {
		require(password != null)
		require(hash != null)

		val verPos = hash.indexOf(PASSWORD_SEP)
		if (verPos == -1) return false
		val ver = hash.substring(0, verPos).toInt
		if (ver != version) return false

		val saltPos = hash.lastIndexOf(PASSWORD_SEP)
		if (saltPos == -1) return false
		val salt = hash.substring(verPos + 1, saltPos)

		return hash.equals(hashPassword(password, salt, stretchCount, algorithm, ver))
	}


	/**
	 * パスワードハッシュ化実行。
	 *
	 * @param md MessageDigest
	 * @param password パスワードバイト配列
	 * @param stretchCount ストレッチ回数
	 * @return ハッシュ化されて Base64 化された文字列
	 */
	@tailrec
	private def doHashPassword(md: MessageDigest, password: Array[Byte], stretchCount: Int): Array[Byte] = {
		if(stretchCount == 0) password
		else doHashPassword(md, md.digest(password), stretchCount - 1)
	}
}


/**
 * セキュリティユーティリティコンポーネントトレイト。
 */
trait SecureUtilComponent {
	/** セキュリティユーティリティ */
	val secureUtil: SecureUtil
}


/**
 * セキュリティユーティリティコンポーネント実装トレイト。
 */
trait SecureUtilComponentImpl {
	/** セキュリティユーティリティ */
	val secureUtil: SecureUtil = new SecureUtilImpl
}
