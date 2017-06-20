package tw.example.ws.tenmax;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.example.core.Advertisement;
import tw.example.repository.AdvertisementRepository;

@RestController
public class TenMaxAdvertisementWebService {

	@Autowired
	private AdvertisementRepository repository;

	@RequestMapping("/advertisements")
	public List<Advertisement> findAdvertisements(@RequestParam(name = "q", defaultValue = "") final String keyword) {
		if (keyword.length() > 0) {
			return repository.findByTitleContaining(keyword);
		}
		List<Advertisement> results = new ArrayList<>();
		repository.findAll().forEach(results::add);
		return results;
	}
}
