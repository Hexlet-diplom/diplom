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
             'Python: Lists',          -- name
             'Lists: Python’s favorite way to hoard stuff.',  -- subtitle
             '/assets/img/courses/Python.png',  -- image_url
             'In this course, you will comprehend the structure of list data. You will learn more about slices, iterators, and lazy calculations. As a result, you will learn how to manipulate data with lists and work even with infinite collections. The knowledge from this course helps programmers solve algorithmic problems and process data efficiently. This course is suitable for those who already know the basics of Python and are still learning.',
             2400,                          -- total_duration in minutes
             4302,                            -- enrollment_count
             8.77,                          -- rating
             'OPENED'                      -- status
         );

INSERT INTO course_objectives (course_id, objective, order_index) VALUES
                                                                      (3, 'Define and manipulate lists in code', 1),
                                                                      (3, 'Form and process lists in loops', 2),
                                                                      (3, 'Create and work with iterable objects', 3);

INSERT INTO lessons (course_id, order_number, name, description, content, media) VALUES (
                                                                                            3,
                                                                                            1,
                                                                                            'About the course',
                                                                                            'Learning about the course, its structure, objectives, and goals',
                                                                                            $$
                                                                                                <p>Programming becomes interesting when you can work with sets of elements.</p>
                                                                                                <p>There are just a few examples where they appear:</p>
                                                                                                <ul>
                                                                                                    <li>Paginated data output on the website</li>
                                                                                                    <li>Calculate the order total based on the price of each item</li>
                                                                                                    <li>Displaying a list of friends, messages, movies, and the like</li>
                                                                                                    <li>Processing a set of DOM nodes (HTML, frontend development)</li>
                                                                                                </ul>
                                                                                                <p>Every list we encounter in a real or virtual world is, from a programmer's point of view, a collection of items. Python uses lists, a data structure that allows you to work with the set as a single entity:</p>
                                                                                                [[CODE:0]]
                                                                                                <p>Unlike primitive data types, lists can change in Python, both the contents and the size of the list itself. It affects how we handle them, adding more power but also responsibility.</p>
                                                                                                <p>With lists, we can solve the problem in many different ways. Only some will be good, while the rest will be inefficient and difficult to debug and analyze.</p>
                                                                                                <p>It is why we devote an entire course to lists, not just a few lessons.</p>
                                                                                                <p>This course covers many cases where programmers commonly use lists. The knowledge gained in this course will be the foundation for all future learning.</p>
                                                                                                <p>The main topics of this course are</p>
                                                                                                <ul>
                                                                                                    <li>Operations on lists</li>
                                                                                                    <li>Working with lists in loops</li>
                                                                                                    <li>Working with nested lists using nested loops</li>
                                                                                                    <li>Sorting lists</li>
                                                                                                    <li>Working with strings through lists</li>
                                                                                                </ul>
                                                                                                <p>In addition to lists, we'll also cover algorithms and data structures in this course. We will discuss the concept of algorithmic complexity and learn how to implement some basic algorithms, which will help in job interviews. Knowledge of these topics is essential to writing effective code.</p>
                                                                                            $$,
                                                                                            '[
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "// Defining a friends list\nfriends = [''petya'', ''vasya'', ''ivan'']",
                                                                                                "language": "python",
                                                                                                "position": 1
                                                                                              }
                                                                                            ]'::jsonb
                                                                                        ),
                                                                                        (
                                                                                            3,
                                                                                            2,
                                                                                            'Lists',
                                                                                            'Introducing lists and their features',
                                                                                            $$
                                                                                                <p>A list is a data structure designed to store ordered sets of elements.</p>
                                                                                                <p>In this case, the word "ordered" means we store the structural elements in the order in which we added them.</p>
                                                                                                <p>Items in the list are indexed, meaning they have a number that tells you their position. The numbering always starts from zero and is always monotonous. It means that each subsequent item index increases by one — this numbering is also called continuous numbering. If you delete items at the beginning or middle of the list or insert items in the middle, Python recalculates the indexes and preserves the above numbering properties.</p>
                                                                                                <h2>Immutability and modifiability</h2>
                                                                                                <p>We already know that concatenating two lines results in a new line, and adding two numbers results in a number.</p>
                                                                                                <p>Tuples, which we looked at earlier:</p>
                                                                                                <ul>
                                                                                                    <li>Allow you to group the elements and then break the group into components</li>
                                                                                                    <li>Can be combined into a larger tuple</li>
                                                                                                    <li>Don't allow you to change the composition of the elements — instead, create new tuples from the elements of the old ones</li>
                                                                                                </ul>
                                                                                                <p>Earlier, we discussed many data types and structures, such as strings, numbers, Boolean values, and tuples. They are immutable, meaning that once we create them, they don't change.</p>
                                                                                                <p>Lists are the first data structure that can change its contents on the fly. These objects are called changeable objects. However, some list operations still create new lists based on old lists.</p>
                                                                                            $$,
                                                                                            null
                                                                                        ),
                                                                                        (
                                                                                            3,
                                                                                            3,
                                                                                            'Creating lists and adding items',
                                                                                            'Learning how to create lists and add items',
                                                                                            $$
                                                                                                <p>Python has built-in lists, so the language provides a special syntax for creating lists called list literals. For example, <span>[1, 2]</span> is a list literal.</p>
                                                                                                <p>The tuples described earlier are also built into the language and created with their literals. This expression in parentheses, <span>("foo", 42)</span>, is a tuple literal.</p>
                                                                                                <p>We can create multiple lists, including an empty one:</p>
                                                                                                [[CODE:0]]
                                                                                                <p>So far, everything looks like tuples, except that the parentheses are square instead of round.</p>
                                                                                                <p>But in the last lesson, we mentioned that lists are modifiable. They can change over time. Let's learn how to modify lists by adding elements to the end:</p>
                                                                                                [[CODE:1]]
                                                                                                <h2>Lists as objects</h2>
                                                                                                <p>The above example demonstrates a new type of syntax, the object method call.</p>
                                                                                                <p>Objects are entities that can store data, including other objects. Also, objects know how to handle the data in them. Programmers say that objects have their behavior.</p>
                                                                                                <p>The behavior of an object is to provide methods — functions related to the owner object in some way. A method call is similar to a function call. The only difference is that it looks like <span>object.method(...)</span>, which means we can always see which object's method we call.</p>
                                                                                                <p>We will discuss Object Oriented Programming in a different course. For now, it is enough to know that methods are like functions — they can modify objects or return information about them.</p>
                                                                                                <h2>The <span>append</span> and <span>extend</span> methods</h2>
                                                                                                <p>So in the code above, list <span>l</span> is the list object, and <span>append</span> and <span>extend</span> are methods of the list object:</p>
                                                                                                <ul>
                                                                                                    <li>The append adds an element to its list</li>
                                                                                                    <li>The extend adds all elements of the argument list to its list</li>
                                                                                                </ul>
                                                                                                <p>Both methods add items to the end of the list. And both of them return <span>None</span>, meaning they have no result we can use. The point of calling these methods is to modify the associated object.</p>
                                                                                                <p>Beginners often make this mistake:</p>
                                                                                                [[CODE:2]]
                                                                                                <p></p>
                                                                                                <p>Here, on line 2, the hypothetical author wanted to store the expanded list in the variable <span>l</span>, but the variable ended up with the value <span>None</span> which was returned by <span>append</span>.</p>
                                                                                                <p>Note that a method can always return <span>None</span> and change its associated object.</p>
                                                                                            $$,
                                                                                            '[
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "numbers = [1, 2, 3]\nstrings = [\"foo\", \"bar\"]\nbooleans = [True, False]\nempty_list = []",
                                                                                                "language": "python",
                                                                                                "position": 1
                                                                                              },
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "l = [1, 2, 3]\nl.append(4)\nl.append(5)\nprint(l)  # => [1, 2, 3, 4, 5]\nl.extend([6, 7, 8])\nprint(l)  # => [1, 2, 3, 4, 5, 6, 7, 8]",
                                                                                                "language": "python",
                                                                                                "position": 2
                                                                                              },
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "l = [1]\nl = l.append(2)\nprint(l) # None\n# Where is the list?",
                                                                                                "language": "python",
                                                                                                "position": 3
                                                                                              }
                                                                                            ]'::jsonb
                                                                                        ),
                                                                                        (
                                                                                            3,
                                                                                            4,
                                                                                            'Links',
                                                                                            'Introducing the concept of links and the mechanism of their work',
                                                                                            $$
                                                                                                <p>While we worked with immutables, we passed their values to functions and stored them in variables.</p>
                                                                                                <p>It's different with mutable objects. It's time to learn that everything in Python is passed by reference.</p>
                                                                                                <p>What is a reference? We're going to find out in this lesson. We will start with references' big brothers — addresses and pointers.</p>
                                                                                                <h2>References and memory management</h2>
                                                                                                <p>The computer's main memory stores all the data needed for a program. To access a specific part of memory, you need to know the address of that part.</p>
                                                                                                <p>In languages with manual memory management, you must constantly monitor that memory at that address is allocated and not yet released. In these languages, the programmer explicitly asks the operating system for the memory needed.</p>
                                                                                                <p>The operating system responds to the request by allocating an area of shared RAM, assigning that block to the requestor, and returning a pointer that is essentially an address. After receiving it, the programmer can store something in the allocated memory.</p>
                                                                                                <p>When we finish the work, we should free the allocated areas. It means we tell the operating system that the memory is free for something else. If the pointer points to a memory area that hasn't been allocated yet or has already been free, the program will end with an error.</p>
                                                                                                <p>Python is a language with automatic memory management. Whenever the programmer needs to create a value, the runtime automatically allocates the required amount of memory. We store the value in this memory and get the reference to the stored value in return results.</p>
                                                                                                <p>Once the data is no longer in use, Python automatically frees the memory. Thus, references play the same role as pointers. But it's always safe to use them because they can't point to an area of memory that's not ready for use.</p>
                                                                                                <p>Also, Python programmers don't have to fetch and fill memory separately because Python places the data in memory at the same runtime.</p>
                                                                                                <h2>How references work</h2>
                                                                                                <p>When we create a value, we get a reference to it from the runtime.</p>
                                                                                                <p>There can be any number of references to the same value at any time. Python saves work all the time by:</p>
                                                                                                <ul>
                                                                                                    <li>Passing any value by reference</li>
                                                                                                    <li>Creating new references to existing data</li>
                                                                                                </ul>
                                                                                                <p>Even variables are just names attached to references. And when you call a function and pass arguments to it, you don't pass the values themselves, only references to them.</p>
                                                                                                <p>When the function ends, Python removes the unnecessary references. Once the last reference to a value disappears, the runtime recognizes it is no longer needed and removes it from memory, freeing the space.</p>
                                                                                                <p>Python does it by a runtime mechanism called a reference counter. Using reference counting allows Python to save a fair amount of memory when passing long strings or large numbers between different parts of the program: you don't have to copy data from place to place, as some other languages do.</p>
                                                                                                <p>But it is a double-edged sword. If we pass a modifiable object reference to some code, we can't prevent that code from modifying the object at runtime. In some cases, this makes the code harder to debug and harder to read. You should always be aware of this feature of Python.</p>
                                                                                            $$,
                                                                                            null
                                                                                        ),
                                                                                        (
                                                                                            3,
                                                                                            5,
                                                                                            'References and variability',
                                                                                            'Finding out how variability manifests in the value transfers',
                                                                                            $$
                                                                                                <p>In the last lesson, we introduced the concept of references and mentioned that everything is always passed by reference in Python. Let us experiment with a list as our first known mutable object.</p>
                                                                                                <p>But first, we need to learn about tools for our experiments: the id function and the is operator.</p>
                                                                                                <h2>The <span>id</span> and <span>is</span> methods</h2>
                                                                                                <p>If you refer to the function description (<span>help(id)</span>), the documentation will tell you:</p>
                                                                                                [[CODE:0]]
                                                                                                <p>The id function returns a unique identifier of an object passed to it as an argument and by reference.</p>
                                                                                                <p>The identifier is an ordinary number. But each object has a unique identifier.</p>
                                                                                                <p>It means that any two objects will always have different identifiers. Python does not preserve identifiers from one run to the next, so the object-identifier relationship is unbreakable within one run.</p>
                                                                                                <p>That is why identifiers help keep track of object references we pass between different code sections.</p>
                                                                                                <p>The object identifier will be the same regardless of what reference we use to access the object:</p>
                                                                                                [[CODE:1]]
                                                                                                <p>When we assign a value of one variable to another, we create a new named reference to the original value. Therefore <span>id(a)</span> and <span>id(b)</span> return the same result.</p>
                                                                                                <p>The is operator checks if the identifiers of its operands are equal. In this example, both variables refer to the same object, so checking <span>a is b</span> gives <span>True</span>.</p>
                                                                                                <p>Checking for equality of identifiers is very quick. And it is convenient to use when we deal with so-called single objects.</p>
                                                                                                <p>The most famous Python singles are:</p>
                                                                                                <ul>
                                                                                                    <li><span>True</span></li>
                                                                                                    <li><span>False</span></li>
                                                                                                    <li><span>None</span></li>
                                                                                                </ul>
                                                                                                <p>So the <span>None</span> check is usually written like this:</p>
                                                                                                [[CODE:2]]
                                                                                                <h2>Lists, tuples, and references</h2>
                                                                                                <p>Check out this example:</p>
                                                                                                [[CODE:3]]
                                                                                                <p>Here we see that lists a and b have been changed. There are not two lists but two references to one list. We will continue:</p>
                                                                                                [[CODE:4]]
                                                                                                <p>As you may have guessed, the list stores two references to the same object, which is also mutable. It is the subtlety of working with the references. When we get them from somewhere, we cannot be sure that the object will not change over time without our involvement.</p>
                                                                                                <p>Remember when we said that a tuple cannot change? Let us look at this example:</p>
                                                                                                [[CODE:5]]
                                                                                                <p>As you can see, the value in the tuple has changed. It happened because the tuple contents are references to values. These references cannot change, but the objects connected to them may well change.</p>
                                                                                                <p>Let us observe lists and tuples created using the special syntax: multiplication of a list or tuple by a number.</p>
                                                                                                <p>If we multiply a list or tuple by n, we get a new collection of the corresponding type.</p>
                                                                                                <p>It consists of n repetitions of elements of the original collection. Here are a few examples:</p>
                                                                                                [[CODE:6]]
                                                                                                <p>Do not forget that collections are always collections of references. You can guess how these duplicated collections will behave when the items being modified change. Take a look:</p>
                                                                                                [[CODE:7]]
                                                                                                <h2>References and assignments</h2>
                                                                                                <p>We saw that you can add more than one reference to a single object to a list. And that variables are the same as references, just named.</p>
                                                                                                <p>But what happens to variables and list items when you assign them? Let us have a look:</p>
                                                                                                [[CODE:8]]
                                                                                                <p>This example shows that the variable name is not strictly related to the reference to the value.</p>
                                                                                                <p>Assigning a variable (<span>+=</span> is a type of assignment) can change one reference to another. This property is also characteristic of list items:</p>
                                                                                                [[CODE:9]]
                                                                                                <p>Here the first two items in the list refer to the same value. However, when you assign a new value to the first element, you break the link between the original value and the element.</p>
                                                                                            $$,
                                                                                            '[
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "id(obj, /)\n    Return the identity of an object.\n\n    It is guaranteed to be unique among simultaneously existing objects.",
                                                                                                "language": "python",
                                                                                                "position": 1
                                                                                              },
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "a = \"some string\"\nb = a\nid(a)  # 139739990935280\nid(b)  # 139739990935280\nprint(a is b)  # => True",
                                                                                                "language": "python",
                                                                                                "position": 2
                                                                                              },
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "...\nif foo is None:\n    ...",
                                                                                                "language": "python",
                                                                                                "position": 3
                                                                                              },
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "a = [1, 2, 3]\nb = a\na.append(4)\nprint(b)  # => [1, 2, 3, 4]",
                                                                                                "language": "python",
                                                                                                "position": 4
                                                                                              },
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "a = []\nl = [a, a]\na.append(1)\nprint(l)  # => [[1], [1]]",
                                                                                                "language": "python",
                                                                                                "position": 5
                                                                                              },
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "a = []\npair = (a, a)\npair[0].append(1)\npair[1].append(2)\nprint(pair)  # => ([1, 2], [1, 2])",
                                                                                                "language": "python",
                                                                                                "position": 6
                                                                                              },
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "print([1, 2, 3] * 3)  # => [1, 2, 3, 1, 2, 3, 1, 2, 3]\nprint((''foo'', ''bar'') * 2)  # => (''foo'', ''bar'', ''foo'', ''bar'')\nprint([[]] * 5)  # => [[], [], [], [], []]\nprint(((),) * 5)  # => ((), (), (), (), ())",
                                                                                                "language": "python",
                                                                                                "position": 7
                                                                                              },
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "t = ([], [], []) * 3\nprint(t)  # => ([], [], [], [], [], [], [], [], [])\nt[0].append(42)\nt[1].append(0)\nprint(t)  # => ([42], [0], [], [42], [0], [], [42], [0], [])",
                                                                                                "language": "python",
                                                                                                "position": 8
                                                                                              },
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "a = \"foo\"\nid(a)  # 139739990954536\na += \"bar\"\nprint(a)  # => ''foobar''\nid(a)  # 139739952783688",
                                                                                                "language": "python",
                                                                                                "position": 9
                                                                                              },
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "a = \"foo\"\nl = [a, a]\nprint(l[0] is l[1])  # => True\nl[0] += \"bar\"\nprint(l)  # => [''foobar'', ''foo'']\nprint(l[0] is l[1])  # => False",
                                                                                                "language": "python",
                                                                                                "position": 9
                                                                                              }
                                                                                            ]'::jsonb
                                                                                        );