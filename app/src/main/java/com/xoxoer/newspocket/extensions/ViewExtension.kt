package com.xoxoer.newspocket.extensions

import android.view.View
import android.widget.LinearLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.xoxoer.newspocket.ui.viewmodels.NewsViewModel

fun View.gone(isGone: Boolean){
    this.visibility = if(isGone){
        View.GONE
    }else{
        View.VISIBLE
    }
}

fun <T : View> BottomSheetBehavior<T>.isExpanded(): Boolean {
    return this.state == BottomSheetBehavior.STATE_EXPANDED
}

fun <T : View> BottomSheetBehavior<T>.expand() {
    this.state = BottomSheetBehavior.STATE_EXPANDED
}

fun <T : View> BottomSheetBehavior<T>.hide() {
    this.state = BottomSheetBehavior.STATE_HIDDEN
}

fun <T : View> BottomSheetBehavior<T>.interact(vm: NewsViewModel, tag: String) {
    when(tag){
        "HEADLINE" -> vm.bottomSheetModeForHeadline()
        "SOURCE" -> vm.bottomSheetModeForSource()
    }
    if(this.state == BottomSheetBehavior.STATE_EXPANDED){
        this.state = BottomSheetBehavior.STATE_HIDDEN
    }else{
        this.state = BottomSheetBehavior.STATE_EXPANDED
    }
}

fun <T : LinearLayout> View.transformToBottomSheetDialog(
    isHideable: Boolean,
    onSliding: () -> Unit,
    onExpand: () -> Unit,
    onCollapse: () -> Unit,
    onDrag: () -> Unit,
    onSettle: () -> Unit,
    onHalfExpand: () -> Unit,
    onHide: () -> Unit
): BottomSheetBehavior<T> {

    @Suppress("UNCHECKED_CAST")
    val bottomSheetBehavior: BottomSheetBehavior<T> = BottomSheetBehavior.from(this)
            as BottomSheetBehavior<T>

    bottomSheetBehavior.isHideable = isHideable
    bottomSheetBehavior.peekHeight = 0

    bottomSheetBehavior.addBottomSheetCallback(object :
        BottomSheetBehavior.BottomSheetCallback() {
        override fun onSlide(bottomSheet: View, slideOffset: Float) {
            onSliding()
        }

        override fun onStateChanged(bottomSheet: View, newState: Int) {
            when (newState) {
                BottomSheetBehavior.STATE_EXPANDED -> {
                    onExpand()
                }
                BottomSheetBehavior.STATE_COLLAPSED -> {
                    onCollapse()
                }
                BottomSheetBehavior.STATE_DRAGGING -> {
                    onDrag()
                }
                BottomSheetBehavior.STATE_SETTLING -> {
                    onSettle()
                }
                BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                    onHalfExpand()
                }
                BottomSheetBehavior.STATE_HIDDEN -> {
                    onHide()
                }
            }
        }
    })

    return bottomSheetBehavior
}