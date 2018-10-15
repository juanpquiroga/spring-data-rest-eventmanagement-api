package co.com.rockthorum.springdatarest.eventmanagement.entities;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@JsonPropertyOrder({"resourceId","name","desc","started"})
public class Event extends AbstractEntity{
	@Column(name="name",nullable=false)
	private String name;
	
	@Column(name="description")
	@JsonProperty("desc")
	private String description;
	
	@Column(name="start_time")
	private ZonedDateTime startTime;
	
	@Column(name="end_time")
	private ZonedDateTime endTime;
	
	@Column(name="zone_id")
	private ZoneId zoneInfo;
	
	@Column(name="started")
	private Boolean started;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="organizer_id",nullable=false)
	private Organizer organizer;

	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="venue_id",nullable=false)
	@RestResource(exported=false)
	private Venue venue;
	
	@OneToMany(mappedBy="event",fetch=FetchType.EAGER,cascade=CascadeType.ALL,orphanRemoval=true)
	private Set<Participant> participants;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ZonedDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(ZonedDateTime startTime) {
		this.startTime = startTime;
	}

	public ZonedDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(ZonedDateTime endTime) {
		this.endTime = endTime;
	}

	public ZoneId getZoneInfo() {
		return zoneInfo;
	}

	public void setZoneInfo(ZoneId zoneInfo) {
		this.zoneInfo = zoneInfo;
	}

	public Boolean getStarted() {
		return started;
	}

	public void setStarted(Boolean started) {
		this.started = started;
	}

	public Organizer getOrganizer() {
		return organizer;
	}

	public void setOrganizer(Organizer organizer) {
		this.organizer = organizer;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}
	
	public int getResourceId() {
		return this.id;
	}

	public Set<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<Participant> participants) {
		this.participants = participants;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((started == null) ? 0 : started.hashCode());
		result = prime * result + ((zoneInfo == null) ? 0 : zoneInfo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if ( id != other.id )
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return String.format(
				"Event [id=%s, created=%s, name=%s, description=%s, startTime=%s, endTime=%s, zoneInfo=%s, started=%s, venue=%s, participants=%s]",
				id, created, name, description, startTime, endTime, zoneInfo, started, venue, participants);
	}
}
