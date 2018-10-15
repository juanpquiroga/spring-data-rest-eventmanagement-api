package co.com.rockthorum.springdatarest.eventmanagement.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.com.rockthorum.springdatarest.eventmanagement.entities.Participant;
import co.com.rockthorum.springdatarest.eventmanagement.exceptions.AlreadyCheckedInException;
import co.com.rockthorum.springdatarest.eventmanagement.exceptions.NotCheckedInException;
import co.com.rockthorum.springdatarest.eventmanagement.repos.ParticipantRepository;

@RepositoryRestController
@RequestMapping("/events")
public class CheckinOutController {

	@Autowired
	private ParticipantRepository participantRepo;
	
	@PostMapping("/checkin/{id}")
	public ResponseEntity<PersistentEntityResource> checkin(@PathVariable("id") Integer id, PersistentEntityResourceAssembler assembler) {
		Optional<Participant> optionalParticipant = participantRepo.findById(id);
		if ( optionalParticipant.isPresent() ) {
			Participant participant = optionalParticipant.get();
			if ( participant.isCheckedIn() ) {
				throw new AlreadyCheckedInException();
			} else {
				participant.setCheckedIn(true);
				participantRepo.save(participant);
				return ResponseEntity.ok(assembler.toResource(participant));
			}
		} else {
			throw new ResourceNotFoundException();
		}
	}
	
	@PostMapping("/checkout/{id}")
	public ResponseEntity<PersistentEntityResource> checkout(@PathVariable("id") Integer id, PersistentEntityResourceAssembler assembler) {
		Participant participant = participantRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException());
		if ( !participant.isCheckedIn() ) {
			throw new NotCheckedInException();
		} else {
			participant.setCheckedIn(false);
			participantRepo.save(participant);
			return ResponseEntity.ok(assembler.toResource(participant));
		}
	}
}
