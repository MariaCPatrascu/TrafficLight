package app.trafficlight.driving

import app.trafficlight.R
import app.trafficlight.mvi.NavDirection
import app.trafficlight.mvi.ReactiveViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DrivingViewModel @Inject constructor(
    override val reducer: Driving.Reducer
) : ReactiveViewModel<Driving.State, Driving.Intent, Driving.Change, NavDirection>(
    Driving.State.INITIAL
) {
    override suspend fun process(intent: Driving.Intent, state: Driving.State) = when (intent) {
        is Driving.Intent.OnStartDrivingClicked -> startDriving(intent.carModel)
    }

    private suspend fun startDriving(carModel: String) {
        if (isCarModelValid(carModel)) {
            change(Driving.Change.SetError(null))
        } else {
            change(Driving.Change.SetError(R.string.car_model_too_short))
            return
        }
        navigate(Driving.DrivingNavigation.GoToTrafficLightActivity(carModel))
    }

    fun isCarModelValid(carModel: String): Boolean = carModel.length >= 3
}
