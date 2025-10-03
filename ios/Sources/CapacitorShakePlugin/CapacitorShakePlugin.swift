import Foundation
import Capacitor

extension UIDevice {
    static let deviceDidShakeNotification = Notification.Name(rawValue: "deviceDidShakeNotification")
}

//  Override the default behavior of shake gestures to send our notification instead.
extension UIWindow {
    override open func motionEnded(_ motion: UIEvent.EventSubtype, with event: UIEvent?) {
        if motion == .motionShake {
            NotificationCenter.default.post(name: UIDevice.deviceDidShakeNotification, object: nil)
        }
    }
}

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(CapacitorShakePlugin)
public class CapacitorShakePlugin: CAPPlugin, CAPBridgedPlugin {
    public let identifier = "CapacitorShakePlugin"
    public let jsName = "CapacitorShake"
    public let pluginMethods: [CAPPluginMethod] = [
    ]

    override public func load() {
        NotificationCenter.default.addObserver(
            self,
            selector: #selector(self.shaked),
            name: UIDevice.deviceDidShakeNotification,
            object: nil)
    }

    deinit {
        NotificationCenter.default.removeObserver(self)
    }

    @objc private func shaked() {
        print("Shake detected")
        notifyListeners("shake", data: [:])
    }
}
