INSERT INTO courses (
    created_at,
    updated_at,
    category_id,
    level,
    name,
    subtitle,
    image_url,
    description,
    total_duration,
    enrollment_count,
    rating,
    status
) VALUES (
             now(),                        -- created_at
             now(),                        -- updated_at
             1,                            -- category_id (Programming)
             'Beginner',                   -- level
             'JavaScript fundamentals',          -- name
             'Your first step into the world of programming.',  -- subtitle
             '/assets/img/courses/JavaScript.png',  -- image_url
             'This course covers the fundamentals of the JavaScript language, and all the necessary general programming concepts, such as dealing with errors, debugging, and importing modules',
             3000,                          -- total_duration in minutes
             2650,                            -- enrollment_count
             7.6,                          -- rating
             'OPENED'                      -- status
         );

INSERT INTO course_objectives (course_id, objective, order_index) VALUES
                                                                             (1, 'How to build programs using basic language constructs (conditions, loops, functions, and others)', 1),
                                                                             (1, 'How to create programs consisting of several modules', 2),
                                                                             (1, 'How to correctly analyze errors in the code and use print statement debugging when searching for them', 3);


INSERT INTO lessons (course_id, order_number, name, description, content, media) VALUES (
                                                                                         1,
                                                                                         1,
                                                                                         'Introduction',
                                                                                         'Course overview',
                                                                                        $$
                                                                                            <p>JavaScript is one of the most widely used programming languages worldwide. It's used to make interactive web pages, mobile apps, and server-side applications. The Frontend Developer career track on Hexlet provides all the necessary knowledge and skills for employment. Immersion in the language itself begins with this course.</p>
                                                                                            <p>The course introduces the fundamentals of JavaScript programming. We'll learn about data representation in memory, naming conventions, string encoding, and a variety of other topics. Learning the fundamentals of programming from scratch requires a unique approach to learning material structure. It's vital to become familiar with language constructions and to immerse oneself in the language.</p>
                                                                                            <h2>Course objectives</h2>
                                                                                            <ul>
                                                                                                <li>Learn the basics of JavaScript syntax</li>
                                                                                                <li>Learn how to write small programs</li>
                                                                                                <li>Get to know debugging and learn how to perform it effectively</li>
                                                                                                <li>Learn how to work in the Hexlet environment, understand the structure of practical tasks</li>
                                                                                                <li>Explore mechanisms for working with multi-file projects</li>
                                                                                            </ul>
                                                                                            <p>We will also put into practice everything that has been studied. Each lesson of the course ends with an exercise, and after the course you will find a lot of additional tasks that you should complete before moving on to the next course. These assignments cover the entire course material at once and help you gain the necessary practice for further professional advancement.</p>
                                                                                        $$,
                                                                                        null
                                                                                        ),
                                                                                        (
                                                                                         1,
                                                                                         2,
                                                                                         'Hello, World!',
                                                                                         'Writing the first program',
                                                                                        $$
                                                                                            <p>As usual, we'll start by writing a "Hello, World!" program. The program will print the text. To print something, you need to give computer a special command. In JavaScript, we use  <span>console.log()</span>:</p>
                                                                                            [[IMAGE:0]]
                                                                                            [[CODE:1]]
                                                                                            <p>We will sometimes show the result of running code lines using the comments, like this: <span>=> RESULT</span>. For example, <span>// => 4</span>.</p>
                                                                                            <h2>Comments</h2>
                                                                                            <p>The source code files may contain comments in addition to the code. This is non-executable line(s) of text that is required for programmers to take notes. They're used to describe how the code works and what errors need to be fixed, as well as to remind you to add anything later.</p>
                                                                                            [[CODE:2]]
                                                                                            <p>Comments in JavaScript are of two kinds.</p>
                                                                                            <p>Single line comments start with <span>//</span>. Any text or symbols that follow, won't be evaluated or executed. A comment can take up the whole line or several lines:</p>
                                                                                            [[CODE:3]]
                                                                                            <p>or can follow some code on the same line:</p>
                                                                                            [[CODE:4]]
                                                                                            <p>Multiline comments start with <span>/*</span> and end with <span>*/</span>.</p>
                                                                                            [[CODE:5]]
                                                                                            <p>Such comments usually clarify the purpose of pieces of code and build its documentation.</p>
                                                                                        $$,
                                                                                         '[
                                                                                           {
                                                                                             "type": "IMAGE",
                                                                                             "url": "/assets/img/lessons/HelloWorld.png",
                                                                                             "alt": "Hello World",
                                                                                             "caption": "Hello World",
                                                                                             "content": "",
                                                                                             "language": "",
                                                                                             "position": 1
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "console.log(''Hello, World!'')\n// => Hello, World!",
                                                                                             "language": "javascript",
                                                                                             "position": 2
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "// Delete the line below after completing the registration task\nconsole.log(10)",
                                                                                             "language": "javascript",
                                                                                             "position": 3
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "// For Winterfell!\n// For Lanisters!",
                                                                                             "language": "javascript",
                                                                                             "position": 4
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "console.log(''I am the King'') // For Lannisters!",
                                                                                             "language": "javascript",
                                                                                             "position": 5
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "/*\nThe night is dark\nand full of terrors.\n*/\nconsole.log(''I am the King'')",
                                                                                             "language": "javascript",
                                                                                             "position": 6
                                                                                           }
                                                                                         ]'::jsonb
                                                                                        ),
                                                                                        (1,
                                                                                         3,
                                                                                         'Statements',
                                                                                         'Learn the nuts and bolts of building JavaScript programs.',
                                                                                        $$
                                                                                            <p>A statement is a command given to a computer to do something. The JavaScript code is a set of statements which are usually separated by a <span>;</span> symbol.</p>
                                                                                            [[IMAGE:0]]
                                                                                            <p>Here is an example of some code with two statements.</p>
                                                                                            [[CODE:1]]
                                                                                            <p>Here is an example of some code with two statements.</p>
                                                                                            <p>Multiple statements can theoretically be placed on the same line:</p>
                                                                                            [[CODE:2]]
                                                                                            <p>The output on the screen will be the same, but because the code is difficult to comprehend, the instructions are placed under each other.</p>
                                                                                            <p>Why is it important to know? A statement is a unit of execution. An interpreter, which is the program that executes code in JavaScript, needs statements to be split in this way. It reads the file with the code, splits the code into statements, and then executes them. As developers, we must be able to recognize this order and mentally split the code into independent pieces that are easy to analyze.</p>
                                                                                            $$,
                                                                                         '[
                                                                                           {
                                                                                             "type": "IMAGE",
                                                                                             "url": "/assets/img/lessons/instructions.png",
                                                                                             "alt": "instructions",
                                                                                             "caption": "instructions",
                                                                                             "content": "",
                                                                                             "language": "",
                                                                                             "position": 1
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "console.log(''Mother of Dragons.'');\nconsole.log(''Dracarys!'');\n// => Mother of Dragons.\n// => Dracarys!",
                                                                                             "language": "javascript",
                                                                                             "position": 2
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "console.log(''Mother of Dragons.''); console.log(''Drakarys!'');",
                                                                                             "language": "javascript",
                                                                                             "position": 3
                                                                                           }
                                                                                         ]'::jsonb
                                                                                        ),
                                                                                        (1,
                                                                                         4,
                                                                                         'Arithmetic operations',
                                                                                         'Using arithmetic operations in JavaScript.',
                                                                                         $$
                                                                                            <p>At the most basic level, computers only use numbers. Even in high-level language applications, there are many numbers and operations with them. To get started with programming all you need to know is basic arithmetic - let’s start with that.</p>
                                                                                            [[IMAGE:0]]
                                                                                            <p>When adding two numbers in math, we write, for example, <span>3 + 4</span>. The same goes for programming. Here is a program that adds two numbers:</p>
                                                                                            [[CODE:1]]
                                                                                            <p>The statement <span>3 + 4</span> makes the computer add up the numbers and find the result. If you run this program, nothing will happen. Well, the computer will calculate the sum, of course, but that'll be it. The result of the sum isn't used, and as such, this program has no real value. We need to ask the computer to add <span>3 + 4</span>, and then give it a command to do something with the result. For example, print it:</p>
                                                                                            [[CODE:2]]
                                                                                            <p>Always indent arithmetic operators with spaces between the numbers (operands), it's a good coding style. This is why we write <span>console.log(3 + 4)</span>, and not <span>console.log(3+4)</span> in our examples.</p>
                                                                                            <p>Besides addition, the following operations are available:</p>
                                                                                            <ul>
                                                                                                <li><span>*</span> — multiplication</li>
                                                                                                <li><span>/</span> — division</li>
                                                                                                <li><span>-</span> — subtraction</li>
                                                                                                <li><span>%</span> — modulo</li>
                                                                                                <li><span>**</span> — exponentiation</li>
                                                                                            </ul>
                                                                                            <p>Now let’s print the result of division, and then the result of exponentiation:</p>
                                                                                            [[CODE:3]]
                                                                                            <p>The first statement will print <span>4</span> (since 8 / 2 is 4), and the second statement will print 9 (since 3&sup2; is 9).</p>
                                                                                            <h2>Operators</h2>
                                                                                            <p>Before we move on, let's take a look at the basic terminology. Operation signs such as + are called operators. They perform operations on certain values (operands). Operators are usually represented by one or more symbols, but occasionally, they can be represented by a word. Most of the operators are identical to those you'll have seen in math class.</p>
                                                                                            [[CODE:4]]
                                                                                            <p>Here the <span>+</span> is an addition operator, <span>8</span> and <span>2</span> are operands.</p>
                                                                                            <p>The addition operation has two operands, positioned to the left and right of the operator <span>+</span>. Operations with two operands are called binary operations. If at least one operand is missing, for example, <span>3 + ;</span> then the program will throw out a syntax error.</p>
                                                                                            <p>Besides binary operations (not operators) there are unary operations (with one operand) and even ternary (with three operands)! Moreover, operators may look the same but denote different operations.</p>
                                                                                            [[CODE:5]]
                                                                                            <p>In the example above, the unary operation applies to <span>3</span>. An interpreter will read it as follows: the minus operator tells it to take the number <span>3</span> and find the opposite, which is <span>-3</span>.</p>
                                                                                            <p>You might be a bit confused since <span>-3</span> is both a number and an operator with an operand, but that's simply how programming languages work.</p>
                                                                                            <h2>Commutative operations</h2>
                                                                                            <p>Do you remember the basic rule of arithmetic that "changing the order of the numbers we are adding doesn't change the sum"? It's called the commutative law.</p>
                                                                                            <p>A binary operation is commutative since swapping operands gets you the same result. Obviously, addition is a commutative operation: 3 + 2 = 2 + 3.</p>
                                                                                            <p>But is subtraction a commutative operation? Of course not: 2 - 3 ≠ 3 - 2. In programming, this law applies just like it does in arithmetic.</p>
                                                                                            <p>Moreover, most of the operations we face in real life are not commutative. Here is the conclusion: always pay attention to the order of things you work with.</p>
                                                                                            <h2>Composition of operations</h2>
                                                                                            <p>Suppose we want to calculate an expression such as <span>3 * 5 - 2</span>. That is exactly how we would write it down:</p>
                                                                                            [[CODE:6]]
                                                                                            <p>Note that the interpreter performs arithmetic operations in the right order: first division and multiplication, then addition and subtraction. Sometimes we want to change the order of calculations. We'll dig into this topic in the next lesson. Or another example:</p>
                                                                                            [[CODE:7]]
                                                                                            <p>As you can see, we can combine operations, which allows us to compute even more complex compound expressions. To visualize how calculations are done inside the interpreter, let's look at an example: <span>2 * 4 * 5 * 10</span>.</p>
                                                                                            <ol>
                                                                                                <li>First we calculate <span>2 * 4</span> and get <span>8 * 5 * 10</span></li>
                                                                                                <li>Then we calculate <span>8 * 5</span>, which gives us <span>40 * 10</span></li>
                                                                                                <li>Finally, do the last multiplication. The result will be <span>400</span></li>
                                                                                            </ol>
                                                                                            <p>As a result, the interpreter joins complex compound expressions by performing the arithmetic operations contained in them sequentially, by default in the correct order: first multiplication and division, then addition and subtraction.</p>
                                                                                            <h2>Operator precedence</h2>
                                                                                            <p>Look closely at the expression <span>2 + 2 * 2</span> and try to work out the answer.</p>
                                                                                            <p>The correct answer is <span>6</span>.</p>
                                                                                            <p>If you guessed 8, then you'll find this lesson useful. You'll have studied the order of operations in high school math. This concept defines the order in which operations are to be performed. For example, multiplication and division have a higher precedence than addition and subtraction, and exponentiation comes before all other arithmetic operations, e.g., <span>2 ** 3 * 2</span> gives us <span>16</span>.</p>
                                                                                            <p>But sometimes we have to perform calculations in a non-standard order. In tricky cases, precedence can (and must) be set with parentheses, just like we did in high school, e.g., <span>(2 + 2) * 2</span>.</p>
                                                                                            <p>Parentheses fit with any operation. They can be nested into each other as many times as you need. Here are a couple of examples:</p>
                                                                                            [[CODE:8]]
                                                                                            <p>Sometimes an expression may be visually cumbersome. In such cases, parentheses can come in handy without affecting the order of operations. For example, the task from the previous lesson becomes clearer with parentheses.</p>
                                                                                            <p>Before:</p>
                                                                                            [[CODE:9]]
                                                                                            <p>After:</p>
                                                                                            [[CODE:10]]
                                                                                            <p>Note: code is written for humans, since they'll be the ones to read it, the machine just executes it. For the machine, code is either valid or invalid, it doesn't recognize "more" or "less" valid code.</p>
                                                                                            $$,
                                                                                         '[
                                                                                           {
                                                                                             "type": "IMAGE",
                                                                                             "url": "/assets/img/lessons/arithmetics.png",
                                                                                             "alt": "arithmetics",
                                                                                             "caption": "arithmetics",
                                                                                             "content": "",
                                                                                             "language": "",
                                                                                             "position": 1
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "// Don''t forget the semicolon at the end,\n// since each line of code is a statement\n3 + 4;",
                                                                                             "language": "javascript",
                                                                                             "position": 2
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "// The sum is calculated first,\n// it is then passed to the print function\nconsole.log(3 + 4); // => ''7''",
                                                                                             "language": "javascript",
                                                                                             "position": 3
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "console.log(8 / 2);  // => 4\nconsole.log(3 ** 2); // => 9",
                                                                                             "language": "javascript",
                                                                                             "position": 4
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "console.log(8 + 2);",
                                                                                             "language": "javascript",
                                                                                             "position": 5
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "console.log(-3); // => -3",
                                                                                             "language": "javascript",
                                                                                             "position": 6
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "console.log(3 * 5 - 2); // => 13",
                                                                                             "language": "javascript",
                                                                                             "position": 7
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "console.log(2 * 4 * 5 * 10);",
                                                                                             "language": "javascript",
                                                                                             "position": 8
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "console.log(3 ** (4 - 2)); // => 9\nconsole.log(7 * 3 + (4 / 2) - (8 + (2 - 1))); // => 14",
                                                                                             "language": "javascript",
                                                                                             "position": 9
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "console.log(8 / 2 + 5 - -3 / 2); // => 10.5",
                                                                                             "language": "javascript",
                                                                                             "position": 10
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "console.log(((8 / 2) + 5) - (-3 / 2)); // => 10.5",
                                                                                             "language": "javascript",
                                                                                             "position": 11
                                                                                           }
                                                                                         ]'::jsonb
                                                                                        ),
                                                                                        (1,
                                                                                         5,
                                                                                         'Formatting errors (syntax and linter)',
                                                                                         'We''ll explore different types of errors and how to solve them.',
                                                                                         $$
                                                                                            <p>If a JavaScript program is syntactically incorrect, the interpreter will show a relevant message and a message showing the file and line where the error might have occurred. Syntax errors occur when the code has grammatical mistakes. Grammar is essential to human language, but a text with grammar mistakes can still be read and understood. However, when it comes to programming, things are much more strict. Even a tiny mistake can mean your program won't run. Maybe you've mixed up your brackets, or there's a ; that you forgot to add — these are just some examples of such mistakes.</p>
                                                                                            <p>Here is an example of some code with a syntax error:</p>
                                                                                            [[CODE:0]]
                                                                                            <p>If we run this code we will see the following message: <span>SyntaxError: missing ) after the argument list</span>. In JavaScript, these errors are labelled as "SyntaxError".</p>
                                                                                            <p>On the one hand, syntax errors are the most obvious because they deal with code grammar rules and have nothing to do with its logic. You can easily fix it once you find it.</p>
                                                                                            <p>On the other hand, an interpreter will not always tell you the correct position of an error. Sometimes you need to add a forgotten bracket to different place than what the error message says.</p>
                                                                                            <h2>Linting errors</h2>
                                                                                            <p>Since we've learned to write simple programs, let's talk about the very process of writing.</p>
                                                                                            <p>The program code should be organized in a certain way so that it is sufficiently clear and easy to maintain. Special sets of rules - standards - describe different aspects of code writing. The most common standard in JavaScript is AirBnb.</p>
                                                                                            <p>In any programming language, there are utilities known as linters. They ensure the code meets the standards. For example, ESLint analyzes JavaScript code.</p>
                                                                                            <p>Take a look at the example from the previous lesson:</p>
                                                                                            [[CODE:1]]
                                                                                            <p>Linter won't be happy about it, because several rules have been violated:</p>
                                                                                            <ul>
                                                                                                <li>space-infix-ops – No space between operator and operands/li>
                                                                                                <li>no-mixed-operators – You can't write code that contains multiple operations in a single expression with no explicit parentheses separation</li>
                                                                                            </ul>
                                                                                            <p>In the last lesson we recognized that such an abundance of numbers and symbols may be confusing and we decided to add parentheses purely for the sake of readability:</p>
                                                                                            [[CODE:2]]
                                                                                            <p>This code does not violate the rules, and the linter will "say nothing" as it were.</p>
                                                                                            <p>In previous exercise you probably did this:</p>
                                                                                            [[CODE:3]]
                                                                                            <p>Is there any violation of the standard here?</p>
                                                                                            <p>Unfortunately, yes. This time, the <span>*</span> and <span>/</span> are in the same expression and there are no parentheses. You can solve this problem by adding additional parentheses. But at some point, too many parentheses can make the code incomprehensible again. At this point, you can divide the expression into separate parts. We will learn how to do this in the following lessons.</p>
                                                                                            <p>no-mixed-operators is just one of many rules. Other ones describe indentations, entity names, parentheses, mathematical operations, line length, and many other aspects. Each rule may seem small and insignificant, but together they form the basis of good code.</p>
                                                                                            <p>On Hexlet, the linter checks your code and reports violations after you purchase subscription.</p>
                                                                                            $$,
                                                                                         '[
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "console.log(''Hodor''",
                                                                                             "language": "javascript",
                                                                                             "position": 1
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "console.log(8/2+5 - -3 / 2); // => 10.5",
                                                                                             "language": "javascript",
                                                                                             "position": 2
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "console.log(((8 / 2) + 5) - (-3 / 2)); // => 10.5",
                                                                                             "language": "javascript",
                                                                                             "position": 3
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "console.log(70 * (3 + 4) / (8 + 2));",
                                                                                             "language": "javascript",
                                                                                             "position": 4
                                                                                           }
                                                                                         ]'::jsonb
                                                                                        ),
                                                                                        (1,
                                                                                         6,
                                                                                         'Strings',
                                                                                         'Learn how to work with strings.',
                                                                                         $$
                                                                                            [[IMAGE:0]]
                                                                                            <p>Which of these five items are strings?</p>
                                                                                            [[CODE:1]]
                                                                                            <h2>Quotation marks</h2>
                                                                                            <p>The first two are clearly strings, we've already worked with similar constructions and mentioned that strings are sets of characters</p>
                                                                                            <p>Any single character between parentheses is a string. The empty string '' is also a string. So everything inside the quotation marks can be considered a string, even if it is a space, one character, or no characters at all.</p>
                                                                                            <p>In previous lessons, we enclosed strings in single quotes, but you can also use double quotes:</p>
                                                                                            [[CODE:2]]
                                                                                            <p>Imagine you want to print a string: Dragon's mother. The apostrophe before the letter s and a single quote are the same symbols. Let's print it:</p>
                                                                                            [[CODE:3]]
                                                                                            <p>This program won't work. For JavaScript, the string begins with a single quote and ends after the letter n. Then comes some characters: <span>s mother</span> without quotes, which are not a string. And then there is one quote opening a sting which is never closed: <span>');</span>. This code is syntactically incorrect (you can see it by the way the code is highlighted).</p>
                                                                                            <p>It's a good idea here is to use double quotes. This version of the program will work correctly:</p>
                                                                                            [[CODE:4]]
                                                                                            <p>Now the interpreter knows that the string begins with a double quote, so it should end with a double quote too. And the single quote inside has become the part of the string.</p>
                                                                                            <p>It works the other way too. If you want to use double quotes inside a string, you should enclose the string in single quotes. And the number of quotes in a string doesn't matter.</p>
                                                                                            <p>Now, what if we want to create a string like this?</p>
                                                                                            [[CODE:5]]
                                                                                            <p>There are single and double quotes here. What can we do in this case? You need to somehow tell the interpreter to consider each quote as a part of the string, and not as its beginning or an end.</p>
                                                                                            <p>To do this, you have to use an escape character. In our case, the character that starts and ends a string is either a single or a double quote, depending on the part of the code. Use a backslash <span>\</span> before the character you want to escape.</p>
                                                                                            [[CODE:6]]
                                                                                            <p>Look closely: we had to use <span>\</span> for double quotes to escape them, and not for the single quote (apostrophe) because the string is written in double quotes. If the string were written in single quotes, the escape character would be used before the apostrophe, not before double quotes.</p>
                                                                                            [[CODE:7]]
                                                                                            <p>But what if you want to print the backslash? Just like any other special symbol, it escapes using a backslash too.</p>
                                                                                            [[CODE:8]]
                                                                                            <p>Self-test: what will be printed?</p>
                                                                                            [[CODE:9]]
                                                                                            <h2>Escape sequences</h2>
                                                                                            <p>Imagine you want to print a dialogue between the Mother of Dragons and her child:</p>
                                                                                            [[CODE:10]]
                                                                                            <p>If you print a string with this text:</p>
                                                                                            [[CODE:11]]
                                                                                            <p>then you'll see:</p>
                                                                                            [[CODE:12]]
                                                                                            <p>Not quite what we were looking for. The strings are written one after the other, it doesn't start a new line. We need to tell the interpreter to "press enter" as it were. In other words, it needs to put a line break after the question mark. You can do this with the new line symbol '\n'.</p>
                                                                                            [[CODE:13]]
                                                                                            <p>The result:</p>
                                                                                            [[CODE:14]]
                                                                                            <p><span>\n</span> is a special symbol. It's often referred to as LF (Line Feed, sometimes as line break or newline) in documentation. You may have initially thought it was a misprint, since there are two symbols - \ and n, but this isn't the case. To the computer, this is no more than an invisible symbol to tell it to go to the next line. Proof:</p>
                                                                                            [[CODE:15]]
                                                                                            <p>Why is it done in this way? <span>\n</span> is just a way to write a line break symbol. That's why line feed is just one character, just invisible. And it's also why this problem has arisen. There had to be a way of representing it using a keyboard. And since the number of keyboard characters is limited and they're all dedicated to very important things, special characters are entered using these escape sequences.</p>
                                                                                            <p>The Line Feed symbol is not something specific to programming. Anyone who has ever typed on a computer has used the line feed by clicking Enter. Many editors can display these invisible characters, you can use this feature to see where they are (though it's only for display, these characters are invisible, they've no graphical representation):</p>
                                                                                            [[CODE:16]]
                                                                                            <p>The device that outputs the corresponding text takes this character into account. For example, when the printer reaches the line feed, it pulls the paper up one line, and the text editor brings all subsequent text down one line as well.</p>
                                                                                            <p><span>\n</span> is an example of an escape sequence. Although there are dozens of these characters, only a few of them are common in programming. Besides line feed, there is also indents (which you get from pressing Tab) and carriage return (Windows only). Programmers often need to use <span>\n</span> line break to format text properly.</p>
                                                                                            [[CODE:17]]
                                                                                            <p>The result:</p>
                                                                                            [[CODE:18]]
                                                                                            <p>Note:</p>
                                                                                            <ol>
                                                                                                <li>It does not matter what comes before or after <span>\n</span>, whether it's a character or an empty string. The line feed will be detected and executed either way</li>
                                                                                                <li>
                                                                                                    <p>Remember that a string can contain a single character or none at all. Additionally, a string can only contain \n. Analyze the following example:</p>
                                                                                                    [[CODE:19]]
                                                                                                    <p>First the interpreter outputs the string "line feed", and then the normal string.</p>
                                                                                                    <p>Why are there two empty lines before the Dunsen line instead of one? The point is that <span>console.log()</span> automatically adds a line feed to the end when it outputs a value. So, we explicitly typed one line feed, passing this escape character as an argument in the function, and the second line feed is added automatically by the function itself.</p>
                                                                                                    <p>One more example:</p>
                                                                                                    [[CODE:20]]
                                                                                                    <p>Now you understand enough to figure out why the result was formed in this way.</p>
                                                                                                </li>
                                                                                                <li>
                                                                                                    <p>If we need to print \n as a text (two separate characters), we can use the escape character, adding another \ at the beginning. I.e., the sequence of \n will be printed as characters \ and n following each other</p>
                                                                                                    [[CODE:21]]
                                                                                                </li>
                                                                                            </ol>
                                                                                            <p>A small but important note about Windows. Windows uses \r\n by default to enter a line break. This combination works well in Windows but creates problems when copied to other systems (for example, when the development team includes both Windows and Linux users). The point is that the sequence \r\n has a different interpretation depending on the encoding chosen (we discuss it later). For this reason, it's common among developers to always use \n without \r, since it means that LF is always interpreted the same way and works fine on any system. Remember to configure your editor to use \n.</p>
                                                                                            <h2>Concatenation</h2>
                                                                                            <p>In web development, programs use strings constantly. Everything we see on websites is represented as text in one way or another. This text is most often dynamic, it's made up of different connected pieces. In programming, the operation of joining one string to another is called concatenation.</p>
                                                                                            [[CODE:22]]
                                                                                            <p>Strings always concatenate in the same order in which the operands are written. The left operand becomes the left part of the string, and the right one becomes the right part</p>
                                                                                            <p>Here are a few more examples:</p>
                                                                                            [[CODE:23]]
                                                                                            <p>As you can see, strings can concatenate even if they are written in different quotes.</p>
                                                                                            <p>In the last example, the name of the city has a misprint: King's Landing should be written with a space. But there were no spaces at the beginning of our strings, and the spaces left and right of the <span>+</span> don't matter because they are not part of the strings.</p>
                                                                                            <p>There are two ways to fix this:</p>
                                                                                            [[CODE:24]]
                                                                                            <p>A space is also a symbol. The more spaces you put, the wider the indentation will be:</p>
                                                                                            [[CODE:25]]
                                                                                            $$,
                                                                                         '[
                                                                                           {
                                                                                             "type": "IMAGE",
                                                                                             "url": "/assets/img/lessons/concatenation.png",
                                                                                             "alt": "concatenation",
                                                                                             "caption": "concatenation",
                                                                                             "content": "",
                                                                                             "language": "",
                                                                                             "position": 1
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "''Hello''\n''Goodbye''\n''G''\n'' ''\n''''",
                                                                                             "language": "javascript",
                                                                                             "position": 2
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "// Coding airbnb standard recommends\n// using single quotes where possible\nconsole.log(\"Dracarys!\");",
                                                                                             "language": "javascript",
                                                                                             "position": 3
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "console.log(''Dragon''s mother'');\n// Uncaught SyntaxError: missing ) after argument list",
                                                                                             "language": "javascript",
                                                                                             "position": 4
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "console.log(\"Dragon''s mother\");",
                                                                                             "language": "javascript",
                                                                                             "position": 5
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "Dragon''s mother said \"No\"",
                                                                                             "language": "javascript",
                                                                                             "position": 6
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "// Only \" is escaped, because in this code\n// double quotes have a special meaning\nconsole.log(\"Dragon''s mother said \\\"No\\\"\");\n// => Dragon''s mother said \"No\"",
                                                                                             "language": "javascript",
                                                                                             "position": 7
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "// \\ won''t be printed if it''s followed by a normal character,\n// not a special one\nconsole.log(\"Death is \\so terribly final\");\n// => Death is so terribly final,",
                                                                                             "language": "javascript",
                                                                                             "position": 8
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "console.log(\"\\\\\");\n// => \\",
                                                                                             "language": "javascript",
                                                                                             "position": 9
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "console.log(\"\\\\ \\\\ \\\\\\\\ \\\\\\ \\''\\\"\");",
                                                                                             "language": "javascript",
                                                                                             "position": 10
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "- Are you hungry?\n- Aaaarrrgh!",
                                                                                             "language": "javascript",
                                                                                             "position": 11
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "console.log(''- Are you hungry?- Aaaarrrgh!'');",
                                                                                             "language": "javascript",
                                                                                             "position": 12
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "- Are you hungry?- Aaaarrrgh!",
                                                                                             "language": "javascript",
                                                                                             "position": 13
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "console.log(''- Are you hungry?\\n- Aaaarrrgh!'');",
                                                                                             "language": "javascript",
                                                                                             "position": 14
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "- Are you hungry?\n- Aaaarrrgh!",
                                                                                             "language": "javascript",
                                                                                             "position": 15
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "// We haven''t studied it yet, but you should know the truth\n// Below is code that returns the length of a string\n\n''a''.length;    // 1\n''\\n''.length;   // 1 !!!\n''\\n\\n''.length; // 2 !!!",
                                                                                             "language": "javascript",
                                                                                             "position": 16
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "- Hi!¶\n- Oh, hey!¶\n- What''s up?",
                                                                                             "language": "javascript",
                                                                                             "position": 17
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "console.log(''Gregor Clegane\\nDunsen\\nPolliver\\nChiswyck'');",
                                                                                             "language": "javascript",
                                                                                             "position": 18
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "Gregor Clegane\nDunsen\nPolliver\nChiswyck",
                                                                                             "language": "javascript",
                                                                                             "position": 19
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "console.log(''\\n'');\nconsole.log(''Dunsen'');\n\n// =>\n// =>\n// => Dunsen",
                                                                                             "language": "javascript",
                                                                                             "position": 20
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "console.log(''Polliver'');\nconsole.log(''Gregor Clegane'');\nconsole.log();\nconsole.log(''Chiswyck'');\nconsole.log(''\\n'');\nconsole.log(''Dunsen'');\n\n// => Polliver\n// => Gregor Clegane\n// =>\n// => Chiswyck\n// =>\n// =>\n// => Dunsen",
                                                                                             "language": "javascript",
                                                                                             "position": 21
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "console.log(''Joffrey loves using \\\\n'');\n// => Joffrey loves using \\n",
                                                                                             "language": "javascript",
                                                                                             "position": 22
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "// The operator is the same as for adding\n// but it has different semantics (meaning)\n\nconsole.log(''Dragon'' + ''stone'');\n// => Dragonstone",
                                                                                             "language": "javascript",
                                                                                             "position": 23
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "console.log(''Kings'' + ''wood'');     // => Kingswood\n\n// Reverse word order\nconsole.log(''road'' + ''Kings'');     // => roadKings\n\n// Any string can be concatenated\nconsole.log(\"King''s\" + ''Landing''); // => King''sLanding",
                                                                                             "language": "javascript",
                                                                                             "position": 24
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "// Both ways are functionally the same\n\n// Put space in the end of the left part\nconsole.log(\"King''s \" + ''Landing''); //  => King''s Landing\n// Put space in the beginning of the right part\nconsole.log(\"King''s\" + '' Landing''); //  => King''s Landing",
                                                                                             "language": "javascript",
                                                                                             "position": 25
                                                                                           },
                                                                                           {
                                                                                             "type": "CODE",
                                                                                             "url": "",
                                                                                             "alt": "",
                                                                                             "caption": "",
                                                                                             "content": "console.log(\"King''s \" + '' Landing'');   // => King''s  Landing\nconsole.log(\"King''s \" + ''  Landing'');   // => King''s   Landing",
                                                                                             "language": "javascript",
                                                                                             "position": 26
                                                                                           }
                                                                                         ]'::jsonb
                                                                                        );

