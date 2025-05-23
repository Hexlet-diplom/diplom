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
             'Learn the core concepts of JavaScript in a beginner-friendly format.',  -- subtitle
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
                                                                                        );
