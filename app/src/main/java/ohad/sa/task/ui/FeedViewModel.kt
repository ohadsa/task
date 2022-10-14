package ohad.sa.task.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ohad.sa.task.models.Post
import ohad.sa.task.repository.FeedsRepository

import javax.inject.Inject

@HiltViewModel
class FeedViewModel  @Inject constructor(
    private val repository: FeedsRepository
) : ViewModel() {
    fun likeStateChange (post: Post) {
        viewModelScope.launch {
            repository.editPost(post)
        }
    }

    val feedsFlow : Flow<List<Post>>
        get() = repository.getFeed()
}