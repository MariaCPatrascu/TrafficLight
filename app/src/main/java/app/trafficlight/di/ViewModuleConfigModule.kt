package app.trafficlight.di

import androidx.lifecycle.ViewModel
import app.trafficlight.driving.DrivingViewModel
import app.trafficlight.traffic.light.TrafficLightViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
abstract class ViewModuleConfigModule {
    @Binds
    @IntoMap
    @ViewModelKey(DrivingViewModel::class)
    abstract fun bindDrivingViewModel(drivingViewModel: DrivingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TrafficLightViewModel::class)
    abstract fun bindTrafficLightViewModel(trafficLightViewModel: TrafficLightViewModel): ViewModel
}
