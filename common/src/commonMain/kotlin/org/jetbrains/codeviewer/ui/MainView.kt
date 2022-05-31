package org.jetbrains.codeviewer.ui

import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import org.jetbrains.codeviewer.platform.HomeFolder
import org.jetbrains.codeviewer.ui.common.AppTheme
import org.jetbrains.codeviewer.ui.common.Settings
import org.jetbrains.codeviewer.ui.editor.Editors
import org.jetbrains.codeviewer.ui.filetree.FileTree

@Composable
fun MainView() {
    var folder by remember { mutableStateOf(HomeFolder) }
    val codeViewer = remember(folder) {
        val editors = Editors()

        CodeViewer(
            editors = editors,
            fileTree = FileTree(folder, editors),
            settings = Settings()
        )
    }

    DisableSelection {
        MaterialTheme(
            colors = AppTheme.colors.material
        ) {
            Surface {
                CodeViewerView(codeViewer, onFolderChange = { newFolder ->
                    if (newFolder != null) {
                        folder = newFolder
                    }
                })
            }
        }
    }
}