package com.impromptu.events;

import java.util.HashSet;
import java.util.Set;

import com.impromptu.users.User;

public class EventDetails extends Event {

    private Set<User> members;
    private Set<User> admins;
    
    public EventDetails() {
        super();
        members = new HashSet<User>();
        admins = new HashSet<User>();
    }
    
    private EventDetails(Event event) {
        super(event);
        members = new HashSet<User>();
        admins = new HashSet<User>();
    }
    
    public static EventDetails forEvent(Event event) {
        return new EventDetails(event);
    }

    public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }

    public Set<User> getAdmins() {
        return admins;
    }

    public void setAdmins(Set<User> admins) {
        this.admins = admins;
    }

}
