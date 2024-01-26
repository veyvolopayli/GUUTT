package com.veyvolopayli.guutt.presentation.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.res.ResourcesCompat
import com.veyvolopayli.guutt.R
import com.veyvolopayli.guutt.databinding.BaseButtonBinding

class BaseButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val binding: BaseButtonBinding
    var isActive: Boolean = false
        set(value) {
            field = value
            if (value) {
                setButtonState(ButtonState.IS_ACTIVE)
            } else {
                setButtonState(ButtonState.IS_DISABLED)
            }
        }

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.base_button, this, true)
        binding = BaseButtonBinding.bind(this)

        background = ResourcesCompat.getDrawable(resources, R.drawable.background_item_8dp, context.theme)

        setButtonState(ButtonState.IS_DISABLED)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.BaseButton)

            val buttonText = typedArray.getString(R.styleable.BaseButton_text)

            binding.button.text = buttonText

            typedArray.recycle()
        }
    }

    private fun setButtonState(buttonState: ButtonState) {
        when(buttonState) {
            ButtonState.IS_ACTIVE -> {
//                binding.button.isClickable = true
                binding.button.isFocusable = false
                binding.button.isClickable = false
                backgroundTintList = resources.getColorStateList(R.color.apple_blue, context.theme)
            }
            ButtonState.IS_DISABLED -> {
//                binding.button.isClickable = false
                binding.button.isFocusable = true
                binding.button.isClickable = true
                backgroundTintList = resources.getColorStateList(R.color.apple_grey, context.theme)
            }
        }
    }

    enum class ButtonState {
        IS_ACTIVE, IS_DISABLED
    }

}