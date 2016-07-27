package barcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

public class Program {

	public static void main(String[] args) {
		try {
			 //生成qr
			// String content = "120605181003;http://www.cnblogs.com/jtmjx";
			 String content = "18915590902";
			 String path = "C:/";
			
			 MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
			
			 Map hints = new HashMap();
			 hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			 BitMatrix bitMatrix = multiFormatWriter.encode(content,
			 BarcodeFormat.CODE_128, 100, 50,hints);
			 //qr.jpg    code128.jpg
			 File file1 = new File(path,"code128.jpg");
			 MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file1);

		 
			
			//解析条码
			MultiFormatReader formatReader = new MultiFormatReader();
			String filePath = "C:/code128.jpg";
			File file = new File(filePath);
			BufferedImage image = ImageIO.read(file);
	
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			Binarizer binarizer = new HybridBinarizer(source);
			BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
			Map hintss = new HashMap();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			Result result = formatReader.decode(binaryBitmap, hintss);

			System.out.println("result = " + result.toString());
			System.out.println("resultFormat = " + result.getBarcodeFormat());
			System.out.println("resultText = " + result.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}