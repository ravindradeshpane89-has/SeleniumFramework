package listerners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTest implements IRetryAnalyzer{
	int count =0;
    int maxRetry =1;
    private static final Logger logger = LogManager.getLogger(RetryTest.class);
	@Override
	public boolean retry(ITestResult result) {
         
         if(count<maxRetry) {
        	 count++;
        	  result.setWasRetried(true);
        	  logger.info("Test "+result.getMethod().getMethodName()+ " retrying "+count+" time");
        	 return true;
         }
		return false;
	}

}
