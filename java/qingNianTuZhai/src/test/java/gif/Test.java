package gif;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Test {

	static String path = "C:/Users/QDHL/Desktop/gifsicle-1.89-win64/gifsicle-1.89-win64/";
	
	
	public static void main(String[] args) throws Exception {
		
		
		Process exec = Runtime.getRuntime().exec(path + "gifsicle.exe C:/Users/QDHL/Desktop/gifsicle-1.89-win64/gifsicle-1.89-win64/55.gif --info ");
		InputStream inputStream = exec.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String line = "";
		System.out.println(1);
		while((line = reader.readLine())!=null) {
			System.out.println(line);
		}
		exec.waitFor();
		inputStream.close();
		reader.close();
		exec.destroy();
	}

}
