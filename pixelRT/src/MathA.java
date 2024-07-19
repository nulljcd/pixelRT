public class MathA {
  public static Vector2 abs(Vector2 input) {
    return new Vector2(Math.abs(input.getX()), Math.abs(input.getY()));
  }

  public static Vector2 floor(Vector2 input) {
    return new Vector2(Math.floor(input.getX()), Math.floor(input.getY()));
  }
  
  public static Vector2 sign(Vector2 input) {
    return new Vector2(Math.signum(input.getX()), Math.signum(input.getY()));
  }

  public static Vector2 lerp(Vector2 a, Vector2 b, double f) {
    return a.add((b.subtract(a).multiply(f)));
  }
}
