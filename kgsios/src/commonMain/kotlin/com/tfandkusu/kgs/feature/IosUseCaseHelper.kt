package com.tfandkusu.kgs.feature

import com.tfandkusu.kgs.feature.home.SearchGithubUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class IosUseCaseHelper : KoinComponent {

    val searchGithub: SearchGithubUseCase by inject()
}
