package com.opl.mudra.api.oneform.enums;

/**
 * @author Sanket
 *
 */
public enum DistributionMarketingTieUps {
	ANY_LARGE_WORLDWIDE_PLAYER_PLAYERS(1, "Any Large Worldwide player/Players",
			"Any Large Worldwide player/Players"), ANY_COUNTRY_WIDE_PLAYER_PLAYERS(2, "Any Country wide player/Players",
					"Any Country wide player/Players"), ANY_REGIONAL_PLAYER_PLAYERS(3, "Any Regional Player/Players",
							"Any Regional Player/Players"), OWN(4, "Own", "Own");

	private final Integer id;
	private final String value;
	private final String description;

	DistributionMarketingTieUps(Integer id, String value, String description) {
		this.id = id;
		this.value = value;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}

	public static DistributionMarketingTieUps getById(Integer id) {
		switch (id) {
		case 1:
			return ANY_LARGE_WORLDWIDE_PLAYER_PLAYERS;
		case 2:
			return ANY_COUNTRY_WIDE_PLAYER_PLAYERS;
		case 3:
			return ANY_REGIONAL_PLAYER_PLAYERS;
		case 4:
			return OWN;
		default:
			return null;
		}
	}

	public static DistributionMarketingTieUps[] getAll() {
		return DistributionMarketingTieUps.values();

	}
}