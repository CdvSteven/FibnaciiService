Feature: Test the fibonacci service 

	Scenario Outline: test the fibonacci service
		Given we have the number <input>
		when we call the web service
		then we check the result <output>

		Examples: 
		| input    | output     |
		| 1        | 0          |
		| 2        | 0 1        |
		| 4        | 0 1 1 2    |
		| -100     | error      |
