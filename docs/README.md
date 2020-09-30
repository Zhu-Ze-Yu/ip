# User Guide

## Features 
Message in round brackets are explanations, no need to type in when using Duke.

Use the # symbol to indicate number
1. `todo xxxx(task_name)`
1. `deadline xxxx(task_name) /by yyyy-mm-dd(date)`
1. `event xxxx(task_name] /at yyyy-mm-dd(date)`
1. `done #(task_index)`
1. `undone #(task_index)`
1. `delete #(task_index)`
1. `find keyword`
1. `clear`
1. `group`
1. `help`
1. `list`
1. `restore`
1. `bye`

## Usage

### `todo` - Add Todo tasks into the list
This command adds Todo task into the list

Example of usage:

`todo xxxx(task_name)` 

Expected outcome:

`Got it. I've added this task:`

`[T][✘] xxxx(task_name)`

`Now you have # tasks in the list.`

### `deadline` - Add Deadline tasks into the list
This command adds Deadline task into the list, 
accept dates in a format such as yyyy-mm-dd format (e.g., 2020-10-15) 
and print in a different format such as MMM dd yyyy e.g., (Oct 15 2020).

Example of usage:

`deadline xxxx(task_name) /by yyyy-mm-dd(date)` 

Expected outcome:

`Got it. I've added this task:`

`[D][✘] xxxx(task_name) (by: MMM dd yyyy(date))`

`Now you have # tasks in the list.`

### `event` - Add Event tasks into the list
This command adds Event task into the list, 
accept dates in a format such as yyyy-mm-dd format (e.g., 2020-10-15) 
and print in a different format such as MMM dd yyyy e.g., (Oct 15 2020).

Example of usage:

`event xxxx(task_name) /at yyyy-mm-dd(date)` 

Expected outcome:

`Got it. I've added this task:`

`[E][✘] xxxx(task_name) (at: MMM dd yyyy(date))`

`Now you have # tasks in the list.`

### `done` - mark one task as done
This command marks the task at position `#(task_index)` as done

Example of usage:

`done #(task_index)` 

Expected outcome:

`Nice! I've marked this task as done:`

`[T][✓] xxxx(task_name)`

### `undone` - mark one task as undone
This command marks the task at position `#(task_index)` as undone

Example of usage:

`undone #(task_index)` 

Expected outcome:

`Noted! I've marked this task as undone:`

`[T][✘] xxxx(task_name)`

### `delete` - delete the task user want to remove
This command deletes the task at position `#(task_index)`

Example of usage:

`delete #(task_index)` 

Expected outcome:

`Noted. I've removed this task:`

`[T][✘] xxxx(task_name)`

`Now you have # tasks in the list.`

### `find` - find the task with the keyword
This command finds tasks that contain `keyword`

Example of usage:

`find keyword` 

Expected outcome:

`Here are the matching tasks in your list:`

`1.[T][✘] xxxx(task_name contains keyword)`

`2.[T][✘] xxxx(task_name contains keyword)`

`... (omit part)`

### `clear` - clear the taskslist
This command remove all tasks in the list

Example of usage:

`clear` 

Expected outcome:

`Good job! You have finished all tasks in the list`

### `group` - group tasks in the list
This command group all tasks in Todo-Deadline-Event order.
In other words, tasks in the list will be rearranged, 
and Todo tasks in the first part, 
Deadline tasks in the second part,
Event tasks in the last part.

Example of usage:

`group` 

Expected outcome:

`Got it. I have group tasks in the Todo-Deadline-Event order`

Expected outcome after `group` and `list`:

`Here are the tasks in your list:`

`1.[T][✘] xxxx(task_name)`

`2.[T][✘] xxxx(task_name)`

`3.[D][✘] xxxx(task_name) (by: MMM dd yyyy(date))`

`4.[D][✘] xxxx(task_name) (by: MMM dd yyyy(date))`

`5.[E][✘] xxxx(task_name) (at: MMM dd yyyy(date))`

`6.[E][✘] xxxx(task_name) (at: MMM dd yyyy(date))`

### `help` - print help list
This command prints the help list

Example of usage:

`help` 

Expected outcome:

`Here is the Help List (words in round brackets are explanations no need to type)`

`1.  add command: you can add three types of tasks by following these format`

`i)   Todo task: todo xxxx(task description)`

`ii)  Deadline task: deadline xxxx(task description) /by yyyy-mm-dd`

`iii) Event task: event xxxx(task description) /at yyyy-mm-dd`

`... (omit part)`

`10. restore command: you can list all removed tasks in the backupList by following this format`

`e.g. restore`

### `list` - list tasks in the list
This command list all tasks in the list

Example of usage:

`list` 

Expected outcome:

`Here are the tasks in your list:`

`1.[T][✘] xxxx(task_name)`

`2.[D][✘] xxxx(task_name) (by: MMM dd yyyy(date))`

`3.[E][✘] xxxx(task_name) (at: MMM dd yyyy(date))`

### `restore` - list deleted tasks 
This command list all tasks in the backupList which contains all deleted tasks,
this command will not store any deleted tasks into list

Example of usage:

`restore` 

Expected outcome:

`Here are all tasks you have removed:`

`1.[T][✘] xxxx(task_name)`

`2.[D][✘] xxxx(task_name) (by: MMM dd yyyy(date))`

`3.[E][✘] xxxx(task_name) (at: MMM dd yyyy(date))`

### `bye` - terminate the program
This command prints bye message and terminate the program

Example of usage:

`bye` 

Expected outcome:

`Bye. Hope to see you again soon!`