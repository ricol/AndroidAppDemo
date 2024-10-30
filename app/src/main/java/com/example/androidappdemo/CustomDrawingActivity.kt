package com.example.androidappdemo

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel

class CustomDrawingViewModel: ViewModel() {
    var points = mutableListOf<PointF>()
    var boxen = mutableListOf<CustomDrawingActivity.Box>()
    var freeDrawSegments = mutableListOf<CustomDrawingActivity.Segment>()
    var type: CustomDrawingActivity.BoxDrawingView.Type = CustomDrawingActivity.BoxDrawingView.Type.freedraw
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "$this onCleared...")
    }

    init {
        Log.d(TAG, "$this init...")
    }
}

class CustomDrawingActivity: ComponentActivity() {
    lateinit var rbLine: RadioButton
    lateinit var rbBox: RadioButton
    lateinit var rbFreeDraw: RadioButton
    lateinit var btnClear: Button
    lateinit var viewDrawing: BoxDrawingView
    val viewModal: CustomDrawingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            Log.d(TAG, "$this->onCreate...${savedInstanceState.getString("string")}")
        }
        setContentView(R.layout.activity_custom_drawing)
        rbLine = findViewById(R.id.rbLine)
        rbBox = findViewById(R.id.rbBox)
        rbFreeDraw = findViewById(R.id.rbFreeDraw)
        btnClear = findViewById(R.id.btnClear)
        viewDrawing = findViewById(R.id.viewDrawing)
        btnClear.setOnClickListener {
            viewDrawing.clear()
        }
        rbLine.setOnClickListener {
            viewModal.type = BoxDrawingView.Type.points
        }
        rbBox.setOnClickListener {
            viewModal.type = BoxDrawingView.Type.box
        }
        rbFreeDraw.setOnClickListener {
            viewModal.type = BoxDrawingView.Type.freedraw
        }
        rbFreeDraw.isChecked = viewModal.type == BoxDrawingView.Type.freedraw
        rbLine.isChecked = viewModal.type == BoxDrawingView.Type.points
        rbBox.isChecked = viewModal.type == BoxDrawingView.Type.box
        viewDrawing.viewModel = viewModal
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "$this->onSaveInstanceState...")
        outState.putString("string", "welcome to Android world!")
    }

    class BoxDrawingView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
        enum class Type {
            points, box, freedraw
        }
        private var currentBox: Box? = null
        private var currentPoint: PointF? = null
        private var clearCanvas: Boolean = false
        var viewModel: CustomDrawingViewModel? = null
        private val boxPaint = Paint().apply {
            color = 0x22ff0000.toInt()
        }
        private val linePaint = Paint().apply {
            color = Color.BLUE
            strokeWidth = 10.toFloat()
        }
        private val currentLinePaint = Paint().apply {
            color = Color.RED
            strokeWidth = 10.toFloat()
        }
        private val backgroundPaint = Paint().apply {
            color = 0xfff8efe0.toInt()
        }
        private val freeDrawPaint = Paint().apply {
            color = Color.GREEN
            strokeWidth = 10.toFloat()
        }
        private var currentSegment: Segment? = null

        fun clear() {
            viewModel?.boxen?.clear()
            viewModel?.points?.clear()
            viewModel?.freeDrawSegments?.clear()
            currentPoint = null
            currentBox = null
            clearCanvas = true
            invalidate()
        }

        override fun onTouchEvent(event: MotionEvent): Boolean {
            val current = PointF(event.x, event.y)
            if (viewModel?.type == Type.points) {
                currentPoint = current
            }
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (viewModel?.type == Type.box) {
                        currentBox = Box(current).also {
                            viewModel?.boxen?.add(it)
                        }
                    }else if (viewModel?.type == Type.freedraw) {
                        if (viewModel?.type == Type.freedraw) {
                            if (currentSegment == null) currentSegment = Segment()
                            viewModel?.freeDrawSegments?.add(currentSegment!!)
                        }
                        currentSegment!!.add(current)
                    }
                }

                MotionEvent.ACTION_MOVE -> {
                    if (viewModel?.type == Type.box) {
                        updateCurrentBox(current)
                    }else if (viewModel?.type == Type.freedraw) {
                        currentSegment!!.add(current)
                    }
                }

                MotionEvent.ACTION_UP -> {
                    if (viewModel?.type == Type.box) {
                        updateCurrentBox(current)
                        currentBox = null
                    }else if (viewModel?.type == Type.points) {
                        viewModel?.points?.add(current)
                    }else if (viewModel?.type == Type.freedraw) {
                        currentSegment = null
                    }
                }

                MotionEvent.ACTION_CANCEL -> {
                    currentBox = null
                }
            }
            invalidate()
            return true
        }

        private fun updateCurrentBox(current: PointF) {
            currentBox?.let {
                it.end = current
                invalidate()
            }
        }

        override fun onDraw(canvas: Canvas) {
            canvas.drawPaint(backgroundPaint)
            if (clearCanvas) { clearCanvas =false; return }
            //box
            viewModel?.boxen?.forEach { box ->
                canvas.drawRect(box.left, box.top, box.right, box.bottom, boxPaint)
            }

            //points
            if (viewModel?.points?.isNotEmpty() == true) {
                val points = viewModel?.points!!
                var from = points.first()
                points.forEach { p ->
                    canvas.drawLine(from.x, from.y, p.x, p.y, linePaint)
                    from = p
                }
                if (currentPoint != null) {
                    canvas.drawLine(from.x, from.y, currentPoint!!.x, currentPoint!!.y, currentLinePaint)
                }
            }
            //freedraw
            viewModel?.freeDrawSegments?.forEach { segment ->
                if (segment.points.isNotEmpty()) {
                    var from = segment.points.first()
                    segment.points.forEach { p ->
                        canvas.drawLine(from.x, from.y, p.x, p.y, freeDrawPaint)
                        from = p
                    }
                }
            }
        }
    }

    class Box(private val start: PointF) {
        var end: PointF = start
        val left: Float
            get() = Math.min(start.x, end.x)

        val right: Float
            get() = Math.max(start.x, end.x)

        val top: Float
            get() = Math.min(start.y, end.y)

        val bottom: Float
            get() = Math.max(start.y, end.y)
    }

    class Segment {
        var points = mutableListOf<PointF>()
        fun add(p: PointF) {
            points.add(p)
        }
    }
}