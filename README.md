# [Web-Crawler](https://hyperskill.org/projects/59)

## About

The Web is called so for a reason: all these pages are connected to many others through links, resembling a spider's
web.
It's easy to get lost and miss important links, so in this project you will write a program that might help. A Web
Crawler collects and saves links from a given page, storing them in the memory for you to access later.

### Stage 1: [Crawl through the window](https://hyperskill.org/projects/59/stages/317/implement)

#### _Summary_

Review what you know about Swing and inheritance in order to create a simple interface: a window with text.

##### _Description_

Let's start developing the crawler by creating a basic window containing a single text area that displays static
text. In further stages, we will improve out bot to behave the way we want.

- The window title should be "Web Crawler"
- Add a `JTextArea` to the window and be name it "TextArea"
- The text area should have "HTML Code?" in it and be disabled

### Stage 2: [Links, links](https://hyperskill.org/projects/59/stages/318/implement)

#### _Summary_

Feed the first link to your program so that it displays the page's content.

##### _Description_

Currently, our window contains only a text area that shows text set at the start of the program. This kind of
functionality is not very impressive, so let's take it one step further: add the ability to download the source code
of a web page and display it in the text area. (To download the source code of a web page use `HttpClient`.)

- Add a `JTextField` named "UrlTextField" to the window
- Add a `JButton` (named "RunButton") for initiating the download and displaying the source code in the text area.
- Rename the `JTextArea` to "HtmlTextArea".

### Stage 3: [Titles](https://hyperskill.org/projects/59/stages/319/implement)

#### _Summary_

Practice working with regular expressions: teach the program to parse and display the page's title.

##### _Description_

### Stage 4: [Collect them all](https://hyperskill.org/projects/59/stages/320/implement)

#### _Summary_

With the help of collections and multidimensional arrays, enable the program to scan pages, collect links and their
titles and display them altogether.

##### _Description_

### Stage 5: [Save the results](https://hyperskill.org/projects/59/stages/321/implement)

#### _Summary_

You've got to save what you found: program you Crawler to store links in a file and save them.

##### _Description_

### Stage 6: [Complicated matters](https://hyperskill.org/projects/59/stages/322/implement)

#### _Summary_

Practice the art of multithreading: small tasks have to be handled simultaneously! Make the parameters of depth and
time regulatable in the program.

##### _Description_
