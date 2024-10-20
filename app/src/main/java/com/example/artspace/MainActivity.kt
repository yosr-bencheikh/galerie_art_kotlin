import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import com.example.artspace.R
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtSpaceApp()
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {

    var currentArtworkIndex by remember { mutableStateOf(1) }

    fun suivantArtwork() {
        if (currentArtworkIndex == 1) {
            currentArtworkIndex = 2
        } else if (currentArtworkIndex == 2) {
            currentArtworkIndex = 3
        } else if (currentArtworkIndex == 3) {
            currentArtworkIndex = 1
        }
    }

    fun precedentArtwork() {
        if (currentArtworkIndex == 1) {
            currentArtworkIndex = 3
        } else if (currentArtworkIndex == 2) {
            currentArtworkIndex = 1
        } else if (currentArtworkIndex == 3) {
            currentArtworkIndex = 2
        }
    }


    val (imageResId, title, artist) = when (currentArtworkIndex) {
        1 -> Triple(R.drawable.art1, "Space Queen", "Morysetta")
        2 -> Triple(R.drawable.art2, "Vortex", "Morysetta")
        3 -> Triple(R.drawable.art3, "Connections", "Morysetta")
        else -> Triple(R.drawable.art4, "13.3 Billion Years", "Pandora Mond")
    }

    val year = when (currentArtworkIndex) {
        1 -> "1997"
        2 -> "2004"
        3 -> "2011"
        else -> "1999"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp).verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(imageResId),
            contentDescription = title,
            modifier = Modifier
                .size(300.dp)
                .padding(16.dp)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = "$artist, $year",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(24.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { precedentArtwork() }) {
                Text("Précédente")
            }
            Button(onClick = { suivantArtwork() }) {
                Text("Suivante")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}
