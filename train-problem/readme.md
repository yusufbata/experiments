# Overview
This project aims to solve the thoughtworks train problem.

# Priorities & status
1: Complete all application features (requirement): Completed - proven using acceptance test cases and sample input files.
2: Maintainability: Not perfect due to time constraints. In particular, unit-level tests would result in more robustness. Acceptance tests do however enable safe refactoring while maintaining existing functionality.
3: Performance: Not measured due to time constraints. IF measurements indicate bottlenecks, maintainability will allow changes to be made much more easily. Most likely area of performance change will be Path module (path finder implementation).

# Running the application
## Prerequisites for running application
- Unzip utility (like 7zip) -> to unpackage compressed file.
- Maven (tested on 3.1.1) -> to build executable from source code.
- Java (tested on 1.7.0_51) -> to run executable.
- (optional) 2 data files for application input. Samples provided with source code.

## Building the application
1: Unzip train-problem.zip file
2: Navigate to unzipped directory -> train-problem/
3: Run: mvn clean install

## Using the application
1: Navigate to the target directory after building application -> train-problem/target/
2: Run: java -jar train-problem-0.0.1-SNAPSHOT.jar classes/sample-tracks.txt classes/sample-routespecs.txt
3: You will see the output of the application on the command-line. Note that the sample files used in the previous step have the problem input specified in the problem statement.
3: (optional) Modify the sample input files and run again, or use your own input files (then remember to change the file paths above accordingly).

# Application design
xxx

## Application Structure
The application has a modular structure following the ports-adapters architecture style:

- adapters -> RouteInputFileAdapter: Used for providing input to the application via files. This is currently the only supported interaction with the application (other than unit tests). More adapters can however easily be added.
- application-services -> RailroadApplicationService: Used for interacting with the application via adapters.
- model -> TrackMaps (Entity) and Path (Value) domain components. Used by the application-services to compose application features.
- infrastructure -> Implementation of application-service dependencies (like Repos).
- runner -> App class used to launch the application via the command-line.

## Application Design
xxx

# Process?
Design principles?
Methodologies?

# Possible improvements
- xx

# Limitations
- External libraries for build and testing ONLY. This resulted in some ugly System.out stuff from diagnostics and application output :)