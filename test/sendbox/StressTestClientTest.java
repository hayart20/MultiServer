package sendbox;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class StressTestClientTest {

	@Test
	public void testSend() {
		StressTestClient stressTestClient = new StressTestClient();
		Boolean stressTestResult1 = stressTestClient.send("http://localhost:8123/api-1?file=c:\\gitProjects\\MultiServer\\tmp_folder\\Meﬂaufgabe.docx", 10);
		assertFalse(!stressTestResult1);
		
		Boolean stressTestResult2 = stressTestClient.send("http://localhost:8123/api-2?file=c:\\gitProjects\\MultiServer\\tmp_folder\\Meﬂaufgabe.docx", 10);
		assertFalse(!stressTestResult2);
		
		Boolean stressTestResult3 = stressTestClient.send("http://localhost:8123/api-3?file=c:\\gitProjects\\MultiServer\\tmp_folder\\Meﬂaufgabe.docx", 10);
		assertFalse(!stressTestResult3);
	}

}
