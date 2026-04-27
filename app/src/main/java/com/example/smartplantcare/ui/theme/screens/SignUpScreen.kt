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
                .fillMaxHeight(0.28f)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            GreenPrimary,
                            GreenPrimary.copy(alpha = 0.85f),
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

            // Welcome content - Fixed positioning to avoid overlap
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 16.dp, bottom = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Logo
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .background(Color.White.copy(alpha = 0.2f), CircleShape)
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Spa,
                        contentDescription = "Logo",
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Spacer(Modifier.height(10.dp))

                Text(
                    text = "Create Account",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Join our plant care community today",
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.9f),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        // ── White Card ──────────────────────────────────────────────────────
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.80f)
                .align(Alignment.BottomCenter)
                .graphicsLayer {
                    translationY = cardOffset
                    alpha        = cardAlpha
                }
                .shadow(20.dp, RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 32.dp)
        ) {
            Text(
                text       = "Sign Up",
                fontSize   = 26.sp,
                fontWeight = FontWeight.Bold,
                color      = TextDark
            )
            Text(
                text       = "Create your account to get started",
                fontSize   = 13.sp,
                color      = TextGray,
                modifier   = Modifier.padding(top = 4.dp, bottom = 24.dp)
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

            Spacer(Modifier.height(16.dp))

            // Password field
            OutlinedTextField(
                value             = password,
                onValueChange     = { password = it },
                singleLine        = true,
                label             = { Text("Password", fontSize = 13.sp) },
                placeholder       = { Text("Create a strong password", fontSize = 14.sp) },
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

            Spacer(Modifier.height(16.dp))

            // Confirm password field
            OutlinedTextField(
                value             = confirmPassword,
                onValueChange     = { confirmPassword = it },
                singleLine        = true,
                label             = { Text("Confirm Password", fontSize = 13.sp) },
                placeholder       = { Text("Re-enter your password", fontSize = 14.sp) },
                leadingIcon       = {
                    Icon(
                        Icons.Default.LockReset,
                        contentDescription = null,
                        tint = GreenPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { confirmHidden = !confirmHidden }) {
                        Icon(
                            imageVector = if (confirmHidden) Icons.Default.VisibilityOff
                            else Icons.Default.Visibility,
                            contentDescription = "Toggle password visibility",
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
                visualTransformation = if (confirmHidden) PasswordVisualTransformation()
                else VisualTransformation.None,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            )

            // Password hint
            Text(
                text = "Password must be at least 8 characters with letters & numbers",
                fontSize = 11.sp,
                color = TextGray,
                lineHeight = 14.sp,
                modifier = Modifier.padding(top = 6.dp, bottom = 24.dp)
            )

            // Sign Up button
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
                    text = "Create Account",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
            }

            // Terms text
            Text(
                text = "By continuing, you agree to our Terms of Service\nand Privacy Policy",
                fontSize = 11.sp,
                color = TextGray,
                textAlign = TextAlign.Center,
                lineHeight = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 18.dp)
            )

            // Divider
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
                    text = "  or sign up with  ",
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

            Spacer(Modifier.height(20.dp))

            // Google button only
            OutlinedButton(
                onClick = { /* Handle Google sign up */ },
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

            Spacer(Modifier.height(24.dp))

            // Login link
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Already have an account? ",
                    fontSize = 14.sp,
                    color = TextGray
                )
                Text(
                    text = "Log In",
                    fontSize = 14.sp,
                    color = GreenPrimary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onLogin
                    )
                )
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}