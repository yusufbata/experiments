# Overview
This project aims to solve the thoughtworks train problem. The Java programming language (1.7) was used along with JUnit and Fest assertions (for testing) and maven (for building the application).

# Prioritised goals & status
1: Complete all application features (requirement): Completed - proven using acceptance test cases and sample input files.
2: Maintainability: Not perfect due to time constraints. In particular, unit-level tests would result in more robustness. Acceptance tests do however enable safe refactoring while maintaining existing functionality.
3: Performance: Not measured due to time constraints. IF measurements indicate bottlenecks, maintainability will allow changes to be made much more easily. Most likely area of performance change will be Path module (path finder implementation).

# Using the application
## Prerequisites for running application
- Unzip utility (like 7zip) -> to unpackage compressed file.
- Maven (tested on 3.1.1) -> to build executable jar file from source code.
- Java (tested on 1.7.0_51) -> to build and run executable jar file.
- (optional) 2 data files for application input. Sample files provided with source code and can be re-used.

## Building the application
1: Unzip train-problem.zip file
2: Navigate to unzipped directory -> train-problem/
3: Run: mvn clean install

## Running the application
1: Navigate to the target directory after building application (see previous step) -> train-problem/target/
2: Run: java -jar train-problem-0.0.1-SNAPSHOT.jar classes/sample-tracks.txt classes/sample-routespecs.txt
3: You will see the output of the application on the command-line. Note that the sample files used in the previous step have the exact problem input specified in the problem statement.
4: (optional) Modify the sample input files and run again, or use your own input files (then remember to change the file paths above to point to your files).

## File input format
The application uses 2 files:
 1: tracks file - used to define track map (graph structure).
 2: route-specs file - used to define required route specifications (matchers).  

NOTE: Comments (lines starting with '#') and blank lines are allowed and ignored in both the files.

The application output is directed to standard out (command-line by default).

### tracks file format
Expected pattern: {From}-{To}-{DistanceInteger}
Expected pattern example: A-B-4
Items separated using '-'
 
### route-specs file format
Format pattern: {NODE-MATCH STRATEGY} = {NODE-MATCH VALUE} | {PATH-MATCHER TYPE} = {PATH-MATCHER VALUE} | {OPTIMUM-PATH-SELECTOR} | {OUTPUT-MEASURE}

Format example: EXACT_PATH = A-B-C-D | NONE = 3 | NONE | PATH_DISTANCE

Format options: EXACT_PATH/START_AND_END = {A-B-C-D} | MAX_HOPS/EXACT_STOPS/MAX_DISTANCE/NONE = {3} | NONE/SHORTEST_DISTANCE |  PATH_DISTANCE/PATH_COUNT

Element separators '|' . Note that they can't be used for values.
Exactly one item required per element (NONE allowed if element not required).
Exact number of elements (4) required in every line, including NONE if element not required.
Key value separators '='. Values ignored when NONE specified.

# Application Structure
The application has a modular structure following the ports-adapters architecture style:

- adapters -> RouteInputFileAdapter: Used for providing input to the application via files. This is currently the only supported interaction with the application (other than unit tests). More adapters can however easily be added.
- application-services -> RailroadApplicationService: Used for interacting with the application via adapters. Uses the input RouteSpec (route specification) to finds matching paths in the trackmap.
- model -> TrackMaps (Entity) and Path (Value) domain components. Used by the application-services to compose application features.
- infrastructure -> Implementation of application-service dependencies (like Repos).
- runner -> App class used to launch the application via the command-line.

# Application domain components
The problem contains 2 distinct domains: trackmaps and paths.

- trackmaps: a map of the tracks and their locations. These are entities with identity that can potentially be persisted for long term storage in the application. The Route is used by the path component by implementing the Path interface. 
- paths: the logic for finding paths that match the path specifications (built using route specifications). Composed of PathFinder and PathMatchers. Currently one implementation of PathFinder algorithm: ExploratorySingleStepRoutingEngine. Can easily be optimised or replaced due to clean separation and acceptance tests.

# Development process
Followed an outside-in development process. Used acceptance tests to guide implementation and enable refactoring. Sacrificed robustness (via thorough unit-test coverage) for functional completeness. Used general OO design approach. Used a combination of top-down (conceptual) and bottom-up (heuristic) design.

# Possible improvements
- Add more thorough unit test coverage. Including testing of various input options and parsers.
- Structure PathMatchers (and parsers) better to allow path operators (like less than, equal to) to be reused across different path measures (like distance, hop count, etc). Would do this if more input variance is introduced into application requirements in future. Would also consider moving route matching options to enums to structure parsing and matching logic.
- Distinguish PathOptimisationMatchers (like ShortestRoute) from PathMatchers. Would make more conceptual sense and possible optimisation.
- Measure performance and optimise bottlenecks if required.

# Assumptions
- Requirement specified "For test input 1 through 5, if no such route exists, output 'NO SUCH ROUTE'". Assume the use of same NOT found message for other (non-exact search) options as well.
- Test input specified "the towns are named using the first few letters of the alphabet from A to D", but tracks included "E" as well. Assumed this is a typo.

# Other notes
- External libraries allowed for build and testing ONLY. This resulted in some ugly System.out stuff for diagnostics and application output :)