package ee.forgr.capacitor.shake;

import android.content.Context;
import android.hardware.SensorManager;
import android.util.Log;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.squareup.seismic.ShakeDetector;

@CapacitorPlugin(name = "CapacitorShake")
public class CapacitorShakePlugin extends Plugin implements ShakeDetector.Listener {

    private final String pluginVersion = "7.2.14";

    @Override
    public void load() {
        super.load();

        SensorManager sensorManager = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            sensorManager = (SensorManager) this.bridge.getActivity().getSystemService(Context.SENSOR_SERVICE);

            if (sensorManager == null) {
                Log.e("CapacitorShakePlugin", "This device couldn't find SENSOR_SERVICE. Perhaps your device doesn't support it");
                return;
            }
        } else {
            Log.e("CapacitorShakePlugin", "This device doesn't support the getSystemService. Minimal android version: 23");
            return;
        }

        try {
            ShakeDetector sd = new ShakeDetector(this);
            sd.start(sensorManager, SensorManager.SENSOR_DELAY_GAME);
            Log.i("CapacitorShakePlugin", "ShakeDetector started");
        } catch (Exception e) {
            Log.e("CapacitorShakePlugin", "Failed to start the shakeDetector", e);
        }
    }

    @Override
    public void hearShake() {
        JSObject ret = new JSObject();
        notifyListeners("shake", ret);
    }

    @PluginMethod
    public void getPluginVersion(final PluginCall call) {
        try {
            final JSObject ret = new JSObject();
            ret.put("version", this.pluginVersion);
            call.resolve(ret);
        } catch (final Exception e) {
            call.reject("Could not get plugin version", e);
        }
    }
}
