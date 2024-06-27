package es.upsa.mimo.android.laligaapp.ui.decoration

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView

class TableDividerItemDecorator : RecyclerView.ItemDecoration() {

    private val paint = Paint().apply {
        color = Color.GRAY
        strokeWidth = 1f
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        for (i in 0 until parent.childCount ) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            // Dibuja la línea horizontal debajo de cada elemento
            val bottom = child.bottom + params.bottomMargin
            c.drawLine(left.toFloat(), bottom.toFloat(), right.toFloat(), bottom.toFloat(), paint)

            // Dibuja la línea vertical a la derecha de cada elemento
            if (child is LinearLayout) { // Asegúrate de que el elemento sea un LinearLayout
                for (j in 0 until child.childCount - 1) {
                    val column = child.getChildAt(j)
                    val rightEdge = column.right
                    c.drawLine(
                        rightEdge.toFloat(),
                        child.top.toFloat(),
                        rightEdge.toFloat(),
                        child.bottom.toFloat(),
                        paint
                    )
                }
            }
        }

    }
}