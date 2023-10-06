# sportradar-scoreboard


## Simplifications:

because "make simple as possible" requirement:
- externalId, should be used when passing data outside of library. Currently is passed real id of entity.
- System.out used as way of logging
- Id provider service that provides ids for entities is not implemented
- Concurrency handling skipped. It is just marked as usage of ConcurrentHashMap for storage. 
- More sophisticated way of handling exceptions and exception hierarchy skipped.
- Usage of NullPointerException for undefined data 
- advanced compare of matches skipped for edge case when date and score are same
- security (XSS etc) skipped
- "This removes a match from the scoreboard."--> my understanding is remove it from store. It should not happen 
  in 'real' lib but this is just interview task.
- homeTeamScore / awayTeamScore-- should be value object but is number.  In real life there is a lot of logic connected with it 
  (scores in match time, penalties, score, is this score for away or home team etc.)


## Assumptions
- max goals: 1000 , this means that one goal per 5,4 second. With all the match breaks its impossible
- max team name length : 1000
- no character restrictions for team name, assuming lib can be used by anyone 




