/*
*
*   SYNTAX EXAMPLES
*
*/
let x;             // How to declare variables
let y;             // How to declare variables
let x, y;          // How to declare variables
// assignment operator ( = ) 
x = 5;             // How to assign values
y = 6;             // How to assign values
x = 5; y = 6;      // How to assign values

// arithmetic operators( + - *  / ) ( % )	Modulus (Remainder) ( ++ )	Increment ( -- )	Decrement
(5 + 6) * 10       // How to compute values
z = x + y;         // How to compute values
// verificar na consola os valores de x,y,z

// Algebra
let price1 = 5;
let price2 = 6;
let total = price1 + price2;

// operator (+) on strings
let fullName = "Zé" + " " + "Carlos"

// Comments
'Code after double slashes // or between /* and */ is treated as a comment.'
/*
let a = 5;
let b= 6;
*/

// All JavaScript identifiers are case sensitive.
let lastname, lastName;
lastName = "Carlos";
lastname = "Manel";
//  Lower Camel Case On Variable names

/*
*
*   Data Types
*
*/
let length = 16;                                // Number (1 / 1.01 / 123e5 (12300000) )

let x = true;                                   // Boolean
let x = 5;
let y = 5;
let z = 6;
(x == y);                                        // Returns true
(x == z);                                        // Returns false

let lastName2 = "Carlos";                          // String
let x = {firstName: "Zé", lastName2: "Carlos"};  // Object

// JSON Syntax Rules
// Data is in name/value pairs
// Data is separated by commas
// Curly braces hold objects (chavetas ({}))
// Square brackets hold arrays

let x;                                          // undefined
let color = ["Blue", "Red", "Green"];           // Object Array

let person = {firstName: "John", lastName: "Doe", age: 50, eyeColor: "blue"};
person = null;        // Now value is null, but type is still an object
let person = {firstName: "John", lastName: "Doe", age: 50, eyeColor: "blue"};
person = undefined;   // Now both value and type is undefined

typeof undefined           // undefined
typeof null                // object
null === undefined         // false
null == undefined          // true

typeof ""                  // Returns "string"
typeof 0                   // Returns "number"
// The typeof operator can return one of these primitive types:
// string
// number
// boolean
// undefined
// The typeof operator can return one of two complex types:
// function
// object

let answer = "It's alright";                    // Single quote inside double quotes
let x = 1 + 4 + "Zé";      // 5Zé
let x = "Zé" + 16 + 4;     // Zé164

/*
*
*   Functions
*
*/
// ES5
let multiply = function(x, y = 2) {
  return x * y;
}

// ES6
let multiply = (x, y) => x * y;
let multiply = (x, y) => {return x * y};
multiply(5, 2);

// Default Parameter Values
function sum(x, y = 10) {
  // y is 10 if not passed or undefined
  return x + y;
}
sum(5); // will return 15

/*
*
*   Declaracao IF
*
*/
if (new Date().getHours() < 13) {
  greeting = "Bom dia";
}

if (new Date().getHours() < 13) {
  greeting = "Bom dia";
} else {
  greeting = "Good evening";
}

greeting = (new Date().getHours() < 13) ? "Bom dia" : "Good evening"

if (new Date().getHours() < 13) {
  greeting = "Bom dia";
} else if (new Date().getHours() > 20) {
  greeting = "Boa noite";
} else {
  greeting = "Boa tarde";
}

greeting = (new Date().getHours() < 13) ? "Bom dia" : (new Date().getHours() > 20)? "Boa noite" : "Boa tarde"

/*
*
*   Declaracao switch
*
*/
// When JavaScript reaches a break keyword, it breaks out of the switch block.

// This will stop the execution of more code and case testing inside the block.

// When a match is found, and the job is done, it's time for a break. There is no need for more testing.

// A break can save a lot of execution time because it "ignores" the execution of all the rest of the code in the switch block.

// It is not necessary to break the last case in a switch block. The block breaks (ends) there anyway.

switch (new Date().getDay()) {
  case 0:
    day = "Sunday";
    break;
  case 1:
    day = "Monday";
    break;
  case 2:
    day = "Tuesday";
    break;
  case 3:
    day = "Wednesday";
    break;
  case 4:
    day = "Thursday";
    break;
  case 5:
    day = "Friday";
    break;
  case 6:
    day = "Saturday";
}

// Default Value
switch (new Date().getDay()) {
  case 6:
    text = "Today is Saturday";
    break;
  case 0:
    text = "Today is Sunday";
    break;
  default:
    text = "Looking forward to the Weekend";
}

// Common Code Blocks
switch (new Date().getDay()) {
  case 4:
  case 5:
    text = "Soon it is Weekend";
    break;
  case 0:
  case 6:
    text = "It is Weekend";
    break;
  default:
    text = "Looking forward to the Weekend";
}


/*
*
*   Declaracao For
*
*/
// Break
for (i = 0; i < 10; i++) {
  if (i === 3) {break;}
  text += "The number is " + i + "<br>";
}

// Continue
for (i = 0; i < 10; i++) {
  if (i === 3) {continue;}
  text += "The number is " + i + "<br>";
}

// for/in     (object / string)
let animal = {
  name: "Zé Carlos", age: "3", type: "Cat"
};
let txt = "";
for (const key in animal) {
  console.log(key)
  txt += animal[key] + " ";
}
console.log(txt);

// for/of     (array / map / set / collections / string)
const animals = ['Zé Carlos', 'Zé Manel', 'Samuel Matarruano'];
for (const iterator of animals) {
  console.log(iterator);
}

// forEach
const animals = ['Zé Carlos', 'Zé Manel', 'Samuel Matarruano'];
animals.forEach(animal => {
  console.log(animal);
});

// for
const animals = ['Zé Carlos', 'Zé Manel', 'Samuel Matarruano'];
for (let index = 0; index < animals.length; index++) {
  const animal = animals[index];
  console.log(animal);
}

/*
*
*   Declaracao while
*
*/
let i = 0;
while (i < 10) {
  text += "Numero " + i;
  i++;
}

// i++ != ++i
let i = 0;
let text = "";
while (++i < 5) {
  text += "Numero " + i + " ";
}
while (i++ < 5) {
  text += "Numero " + i + " ";
}


// break example
let index = 0;
while (true) {
  if (index == 10) {
    console.log("Index 10");
    break;
  }
  if (index == 11) {
    console.log("Index 11");
  }
  index++;
}

// Do/while
do {
  text += "Numero " + i;
  i++;
}
while (i < 10);

