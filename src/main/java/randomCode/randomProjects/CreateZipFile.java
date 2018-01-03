package randomCode.randomProjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class CreateZipFile {

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		
		System.out.println("Please enter a comma separated list of file paths.");
		String[] inputFilePaths = StringUtils.split(kb.nextLine(), ',');
		
		System.out.println("\nPlease enter where you would like to save the zip file.");
		String outputFilePath = kb.nextLine();
		
		System.out.println("\nWhat would you like the zip file to be called?");
		String zipFileName = kb.nextLine();
		kb.close();
		
		
		String fullOutputPath = outputFilePath + "\\" + zipFileName + ".zip";
		
		zipFiles(inputFilePaths, fullOutputPath);
		
		System.out.println("\nProcess Complete");

	}

	private static void zipFiles(String[] inputFilePaths, String fullOutputPath) {
		FileOutputStream fout = null;
		ZipOutputStream zout = null;
		
		try {
			fout = new FileOutputStream(fullOutputPath);
			zout = new ZipOutputStream(fout);
			
			for (String filePath: inputFilePaths) {
				File file = new File(filePath);
				FileInputStream finput = new FileInputStream(file);
				
				ZipEntry zipEntry = new ZipEntry(file.getName());
			    zout.putNextEntry(zipEntry);
			    
			    IOUtils.copy(finput, zout);
			    
			    zout.closeEntry();
			    IOUtils.closeQuietly(finput);
			    
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			IOUtils.closeQuietly(zout);
			IOUtils.closeQuietly(fout);
			
		}
		
	}

}
