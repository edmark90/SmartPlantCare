package com.example.smartplantcare.ui.theme.screens



import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
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
        //  Takes ~32% of screen height. TopNavBar places back button on the
        //  far left and the Plant logo on the far right inside a single Row.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.32f)
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
                .fillMaxHeight(0.76f)
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
                text       = "log in",
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

            Spacer(Modifier.height(28.dp))
            PrimaryButton(text = "Continue", onClick = {})
            Spacer(Modifier.height(16.dp))

            // Sign Up link
            Row(
                modifier              = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Don't have an account? ", fontSize = 14.sp, color = TextGray)
                Text(
                    text       = "Sign Up",
                    fontSize   = 14.sp,
                    color      = GreenPrimary,
                    fontWeight = FontWeight.SemiBold,
                    modifier   = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication        = null,
                        onClick           = onSignUp
                    )
                )
            }

            Spacer(Modifier.height(20.dp))
            AuthDivider(text = "Or sign up with")
            Spacer(Modifier.height(16.dp))

            SocialButton(
                label   = "Sign up with Google",
                icon    = Icons.Default.Email,  // swap for Google brand icon
                onClick = {}
            )
            Spacer(Modifier.height(12.dp))
            SocialButton(
                label   = "Sign up with Facebook",
                icon    = Icons.Default.Person, // swap for Facebook brand icon
                onClick = {}
            )
        }
    }
}

// ─── Shared auth helpers (imported by SignUpScreen too) ──────────────────────

@Composable
fun AuthField(label: String, content: @Composable () -> Unit) {
    Text(
        text       = label,
        fontSize   = 13.sp,
        fontWeight = FontWeight.SemiBold,
        color      = TextDark,
        modifier   = Modifier.padding(bottom = 6.dp)
    )
    content()
}

@Composable
fun AuthTextField(
    value            : String,
    onValueChange    : (String) -> Unit,
    keyboardType     : KeyboardType = KeyboardType.Text,
    isPassword       : Boolean      = false,
    passwordHidden   : Boolean      = true,
    onTogglePassword : () -> Unit   = {}
) {
    OutlinedTextField(
        value             = value,
        onValueChange     = onValueChange,
        singleLine        = true,
        shape             = RoundedCornerShape(10.dp),
        colors            = OutlinedTextFieldDefaults.colors(
            focusedBorderColor   = GreenPrimary,
            unfocusedBorderColor = InputBorder
        ),
        keyboardOptions      = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = if (isPassword && passwordHidden) PasswordVisualTransformation()
        else VisualTransformation.None,
        trailingIcon = if (isPassword) ({
            IconButton(onClick = onTogglePassword) {
                Icon(
                    imageVector      = if (passwordHidden) Icons.Default.Visibility
                    else Icons.Default.VisibilityOff,
                    contentDescription = "Toggle password",
                    tint             = TextGray
                )
            }
        }) else null,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun AuthDivider(text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        HorizontalDivider(modifier = Modifier.weight(1f), color = InputBorder)
        Text("  $text  ", fontSize = 12.sp, color = TextGray)
        HorizontalDivider(modifier = Modifier.weight(1f), color = InputBorder)
    }
}

@Composable
fun SocialButton(label: String, icon: ImageVector, onClick: () -> Unit) {
    OutlinedButton(
        onClick  = onClick,
        shape    = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth().height(50.dp),
        colors   = ButtonDefaults.outlinedButtonColors(containerColor = SocialBtnBg),
        border   = BorderStroke(1.dp, InputBorder)
    ) {
        Icon(icon, contentDescription = null, tint = TextDark, modifier = Modifier.size(20.dp))
        Spacer(Modifier.width(10.dp))
        Text(label, fontSize = 14.sp, color = TextDark, fontWeight = FontWeight.Medium)
    }
}