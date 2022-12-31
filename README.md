# ODE_Projekt

## Message Format
- "EmployeeID", "Name", "Task", "Date-from", "Date-to"
  - **_Example_**: 12331;ahmad Schurbaji;update workflow;11-04-2022 22:01:33:023;15-04-2022 05:07:20:02
- Client has to send this format in one Line

## Notes
- in `src/main/java/org/fhtw/client/` there is a client program which has been used for testing the server.
- the server has to be up running, when the client trys to connect

## New
* only specific ids are supported, here is the list of the following IDs that are supported.
  * "0000"
  * "0001"
  * "0002"
  * "0003"
  * "0004"
  * "0005"
  * "0006"
  * "0007"
  * "0008"
  * "0009"
  * "0010"
  * "0011"
  * "0012"
  * "0013"
  * "0014"
* Server sends now error to the Client, it`s on the client side to handle these errors.
  * Notes: in order to get the error, the client has to open the Server Output stream, so it can read it.
* An example of how a valid message should like:
  * 0000;Momo Schurbaji;Create workflow;11-04-2015 22:01:33:023;11-04-2015 09:07:20:02
  * Note that the date has to be a time stamp: `dd-MM-yyyy HH:mm:ss:ms`