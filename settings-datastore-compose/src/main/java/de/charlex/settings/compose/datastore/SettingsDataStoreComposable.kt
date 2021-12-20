package de.charlex.settings.compose.datastore

import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import de.charlex.settings.datastore.IDataStorePreference
import de.charlex.settings.datastore.SettingsDataStore
import kotlinx.coroutines.launch

val LocalSettingsDataStore = staticCompositionLocalOf<SettingsDataStore> {
    error("LocalSettingsDataStore not present")
}

open class SettingsScope {

    sealed interface Scope {
        class GroupScope(val title: String, val children: List<Scope>) : Scope
        class BooleanScope(val key: IDataStorePreference<Boolean>, val label: String) : Scope
        class IntScope(val key: IDataStorePreference<kotlin.Int>, val label: String) : Scope
        class FloatScope(val key: IDataStorePreference<kotlin.Float>, val label: String) : Scope
        class LongScope(val key: IDataStorePreference<kotlin.Long>, val label: String) : Scope
        class DoubleScope(val key: IDataStorePreference<kotlin.Double>, val label: String) : Scope
        class StringScope(val key: IDataStorePreference<kotlin.String>, val label: String) : Scope
    }

    val settingConfigs = mutableListOf<Scope>()

    open fun group(
        label: String,
        builder: NestedSettingsScope.() -> Unit
    ) {
        val groupScope = NestedSettingsScope().apply(builder)
        settingConfigs.add(Scope.GroupScope(title = label, children = groupScope.settingConfigs))
    }

    fun boolean(key: IDataStorePreference<Boolean>, label: String) {
        settingConfigs.add(Scope.BooleanScope(key, label))
    }

    fun int(key: IDataStorePreference<Int>, label: String) {
        settingConfigs.add(Scope.IntScope(key, label))
    }

    fun float(key: IDataStorePreference<Float>, label: String) {
        settingConfigs.add(Scope.FloatScope(key, label))
    }

    fun long(key: IDataStorePreference<Long>, label: String) {
        settingConfigs.add(Scope.LongScope(key, label))
    }

    fun double(key: IDataStorePreference<Double>, label: String) {
        settingConfigs.add(Scope.DoubleScope(key, label))
    }

    fun string(key: IDataStorePreference<String>, label: String) {
        settingConfigs.add(Scope.StringScope(key, label))
    }
}

class NestedSettingsScope : SettingsScope() {
    @Deprecated(level = DeprecationLevel.ERROR, message = "Nested groups not allowed")
    override fun group(
        label: String,
        builder: NestedSettingsScope.() -> Unit
    ) {
    }
}

