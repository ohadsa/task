package ohad.sa.task.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import ohad.sa.task.models.Post
import javax.inject.Inject


class PostsDiffUtils @Inject constructor(): DiffUtil.Callback() {
    lateinit var oldList: List<Post>
    lateinit var newList: List<Post>

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].postId == newList[newItemPosition].postId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}