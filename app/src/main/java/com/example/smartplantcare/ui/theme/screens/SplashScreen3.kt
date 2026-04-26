package com.example.smartplantcare.ui.theme.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay
import com.example.smartplantcare.ui.theme.*
import com.example.smartplantcare.R


@Composable
fun SplashScreen3(
    onCreateAccount : () -> Unit,
    onLogin         : () -> Unit
) {
    // Entrance animations
    var textVisible  by remember { mutableStateOf(false) }
    var imageVisible by remember { mutableStateOf(false) }
    var btnsVisible  by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        textVisible  = true
        delay(300)
        imageVisible = true
        delay(200)
        btnsVisible  = true
    }

    val textAlpha by animateFloatAsState(
        targetValue   = if (textVisible) 1f else 0f,
        animationSpec = tween(600),
        label         = "textAlpha"
    )
    val textOffset by animateFloatAsState(
        targetValue   = if (textVisible) 0f else (-30f),
        animationSpec = tween(600, easing = FastOutSlowInEasing),
        label         = "textOffsetX"
    )

    val imageAlpha by animateFloatAsState(
        targetValue   = if (imageVisible) 1f else 0f,
        animationSpec = tween(700),
        label         = "imageAlpha"
    )
    val imageOffset by animateFloatAsState(
        targetValue   = if (imageVisible) 0f else 60f,
        animationSpec = tween(700, easing = FastOutSlowInEasing),
        label         = "imageOffsetY"
    )

    val btnsAlpha by animateFloatAsState(
        targetValue   = if (btnsVisible) 1f else 0f,
        animationSpec = tween(500),
        label         = "btnsAlpha"
    )

    // ── Layout ───────────────────────────────────────────────────────────────
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        // ── "PLANT" rotated label ────────────────────────────────────────────
        Box(
            modifier = Modifier
                .padding(start = 20.dp, top = 80.dp)
                .graphicsLayer {
                    translationX = textOffset
                    alpha        = textAlpha
                }
        ) {
            Row(verticalAlignment = Alignment.Top) {
                // Thin vertical bar
                Box(
                    modifier = Modifier
                        .width(3.dp)
                        .height(90.dp)
                        .background(TextDark)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text       = "Every Plant Has a\nStory—Start\nYours",
                    fontSize   = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color      = TextDark,
                    lineHeight = 34.sp
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.9f)
                .align(Alignment.CenterStart)
                .graphicsLayer {
                    translationY = imageOffset
                    alpha        = imageAlpha
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo2),
                contentDescription = "Plant",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .offset(x = (-50).dp)  // Palakihin mo ito - mas negative = mas sagad sa left
            )
        }

        // ── Buttons ──────────────────────────────────────────────────────────
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .graphicsLayer { alpha = btnsAlpha }
                .padding(horizontal = 32.dp, vertical = 40.dp)
        ) {
            PrimaryButton(
                text    = "Create Account",
                onClick = onCreateAccount
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedSecondaryButton(
                text    = "Login",
                onClick = onLogin
            )
        }
    }
}