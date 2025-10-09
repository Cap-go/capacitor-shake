import XCTest
@testable import CapacitorShakePlugin

final class CapacitorShakeTests: XCTestCase {
    func testEcho() {
        // Ensure the helper mirrors the passed value.
        let implementation = CapacitorShake()
        let value = "Hello, World!"
        let result = implementation.echo(value)

        XCTAssertEqual(value, result)
    }
}
