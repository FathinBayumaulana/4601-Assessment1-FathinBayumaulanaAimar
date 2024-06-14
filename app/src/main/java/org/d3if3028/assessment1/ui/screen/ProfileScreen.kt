package org.d3if3028.assessment1.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3028.assessment1.R
import org.d3if3028.assessment1.navigation.Screen
import org.d3if3028.assessment1.ui.theme.Assessment1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavHostController){
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = stringResource(id = R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = { Text(text = stringResource(id = R.string.fitur3)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { paddingValues ->
        ProfileContent(Modifier.padding(paddingValues))
    }
}

@Composable
fun ProfileContent(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.kuradarat),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.padding(vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.nama_diri),
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            Spacer(
                modifier = Modifier.padding(6.dp)
            )
            Text(
                text = stringResource(id = R.string.nim),
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            Spacer(
                modifier = Modifier.padding(6.dp)
            )
            Text(
                text = stringResource(id = R.string.kelas),
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PublicPreview() {
    Assessment1Theme {
        ProfileScreen(rememberNavController())
    }
}