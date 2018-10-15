package co.com.rockthorum.springdatarest.eventmanagement.repos;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.rockthorum.springdatarest.eventmanagement.entities.Organizer;

public interface OrganizerRepository extends PagingAndSortingRepository<Organizer, Integer> {

}
