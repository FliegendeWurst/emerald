package ru.henridellal.emerald;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class ShortcutData extends BaseData {
	
	public static String SHORTCUT_NAME = "S";
	public static String SHORTCUT_URI = "U";
	public static String SHORTCUT_COMPONENT = "P";
	
	private String uri;
	@Override
	public String getId() {
		return uri;
	}
	public String getUri() {
		return uri;
	}
	public ShortcutData() {
		super();
	}
	public ShortcutData(String name, String uri, String packageName, String resourceName) {
		this(name, uri);
		this.component = packageName +"/"+resourceName;
	}
	public ShortcutData(String name, String uri) {
		super(null, name);
		this.uri = uri;
	}
	
	@Override
	public boolean equals(Object shortcut) {
		if (! (shortcut instanceof ShortcutData))
			return false;
		if (uri == null) {
			return shortcut == null || ((ShortcutData)shortcut).getUri() == null;
		}
		return uri.equals( ((ShortcutData)shortcut).getUri() );
	}
	
	public void read(BufferedReader reader, String firstLineOfData){
		try {
			this.name = firstLineOfData.substring(1).trim();
			this.uri = readLine(reader, SHORTCUT_URI).substring(1).trim();
			String componentLine = readLine(reader, SHORTCUT_COMPONENT).substring(1).trim();
			this.component = (componentLine.length() == 0) ? null : componentLine;
		} catch (IOException e) {
		
		}
	}
	
	public void write(BufferedWriter writer) throws IOException {
		writer.write(new StringBuilder(SHORTCUT_NAME)
		.append(this.name)
		.append("\n")
		.append(SHORTCUT_URI)
		.append(this.uri)
		.append("\n")
		.append(SHORTCUT_COMPONENT)
		.append(this.component)
		.append("\n").toString());
	}
	@Override
	public int hashCode() {
		return getUri().hashCode();
	}
}
