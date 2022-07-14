package com.example.feature_daily_weather_details.di.component

internal interface ComponentProvider {

    val component: TestFeatureDailyWeatherDetailsComponent

    companion object : ComponentProvider {
        private var _component: TestFeatureDailyWeatherDetailsComponent? = null

        override val component: TestFeatureDailyWeatherDetailsComponent
            get() {
                if (_component == null)
                    _component = DaggerTestFeatureDailyWeatherDetailsComponent.create()
                return _component!!
            }

    }
}