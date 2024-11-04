package com.example.androidappdemo.base

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.AttributeSet
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidappdemo.utils.AppConstants
import com.example.androidappdemo.utils.Utils

open class DefaultBaseFragment: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.output(AppConstants.TagLifeCycle, "$this.onCreate...")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Utils.output(AppConstants.TagLifeCycle, "$this.onCreateView...")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        Utils.output(AppConstants.TagLifeCycle, "$this.onAttach...")
        super.onAttach(context)
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        Utils.output(AppConstants.TagLifeCycle, "$this.onCreateContextMenu...")
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onDestroy() {
        Utils.output(AppConstants.TagLifeCycle, "$this.onDestroy...")
        super.onDestroy()
    }

    override fun onDestroyView() {
        Utils.output(AppConstants.TagLifeCycle, "$this.onDestroyView...")
        super.onDestroyView()
    }

    override fun onDetach() {
        Utils.output(AppConstants.TagLifeCycle, "$this.onDetach...")
        super.onDetach()
    }

    override fun onLowMemory() {
        Utils.output(AppConstants.TagLifeCycle, "$this.onLowMemory...")
        super.onLowMemory()
    }

    override fun onPause() {
        Utils.output(AppConstants.TagLifeCycle, "$this.onPause...")
        super.onPause()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        Utils.output(AppConstants.TagLifeCycle, "$this.onConfigurationChanged...")
        super.onConfigurationChanged(newConfig)
    }

    override fun onResume() {
        Utils.output(AppConstants.TagLifeCycle, "$this.onResume...")
        super.onResume()
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        Utils.output(AppConstants.TagLifeCycle, "$this.onContextItemSelected...")
        return super.onContextItemSelected(item)
    }

    override fun onStart() {
        Utils.output(AppConstants.TagLifeCycle, "$this.onStart...")
        super.onStart()
    }

    override fun onStop() {
        Utils.output(AppConstants.TagLifeCycle, "$this.onStop...")
        super.onStop()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        Utils.output(AppConstants.TagLifeCycle, "$this.onHiddenChanged...")
        super.onHiddenChanged(hidden)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Utils.output(AppConstants.TagLifeCycle, "$this.onSaveInstanceState...")
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        Utils.output(AppConstants.TagLifeCycle, "$this.onViewStateRestored...")
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onInflate(context: Context, attrs: AttributeSet, savedInstanceState: Bundle?) {
        Utils.output(AppConstants.TagLifeCycle, "$this.onInflate...")
        super.onInflate(context, attrs, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Utils.output(AppConstants.TagLifeCycle, "$this.onViewCreated...")
        super.onViewCreated(view, savedInstanceState)
    }
}