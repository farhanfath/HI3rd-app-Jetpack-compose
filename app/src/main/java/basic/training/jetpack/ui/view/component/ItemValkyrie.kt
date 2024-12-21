package basic.training.jetpack.ui.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import basic.training.jetpack.R
import basic.training.jetpack.data.Constants
import basic.training.jetpack.data.Valkyrie
import basic.training.jetpack.ui.theme.Hi3rdJetpackTheme

@Composable
fun ItemValkyrie(modifier: Modifier, item: Valkyrie) {
    Card(modifier = Modifier
        .fillMaxWidth(),
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
            )
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(fontSize = 14.sp, text = item.name)
                HorizontalDivider()
                Text(fontSize = 12.sp, text = item.weapon)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Hi3rdJetpackTheme {
        ItemValkyrie(modifier = Modifier, item = Constants.listValkyrie[0])
    }
}