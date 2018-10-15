package co.com.rockthorum.springdatarest.eventmanagement.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Participant extends AbstractEntity{
	@Column(name="name",nullable=false)
	private String name;
	
	@Column(name="email",nullable=false)
	private String email;
	
	@Column(name="checked_in")
	private Boolean checkedIn;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="event_id",nullable=false)
	private Event event;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean isCheckedIn() {
		return checkedIn;
	}

	public void setCheckedIn(Boolean checkedIn) {
		this.checkedIn = checkedIn;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
	public int getResourceId() {
		return this.id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((checkedIn == null) ? 0 : checkedIn.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Participant other = (Participant) obj;
		if ( id != other.id )
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Participant [id=%s, created=%s, name=%s, email=%s, checkedIn=%s]", id, created,
				name, email, checkedIn);
	}


}
