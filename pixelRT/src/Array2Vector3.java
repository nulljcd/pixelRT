public class Array2Vector3 {
  private int width;
  private int height;
  private Vector3[] data;

  public Array2Vector3(int width, int height) {
    this.width = width;
    this.height = height;
    this.data = new Vector3[width * height];
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public void fill(Vector3 vector3) {
    for (int i = 0; i < width * height; i++)
      data[i] = vector3;
  }

  public Vector3 get(int x, int y) {
    return x > -1 && x < width && y > -1 && y < height ? data[x + y * width] : null;
  }

  public void set(int x, int y, Vector3 vector3) {
    this.data[x + y * width] = vector3;
  }
}