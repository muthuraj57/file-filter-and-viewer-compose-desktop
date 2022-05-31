@file:Suppress("NewApi")

package org.jetbrains.codeviewer.platform

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.AwtWindow
import java.awt.FileDialog
import java.awt.Frame

actual val HomeFolder: File get() = java.io.File(System.getProperty("user.home")).toProjectFile()

@Composable
actual fun FileDialog(
    title: String,
    selectDirectory: Boolean,
    onCloseRequest: (file: File?) -> Unit
) {
    AwtWindow(
        create = {
            if (selectDirectory) {
                System.setProperty("apple.awt.fileDialogForDirectories", "true")
            }
            val parent: Frame? = null
            object : FileDialog(parent, title, LOAD) {
                override fun setVisible(value: Boolean) {
                    super.setVisible(value)
                    if (value) {
                        val filePath = if (file != null) {
                            if (directory != null) {
                                "${directory}/${file}"
                            } else {
                                file
                            }
                        } else {
                            null
                        }
                        if (selectDirectory) {
                            System.setProperty("apple.awt.fileDialogForDirectories", "false")
                        }
                        onCloseRequest(java.io.File(filePath).toProjectFile())
                    }
                }
            }
        },
        dispose = FileDialog::dispose
    )
}