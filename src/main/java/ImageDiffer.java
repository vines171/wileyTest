import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ImageDiffer {
  public static boolean checkImage(Object image) throws IOException {
    BufferedImage extendImage = ImageIO.read(new File(EndpointEnum.ImagePathApi));
    BufferedImage actualImage = ImageIO.read((InputStream) image);

    DataBuffer extendImageBuffer = extendImage.getRaster().getDataBuffer();
    DataBuffer actualImageBuffer = actualImage.getRaster().getDataBuffer();

    int extendImageWidth = extendImage.getWidth();
    int actualImageWidth = actualImage.getWidth();
    int actualImageHeight = actualImage.getHeight();
    int extendImageHeight = extendImage.getHeight();
    ColorModel extendImageColorModel = extendImage.getColorModel();
    ColorModel actualImageColorModel = actualImage.getColorModel();

    if ((actualImageWidth != extendImageWidth)
        || (actualImageHeight != extendImageHeight)
        || (extendImageColorModel != actualImageColorModel)) {
      return false;
    } else if ((extendImageBuffer.getDataType()) != (actualImageBuffer.getDataType())
        || (actualImageBuffer.getSize() != actualImageBuffer.getSize())) return false;
    else return true;
  }
}
