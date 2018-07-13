package us.interact.utils.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.commons.compress.utils.IOUtils;

import us.interact.Interact;

public class OnlineSettings {
	
	public static String getSettings(String name) {
		BufferedReader reader = null;
		StringBuilder builder = new StringBuilder(128000);
		try {
			reader = new BufferedReader(new InputStreamReader(new URL("http://interact.bplaced.net/client/settings/" + name + ".txt").openStream(), "UTF-8"));
			int count;
			char[] data = new char[5000];
			while((count = reader.read(data)) != -1)
				builder.append(data, 0, count);
		} catch(IOException e){
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(reader);
		}
		
		String s = "NONE";
		
		if(builder.toString().startsWith("Speed"))
			s = builder.toString();
		
		return s;
	}
	
	public static String getAvailableSettings() {
		BufferedReader reader = null;
		StringBuilder builder = new StringBuilder(128000);
		try {
			reader = new BufferedReader(new InputStreamReader(new URL("http://interact.bplaced.net/client/settings/configs.txt").openStream(), "UTF-8"));
			int count;
			char[] data = new char[5000];
			while((count = reader.read(data)) != -1)
				builder.append(data, 0, count);
		} catch(IOException e){
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(reader);
		}
		
		return builder.toString();
	}

}
