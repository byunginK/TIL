# File Class
```java
package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import dto.Batter;
import dto.Human;
import dto.Pitcher;

public class FileProcess {

	String addres;
	File f1;

	public FileProcess() {
		f1 = new File("d:\\temp\\memberlist.txt");

	}

	public void saveFile(String human[]) {
		// this.addres = addres;
		int count = 0;
		for (int i = 0; i < human.length; i++) {
			if (human[i] != null) {
				count++;
			}
		}
		String data[] = new String[count];

		for (int i = 0; i < data.length; i++) {

			if (human[i] != null) {

				data[i] = human[i];
			}
		}

		try {
			FileWriter fw = new FileWriter(f1);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);

			for (int j = 0; j < data.length; j++) {
				pw.println(data[j]);
			}
			pw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("저장 완료");
	}

//	public String[] loadFile() {
////		return addres;
//	}
}
```
