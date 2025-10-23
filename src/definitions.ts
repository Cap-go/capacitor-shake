import type { PluginListenerHandle } from '@capacitor/core';

export interface CapacitorShakePlugin {
  addListener(eventName: 'shake', listenerFunc: () => void): Promise<PluginListenerHandle>;

  /**
   * Get the native Capacitor plugin version
   *
   * @returns {Promise<{ id: string }>} an Promise with version for this device
   * @throws An error if the something went wrong
   */
  getPluginVersion(): Promise<{ version: string }>;
}
