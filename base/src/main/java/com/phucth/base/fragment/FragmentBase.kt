package com.phucth.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.phucth.service.R

open class FragmentBase<binding : ViewBinding>(private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> binding) :
    Fragment() {

    protected var viewBinding: binding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (viewBinding == null) {
            viewBinding = bindingInflater.invoke(inflater, container, false)
        }
        return viewBinding?.root
    }

    protected fun moveToNewScreen(
        idNextScreen: Int,
        isUseAnimation: Boolean = true,
        data: Bundle? = null,
        idPopScreen: Int = -1,
        isPopCurrentScreen: Boolean = false
    ) {
        val navOptions = NavOptions
            .Builder()
            .setPopUpTo(idPopScreen, isPopCurrentScreen)
        if (isUseAnimation) {
            navOptions.setEnterAnim(R.anim.slide_in_right)
                .setExitAnim(R.anim.slide_out_left)
                .setPopEnterAnim(R.anim.slide_in_left)
                .setPopExitAnim(R.anim.slide_out_right)
        }
        findNavController().navigate(idNextScreen, data, navOptions.build())
    }

    protected fun backToPreviousScreen() {
        findNavController().popBackStack()
    }
}