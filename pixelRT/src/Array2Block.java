public class Array2Block {
  private int width;
  private int height;
  private Block[] data;

  public Array2Block(int width, int height) {
    this.width = width;
    this.height = height;
    this.data = new Block[width * height];
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public void fill(Block block) {
    for (int i = 0; i < width * height; i++)
      data[i] = block;
  }

  public Block get(int x, int y) {
    return x > -1 && x < width && y > -1 && y < height ? data[x + y * width] : null;
  }

  public void set(int x, int y, Block block) {
    this.data[x + y * width] = block;
  }
}