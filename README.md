Wrangler Data Parser – Enhanced Version

Welcome to the Wrangler Project! This tool helps you process and transform data easily. In this version, we've added some awesome new features to handle byte sizes and time durations, making data parsing and aggregation much more flexible.

Features 
1. Byte Size Parser
We’ve added a feature to handle byte sizes like:

10KB

1.5MB

3GB

500B

This parser automatically converts the values into bytes (the standard unit for sizes in programming), making it easier to perform calculations or transformations.

2. Time Duration Parser
We’ve also added a parser for time durations, so you can handle values like:

500ms (milliseconds)

2.5s (seconds)

1min (minutes)

These values are automatically converted into milliseconds for consistency, so you can easily do calculations involving time.

New Feature: aggregate-stats Directive
What does it do?
This new feature lets you aggregate data (like summing up values) from two columns: one for byte size and one for time duration. It then stores the results in new columns.

How do you use it?
Here’s the basic syntax:


aggregate-stats :inputColumn1 :inputColumn2 outputColumn1 outputColumn2
Example
Imagine you have this table of data:

size	time
10KB	500ms
5MB    	2s
You can use the directive like this:

]
aggregate-stats :size :time total_size_bytes total_time_ms
This would sum up the total size in bytes and total time in milliseconds, and give you this output:

total_size_bytes	total_time_ms
5244928	              2500
 Testing
We’ve also written tests to make sure everything works properly, including:

Checking that byte sizes (like 1MB or 500B) convert to the correct byte value.

Ensuring time durations (like 2s or 1min) are correctly converted to milliseconds.

Testing the new aggregate-stats directive to ensure it correctly calculates the totals.

To run the tests, just use:

bash
Copy
Edit
mvn clean install
📁 Project Structure
Here’s a quick rundown of the project files:

Directives.g4 – This is where we define how the byte size and time duration parsers work.

ByteSize.java – This file handles the byte size parsing.

TimeDuration.java – This one handles the time duration parsing.

AggregateStats.java – This file contains the logic for the aggregate-stats directive.

tests/ – This folder contains the unit tests to check if everything works properly.

💡 Helpful Notes
We’ve also included a file called prompts.txt. This file has useful questions and prompts you can use if you're looking for some guidance or need help with similar tasks in the future.

Contributing
If you want to help improve this tool, feel free to fork the repo, make changes, and submit a pull request. Whether you add new features, fix bugs, or improve the documentation, all contributions are welcome!

Credits
This project was created by Arslan Salim as a part of learning and contributing to open-source. A big thanks to the open-source community and my mentors for their guidance!

