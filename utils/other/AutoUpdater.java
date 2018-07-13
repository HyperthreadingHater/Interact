package us.interact.utils.other;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;

import net.minecraft.client.Minecraft;
import us.interact.Interact;

public class AutoUpdater {
	
	public void download() {
		ReadableByteChannel rbc = null;
		try {
			rbc = Channels.newChannel(new URL("http://interact.bplaced.net/client/Interact_.jar").openStream());
			@SuppressWarnings("resource")
			FileOutputStream fos = new FileOutputStream("versions/Interact/Interact.jar");
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(rbc);
		}
	}
	
	public boolean checkForUpdate() {
		if(!Interact.eclipse) {
			BufferedReader reader = null;
			StringBuilder builder = new StringBuilder(128000);
			try {
				reader = new BufferedReader(new InputStreamReader(new URL("http://interact.bplaced.net/client/version.txt").openStream(), "UTF-8"));
				int count;
				char[] data = new char[5000];
				while((count = reader.read(data)) != -1)
					builder.append(data, 0, count);
			} catch(IOException e){
				e.printStackTrace();
			} finally {
				IOUtils.closeQuietly(reader);
			}
			
			return !Interact.version.equalsIgnoreCase(builder.toString());
		}
		return false;
	}

}
