package com.example.smartplantcare.ui.theme.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartplantcare.ui.theme.*

@Composable
fun SignUpScreen(
    onBack  : () -> Unit,
    onLogin : () -> Unit
) {
    var email           by remember { mutableStateOf("") }
    var password        by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordHidden  by remember { mutableStateOf(true) }
    var confirmHidden   by remember { mutableStateOf(true) }

    var cardVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { cardVisible = true }

    val cardOffset by animateFloatAsState(
        targetValue   = if (cardVisible) 0f else 60f,
        animationSpec = tween(450, easing = FastOutSlowInEasing),
        label         = "cardOffset"
    )
    val cardAlpha by animateFloatAsState(
        targetValue   = if (cardVisible) 1f else 0f,
        animationSpec = tween(450),
        label         = "cardAlpha"
    )

    Box(modifier = Modifier.fillMaxSize()) {

        // ── Mint header ──────────────────────────────────────────────────────
        //  Same structure as LoginScreen: TopNavBar Row with back button left,
        //  Plant logo right, both inside a single horizontal pass.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.28f)
                .background(MintBackground)
        ) {
            TopNavBar(
                onBack   = onBack,
                showLogo = true,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }

        // ── White card slides up from bottom ─────────────────────────────────
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.82f)
                .align(Alignment.BottomCenter)
                .graphicsLayer {
                    translationY = cardOffset
                    alpha        = cardAlpha
                }
                .clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 28.dp, vertical = 32.dp)
        ) {
            Text(
                text       = "Sign Up",
                fontSize   = 26.sp,
                fontWeight = FontWeight.Bold,
                color      = TextDark
            )
            Text(
                text       = "Keep your plants alive by watering, providing sunlight, and checking pests.",
                fontSize   = 13.sp,
                color      = TextGray,
                lineHeight = 18.sp,
                modifier   = Modifier.padding(top = 6.dp, bottom = 24.dp)
            )

            AuthField(label = "Your email address") {
                AuthTextField(
                    value         = email,
                    onValueChange = { email = it },
                    keyboardType  = KeyboardType.Email
                )
            }
            Spacer(Modifier.height(16.dp))

            AuthField(label = "Choose your password") {
                AuthTextField(
                    value            = password,
                    onValueChange    = { password = it },
                    keyboardType     = KeyboardType.Password,
                    isPassword       = true,
                    passwordHidden   = passwordHidden,
                    onTogglePassword = { passwordHidden = !passwordHidden }
                )
            }
            Spacer(Modifier.height(16.dp))

            AuthField(label = "Confirm your password") {
                AuthTextField(
                    value            = confirmPassword,
                    onValueChange    = { confirmPassword = it },
                    keyboardType     = KeyboardType.Password,
                    isPassword       = true,
                    passwordHidden   = confirmHidden,
                    onTogglePassword = { confirmHidden = !confirmHidden }
                )
            }

            Spacer(Modifier.height(28.dp))
            PrimaryButton(text = "Continue", onClick = {})
            Spacer(Modifier.height(16.dp))

            // Log In link
            Row(
                modifier              = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Already have an account? ", fontSize = 14.sp, color = TextGray)
                Text(
                    text       = "Log In",
                    fontSize   = 14.sp,
                    color      = GreenPrimary,
                    fontWeight = FontWeight.SemiBold,
                    modifier   = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication        = null,
                        onClick           = onLogin
                    )
                )
            }
        }
    }
}