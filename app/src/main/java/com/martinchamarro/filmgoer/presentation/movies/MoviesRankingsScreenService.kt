package com.martinchamarro.filmgoer.presentation.movies

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.martinchamarro.filmgoer.R
import com.martinchamarro.filmgoer.presentation.model.ServiceState.*
import io.mattcarroll.hover.Content
import io.mattcarroll.hover.HoverMenu
import io.mattcarroll.hover.HoverView
import io.mattcarroll.hover.window.HoverMenuService
import java.util.*


class MoviesRankingsScreenService : HoverMenuService() {

    companion object {
        var state = NOT_INITIALIZED
        const val TAG = "MoviesRankingsService"

        fun startIfNeeded(context: Context) {
            if (state == NOT_INITIALIZED) {
                state = RUNNING
                context.startService(Intent(context, MoviesRankingsScreenService::class.java))
            }
        }
    }

    override fun onHoverMenuLaunched(intent: Intent, hoverView: HoverView) {
        hoverView.setMenu(createHoverMenu())
        hoverView.collapse()
        Log.d(TAG, "Launched!")
    }

    override fun onHoverMenuExitingByUserRequest() {
        super.onHoverMenuExitingByUserRequest()
        state = KILLED
        Log.d(TAG, "Exited!")
    }

    private fun createHoverMenu(): HoverMenu = SingleSectionHoverMenu(applicationContext)

    private class SingleSectionHoverMenu(private val context: Context) : HoverMenu() {

        private val section = Section(SectionId("1"), createTabView(), createScreen())

        private fun createTabView(): View {
            val imageView = ImageView(context)
            imageView.setImageResource(R.drawable.tab_background)
            imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
            return imageView
        }

        private fun createScreen(): Content = MoviesRankingsScreen(context, "Screen 1")

        override fun getId(): String = "singlesectionmenu"

        override fun getSectionCount() = 1

        override fun getSection(index: Int) = section

        override fun getSection(sectionId: SectionId) = section

        override fun getSections(): List<Section> = Collections.singletonList(section)
    }

}