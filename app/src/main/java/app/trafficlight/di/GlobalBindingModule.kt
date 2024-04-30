package app.trafficlight.di

import app.trafficlight.driving.DrivingActivity
import app.trafficlight.driving.DrivingViewModel
import app.trafficlight.traffic.light.TrafficLightActivity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GlobalBindingModule {
    @Binds
    abstract fun drivingActivity(drivingActivity: DrivingActivity): DrivingActivity

    @Binds
    abstract fun trafficLightActivity(trafficLightActivity: TrafficLightActivity): TrafficLightActivity
}
