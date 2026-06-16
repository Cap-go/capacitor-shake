# @capgo/capacitor-shake

<a href="https://capgo.app/"><img src="https://capgo.app/readme-banner.svg?repo=Cap-go/capacitor-shake" alt="Capgo - Instant updates for Capacitor" /></a>

<div align="center">
  <h2><a href="https://capgo.app/?ref=plugin_shake"> ➡️ Get Instant updates for your App with Capgo</a></h2>
  <h2><a href="https://capgo.app/consulting/?ref=plugin_shake"> Missing a feature? We’ll build the plugin for you 💪</a></h2>
</div>



Detect shake gesture in device

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
