package jun.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/images/")
public class ImgurUploadController {

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
		return new ResponseEntity("Submit image url", HttpStatus.OK);
	}

}
