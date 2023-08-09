package me.mauricee.lama.users

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.mauricee.lama.core.base.di.ApplicationScope
import me.mauricee.lama.zen.user.UserApi
import me.tatarka.inject.annotations.Inject
import kotlin.jvm.JvmInline

@Inject
@ApplicationScope
class UserRepository(
    private val api: UserApi
) {
    fun observeUser(userName: UserId): Flow<User> {
        return when (userName) {
            UserId.Me -> flow {
                val user = api.currentUser().payload.person
                emit(User(user.personId, user.firstName, user.lastName))
            }
            else -> TODO()
        }
    }
}


@JvmInline
value class UserId(val value: String) {
    companion object {
        val Me = UserId("me")
    }
}

//TODO
data class User(
    val id: String,
    val firstName: String,
    val lastName: String
)