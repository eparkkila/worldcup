This is a simple implementation of the Sportradar exercise.
The progrm uses homeTeam and visitorTeam as keys when the supported actions are called.
  - Start a new match
  - Update score
  - Finish
  - Get a summary of mathches in progres
The goal is that everything else but the get a summay method would hav O(log n). With summary method a simple caching could have helped a lot but decided leave it out because the expected data is relatively small
Big O of the get summary is around O(n)

The implementation started from obvious unit tests (shown in the code) and while I impoved the test I simultanuously coded the necessary part of library.
