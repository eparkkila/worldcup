This is a simple implementation of the Sportradar library.
The library uses WorldCupMatch data object for presenting matches.
The supported operations are:
  - Start a new match
  - Update score
  - Finish match
  - Get a summary of mathches in progres

The implementation started from obvious unit tests (shown in the code) and while I added test I simultanuously coded the necessary part of the library.
Library is build using Maven:
mvn clean install
mvn test // to run the existing test cases

I wanted to impvove the performance (Big O) of the operations but ran out of time. On the other hand this kind library if used only for World Cup will perform just fine with the current implementation.
