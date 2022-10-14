package ohad.sa.task.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Feed(
    @SerializedName("data")
    val items: MutableList<Post>,
):  Parcelable