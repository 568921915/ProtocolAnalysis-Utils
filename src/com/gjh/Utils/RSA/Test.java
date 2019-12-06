package com.gjh.Utils.RSA;

import com.gjh.Utils.base64.Base64Utils;

public class Test {

	public static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCcBzNRRl35DRbolU8CXGm7tNijtnUTAxB28ie/NCaUsPr66eiT10nV3fzYjek8sQn3JbpRWMX/nlI+yI0fPSKPQmzZmsp1MlnwJDeyPzr+pTUETdIOTpxxIdjbeUhVbh50q8ph/kG1uQApG5P/0q+OaC+aciC7SyUviGsNvTfvKQIDAQAB";
	public static final String privateKey= "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJwHM1FGXfkNFuiVTwJcabu02KO2dRMDEHbyJ780JpSw+vrp6JPXSdXd/NiN6TyxCfclulFYxf+eUj7IjR89Io9CbNmaynUyWfAkN7I/Ov6lNQRN0g5OnHEh2Nt5SFVuHnSrymH+QbW5ACkbk//Sr45oL5pyILtLJS+Iaw29N+8pAgMBAAECgYEAhYXT8LWbLcp51EgR+R9EjJHS4yb9QWvZ5zCLuyDR82URIzCRDBOs6Ay0mE25T2rh8FKlA4BI0NuX3oqZ2iXjMAe42jhIVLj5qEI7xl0PGUnaHpi3mS3Cr4445eqzymt39GHNz/BVqjqR5noMMAQetKc/0uc3YFz1LhO1c/eM+FECQQDK9tIozBbXhrLbGryYpmu/GSeLhiwI5yQlRvYGOw1zHjtY+cpgYkHes/gyv2QvWsSv57Uco0fk9sZdSx8rluJ7AkEAxMyelSEXYPL3fAkS0t5O+TpetXNnjUgOHnk6iPjrAzAia2YVXsh0ICKYA7Z9lVnlkrimQXyc4Wr/ELUDO5LFqwJBAKN5ndhCWfuKgLC9nj4NfSdUaWeV0A3zrkdgGnT2R2T7aq3Fs91iBtALgNPPGDCUGWvdYyEH2rZulWM7SIXhZ9UCQCWElN79VgWnjELbP/lvZELjO9b/z0gB6p8ooV4nhjYvYQB0j7JoKKtPrQdYLQzh/IBCL3/gj8/xo/q9Z4Zd7s0CQAMPp+//yb/ZkL2ksIIj5MtJHoYDcXZAe6aZvXNnkCxUwPwqdSocU9Y8Xkf+vUH6fl0tUWBnlrdb3x0tHLX0GKM=";
	
	public static void main1(String[] args) {
		String input = "hello";
		byte[] enc = RsaUtils.encrypt(input.getBytes(), publicKey);
		System.out.println("加密后：" + Base64Utils.encodeData(enc));
		
		byte[] dec = RsaUtils.decrypt(enc, privateKey);
		System.out.println("解密后： " + new String(dec));
	}	
	
	public static void main(String[] args) {
		String a = "eH3ZrEqqC4pJyDlpB9CKUHLRT5z6foFs6s7FCaKoR9JBr2E/+MPja72/J7U71SV5ajiQcrvi9umo9EaIMjiqlaujsYAO6ySLleIUHQukRKvGXfhN7isLRREBiyBXBkleKfMxWjro191Wq3yjbPikvgcL+hd1Arq+Nqk/moxhoAQ=";
		
		
		
	}
}
