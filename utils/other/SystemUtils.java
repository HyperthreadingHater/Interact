package us.interact.utils.other;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class SystemUtils {

	public static String getClipboard() {
		Clipboard systemClipboard;
		systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable transferData = systemClipboard.getContents(null);
		for (DataFlavor dataFlavor : transferData.getTransferDataFlavors()) {
			Object content;
			try {
				content = transferData.getTransferData(dataFlavor);
				if (content instanceof String) {
					return (String) content;
				}
			} catch (UnsupportedFlavorException | IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

}
