package jun.demo.runnable;

import jun.demo.bean.SubmitAttr;
import jun.demo.bean.UploadEvent;
import jun.demo.helper.imgur.ImgurHelper;
import jun.demo.helper.imgur.PostResult;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class UploadTask implements Runnable {

	private String imageSrc;
	private SubmitAttr submitAttr;
	private Map<String, String > uploadStatus;

	public UploadTask(String imageSrc, SubmitAttr submitAttr, Map<String, String > uploadStatus) {
		this.imageSrc = imageSrc;
		this.submitAttr = submitAttr;
		this.uploadStatus = uploadStatus;
		this.uploadStatus.put(imageSrc, "");
	}

	@Override
	public void run() {
		// UploadTask to Imgur
		String clientId = submitAttr.getClientId();
		ImgurHelper imgurHelper = new ImgurHelper(clientId);
		PostResult postResult = imgurHelper.uploadImage(submitAttr.getSubmitUrl(), this.imageSrc).get();
		System.out.println("PostResult: " + postResult.getData().toString());

		// Update Concurrent Map
		uploadStatus.put(this.imageSrc, (String)postResult.getData().get("link"));
	}

	private void downloadImage(String imageSrc) {

		// Download image
		Image img = null;
		try {
			img = ImageIO.read(new URL(imageSrc));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Save image.
		//String fileName = extracFileName(imageSrc);
		//File file = new File(imagePath + fileName);
		//try {
		//	assert img != null;
		//	ImageIO.write((RenderedImage) img, getExtention(fileName), file);
		//} catch (IOException e) {
		//	e.printStackTrace();
		//}

		WritableRaster wr = ((BufferedImage) img).getRaster();
		DataBufferByte dbb = (DataBufferByte) wr.getDataBuffer();
	}

	private String extracFileName(String imageSrc) {
		String[] u = imageSrc.split("/");
		String fileName = u[u.length - 1];
		System.out.println("Download image " + fileName + " from url: " + imageSrc);
		return fileName;
	}

	private String getExtention(String fileName) {
		String[] f = fileName.split(".");
		//return f[f.length - 1];
		return "jpg";
	}
}
