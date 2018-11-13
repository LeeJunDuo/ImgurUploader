package jun.demo.service.impl;

import jun.demo.dto.SubmitResponse;
import jun.demo.dto.UploadedImage;
import jun.demo.service.WrapperService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("WrapperService")
public class ResponseWrapperImpl implements WrapperService {
	@Override
	public SubmitResponse submitEvent(String jobId) {
		return new SubmitResponse(jobId);
	}

	@Override
	public UploadedImage getAllUploadedImages(List<String> imageSrc) {
		UploadedImage images = new UploadedImage();
		images.setUploaded(imageSrc);
		return images;
	}
}
