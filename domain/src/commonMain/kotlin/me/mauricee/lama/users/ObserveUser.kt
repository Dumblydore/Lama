package me.mauricee.lama.users

import kotlinx.coroutines.flow.Flow
import me.mauricee.lama.SubjectInteractor
import me.tatarka.inject.annotations.Inject

@Inject
class ObserveUser(
    private val repository: UserRepository
) : SubjectInteractor<UserId, User>() {
    override fun createObservable(params: UserId): Flow<User> = repository.observeUser(params)
}