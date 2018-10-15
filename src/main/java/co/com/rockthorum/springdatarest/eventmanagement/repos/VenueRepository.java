package co.com.rockthorum.springdatarest.eventmanagement.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import co.com.rockthorum.springdatarest.eventmanagement.entities.Venue;
import java.lang.String;

public interface VenueRepository extends PagingAndSortingRepository<Venue, Integer> {
	Page<Venue> findByPostalCode(@Param("postalCode") String postalcode, Pageable pageable);
	
}
