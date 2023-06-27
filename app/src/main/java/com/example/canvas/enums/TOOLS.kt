package com.example.canvas.enums

import androidx.annotation.DrawableRes
import com.example.canvas.R

enum class TOOLS (
    @DrawableRes
    val value: Int
) {
    NORMAL(R.drawable.ic_horizontal_line),
    DASH(R.drawable.ic_dashed_line),
    SIZE(R.drawable.baseline_line_weight_24),
    PALETTE(R.drawable.baseline_brightness_1_24)
}