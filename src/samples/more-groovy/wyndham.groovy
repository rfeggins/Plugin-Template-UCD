def fileLocation = "${p:fileLocation}"
def listOfScripts = []
def baseDirectory = "${p:baseDirectory}"
def tempDirectory = "${p:tempDirectory}"
def component = "${p:componentName}"
def version = "${p:versionNumber}"
def udclient = "${p:udclient_home}"

File scriptsToDeploy = new File(fileLocation)

	/*********************
	* Find the file      *
	*********************/

if (!scriptsToDeploy.exists()) {
     println "File does not exist"

} else {
	println "Found the file!" 
	scriptsToDeploy.eachLine { line ->
     listOfScripts.add(line)
	}
	
	//print the items in the list
	listOfScripts.each { println it } 
	
	println "Getting ready to create the version!"
	
	/*********************
	* Create the Version *
	*********************/
	
	// build and call the createVersion command
	
	def createVersion = ["$udclient", "-weburl", "https://localhost:8443", "-username", "admin", "-password", "admin", "createVersion", "-component", "$component", "-name", "$version"] as String[]
	
	def proc = createVersion.execute()                 
	proc.waitFor()
	println "return code: ${ proc.exitValue()}"
	println "stdout: ${proc.in.text}"
    println "stderr: ${proc.err.text}"
    
    /*******************************
	* Copy files to temp directory *
	********************************/
	
	//cleanup temp directory before copying files
	
	tempDir = new File("$tempDirectory")
	println "Cleaning the temp directory $tempDirectory"
	tempDir.deleteDir()
	
	
	//create the temp directory
	println "Creating the temp directory $tempDirectory"
	new File("$tempDirectory").mkdir()  
	
	//copy the files to a temp directory directory
	
	println "Attempting to copy the files"
	listOfScripts.each { line ->
     new AntBuilder().copy( file:"$baseDirectory"+line, todir:"$tempDirectory")
	}
	
	/*******************************
	* Add Files to the Version     *
	********************************/
	
	// build and call the addVersionFiles command
	
	def addVersionFiles = ["$udclient", "-weburl", "https://localhost:8443", "-username", "admin", "-password", "admin", "addVersionFiles", "-component", "$component", "-version", "$version", "-base", "$tempDirectory"] as String[]
		
	println "Getting ready to add the files to version $version!"	
	def proc2 = addVersionFiles.execute()                 
	proc2.waitFor()
	println "return code: ${ proc2.exitValue()}"
	println "stdout: ${proc2.in.text}"
    println "stderr: ${proc2.err.text}"
}

                        
 
 