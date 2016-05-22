#include <SoftwareSerial.h>

int bluetoothTx = 2; // TX-O pin of bluetooth mate, Arduino D2
int bluetoothRx = 3; // RX-I pin of bluetooth mate, Arduino D3

int led8 = 8, led9 = 9, led10 = 10, led11 = 11, led12 = 12, led13 = 13;

int button1State = 0;
int button2State = 0;

int dataFromBt;

SoftwareSerial bluetooth(bluetoothTx, bluetoothRx);

void setup()
{
  Serial.begin(9600); // Begin the serial monitor at 9600bps

  bluetooth.begin(115200); // The Bluetooth Mate defaults to 115200bps
  bluetooth.print("$"); // Print three times individually
  bluetooth.print("$");
  bluetooth.print("$"); // Enter command mode
  delay(100); // Short delay, wait for the Mate to send back CMD
  bluetooth.println("U,9600,N"); // Temporarily Change the baudrate to 9600, no parity
  // 115200 can be too fast at times for NewSoftSerial to relay the data reliably
  bluetooth.begin(9600); // Start bluetooth serial at 9600
  pinMode(led8, OUTPUT);
  pinMode(led9, OUTPUT);
  pinMode(led10, OUTPUT);
  pinMode(led11, OUTPUT);  
  pinMode(led12, OUTPUT);  
  pinMode(led13, OUTPUT);  
}

void loop()
{

  if (bluetooth.available()) // If the bluetooth sent any characters
  {
    // Send any characters the bluetooth prints to the serial monitor
    //Serial.println((char)bluetooth.read());

    dataFromBt = bluetooth.read();

    if (dataFromBt == 'a') {
      Serial.println("led8 on");
      digitalWrite(led8, HIGH);
      bluetooth.print("81");
    }
    if (dataFromBt == 'b') {
      Serial.println("led off");
      digitalWrite(led8, LOW);
      bluetooth.print("80");
    }
    if (dataFromBt == 'c') {
      Serial.println("led9 on");
      digitalWrite(led9, HIGH);
      bluetooth.print("91");
    }
    if (dataFromBt == 'd') {
      Serial.println("led off");
      digitalWrite(led9, LOW);
      bluetooth.print("90");
    }
    if (dataFromBt == 'e') {
      Serial.println("led10 on");
      digitalWrite(led10, HIGH);
      bluetooth.print("101");
    }
    if (dataFromBt == 'f') {
      Serial.println("led off");
      digitalWrite(led10, LOW);
      bluetooth.print("100");
    }
    if (dataFromBt == 'g') {
      Serial.println("led11 on");
      digitalWrite(led11, HIGH);
      bluetooth.print("111");
    }
    if (dataFromBt == 'h') {
      Serial.println("led10 off");
      digitalWrite(led11, LOW);
      bluetooth.print("110");
    }
    if (dataFromBt == 'i') {
      Serial.println("led12 on");
      digitalWrite(led12, HIGH);
      bluetooth.print("121");
    }
    if (dataFromBt == 'j') {
      Serial.println("led12 off");
      digitalWrite(led12, LOW);
      bluetooth.print("120");
    }
    if (dataFromBt == 'k') {
      Serial.println("led13 on");
      digitalWrite(led13, HIGH);
      bluetooth.print("131");
    }
    if (dataFromBt == 'l') {
      Serial.println("led13 off");
      digitalWrite(led13, LOW);
      bluetooth.print("130");
    }    
  }

  if (Serial.available()) // If stuff was typed in the serial monitor
  {
    // Send any characters the Serial monitor prints to the bluetooth
    bluetooth.print((char)Serial.read());
  }
}
