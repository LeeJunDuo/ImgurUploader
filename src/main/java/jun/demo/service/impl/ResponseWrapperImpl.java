package jun.demo.service.impl;

import jun.demo.dto.SubmitResponse;
import jun.demo.service.WrapperService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

@Service("WrapperService")
public class ResponseWrapperImpl implements WrapperService {
	@Override
	public SubmitResponse submitEvent(LocalDateTime dateTime) {
		String eventId = DigestUtils.md5DigestAsHex(dateTime.toString().getBytes());
		System.out.println("LocalDateTime " + dateTime.toString() + " to md5 : " + eventId);
		return new SubmitResponse(eventId);
	}
}
