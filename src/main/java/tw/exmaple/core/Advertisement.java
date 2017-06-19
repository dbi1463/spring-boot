package tw.exmaple.core;

import static java.lang.Integer.MAX_VALUE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Advertisement {

	@Id
	private String title;

	@Column(length = MAX_VALUE)
	private String description;

	@Column(length = MAX_VALUE)
	private String imageUrl;

	@Column(length = MAX_VALUE)
	private String iconUrl;

	@Column(length = MAX_VALUE)
	private String impressionLink;

	@Column(length = MAX_VALUE)
	private String clickUrl;

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public String getImpressionLink() {
		return impressionLink;
	}

	public String getClickUrl() {
		return clickUrl;
	}

	public void setTitle(String value) {
		title = value;
	}

	public void setDescription(String value) {
		description = value;
	}

	public void setImageUrl(String url) {
		imageUrl = url;
	}

	public void setIconUrl(String url) {
		iconUrl = url;
	}

	public void setImpressionLink(String link) {
		impressionLink = link;
	}

	public void setClickUrl(String url) {
		clickUrl = url;
	}
}
