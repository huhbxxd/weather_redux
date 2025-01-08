package com.lmd.redux.actions

import com.lmd.redux.interfaces.IAction
import com.lmd.redux.middlewares.MiddlewareWrapper

data class AddMiddleWare(val wrapper: MiddlewareWrapper) : IAction
data class RemoveMiddleWare(val type: String) : IAction