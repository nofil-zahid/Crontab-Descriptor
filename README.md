# Crontab Descriptor

### Overview
Crontab Descriptor is a lightweight Java tool that translates crontab strings into human-readable descriptions. If you've ever wondered what a crontab expression like `* * * * *` actually means, this tool is for you. It dissects the crontab format and gives a plain language explanation of when your cron job is scheduled to run.

### Table of Contents
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Examples](#examples)

### Features
- **Full Crontab Support:** Handles all standard fields in a crontab expression (minute, hour, day of the month, month, day of the week).
- **No External Libraries:** Built purely with Java, using no third-party libraries.
- **Interactive Interface:** Command-line based input and output.
- **Error Validation:** Ensures the input crontab string is correctly formatted before processing.

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/nofil-zahid/Crontab-Descriptor.git
   ```
2. Navigate to the project directory:
   ```bash
   cd Crontab-Descriptor
   ```
3. Compile the Java files:
   ```bash
   javac Main.java
   ```

### Usage
1. Run the compiled Java program:
   ```bash
   java Main.java
   ```
2. Enter a crontab string when prompted:
   ```bash
   Enter a crontab string: * * * * *
   ```
3. The program will output a detailed explanation of the crontab string.

### Examples
- Input: `* * * * *`
  - Output: "At every minute"
  
- Input: `0 12 * * 1-5`
  - Output: "At 12:00 on every day-of-week from Monday through Friday"
 
- Input: `3 2 * 1,4,5 1-5`
  - Output: "At 02:03 on every day-of-week from Monday through Friday in January, April and May"
