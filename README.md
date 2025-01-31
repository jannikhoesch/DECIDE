# DECIDE Project - DD2480 Assignment 1

## Overview
DECIDE is a Java-based project designed to implement and evaluate launch interceptor conditions (LICs) as part of a hypothetical anti-ballistic missile system. The project is structured into multiple Java classes that handle different aspects of the computation, with unit tests ensuring correctness.

## Project Structure
```
DECIDE/
├── decide/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/decide/
│   │   │   │   ├── Decide.java             # Main logic class
│   │   │   │   ├── LICConditions.java      # Contains LIC implementations
│   │   │   │   ├── Parameters.java         # Stores parameters for LICs
│   │   │   │   ├── Point.java              # Represents a geometric point
│   │   ├── resources/                       # Resource files (if needed)
│   ├── test/
│   │   ├── java/
│   │   │   ├── DecideTest.java             # Unit tests for Decide class
│   │   │   ├── FUVTest.java                # Unit tests for the FUV method
│   │   │   ├── InputTest.java              # Unit tests for input validation method
│   │   │   ├── LAUNCHTest.java             # Unit tests for the LAUNCH method
│   │   │   ├── LICConditionsTest.java      # Unit tests for LIC conditions
│   │   │   ├── PointTest.java              # Unit tests for Point class
│   │   │   ├── PUMTest.java                # Unit tests for PUM method
├── target/                   # Compiled output (generated by Maven)
├── pom.xml                    # Maven build configuration
├── .gitignore                 # Git ignore file
├── decide.pdf                 # Documentation file
├── README.md                  # Project documentation
```

## Prerequisites
- Java 11 or later
- Apache Maven (for dependency management and build automation)
- A Java-compatible IDE (VS Code, IntelliJ IDEA, Eclipse, etc.)

## Installation & Setup
1. Clone the repository:
   ```sh
   git clone https://github.com/jannikhoesch/DECIDE.git
   cd DECIDE
   ```
2. Build the project using Maven:
   ```sh
   mvn clean install
   ```
3. Run the tests:
   ```sh
   mvn test
   ```

## Running Tests
To execute unit tests, use:
```sh
mvn test
```
This will run all tests under `test/java/` to verify correctness.

## Contributing
1. Fork the repository
2. Create an issue
3. Create a feature branch (`git checkout -b {branchname}`)
4. Commit your changes (`git commit`)
5. Push to the branch (`git push origin {brancname}`)
6. Open a Pull Request

## Commit Naming Convention
Title: `{type}: {title}`  
Description: `{description of change} #{issueNr}`

## Branch Naming Convention
Title: `issue/{issueNr}`  
Description: `{description of change} #{issueNr}`

---

## Way of Working  

We used our first meeting to agree upon our way of working, which we have written down in the first assignment report **“Meet your group”**. Then we started distributing the workload by creating Git issues together and assigning them evenly. Each group member was responsible for finishing their tasks in their own time.  

Especially in the beginning, most group members who had not worked with Git before in a similar project setting had challenges getting used to it. During the first week, after some experience, the problems decreased, and we started working efficiently both as a team and with our own tasks.  

Using the **Essence Standard Checklist**, we currently consider ourselves to be in the **“In Place”** state since using the tools is working properly, but we are still not entirely used to working with Git in this way yet, which causes some problems to arise from time to time. We believe, however, that more practice will optimize our way of working and that we are on a good path to reaching an efficient workflow.  


## Task Distribution & Reflections  

### **Harald**  
- **Tasks:**  
  - LIC 1, LIC 6, LIC 11  
  - PUM method  
  - LIC Input validation  
  - Point helper method `circleLineSegment`  

- **Reflection:**  
  I feel like, while the programming was quite simple, I’m very proud of the teamwork the group has shown. We’ve worked well together and helped each other out, which is what this assignment is really about. We’ve delegated work effectively, and even though one group member dropped the course one day before the presentation, we solved those issues anyway.  

  Personally, I’m also happy to have improved my Git skills since I had only used Git for very simple version control before, without proper pull requests and code reviews.

---

### **Jannik**  
- **Tasks:**  
  - LIC 3, LIC 8, LIC 13  
  - Refactoring of LIC methods  
  - `DECIDE` method  

- **Reflection:**  
  In the beginning, this assignment was quite challenging because I had not worked with Git a lot. After some days, the process of creating issues, branches, and pull requests became clearer, and I am glad to have learned the proper use of Git in a group project.  

  I think that the team has worked together very well, and the final result is remarkable.

---

### **Amanda**  
- **Tasks:**  
  - LIC 4, LIC 7, LIC 9, LIC 12, LIC 14  
  - `FUV` method  
  - `LAUNCH` method  

- **Reflection:**  
  I had never used Git before and found it a bit overwhelming at first. However, after just a week, I feel much more comfortable using it, which is good since it is such a useful tool.  

  I am also very happy with the teamwork since everyone has been helpful, collaborative, and contributed to the project.

---

### **Zyad**  
- **Tasks:**  
  - LIC 0, LIC 2, LIC 5, LIC 10  
  - Documentation  
  - Point helper methods  

- **Reflection:**  
  While I’ve used Git in projects before, I wasn’t accustomed to using issues and linking them with pull requests in a rigorous manner. This project opened my eyes to the issue tracking capabilities of GitHub, and I’m thankful that, as a group, we worked really well together and collaborated deeply.  

  Had it not been the case, the GitHub repository wouldn’t have been this clean.