package com.aoliva.metmuseum.common.dispatcher

import kotlinx.coroutines.Dispatchers

class DefaultDispatcherProvider : DispatcherProvider {

    override val main = Dispatchers.Main

    override val default = Dispatchers.Default

    override val io = Dispatchers.IO
}
