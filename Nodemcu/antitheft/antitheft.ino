/////////////////////////////////
// Generated with a lot of love//
// with TUNIOT FOR ESP8266     //
// Website: Easycoding.tn      //
/////////////////////////////////

#include <ESP8266WiFi.h>

#include <ESP8266HTTPClient.h>

const char* ssid = "redmi";

const char* password = "abc12345";

const char* host = "45.55.34.11";


int count = 0;                                          // count = 0
String input="";                                         // character array of size 12 
boolean flag = 0;                                       // flag =0


WiFiClient client;

String thingSpeakAddress= "http://api.thingspeak.com/update?";
String writeAPIKey;
String tsfield1Name;
String request_string;

HTTPClient http;

void connection(){
  Serial.begin(9600);
  Serial.println();

//  Serial.printf("Connecting to %s ", ssid);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED)
  {
    delay(500);
    Serial.print(".");
  }
  Serial.println(" connected");
}


void setup()
{
  connection();
  pinMode(5,OUTPUT);
  pinMode(4,OUTPUT);
}


void loop()
{
    
  WiFiClient client;

//  Serial.printf("Connecting to %s ", ssid);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED)
  {
    delay(500);
    Serial.print(".");
  }
 // Serial.println(" \nconnected");

 // Serial.printf("\n[Connecting to %s ... ", host);
    client.connect(host,80);
    //Serial.println("connected]");
  if(Serial.available())
   {
      count = 0;
  //    Serial.println(WiFi.localIP());
      
           
      while(Serial.available() && count < 12)          // Read 12 characters and store them in input array
      {
         input += (char)Serial.read();
         count++;
         delay(5);
      }
      Serial.println(input);                             // Print RFID tag number 
   // Serial.println("[Sending a request]");
    client.print(String("GET https://45.55.34.11/check.php?p=") + input +" HTTP/1.0\r\n\r\n" + " Host:" + host + "\r\n" +
                 "Connection: close\r\n" +
                 "\r\n"
                );

    input="";
    String line="";
    //Serial.println("[Response:]");
    while (client.connected())
    {
      if (client.available())
      {
         line += client.readStringUntil('\n');
 //       Serial.println(line);
        
      }
    }
   
     String ans=line.substring(line.indexOf('#')+1);
   //  Serial.println(ans);
     
     if(ans=="no"){
         Serial.println("Unissued book.... theft theft");
         digitalWrite(5,HIGH);
         
         delay(1000);
     }
     else{
      digitalWrite(4,HIGH);
      Serial.println("Book is issued"); 
      delay(4000);
      }
            
   }
   digitalWrite(5,LOW);
   digitalWrite(4,LOW);
   client.stop();
  //  Serial.println("\n[Disconnected]");
  delay(500);

  }
   


  
  
  //  Serial.println("\n[Disconnected]");

    
//    if(client.connect("api.thingspeak.com",80)) {
//      writeAPIKey = "key=JYLKR4WVJ5NPEPOC";
//      tsfield1Name = "&field1=10";
//      request_string = thingSpeakAddress;
//      request_string += "key=";
//      request_string += "PD2YYI36VOXZSS8X";
//      request_string += "&";
//      request_string += "field1";
//      request_string += "=";
//      request_string += getRFID();
//      http.begin(request_string);
//      http.GET();
//      http.end();
//
//    }

