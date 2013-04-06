package uk.ac.cam.cl.dtg.univdate.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Event {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String title;
	
	private String location;

	private String organiser;
	
	private String tags;
	
	private Date startTime;
	private Date endTime;
	private boolean provisional;

	public Event() {}
	
	public Event(String title, String location, String organiser, String tags,
			Date startTime, Date endTime) {
		super();
		this.title = title;
		this.location = location;
		this.organiser = organiser;
		this.tags = tags;
		this.startTime = startTime;
		this.endTime = endTime;
		this.provisional= true;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOrganiser() {
		return organiser;
	}

	public void setOrganiser(String organiser) {
		this.organiser = organiser;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public boolean isProvisional() {
		return provisional;
	}

	public void setProvisional(boolean provisional) {
		this.provisional = provisional;
	}

}
