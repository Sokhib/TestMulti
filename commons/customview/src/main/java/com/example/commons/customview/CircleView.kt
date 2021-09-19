package com.example.commons.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat

//TODO: Handle livedata support
//TODO: Handle click listener
class CircleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var radius = 0f
    private var color = ContextCompat.getColor(context, android.R.color.black)
    private var state = false

    init {
        this.setBackgroundColor(Color.TRANSPARENT)
        attrs?.let { attributeSet ->
            val typedArrayAttrs = context.obtainStyledAttributes(
                attributeSet,
                R.styleable.CircleView
            )
            radius = typedArrayAttrs.getDimension(
                R.styleable.CircleView_circleRadius,
                dpToPx(30f, context).toFloat()
            )
            color = typedArrayAttrs.getColor(R.styleable.CircleView_circleColor, color)
            state = typedArrayAttrs.getBoolean(R.styleable.CircleView_circleState, false)
            if (state) setChecked() else setUnchecked()
            typedArrayAttrs.recycle()
        }
        paint.color = color
//        paint.apply {
//            alpha = 200 // Ranges between 0-255
//            style = Paint.Style.STROKE
//            strokeWidth = 10f
//        }

    }

    fun setChecked() {
        paint.style = Paint.Style.FILL
        invalidate()
    }

    fun setUnchecked() {
        paint.apply {
            style = Paint.Style.STROKE
            strokeWidth = 10f
        }
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val height = measureHeight(heightMeasureSpec)
        val width = measureWidth(widthMeasureSpec)
        setMeasuredDimension(width, height)
    }

    private fun measureWidth(widthMeasureSpec: Int) =
        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY) {
            MeasureSpec.getSize(widthMeasureSpec) + paddingStart
        } else {
            dpToPx(context = context) + paddingStart
        }


    private fun measureHeight(heightMeasureSpec: Int) =
        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) {
            MeasureSpec.getSize(heightMeasureSpec) + paddingTop
        } else {
            dpToPx(context = context) + paddingStart
        }


    override fun onDraw(canvas: Canvas?) {
        canvas?.drawCircle(
            measuredWidth.toFloat() / 2f,
            measuredHeight.toFloat() / 2f,
            radius,
            paint
        )
    }

    private fun dpToPx(dp: Float = 30f, context: Context): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.resources.displayMetrics
        )
            .toInt()
    }
}