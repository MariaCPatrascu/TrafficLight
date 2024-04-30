package app.trafficlight.traffic.light

import app.trafficlight.mvi.UiChange
import app.trafficlight.mvi.UiIntent
import app.trafficlight.mvi.UiReducer
import app.trafficlight.mvi.UiState
import javax.inject.Inject

interface TrafficLight {
    data class State(
        val trafficLightData: List<TrafficLightData>? = null,
    ) : UiState {
        companion object {
            val INITIAL = State()
        }
    }

    sealed class Intent : UiIntent {
        data object LoadContent : Intent()
    }

    sealed class Change : UiChange {
        data class SetTrafficLightData(val trafficLightData: List<TrafficLightData>?) : Change()
    }

    class Reducer @Inject constructor() : UiReducer<State, Change> {
        override fun invoke(state: State, change: Change): State {
            return when (change) {
                is Change.SetTrafficLightData -> state.copy(trafficLightData = change.trafficLightData)
            }
        }
    }
}
