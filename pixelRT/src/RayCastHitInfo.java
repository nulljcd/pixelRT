public class RayCastHitInfo {
  private Block block;
  private Vector2 position;
  private double length;
  private Vector2 normal;

  public RayCastHitInfo(Block block, Vector2 position, double length, Vector2 normal) {
    this.block = block;
    this.position = position;
    this.length = length;
    this.normal = normal;
  }

  public Block getBlock() {
    return block;
  }

  public Vector2 getPosition() {
    return position;
  }

  public double getLength() {
    return length;
  }

  public Vector2 getNormal() {
    return normal;
  }
}
