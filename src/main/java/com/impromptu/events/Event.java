package com.impromptu.events;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.format.annotation.DateTimeFormat;

import com.impromptu.users.User;

public class Event {

	@Id
	private String id;
	
	@NotBlank
	private String eventName;
	
	private String eventDescription;
	
	@NotNull
	@DateTimeFormat(pattern="mm/dd/yyyy")
	private Date eventDate;
	
	private boolean recurring;
	
	@NotBlank
	private String location;
	
	private List<String> messages;
	
	@Indexed
	private Set<String> adminIds;

	@Indexed
	private Set<String> memberIds;
	
	private String eventPic;
	
	public Event() {

		this.memberIds = new HashSet<String>();
		this.adminIds = new HashSet<String>();
		this.messages = new ArrayList<String>();
	}
	
	public Event(Event other) {
		setId(other.getId());
		setEventName(other.getEventName());
		setEventDescription(other.getEventDescription());
		setEventDate(getEventDate());
		setRecurring(other.isRecurring());
		setLocation(other.getLocation());
		setAdminIds(other.getAdminIds());
		setMemberIds(other.getMemberIds());
		setMessages(other.getMessages());
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	
	public boolean isRecurring() {
		return recurring;
	}
	
	public void setRecurring(boolean recurring) {
		this.recurring = recurring;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public Date getEventDate() {
		return eventDate;
	}
	
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	
	public Set<String> getMemberIds() {
		return memberIds;
	}
	
	public void setMemberIds(Set<String> memberIds) {
		this.memberIds = memberIds;
	}
	
	public List<String> getMessages() {
		return messages;
	}
	
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	
	public Set<String> getAdminIds() {
		return adminIds;
	}
	
	public void setAdminIds(Set<String> adminIds) {
		this.adminIds = adminIds;
	}
	
	public String getEventPic() {
		return eventPic;
	}
	
	public void setEventPic(String eventPic) {
		this.eventPic = eventPic;
	}
	
	public boolean hasMember(User user) {
		return getMemberIds().contains(user.getId());
	}
}
