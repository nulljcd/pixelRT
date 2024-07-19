public class Block {
  private int type;
  private Vector3 color;
  private double strength;

  public Block(int type, Vector3 color, double strength) {
    this.type = type;
    this.color = color;
    this.strength = strength;
  }

  public Block(int type, Vector3 color) {
    this.type = type;
    this.color = color;
  }

  public int getType() {
    return type;
  }

  public Vector3 getColor() {
    return color;
  }

  public double getStrength() {
    return strength;
  }
}
