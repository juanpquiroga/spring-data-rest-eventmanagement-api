package co.com.rockthorum.springdatarest.eventmanagement.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import co.com.rockthorum.springdatarest.eventmanagement.entities.Event;
import co.com.rockthorum.springdatarest.eventmanagement.repos.EventRepository;

@RepositoryRestController
@RequestMapping("/events")
public class EventKickoffController {
	
	@Autowired
	private EventRepository eventRepo;
	
	@PostMapping("/start/{id}")
	public ResponseEntity start(@PathVariable("id") Integer id) {
		Optional<Event> optionalEvent = eventRepo.findById(id);
		if ( optionalEvent.isPresent() ) {
			Event event = optionalEvent.get();
			event.setStarted(true);
			eventRepo.save(event);
			return ResponseEntity.ok(event.getName() + " se inició");
		} else {
			throw new ResourceNotFoundException();
		}
	}
	
	
}
