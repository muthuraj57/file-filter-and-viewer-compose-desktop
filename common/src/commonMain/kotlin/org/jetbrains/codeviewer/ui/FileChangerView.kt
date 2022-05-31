import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.codeviewer.platform.File
import org.jetbrains.codeviewer.platform.FileDialog

@Composable
fun FileChangerView(modifier: Modifier = Modifier, onFolderChange: (file: File?) -> Unit) {
    var changeFolder by remember { mutableStateOf(false) }
    Text(
        "Change Folder",
        color = LocalContentColor.current.copy(alpha = 0.60f),
        fontSize = 14.sp,
        modifier = Modifier.then(modifier).clickable { changeFolder = true }.padding(horizontal = 8.dp).padding(bottom = 4.dp)
    )
    if (changeFolder) {
        FileDialog("Select folder to view", selectDirectory = true, onCloseRequest = {
            onFolderChange(it)
            changeFolder = false
        })
    }
}