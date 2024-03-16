package com.example.buisinesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buisinesscard.ui.theme.BuisinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuisinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column (
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxHeight().border(width = 1.dp, color = Color.Black)
                    ) {
                        PersonalInfo(fullName = getString(R.string.fullName), title= getString(R.string.title))
                        Contacts(phone = "+77770889955", socialMediaHandle = "@mega_macho_man", email="mr_x@gmail.com")
                    }
                }
            }
        }
    }


}


@Composable
fun PersonalInfo(fullName: String, title:String) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(bottom = 120.dp)
    )
    {
        Image(
            painter = painterResource(R.drawable.user),
            contentDescription = null,
            modifier = Modifier.size(150.dp).clip(RoundedCornerShape(16.dp))
        )
        Text(
            text = fullName,
            fontSize = 22.sp
        )
        Text(
            text = title,
            color = Color.Green
        )
    }
}

@Composable
fun Contacts(phone: String, socialMediaHandle: String, email: String) {
    Column (
        horizontalAlignment = Alignment.Start,
    ) {
        Row (
            horizontalArrangement = Arrangement.spacedBy(6.dp, alignment = Alignment.CenterHorizontally),
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Rounded.Call, contentDescription = null)
            Text(
                text = phone
            )
        }
        Row (
            horizontalArrangement = Arrangement.spacedBy(6.dp, alignment = Alignment.CenterHorizontally),
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Rounded.Share, contentDescription = null)
            Text(
                text = socialMediaHandle
            )
        }
        Row (
            horizontalArrangement = Arrangement.spacedBy(6.dp, alignment = Alignment.CenterHorizontally),
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Rounded.Email, contentDescription = null)
            Text(
                text = email
            )
        }

    }
}
