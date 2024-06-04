package controller

import java.io.File

class Extractor() {

    companion object {
        fun extract(file: File) : File {
            var newFileName = file.name
            var countSignatures = 0

            while(newFileName.lowercase().endsWith(".p7m")) {
                newFileName = newFileName.substring(0, newFileName.length - 4)
                countSignatures++
            }

            val newFile = File(newFileName)



            return newFile
        }
    }

}