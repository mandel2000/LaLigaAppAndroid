package es.upsa.mimo.android.laligaapp.ui.customviews

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import es.upsa.mimo.android.laligaapp.R

class TeamNameTextView  @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr){

    init {
        setTypeface(typeface, Typeface.BOLD_ITALIC)

        val customFont = Typeface.createFromAsset(context.assets, "LaLigaFont.ttf")
        typeface = customFont
        textSize = 30f
        setTextColor(R.color.teamNameColor.toInt())
    }
}