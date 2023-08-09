package me.mauricee.lama.root.home

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import me.mauricee.lama.login.ObserveZenAuthState
import me.mauricee.lama.login.ZenAuthState
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class RootViewModel(
    @Assisted private val coroutineScope: CoroutineScope,
//    private val updateUserDetails: UpdateUserDetails,
//    observeUserDetails: ObserveUserDetails,
//    private val logoutTrakt: LogoutTrakt,
//    private val logger: Logger,
) {

    init {
        coroutineScope.launch {
//            observeUserDetails.flow.collect { user ->
//                logger.setUserId(user?.username ?: "")
//            }
        }
//        observeUserDetails(ObserveUserDetails.Params("me"))

        coroutineScope.launch {
//            observeTraktAuthState.flow.collect { state ->
//                if (state == TraktAuthState.LOGGED_IN) refreshMe()
//            }
        }
//        zenAuthState(Unit)
    }
//
//    private fun refreshMe() {
//        coroutineScope.launch {
//            try {
//                updateUserDetails(UpdateUserDetails.Params("me", false))
//            } catch (e: ResponseException) {
//                if (e.response.status == HttpStatusCode.Unauthorized) {
//                    // If we got a 401 back from Trakt, we should clear out the auth state
//                    logoutTrakt()
//                }
//            } catch (ce: CancellationException) {
//                throw ce
//            } catch (t: Throwable) {
//                // no-op
//            }
//        }
//    }

    fun clear() {
        coroutineScope.cancel()
    }
}
