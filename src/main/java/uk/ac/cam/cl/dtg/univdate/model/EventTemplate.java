package uk.ac.cam.cl.dtg.univdate.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.joda.time.LocalTime;
import org.joda.time.Period;

import uk.ac.cam.cl.dtg.univdate.InvalidRelativeDateSpecification;

@Entity
public class EventTemplate {

	@Id
	@GeneratedValue
	private Integer id;

	private String title;

	private String location;

	private String organiser;

	private String tags;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "term", column = @Column(name = "startTimeTerm")),
			@AttributeOverride(name = "day", column = @Column(name = "startDay")),
			@AttributeOverride(name = "hourOfDay", column = @Column(name = "startHourOfDay")),
			@AttributeOverride(name = "minute", column = @Column(name = "startMinute")), })
	private RelativeDate startTime;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "term", column = @Column(name = "endTimeTerm")),
			@AttributeOverride(name = "day", column = @Column(name = "endDay")),
			@AttributeOverride(name = "hourOfDay", column = @Column(name = "endHourOfDay")),
			@AttributeOverride(name = "minute", column = @Column(name = "endMinute")), })
	private RelativeDate endTime;

	public EventTemplate() {
	}

	public EventTemplate(String title, String location, String organiser,
			String tags, RelativeDate startTime, RelativeDate endTime) {
		super();
		this.title = title;
		this.location = location;
		this.organiser = organiser;
		this.tags = tags;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public EventTemplate(String title, String location, String organiser,
			String tags, RelativeDate startTime, Period period) {
		super();
		this.title = title;
		this.location = location;
		this.organiser = organiser;
		this.tags = tags;
		this.startTime = startTime;
		LocalTime ending = startTime.getTime().plus(period);

		this.endTime = new RelativeDate(startTime.getTerm(),
				startTime.getDay(), ending.getHourOfDay(),
				ending.getMinuteOfHour());
	}

	public Event schedule(int academicYear)
			throws InvalidRelativeDateSpecification {
		Date start = startTime.getTime(academicYear);
		Date end = endTime.getTime(academicYear);
		return new Event(title, location, organiser, tags, start, end);
	}

	public Integer getId() {
		return id;
	}

	public RelativeDate getStartTime() {
		return startTime;
	}

	public RelativeDate getEndTime() {
		return endTime;
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

	public void setStartTime(RelativeDate startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(RelativeDate endTime) {
		this.endTime = endTime;
	}

	public String getDayString() {
		return "Monday";
	}

}
