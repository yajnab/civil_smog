/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fog_civil;

/**
 *
 * @author yajnavalkya
 */
/*
 * Collection of the Data of different Air pollutants for a research work.
 * Team Members -
 * 1. Dr. Indranil Mukherjee, HOD & Associate Professor,Department of Civil Engineering, Techno India College of Technology
 *2. Yajnavalkya Bandyopadhyay, 2nd Year Student,Dept of Civil Engineering, Techno India College of Technology
 *3. Sk. Mamun Arfin, 4th Year, Department of Computer Science , Techno India College of Technology
**/
/**
 *
 * @author Sk. Mamun Arfin(Mamun67)
 * @author Yajavalkya Bandyopadhyay(yajnab)
 */
 
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
 
public class Fog_civil {
 
    private static final String USER_AGENT = "Mozilla/5.0";
 
   private static final String POST_URL = "http://emis.wbpcb.gov.in/airquality/showaqdatanxt.do";
   //dist_code is for city check the source code for by inspect element for dist_code 
   //dt_part for date
   //mon_part for month
   //yr_part for year
    
    
 
    @SuppressWarnings("empty-statement")
     private static void sendPost(String param,String dt) throws IOException {
        URL obj = new URL(POST_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
 
        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(param.getBytes());
        os.flush();
        os.close();
        // For POST only - END
        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);
        
        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
 
            // print result
            System.out.println(response.toString());
            FileWriter f = new FileWriter((dt+".html"),true);
            f.write(response.toString());
            f.flush();
            f.close();
        } else {
            System.out.println("POST request not worked");
        }
    }
    public static void main(String[] args) throws IOException {
 
         //sendPOST();
        //private static final String POST_PARAMS = "dist_code=013&dt_part=1&mon_part=1&yr_part=2005";

         Integer[] numbers = {1, 3, 5, 7, 8, 10, 12};
        
        //for(int year=2005;year<=2005;year++){
            for (int month=1;month<=1;month++){
                Integer mchk = new Integer(month);
                boolean b = Arrays.asList(numbers).contains(mchk);
                if(b){for(int dt=01;dt<=31;dt++){sendPost("dist_code=013&dt_part="+dt+"&mon_part="+month+"&yr_part=2005",dt+"-"+month+"-2005");}}
                else if(month==2){for(int dt=01;dt<=28;dt++){sendPost("dist_code=013&dt_part="+dt+"&mon_part="+month+"&yr_part=2005",dt+"-"+month+"-2005");}}
                else{for(int dt=01;dt<=28;dt++){sendPost("dist_code=013&dt_part="+dt+"&mon_part="+month+"&yr_part=2005",dt+"-"+month+"-2005");}}
            }
        //}
        System.out.println("POST DONE");
    }
}

