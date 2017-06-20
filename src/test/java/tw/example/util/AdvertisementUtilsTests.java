package tw.example.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static tw.exmaple.util.AdvertisementUtils.toAdvertisement;

import org.junit.Test;

import tw.exmaple.core.Advertisement;
import tw.exmaple.util.IOUtils;

public class AdvertisementUtilsTests {

	@Test
	public void testToAdvertisement() {
		String response = IOUtils.toString(getClass().getResourceAsStream("sample.json"));
		Advertisement advertisement = toAdvertisement(response);
		assertNotNull(advertisement);
		assertEquals("女明星人手一雙！最近引爆話題的人氣品牌就是它！", advertisement.getTitle());
		assertEquals("是什麼品牌，能在好萊塢持燒發燒，答案就是UGG！不僅變形金剛女主角愛穿，連時尚Icon金手指造型師Rachel  Zoe都大推，不僅有時尚造型，「舒適度」更是無人能及......", advertisement.getDescription());
		assertEquals("//tenmaximg.cacafly.net/upload/7/7/7/0/76ebda77_icon_s.jpg?v=2", advertisement.getIconUrl());
		assertEquals("//tenmaximg.cacafly.net/upload/7/7/7/0/76ebda77.png?v=2", advertisement.getImageUrl());
		assertEquals("https://beta-rtb.tenmax.io/bid/asiamax/click/1497922708704/34de6590-5559-11e7-9cd4-dd76d956f8c0/5474/7770/?optInfo=xlKYg0A6C3ru_w&sUrl=http%3A%2F%2Fbeta-ssp.tenmax.io%2Fauction%2Fwinner%2Ftracking%2Fclk%3Fb%3D34de6590-5559-11e7-9cd4-dd76d956f8c0%26i%3D0%26id%3Ddb0efe1b-8522-45f8-a460-86bf42e3e3b8%26adurl%3D&rUrl=https%3A%2F%2Fwww.facebook.com%2FUGG-450444628492225%2F%3Ffref%3Dts", advertisement.getClickUrl());
		assertEquals("//beta-ssp.tenmax.io/supply/tracking/pixel?s=34de6590-5559-11e7-9cd4-dd76d956f8c0&r=55ba76bca772421f", advertisement.getImpressionLink());
	}

	@Test
	public void testToAdvertisementWithEmptyJson() {
		Advertisement advertisement = toAdvertisement("{}");
		assertNull(advertisement);
	}
}
