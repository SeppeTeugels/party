package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Artist;
import be.thomasmore.party.model.Venue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ArtistRepository extends CrudRepository<Artist,Integer> {
    @Query("SELECT v from Artist v where upper(v.artistName)  like %:keyword%")
    Iterable<Artist> findByTitleContainingIgnoreCase(@Param("keyword") String keyword);
}
