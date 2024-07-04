package es.upsa.mimo.android.laligaapp.ui.customviews

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import es.upsa.mimo.android.laligaapp.R

class CustomToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = androidx.appcompat.R.attr.toolbarStyle
) : Toolbar(context, attrs, defStyleAttr) {

    private lateinit var titleTextView : TextView
    private lateinit var navigationIconView : ImageView

    init {
        inflate(context, R.layout.custom_toolbar, this)
        titleTextView = findViewById(R.id.toolbar_title)
        //navigationIconView = findViewById(R.id.toolbar_icon)
    }

    fun setTitle(title: String) {
        titleTextView.text = title
    }

    override fun setTitleTextColor(color: Int){
        titleTextView.setTextColor(color)
    }

    fun setTitleTextSize(size: Float) {
        titleTextView.textSize = size
    }

    override fun setNavigationIcon(iconResId: Int) {
        navigationIconView.setImageResource(iconResId)
    }

    fun setNavigationIconClickListener(listener: OnClickListener) {
        navigationIconView.setOnClickListener(listener)
    }

    fun setTitleTypeface(typeface: Typeface) {
        titleTextView.typeface = typeface
    }

    fun hideTitle() {
        titleTextView.visibility = GONE
    }

    fun showTitle() {
        titleTextView.visibility = VISIBLE
    }

}