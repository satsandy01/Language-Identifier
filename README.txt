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

Allows the user to decide to what extent he/she wants accuracy by allowing the user to provide training size as input. By default, it is 5000 words/language. These words are selected on the basis of their frequencies in the corpus data.
To run the Identifier,
	
	1. Open Terminal and Navigate to this directory.
	2. Execute
		java Identifier
		Alternatively, to specify training size to 10000,for example,
		java Identifier 10000
