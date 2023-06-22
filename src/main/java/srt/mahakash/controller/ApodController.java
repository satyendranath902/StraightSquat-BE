package srt.mahakash.controller;

import srt.mahakash.model.ApodModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

@RestController
@RequestMapping("api/v1/image")
public class ApodController {

	private static final Logger log = LoggerFactory.getLogger(ApodController.class);

	private String imageTitle;

	@Autowired
	private ApodModel apodModel;

	@GetMapping
	public String getImage() throws IOException, URISyntaxException {
		String imageUrl = apodModel.getUrl();
		// String hdImageUrl = apodResponseModel.getHdurl();
		byte[] response = readImage(imageUrl);
		imageTitle = apodModel.getTitle();
		String author = apodModel.getCopyright();
		if (null == author) {
			author = "NASA";
		}
		saveImage(response);
		displayImage(imageUrl);
		// displayHDImage(hdImageUrl);
		return imageTitle + "( " + apodModel.getDate() + ") " + "<br>" + "<br>" + author + ":" + "<br>"
				+ apodModel.getExplanation();
	}

	private byte[] readImage(String imageUrl) throws IOException, URISyntaxException {
		URL url = new URL(imageUrl);
		InputStream in = new BufferedInputStream(url.openStream());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int n = 0;
		while (-1 != (n = in.read(buf))) {
			out.write(buf, 0, n);
		}
		out.close();
		in.close();
		return out.toByteArray();
	}

	private void saveImage(byte[] response) throws IOException {
		FileOutputStream fos = new FileOutputStream(imageTitle + ".jpg");
		fos.write(response);
		fos.close();
	}

	private void displayImage(String imageUrl) {
		Image image = null;
		try {
			URL url = new URL(imageUrl);
			image = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}

		JFrame frame = new JFrame();
		frame.setSize(800, 650);
		JLabel label = new JLabel(new ImageIcon(image));
		frame.add(label);
		frame.setVisible(true);
	}

	/*
	 * private void displayHDImage(String hdImageUrl) { Image image = null; try {
	 * URL url = new URL(hdImageUrl); image = ImageIO.read(url); } catch
	 * (IOException e) { e.printStackTrace(); }
	 * 
	 * JFrame frame = new JFrame(); frame.setSize(800, 650); JLabel label = new
	 * JLabel(new ImageIcon(image)); frame.add(label); frame.setVisible(true); }
	 */

}
