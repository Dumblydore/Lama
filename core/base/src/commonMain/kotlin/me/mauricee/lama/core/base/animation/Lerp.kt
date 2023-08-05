package me.mauricee.lama.core.base.animation

fun lerp(
    startValue: Float,
    endValue: Float,
    fraction: Float,
) = startValue + fraction * (endValue - startValue)