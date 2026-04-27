package com.example.smartplantcare.ui.theme.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartplantcare.ui.theme.*

@Composable
fun LoginScreen(
    onBack   : () -> Unit,
    onSignUp : () -> Unit
) {
    var email          by remember { mutableStateOf("") }
    var password       by remember { mutableStateOf("") }
    var passwordHidden by remember { mutableStateOf(true) }

    var cardVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { cardVisible = true }

    val cardOffset by animateFloatAsState(
        targetValue   = if (cardVisible) 0f else 80f,
        animationSpec = tween(600, easing = FastOutSlowInEasing),
        label         = "cardOffset"
    )
    val cardAlpha by animateFloatAsState(
        targetValue   = if (cardVisible) 1f else 0f,
        animationSpec = tween(600),
        label         = "cardAlpha"
    )

    Box(modifier = Modifier.fillMaxSize()) {

        // ── Gradient Header ─────────────────────────────────────────────────
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.35f)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            GreenPrimary,
                            GreenPrimary.copy(alpha = 0.8f),
                            MintBackground
                        )
                    )
                )
        ) {
            // Back button
            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .padding(start = 16.dp, top = 48.dp)
                    .size(40.dp)
                    .background(Color.White.copy(alpha = 0.2f), CircleShape)
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            // Welcome content
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Logo placeholder
                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .background(Color.White.copy(alpha = 0.2f), CircleShape)
                        .padding(15.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Spa,
                        contentDescription = "Logo",
                        tint = Color.White,
                        modifier = Modifier.size(35.dp)
                    )
                }

                Spacer(Modifier.height(16.dp))

                Text(
                    text = "Welcome Back!",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Log in to continue your plant care journey",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.9f),
                    modifier = Modifier.padding(top = 6.dp)
                )
            }
        }

        // ── White Card ──────────────────────────────────────────────────────
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.73f)
                .align(Alignment.BottomCenter)
                .graphicsLayer {
                    translationY = cardOffset
                    alpha        = cardAlpha
                }
                .shadow(20.dp, RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 36.dp)
        ) {
            Text(
                text       = "Log In",
                fontSize   = 26.sp,
                fontWeight = FontWeight.Bold,
                color      = TextDark
            )
            Text(
                text       = "Please enter your credentials to continue",
                fontSize   = 13.sp,
                color      = TextGray,
                modifier   = Modifier.padding(top = 4.dp, bottom = 28.dp)
            )

            // Email field
            OutlinedTextField(
                value             = email,
                onValueChange     = { email = it },
                singleLine        = true,
                label             = { Text("Email Address", fontSize = 13.sp) },
                placeholder       = { Text("you@example.com", fontSize = 14.sp) },
                leadingIcon       = {
                    Icon(
                        Icons.Default.Email,
                        contentDescription = null,
                        tint = GreenPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                },
                shape             = RoundedCornerShape(16.dp),
                colors            = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor   = GreenPrimary,
                    unfocusedBorderColor = InputBorder,
                    focusedLabelColor    = GreenPrimary,
                    unfocusedLabelColor  = TextGray,
                    cursorColor          = GreenPrimary
                ),
                keyboardOptions      = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            )

            Spacer(Modifier.height(20.dp))

            // Password field
            OutlinedTextField(
                value             = password,
                onValueChange     = { password = it },
                singleLine        = true,
                label             = { Text("Password", fontSize = 13.sp) },
                placeholder       = { Text("Enter your password", fontSize = 14.sp) },
                leadingIcon       = {
                    Icon(
                        Icons.Default.Lock,
                        contentDescription = null,
                        tint = GreenPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { passwordHidden = !passwordHidden }) {
                        Icon(
                            imageVector = if (passwordHidden) Icons.Default.VisibilityOff
                            else Icons.Default.Visibility,
                            contentDescription = "Toggle password",
                            tint = TextGray
                        )
                    }
                },
                shape             = RoundedCornerShape(16.dp),
                colors            = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor   = GreenPrimary,
                    unfocusedBorderColor = InputBorder,
                    focusedLabelColor    = GreenPrimary,
                    unfocusedLabelColor  = TextGray,
                    cursorColor          = GreenPrimary
                ),
                keyboardOptions      = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (passwordHidden) PasswordVisualTransformation()
                else VisualTransformation.None,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            )

            // Forgot Password
            Text(
                text = "Forgot Password?",
                fontSize = 13.sp,
                color = GreenPrimary,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 10.dp, bottom = 28.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { /* Handle forgot password */ }
            )

            // Login button
            Button(
                onClick = { },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GreenPrimary,
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 4.dp,
                    pressedElevation = 8.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = "Log In",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
            }

            Spacer(Modifier.height(28.dp))

            // Divider with text
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = InputBorder.copy(alpha = 0.5f),
                    thickness = 1.dp
                )
                Text(
                    text = "  or continue with  ",
                    fontSize = 12.sp,
                    color = TextGray,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = InputBorder.copy(alpha = 0.5f),
                    thickness = 1.dp
                )
            }

            Spacer(Modifier.height(24.dp))

            // Google button
            OutlinedButton(
                onClick = { /* Handle Google login */ },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                    contentColor = TextDark
                ),
                border = BorderStroke(1.5.dp, InputBorder)
            ) {
                Icon(
                    Icons.Default.Email,
                    contentDescription = null,
                    tint = Color(0xFF4285F4),
                    modifier = Modifier.size(22.dp)
                )
                Spacer(Modifier.width(12.dp))
                Text(
                    "Continue with Google",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextDark
                )
            }

            Spacer(Modifier.height(32.dp))

            // Sign Up link
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Don't have an account? ",
                    fontSize = 14.sp,
                    color = TextGray
                )
                Text(
                    text = "Sign Up",
                    fontSize = 14.sp,
                    color = GreenPrimary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onSignUp
                    )
                )
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}