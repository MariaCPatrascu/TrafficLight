package app.trafficlight.di

import androidx.lifecycle.ViewModel
import app.trafficlight.driving.DrivingViewModel
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
}
