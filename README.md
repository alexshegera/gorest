# QA-Auto-testing

byte[] secretKey = "0mng65v8jf4lxx91 .... ".getBytes(); // 24 bytes
		SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "TripleDES");
		byte[] iv = "6jk5..".getBytes(); // 8 bytes
		IvParameterSpec ivSpec = new IvParameterSpec(iv);

		String secretMessage = "Bearer 538325ae525d5e775e5e6e08936799694dae7ef3764 ...."; // Your Bearer token for decrypt 
		Cipher encryptCipher = Cipher.getInstance("TripleDES/CBC/PKCS5Padding");
		encryptCipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);
		byte[] secretMessagesBytes = secretMessage.getBytes();
		byte[] encryptedMessageBytes = encryptCipher.doFinal(secretMessagesBytes);
		String encodedMessage = Base64.getEncoder().encodeToString(encryptedMessageBytes);

		System.out.println(encodedMessage);