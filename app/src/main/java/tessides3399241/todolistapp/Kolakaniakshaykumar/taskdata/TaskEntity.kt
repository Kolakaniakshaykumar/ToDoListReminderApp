package tessides3399241.todolistapp.Kolakaniakshaykumar.taskdata

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String,
    val date: String,
    val time: String,
    val priority: String,
    val repeat: String,
    val notify: Boolean,
    val status:String
)
