package com.lmd.redux.actions

import com.lmd.redux.interfaces.IAction
import com.lmd.redux.interfaces.IListenerAction

data class AddListener(override val block: suspend (IAction) -> Unit) : IListenerAction
data class RemoveListener(override val block: suspend (IAction) -> Unit) : IListenerAction