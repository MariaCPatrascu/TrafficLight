package app.trafficlight.util

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object FragmentUtil {
    fun addFragment(
        fragmentManager: FragmentManager,
        newFragment: Fragment,
        @IdRes parentContainer: Int
    ) {
        val transaction = fragmentManager.beginTransaction()
        transaction.add(parentContainer, newFragment)
        transaction.commit()
    }
}
