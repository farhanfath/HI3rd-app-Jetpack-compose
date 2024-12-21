package basic.training.jetpack.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import basic.training.jetpack.data.model.Valkyrie
import basic.training.jetpack.ui.theme.Hi3rdJetpackTheme
import basic.training.jetpack.utils.Constants

@Composable
fun ItemValkyrie(modifier: Modifier, item: Valkyrie) {
    Card(modifier = modifier,
        shape = RoundedCornerShape(corner = CornerSize(10.dp))
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
        ) {
            ImageLoader(
                modifier = Modifier
                    .border(width = 1.dp, color = Color.Black, shape = CircleShape)
                    .clip(CircleShape)
                    .size(50.dp),
                imageUrl = item.image
            )
            Spacer(modifier = Modifier.size(20.dp))
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(fontSize = 14.sp, text = item.name,
                    modifier = Modifier.padding(bottom = 5.dp)
                )
                HorizontalDivider(color = Color.Gray, thickness = 1.dp)
                Text(fontSize = 12.sp, text = item.weapon,
                    modifier = Modifier.padding(top = 5.dp)
                )
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