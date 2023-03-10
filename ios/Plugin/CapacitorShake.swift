import Foundation

@objc public class CapacitorShake: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
