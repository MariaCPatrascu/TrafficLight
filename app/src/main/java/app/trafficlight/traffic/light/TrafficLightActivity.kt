package app.trafficlight.traffic.light

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.trafficlight.R
import app.trafficlight.databinding.ActivityTrafficLightBinding
import app.trafficlight.util.FragmentUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TrafficLightActivity : AppCompatActivity() {

    @Inject
    lateinit var trafficLightFragment: TrafficLightFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isFinishing) return
        val viewBinding =
            ActivityTrafficLightBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        trafficLightFragment.arguments = intent.extras
        FragmentUtil.addFragment(supportFragmentManager, trafficLightFragment, R.id.main_content_container)
    }

    companion object {
        fun startActivity(
            context: Context,
            carModel: String
        ) {
            val intent = Intent(context, TrafficLightActivity::class.java)
            intent.putExtra("CAR_MODEL", carModel)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }
}
