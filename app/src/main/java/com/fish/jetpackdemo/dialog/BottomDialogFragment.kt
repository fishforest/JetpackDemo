package com.fish.jetpackdemo.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fish.jetpackdemo.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomDialogFragment:BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.layout_first_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setOnKeyListener { dialog, keyCode, event ->
            println("dd")
            false
        }
        dialog?.setCanceledOnTouchOutside(false)
    }

    private var mBottomSheetBehavior: BottomSheetBehavior<View>? = null
    private val mBottomSheetBehaviorCallback: BottomSheetBehavior.BottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onStateChanged(bottomSheet: View, newState: Int) {
            //禁止拖拽，
            if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                //设置为收缩状态
                mBottomSheetBehavior!!.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {}
    }

    override fun onStart() {
        super.onStart()
//        if (dialog != null) {
//            val bottomSheet = dialog?.findViewById<View>(R.id.design_bottom_sheet)
//            bottomSheet?.layoutParams?.height = ViewGroup.LayoutParams.MATCH_PARENT
//        }

//        val view = view
//        view!!.post {
//            val parent = view.parent as View
//            val params = parent.layoutParams as CoordinatorLayout.LayoutParams
//            val behavior = params.behavior
//            mBottomSheetBehavior = behavior as BottomSheetBehavior<View>?
//            mBottomSheetBehavior!!.addBottomSheetCallback(mBottomSheetBehaviorCallback)
//            val display = requireActivity().windowManager.defaultDisplay
//            //设置高度
//            //val height = display.height / 2
//            val height = display.height / 3* 2
////            mBottomSheetBehavior!!.peekHeight = height
//            parent.setBackgroundColor(Color.TRANSPARENT)
//        }

        mBottomSheetBehavior =
            BottomSheetBehavior.from(dialog?.findViewById(R.id.design_bottom_sheet)!!)
        mBottomSheetBehavior!!.addBottomSheetCallback(mBottomSheetBehaviorCallback)
    }
}