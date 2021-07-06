package com.finn.supersubspan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.SuperscriptSpan
import android.text.style.TextAppearanceSpan
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TEXT_DATE = "Jul 25th, 11:11am"
        private const val TEXT_TH = "th"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        //添加上标可以简单实现效果
        text_sub_superscript.text = getSuperscriptSpanShow()

        text_sub_default.text = getDateSpanShow(SubSpanType.DEFAULT)
        text_sub_top.text = getDateSpanShow(SubSpanType.TOP)
        text_sub_center.text = getDateSpanShow(SubSpanType.DEFAULT)

        text_sub_center.setOnClickListener {
            startActivity(Intent(this@MainActivity, TextClickSpanActivity::class.java))
        }
    }

    private fun getDateSpanShow(type: SubSpanType): Spannable {
        val indexTH = TEXT_DATE.indexOf(TEXT_TH)
        val sb = SpannableStringBuilder(TEXT_DATE)
        sb.setSpan(
            TextAppearanceSpan(this, R.style.DateTextTH),
            indexTH,
            indexTH + TEXT_TH.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )

        sb.setSpan(
            SuperSubSpan(type),
            indexTH,
            indexTH + TEXT_TH.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        return sb
    }

    private fun getSuperscriptSpanShow(): Spannable {
        val indexTH = TEXT_DATE.indexOf(TEXT_TH)
        val sb = SpannableStringBuilder(TEXT_DATE)
        sb.setSpan(
            TextAppearanceSpan(this, R.style.DateTextTH),
            indexTH,
            indexTH + TEXT_TH.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )

        //将TH作为上标
        sb.setSpan(
            SuperscriptSpan(),
            indexTH,
            indexTH + TEXT_TH.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        return sb
    }
}