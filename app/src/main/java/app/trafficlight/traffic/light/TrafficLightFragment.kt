package app.trafficlight.traffic.light

import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.lifecycleScope
import app.trafficlight.R
import app.trafficlight.databinding.FragmentTrafficLightBinding
import app.trafficlight.mvi.ReactiveView
import app.trafficlight.mvi.bind
import app.trafficlight.mvi.intent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class TrafficLightFragment @Inject constructor() :
    ReactiveView<TrafficLight.State, TrafficLight.Intent>() {

    private var _viewBinding: FragmentTrafficLightBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentTrafficLightBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind(TrafficLightViewModel::class, sharedWithActivity = true)
        intent(TrafficLight.Intent.LoadContent)
        viewBinding.carModelText.text = getCarModelArguments()
    }

    private fun getCarModelArguments(): String {
        arguments?.let {
            if (it.containsKey("CAR_MODEL")) {
                return it.getString("CAR_MODEL") ?: ""
            }
        }
        return ""
    }

    private fun <T> Flow<T>.repeat(): Flow<T> = flow {
        while (true) {
            emitAll(this@repeat)
        }
    }

    private fun setTrafficLight(trafficLightDataList: List<TrafficLightData>) {
        var repeatCount = 0
        flowOf(trafficLightDataList)
            .onEach { trafficLightData ->
                trafficLightData.forEach {
                    when (it) {
                        TrafficLightData.GREEN -> {
                            if (repeatCount == 0) {
                                showDefaultColors()
                            } else {
                                showGreenTransitions()
                            }
                            delay(4000)
                            repeatCount += 1
                        }

                        TrafficLightData.ORANGE -> {
                            showOrangeTransitions()
                            delay(1000)
                        }

                        TrafficLightData.RED -> {
                            showRedTransitions()
                            delay(4000)
                        }
                    }
                }
            }
            .repeat()
            .launchIn(lifecycleScope)
    }

    private fun showRedTransitions() {
        val transitionRed: TransitionDrawable = ResourcesCompat
            .getDrawable(
                resources,
                R.drawable.red_transition_active,
                null
            ) as TransitionDrawable
        viewBinding.trafficLightRed.setImageDrawable(transitionRed)
        transitionRed.startTransition(200)

        viewBinding.trafficLightGreen.setImageDrawable(
            ResourcesCompat
                .getDrawable(
                    resources,
                    R.drawable.traffic_light_inactive_green,
                    null
                )
        )

        val transitionOrange: TransitionDrawable = ResourcesCompat
            .getDrawable(
                resources,
                R.drawable.orange_transition_inactive,
                null
            ) as TransitionDrawable
        viewBinding.trafficLightOrange.setImageDrawable(transitionOrange)
        transitionOrange.startTransition(200)
    }

    private fun showOrangeTransitions() {
        viewBinding.trafficLightRed.setImageDrawable(
            ResourcesCompat
                .getDrawable(
                    resources,
                    R.drawable.traffic_light_inactive_red,
                    null
                )
        )
        val transitionGreen: TransitionDrawable = ResourcesCompat
            .getDrawable(
                resources,
                R.drawable.green_transition_inactive,
                null
            ) as TransitionDrawable
        viewBinding.trafficLightGreen.setImageDrawable(transitionGreen)
        transitionGreen.startTransition(200)

        val transitionOrange: TransitionDrawable = ResourcesCompat
            .getDrawable(
                resources,
                R.drawable.orange_transition_active,
                null
            ) as TransitionDrawable

        viewBinding.trafficLightOrange.setImageDrawable(transitionOrange)
        transitionOrange.startTransition(200)
    }

    private fun showGreenTransitions() {
        val transitionGreen: TransitionDrawable = ResourcesCompat
            .getDrawable(
                resources,
                R.drawable.green_transition_active,
                null
            ) as TransitionDrawable
        viewBinding.trafficLightGreen.setImageDrawable(transitionGreen)
        transitionGreen.startTransition(200)

        viewBinding.trafficLightOrange.setImageDrawable(
            ResourcesCompat
                .getDrawable(
                    resources,
                    R.drawable.traffic_light_inactive_orange,
                    null
                )
        )

        val transitionRed: TransitionDrawable = ResourcesCompat
            .getDrawable(
                resources,
                R.drawable.red_transition_inactive,
                null
            ) as TransitionDrawable
        viewBinding.trafficLightRed.setImageDrawable(transitionRed)
        transitionRed.startTransition(200)
    }

    private fun showDefaultColors() {
        val transitionGreen: TransitionDrawable = ResourcesCompat
            .getDrawable(
                resources,
                R.drawable.green_transition_active,
                null
            ) as TransitionDrawable
        viewBinding.trafficLightGreen.setImageDrawable(transitionGreen)
        transitionGreen.startTransition(200)

        viewBinding.trafficLightOrange.setImageDrawable(
            ResourcesCompat
                .getDrawable(
                    resources,
                    R.drawable.traffic_light_inactive_orange,
                    null
                )
        )
        viewBinding.trafficLightRed.setImageDrawable(
            ResourcesCompat
                .getDrawable(
                    resources,
                    R.drawable.traffic_light_inactive_red,
                    null
                )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }

    override fun render(state: TrafficLight.State) {
        with(state) {
            trafficLightData?.let {
                setTrafficLight(it)
            }
        }
    }
}
