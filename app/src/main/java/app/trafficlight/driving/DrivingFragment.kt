package app.trafficlight.driving

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import app.trafficlight.databinding.FragmentDrivingBinding
import app.trafficlight.mvi.ReactiveView
import app.trafficlight.mvi.bind
import app.trafficlight.mvi.intent
import javax.inject.Inject

class DrivingFragment @Inject constructor() :
    ReactiveView<Driving.State, Driving.Intent>() {

    private var _viewBinding: FragmentDrivingBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentDrivingBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind(DrivingViewModel::class, sharedWithActivity = true)
        viewBinding.startDrivingButton.setOnClickListener {
            intent(Driving.Intent.OnStartDrivingClicked(viewBinding.carModelEditText.text.toString()))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }

    override fun render(state: Driving.State) {
        with(state) {
            viewBinding.carModelError.isVisible = error != null
            error?.let {
                viewBinding.carModelError.text = getString(error)
            }
        }
    }
}
