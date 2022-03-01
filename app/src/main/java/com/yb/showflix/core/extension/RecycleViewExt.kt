package com.yb.showflix.core.extension

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addDecoration(){
    addItemDecoration(
        DividerItemDecoration(
            this.context,
            (this.layoutManager as LinearLayoutManager).orientation
        )
    )
}