package com.finn.supersubspan

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.URLSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_text_span_click.*


class TextClickSpanActivity : AppCompatActivity() {

    companion object {
        private const val TEXT_CONTENT = "Click: Today is Jul 25th"
        private const val TEXT_URL = "Jul"
        private const val TEXT_CLICK = "Today"
        private const val url = "https://blog.csdn.net/qq_20613731"
    }

    private val index by lazy { TEXT_CONTENT.indexOf(TEXT_URL) }
    private val indexClick by lazy { TEXT_CONTENT.indexOf(TEXT_CLICK) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_span_click)
        initView()
    }

    private fun initView() {
        text_type_url.text = getURLSpan(url)
        text_type_m_url.text = getMURLSpan(url)
        text_type_click.text = getTextClickableSpan()

        //TextView必须设置了此方法的前提下，才能选择受影响的文本范围
        text_type_url.movementMethod = LinkMovementMethod.getInstance()
        text_type_click.movementMethod = LinkMovementMethod.getInstance()
        text_type_m_url.movementMethod = LinkMovementMethod.getInstance()

    }

    //设置部分文字的超链接 URLSpan
    private fun getURLSpan(url: String): Spannable {
        val sb = SpannableStringBuilder(TEXT_CONTENT)
        sb.setSpan(
            URLSpan(url),
            index,
            index + TEXT_URL.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        return sb
    }

    //设置部分文字的超链接 MURLSpan > URLSpan
    private fun getMURLSpan(url: String): Spannable {
        val sb = SpannableStringBuilder(TEXT_CONTENT)
        sb.setSpan(
            MURLSpan(this, url),
            index,
            index + TEXT_URL.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        return sb
    }

    //设置部分文字的点击事件 MClickableSpan > ClickableSpan
    private fun getTextClickableSpan(): Spannable {
        val sb = SpannableStringBuilder(TEXT_CONTENT)
        sb.setSpan(
            MClickableSpan(this),
            indexClick,
            indexClick + TEXT_CLICK.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        return sb
    }

    class MURLSpan(private val context: Context, private val url: String) : URLSpan(url){
        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            //设置超链接文本的颜色
            ds.color = Color.RED
            //这里可以去除点击文本的默认的下划线
            ds.isUnderlineText = false
        }

        override fun onClick(widget: View) {
//========== 屏蔽原来默认的跳转链接
//           super.onClick(widget)
//>>>>>>>>>>>
            //去除点击后字体出现的背景色
            (widget as? TextView)?.highlightColor = Color.TRANSPARENT
            //自定义超链接动作
            Toast.makeText(context, "自定义超链接动作", Toast.LENGTH_SHORT).show()
            context.startActivity(Intent(context, ImageSpanActivity::class.java))
        }
    }

    class MClickableSpan(private val context: Context): ClickableSpan(){
        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            //设置点击文本的颜色
            ds.color = Color.BLUE
            //去除点击文本的默认的下划线
            ds.isUnderlineText = false
        }

        override fun onClick(widget: View) {
            //去除点击后字体出现的背景色
            (widget as? TextView)?.highlightColor = Color.TRANSPARENT
            //设置点击事件的动作
            Toast.makeText(context, "自定义链接动作", Toast.LENGTH_SHORT).show()
            context.startActivity(Intent(context, TextSpanActivity::class.java))
        }
    }
}
