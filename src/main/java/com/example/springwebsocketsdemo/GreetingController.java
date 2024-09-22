package com.example.springwebsocketsdemo;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello , "+ HtmlUtils.htmlEscape(message.getName()) + "!");
    }
    // The @MessageMapping annotation ensures that, if a message is sent to the /hello destination, the greeting() method is called.
    //
    // The payload of the message is bound to a HelloMessage object, which is passed into greeting().
    //
    // Internally, the implementation of the method simulates a processing delay by causing the thread to sleep for one second. This is to demonstrate that, after the client sends a message, the server can take as long as it needs to asynchronously process the message. The client can continue with whatever work it needs to do without waiting for the response.
    //
    // After the one-second delay, the greeting() method creates a Greeting object and returns it. The return value is broadcast to all subscribers of /topic/greetings, as specified in the @SendTo annotation. Note that the name from the input message is sanitized, since, in this case, it will be echoed back and re-rendered in the browser DOM on the client side.
}
