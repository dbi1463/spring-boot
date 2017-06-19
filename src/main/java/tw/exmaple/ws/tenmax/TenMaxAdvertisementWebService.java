package tw.exmaple.ws.tenmax;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.exmaple.core.Advertisement;
import tw.exmaple.repository.AdvertisementRepository;

@RestController
public class TenMaxAdvertisementWebService {

	@Autowired
	private AdvertisementRepository repository;

	@RequestMapping("/advertisements")
	public List<Advertisement> findAdvertisements(@RequestParam(name = "q", defaultValue = "") String keyword) {
		if (keyword.length() > 0) {
			// TODO: add query method in the repository
		}
		List<Advertisement> results = new ArrayList<>();
		repository.findAll().forEach(results::add);
		return results;
	}
}
