package co.com.rockthorum.springdatarest.eventmanagement.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
public class Organizer extends AbstractEntity {
	@Column(name="name")
	private String name;
	
	@OneToMany(mappedBy="organizer")
	private Set<Event> events;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}
	
	public int getResourceId() {
		return this.id;
	}

	@Override
	public String toString() {
		return String.format("Organizer [id=%s, created=%s, name=%s, events=%s]", id, created, name, events);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + created.hashCode();
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
		Organizer other = (Organizer) obj;
		if ( id != other.id)
			return false;
		return true;
	}
	
	
	
}
