package com.mad.notes.ui

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    /**
     * Instance of the base activity to which the fragment is attached
     */
    protected var mActivity: BaseActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onAttachToContext(context)
    }

    /*
     * Deprecated on API 23
     * Use onAttachToContext instead
     */
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity)
        }
    }

    /*
     * Called when the fragment attaches to the context
     */
    protected open fun onAttachToContext(context: Context?) {
        mActivity = context as BaseActivity?
    }

    override fun onDetach() {
        mActivity = null
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    /**
     * Handles the back press event for the particular fragment
     *
     * @return whether the back press is consumed or not. Returns true if the action for back press is completed. Returns
     * false when the fragment is not handling the event.
     */
    open fun onBackPressed(): Boolean {
        val fragmentManager = childFragmentManager
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStackImmediate()
            return true
        }
        return false
    }

    fun setUpActionBar(title: String?, isBack: Boolean) {
        val actionBar = activity!!.actionBar
        actionBar?.title = title
        actionBar?.setDisplayHomeAsUpEnabled(isBack)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}