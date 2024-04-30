package app.trafficlight.traffic.light

import app.trafficlight.mvi.NavDirection
import app.trafficlight.mvi.ReactiveViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrafficLightViewModel @Inject constructor(
    override val reducer: TrafficLight.Reducer
) : ReactiveViewModel<TrafficLight.State, TrafficLight.Intent, TrafficLight.Change, NavDirection>(
    TrafficLight.State.INITIAL
) {
    override suspend fun process(intent: TrafficLight.Intent, state: TrafficLight.State) =
        when (intent) {
            is TrafficLight.Intent.LoadContent -> loadContent()
        }

    private suspend fun loadContent() = change(TrafficLight.Change.SetTrafficLightData(fetchData()))

    fun fetchData(): List<TrafficLightData> = TrafficLightData.entries
}
