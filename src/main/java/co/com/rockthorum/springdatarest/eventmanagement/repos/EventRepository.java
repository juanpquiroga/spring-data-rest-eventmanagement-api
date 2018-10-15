package co.com.rockthorum.springdatarest.eventmanagement.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import co.com.rockthorum.springdatarest.eventmanagement.entities.Event;
import co.com.rockthorum.springdatarest.eventmanagement.entities.projections.PartialEventProjection;

import java.lang.String;
import java.time.ZoneId;
import java.util.List;

@RepositoryRestResource(excerptProjection=PartialEventProjection.class)
public interface EventRepository extends PagingAndSortingRepository<Event, Integer> {
	Page<Event> findByName(@Param("name") String name, Pageable pageable);
	
	Page<Event> findByNameAndZoneInfo(@Param("name") String name, @Param("zoneId") ZoneId zoneinfo, Pageable pageable );
	
	@Override
	@PreAuthorize("hasRole('ADMIN')")
	void deleteById(Integer id);
}
