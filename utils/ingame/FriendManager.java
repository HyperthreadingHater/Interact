package us.interact.utils.ingame;

import java.util.HashMap;
import java.util.function.BiConsumer;

import us.interact.mod.ModManager;

public class FriendManager {

	private static HashMap<String, String> friends = new HashMap<>();

	public static void addFriend(String name, String alias) {
		friends.put(name, alias);
	}

	private static String remove = "";

	public static void removeFriend(String name) {
		if (isFriend(name)) {
			remove = "";
			friends.forEach(new BiConsumer<String, String>() {

				@Override
				public void accept(String n, String a) {
					if (n.equalsIgnoreCase(name) || a.equalsIgnoreCase(name))
						remove = n;
				}
			});
			friends.remove(remove);
		}
	}

	public static boolean isFriend(String name) {

		if (!ModManager.getMod("NoFriends").isEnabled()) {
			for (String s : friends.keySet())
				if (name.equalsIgnoreCase(s))
					return true;
			for (String s : friends.values())
				if (name.equalsIgnoreCase(s))
					return true;
		}

		return false;
	}

	public static boolean isCmdFriend(String name) {

		for (String s : friends.keySet())
			if (name.equalsIgnoreCase(s))
				return true;
		for (String s : friends.values())
			if (name.equalsIgnoreCase(s))
				return true;

		return false;
	}

	public static String getAlias(String name) {

		if (isFriend(name)) {
			return friends.get(name);
		}

		return null;
	}

	private static String name = "";

	public static String getName(String alias) {

		if (friends.containsValue(alias)) {
			friends.forEach(new BiConsumer<String, String>() {

				@Override
				public void accept(String s1, String s) {
					if (alias.equalsIgnoreCase(s))
						name = s1;
				}
			});
		}

		return null;
	}

	public static HashMap<String, String> getFriends() {
		return friends;
	}

	public static void clear() {
		friends.clear();
	}

}
