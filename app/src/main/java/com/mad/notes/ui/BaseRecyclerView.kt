package com.mad.notes.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView

//androidx exactly?
open class BaseRecyclerView : RecyclerView {

    private var mEmptyView: View? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    fun setEmptyView(emptyView: View?) {
        mEmptyView = emptyView
        updateRecyclerviewStatus()
    }

    private fun updateRecyclerviewStatus() {
        val adapter = adapter
        val empty = adapter == null || adapter.itemCount == 0
        if (empty) {

            //if line 46 needed

            mEmptyView?.visibility = View.VISIBLE
            visibility = View.GONE
            //ask: why line 48 written?
        } else {
            mEmptyView?.visibility = View.GONE
            visibility = View.VISIBLE
        }

        //ask
        requestLayout()

    }

    override fun setAdapter(adapter: Adapter<*>?) {
        //note:- written above super
        adapter?.unregisterAdapterDataObserver(mDataObserver)
        super.setAdapter(adapter)
    }

    private val mDataObserver: AdapterDataObserver = object : AdapterDataObserver() {

        override fun onChanged() {
            super.onChanged()
            updateRecyclerviewStatus()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)
            updateRecyclerviewStatus()
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            super.onItemRangeChanged(positionStart, itemCount)
            updateRecyclerviewStatus()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount)
            updateRecyclerviewStatus()
        }

        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount)
            updateRecyclerviewStatus()
        }
    }


}