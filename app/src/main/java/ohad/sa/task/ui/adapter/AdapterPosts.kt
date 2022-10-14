package ohad.sa.task.ui.adapter


import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ImageView.ScaleType
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso
import ohad.sa.task.R
import ohad.sa.task.models.Post
import ohad.sa.utils.getTimePast
import javax.inject.Inject


class AdapterPosts @Inject constructor(
    private val postsDiffUtils: PostsDiffUtils,
    private val picasso: Picasso,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: MutableList<Post> = mutableListOf()
    var likeClickedListener = LikeClickedListener { }

    fun interface LikeClickedListener {
        fun likeClicked(item: Post)
    }

    private fun getItem(position: Int): Post {
        return items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PostViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.post, viewGroup, false)
        return PostViewHolder(view)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh = holder as PostViewHolder
        val post = getItem(position)


        vh.userNameTextView.text = post.userName
        vh.timeTextView.text = post.date.getTimePast()
        vh.descriptionTextView.text = post.text
        vh.likesTextView.text = "${post.likes} likes"
        vh.commentsTextView.text = "${post.comments} comments"
        val lst = listOf(vh.image1, vh.image2)
        lst.forEach { it.visibility = View.GONE }
        post.images?.mapNotNull { it }?.take(2)?.forEachIndexed { index, items ->
            lst[index].visibility = View.VISIBLE
            picasso.load(items).into(lst[index])
        }
        picasso.load(post.avatarLink).into(vh.userImageView)
        picasso.load(R.drawable.like2).into(vh.likeBlueImageView)
        picasso.load(R.drawable.like3).into(vh.likeBlackImageView)
        vh.userImageView.toRound()
        setLiked(post.didLike, post.likes, vh)
        vh.likeButtonView.setOnClickListener {
            post.didLike = !post.didLike
            setLiked(post.didLike, post.likes, vh)
            likeClickedListener.likeClicked(post)
        }

    }

    private fun setLiked(didLike: Boolean, likes: Int,vh: PostViewHolder) {
        if (didLike) {
            vh.likeBlueImageView.visibility = View.VISIBLE
            vh.likeBlackImageView.visibility = View.INVISIBLE
            vh.likeButtonTextView.setTextColor(Color.parseColor("#4267B2"))
            vh.likesTextView.text =  if (likes > 0)  "you and $likes likes" else "like by you"
        } else {
            vh.likeBlackImageView.visibility = View.VISIBLE
            vh.likeBlueImageView.visibility = View.INVISIBLE
            vh.likeButtonTextView.setTextColor(Color.parseColor("#808080"))
            vh.likesTextView.text = "$likes likes"
        }
    }

    fun removeItem(item: Post) {
        val index = items.indexOf(item)
        notifyItemRemoved(index)
        items.remove(item)
    }

    fun updateList(updatedUserList: List<Post>) {
        postsDiffUtils.oldList = items
        postsDiffUtils.newList = updatedUserList
        val diffResult = DiffUtil.calculateDiff(postsDiffUtils)
        items.clear()
        items.addAll(updatedUserList)
        diffResult.dispatchUpdatesTo(this)
    }

    fun addItem(item: Post) {
        if (!items.contains(item)) {
            items.add(item)
            notifyItemInserted(items.indexOf(item))
        }
    }

    private fun RoundedImageView.toRound() {
        this.scaleType = ScaleType.CENTER_CROP
        this.cornerRadius = 10f
        this.borderWidth = 1f
        this.isOval = true
    }


    class PostViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {

        val userImageView: RoundedImageView
        val userNameTextView: TextView
        val timeTextView: TextView
        val descriptionTextView: TextView
        val likesTextView: TextView
        val commentsTextView: TextView
        val likeBlackImageView: ImageView
        val likeBlueImageView: ImageView
        val commentImageView: ImageView
        val image1: ImageView
        val image2: ImageView
        val likeButtonTextView: TextView
        val commentButtonView : View
        val likeButtonView : View

        init {
            userImageView = itemView.findViewById(R.id.userImageView)
            userNameTextView = itemView.findViewById(R.id.userNameTextView)
            timeTextView = itemView.findViewById(R.id.timeTextView)
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView)
            likesTextView = itemView.findViewById(R.id.likesTextView)
            commentsTextView = itemView.findViewById(R.id.commentsTextView)
            likeBlackImageView = itemView.findViewById(R.id.likeBlackImageView)
            likeBlueImageView = itemView.findViewById(R.id.likeBlueImageView)
            commentImageView = itemView.findViewById(R.id.commentImageView)
            image1 = itemView.findViewById(R.id.image1)
            image2 = itemView.findViewById(R.id.image2)
            likeButtonTextView = itemView.findViewById(R.id.likeButtonTextView)
            commentButtonView = itemView.findViewById(R.id.commentButtonView)
            likeButtonView = itemView.findViewById(R.id.likeButtonView)
        }
    }

}