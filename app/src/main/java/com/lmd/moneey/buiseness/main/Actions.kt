package com.lmd.moneey.buiseness.main

import com.lmd.redux.IAction


sealed class MainScreenActions : IAction {
    data object Init : MainScreenActions()
    data object Increment : MainScreenActions()
    data object Remove : MainScreenActions()
}