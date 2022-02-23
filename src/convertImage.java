import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class convertImage {
public static boolean imageConverter (String inputImagePath,String outputImagePath, String formatName)  {
    try {
        FileInputStream inputStream = new FileInputStream(inputImagePath);
        FileOutputStream outputStream = new FileOutputStream(outputImagePath);

        // reads input image from file
        BufferedImage inputImage = ImageIO.read(inputStream);

        // writes to the output image in specified format
        boolean result = ImageIO.write(inputImage, formatName, outputStream);

        // needs to close the streams
        outputStream.close();
        inputStream.close();

        return result;
    }
    catch( IOException e){
        System.out.println("error in converting image");
        e.printStackTrace();
        return false;
    }
        }
    }


