package com.hbmpojos;

// Generated 15 Dec, 2015 1:12:09 PM by Hibernate Tools 3.4.0.CR1

/**
 * PlaceNameXid generated by hbm2java
 */
public class PlaceNameXid implements java.io.Serializable {

	private Integer id;
	private String placeName;
	private Integer apsrtcId;
	private Integer tsrtcId;
	private Integer abhibusId;

	public PlaceNameXid() {
	}

	public PlaceNameXid(String placeName, Integer apsrtcId, Integer tsrtcId,
			Integer abhibusId) {
		this.placeName = placeName;
		this.apsrtcId = apsrtcId;
		this.tsrtcId = tsrtcId;
		this.abhibusId = abhibusId;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlaceName() {
		return this.placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public Integer getApsrtcId() {
		return this.apsrtcId;
	}

	public void setApsrtcId(Integer apsrtcId) {
		this.apsrtcId = apsrtcId;
	}

	public Integer getTsrtcId() {
		return this.tsrtcId;
	}

	public void setTsrtcId(Integer tsrtcId) {
		this.tsrtcId = tsrtcId;
	}

	public Integer getAbhibusId() {
		return this.abhibusId;
	}

	public void setAbhibusId(Integer abhibusId) {
		this.abhibusId = abhibusId;
	}

}