package com.finn.supersubspan

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.DynamicDrawableSpan
import android.text.style.ImageSpan
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_image_span.*


class ImageSpanActivity : AppCompatActivity() {

    companion object {
        private const val TEXT_CONTENT =
            "Today is  Jul 25th Today is  Jul 25thToday is Jul 25thToday is Jul 25th"
        private const val TEXT_SPACE = "占位"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_span)
        initView()
    }

    private fun initView() {
        text_single.text = displaySpannableWith()
        text_type_image.text = displaySpannableWithIcon()
        text_type_image_size.text = displaySpannableSuperWithIcon()
        text_type_image_size_center.text = displaySpannableSuperWithPic()
    }

    private fun displaySpannableWith(): Spannable {
        val sb = SpannableStringBuilder("Today  25th")
        sb.insert(6, TEXT_SPACE)//占位

        sb.setSpan(
            ImageSpan(this, R.drawable.ic_clock_small,ImageSpan.ALIGN_CENTER),
            6,
            6 + TEXT_SPACE.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        return sb
    }

    //默认ALIGN_BOTTOM；
    // 只有在图片的高度小于文字高度的时候，ImageSpan.ALIGN_CENTER/ALIGN_BASELINE/ALIGN_BOTTOM起作用
    private fun displaySpannableWithIcon(customSize: Int? = null): Spannable {
        val sb = SpannableStringBuilder(TEXT_CONTENT)
        sb.insert(6, TEXT_SPACE)//占位
        sb.insert(11, TEXT_SPACE)//占位
        sb.insert(31, TEXT_SPACE)//占位

        sb.setSpan(
            ImageSpan(this, R.drawable.ic_clock_small),
            6,
            6 + TEXT_SPACE.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )

        sb.setSpan(
            ImageSpan(this, R.drawable.ic_clock, ImageSpan.ALIGN_CENTER),
            11,
            11 + TEXT_SPACE.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )

        sb.setSpan(
            ImageSpan(this, R.drawable.ic_clock_small, ImageSpan.ALIGN_CENTER),
            31,
            31 + TEXT_SPACE.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        return sb
    }

    private fun displaySpannableSuperWithIcon(): Spannable {
        val sb = SpannableStringBuilder(TEXT_CONTENT)
        sb.insert(9, TEXT_SPACE)//占位
        sb.insert(31, TEXT_SPACE)//占位
        sb.setSpan(
            CenterImageSpan(this, R.drawable.ic_clock),
            9,
            9 + TEXT_SPACE.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )

        sb.setSpan(
            CenterImageSpan(this, R.drawable.ic_clock_small),
            31,
            31 + TEXT_SPACE.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        return sb
    }

    private fun displaySpannableSuperWithPic(): Spannable {
        val sb = SpannableStringBuilder(TEXT_CONTENT)
        sb.insert(37, TEXT_SPACE)//占位
        var image = CenterImageSpan(this, R.drawable.ic_launcher_background)
        val index = sb.indexOf(TEXT_SPACE)
        sb.setSpan(
            image,
            index,
            index + TEXT_SPACE.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        return sb
    }

}
