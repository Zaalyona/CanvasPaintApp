package com.example.canvas

import androidx.annotation.ColorRes
import com.example.canvas.base.Item
import com.example.canvas.enums.COLOR
import com.example.canvas.enums.SIZE
import com.example.canvas.enums.TOOLS

sealed class ToolItem : Item {
    data class ColorModel(@ColorRes val color: Int) : ToolItem()
    data class ToolModel(
        val type: TOOLS,
        val selectedTool: TOOLS = TOOLS.NORMAL,
        val isSelected: Boolean = false,
        val selectedSize: SIZE = SIZE.SMALL,
        val selectedColor: COLOR = COLOR.BLACK
    ) : ToolItem()
}