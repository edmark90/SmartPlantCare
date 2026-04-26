package com.example.smartplantcare.ui.theme.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import kotlinx.coroutines.delay
import com.example.smartplantcare.ui.theme.*


@Composable
fun SplashScreen1(onGetStarted: () -> Unit) {

    // ── Animation state ──────────────────────────────────────────────────────
    var plantVisible  by remember { mutableStateOf(false) }
    var textVisible   by remember { mutableStateOf(false) }
    var buttonVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        plantVisible  = true
        delay(400)
        textVisible   = true
        delay(300)
        buttonVisible = true
    }

    // Plant: scale + fade
    val plantScale by animateFloatAsState(
        targetValue   = if (plantVisible) 1f else 0.5f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness    = Spring.StiffnessLow
        ),
        label = "plantScale"
    )
    val plantAlpha by animateFloatAsState(
        targetValue   = if (plantVisible) 1f else 0f,
        animationSpec = tween(600),
        label         = "plantAlpha"
    )

    // Text: slide up + fade
    val textOffset by animateFloatAsState(
        targetValue   = if (textVisible) 0f else 40f,
        animationSpec = tween(500, easing = FastOutSlowInEasing),
        label         = "textOffset"
    )
    val textAlpha by animateFloatAsState(
        targetValue   = if (textVisible) 1f else 0f,
        animationSpec = tween(500),
        label         = "textAlpha"
    )

    // Button: fade only
    val buttonAlpha by animateFloatAsState(
        targetValue   = if (buttonVisible) 1f else 0f,
        animationSpec = tween(500),
        label         = "buttonAlpha"
    )

    // ── Layout ───────────────────────────────────────────────────────────────
    Box(
        modifier         = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier            = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {

            PlantImage(
                modifier = Modifier
                    .size(220.dp)
                    .graphicsLayer {
                        scaleX = plantScale
                        scaleY = plantScale
                        alpha  = plantAlpha
                    },
                 // Palakihin ang image sa loob!
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Headline
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = TextDark)) {
                        append("Grow plants\ngreen the ")
                    }
                    withStyle(SpanStyle(color = GreenPrimary)) {
                        append("earth")
                    }
                },
                fontSize    = 26.sp,
                fontWeight  = FontWeight.Bold,
                textAlign   = TextAlign.Center,
                lineHeight  = 34.sp,
                modifier    = Modifier.graphicsLayer {
                    translationY = textOffset
                    alpha        = textAlpha
                }
            )

            Spacer(modifier = Modifier.height(48.dp))

            // CTA button
            PrimaryButton(
                text     = "Get Started",
                onClick  = onGetStarted,
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .graphicsLayer { alpha = buttonAlpha }
            )
        }
    }
}