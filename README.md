# IdentificationEliminator
 
## Usage: 

 1. Initialize an instance first.
 
 IdentificationEliminator IE = new IdentificationEliminator();
 
 2. Call eliminateIndentif(String) method to get processed text back
 
 text = IE.eliminateIndentif(text);
 
## Regular Expressions:
 'regex/' directory is where regular experessions are stored. Multiple regular expressions can be stored in one file using the following fotmat:
  1. Each row has two columns seperated by a tab ('\t'), first column is regular expression name you would like to call it, the second column is the regular expression itself. 
  
  REGEX_NAME[\tab]REGEX
  
  2. A row starting with '#' is considered as a comment, and will not be treated as regular expressions.
  
 

