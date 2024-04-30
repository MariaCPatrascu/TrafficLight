package app.trafficlight.traffic.light

import junit.framework.TestCase.assertTrue
import org.junit.Test

class TrafficLightViewModelTest {
    @Test
    fun fetchData() {
        val reducer = TrafficLight.Reducer()
        val trafficLightViewModel = TrafficLightViewModel(reducer)
        assertTrue(
            trafficLightViewModel.fetchData().isNotEmpty()
        )
    }
}