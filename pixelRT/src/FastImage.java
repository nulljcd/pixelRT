import java.awt.image.ColorModel;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.awt.image.Raster;

class FastImage {
  // https://stackoverflow.com/questions/33460365/what-the-fastest-way-to-draw-pixels-buffer-in-java
  private int width;
  private int height;
  private int[] raster;
  private ColorModel ColorModel;
  private DataBuffer buffer;
  private SampleModel sampleModel;
  private WritableRaster writableRaster;
  private BufferedImage image;

  public FastImage(int width, int height) {
    this.width = width;
    this.height = height;
    raster = new int[this.width*this.height];
    ColorModel = new DirectColorModel(24, 255, 255<<8, 255<<16);
    buffer = new DataBufferInt(raster, raster.length);
    sampleModel = ColorModel.createCompatibleSampleModel(this.width, this.height);
    writableRaster = Raster.createWritableRaster(sampleModel, buffer, null);
    image = new BufferedImage(ColorModel, writableRaster, false, null);
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public BufferedImage getBufferedImage() {
    return image;
  }

  public void set(int x, int y, int color) {
    raster[x+y*width] = color;
  }
}