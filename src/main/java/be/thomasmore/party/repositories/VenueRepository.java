package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Venue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface VenueRepository extends CrudRepository<Venue,Integer> {
    Iterable<Venue> findByOutdoor(boolean outdoor);
    Iterable<Venue> findByIndoor(boolean indoor);
    @Query
            ("SELECT v from Venue v where v.capacity >=:min and v.capacity<=:max")
    Iterable<Venue> findByCapacityBetween (@Param("min") Integer minCapacity , @Param("max") Integer maxCapacity);
    @Query
            ("SELECT v from Venue v where v.capacity<=:max")
    Iterable<Venue> findBySmaller( @Param("max") Integer maxCapacity);

    @Query
            ("SELECT v from Venue v where v.capacity >=:min")
    Iterable<Venue> findBybigger( @Param("min") Integer minCapacity);

}
