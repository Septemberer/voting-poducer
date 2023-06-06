package itmo.apartmentService.controller;

import itmo.apartmentService.dto.VoteDTO;
import itmo.apartmentService.service.VotingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/apartmentService")
public class VoteController {

	private final VotingService votingService;

	@PostMapping(value = "/vote")
	public String createOffer(@RequestBody VoteDTO voteDTO) {
		votingService.sendVote(voteDTO.getToken(), voteDTO.getVoteId(), voteDTO.getPrice());
		return "Запрос обработан!";
	}

	@GetMapping(value = "/")
	public String hello() {
		return "Hello Voting!!!";
	}


}
