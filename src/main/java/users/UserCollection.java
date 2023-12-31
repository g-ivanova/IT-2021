package users;

import java.util.HashSet;
import java.util.Set;

public class UserCollection {
	
	private static UserCollection instance = null;
	private static Set<User> collection;
	static int index=1;
	
	private UserCollection()
	{}
	
public static UserCollection getInstance()
{
	if(instance == null)
	{
		instance = new UserCollection();
		collection = new HashSet<User>();
	}
	
	return instance;
	
	}

public void addUser(User user)
{
user.setId(index++);
collection.add(user);	
}

public User getUser(String userName)
{
	for(User u:collection)
	{
		if(u.getUserName().equals(userName))
		{
			return u;
		}
	}
	
	return null;
}


public User getById(int id)
{
	User user = null;
	for(User u:collection)
		
	{
		if(id==u.getId())
		{
			user = u;
		}
	}
	
	return user;	
}

public boolean checkifExist(User user)
{
	return collection.contains(user);
}

}
