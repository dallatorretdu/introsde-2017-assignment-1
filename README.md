Giulio Dallatorre
giulio.dallatorre@studenti.unitn.it

PROJECT:
The classes are well-separated so not to have interconnections between the modules that would require a lot of duplicated code. It's also organized in packages
.people has the main functions
.generated are the generated classes from the XSD schema
.evaluation contains the evaluation points, 1 for each class
.creator contains utility classes to slim the usage of the scripts and application
I did My best to follow the EXtreme programming tecniques, providing readable names and refactoring everything in small functions
I did developed the project using ~TDD and I am including the TEST src folder.

People Marshaller marshals a people Object into a file or stream (facilitating unitary testing)
People Unmarshaller reads a people.xsd-TYPE XML document and unmarshals it in the People class or console output. This class needs some refactoring, as it could also be static.
XPath navigator functions is quite challenging to read, every sub-fucntion uses the getPeopleCustomXpathResource function trough executeGenericPeopleXPath. This class manages the exceptions.
JSONMarshaller just gets a people object and marshals it into a file or a string.

TESTS: i did written the test mostly before writing the code, as TDD suggests, and in order not to use the filesystem the superclass PeopleTestSuperclass contains several methods to generate a sample XML document in-memory and a custom "StringableOutputStream" which can be printed to console.

EXECUTION:
In order to execute the evaluation sequence it is enough to run 
	ant execute.evaluation
the script will retrive ivy and all the needed dependency. After that it will launch every single evaluation script (1-6)

If for some reason it's needed to run the tests, the command
	ant execute.TEST 
will compile the project including JUNIT and execute all the test cases.

	ant clean
will remove the build files and the auto-generated files left over from the evaluation

	ant generateSampleXML
will generate a sample xml file containing 20 persons

All the other ant commands are automatically called from the commands listed above.
