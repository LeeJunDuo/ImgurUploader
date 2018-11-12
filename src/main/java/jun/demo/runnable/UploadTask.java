package jun.demo.runnable;

import jun.demo.helper.imgur.ImgurHelper;
import jun.demo.helper.imgur.PostResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

@PropertySource("classpath:application.properties")
public class UploadTask implements Runnable {
	@Value("${image.sink.path}")
	private String imagePath;

	@Value("${imgur.upload.url")
	private String imgurUploadUrl;

	@Value("${imgur.oath.clentId")
	private String clientId;

	private String imageUrl;

	public UploadTask(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public void run() {
		// Download image
		Image img = null;
		try {
			img = ImageIO.read(new URL(this.imageUrl));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Save image.
		String fileName = extracFileName(imageUrl);
		File file = new File(imagePath + fileName);
		try {
			assert img != null;
			ImageIO.write((RenderedImage) img, getExtention(fileName), file);
		} catch (IOException e) {
			e.printStackTrace();
		}


		// UploadTask to Imgur
		ImgurHelper imgurHelper = new ImgurHelper(this.clientId);
		WritableRaster wr = ((BufferedImage) img).getRaster();
		DataBufferByte dbb = (DataBufferByte) wr.getDataBuffer();
		PostResult postResult = imgurHelper.uploadImage(this.imgurUploadUrl, dbb.getData()).get();
		System.out.println("PostResult: " + postResult.toString());
	}

	private String extracFileName(String imageUrl) {
		String[] u = imageUrl.split("/");
		String fileName = u[u.length - 1];
		System.out.println("Download image " + fileName + " from url: " + imageUrl);
		return fileName;
	}

	private String getExtention(String fileName) {
		String[] f = fileName.split(".");
		return f[f.length - 1];
	}
}
