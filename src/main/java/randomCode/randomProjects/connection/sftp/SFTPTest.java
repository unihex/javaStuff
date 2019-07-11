package randomCode.randomProjects.connection.sftp;

import java.io.InputStream;
import java.util.Base64;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.HostKey;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SFTPTest {
	
	private static JSch jsch;
	private static Session jschSession;
	private static Channel jschChannel;
	
	public static void main(String[] args) throws Exception {
		jschSession = accquireJschSession();
		login();
		
		StringBuilder expectedText = new StringBuilder();
		expectedText.append("Welcome, ");
		expectedText.append("you are connected using an FTP account used for testing purposes by Rebex FTP for .NET and Rebex FTP/SSL for .NET sample code. ");
		expectedText.append("Only read access is allowed and download speed is limited to 16KBps. ");
		expectedText.append("For infomation about Rebex FTP, Rebex FTP/SSL and other Rebex .NET components, please visit our website at http://www.rebex.net/ ");
		expectedText.append("For feedback and support, contact support@rebex.cz ");
		expectedText.append("Thanks!");
		
		String strActualText = StringUtils.normalizeSpace(getReadmeText());
		String strExpectedText = expectedText.toString();
		
		if (strExpectedText.equals(strActualText)) {
			System.out.println("SFTP test sucessful");
			
		} else {
			System.out.println("SFTP test failed");
		}
	}
	
	public static Session accquireJschSession() throws Exception {
		String hostName = "test.rebex.net";
		String username = "demo";
		
		jsch = new JSch();
		
		//Programmatically set the host key otherwise an UnknownHostKey exception
		//Another option would be adding a key to known_hosts file
		//Another option is jschSession.setConfig("StrictHostKeyChecking", "no"); but this very very insecure
		StringBuilder strHostKey = new StringBuilder();
		strHostKey.append("AAAAB3NzaC1yc2EAAAABJQAAAQEAkRM6RxDdi3uAGogR3nsQMpmt43X4WnwgMzs8");
		strHostKey.append("VkwUCqikewxqk4U7EyUSOUeT3CoUNOtywrkNbH83e6/yQgzc3M8i/eDzYtXaNGcK");
		strHostKey.append("yLfy3Ci6XOwiLLOx1z2AGvvTXln1RXtve+Tn1RTr1BhXVh2cUYbiuVtTWqbEgErT");
		strHostKey.append("20n4GWD4wv7FhkDbLXNi8DX07F9v7+jH67i0kyGm+E3rE+SaCMRo3zXE6VO+ijcm");
		strHostKey.append("9HdVxfltQwOYLfuPXM2t5aUSfa96KJcA0I4RCMzA/8Dl9hXGfbWdbD2hK1ZQ1pLv");
		strHostKey.append("vpNPPyKKjPZcMpOznprbg+jIlsZMWIHt7mq2OJXSdruhRrGzZw==");
		
		byte[] byteHostKey = Base64.getDecoder().decode(strHostKey.toString());
		HostKey hostKey = new HostKey(hostName, byteHostKey);
		jsch.getHostKeyRepository().add(hostKey, null);
		
		return jsch.getSession(username, hostName);
	}
	
	public static void login() throws Exception {
		String password = "password";
		
		jschSession.setPassword(password);
		jschSession.connect();
	}
	
	public static String getReadmeText() {		
		String readmeText = "";
		
		try {
			jschChannel = jschSession.openChannel("sftp");
			jschChannel.connect();
			
			ChannelSftp sftpChannel = (ChannelSftp) jschChannel;
			sftpChannel.cd("/pub/example/");
			
			InputStream inputStream = sftpChannel.get("readme.txt");
			readmeText = IOUtils.toString(inputStream, "UTF-8");
			
			
		} catch (Exception ex) {
			System.out.println("Retrieval of readme text has failed!");
			ex.printStackTrace();
			
		} finally {
			jschSession.disconnect();
			jschChannel.disconnect();
		}
		
		return readmeText;
	}

}
