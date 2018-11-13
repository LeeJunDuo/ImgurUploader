package jun.demo.service;

import jun.demo.bean.UploadEvent;

import java.util.List;
import java.util.Map;

public interface ImgurService {
	void upload(List<String> imageUrls,  String jobId, Map<String, UploadEvent> eventRecord);
	List<String> getUploadedImages(Map<String, UploadEvent> eventRecord );
}
