package app.trafficlight.driving

import junit.framework.TestCase.assertTrue
import org.junit.Test

class DrivingViewModelTest {
    @Test
    fun isCarModelValid() {
        val carModel = "Corolla"
        val reducer = Driving.Reducer()
        val drivingViewModel = DrivingViewModel(reducer)
        assertTrue(drivingViewModel.isCarModelValid(carModel))
    }
}