package tw.exmaple.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tw.exmaple.core.Advertisement;

public interface AdvertisementRepository extends CrudRepository<Advertisement, String> {

	List<Advertisement> findByTitleContaining(String title);
}
