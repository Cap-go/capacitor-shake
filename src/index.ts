import { registerPlugin } from "@capacitor/core";

import type { CapacitorShakePlugin } from "./definitions";

const CapacitorShake = registerPlugin<CapacitorShakePlugin>("CapacitorShake", {
  web: () => import("./web").then((m) => new m.CapacitorShakeWeb()),
});

export * from "./definitions";
export { CapacitorShake };
