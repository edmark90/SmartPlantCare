package com.example.smartplantcare.ui.theme.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartplantcare.ui.theme.*
import com.example.smartplantcare.R

@Composable
fun PlantLogoIcon(size: Dp = 22.dp) {
    Canvas(modifier = Modifier.size(width = size * 0.85f, height = size)) {
        val w = this.size.width
        val h = this.size.height

        // ── Left leaf ─────────────────────────────────────────────────────────
        val leftLeaf = Path().apply {
            moveTo(w * 0.50f, h * 0.82f)          // stem base
            cubicTo(
                w * 0.48f, h * 0.55f,             // inner curve down
                w * 0.02f, h * 0.42f,             // outer swing left
                w * 0.22f, h * 0.06f              // leaf tip
            )
            cubicTo(
                w * 0.30f, h * -0.04f,            // tip round
                w * 0.52f, h * 0.18f,             // inner return
                w * 0.50f, h * 0.82f              // back to stem base
            )
            close()
        }
        drawPath(leftLeaf, color = Color(0xFF2E7D32))   // dark green

        // ── Right leaf ────────────────────────────────────────────────────────
        val rightLeaf = Path().apply {
            moveTo(w * 0.50f, h * 0.82f)          // stem base
            cubicTo(
                w * 0.52f, h * 0.55f,             // inner curve down
                w * 0.98f, h * 0.42f,             // outer swing right
                w * 0.78f, h * 0.06f              // leaf tip
            )
            cubicTo(
                w * 0.70f, h * -0.04f,            // tip round
                w * 0.48f, h * 0.18f,             // inner return
                w * 0.50f, h * 0.82f              // back to stem base
            )
            close()
        }
        drawPath(rightLeaf, color = Color(0xFF4CAF50))  // primary green

        // ── Stem ──────────────────────────────────────────────────────────────
        drawLine(
            color       = Color(0xFF388E3C),
            start       = Offset(w * 0.50f, h * 0.80f),
            end         = Offset(w * 0.50f, h * 1.00f),
            strokeWidth = w * 0.12f,
            cap         = StrokeCap.Round
        )
    }
}

// ─── Brand logo row  ← back button  |  [icon] Plant → ──────────────────────
//  Used in Login and Sign Up screens
@Composable
fun PlantLogo(modifier: Modifier = Modifier) {
    Row(
        modifier          = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PlantLogoIcon(size = 22.dp)
        Spacer(Modifier.width(5.dp))
        Text(
            text       = "Plant",
            fontWeight = FontWeight.Bold,
            fontSize   = 15.sp,
            color      = TextDark
        )
    }
}

// ─── Top navigation bar (back button left, logo right) ───────────────────────
//  Drop this into the header Box of any screen that needs both elements.
@Composable
fun TopNavBar(
    onBack      : () -> Unit,
    showLogo    : Boolean    = true,
    modifier    : Modifier   = Modifier
) {
    Row(
        modifier              = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment     = Alignment.CenterVertically
    ) {
        BackButton(onClick = onBack)
        if (showLogo) PlantLogo()
    }
}

// ─── Circular back button ─────────────────────────────────────────────────────
@Composable
fun BackButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(
        onClick  = onClick,
        modifier = modifier
            .size(40.dp)
            .background(Color.White, CircleShape)
    ) {
        Icon(
            imageVector      = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint             = TextDark,
            modifier         = Modifier.size(20.dp)
        )
    }
}

// ─── Primary green button ─────────────────────────────────────────────────────
@Composable
fun PrimaryButton(
    text     : String,
    onClick  : () -> Unit,
    modifier : Modifier = Modifier
) {
    Button(
        onClick  = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        shape    = RoundedCornerShape(26.dp),
        colors   = ButtonDefaults.buttonColors(containerColor = GreenPrimary)
    ) {
        Text(
            text       = text,
            color      = Color.White,
            fontSize   = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}


@Composable
fun OutlinedSecondaryButton(
    text     : String,
    onClick  : () -> Unit,
    modifier : Modifier = Modifier
) {
    OutlinedButton(
        onClick  = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        shape    = RoundedCornerShape(26.dp),
        colors   = ButtonDefaults.outlinedButtonColors(contentColor = TextDark)
    ) {
        Text(
            text       = text,
            fontSize   = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

// ─── Plant image placeholder ──────────────────────────────────────────────────
// Replace with: Image(painter = painterResource(R.drawable.plant_image), ...)
// ─── Plant Image from drawable ──────────────────────────────────────────────
@Composable
fun PlantImage(
    modifier: Modifier = Modifier,
    showBackground: Boolean = true  // Add this parameter
) {
    Box(
        modifier         = modifier,
        contentAlignment = Alignment.Center
    ) {
        if (showBackground) {
            // With background (default)
            Box(
                modifier = Modifier
                    .size(160.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE8F5E9)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.plant),
                    contentDescription = "Plant",
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        } else {
            // Without background - for SplashScreen2
            Image(
                painter = painterResource(id = R.drawable.plant),
                contentDescription = "Plant",
                modifier = Modifier
                    .size(160.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
    }
}