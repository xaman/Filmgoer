/*
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.martinchamarro.filmgoer.presentation.movies

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.TextView
import io.mattcarroll.hover.Content

/**
 * A screen that is displayed in our Hello World Hover Menu.
 */
class MoviesRankingsScreen(context: Context, private val pageTitle: String) : Content {

    private val context: Context = context.applicationContext
    private val view = createScreenView()

    private fun createScreenView(): View {
        val contentView = TextView(context)
        contentView.text = "Screen: $pageTitle"
        contentView.gravity = Gravity.CENTER
        return contentView
    }

    // Make sure that this method returns the SAME View.  It should NOT create a new View each time
    // that it is invoked.
    override fun getView() = view

    override fun isFullscreen() = false

    override fun onShown() {}

    override fun onHidden() {}

}
