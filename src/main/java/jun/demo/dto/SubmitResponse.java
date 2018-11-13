package jun.demo.dto;

public class SubmitResponse {
	private String jobId;
	public SubmitResponse(String jsonId) {
		this.jobId = jsonId;
	}


	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
}
