package com.finn.supersubspan

import android.graphics.Canvas
import android.graphics.Paint
import android.text.style.ReplacementSpan

class SuperSubSpan(private val type: SubSpanType = SubSpanType.DEFAULT) : ReplacementSpan() {

    override fun getSize(
        paint: Paint,
        text: CharSequence,
        start: Int,
        end: Int,
        fm: Paint.FontMetricsInt?
    ): Int {
        val subText = text.subSequence(start, end)
        return paint.measureText(subText.toString()).toInt()
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
        val subText = text.subSequence(start, end)
        val fm = paint.fontMetricsInt
        val subY = when (type) {
            SubSpanType.CENTER -> {
                (fm.descent - fm.ascent) + (y - (fm.descent - fm.ascent)) / 2
            }
            SubSpanType.TOP -> {
                fm.descent - fm.ascent
            }
            SubSpanType.DEFAULT -> {
                y
            }
        }
        canvas.drawText(subText.toString(), x, subY.toFloat(), paint)
    }
}