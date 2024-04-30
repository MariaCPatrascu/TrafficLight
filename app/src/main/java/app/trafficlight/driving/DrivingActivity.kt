package app.trafficlight.driving

import android.os.Bundle
import app.trafficlight.R
import app.trafficlight.databinding.ActivityDrivingBinding
import app.trafficlight.mvi.NavDirection
import app.trafficlight.mvi.ReactiveActivity
import app.trafficlight.mvi.observeNavigation
import app.trafficlight.traffic.light.TrafficLightActivity
import app.trafficlight.util.FragmentUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DrivingActivity : ReactiveActivity<NavDirection>() {
    @Inject
    lateinit var drivingFragment: DrivingFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isFinishing) return
        val viewBinding =
            ActivityDrivingBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        observeNavigation(DrivingViewModel::class)
        FragmentUtil.addFragment(
            supportFragmentManager,
            drivingFragment,
            R.id.main_content_container
        )
    }

    override fun navigate(navDirection: NavDirection) {
        when (navDirection) {
            is Driving.DrivingNavigation.GoToTrafficLightActivity -> TrafficLightActivity.startActivity(
                this,
                navDirection.carModel
            )
        }
    }
}