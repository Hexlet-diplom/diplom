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
             'Git fundamentals',          -- name
             'Version control made simple.',  -- subtitle
             '/assets/img/courses/Git.png',  -- image_url
             'Git (a version control kit) is one of a developer''s many important tools. Regardless of the area of development you choose, all programmers work with source code for projects that are constantly updated, changed, and deleted. In this free beginner''s course on Git, you''ll learn how to manage this process properly, how to deal with bugs, how to examine your change history, and how to maintain collaborative development.',
             1080,                          -- total_duration in minutes
             1834,                            -- enrollment_count
             8.13,                          -- rating
             'OPENED'                      -- status
         );

INSERT INTO course_objectives (course_id, objective, order_index) VALUES
                                                                      (2, 'Develop according to the latest engineering practices', 1),
                                                                      (2, 'Effectively manage source code, add it to the public repository, and analyze and modify history', 2),
                                                                      (2, 'Work with GitHub and contribute to open-source projects', 3);

INSERT INTO lessons (course_id, order_number, name, description, content, media) VALUES (
                                                                                            2,
                                                                                            1,
                                                                                            'Introduction',
                                                                                            'Why developers use Git and what problems it solves in modern development.',
                                                                                            $$
                                                                                                [[IMAGE:0]]
                                                                                                <p>Regardless of the chosen language or area of development, code written by a programmer is no more than plain text written in multiple files. These files are regularly added, removed, and changed. And in the process of working on them, a lot of questions arise:</p>
                                                                                                <ul>
                                                                                                    <li>How do I not lose the source code?</li>
                                                                                                    <li>How do I remember what I changed recently and where?</li>
                                                                                                    <li>How do I protect myself from accidental changes and deletions?</li>
                                                                                                    <li>How do I undo changes if they were incorrect?</li>
                                                                                                    <li>How can two or more programmers work on the code simultaneously?</li>
                                                                                                </ul>
                                                                                                <p>Imagine your project consists of hundreds of files and tens of thousands of lines of code. You do a task, and in the process you change 15 files and 300 lines of code, and suddenly it becomes clear that the task is no longer up to date. At this point, you need to return the source code to the state it was in before the changes. Another example - while working on your code, you realized that you made a change to the working project/site. A new task in a non-working state cannot be uploaded to the site, which means you must correct the version of the code that is from before you started working on the new task.</p>
                                                                                                <p>Collaborative development is its own headache too. If two programmers are working on tasks that require changes to the code in the same files, how can they do their work in such a way that they don't damage or overwrite the other developer's changes?</p>
                                                                                                <p>To solve all these and many other problems, programmers use the Git version control system. This is a special program that allows you to control changes in project files for both individual and collaborative code development. The basic features of Git include:</p>
                                                                                                <ul>
                                                                                                    <li>Returning to any version of the code from the past</li>
                                                                                                    <li>View the history of changes and restore any data</li>
                                                                                                    <li>Developers can work together without fear of losing data or messing up someone else's work</li>
                                                                                                </ul>
                                                                                                <p>In today's world, Git has become the universal tool with which almost any development project starts. The entire current ecosystem of tools is built around Git (Git is integrated into all code editors) and the online services that integrate with it, such as GitHub or GitLab. As a rule, project code is stored on these sites, providing the team with both shared access and a copy in case of computer failure.</p>
                                                                                                <p>In this course, we will install Git and learn how to use its basic commands. Let's create our first repository and practice typical development tasks, such as adding, modifying or deleting files, and analyzing the project's history. Let's get acquainted with online code storage services and the open source movement.</p>
                                                                                                <p>The main source for mastering Git, besides the documentation, is the Pro Git book.</p>
                                                                                                <p>By the way, this course was also made in Git.</p>
                                                                                                <h2>Preparation</h2>
                                                                                                <p>We recommend that you take courses on Hexlet in a certain order. This is important. The knowledge gained in one course helps you understand and master others. Git is run from the command line. And before you dive into Git, take or refresh your memory with a Command Line Fundamentals course. Then you won't be distracted from the current material and you'll get up to speed more quickly.</p>
                                                                                            $$,
                                                                                            '[
                                                                                              {
                                                                                                "type": "IMAGE",
                                                                                                "url": "/assets/img/lessons/git.png",
                                                                                                "alt": "git",
                                                                                                "caption": "git",
                                                                                                "content": "",
                                                                                                "language": "",
                                                                                                "position": 1
                                                                                              }
                                                                                            ]'::jsonb
                                                                                        ),
                                                                                        (
                                                                                            2,
                                                                                            2,
                                                                                            'Installation and Setup',
                                                                                            'Install Git, VSCode, and set up your GitHub account on any OS.',
                                                                                            $$
                                                                                                <p>Git is a fairly complex system with a lot of commands and ways of working. So complex that even experienced developers often have to refer to the documentation and don't know many of its features. On the other hand, git has a kernel, which is sufficient in the vast majority of cases. It affects not only the commands used to work with it, but also some of the ideas that underlie it.</p>
                                                                                                <p>That's what we're going to deal with in this course. However, you should always keep one detail in mind. Git is something that can only be understood by constantly practicing and making mistakes. No one has yet learned how to use git purely by reading the documentation or watching a course.</p>
                                                                                                <p>The goal of this course is to give you the basics and show some primary directions, beyond that, it's all about experimentation. As a rule, git is studied extensively in the first weeks and months after starting a new job, and the process is far from painless. This is because it is almost impossible to simulate the problems that arise in collaborative development during training. In this case, developers who are actively involved in working on open source projects during training are the ones that win. This is the most reliable way to really understand and learn how to master git.</p>
                                                                                                <h2>Installation</h2>
                                                                                                <p>The way of installing git differs between operating systems. The easiest way to do this is on MacOS and Ubuntu. They allow you to install git through package managers:</p>
                                                                                                [[CODE:0]]
                                                                                                <p>On Windows, there are lots of options for installing git. The main one is by installing Ubuntu on Windows and then git. This setup may take time, but it's worth it. Ubuntu on Windows adds an environment for developers to work as efficiently and comfortably as possible. In addition, this environment is very similar to the environment in which your projects' code will run.</p>
                                                                                                <p>If your version of Windows does not support the options above, there are several alternatives:</p>
                                                                                                <ol>
                                                                                                    <li>The boldest is to install Ubuntu as the main system. It's very simple</li>
                                                                                                    <li>Set up Ubuntu in a virtual machine. It's the safest way, but it requires a fairly powerful computer</li>
                                                                                                    <li>Use Git For Windows</li>
                                                                                                </ol>
                                                                                                <p>After installing git, you should go to the command terminal and check that it works:</p>
                                                                                                [[CODE:1]]
                                                                                                <p>If you have an older version of git installed and are running Ubuntu or Ubuntu on Windows, try the following commands:</p>
                                                                                                [[CODE:2]]
                                                                                                <p>After installing git, you need to set it up. It needs to know your name and email address to work. This data will be inserted into the history of changes. This is the only way to find out who did what in the project:</p>
                                                                                                [[CODE:3]]
                                                                                                <h2>Installing the editor</h2>
                                                                                                <p>For further work, you need a special code editor. We recommend VSCode. It's currently the most popular (free!) editor, and not only does it have a wide range of features, but also an extensive system of plug-ins, allowing you to buff up your editor significantly.</p>
                                                                                                <h2>GitHub account</h2>
                                                                                                <p>You will also need to create an account on GitHub, which is a free (for individuals) service where most companies and developers store their projects. It is also used by recruiters to find active and outstanding programmers. They look at the code and assess how popular and complex it is. A GitHub account with high project activity (their own or others') is one of the key elements in employment.</p>
                                                                                                <p>After creating the account, you need to perform one more important operation - adding ssh keys to github.com. In simple terms, the keys allow repositories to work with GitHub without you having to constantly enter your login and password when synchronizing the local and remote repositories (which are on GitHub).</p>
                                                                                                <p>This task is performed in two stages. First, you need to generate ssh keys and then add one of them (the public one) to your GitHub settings. Detailed instructions on how to create ssh keys are available on the website. In a nutshel</p>
                                                                                                [[CODE:4]]
                                                                                                <p>Once the ssh keys have been created and added to the system, you can start integrating with Github. This procedure is described in detail in the documentation. In a nutshell:</p>
                                                                                                <ol>
                                                                                                    <li>
                                                                                                        Print the contents of the file ~/.ssh/id_rsa.pub and copy it:
                                                                                                        [[CODE:5]]
                                                                                                    </li>
                                                                                                    <li>Add the ssh key to your Github account. When you add it, you will be asked to name a key. Write something like home</li>
                                                                                                </ol>
                                                                                            $$,
                                                                                            '[
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "# MacOS\n# https://brew.sh/\nbrew install git\n\n# Ubuntu\nsudo apt update # just in case, let''s look at the new versions\nsudo apt install git-all",
                                                                                                "language": "bash",
                                                                                                "position": 1
                                                                                              },
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "git --version\n\ngit version 2.28.0\n# Your version may be different, but it is important that it is at least 2.23.0",
                                                                                                "language": "bash",
                                                                                                "position": 2
                                                                                              },
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "sudo apt install software-properties-common\nsudo add-apt-repository ppa:git-core/ppa\nsudo apt update\nsudo apt install git",
                                                                                                "language": "bash",
                                                                                                "position": 3
                                                                                              },
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "# It runs from any directory\ngit config --global user.name \"<name surname>\"\ngit config --global user.email \"<your email>\"",
                                                                                                "language": "bash",
                                                                                                "position": 4
                                                                                              },
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "# Creating ssh keys\nssh-keygen -t rsa -b 4096 -C \"your_email@example.com\"\n# There will be some questions next. All questions must be answered by pressing Enter\n\n# Running the ssh agent that monitors the keys\neval \"$(ssh-agent -s)\"\n\n# Adding a new ssh key to the agent\nssh-add ~/.ssh/id_rsa",
                                                                                                "language": "bash",
                                                                                                "position": 5
                                                                                              },
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "cat ~/.ssh/id_rsa.pub",
                                                                                                "language": "bash",
                                                                                                "position": 6
                                                                                              }
                                                                                            ]'::jsonb
                                                                                        ),
                                                                                        (
                                                                                            2,
                                                                                            3,
                                                                                            'Workflow',
                                                                                            'Create a repo, add files, and make your first commit with Git.',
                                                                                            $$
                                                                                                <p>Before we dive into the details, let's have a glance at the process from creating a project in git to starting to track changes. Then, in the following lessons, we'll talk more about each step. In the process, we'll learn a lot of new terms and commands that are needed to understand git.</p>
                                                                                                <p>Git can only track project files when they are placed under version control. To do this, go to the project directory and run the initialization command, <span>git init</span>. It can be either a new project or an existing one. This does not change the initialization process.</p>
                                                                                                [[CODE:0]]
                                                                                                <p>The <span>git init</span> command creates a repository, a .git directory that contains all the files git needs.</p>
                                                                                                <p>You can use the <span>git status</span> command to view the status of the repository:</p>
                                                                                                [[CODE:1]]
                                                                                                <p>This output shows that the repository is empty (No commits yet) and there is nothing to add to it because there are no new or changed files. Let's try to add some files:</p>
                                                                                                [[CODE:2]]
                                                                                                <p>Now look at the status again:</p>
                                                                                                [[CODE:3]]
                                                                                                <p>Git saw that there were new files in the project that it knows nothing about. They are marked as untracked files. Git does not keep track of changes to these files because they have not been added to the repository. Adding to the repository takes two steps. The first step is to run the file preparation command <span>git add <path to file></span>:</p>
                                                                                                [[CODE:4]]
                                                                                                <p>Let's see what happened:</p>
                                                                                                [[CODE:5]]
                                                                                                <p>The README.md file is now in the "prepared for commit" state or, in other words, the files are indexed. The term <span>commit</span> refers to the final addition of a file to the repository. Git will remember the file forever and keep track of all subsequent changes.</p>
                                                                                                <p>The commit command is an operation that takes all prepared changes (they can include any number of files) and sends them to the repository as a whole. Here's how it's executed:</p>
                                                                                                [[CODE:6]]
                                                                                                <p>The <span>-m</span> flag stands for message, i.e., a description of the commit. You can also commit without it, but then an editor will open in which you must enter a description of the commit. We recommend making meaningful descriptions - it's a good tone. An example of a commit naming convention can be found in the appendix to this lesson.</p>
                                                                                                [[IMAGE:7]]
                                                                                                <p>You might ask why it's so complicated, why do we need a separate index (a staging area where files go after <span>git add</span>), and why can't we add all the modified files to the commit at once? Oddly enough, this process was created for the convenience of programmers. The fact is that during development, many files may be added and changed. But that doesn't mean we want to add all these changes in one commit.</p>
                                                                                                <p>From a semantic point of view, a commit is a coherent and logically completed change within a project. It can be something very small, such as correcting a typo in a single file, and sometimes something large, such as when a new function is introduced. The main thing about a commit is its atomicity, i.e., it must perform exactly one task.</p>
                                                                                                <p>The README.md file is now inside the repository. You can verify this by running the <span>git status</span> command:</p>
                                                                                                [[CODE:8]]
                                                                                                <p>git status does not output files that have been added to the repository and do not contain changes. The README.md file itself is located inside the hexlet-git directory.</p>
                                                                                            $$,
                                                                                            '[
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "# Create a new project\nmkdir hexlet-git\n\n# Navigate to the created directory\ncd hexlet-git\n\n# Perform initialization\ngit init\n\nInitialized empty git repository in /private/tmp/hexlet-git/.git/",
                                                                                                "language": "bash",
                                                                                                "position": 1
                                                                                              },
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "git status\n\nOn branch main\nNo commits yet\nnothing to commit (create/copy files and use \"git add\" to track)",
                                                                                                "language": "bash",
                                                                                                "position": 2
                                                                                              },
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "# Create a README.md file with a line of text\necho ''Hello, Hexlet!'' > README.md\necho ''Haskell Curry'' > PEOPLE.md",
                                                                                                "language": "bash",
                                                                                                "position": 3
                                                                                              },
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "git status\n\n# Part of the output has been removed\nUntracked files:\n(use \"git add <file>...\" to include in what will be committed)\nPEOPLE.md\nREADME.md",
                                                                                                "language": "bash",
                                                                                                "position": 4
                                                                                              },
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "# For each new or modified file\ngit add README.md",
                                                                                                "language": "bash",
                                                                                                "position": 5
                                                                                              },
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "git status\n\nChanges to be committed:\n  (use \"git rm --cached <file>...\" to unstage)\n    new file:   README.md\n\nUntracked files:\n   (use \"git add <file>...\" to include in what will be committed)\n    PEOPLE.md",
                                                                                                "language": "bash",
                                                                                                "position": 6
                                                                                              },
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "git commit -m ''add README.md''\n\n[main (root-commit) 3c5d976] add README.md\n1 file changed, 1 insertion(+)\ncreate mode 100644 README.md",
                                                                                                "language": "bash",
                                                                                                "position": 7
                                                                                              },
                                                                                              {
                                                                                                "type": "IMAGE",
                                                                                                "url": "/assets/img/lessons/Git-workflow.png",
                                                                                                "alt": "Git workflow",
                                                                                                "caption": "Git workflow",
                                                                                                "content": "",
                                                                                                "language": "",
                                                                                                "position": 8
                                                                                              },
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "git status\n\nUntracked files:\n  (use \"git add <file>...\" to include in what will be committed)\n    PEOPLE.md",
                                                                                                "language": "bash",
                                                                                                "position": 9
                                                                                              }
                                                                                            ]'::jsonb
                                                                                        ),
                                                                                        (
                                                                                            2,
                                                                                            4,
                                                                                            'Integration with GitHub',
                                                                                            'Connect a local repo to GitHub and learn how to clone one.',
                                                                                            $$
                                                                                                <p>At the moment, we have a repository with two commits. The contents of the hexlet-git directory look like this:</p>
                                                                                                [[CODE:0]]
                                                                                                <p>Before we continue experimenting, let's add our repository to github.com. A saved repository can be retrieved at any time to continue working in it from the last commit you added to it. This is useful in case we accidentally delete or change the local repository in such a way that it becomes impossible to work with it.</p>
                                                                                                <ol>
                                                                                                    <li>Create a repository on GitHub. Call it hexlet-git. It's important to create an empty repository, so do not check the boxes that add files</li>
                                                                                                    <li>
                                                                                                        <p>On the repository page you will see ready-made commands to connect the repository you created on GitHub to an existing repository on your computer</p>
                                                                                                        [[IMAGE:1]]
                                                                                                        <p>Follow these steps</p>
                                                                                                        [[CODE:2]]
                                                                                                    </li>
                                                                                                    <li>Update the repository page at github.com. Have a close look at the page's interface and the contents of the repository. Note that the .git directory is missing. We'll find out why this is so in a future lesson</li>
                                                                                                </ol>
                                                                                                <p>After this command, the repository created on github.com will "connect" to the local hexlet-git repository. You may have a couple of questions at this point. Why does it need to connect? Isn't it the same repository?</p>
                                                                                                <p>They're actually different repositories. Git is one of so-called distributed version control systems. Git doesn't have a central location where one main repository resides that all the developers work with from their computers. Every developer's git has its own full-fledged repository as well as the repository on GitHub itself. These git repositories are linked and have a common history and ability to share changes. In the example above, it's the <span>git push</span> command that sends changes to the newly created repository.</p>
                                                                                                [[IMAGE:3]]
                                                                                                <p>Right now, after running the commands above, the local and remote repositories are identical. But they will diverge all the time during the development process, and programmers must remember to synchronize changes, adding new commits into the repository and pulling commits made by other developers.</p>
                                                                                                <p>It doesn't matter what changes are made in the local repository, all the commits will go to GitHub only after the <span>git push</span> command is used. Don't forget to do it, it's not unheard of that a developer has accidentally deleted the local repository and forgets to push the changes.</p>
                                                                                                <p>Next, we will try to download the repository from GitHub as if we didn't have a local copy. To do this, delete the hexlet-git project directory from your computer.</p>
                                                                                                <h2>Cloning</h2>
                                                                                                <p>The repository created on GitHub is public. Anyone can clone it on their computer and start working with it as if it were their own personal repository. The only limitation is that you will not be able to make changes, because GitHub does not allow you to change other people's repositories directly.</p>
                                                                                                <p>Cloning is a basic operation when working with remote repositories. Projects that programmers work on can always be found on services like GitHub. To work with them, you need to clone the repository to your computer. This is done by using the <span>git clone</span> command. The full clone command is available on the repository page. To do this, click the big "Code" button, go to the SSH tab and copy the contents.</p>
                                                                                                [[CODE:4]]
                                                                                                <p>We've got an exact copy of the repository we had before we deleted the hexlet-git directory.</p>
                                                                                                <h2>Pulling changes from GitHub</h2>
                                                                                                <p>Developers don't only send changes to GitHub, but also take changes from there. More often than not, these are changes made by other project developers, but this isn't always the case. It's not uncommon that one developer works on a project from different computers, each with its own copy of the repository (this is the only way that git works). In that case, you should always run the <span>git pull --rebase</span> command before starting, which downloads new commits from an external repository and adds them to the local repository.</p>
                                                                                                <p>Articles often say that calling a git pull is enough, but this can create unnecessary merge commits that damage the change history. Working properly with git pull requires knowledge of things like branching and git rebase. They're quite complicated for newcomers and we'll look at them later when you have a little experience with git</p>
                                                                                                <h2>Summary</h2>
                                                                                                <p>Let's make a quick summary. We created a repository with several commits. This repository has been added to GitHub and can be cloned for further development. How can we benefit from git right now? We have a backup copy of the code on GitHub. At the very least, we don't need to be too scared of losing our code. Now it's easy to restore it if needed and share it with others.</p>
                                                                                                <p>It's also worth mentioning that GitHub, although the most popular, is not the only repository hosting site. Besides GitHub, Bitbucket and GitLab are quite well known. You can even put GitLab on your server and host repositories within your company, which many people do for security or money-saving reasons.</p>

                                                                                            $$,
                                                                                            '[
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "ls -a\n\n.git\nPEOPLE.md\nREADME.md",
                                                                                                "language": "bash",
                                                                                                "position": 1
                                                                                              },
                                                                                              {
                                                                                                "type": "IMAGE",
                                                                                                "url": "/assets/img/lessons/New-repository.png",
                                                                                                "alt": "New repository on GitHub",
                                                                                                "caption": "New repository on GitHub",
                                                                                                "content": "",
                                                                                                "language": "",
                                                                                                "position": 2
                                                                                              },
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "# We''ll look at these commands in more detail later\ngit branch -M main\ngit remote add origin git@github.com:<GitHub username>/hexlet-git.git\ngit push -u origin main",
                                                                                                "language": "bash",
                                                                                                "position": 3
                                                                                              },
                                                                                              {
                                                                                                "type": "IMAGE",
                                                                                                "url": "/assets/img/lessons/Workflow-with-GitHub.png",
                                                                                                "alt": "New repository on GitHub",
                                                                                                "caption": "New repository on GitHub",
                                                                                                "content": "",
                                                                                                "language": "",
                                                                                                "position": 4
                                                                                              },
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "git clone git@github.com:<GitHub username>/hexlet-git.git\ncd hexlet-git\nls -la\n\n# If this is the first time\n# Then you will probably see a message like this\nThe authenticity of host github.com cannot be established. RSA key fingerprint is SHA256: хххххххххх Are you sure you want to continue connecting (yes/no/[fingerprint])? yes Warning: Permanently added github.com (RSA) to the list of known hosts.\n# Type ''yes'' and press Enter",
                                                                                                "language": "bash",
                                                                                                "position": 5
                                                                                              }
                                                                                            ]'::jsonb
                                                                                        ),
                                                                                        (
                                                                                            2,
                                                                                            5,
                                                                                            'Working Directory',
                                                                                            'Understand the working directory and how to restore files.',
                                                                                            $$
                                                                                                <p>After cloning hexlet-git, we can see the .git directory inside and the files we added. What happens if you try to delete one of the files?</p>
                                                                                                [[CODE:0]]
                                                                                                <p>Git tells you when a file has been deleted and offers commands to restore or commit changes. It's worth stopping here and diving a bit into how git works. How does it even know that the file has been deleted?</p>
                                                                                                <p>Inside the project directory, we can see the project files on one side and the .git directory on the other. The repository is the .git directory. It stores all the information about what changes were made, as well as the changes themselves. But everything outside is the so-called working directory. These files (and directories, if any) are extracted from .git at the time of cloning. Every time we make a change to the working directory, git compares the modified files with the files inside .git, i.e., their state at the time of the last commit. If there are changes to the last committed version, git will tell us in the <span>git status</span> output.</p>
                                                                                                <p>This is very easy to see if you follow git's advice in the output above and restore the deleted file:</p>
                                                                                                [[CODE:1]]
                                                                                                <p>You can delete the entire working directory and then restore it without any problems. This helps us achieve something very important - we've made it possible to quickly restore the latest version of the code if the changes we made don't suit us anymore. Or we can commit them, if necessary:</p>
                                                                                                [[CODE:2]]
                                                                                                <p>There's something important you need to take note of. Whether we delete, add or change a file, the commit procedure does not change. After changes are made, we always perform a <span>git add</span>, which prepares the change for a commit (it doesn't add a file!), then we perform the commit itself.</p>
                                                                                                <p>By the way, git has a command called <span>git rm</span> that combines deletion and commit preparation:</p>
                                                                                                [[CODE:3]]
                                                                                            $$,
                                                                                            '[
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "rm PEOPLE.md\ngit status\n\nOn branch main\nYour branch is up to date with ''origin/main''.\n\nChanges not staged for commit:\n  (use \"git add/rm <file>...\" to update what will be committed)\n  (use \"git restore <file>...\" to discard changes in working directory)\n    deleted:    PEOPLE.md\n\nno changes added to commit (use \"git add\" and/or \"git commit -a\")",
                                                                                                "language": "bash",
                                                                                                "position": 1
                                                                                              },
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "git restore PEOPLE.md\ngit status\n\nOn branch main\nYour branch is up to date with ''origin/main''.\n\nnothing to commit, working tree clean\n\n# The file itself is back where it was on the last commit",
                                                                                                "language": "bash",
                                                                                                "position": 2
                                                                                              },
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "rm PEOPLE.md\n# Any change must be added to the index\ngit add PEOPLE.md\ngit commit -m ''remove PEOPLE.md''\n\n[main e15afd2] remove PEOPLE.md\n1 file changed, 1 deletion(-)\ndelete mode 100644 PEOPLE.md\n# This file is now gone from the working directory",
                                                                                                "language": "bash",
                                                                                                "position": 3
                                                                                              },
                                                                                              {
                                                                                                "type": "CODE",
                                                                                                "url": "",
                                                                                                "alt": "",
                                                                                                "caption": "",
                                                                                                "content": "git rm PEOPLE.md\n# it''s the same as rm + git add",
                                                                                                "language": "bash",
                                                                                                "position": 3
                                                                                              }
                                                                                            ]'::jsonb
                                                                                        );