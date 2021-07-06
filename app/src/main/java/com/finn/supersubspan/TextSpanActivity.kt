package com.finn.supersubspan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.*
import kotlinx.android.synthetic.main.activity_text_span.*

class TextSpanActivity : AppCompatActivity() {

    companion object {
        private const val TEXT_CONTENT = "Today is Jul 25th"
        private const val TEXT_SUB = "Jul"
        private const val TEXT_TH = "th"
    }

    private val index by lazy { TEXT_CONTENT.indexOf(TEXT_SUB) }
    private val indexTH by lazy { TEXT_CONTENT.indexOf(TEXT_TH) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_span)
        initView()
    }

    private fun initView() {
        //Jul 23th
        text_type_size.text = getAbsoluteSizeSpan(12.toDp())
        text_type_color.text = getTextForegroundColorSpan(resources.getColor(R.color.colorAccent))
        text_type_text.text = getTextAppearanceSpan(R.style.DateTextOther)
        text_type_background.text = getBackgroundColorSpan(resources.getColor(R.color.colorAccent))
        text_type_underline.text = getUnderlineSpan()
        text_type_strikethrough.text = getStrikethroughSpan()
        text_type_superscript.text = getSuperscript_SubscriptSpan()
    }

    //设置部分文字的大小 AbsoluteSizeSpan/RelativeSizeSpan
    private fun getAbsoluteSizeSpan(size: Int): Spannable {
        val sb = SpannableStringBuilder(TEXT_CONTENT)
        sb.setSpan(
            AbsoluteSizeSpan(size),
            index,
            index + TEXT_SUB.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        return sb
    }

    //设置部分文字的颜色
    private fun getTextForegroundColorSpan(color: Int): Spannable {
        val sb = SpannableStringBuilder(TEXT_CONTENT)
        sb.setSpan(
            ForegroundColorSpan(color),
            index,
            index + TEXT_SUB.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        return sb
    }

    //设置部分文字的字体/大小/颜色
    private fun getTextAppearanceSpan(appearance: Int): Spannable {
        val sb = SpannableStringBuilder(TEXT_CONTENT)
        sb.setSpan(
            TextAppearanceSpan(this, appearance),
            index,
            index + TEXT_SUB.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        return sb
    }

    //设置部分文字背景色
    private fun getBackgroundColorSpan(color: Int): Spannable {
        val sb = SpannableStringBuilder(TEXT_CONTENT)
        sb.setSpan(
            BackgroundColorSpan(color),
            index,
            index + TEXT_SUB.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        return sb
    }

    //设置部分文字添加下划线
    private fun getUnderlineSpan(): Spannable {
        val sb = SpannableStringBuilder(TEXT_CONTENT)
        sb.setSpan(
            UnderlineSpan(),
            index,
            index + TEXT_SUB.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        return sb
    }

    //设置部分文字添加删除线
    private fun getStrikethroughSpan(): Spannable {
        val sb = SpannableStringBuilder(TEXT_CONTENT)
        sb.setSpan(
            StrikethroughSpan(),
            index,
            index + TEXT_SUB.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        return sb
    }

    //设置部分文字 上下标 Superscript/SubscriptSpan
    private fun getSuperscript_SubscriptSpan(): Spannable {
        val sb = SpannableStringBuilder(TEXT_CONTENT)
        //设置上标文字的字体/大小/颜色
        sb.setSpan(
            TextAppearanceSpan(this, R.style.DateTextOther),
            indexTH,
            indexTH + TEXT_TH.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        //设置为上标
        sb.setSpan(
            SuperscriptSpan(),
            indexTH,
            indexTH + TEXT_TH.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )

        //设置下标文字的字体/大小/颜色
        sb.setSpan(
            TextAppearanceSpan(this, R.style.DateTextOther),
            index,
            index + TEXT_SUB.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        //设置为下标
        sb.setSpan(
            SubscriptSpan(),
            index,
            index + TEXT_SUB.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        return sb
    }
}
