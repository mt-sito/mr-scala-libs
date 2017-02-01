package com.github.mt_sito.mr_scala_libs.utils

import java.net.{URL, URLClassLoader}

import org.scalatest.FlatSpec


/**
 * ClassUtil テストスペッククラス。
 */
class ClassUtilSpec extends FlatSpec with ClassUtilComponentImpl {
	class CLThread extends Thread {
		var cl: ClassLoader = _

		override def run(): Unit = {
			cl = classUtil.classLoader
		}
	}


	"classLoader" should "同一スレッドの場合自身のクラスローダを返す" in {
		val cl = new URLClassLoader(new Array[URL](0))
		val actual = classUtil.classLoader

		assert(actual ne cl)
		assert(actual eq Thread.currentThread.getContextClassLoader)
	}

	it should "スレッド有りでクラスローダ未定義の場合自身のクラスローダを返す" in {
		val cl = new URLClassLoader(new Array[URL](0))
		val t = new CLThread()
		t.start()
		t.join()
		val actual = t.cl

		assert(actual ne cl)
		assert(actual eq Thread.currentThread.getContextClassLoader)
	}

	it should "(スレッド有りでクラスローダ設定した場合設定したクラスローダを返す" in {
		val cl = new URLClassLoader(new Array[URL](0))
		val t = new CLThread()
		t.setContextClassLoader(cl)
		t.start()
		t.join()
		val actual = t.cl

		assert(actual eq cl)
		assert(actual ne Thread.currentThread.getClass.getClassLoader)
	}
}
