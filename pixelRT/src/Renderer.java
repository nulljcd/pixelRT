import java.util.Random;

public class Renderer {
  private int width;
  private int height;
  private Array2Vector3 frameBuffer;
  private FastImage image;
  private int frames;

  public Renderer(int width, int height) {
    this.width = width;
    this.height = height;
    this.frameBuffer = new Array2Vector3(width, height);
    this.image = new FastImage(width, height);
    this.frames = 0;

    frameBuffer.fill(Vector3.zero());
  }

  public FastImage getImage() {
    return image;
  }

  public int getFrames() {
    return frames;
  }

  public void render(Array2Block world, int maxBounces, int maxRayCastSteps) {
    frames++;

    for (int y = 0; y<height; y++) {
      for (int x = 0; x<width; x++) {
        Block block = world.get(x, y);

        Vector3 color = Vector3.zero();

        if (block.getType() == 0) {
          Vector3 incomingLight = Vector3.zero();
          Vector3 rayColor = block.getColor();

          Vector2 position = new Vector2(x, y).add(0.5);
          Vector2 direction = randomDirection();

          for (int bounces = 0; bounces < maxBounces; bounces++) {
            RayCastHitInfo hitInfo = rayCast(world, position, direction, maxRayCastSteps);

            if (hitInfo != null) {
              if (hitInfo.getBlock().getType() == 1) {
                position = hitInfo.getPosition().add(hitInfo.getNormal()).add(0.6);
                direction = hitInfo.getNormal().add(randomDirection()).normal();
                rayColor = rayColor.multiply(hitInfo.getBlock().getColor());
              } else if (hitInfo.getBlock().getType() == 2) {
                Vector3 emittedLight = hitInfo.getBlock().getColor().multiply(hitInfo.getBlock().getStrength());
                incomingLight = incomingLight.add(emittedLight.multiply(rayColor));
                break;
              }
            } else
              break;
          }

          color = incomingLight;
        } else if (block.getType() == 1) {
          color = block.getColor().multiply(.2);
        } else if (block.getType() == 2) {
          color = block.getColor().multiply(block.getStrength());
        }

        frameBuffer.set(x, y, frameBuffer.get(x, y).add(color));

        image.set(x, y, ToneMapping.simple(frameBuffer.get(x, y).multiply(1 / (double) frames)).getRGB());
      }
    }
  }

  private RayCastHitInfo rayCast(Array2Block world, Vector2 position, Vector2 direction, int maxSteps) {
    Vector2 worldPosition = MathA.floor(position);
    Vector2 deltaDist = MathA.abs(Vector2.one().divide(direction));
    Vector2 step = MathA.floor(MathA.sign(direction));
    Vector2 sideDist = Vector2.zero();
    sideDist.setX(direction.getX() < 0 ? (position.getX() - worldPosition.getX()) * deltaDist.getX()
        : (worldPosition.getX() + 1 - position.getX()) * deltaDist.getX());
    sideDist.setY(direction.getY() < 0 ? (position.getY() - worldPosition.getY()) * deltaDist.getY()
        : (worldPosition.getY() + 1 - position.getY()) * deltaDist.getY());
    int side = 0;

    for (int steps = 0; steps < maxSteps; steps++) {
      if (sideDist.getX() < sideDist.getY()) {
        sideDist.setX(sideDist.getX() + deltaDist.getX());
        worldPosition.setX(worldPosition.getX() + step.getX());
        side = 0;
      } else {
        sideDist.setY(sideDist.getY() + deltaDist.getY());
        worldPosition.setY(worldPosition.getY() + step.getY());
        side = 1;
      }

      Block block = world.get((int) worldPosition.getX(), (int) worldPosition.getY());
      
      if (block == null)
        return null;

      if (block.getType() != 0) {
        double length = side == 0 ? sideDist.getX() - deltaDist.getX() : sideDist.getY() - deltaDist.getY();

        Vector2 normal = Vector2.zero();
        if (side == 0)
          normal.setX(-Math.signum(direction.getX()));
        else
          normal.setY(-Math.signum(direction.getY()));

        return new RayCastHitInfo(block, worldPosition, length, normal);
      }
    }

    return null;
  }

  private Vector2 randomDirection() {
    Random random = new Random();
    return new Vector2(random.nextGaussian(), random.nextGaussian()).normal();
  }
}
