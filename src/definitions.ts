import type { PluginListenerHandle } from "@capacitor/core";

export interface CapacitorShakePlugin {
  addListener(
    eventName: "shake",
    listenerFunc: () => void
  ): Promise<PluginListenerHandle> & PluginListenerHandle;
}
