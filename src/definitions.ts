import type { PluginListenerHandle } from '@capacitor/core';

/**
 * Capacitor Shake Plugin interface for detecting shake gestures on mobile devices.
 * This plugin allows you to listen for shake events and get plugin version information.
 *
 * @since 1.0.0
 */
export interface CapacitorShakePlugin {
  /**
   * Listen for shake event on the device.
   *
   * Registers a listener that will be called whenever a shake gesture is detected.
   * The shake detection uses the device's accelerometer to identify shake patterns.
   *
   * @since 1.0.0
   * @param eventName The shake change event name. Must be 'shake'.
   * @param listenerFunc Callback function invoked when the phone is shaken.
   * @returns {Promise<PluginListenerHandle>} A promise that resolves to a listener handle that can be used to remove the listener.
   *
   * @example
   * ```typescript
   * const listener = await CapacitorShake.addListener('shake', () => {
   *   console.log('Shake detected!');
   * });
   *
   * // To remove the listener:
   * await listener.remove();
   * ```
   */
  addListener(eventName: 'shake', listenerFunc: () => void): Promise<PluginListenerHandle>;

  /**
   * Get the native Capacitor plugin version.
   *
   * Returns the current version of the native plugin implementation.
   *
   * @since 1.0.0
   * @returns {Promise<{ version: string }>} A promise that resolves with an object containing the version string.
   * @throws An error if something went wrong retrieving the version.
   *
   * @example
   * ```typescript
   * const { version } = await CapacitorShake.getPluginVersion();
   * console.log('Plugin version:', version);
   * ```
   */
  getPluginVersion(): Promise<{ version: string }>;
}
