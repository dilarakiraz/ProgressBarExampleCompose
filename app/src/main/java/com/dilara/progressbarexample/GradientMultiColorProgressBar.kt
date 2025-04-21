package com.dilara.progressbarexample

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.PageSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun GradientMultiColorProgressBar(
    segments: List<GradientProgressSegment>,
    modifier: Modifier = Modifier,
    height: Dp = 16.dp,
    cornerRadius: Dp = 8.dp
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

                val brush = Brush.horizontalGradient(
                    colors = segment.colors,
                    startX = startX,
                    endX = endX
                )

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

data class GradientProgressSegment(
    val progress: Float,
    val colors: List<Color>
)