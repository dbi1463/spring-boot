package tw.exmaple.repository;

import org.springframework.data.repository.CrudRepository;

import tw.exmaple.core.Advertisement;

public interface AdvertisementRepository extends CrudRepository<Advertisement, String> {

}
