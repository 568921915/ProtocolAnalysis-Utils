package com.gjh.Utils.base64;

import java.io.UnsupportedEncodingException;


import com.gjh.Utils.FileUtils;

public class MainBase64 {
	public static void main(String[] args) {
		
		String inputData = "ib8iIBEvuchv6IYYA9smb55QMgQLD8KofPWKpqWM53mHxMjZGvPyBd8QIi3J+raOAeb4V1Xst3su3vLJ78a6L29SgeRfqMdE3xeWmXuxsnycY0Vb2U512CfqwTJcCBcBicsp8ChcDNi0FbyGecw5j37sTQPcocu/o4cpuY4PzOUTtYRVVnC2RPN6Jea3E2VmvVmLz95CAFRKWikXMLpJlxabNPRxntZHZy0SyqSNeuBNu+4Hwk9PMWzIz7upq2ZnwbInWMuO1XBmljE/MABpkpzYQRvfkucFaRFYFt+u8UUoC61T5XCo4o1JlOFd4Yr1SrjMceVcHtLtMyXEhIDB3KIl12wQchprxh2tSEyF72rp/RCNWk+QCN4dI7qGMr37lacY/TOAgU6TzbOtgZAGePZAgeRQNBSETSoglPlupRGk1p1pkTqD41/TulDPJpZh7CDNAty3mMAEFuwO+zx2ogOG4j66PgHieZmaDitM3VF1ro687XSukXcQ1Oz6iXwBi/y66V8hXaEL6Iv4fBWQ4cuOSezAqR0xt0mcdDRDaTPxLO6zTZjf6Ta1nAGUUuvQGNpWxGpTCC19rS8Xm5SO95mP5nKNOSXK7x3XYMS+qeFEyW/cuTjiE3r8+852NlOt+bzzedK8PKEaBesBvPBAf8biDhyuOaoocDMgPO1kwEUTT+jCXFi0kT2PQHl7iHAnIF1pk3F2E+SOwMtmn4RzFPHoKXSL43iFV7luoatpKgbD2VzegZvej/Gbq67ztVhOt4kNWNpVdPUI5kb9joTf2/HlzQvahRpl4g9tJHCBcNKdYo4MJgPVJ8eqhebvPiAwg5+XjmeOmCabFha2LqpDpJaiSWdLyOs44adD0AAM4TOTM5mlZPqM33yZ4GLAuQEQJIAu+TTZEP1sbpEEmuFgdd2A1dmZ1H3XoBlWaBYg9p/ZeytVjDP4k3v9hBZCccjEVmF8wDAHihWWUYiS9zRX8MZj8nTyPPCaQeZxWgrfBb8Y+OD9pmfbMfrYQvekzZQ7P24UydUL/ajjmpLXJD/y4Q==";
		byte[] afterBase64 = Base64Utils.decodeData2Byte(inputData);
		FileUtils.SaveByteToFile("e:/aa.bin", afterBase64);
		System.out.println( new String(afterBase64));
		try {
			System.out.println( new String(afterBase64,"utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//System.out.println(PrintByte.byteArrayToHexStr(afterBase64));
		//System.out.println(Base64Utils.decodeData2Str(inputData));
		
		
		/*
		byte [] input = ReadFileUtils.ReadBinaryFile("C:/Users/GuanJh/Desktop/ingke/a.bin");
		byte[] afterBase64 = Base64Utils.decodeByte2Byte(input);
		System.out.println(PrintByte.byteArrayToHexStr(afterBase64));
		ReadFileUtils.SaveByteToFile("e:/aa.bin", afterBase64);
		*/
		
	}
	
	public static void a(String[] args) {
		
		byte[] b = FileUtils.ReadBinaryFile("e:/base64in.bin");
		byte[] afterBase64 = Base64Utils.decodeByte2Byte(b);
		FileUtils.SaveByteToFile("e:/aa.bin", afterBase64);
	}

}
