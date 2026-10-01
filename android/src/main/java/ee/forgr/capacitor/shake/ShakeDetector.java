// Shake detection algorithm ported from com.squareup:seismic 1.0.3 (Square, Apache 2.0).
package ee.forgr.capacitor.shake;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Detects phone shaking. If more than 75% of the samples taken in the past 0.5s are
 * accelerating, the device is a) shaking, or b) free falling 1.84m (h =
 * 1/2*g*t^2*3/4).
 */
public class ShakeDetector implements SensorEventListener {

    public static final int SENSITIVITY_LIGHT = 11;
    public static final int SENSITIVITY_MEDIUM = 13;
    public static final int SENSITIVITY_HARD = 15;

    private static final int DEFAULT_ACCELERATION_THRESHOLD = SENSITIVITY_MEDIUM;

    private int accelerationThreshold = DEFAULT_ACCELERATION_THRESHOLD;

    public interface Listener {
        void hearShake();
    }

    private final SampleQueue queue = new SampleQueue();
    private final Listener listener;

    private SensorManager sensorManager;
    private Sensor accelerometer;

    public ShakeDetector(Listener listener) {
        this.listener = listener;
    }

    public boolean start(SensorManager sensorManager) {
        return start(sensorManager, SensorManager.SENSOR_DELAY_FASTEST);
    }

    public boolean start(SensorManager sensorManager, int sensorDelay) {
        if (accelerometer != null) {
            return true;
        }

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (accelerometer != null) {
            this.sensorManager = sensorManager;
            sensorManager.registerListener(this, accelerometer, sensorDelay);
        }
        return accelerometer != null;
    }

    public void stop() {
        if (accelerometer != null) {
            queue.clear();
            sensorManager.unregisterListener(this, accelerometer);
            sensorManager = null;
            accelerometer = null;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        boolean accelerating = isAccelerating(event);
        long timestamp = event.timestamp;
        queue.add(timestamp, accelerating);
        if (queue.isShaking()) {
            queue.clear();
            listener.hearShake();
        }
    }

    private boolean isAccelerating(SensorEvent event) {
        float ax = event.values[0];
        float ay = event.values[1];
        float az = event.values[2];

        final double magnitudeSquared = ax * ax + ay * ay + az * az;
        return magnitudeSquared > accelerationThreshold * accelerationThreshold;
    }

    public void setSensitivity(int accelerationThreshold) {
        this.accelerationThreshold = accelerationThreshold;
    }

    static class SampleQueue {

        private static final long MAX_WINDOW_SIZE = 500000000; // 0.5s
        private static final long MIN_WINDOW_SIZE = MAX_WINDOW_SIZE >> 1; // 0.25s
        private static final int MIN_QUEUE_SIZE = 4;

        private final SamplePool pool = new SamplePool();

        private Sample oldest;
        private Sample newest;
        private int sampleCount;
        private int acceleratingCount;

        void add(long timestamp, boolean accelerating) {
            purge(timestamp - MAX_WINDOW_SIZE);

            Sample added = pool.acquire();
            added.timestamp = timestamp;
            added.accelerating = accelerating;
            added.next = null;
            if (newest != null) {
                newest.next = added;
            }
            newest = added;
            if (oldest == null) {
                oldest = added;
            }

            sampleCount++;
            if (accelerating) {
                acceleratingCount++;
            }
        }

        void clear() {
            while (oldest != null) {
                Sample removed = oldest;
                oldest = removed.next;
                pool.release(removed);
            }
            newest = null;
            sampleCount = 0;
            acceleratingCount = 0;
        }

        void purge(long cutoff) {
            while (sampleCount >= MIN_QUEUE_SIZE && oldest != null && cutoff - oldest.timestamp > 0) {
                Sample removed = oldest;
                if (removed.accelerating) {
                    acceleratingCount--;
                }
                sampleCount--;

                oldest = removed.next;
                if (oldest == null) {
                    newest = null;
                }
                pool.release(removed);
            }
        }

        List<Sample> asList() {
            List<Sample> list = new ArrayList<>();
            Sample s = oldest;
            while (s != null) {
                list.add(s);
                s = s.next;
            }
            return list;
        }

        boolean isShaking() {
            return (
                newest != null &&
                oldest != null &&
                newest.timestamp - oldest.timestamp >= MIN_WINDOW_SIZE &&
                acceleratingCount >= (sampleCount >> 1) + (sampleCount >> 2)
            );
        }
    }

    static class Sample {

        long timestamp;
        boolean accelerating;
        Sample next;
    }

    static class SamplePool {

        private Sample head;

        Sample acquire() {
            Sample acquired = head;
            if (acquired == null) {
                acquired = new Sample();
            } else {
                head = acquired.next;
            }
            return acquired;
        }

        void release(Sample sample) {
            sample.next = head;
            head = sample;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
