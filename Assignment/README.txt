Dependency Injections used:
	Service layer is autowired using annotation in Controller.
Interfaces:
	Service layer is an interface which is being implemented by a class. This helps creating a abstraction of all functions implemented.
Implementation:
	Program uses Multi-threaded environment where each thread maintains a step function. 
	It implemenents following functionality.
		1. Create a task with given start value and step time.
		2. Check current counter value, created date and step time.
		3. Get all tasks and their state that are running currently.
		4. Render all these tasks in an HTML table using Spring Thymeleaf.
		5. Clear current counter value and stop thread execution.
		6. Pause specified thread operation using volatile boolean field.
	Junit test cases and jacoco plugin for code coverage.

Postman collection for assignment included in project zip.
