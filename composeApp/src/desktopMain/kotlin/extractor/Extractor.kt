package extractor

import java.io.File

abstract class Extractor {

    abstract fun extract(file: File, destination: File?): File
}