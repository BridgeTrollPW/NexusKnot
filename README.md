wip
# NexusKnot
Nexusknot is a full featured Heartbeat Service. In it's Core, it consists of three kernel entities, a server running as service, a WebAPI named Polymer and a Database.

## Nexusknot Server
The server will periodically request a pinglist from the API. The API response with Pingobjects. Each Pingobject holds data about the target. A target can be any sort of service that can be served via common communication protocols like Hyper Text Markup Protocol or WebSocket. It will also support std output from common cli applications which the underlying operating system can server, like PHP files executet using the php interpreter or JAR files using the JVM.
The Pingobject will also hold informations about when the target service should be requested. Optionally, the Pingobject can hold multiple ExpectedResponse objects. They tell the server how to react on responses from the target. For instance, what to do when the target is a server and it response to a http request with a 304 code. The ExpectedResponse object can react in quite sepcific ways, like calling another webservice or sending an e-mail

## Polymer API
The Polymer API will handle all the data in the database. This will for the most part be the Pingobjects. It will be able to perform selektive and administrative tasks. For instance, preparing all Pingrequests for the Server when it requests the next targets for heartbeats.

## Optional Client Interface
As planned, the NexusKnot Server should be able to take client socket connections via an authorisation based system. Depending on their authorisation, they will be able to get real time updates on how the heartbeats are sent. That means from the point of request till the response. Timings, ips, server inforamtions. In the future, this can be used to create frontends that can display this data.
