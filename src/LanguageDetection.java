import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
public class LanguageDetection {
	
	
	// connect your wifi properly to start application
	String Detect_Language(String Text) 
	{
		 	String profileDirectory = "C:\\Users\\Ahsaa\\eclipse-workspace\\AP Project\\profiles";
			try {
				
				DetectorFactory.loadProfile(profileDirectory);			 					
				Detector detector = DetectorFactory.create();
				detector.append(Text);
				
	//			System.out.print(detector.detect());
				
				return detector.detect();
			}
			catch (LangDetectException e) 
			{
				System.out.println("Path Not Valid / Language Not Detected");
				return "";
			}
	}
	
	public static void main(String[] args) 
	{
		String Text ="how are you";		
		LanguageDetection LD = new LanguageDetection();
		String Lang = LD.Detect_Language(Text);
		
		System.out.println(Lang);	
	}
}
