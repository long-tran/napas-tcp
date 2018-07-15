# NAPAS TCP ISO8583 Messaging

 I create this piece of software to be used to decode/encode Vietnam Card Switching network (NAPAS) messages using [JPOS](https://github.com/jpos/jPOS). The code base is Groovy/JAVA.
 
Once decode/encode the messages, you can process them further with your business logics in your Backend/CoreBanking system, example: when receiving message ```0100```, processing code ```31A000``` (balance inquiry), you can then call an API to return the balance and respond back to NAPAS.
***
# Let's assume the network connection

 The BANK's server (this awesome software) | ----------------------> | ```8001``` NAPAS System running on TCP PORT ```8001```

# What you need to know before reading on

1. Groovy & Gradle: simply the best way to write JAVA/GROOVY codes, utilizing all the available JAVA libraries on Maven repository.
2. [ISO8583](https://en.wikipedia.org/wiki/ISO_8583): well, this is mainly the message exchanged between the banks and NAPAS.
3. Encoding/Decoding message: each message is presented differently in different code base, for example, there 02 message below are exactly the same, just different encoding:

XML encoding:
```
<field id="0" value="0800"/>
<field id="2" value="9704001234567890"/>
<field id="3" value="31A000"/>
<field id="52" value="4341464542414245" type="binary"/>
```
ASCII encoding:
```
080097040012345678900000004341464542414245
```
4. Server and Client (Channel Adaptor): in this context, I consider NAPAS the "SERVER", and the BANK is the "CLIENT"/"CHANNEL ADAPTOR"
5. [Multiplexing/Mux](https://en.wikipedia.org/wiki/Multiplexing): well, simply put: use the same TCP connection to send multiple messages (requests) and match the responses CORRECTLY to the original requests (usually by Trace Number and Terminal ID (ISO8583 ```field 11, 41```)) 
***
## What I am writing in this software (and what I am NOT)
### YES, I will be writing
1. A Mock/Fake NAPAS server, (see here: https://github.com/long-tran/napas-mock-server) running on your local host port ```8001```, with a default response (```field 39```) as the last 02 digits of the Trace Number (```field 11```). Example: you send a message with Trace Number = 01920**55**, then, the response will be **55**, see what I did there? Cool, huh.
2. An up-to-date decoder/encoder (or a packager) that understands NAPAS (as of writing of this document), located at: https://github.com/long-tran/napas-tcp/blob/master/src/main/q2/packagers/packager.napas.xml
3. A Factory for you to map between the message and your business logic via the (```MtiAndProcessingCodeListener```) using MTI and Processing Code (```field 03```).
### NO, I am NOT writing here in this software
Your **BUSINESS LOGIC or INTERFACE** to your Backend/CoreBanking systems (unless you PAY me a huge load of $$$)

# How do you get started
1. Install [gradle](http://gradle.org)
2. Clone my code here: https://github.com/long-tran/napas-tcp
3. Do a: ```gradle q2``` in my code
4. Play around with the scripts under ```src/main/q2```
5. Enjoy your cup of coffee
***
# Author
It's just me, for now, ping me at **long** at **long-tran** dot **com**.

# Credits
This application uses Open Source components. You can find the source code of their open source projects along with license information below. We acknowledge and are grateful to these developers for their contributions to open source.

Project: jPOS, https://jpos.org, by [Alejandro Revilla](https://github.com/ar)




