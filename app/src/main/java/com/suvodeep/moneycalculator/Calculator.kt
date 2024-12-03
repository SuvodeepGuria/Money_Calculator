package com.suvodeep.moneycalculator

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.suvodeep.moneycalculator.ui.mainContent.InputSection
import com.suvodeep.moneycalculator.ui.mainContent.RoundedButton
import com.suvodeep.moneycalculator.ui.mainContent.TotalPerPerson
import kotlin.math.roundToInt

@SuppressLint("AutoboxingStateValueProperty")
@Preview
@Composable
fun Calculator() {
    val inputAmountState = remember { mutableStateOf("") }
    val checkValidState = remember(inputAmountState.value) { inputAmountState.value.trim().isNotEmpty() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val splitState = remember { mutableIntStateOf(1) }
    val tipAmount = remember { mutableIntStateOf(0) }  // Tip as an integer
    val totalBill = inputAmountState.value.toFloatOrNull() ?: 0f
    val totalWithTip = totalBill + tipAmount.intValue
    val totalPerPerson = if (splitState.intValue > 0) totalWithTip / splitState.intValue else 0f

    Column(modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center) {
        TotalPerPerson(totalAmount = "%.2f".format(totalPerPerson))
        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 20.dp)
                .fillMaxWidth()
                .height(350.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                InputSection(
                    valueState = inputAmountState,
                    labelId = "Bill Amount",
                    enabled = true,
                    keyboardType = KeyboardType.Number,
                    modifier = Modifier.fillMaxWidth(),
                    onAction = KeyboardActions {
                        if (!checkValidState) return@KeyboardActions
                        keyboardController?.hide()
                    },
                    singledLine = true,
                    imeAction = ImeAction.Done
                )

                Spacer(modifier = Modifier.height(20.dp))

                if (checkValidState) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Split Between:",
                                fontSize = 25.sp)

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 20.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RoundedButton(
                                    modifier = Modifier.size(40.dp),
                                    imageVector = Icons.Rounded.Remove,
                                    onClick = {
                                        if (splitState.intValue > 1) splitState.value -= 1
                                    },
                                )
                                Text("${splitState.value}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp)
                                RoundedButton(
                                    modifier = Modifier.size(40.dp),
                                    imageVector = Icons.Rounded.Add,
                                    onClick = {
                                        splitState.value += 1
                                    },
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                "Tip: ₹${tipAmount.value}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                            Slider(
                                value = tipAmount.value.toFloat(),
                                onValueChange = { newValue -> tipAmount.value = newValue.roundToInt() },
                                modifier = Modifier.fillMaxWidth(0.8f),
                                valueRange = 0f..200f
                            )
                        }
                    }
                }
            }
        }
    }
}
