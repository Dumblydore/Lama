package me.mauricee.lama.login

import me.mauricee.lama.SubjectInteractor
import me.tatarka.inject.annotations.Inject

@Inject
class ObserveZenAuthState(private val zenAuthRepository: LoginRepository) :
    SubjectInteractor<Unit, ZenAuthState>() {
    override fun createObservable(params: Unit) = zenAuthRepository.state

}