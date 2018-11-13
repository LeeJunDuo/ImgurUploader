package jun.demo.service;

import jun.demo.bean.UploadEvent;

import java.util.List;
import java.util.Map;

public interface ImgurService {
	void upload(List<String> imageUrls, UploadEvent uploadEvent);
	List<String> getUploadedImages(Map<String, UploadEvent> eventRecord );
}