@Composable
fun Settings(
    modifier: Modifier = Modifier,
    title: String = "Settings",
    verticalArrangement: Arrangement.Vertical = spacedBy(10.dp),
    contentPadding: PaddingValues = PaddingValues(horizontal = 10.dp, vertical = 26.dp),
    settingsDataStore: SettingsDataStore = LocalSettingsDataStore.current,
    booleanRepresentation: @Composable (settingsDataStore: SettingsDataStore, key: IDataStorePreference<Boolean>, label: String) -> Unit = { settingsDataStore, key, label ->
        BooleanSettings(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = TextFieldDefaults.MinHeight)
                .padding(horizontal = 16.dp),
            settingsDataStore = settingsDataStore,
            key = key,
            label = label
        )
    },
    stringRepresentation: @Composable (settingsDataStore: SettingsDataStore, key: IDataStorePreference<String>, label: String) -> Unit = { settingsDataStore, key, label ->
        StringSettings(
            modifier = Modifier.fillMaxWidth(),
            settingsDataStore = settingsDataStore,
            key = key,
            label = label
        )
    },
    builder: SettingsScope.() -> Unit
) {

    var settingsScope by remember { mutableStateOf(SettingsScope()) }

    LaunchedEffect(builder) {
        settingsScope = SettingsScope().apply(builder)
    }

    LazyColumn(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        contentPadding = contentPadding
    ) {

        item {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = title,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
        }

        settingsScope.settingConfigs.forEach { scope ->

            when (scope) {
                is SettingsScope.Scope.BooleanScope -> {
                    item {
                        booleanRepresentation(
                            settingsDataStore = settingsDataStore,
                            key = scope.key,
                            label = scope.label
                        )
                    }
                }
                is SettingsScope.Scope.GroupScope -> {
                    items(count = scope.children.size + 1) { index ->
                        if (index == 0) {
                            Text(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                text = "${scope.title}",
                                fontWeight = FontWeight.Bold
                            )
                        } else {
                            when (val currentScope = scope.children[index - 1]) {
                                is SettingsScope.Scope.BooleanScope -> {
                                    booleanRepresentation(
                                        settingsDataStore = settingsDataStore,
                                        key = currentScope.key,
                                        label = currentScope.label
                                    )
                                }
                                is SettingsScope.Scope.GroupScope -> {}
                                is SettingsScope.Scope.DoubleScope -> TODO()
                                is SettingsScope.Scope.FloatScope -> TODO()
                                is SettingsScope.Scope.IntScope -> TODO()
                                is SettingsScope.Scope.LongScope -> TODO()
                                is SettingsScope.Scope.StringScope -> {
                                    stringRepresentation(
                                        settingsDataStore = settingsDataStore,
                                        key = currentScope.key,
                                        label = currentScope.label
                                    )
                                }
                            }
                        }
                    }
                }
                is SettingsScope.Scope.DoubleScope -> TODO()
                is SettingsScope.Scope.FloatScope -> TODO()
                is SettingsScope.Scope.IntScope -> TODO()
                is SettingsScope.Scope.LongScope -> TODO()
                is SettingsScope.Scope.StringScope -> {
                    item {
                        stringRepresentation(
                            settingsDataStore = settingsDataStore,
                            key = scope.key,
                            label = scope.label
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BooleanSettings(
    modifier: Modifier = Modifier,
    settingsDataStore: SettingsDataStore = LocalSettingsDataStore.current,
    key: IDataStorePreference<Boolean>,
    label: String = key.preferenceKey.name
) {
    val prefValue by settingsDataStore.get(key)
        .collectAsState(initial = key.defaultValue)
    val coroutineScope = rememberCoroutineScope()

    val interactionSource = remember { MutableInteractionSource() }
    val color by TextFieldDefaults.textFieldColors().labelColor(enabled = true, error = false, interactionSource = interactionSource)

    val focusManager = LocalFocusManager.current
    val focusRequester = FocusRequester()

    val onClick: (Boolean) -> Unit = {
        coroutineScope.launch {
            focusManager.clearFocus()
            focusRequester.requestFocus()
            settingsDataStore.put(key, it)
        }
    }

    Row(
        modifier = modifier
            .focusRequester(focusRequester)
            .focusable(true, interactionSource)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    onClick(prefValue.not())
                }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f, true)) {
            Text(
                text = label,
                style = MaterialTheme.typography.caption,
                color = color
            )
            Text(label)
        }
        Switch(
            modifier = Modifier.requiredWidth(50.dp),
            checked = prefValue,
            interactionSource = interactionSource,
            onCheckedChange = onClick
        )
    }
}

@Composable
fun StringSettings(
    modifier: Modifier = Modifier,
    settingsDataStore: SettingsDataStore = LocalSettingsDataStore.current,
    key: IDataStorePreference<String>,
    label: String = key.preferenceKey.name,
) {
    val prefValue by settingsDataStore.get(key).collectAsState(initial = key.defaultValue)
    val coroutineScope = rememberCoroutineScope()
    TextField(
        modifier = modifier,
        label = {
            Text(text = label)
        },
        value = prefValue,
        onValueChange = {
            coroutineScope.launch {
                settingsDataStore.put(key, it)
            }
        },
        placeholder = { Text(text = key.defaultValue) },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface
        )
    )
}
