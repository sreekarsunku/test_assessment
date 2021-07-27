package com.project.cba.fdp.pub;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ParseTestData {

	public static String readFile(String filePath) {
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");
		try {
			List<String> allLines = Files.readAllLines(Paths.get(filePath));
			for (String line : allLines) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}
	
	public static void parseString(String filePath,String outputFilePath) {
		FileWriter writer = null;
		String data = readFile(filePath);
		System.out.println(data);
		String[] strSplit = data.split("]SZ");

		for (int i = 0; i < strSplit.length; i++) {
			if (!strSplit[i].contains("[record") || !strSplit[i].contains("SZ[record")) {
				try {
				File outFile = new File(outputFilePath + i + ".csv");
				writer = new FileWriter(outFile);
				data = strSplit[i].trim();
				System.out.println(data.replace("[record", "").replaceAll("[\\[\\]\"]", ""));
				
					writer.write(data.replace("[record", "").replaceAll("[\\[\\]\"]", "").trim());
					writer.flush();
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
	
	public static void main(String[] args) {
		parseString("src/test/java/com/project/cba/fdp/pub/q1test_data.txt", "src/test/java/com/project/cba/fdp/pub/q1test_data_");
	}
	}

