package com.example.smartplantcare.ui.theme.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import com.example.smartplantcare.ui.theme.*


private data class PlantOption(val label: String, val icon: ImageVector)

private val plantOptions = listOf(
    PlantOption("Potted plants indoor",    Icons.Outlined.Home),
    PlantOption("Potted plants outdoor",   Icons.Outlined.Deck),
    PlantOption("Garden plants in ground", Icons.Outlined.LocalFlorist)
)

// ── Screen ──────────────────────────────────────────────────────────────────
@Composable
fun SplashScreen2(
    onNext : () -> Unit,
    onBack : () -> Unit
) {
    var selected by remember { mutableStateOf(0) }

    var headerVisible by remember { mutableStateOf(false) }
    var cardVisible   by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        headerVisible = true
        delay(200)
        cardVisible   = true
    }

    val headerAlpha by animateFloatAsState(
        targetValue   = if (headerVisible) 1f else 0f,
        animationSpec = tween(500),
        label         = "headerAlpha"
    )
    val cardOffset by animateFloatAsState(
        targetValue   = if (cardVisible) 0f else 80f,
        animationSpec = tween(500, easing = FastOutSlowInEasing),
        label         = "cardOffset"
    )
    val cardAlpha by animateFloatAsState(
        targetValue   = if (cardVisible) 1f else 0f,
        animationSpec = tween(500),
        label         = "cardAlpha"
    )

    Box(modifier = Modifier.fillMaxSize()) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.44f)
                .background(MintBackground)
                .graphicsLayer { alpha = headerAlpha }
        ) {
            BackButton(
                onClick  = onBack,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 14.dp, start = 16.dp)
            )


            PlantImage(
                modifier = Modifier
                    .size(160.dp)
                    .align(Alignment.BottomCenter)
                    .offset(y = (-110).dp)
                    .background(Color.Transparent)
            )
        }

        // White card section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.63f)
                .align(Alignment.BottomCenter)
                .graphicsLayer {
                    translationY = cardOffset
                    alpha        = cardAlpha
                }
                .clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
                .background(Color.White)
                .padding(horizontal = 24.dp, vertical = 28.dp)
        ) {
            // Smaller spacer since image is now above the card
            Spacer(Modifier.height(16.dp))

            Text(
                text       = "Where are your plants",
                fontSize   = 22.sp,
                fontWeight = FontWeight.Bold,
                color      = TextDark
            )
            Text(
                text     = "You can pick multiple options",
                fontSize = 13.sp,
                color    = TextGray,
                modifier = Modifier.padding(top = 4.dp, bottom = 20.dp)
            )

            plantOptions.forEachIndexed { index, option ->
                PlantOptionRow(
                    label      = option.label,
                    icon       = option.icon,
                    isSelected = selected == index,
                    onClick    = { selected = index }
                )
                if (index < plantOptions.lastIndex) Spacer(Modifier.height(12.dp))
            }

            Spacer(Modifier.weight(1f))

            Row(
                modifier              = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment     = Alignment.CenterVertically
            ) {
                TextButton(onClick = onNext) {
                    Text("Skip", color = TextGray, fontSize = 15.sp)
                }
                Button(
                    onClick  = onNext,
                    shape    = RoundedCornerShape(24.dp),
                    colors   = ButtonDefaults.buttonColors(containerColor = GreenPrimary),
                    modifier = Modifier.height(48.dp).width(120.dp)
                ) {
                    Text("Next →", color = Color.White, fontSize = 15.sp)
                }
            }
        }
    }
}

// ── Option row ───────────────────────────────────────────────────────────────
@Composable
private fun PlantOptionRow(
    label      : String,
    icon       : ImageVector,
    isSelected : Boolean,
    onClick    : () -> Unit
) {
    val borderColor = if (isSelected) GreenPrimary else OptionBorder
    val bgColor     = if (isSelected) Color(0xFFF0FBF0) else Color.White

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .border(1.5.dp, borderColor, RoundedCornerShape(14.dp))
            .background(bgColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment     = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier         = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE8F5E9)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector      = icon,
                    contentDescription = null,
                    tint             = GreenPrimary,
                    modifier         = Modifier.size(20.dp)
                )
            }
            Spacer(Modifier.width(12.dp))
            Text(
                text       = label,
                fontSize   = 14.sp,
                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                color      = TextDark
            )
        }

        Box(
            modifier         = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .border(2.dp, if (isSelected) GreenPrimary else Color(0xFFCCCCCC), CircleShape)
                .background(if (isSelected) GreenPrimary else Color.White),
            contentAlignment = Alignment.Center
        ) {
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreen2Preview() {
    PlantAppTheme {
        SplashScreen2(
            onNext = {},
            onBack = {}
        )
    }
}