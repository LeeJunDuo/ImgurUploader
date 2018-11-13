package jun.demo.helper.imgur;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

public class ImgurHelper {
	private String clientId;

	public ImgurHelper(String clientId) {
		this.clientId = clientId;
	}

	public Optional<PostResult> uploadImage(String submitionUrl, String imageUrl) {
		HttpEntity<MultiValueMap<String, String>> entity = generateHttpEntity(imageUrl);
		RestTemplate restTemplate = new RestTemplate();
		URI uri = generateSubmitionURI(submitionUrl);
		ResponseEntity<PostResult> response = restTemplate.postForEntity(uri, entity, PostResult.class);
		return Optional.of(response.getBody());
	}

	private HttpEntity<MultiValueMap<String, String>> generateHttpEntity(String imageUrl) {
		final HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Client-ID " + this.clientId );
		//headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		//headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		headers.set("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
		//headers.set("content-type", "application/json;charset=UTF-8");

		MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
		bodyMap.add("image", imageUrl);

		return new HttpEntity<>(bodyMap, headers);
	}

	private URI generateSubmitionURI(String imageUrl) {
		URI uri = null;
		try {
			uri = new URI(imageUrl);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return uri;
	}
}
