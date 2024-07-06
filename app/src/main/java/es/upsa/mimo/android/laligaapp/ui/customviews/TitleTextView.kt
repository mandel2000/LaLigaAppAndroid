package es.upsa.mimo.android.laligaapp.ui.customviews

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import es.upsa.mimo.android.laligaapp.R
import org.w3c.dom.Text

class TitleTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr){

    init {
        setTextColor(ContextCompat.getColor(context, R.color.black)) // Cambia el color del texto
        textSize = 24f // Cambia el tamaño del texto
        typeface = Typeface.DEFAULT_BOLD // Cambia el estilo a negrita
    }
}