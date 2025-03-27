package dev.fluttercommunity.workmanager

import android.content.Context
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel

class WorkmanagerPlugin : FlutterPlugin {

    private var methodChannel: MethodChannel? = null
    private var workmanagerCallHandler: WorkmanagerCallHandler? = null

    override fun onAttachedToEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        val context = binding.applicationContext
        val messenger = binding.binaryMessenger

        workmanagerCallHandler = WorkmanagerCallHandler(context)
        methodChannel = MethodChannel(messenger, "be.tramckrijte.workmanager/foreground_channel_work_manager")
        methodChannel?.setMethodCallHandler(workmanagerCallHandler)
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        methodChannel?.setMethodCallHandler(null)
        methodChannel = null
        workmanagerCallHandler = null
    }

    companion object {
        // Optional: if still used for background isolate registration
        var pluginRegistryCallback: io.flutter.plugin.common.PluginRegistry.PluginRegistrantCallback? = null

        @Deprecated("This method is no longer required with Flutter v2 embedding.")
        @JvmStatic
        fun setPluginRegistrantCallback(callback: io.flutter.plugin.common.PluginRegistry.PluginRegistrantCallback) {
            pluginRegistryCallback = callback
        }
    }
}
