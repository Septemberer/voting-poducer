package itmo.apartmentService.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class VotingService {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(VotingService.class);
	private final KafkaTemplate<Object, Object> template;

	public VotingService(KafkaTemplate<Object, Object> template) {
		this.template = template;
	}

	public void sendVote(String token, Long voteId, Long price) {
		if (token.trim().isEmpty() || voteId < 0 || price <= 0) {
			log.info("Bad request");
			return;
		}
		String message = String.format("%s&&%s&&%s", token, voteId, price);
		log.info("producing message to Kafka, topic=voting.topic, message={}", message);
		this.template.send("voting.topic", message);
	}
}