import { WebPlugin } from '@capacitor/core';

import type { CapacitorShakePlugin } from './definitions';

export class CapacitorShakeWeb
  extends WebPlugin
  implements CapacitorShakePlugin
{
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
