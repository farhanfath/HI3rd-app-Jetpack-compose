package basic.training.jetpack.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import basic.training.jetpack.R
import basic.training.jetpack.utils.Constants
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.brands.Github
import compose.icons.fontawesomeicons.brands.Linkedin

@Composable
fun ProfileScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier
                .padding(20.dp)
                .size(250.dp)
                .clip(CircleShape)
                .border(width = 1.dp, color = MaterialTheme.colorScheme.primary, shape = CircleShape),
            painter = painterResource(R.drawable.me),
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = "Muhammaad Farhan Fathurrohman",
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        Constants.navigateToUrl(
                            context = context,
                            url = Constants.GITHUBURL
                        )
                    }
            ) {
                Icon(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(10.dp),
                    imageVector = FontAwesomeIcons.Brands.Github,
                    contentDescription = "Github"
                )
            }
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        Constants.navigateToUrl(
                            context = context,
                            url = Constants.LINKEDINURL
                        )
                    }
            ) {
                Icon(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(10.dp),
                    imageVector = FontAwesomeIcons.Brands.Linkedin,
                    contentDescription = "Linked In"
                )
            }
        }
    }
}