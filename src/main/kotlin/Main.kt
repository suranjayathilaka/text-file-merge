import java.io.File
import java.util.*

fun main() {
    // Replace "/path/to/input/folder" with the actual path of your input folder
    val inputFolderPath = "c:/data-files/sequence/status/merged/suppress-merged"
    val outputFilePath = "c:/data-files/sequence/status/suppress-merged-all.txt"

    val inputFolder = File(inputFolderPath)
    val outputFile = File(outputFilePath)

    if (!inputFolder.isDirectory) {
        println("Input path is not a directory.")
        return
    }

    // Check if the output file already exists, and delete it to start fresh
    if (outputFile.exists()) {
        outputFile.delete()
    }
    // OX175490.1|05-AUG-2022|903647974|PRJEB53990|
//    OX132621.2
    val set = TreeSet<String>();
    inputFolder.listFiles()?.forEach { inputFile ->
        // Check if the item is a file (not a subdirectory)
        if (inputFile.isFile) {
            // Read lines from each file and write to the output file
            inputFile.bufferedReader().use { input ->
                input.forEachLine { line ->
                    if (!line.isNullOrBlank()) {
//                        println("$line")
                        if (line.contains("|")) {
                            val acc = line.split("|").get(0);
                            set.add(acc)
                        } else {
                            set.add(line)
                        }
                    }
                }
            }
        }
    }
    println("size:${set.size}")

    // Writing set contents to the output file
    File(outputFilePath).bufferedWriter().use { writer ->
        set.forEach { element ->
            writer.write("$element\n")
        }
    }


    println("Process complete. Output written to: $outputFilePath")
}
