###Contents:
* Python notebook for pre-processing of the data - "Data Pre-Proccesing.ipynb"
* Data directory - containing the original corrupted data file. It is also the directory of the processed data file
* Java source code - Main class + helper classes
* Dockerfile for building the image
* Tasks solutions are included for convenience:
    * "data/targets.csv" is the "clean" file after running the notebook
    * "output/targets_groups.txt" is the output file containing the groups of similar entries

###How To:
* If does'nt exist, create a directory named "data" in the same directory level as the python notebook
* Run python notebook
* Run the code:
     * Using an IDE able to run java source code. You should have a directory named "output" at the same source code level
     * By running the jar file in "out/artifacts/MedAware_jar/MedAware.jar" using the command:` java -jar <path/to/jar> -i <path/to/input/file> -o <path/to/output/file>`
     * By downloading the dockerized version:
        * Pull the relevant docker:
        `docker pull saartk/medtask:v1`
        * Run the docker using the following command:
        `docker run -it --rm --name <some-name> 
        -v <path/to/input/directory>:<path/to/custom/input/directory> 
        -e input="<path/to/custom/input/file>" 
        -v <path/to/output/directory>:<path/to/custom/input/directory> 
        -e output="<path/to/custom/input/file>" saartk/medtask:v1`       
        * You can run a shorter version using the default paths using the following:
            * Your input file should be "data/targets.csv"
            * `docker run -it --rm --name <some-name> 
                -v <path/to/input/directory>:/data 
                -v <path/to/input/directory>:/output 
                 saartk/medtask:v1`

##Assumptions:
* The data contains duplicate entries (i.e date,time and action are all identical) - duplicates will remain in the "clean" file
* The output file will not include duplicates in its groups
* Entries without non-duplicate similar sentences will not appear in the output file
 
 
                 