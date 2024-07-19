public class ToneMapping {
  public static Vector3 simple(Vector3 input) {
    Vector3 output = input.divide(input.add(0.16).multiply(1.02));

    return output;
  }
}
