# Sockets/Custom Protocols (TCP)

## Description
This program will start a server and client image guessing game - The server will send images/prompts to the client and the client will respond with user input.  All "computataions" will be done on the server side and then sent to the client.  The client will display messages from the server. 

[Screencast](https://youtu.be/U7vyDRurjGA)


## To start the TCP server/clients, run the following:

`gradle TCPServer`

`gradle TCPClient`

## To start the UDP server/clients, run the following:

`gradle UDPServer`

`gradle UDPClient`

**Differences between UDP and TCP:**
* UDP uses a DatagramSocket whereas TCP uses a Socket.  A datagram socket provides a connection-less point for sending/receiving packets
  * Because datagram sockets are connectionless, it does not need to open Input/Output streams
* The NetworkUtils for UDP is more involved as the connection information needs to be written and sent/received with every packet since there is no open input/output stream (Stateless connection)
  * The TCP NetworkUtils is shorter and easy to read as the connection information remains open and only the payloads are sent while the connection is open


## Simple Protocol

Client will start by asking user for their name, and then following up with asking how many rounds (images) the user wants to attempt.  Afterwards, the user can enter `start` to start game.  The client display will depend on the order of the responses

```
{
  "userResp": <String: "name", "rounds", "start", "more", "next", "quit">
}
```

After the game is started, users can enter the following options during:

`more`- to increase image size (up to 2x) for a hint

`next` - to skip to next image 

`quit` - to quit 

Server will read in clients responses and check for certain "keys" in the responses.  If any of the keys match the appropiate options listed, then the server will send an appropiate response, otherwise it will send an error message.  Receiving a "start" from the client will start the timer on the server's end.  Once the timer elapses, the server will respond to the next input from the client with a loss response.

```
{
   "order": <int: 1, 2, 3, 4, 5, 7>, 
   "type": <"greeting", "image", "start-prompt", "goodbye-prompt", "wrong-prompt">,
   "data": <thing to return> 
}
```

