package com.lijewski.sample.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lijewski.sample.R
import com.lijewski.sample.data.model.TextMessage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@Destination
@Composable
fun ListScreen(
    viewModel: ListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ListScreenUi(
        uiState = uiState,
        onTextMessageItemClicked = { viewModel.onTextMessageItemClicked(it) },
        onNewMessageClicked = { viewModel.onAddNewTextMessage() },
        onSaveNewMessage = { viewModel.saveNewTextMessage(it) },
    )
}

@Composable
fun ListScreenUi(
    uiState: ListUiState,
    onTextMessageItemClicked: (Int) -> Unit,
    onNewMessageClicked: () -> Unit,
    onSaveNewMessage: (String) -> Unit,
) {
    val listState = rememberLazyListState()
    val expandedFabState = remember {
        derivedStateOf {
            listState.firstVisibleItemIndex == 0
        }
    }

    if (uiState.isNewMessageDialogVisible) {
        SaveMessageDialog(
            onDismiss = { },
            onSaveBtnClick = onSaveNewMessage
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
        ) {
            itemsIndexed(
                items = uiState.messagesList,
            ) { index, item ->
                TextMessageListItem(
                    textMessage = item.data,
                    isSelected = item.isSelected
                ) { onTextMessageItemClicked(index) }
            }
        }

        ExtendedFloatingActionButton(
            text = {
                Text(text = "Add Message", color = Color.White)
            },
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add New Message",
                    tint = Color.White,
                )
            },
            onClick = onNewMessageClicked,
            expanded = expandedFabState.value,
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
fun TextMessageListItem(textMessage: TextMessage, isSelected: Boolean, onclick: () -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .clickable { onclick() }
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.surfaceContainerHigh,
        )
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                text = textMessage.dateCreated.toString(),
                fontStyle = FontStyle.Italic
            )
            Text(
                text = textMessage.name,
                textDecoration = TextDecoration.Underline
            )
            Text(text = textMessage.text)
        }
    }
}

@Composable
fun SaveMessageDialog(
    onDismiss: () -> Unit,
    onSaveBtnClick: (String) -> Unit
) {
    var text by rememberSaveable { mutableStateOf("") }

    Dialog(
        onDismissRequest = onDismiss,
        DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
    ) {
        Card {
            Column {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color.White, RoundedCornerShape(4.dp)),
                    shape = RoundedCornerShape(4.dp),
                    value = text,
                    label = { stringResource(id = R.string.type_new_message) },
                    onValueChange = { text = it },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    minLines = 4,
                    maxLines = 4,
                    textStyle = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { onSaveBtnClick(text) }) {
                    Text(text = stringResource(id = R.string.save))
                }
            }
        }
    }
}
