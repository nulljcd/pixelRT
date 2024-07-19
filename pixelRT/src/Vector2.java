public class Vector2 {
  private double x;
  private double y;

  public Vector2(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public void setX(double x) {
    this.x = x;
  }

  public void setY(double y) {
    this.y = y;
  }

  public Vector2 add(Vector2 other) {
    return new Vector2(x + other.x, y + other.y);
  }

  public Vector2 add(double other) {
    return new Vector2(x + other, y + other);
  }

  public Vector2 subtract(Vector2 other) {
    return new Vector2(x - other.x, y - other.y);
  }

  public Vector2 subtract(double other) {
    return new Vector2(x - other, y - other);
  }

  public Vector2 multiply(Vector2 other) {
    return new Vector2(x * other.x, y * other.y);
  }

  public Vector2 multiply(double other) {
    return new Vector2(x * other, y * other);
  }

  public Vector2 divide(Vector2 other) {
    return this.multiply(other.reciprocal());
  }

  public Vector2 divide(double other) {
    return this.multiply(1/other);
  }

  public double length2() {
    return this.dot(this);
  }

  public double dot(Vector2 other) {
    return other.x * this.x + other.y * this.y;
  }

  public Vector2 cross(Vector2 other) {
    return new Vector2(0, 0); // TODO: cross 2d
  }

  public Vector2 normal() {
    double invLength = 1 / Math.sqrt(this.length2());
    return new Vector2(x * invLength, y * invLength);
  }

  public Vector2 reverse() {
    return new Vector2(-x, -y);
  }
  
  public Vector2 reciprocal() {
    return new Vector2(1/x, 1/y);
  }

  public Vector2 clone() {
    return new Vector2(x, y);
  }

  public static Vector2 zero() {
    return new Vector2(0, 0);
  }

  public static Vector2 one() {
    return new Vector2(1, 1);
  }
}