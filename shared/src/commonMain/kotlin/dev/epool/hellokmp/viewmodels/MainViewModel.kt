package dev.epool.hellokmp.viewmodels

import dev.epool.hellokmp.Parcelable
import dev.epool.hellokmp.Parcelize
import dev.epool.hellokmp.di.myKodein
import dev.epool.hellokmp.network.models.GithubUser
import dev.epool.hellokmp.usecases.GetUserUseCase
import org.kodein.di.erased.instance

class MainViewModel : ViewStateViewModel<MainViewModel.ViewState>(ViewState()) {

    private val getUserUseCase: GetUserUseCase by myKodein.instance()

    fun getUser(username: String) {
        reduce { copy(lastQuery = username) }
        reduce { lastQuery?.let { toSuccess(getUserUseCase(it)) } ?: this }
    }

    fun retry() {
        currentViewState.lastQuery?.let { getUser(it) }
    }

    @Parcelize
    data class ViewState(
        internal val lastQuery: String?,
        private val _username: String?,
        override val basicStateView: BasicStateView
    ) : BaseStateView<ViewState>, Parcelable {

        /**
         * This is to be able to do ViewState() in Swift.
         * If we use these default values in the primary constructor, Swift complains with "`init()` is unavailable".
         */
        constructor() : this(null, null, BasicStateView())

        val username: String
            get() = _username.orEmpty()

        val isSuccess: Boolean
            get() = _username != null

        fun toSuccess(githubUser: GithubUser) = copy(_username = githubUser.login)

        override fun copyBasic(basicStateView: BasicStateView) = copy(basicStateView = basicStateView)

    }

}