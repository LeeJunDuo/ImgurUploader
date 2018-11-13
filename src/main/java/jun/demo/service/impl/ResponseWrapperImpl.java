package jun.demo.service.impl;

import jun.demo.dto.SubmitResponse;
import jun.demo.service.WrapperService;
import org.springframework.stereotype.Service;

@Service("WrapperService")
public class ResponseWrapperImpl implements WrapperService {
	@Override
	public SubmitResponse submitEvent(String jobId) {
		return new SubmitResponse(jobId);
	}
}
