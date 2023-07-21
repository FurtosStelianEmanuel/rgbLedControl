String inputString = "";      // a String to hold incoming data
bool stringComplete = false;  // whether the string is complete

void setup() {
  // initialize serial:
  Serial.begin(9600);
  // reserve 200 bytes for the inputString:
  inputString.reserve(200);
  pinMode(LED_BUILTIN, OUTPUT);
  
}

void loop() {
  // print the string when a newline arrives:
  if (stringComplete) {
    //Serial.println(inputString);
    if (inputString=="ceva"){
      digitalWrite(LED_BUILTIN, !digitalRead(LED_BUILTIN));  // turn the LED on (HIGH is the voltage level)
    }
    // clear the string:
    inputString = "";
    stringComplete = false;
  }

  //Serial.println("ceau123456");
}

/*
  SerialEvent occurs whenever a new data comes in the hardware serial RX. This
  routine is run between each time loop() runs, so using delay inside loop can
  delay response. Multiple bytes of data may be available.
*/
void serialEvent() {
  while (Serial.available()) {
    // get the new byte:
    char inChar = (char)Serial.read();
    // add it to the inputString:
    if ((int)inChar != 10){
      inputString += inChar;
    }
    
    // if the incoming character is a newline, set a flag so the main loop can
    // do something about it:
    if (inChar == '\n') {
      stringComplete = true;
    }
  }
}
