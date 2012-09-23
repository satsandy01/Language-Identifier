A Language Identifier that finds out the language in which a document is written in.
It can identify from the following languages:

	+ English
	+ Spanish
	+ French
	+ Italian
	+ German
	+ Latin
	+ Lithuanian
	+ Hungarian
	+ Danish
	+ Bulgarian

Corpus used: http://corpora.uni-leipzig.de/

Allows the user to decide to what extent he/she wants accuracy by allowing the user to provide training size as input. By default, it is 5000 words/language. These words are selected on the basis of their frequencies in the corpus data.
To run the Identifier:
	
	1. Open Terminal and Navigate to this directory.
	2. Compile
		javac *.java
	3. Execute
		java Identifier
	   Alternatively, to specify training size to 10000 (for example),
		java Identifier 10000

The program starts with training on the languages and then prompts to provide the name of a test document. Once you provide it, the Identifier will score and predict its language.

To add another language:
	
	1. Download the corpus for that language.
	2. Find the file within the corpus which contains the triplet
		wordId-word-frequency
	3. Copy that file to the resources directory within this directory.
	4. Open the file resources/language.txt
	5. Add an entry in this format:
		"<language_name><tab>resources/<name_of_words-file_copied>".
