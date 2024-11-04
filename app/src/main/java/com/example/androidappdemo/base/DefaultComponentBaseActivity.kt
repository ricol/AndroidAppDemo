package com.example.androidappdemo.base

import android.app.Activity
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import androidx.activity.ComponentActivity
import com.example.androidappdemo.utils.AppConstants
import com.example.androidappdemo.utils.Utils

class DefaultComponentBaseActivity: ComponentActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Utils.output(AppConstants.TagLifeCycle, "$this.onCreateOptionsMenu...")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Utils.output(AppConstants.TagLifeCycle, "$this.onCreate...")
        super.onCreate(savedInstanceState)
    }

    override fun onAttachedToWindow() {
        Utils.output(AppConstants.TagLifeCycle, "$this.onAttachedToWindow...")
        super.onAttachedToWindow()
    }

    override fun onContentChanged() {
        Utils.output(AppConstants.TagLifeCycle, "$this.onContentChanged...")
        super.onContentChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        Utils.output(AppConstants.TagLifeCycle, "$this.onCreate...")
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onContextMenuClosed(menu: Menu) {
        Utils.output(AppConstants.TagLifeCycle, "$this.onContextMenuClosed...")
        super.onContextMenuClosed(menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        Utils.output(AppConstants.TagLifeCycle, "$this.onContextItemSelected...")
        return super.onContextItemSelected(item)
    }

    override fun onActivityReenter(resultCode: Int, data: Intent?) {
        Utils.output(AppConstants.TagLifeCycle, "$this.onActivityReenter...")
        super.onActivityReenter(resultCode, data)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        Utils.output(AppConstants.TagLifeCycle, "$this.onCreateContextMenu...")
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onCreateDescription(): CharSequence? {
        Utils.output(AppConstants.TagLifeCycle, "$this.onCreateDescription...")
        return super.onCreateDescription()
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        Utils.output(AppConstants.TagLifeCycle, "$this.onCreateView...")
        return super.onCreateView(name, context, attrs)
    }

    override fun onCreateView(parent: View?, name: String, context: Context, attrs: AttributeSet): View? {
        Utils.output(AppConstants.TagLifeCycle, "$this.onCreateView...")
        return super.onCreateView(parent, name, context, attrs)
    }

    override fun onDetachedFromWindow() {
        Utils.output(AppConstants.TagLifeCycle, "$this.onDetachedFromWindow...")
        super.onDetachedFromWindow()
    }

    override fun onLowMemory() {
        Utils.output(AppConstants.TagLifeCycle, "$this.onLowMemory...")
        super.onLowMemory()
    }

    override fun onNavigateUp(): Boolean {
        Utils.output(AppConstants.TagLifeCycle, "$this.onNavigateUp...")
        return super.onNavigateUp()
    }

    override fun onMenuOpened(featureId: Int, menu: Menu): Boolean {
        Utils.output(AppConstants.TagLifeCycle, "$this.onMenuOpened...")
        return super.onMenuOpened(featureId, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Utils.output(AppConstants.TagLifeCycle, "$this.onOptionsItemSelected...")
        return super.onOptionsItemSelected(item)
    }

    override fun onUserInteraction() {
        Utils.output(AppConstants.TagLifeCycle, "$this.onUserInteraction...")
        super.onUserInteraction()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        Utils.output(AppConstants.TagLifeCycle, "$this.onSaveInstanceState...")
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onOptionsMenuClosed(menu: Menu?) {
        Utils.output(AppConstants.TagLifeCycle, "$this.onOptionsMenuClosed...")
        super.onOptionsMenuClosed(menu)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        Utils.output(AppConstants.TagLifeCycle, "$this.onRestoreInstanceState...")
        super.onRestoreInstanceState(savedInstanceState, persistentState)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Utils.output(AppConstants.TagLifeCycle, "$this.onTouchEvent...")
        return super.onTouchEvent(event)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Utils.output(AppConstants.TagLifeCycle, "$this.onSaveInstanceState...")
        super.onSaveInstanceState(outState)
    }

    override fun onNewIntent(intent: Intent) {
        Utils.output(AppConstants.TagLifeCycle, "$this.onNewIntent...")
        super.onNewIntent(intent)
    }

    override fun onDestroy() {
        Utils.output(AppConstants.TagLifeCycle, "$this.onDestroy...")
        super.onDestroy()
    }

    override fun onPause() {
        Utils.output(AppConstants.TagLifeCycle, "$this.onPause...")
        super.onPause()
    }

    override fun onRestart() {
        Utils.output(AppConstants.TagLifeCycle, "$this.onRestart...")
        super.onRestart()
    }

    override fun onResume() {
        Utils.output(AppConstants.TagLifeCycle, "$this.onResume...")
        super.onResume()
    }

    override fun onPostResume() {
        Utils.output(AppConstants.TagLifeCycle, "$this.onPostResume...")
        super.onPostResume()
    }

    override fun onStart() {
        Utils.output(AppConstants.TagLifeCycle, "$this.onStart...")
        super.onStart()
    }

    override fun onStop() {
        Utils.output(AppConstants.TagLifeCycle, "$this.onStop...")
        super.onStop()
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        Utils.output(AppConstants.TagLifeCycle, "$this.onPostCreate...")
        super.onPostCreate(savedInstanceState, persistentState)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        Utils.output(AppConstants.TagLifeCycle, "$this.onWindowFocusChanged...")
        super.onWindowFocusChanged(hasFocus)
    }

    override fun onMenuItemSelected(featureId: Int, item: MenuItem): Boolean {
        Utils.output(AppConstants.TagLifeCycle, "$this.onMenuItemSelected...")
        return super.onMenuItemSelected(featureId, item)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        Utils.output(AppConstants.TagLifeCycle, "$this.onPostCreate...")
        super.onPostCreate(savedInstanceState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Utils.output(AppConstants.TagLifeCycle, "$this.onRestoreInstanceState...")
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onCreateNavigateUpTaskStack(builder: TaskStackBuilder?) {
        Utils.output(AppConstants.TagLifeCycle, "$this.onCreateNavigateUpTaskStack...")
        super.onCreateNavigateUpTaskStack(builder)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        Utils.output(AppConstants.TagLifeCycle, "$this.onPrepareOptionsMenu...")
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Utils.output(AppConstants.TagLifeCycle, "$this.onConfigurationChanged...")
    }

    override fun onTitleChanged(title: CharSequence?, color: Int) {
        super.onTitleChanged(title, color)
        Utils.output(AppConstants.TagLifeCycle, "$this.onTitleChanged...")
    }
}