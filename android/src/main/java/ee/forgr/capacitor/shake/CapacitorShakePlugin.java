package ee.forgr.capacitor.shake;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.squareup.seismic.ShakeDetector;

@CapacitorPlugin(name = "CapacitorShake")
public class CapacitorShakePlugin extends Plugin implements ShakeDetector.Listener {

    @Override public void hearShake() {
        JSObject ret = new JSObject();
        notifyListeners("shake", ret);
    }
}
