Restrictions:
- select only 1 problem
- no external libs for application (only build and test tools)
- no executable files in solution

Outputs:
- brief design explanation
- assumptions
- code
- run instructions
- single zip file

Measurements:
- design of solution
- OO skills
- Production quality: run, maintain & evolve 

Problem requirements:
- supply application with input data via text file
- application must run
- evidence that it works - minimum is indication that it works with supplied test data

Train problem:
Configuration: towns/tracks
Input:	Route specification
	-> start, end
	-> route filters (acts on specific potential route)
		- exact path
		- maximum number of stops
		- exact number of stops
	-> route selectors (selects one route from list of available routes)
		- shortest distance
		- note: this is filter that needs context of all available routes
Output:	Information about routes
- distance for EXACT route
	- if NOT found, output 'NO SUCH ROUTE'
	- exact matches to route spec ONLY
	- note: also distance of any route for route selection based on shortest distance
- number of routes between start and end town
- shortest route between start and end town
# Assume same NOT found message for other options as well

Configuration validation:
- track between start and end town will appear only once
- track won't have same start and end town

