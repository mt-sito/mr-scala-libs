package com.github.mt_sito.mr_scala_libs.pagination

import org.scalatest.FlatSpec


/**
 * Page スペッククラス。
 */
class PageSpec extends FlatSpec {
	"constractor" should "no, limit 共に正の値の場合正常に返す" in {
		val accept = Page(3, 5)
		assert(accept === Page(3, 5))
		assert(accept.no === 3)
		assert(accept.limit === 5)
		assert(accept.offset === 10)
	}

	it should "no が 0 の場合例外" in {
		intercept[IllegalArgumentException] {
			Page(0, 5)
		}
	}

	it should "limit が 0 の場合例外" in {
		intercept[IllegalArgumentException] {
			Page(1, 0)
		}
	}

	"hasPrev" should "no が 1 の場合 false" in {
		assert(!Page(1, 5).hasPrev)
	}

	it should "no が 1 より大きいの場合 true" in {
		assert(Page(3, 5).hasPrev)
	}

	"hasNext" should "allCount より次頁がない場合 false" in {
		assert(!Page(1, 5, Nil, 5).hasNext)
	}

	it should "allCount より次頁がある場合 true" in {
		assert(Page(1, 5, Nil, 6).hasNext)
	}

	"prevNo" should "no が 1 の場合 1" in {
		assert(Page(1, 5).prevNo === 1)
	}

	it should "no が 1 より大きいの場合前ページ" in {
		assert(Page(3, 5).prevNo === 2)
	}

	"nextNo" should "allCount より次頁がないの場合最終ページ" in {
		assert(Page(2, 5, Nil, 10).nextNo === 2)
	}

	it should "allCount より次頁がある場合次ページ" in {
		assert(Page(1, 5, Nil, 6).nextNo === 2)
	}

	"maxPage" should "全件数が 4 で limit が 3 の場合 2 を返す" in {
		assert(Page(1, 3, Nil, 4).maxPage === 2)
	}

	"ranges" should "1 ページの場合 1 - 10 を返す" in {
		assert(Page[Any](1, 5, Nil, 100).ranges() === Seq(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
	}

	it should "最終ページの場合 11 - 20 を返す" in {
		assert(Page[Any](20, 5, Nil, 100).ranges() === Seq(11, 12, 13, 14, 15, 16, 17, 18, 19, 20))
	}

	it should "中間ページの途中の場合 3 - 12 を返す" in {
		assert(Page[Any](7, 5, Nil, 100).ranges() === Seq(3, 4, 5, 6, 7, 8, 9, 10, 11, 12))
	}

	it should "前ページがあまりない場合 1 - 10 を返す" in {
		assert(Page[Any](3, 5, Nil, 100).ranges() === Seq(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
	}

	it should "次ページがあまりない場合 11 - 20 を返す" in {
		assert(Page[Any](16, 5, Nil, 100).ranges() === Seq(11, 12, 13, 14, 15, 16, 17, 18, 19, 20))
	}

	it should "1 ページのみの場合 1 を返す" in {
		assert(Page[Any](1, 5, Nil, 3).ranges() === Seq(1))
	}

	it should "全件数が 4 で page が 2 で limit が 3 の場合 Seq(1, 2) を返す" in {
		assert(Page[Any](2, 3, Nil, 4).ranges() === Seq(1, 2))
	}

	it should "最終ページが padding で割り切れない場合 101 - 110 を返す" in {
		assert(Page[Any](105, 90, Nil, 10082).ranges() === Seq(101, 102, 103, 104, 105, 106, 107, 108, 109, 110))
	}

	it should "幅以上のページ番号の場合 7 - 16 を返す" in {
		assert(Page[Any](11, 3, Nil, 55).ranges() === Seq(7, 8, 9, 10, 11, 12, 13, 14, 15, 16))
	}

	it should "幅が奇数で 1 ページの場合1 - 3 を返す" in {
		assert(Page[Any](1, 5, Nil, 100).ranges(3) === Seq(1, 2, 3))
	}

	it should "幅が奇数で最終ページの場合 18 - 20 を返す" in {
		assert(Page[Any](20, 5, Nil, 100).ranges(3) === Seq(18, 19, 20))
	}

	it should "幅が奇数で中間ページの途中の場合 6 - 8 を返す" in {
		assert(Page[Any](7, 5, Nil, 100).ranges(3) === Seq(6, 7, 8))
	}

	it should "最終ページで limit 件数ない場合 1 - 2 を返す" in {
		assert(Page[Any](2, 8, Nil, 10).ranges(3) === Seq(1, 2))
	}

	"offset" should "no, limit より計算される" in {
		assert(Page.offset(3, 5) === 10)
	}

	it should "開始は 0" in {
		assert(Page.offset(1, 5) === 0)
	}
}
