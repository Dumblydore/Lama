package me.mauricee.lama.classes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.mauricee.lama.ui.LocalLamaDateFormatter
import me.mauricee.lama.ui.LocalStrings
import me.mauricee.lama.ui.components.lazy.LazyLayout

@Composable
fun ClassDayPage(
    state: ClassesPageState,
    classItem: (ClassListItem) -> Unit,
    register: (ClassListItem) -> Unit,
    modifier: Modifier
) {
    LazyLayout(
        state = state.state,
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp)
        ) {
            items(
                items = state.classes,
                key = { it.id }
            ) {
                ClassCard(
                    item = it,
                    register = register,
                    classItem = classItem,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassCard(
    item: ClassListItem,
    classItem: (ClassListItem) -> Unit,
    register: (ClassListItem) -> Unit,
    modifier: Modifier
) = Card(
    colors = CardDefaults.elevatedCardColors(),
    elevation = CardDefaults.cardElevation(2.dp),
    onClick = { classItem(item) },
    modifier = modifier
) {
    val strings = LocalStrings.current
    val formatter = LocalLamaDateFormatter.current
    val textColor = when (item.state) {
        RegistrationState.NotRegistered,
        RegistrationState.Full -> Color.Unspecified

        RegistrationState.Registered,
        RegistrationState.CheckedIn -> MaterialTheme.colorScheme.tertiary

        RegistrationState.WaitListed -> MaterialTheme.colorScheme.errorContainer
    }
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
        modifier = Modifier.padding(8.dp).fillMaxWidth().height(IntrinsicSize.Min)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight()
        ) {
            Column {
                Text(
                    color = textColor,
                    style = MaterialTheme.typography.titleSmall,
                    text = strings.range(
                        formatter.formatShortTime(item.startTime),
                        formatter.formatShortTime(item.endTime)
                    )
                )
                Text(
                    color = textColor,
                    style = MaterialTheme.typography.titleMedium,
                    text = item.name
                )
            }

            item.instructorName?.let {
                Text(
                    color = textColor,
                    style = MaterialTheme.typography.titleSmall,
                    text = "Instructor: $it"
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            Text(
                color = textColor,
                style = MaterialTheme.typography.titleSmall,
                text = "${item.capacity.takenSpots}/${item.capacity.totalSpots}"
            )

            Button(
                onClick = { register(item) },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(text = "Reserve")
            }
        }
    }
}