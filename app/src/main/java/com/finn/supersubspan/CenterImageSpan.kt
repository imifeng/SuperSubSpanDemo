package com.finn.supersubspan

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.style.DynamicDrawableSpan
import android.text.style.ImageSpan


class CenterImageSpan(context: Context, mResourceId: Int) :
    ImageSpan(context, mResourceId, DynamicDrawableSpan.ALIGN_CENTER) {

    var isSmallImage = false

    override fun getSize(
        paint: Paint,
        text: CharSequence,
        start: Int,
        end: Int,
        fm: Paint.FontMetricsInt?
    ): Int {
        val drawable = drawable
        val rect = drawable.bounds
        if (fm != null) {
            val fmPaint = paint.fontMetricsInt
            val fontH = fmPaint.descent - fmPaint.ascent //文字行的高度（具体字符顶部到底部的真实高度）
            val imageH = rect.bottom - rect.top  // 图片的高度

            //如果图片的高度 <= 文本的高度,可以直接使用 ImageSpan.ALIGN_CENTER 来实现垂直居中，即将当前图片所在行的图片相对于文字的高度居中，但仅支持单行有效
            if (imageH > fontH) {
                isSmallImage = false
                //这里直接将文字行的高度调整至Image高度对应的top和bottom，即将当前图片所在行的文字相对于图片的高度居中
                fm.ascent = fmPaint.ascent - (imageH - fontH) / 2
                fm.top = fmPaint.ascent - (imageH - fontH) / 2
                fm.bottom = fmPaint.descent + (imageH - fontH) / 2
                fm.descent = fmPaint.descent + (imageH - fontH) / 2
            } else {
                //如果是小图片，就直接使用DynamicDrawableSpan.getSize()里面但方法
                isSmallImage = true
                fm.ascent = -rect.bottom
                fm.descent = 0

                fm.top = fm.ascent
                fm.bottom = 0
            }
        }
        return rect.right
    }

    override fun draw(
        canvas: Canvas,
        text: CharSequence,
        start: Int,
        end: Int,
        x: Float,
        top: Int,
        y: Int,
        bottom: Int,
        paint: Paint
    ) {
        // 这里说明下：
        // 通过重写getSize，使当前图片所在行的文字相对于图片的高度居中，可以直接用DynamicDrawableSpan.draw方法
        val b = drawable
        canvas.save()

        var transY = bottom - b.bounds.bottom
        if (isSmallImage) {
            //但是对于多行使用小图标时，需要我们稍加改变使其居中
            transY -= ((bottom - top) / 2 - b.bounds.height() / 2)
        }

        canvas.translate(x, transY.toFloat())
        b.draw(canvas)
        canvas.restore()
    }
}