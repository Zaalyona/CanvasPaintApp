package com.example.canvas.enums

import androidx.annotation.ColorRes
import com.example.canvas.R

enum class COLOR(
    @ColorRes
    val value: Int
) {

    BLACK(R.color.colorPaintBlack),
    RED(R.color.colorPaintRed),
    BLUE(R.color.colorPaintBlue),
    GREEN(R.color.colorPaintGreen),
    YELLOW(R.color.colorPaintYellow),
    ORANGE(R.color.colorPaintOrange),
    VIOLET(R.color.colorPaintViolet),
    GREY(R.color.colorPaintGrey);

    companion object {
        private val map = values().associateBy(COLOR::value)
        fun from(color: Int) = map[color] ?: BLACK
    }
}