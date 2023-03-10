export interface CapacitorShakePlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
