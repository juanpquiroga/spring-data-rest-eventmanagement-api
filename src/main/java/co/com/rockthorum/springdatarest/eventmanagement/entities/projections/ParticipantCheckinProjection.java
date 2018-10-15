package co.com.rockthorum.springdatarest.eventmanagement.entities.projections;

import org.springframework.data.rest.core.config.Projection;

import co.com.rockthorum.springdatarest.eventmanagement.entities.Participant;

@Projection(name="participantPartial",types= {Participant.class})
public interface ParticipantCheckinProjection {
	String getName();
	Boolean getCheckedIn();
}
