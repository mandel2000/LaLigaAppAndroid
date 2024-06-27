package es.upsa.mimo.android.laligaapp.ui.decoration

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView

class DividerItemDecoration(private val dividerThickness: Int = 1, private val color: Int = Color.GRAY)
    : RecyclerView.ItemDecoration() {

    private val paint = Paint().apply {
        color = this@DividerItemDecoration.color
        style = Paint.Style.FILL
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        for (i in 0 until parent.childCount - 1) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + 1 // Divider thickness (adjust as needed)

            // Draw the divider (customize appearance here)
            c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
        }
    }
}