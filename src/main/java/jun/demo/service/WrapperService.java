package jun.demo.service;

import jun.demo.dto.SubmitResponse;
import jun.demo.dto.UploadedImage;

import java.util.List;

public interface WrapperService {
	SubmitResponse submitEvent(String jobId);
	UploadedImage getAllUploadedImages(List<String> imageSrc);
}
