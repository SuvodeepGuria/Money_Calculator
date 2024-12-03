package com.suvodeep.moneycalculator.ui.mainContent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RoundedButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    onClick: () -> Unit,
    tint: Color = Color.Black.copy(),
    backgroundColor: Color = MaterialTheme.colorScheme.background,  // Use colorScheme
    elevation: Dp = 4.dp
) {
    Surface(
        modifier = modifier.size(40.dp),  // Set the size of the button
        shape = CircleShape,
        color = backgroundColor,
        shadowElevation = elevation,
        onClick = { onClick() }
    ) {
        Box (modifier=Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                tint = tint,
                modifier = Modifier.size(24.dp)  // Set the size of the icon
            )
        }
    }
}
