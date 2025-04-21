package com.dilara.progressbarexample

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MultiColorProgressBar(
    segments: List<ProgressSegment>,
    modifier: Modifier = Modifier,
    height: Dp = 16.dp,
    cornerRadius: Dp = 8.dp,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val canvasWidth = size.width
            var startX = 0f

            segments.forEachIndexed { index, segment ->
                val segmentWidth = canvasWidth * segment.progress
                val endX = startX + segmentWidth
                val isFirst = index == 0
                val isLast = index == segments.lastIndex
                val brush = SolidColor(segment.colors.first())

                if (isFirst || isLast) {
                    val path = createRoundedSegment(
                        startX = startX,
                        endX = endX,
                        height = size.height,
                        cornerRadius = cornerRadius.toPx(),
                        isFirst = isFirst,
                        isLast = isLast
                    )

                    drawPath(
                        path = path,
                        brush = brush,
                        style = Fill
                    )
                } else {
                    drawRect(
                        brush = brush,
                        topLeft = Offset(startX, 0f),
                        size = Size(segmentWidth, size.height)
                    )
                }
                startX = endX
            }
        }
    }
}

 fun DrawScope.createRoundedSegment(
    startX: Float,
    endX: Float,
    height: Float,
    cornerRadius: Float,
    isFirst: Boolean,
    isLast: Boolean
): Path {
    return Path().apply {
        if (isFirst && isLast) {
            addRoundRect(
                RoundRect(
                    left = startX,
                    top = 0f,
                    right = endX,
                    bottom = height,
                    radiusX = cornerRadius,
                    radiusY = cornerRadius
                )
            )
        } else if (isFirst) {
            moveTo(startX, cornerRadius)
            quadraticBezierTo(startX, 0f, startX + cornerRadius, 0f)
            lineTo(endX, 0f)
            lineTo(endX, height)
            lineTo(startX + cornerRadius, height)
            quadraticBezierTo(startX, height, startX, height - cornerRadius)
            close()
        } else if (isLast) {
            moveTo(startX, 0f)
            lineTo(endX - cornerRadius, 0f)
            quadraticBezierTo(endX, 0f, endX, cornerRadius)
            lineTo(endX, height - cornerRadius)
            quadraticBezierTo(endX, height, endX - cornerRadius, height)
            lineTo(startX, height)
            close()
        }
    }
}

data class ProgressSegment(
    val progress: Float,
    val colors: List<Color>
)
