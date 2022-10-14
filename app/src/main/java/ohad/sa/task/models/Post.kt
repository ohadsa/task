package ohad.sa.task.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Post")
data class Post (
    @SerializedName("id")
    @PrimaryKey
    val postId: String,
    val userId: String,
    @SerializedName("username")
    val userName: String,
    @SerializedName("avatar")
    val avatarLink: String,
    val shopName: String?,
    val shopId: String?,
    @TypeConverters
    val images: List<String?>?,
    val comments: Int,
    val date: String,
    val text: String,
    val likes: Int,
    var didLike: Boolean,
    val premium: Boolean,
    ) : Parcelable

