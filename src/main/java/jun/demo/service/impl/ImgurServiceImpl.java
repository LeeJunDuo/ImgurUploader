package jun.demo.service.impl;

import jun.demo.bean.SubmitAttr;
import jun.demo.bean.UploadEvent;
import jun.demo.runnable.UploadTask;
import jun.demo.service.ImgurService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


@Service("ImgurService")
@PropertySource("classpath:application.properties")
public class ImgurServiceImpl implements ImgurService {
	@Value("${image.sink.path}")
	private String sinkPath;

	@Value("${imgur.upload.url}")
	private String submitUrl;

	@Value("${imgur.client.id}")
	private String clientId;

	ExecutorService exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

	@Override
	public void upload(List<String> imageSrc, String jobId, Map<String, UploadEvent> eventRecord) {
//		UploadEvent uploadEvent = new UploadEvent(jobId, new ConcurrentHashMap<>());
//		eventRecord.put(jobId, uploadEvent);
		SubmitAttr submitAttr = new SubmitAttr(sinkPath, clientId, submitUrl);
		imageSrc.forEach(src -> exec.execute(new UploadTask(src, submitAttr)));
	}
}
