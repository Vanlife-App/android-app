package com.vanlife.android_app.presentation.ui.login

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.vanlife.android_app.R
import com.vanlife.android_app.common.Constants
import com.vanlife.android_app.presentation.composables.CustomTextField
import com.vanlife.android_app.presentation.navigation.Screens
import com.vanlife.android_app.presentation.ui.theme.spacing
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    navController: NavController
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val coroutine = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.signInState.collectAsState(initial = null)

    val googleSignInState = viewModel.googleState.value

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val result = account.getResult(ApiException::class.java)
                val credentials = GoogleAuthProvider.getCredential(result.idToken, null)
                viewModel.googleSignIn(credentials)
            } catch (it: ApiException) {
                print(it)
            }
        }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Sign in",
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.onBackground,
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
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

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

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                coroutine.launch {
                    viewModel.loginUser(email.value, password.value)
                }
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onBackground
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                modifier = Modifier.padding(MaterialTheme.spacing.small),
                text = "Sign in",
                style = MaterialTheme.typography.h3
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Don't have an account? ",
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.secondaryVariant
            )
            Text(
                modifier = Modifier.clickable { navController.navigate(Screens.SignUpScreen.route) },
                text = "Sign up",
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.primary
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

        Text(
            text = "or connect with",
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.secondaryVariant
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

        IconButton(
            onClick = {
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .requestIdToken(Constants.ServerClientId)
                    .build()

                val googleSignInClient = GoogleSignIn.getClient(context, gso)
                launcher.launch(googleSignInClient.signInIntent)
            }
        ) {
            Icon(
                painter = painterResource(id = R.mipmap.ic_google),
                contentDescription = "Google Icon",
                modifier = Modifier.size(50.dp),
                tint = Color.Unspecified
            )

            LaunchedEffect(state.value?.success) {
                coroutine.launch {
                    if (state.value?.success?.isNotEmpty() == true) {
                        val success = state.value?.success
                        Toast.makeText(context, success, Toast.LENGTH_LONG).show()
                    }
                }
            }

            LaunchedEffect(state.value?.error) {
                coroutine.launch {
                    if (state.value?.error?.isNotEmpty() == true) {
                        val error = state.value?.error
                        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                    }
                }
            }

            LaunchedEffect(googleSignInState.success) {
                coroutine.launch {
                    if (googleSignInState.success != null) {
                        Toast.makeText(context, "Sign in successful", Toast.LENGTH_LONG).show()
                    }
                }
            }

            LaunchedEffect(googleSignInState.error) {
                coroutine.launch {
                    if (googleSignInState.error?.isNotEmpty() == true) {
                        val error = googleSignInState.error
                        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}