## Content ##
1. Variables
	String
	Number
	Boolean
	Array
	Object
2. Comments
```
// This is a comment
/*
This is a multi line
comment
*/
```
3. Operators
| Operator | Symbol(s) |
| -------- | --------- |
| add/concatenation | + |
| subtract, multiple, divide | -, *, / | 
| assignment operator | = |
| Identity operator | === |
| Negation, not equal | !, !== |
4. Conditionals
```javascript
if (...) {
	...
} else {
	...
}
```
5. Functions
6. Events


## javascript guide ##

1. Grammar and types
	Lexical grammar & syntax
	Variables and constants
	Scoping
	Data structures and types
	Literals

**Values**
| Type | Examples of typed values / Notes |
| ---- | -------------------------------- |	
| Numbers | 42, 3.14159 |
| Logical(Boolean) | true/false |
| Strings | "Howdy" |
| null | a special keyword denoting a null value; null is also a primitive value. Because JavaScript is case-sensitive, null is not the same as Null, NULL, or any other variant |
| undefined | a top-level property whose value is undefined; undefined is also a primitive value |

**Converting strings to numbers**
``` javascript
parseInt()
parseFloat()
```

**Variables**
``` javascript
var var1 = 1;
```

**Constants**
```javascript
const prefix = '212';
```

**Strings**
```javascript
var str = "string";
```
**Array**
```javascript
var array = ["a", "b", 3];
```

**Object**
```javascript
var obj = {
	a : "1",
	2 : "b"
};
```

2. Control flow and error handling
	if .. else ...
	switch
	try/catch/throw
	Error objects
	Promises
3. Loops and iteration
	for
	for .. in
	for .. of
	while
	do ... while
4. Functions
	Defining functions
	Calling functions
	Closures
	Arguments and parameters
	Arrow functions
5. Expressions and operators
	Expressions
	Arithmetic operators
	Bitwise & logical
	operators
	Comparisons
	Assignment & destructuring
6. Numbers and dates
	Number
	Math
	Data
7. Text formatting
	Strings
	Template strings
	Regular Expressions
	Internationalization
8. Indexed collections
	Arrays
	Typed arrays
9. Keyed collections and structured data
	Maps, WeakMaps
	Sets, WeakSets
	JSON
10. Working with objects
	Creating objects
	Object initializer
	Inheritance
	Getter and Setter
11. Details of the object model
	Prototype-based OOP
	Properties and methods
	Inheritance
12. Iterators and generators
	Iterable protocol
	Iterator protocol
	Generators
13. Meta programming
	Proxy
	Reflect