package com.impromptu.events;

import com.impromptu.users.User;

public interface EventMembershipService {

	Event leaveEvent(String eventId, User member);
	
	Event joinEvent(String eventId, User user);
}
