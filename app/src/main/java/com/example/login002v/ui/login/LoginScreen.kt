package com.example.login002v.ui.login


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.login002v.R


@OptIn(ExperimentalMaterial3Api::class)
// Permite usar funciones Material 3 qe son experimentales
@Composable  // Genera Interfz Garfica

fun LoginScreen(
    navController: NavController,
    vm: LoginViewModel= viewModel()

){

    val state =vm.uiState
    var showPass by remember { mutableStateOf(false) }

    // darkColorScheme  es una funcion de material3 que define un color oscuro
    val ColorScheme = lightColorScheme(
        primary= Color(0xFF00049E),   // Boton
        onPrimary = Color(0xFF00049E), //Azul,
        onSurface = Color(0xFF1E1D1D), //Gris
    ) // fin dark


    MaterialTheme(
        colorScheme = ColorScheme
    ){ // inicio Aplicar Material



        Scaffold (
            // Crea Estuctra basica de la pantalla Se define topBar, BottomBar


            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            "Marcaciones Reinspección",
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                )
            }

          /*
            topBar = {
                CenterAlignedTopAppBar(
                TopAppBar(title = {Text("Marcaciones Reinspección",
                    color =MaterialTheme.colorScheme.onPrimary,
                )}))
*/
                // Crea un AppBar con un titulo

 //           }// fin topBar
        ) // fin Scaff
        {// Inicio Inner
                innerPadding ->
            // Representa el espacio interno para que no choque con el topBar

            Column (  //   Colaca los elementos de la Ui
                modifier = Modifier
                    .padding(innerPadding)
                    // Evita que quede oculto
                    .fillMaxSize() // Hace que la columnna tome el todo el tamaño
                    .padding(16.dp)
                    .background(Color(0xFFFFFFFF)), // gris Claro
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally  // Centra horizontalmente
                //Define  que elementos dentro la columna estaran separados por 20.dp
            )// fin column
            {// inicio Contenido
                Text(text="Bienvenido !",
                    style= MaterialTheme.typography.headlineMedium,
                    color=MaterialTheme.colorScheme.onPrimary


                ) // Muestra un texto simple en la pantalla




                Image(  // insertar una imagen en la interfaz
                    painter= painterResource(id = R.drawable.logogris),
                    contentDescription = "Logo App",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentScale = ContentScale.Fit
                    // Ajusta la imagen para que encaje dentro del espacio

                ) // Fin Image


// agregar un espacio entre la imagen y el boton

                Spacer(modifier = Modifier.height(66.dp))







                OutlinedTextField(
                    value=state.username,
                    onValueChange = vm::onUsernameChange,
                    label={Text("Usuario")},
                    singleLine = true,
                    modifier=Modifier.fillMaxWidth(0.95f)

                )

                OutlinedTextField(
                    value=state.password,
                    onValueChange = vm::onPasswordChange,
                    label={Text("Contraseña")},
                    singleLine = true,
                    visualTransformation = if (showPass) VisualTransformation.None else
                        PasswordVisualTransformation(),

                    trailingIcon = {
                        TextButton( onClick = {showPass =!showPass})
                        {
                            Text(if (showPass) "Ocultar" else "Ver")
                        }
                    },// fin trail

                            modifier=Modifier.fillMaxWidth(0.95f)

                )// Password





                if (state.error !=null){
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text=state.error ?:"",
                        color= MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold
                    )

                }


// agregar un espacio entre la imagen y el boton

                Spacer(modifier = Modifier.height(66.dp))

                Button(
                    onClick = {/* accion futura*/
                        vm.submit { user ->
                            navController.navigate("DrawerMenu/user") {
                                popUpTo("login") { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                    },
                    enabled = !state.isLoading,
                    modifier = Modifier.fillMaxWidth(0.6f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00049E),     // R=0, G=4, B=158
                        contentColor = Color.White,             // texto en blanco
                        disabledContainerColor = Color(0xFF00049E).copy(alpha = 0.45f),
                        disabledContentColor = Color.White.copy(alpha = 0.7f)
                    )
                ) //fin Button

                {
                   // Text("Presioname")
                    Text(if(state.isLoading) "Validando" else "Iniciar Sesion" )

                } // fin boton



            }// fin Contenido

        } // Fin inner


    } // fin Aplicar Material
}// Fin HomeScreen


@Preview(showBackground = true) // Genera la vista
@Composable  // Genera Interfz Garfica

fun LoginScreenPreview(){

 //   Crear un navController de manera ficticia para fines de la vista previa
    val navController = rememberNavController()

    // Puedes usar un ViewModel simulado aquí si no tienes acceso a uno real
    val vm = LoginViewModel() // Suponiendo que LoginViewModel está correctamente configurado para la vista previa

    LoginScreen(navController = navController, vm = vm)


}// Fin LoginScreen
