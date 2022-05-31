package org.jetbrains.codeviewer.platform

lateinit var _HomeFolder: java.io.File
actual val HomeFolder: File get() = _HomeFolder.toProjectFile()
actual fun FileDialog(
    title: String,
    selectDirectory: Boolean,
    onCloseRequest: (file: File?) -> Unit
) {
    onCloseRequest(HomeFolder)
}