# @capgo/capacitor-shake

Detect when users shake their phone in your Capacitor app, to open feedback, undo an action or reveal a debug menu.

<a href="https://capgo.app/?ref=plugin_shake"><img src="https://capgo.app/readme-banner.svg?repo=Cap-go/capacitor-shake" alt="Capgo - Instant updates for Capacitor" /></a>

<div align="center">
  <p><b>Capgo</b>: open-source live updates for Ionic and Capacitor apps. Ship OTA fixes and features instantly, without waiting for app store review.</p>
  <h2><a href="https://capgo.app/register/?ref=plugin_shake">➡️ Get started for free</a></h2>
  <p>14-day unlimited free trial. No credit card required</p>
  <p><a href="https://capgo.app/consulting/?ref=plugin_shake">Missing a feature? We'll build the plugin for you 💪</a></p>
</div>

<p align="center">
  <img src="https://raw.githubusercontent.com/Cap-go/capacitor-shake/main/assets/github-social-preview.png" alt="@capgo/capacitor-shake for Capacitor apps" width="300" />
</p>

## Key features

- **Shake event**: `addListener('shake', ...)` calls your code on each shake.
- **iOS**: uses the system shake motion event.
- **Android**: accelerometer-based detection with a tuned threshold.
- **Clean up**: remove the listener when you no longer need it.
- **Platforms**: iOS and Android. Not available on web.

## Documentation

The most complete doc is available here: https://capgo.app/docs/plugins/shake/

## Compatibility

| Plugin version | Capacitor compatibility | Maintained |
| -------------- | ----------------------- | ---------- |
| v8.\*.\*       | v8.\*.\*                | ✅          |
| v7.\*.\*       | v7.\*.\*                | On demand   |
| v6.\*.\*       | v6.\*.\*                | ❌          |
| v5.\*.\*       | v5.\*.\*                | ❌          |

> **Note:** The major version of this plugin follows the major version of Capacitor. Use the version that matches your Capacitor installation (e.g., plugin v8 for Capacitor 8). Only the latest major version is actively maintained.

## Install

You can use our AI-Assisted Setup to install the plugin. Add the Capgo skills to your AI tool using the following command:

```bash
npx skills add https://github.com/cap-go/capacitor-skills --skill capacitor-plugins
```

Then use the following prompt:

```text
Use the `capacitor-plugins` skill from `cap-go/capacitor-skills` to install the `@capgo/capacitor-shake` plugin in my project.
```

If you prefer Manual Setup, install the plugin by running the following commands and follow the platform-specific instructions below:

```bash
npm install @capgo/capacitor-shake
npx cap sync
```

## API

<docgen-index>

* [`addListener('shake', ...)`](#addlistenershake-)
* [`getPluginVersion()`](#getpluginversion)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

Capacitor Shake Plugin interface for detecting shake gestures on mobile devices.
This plugin allows you to listen for shake events and get plugin version information.

### addListener('shake', ...)

```typescript
addListener(eventName: 'shake', listenerFunc: () => void) => Promise<PluginListenerHandle>
```

Listen for shake event on the device.

Registers a listener that will be called whenever a shake gesture is detected.
The shake detection uses the device's accelerometer to identify shake patterns.

| Param              | Type                       | Description                                         |
| ------------------ | -------------------------- | --------------------------------------------------- |
| **`eventName`**    | <code>'shake'</code>       | The shake change event name. Must be 'shake'.       |
| **`listenerFunc`** | <code>() =&gt; void</code> | Callback function invoked when the phone is shaken. |

**Returns:** <code>Promise&lt;<a href="#pluginlistenerhandle">PluginListenerHandle</a>&gt;</code>

**Since:** 1.0.0

--------------------


### getPluginVersion()

```typescript
getPluginVersion() => Promise<{ version: string; }>
```

Get the native Capacitor plugin version.

Returns the current version of the native plugin implementation.

**Returns:** <code>Promise&lt;{ version: string; }&gt;</code>

**Since:** 1.0.0

--------------------


### Interfaces


#### PluginListenerHandle

| Prop         | Type                                      |
| ------------ | ----------------------------------------- |
| **`remove`** | <code>() =&gt; Promise&lt;void&gt;</code> |

</docgen-api>
