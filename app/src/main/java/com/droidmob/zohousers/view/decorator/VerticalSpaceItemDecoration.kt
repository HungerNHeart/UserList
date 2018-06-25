package com.droidmob.moviesurf_yify.adapter.decorator

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Guru on 23-03-2018.
 */
class VerticalSpaceItemDecoration(val verticalSpaceHeight: Int) : RecyclerView.ItemDecoration() {

    var position: Position

    init {
        position = Position.BOTTOM
    }


    constructor(verticalSpaceHeight: Int, position: Position) : this(verticalSpaceHeight) {
        this.position = position
    }

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        when (position) {
            Position.TOP -> outRect?.top = verticalSpaceHeight
            Position.LEFT -> outRect?.left = verticalSpaceHeight
            Position.RIGHT -> outRect?.right = verticalSpaceHeight
            else -> outRect?.bottom = verticalSpaceHeight
        }
    }

    enum class Position {
        TOP,
        LEFT,
        RIGHT,
        BOTTOM
    }
}