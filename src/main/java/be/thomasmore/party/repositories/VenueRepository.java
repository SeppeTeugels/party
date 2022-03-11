package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Venue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface VenueRepository extends CrudRepository<Venue,Integer> {
    Iterable<Venue> findByOutdoor(boolean outdoor);
    Iterable<Venue> findByIndoor(boolean indoor);

    @Query("SELECT v from Venue v where ((:min IS NULL or :min <= v.capacity) AND (:max IS NULL or :max >= v.capacity)) and (:maxkm IS NULL or v.distanceFromPublicTransportInKm <=:maxkm) and (:food IS NULL or v.foodProvided= :food) and (:indoor IS NULL or v.indoor = :indoor) and (:outdoor IS NULL or  v.outdoor = :outdoor)")
    Iterable<Venue> findByFilterContainingIgnoreCase(@Param("min") Integer min,@Param("max") Integer max, @Param("maxkm") Double maxKm, @Param("food") Boolean food, @Param("indoor") Boolean indoor, @Param("outdoor") Boolean outdoor);
}

