package jun.demo.service;

import jun.demo.dto.SubmitResponse;

import java.time.LocalDateTime;

public interface WrapperService {
	SubmitResponse submitEvent(LocalDateTime dateTime);
}
