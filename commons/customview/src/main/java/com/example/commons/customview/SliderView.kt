package com.example.commons.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class SliderView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var defaultBarWidth = 350
    private var defaultBarHeight = 150
    private val rectPaint = Paint()
    private val circlePaint = Paint()

    init {
        rectPaint.apply {
            color = Color.GRAY
        }

        circlePaint.apply {
            color = Color.GREEN
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val width = when (widthMode) {
            MeasureSpec.EXACTLY -> widthSize
            else -> defaultBarWidth
        }
        val height = when (heightMode) {
            MeasureSpec.EXACTLY -> heightSize
            else -> defaultBarHeight
        }
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.let { canvas ->
            //Rounded Rectangle
            canvas.drawRoundRect(
                paddingLeft + 0f,
                paddingTop + 0f,
                width.toFloat() - paddingEnd,
                height.toFloat() - paddingBottom,
                10f,
                10f,
                rectPaint
            )

            // Circle
            canvas.drawCircle(width.toFloat() * 0.1f, height.toFloat() / 2f, 50f, circlePaint)
        }

        invalidate()
    }
}