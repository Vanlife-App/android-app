package com.vanlife.android_app.presentation.ui.signup

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vanlife.android_app.presentation.composables.CustomTextField
import com.vanlife.android_app.presentation.navigation.Screens
import com.vanlife.android_app.presentation.ui.theme.spacing
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navController: NavController
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }

    val coroutine = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.signUpState.collectAsState(initial = null)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Sign up",
            style = MaterialTheme.typography.h1
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(
            onClick = { /*TODO*/ }
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = Icons.Outlined.Settings,
                contentDescription = "Settings Icon",
                tint = MaterialTheme.colors.primary
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = MaterialTheme.spacing.large),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTextField(
            value = email,
            label = "Email",
            leadingIcon = Icons.Outlined.Email,
            isPassword = false
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        CustomTextField(
            value = password,
            label = "Password",
            leadingIcon = Icons.Outlined.Lock,
            isPassword = true
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        CustomTextField(
            value = confirmPassword,
            label = "Confirm password",
            leadingIcon = Icons.Outlined.Lock,
            isPassword = true
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                coroutine.launch {
                    viewModel.registerUser(email.value, password.value)
                }
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                modifier = Modifier.padding(MaterialTheme.spacing.small),
                text = "Sign up",
                style = MaterialTheme.typography.h2
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Already have an account? ",
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                modifier = Modifier.clickable { navController.navigate(Screens.SignInScreen.route) },
                text = "Sign in",
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.primary
            )
        }

        LaunchedEffect(state.value?.isSuccess) {
            coroutine.launch {
                if (state.value?.isSuccess?.isNotEmpty() == true) {
                    navController.navigate(Screens.HomeScreen.route)
                }
            }
        }

        LaunchedEffect(state.value?.isError) {
            coroutine.launch {
                if (state.value?.isError?.isNotEmpty() == true) {
                    val error = state.value?.isError
                    Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    if (state.value?.isLoading == true) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                strokeWidth = 4.dp
            )
        }
    }
}

