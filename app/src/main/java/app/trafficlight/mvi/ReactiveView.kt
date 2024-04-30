package app.trafficlight.mvi

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class ReactiveView<S : UiState, I : UiIntent> :
    Fragment(), StateConsumer<S>, IntentProducer<I> by ChannelIntentProducer()

abstract class ReactiveActivity<N : NavDirection> :
    AppCompatActivity(), NavigationConsumer<N>