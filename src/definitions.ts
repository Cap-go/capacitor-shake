import type { PluginListenerHandle } from '@capacitor/core';

export interface CapacitorShakePlugin {
  /**
   * Listen for shake event
   *
   * @param eventName The shake change event name.
   * @param listenerFunc Callback invoked when phone is shaked
   */
  addListener(eventName: 'shake', listenerFunc: () => void): Promise<PluginListenerHandle>;

  /**
   * Get the native Capacitor plugin version
   *
   * @returns {Promise<{ id: string }>} an Promise with version for this device
   * @throws An error if the something went wrong
   */
  getPluginVersion(): Promise<{ version: string }>;
}
