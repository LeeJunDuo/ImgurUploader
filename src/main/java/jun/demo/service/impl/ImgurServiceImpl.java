package jun.demo.service.impl;

import jun.demo.bean.UploadEvent;
import jun.demo.runnable.UploadTask;
import jun.demo.service.ImgurService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


@Service("ImgurService")
public class ImgurServiceImpl implements ImgurService {
	ExecutorService exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

	@Override
	public void upload(List<String> imageUrls, String jobId, Map<String, UploadEvent> eventRecord) {
//		UploadEvent uploadEvent = new UploadEvent(jobId, new ConcurrentHashMap<>());
//		eventRecord.put(jobId, uploadEvent);
		imageUrls.forEach(url -> exec.execute(new UploadTask(url)));
	}
}
