package MailService;

import MailService.BulkMailService;

public class Test {
	public static void main(String[] args) {
		String[] bulkMail = { "mosharraf.talukder@bhbfc.gov.bd", "talukder.csedu@gmail.com" };
		try {
			System.out.println(BulkMailService.MailService("Test Header", "Test Body", "ict.bhbfc@gmail.com","apbhbfc123", bulkMail));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
