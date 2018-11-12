package jun.demo.controller;

import jun.demo.bean.UploadEvent;
import jun.demo.dto.UploadedDTO;
import jun.demo.service.ImgurService;
import jun.demo.service.WrapperService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

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
		return new ResponseEntity("Get Submition Result", HttpStatus.OK);
	}

	@GetMapping(value = "upload/${jobId}", produces = "application/json")
	public ResponseEntity<Object> getSubmitionStatus() {
		return new ResponseEntity("Get Submition Status", HttpStatus.OK);
	}

	@PostMapping(value = "upload", produces = "application/json")
	public ResponseEntity<Object> sumbitImageUrl() {
		LocalDateTime currentDateTime = LocalDateTime.now();

		return new ResponseEntity(wrapperService.submitEvent(currentDateTime), HttpStatus.OK);
	}

}
