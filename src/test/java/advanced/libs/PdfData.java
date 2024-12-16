package advanced.libs;

import com.framework.utils.ReadPdf;

public class PdfData {

	public static void main(String[] args) {
		System.out.println(ReadPdf.getText(System.getProperty("user.dir")+"\\Files\\Data.pdf"));
	}

}
