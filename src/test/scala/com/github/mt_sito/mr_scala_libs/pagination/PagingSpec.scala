package com.github.mt_sito.mr_scala_libs.pagination

import org.scalatest.FlatSpec


/**
 * Paging スペッククラス。
 */
class PagingSpec extends FlatSpec {
	class MyPaging extends Paging[String] {
		override def sqlSyntax(no: Int, limit: Int): String = s"LIMIT $limit OFFSET ${Page.offset(no, limit)}"
	}


	"apply" should "リストサイズが limit 以下の場合全件数はリストサイズとなる" in {
		val list = Seq(1, 2, 3, 4)
		assert(new MyPaging().create(5, 5, list) { fail("Call count(*)"); 0L }  === Page(5, 5, list, 24L))
	}

	it should "リストサイズが limit の場合全件数取得関数の結果となる" in {
		val list = Seq(1, 2, 3, 4, 5)
		assert(new MyPaging().create(5, 5, list) { 26L }  === Page(5, 5, list, 26L))
	}

	"sqlSyntax" should "no, offset により Syntax が作成される" in {
		assert(new MyPaging().sqlSyntax(3, 5) === "LIMIT 5 OFFSET 10")
	}
}
