package jun.demo.controller;

import jun.demo.Utils;
import jun.demo.bean.UploadEvent;
import jun.demo.dto.SubmitRequest;
import jun.demo.dto.SubmitResponse;
import jun.demo.dto.UploadedImage;
import jun.demo.service.ImgurService;
import jun.demo.service.WrapperService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@CrossOrigin(origins = "http://jun.local.demo:8888")
@RestController
@RequestMapping("/v1/images/")
public class ImgurUploadController {

	@Resource
	ImgurService imgurService;

	@Resource
	WrapperService wrapperService;

	private Map<String, UploadEvent> eventRecord = new ConcurrentHashMap<>();

	@GetMapping(value = "", produces = "application/json")
	public ResponseEntity<Object> getSubmitionResult() {
		UploadedImage image = wrapperService.getAllUploadedImages(imgurService.getUploadedImages(eventRecord));
		return new ResponseEntity(image, HttpStatus.OK);
	}

	@GetMapping(value = "upload/{jobId}", produces = "application/json")
	public ResponseEntity<Object> getSubmitionStatus(@PathVariable("jobId") String jobId) {
		System.out.println("JobId: " + jobId);
		return new ResponseEntity("Get Submition Status", HttpStatus.OK);
	}

	@PostMapping(value = "upload", produces = "application/json")
	public ResponseEntity<Object> sumbitImageUrl(@RequestBody SubmitRequest imageUrls) {
		System.out.println("Submit images url with:");
		for (String url : imageUrls.getUrls()) {
			System.out.println("\t" + url);
		}
		LocalDateTime currentDateTime = LocalDateTime.now();
		String jobId = generateJobId(currentDateTime);
		newUploadEvent(jobId);
		imgurService.upload(imageUrls.getUrls(), eventRecord.get(jobId));
		SubmitResponse sr = wrapperService.submitEvent(jobId);
		return new ResponseEntity(sr, HttpStatus.OK);
	}


	private String  generateJobId(LocalDateTime dateTime) {
		String jobId = Utils.md5(dateTime.toString());
		System.out.println("LocalDateTime " + dateTime.toString() + " to md5 : " + jobId);
		return jobId;
	}

	private void newUploadEvent(String jobId) {
		eventRecord.put(jobId, new UploadEvent(jobId, new ConcurrentHashMap<>()));
	}
}
