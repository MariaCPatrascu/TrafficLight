package app.trafficlight.driving

import app.trafficlight.mvi.NavDirection
import app.trafficlight.mvi.UiChange
import app.trafficlight.mvi.UiIntent
import app.trafficlight.mvi.UiReducer
import app.trafficlight.mvi.UiState
import javax.inject.Inject

interface Driving {
    data class State(
        val error: Int? = null
    ) : UiState {
        companion object {
            val INITIAL = State()
        }
    }

    sealed class Intent : UiIntent {
        data class OnStartDrivingClicked(val carModel: String) : Intent()
    }

    sealed class Change : UiChange {
        data class SetError(val error: Int?) : Change()
    }

    sealed class DrivingNavigation : NavDirection {
        data class GoToTrafficLightActivity(val carModel: String) : DrivingNavigation()
    }

    class Reducer @Inject constructor() : UiReducer<State, Change> {
        override fun invoke(state: State, change: Change): State {
            return when (change) {
                is Change.SetError -> state.copy(error = change.error)
            }
        }
    }
}
