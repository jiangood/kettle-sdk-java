package io.github.jiangood.kettle.sdk;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPInputStream;

public class HttpUtil {

  public static final int ZIP_BUFFER_SIZE = 8192;



  public static String decodeBase64ZippedString( String loggingString64 ) throws IOException {
    if ( loggingString64 == null || loggingString64.isEmpty() ) {
      return "";
    }
    StringWriter writer = new StringWriter();

    // base 64 decode
    // 为了减少依赖，不映入commons-code包，但有换行符，需要下面这种解码方式
    byte[] bytes64 = Base64.getMimeDecoder().decode(loggingString64.getBytes(StandardCharsets.UTF_8));

    // unzip to string encoding-wise
    ByteArrayInputStream zip = new ByteArrayInputStream( bytes64 );

    // PDI-4325 originally used xml encoding in servlet
    try ( GZIPInputStream unzip = new GZIPInputStream( zip, HttpUtil.ZIP_BUFFER_SIZE );
           BufferedInputStream in = new BufferedInputStream( unzip, HttpUtil.ZIP_BUFFER_SIZE );
           InputStreamReader reader = new InputStreamReader( in, "UTF-8" ) ) {

      // use same buffer size
      char[] buff = new char[ HttpUtil.ZIP_BUFFER_SIZE ];
      for ( int length; ( length = reader.read( buff ) ) > 0; ) {
        writer.write( buff, 0, length );
      }
    }
    return writer.toString();
  }



  public static void main(String[] args) throws IOException {
    String str = "H4sIAAAAAAAA/zMyMDLRN7DQNzBTMDSwMrW0AjJ0FbLyk4yA1NM9DU+Xdz/rXP5iYc+T3bufdi3k\\n5TLC0GCOruHlwp3RyUWpiSWp8WmZOamxuDQhqQHyXIOC/IMUNMpSi4oz8/MULPVM9Az0DHSNTYx1\\nFJJKM3NSFIBchbSi/FwFoHFGuoaGugYWCgbmeqYGekbmCkmVEFXppZWaClYKbiBDo12sYkpyC2JK\\nUotLDPVy8tNjFVIrMotLinUU0hIzczLz0vUIeWhdz7OOCU/2znmyYxa6txQ0nu+e/GzeHNvotMSc\\n4tRYTQJmQUIQEpogc9dP5eUCAAnbkXN/AQAA\\n";

    String s = HttpUtil.decodeBase64ZippedString(str);
    System.out.println(s);
  }
}
