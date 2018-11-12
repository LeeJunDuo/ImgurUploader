package jun.demo.helper.imgur;

import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.Optional;

public class ImgurHelper {
	private String clientId;

	public ImgurHelper(String clientId) {
		this.clientId = clientId;
	}

	public Optional<PostResult> uploadImage(String uploadUrl, byte[] fileBuffer) {
		final String url = uploadUrl;

		final HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Client-ID " + this.clientId );
		headers.set("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");

		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
		bodyMap.add("image", fileBuffer);

		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(bodyMap, headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<PostResult> response = restTemplate.exchange(url, HttpMethod.GET, entity, PostResult.class);
		return Optional.of(response.getBody());

	}
}
