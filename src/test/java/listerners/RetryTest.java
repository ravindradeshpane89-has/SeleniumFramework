package listerners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTest implements IRetryAnalyzer{
	int count =0;
    int maxRetry =1;
	@Override
	public boolean retry(ITestResult result) {
         
         if(count<maxRetry) {
        	 count++;
        	  result.setWasRetried(true);
        	 return true;
         }
		return false;
	}

}
