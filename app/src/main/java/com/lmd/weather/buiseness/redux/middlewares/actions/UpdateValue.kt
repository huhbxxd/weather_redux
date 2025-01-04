package com.lmd.weather.buiseness.redux.middlewares.actions

import com.lmd.redux.interfaces.IAction

@JvmInline
value class UpdateValue(val newValue: Int) : IAction