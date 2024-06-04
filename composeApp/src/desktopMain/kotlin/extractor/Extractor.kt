package extractor

import java.io.File

abstract class Extractor {

    abstract fun extract(file: File, destination: File?): File?
    abstract fun extractDirectory(source: File, destination: File?): List<File?>
}