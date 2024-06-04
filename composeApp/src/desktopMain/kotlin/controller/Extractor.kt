package controller

import exceptions.NotValidP7MFileContentException
import exceptions.NotValidP7MFileNameException
import org.bouncycastle.cms.CMSException
import org.bouncycastle.cms.CMSProcessableByteArray
import org.bouncycastle.cms.CMSSignedData
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths


class Extractor() {

    companion object {
        fun extract(file: File): File {
            var newFileName = file.name
            val totalSignatures: Int
            var countSignatures = 0

            while (newFileName.regionMatches(newFileName.length - 4, ".p7m", 0, 4, true)) {
                newFileName = newFileName.substring(0, newFileName.length - 4)
                countSignatures++
            }

            totalSignatures = countSignatures

            println("Signatures found: $totalSignatures")

            if (totalSignatures == 0) {
                throw NotValidP7MFileNameException("The file name is not valid for a p7m file: no .p7m extension found (${file.name})")
            }

            var p7mContent = Files.readAllBytes(Paths.get(file.absolutePath))

            while (countSignatures > 0) {
                val signedData: CMSSignedData = try {
                    CMSSignedData(p7mContent)
                } catch (e: CMSException) {
                    if (countSignatures == totalSignatures) {
                        throw NotValidP7MFileContentException("The file content is not valid for a p7m file: ${e.message}")
                    } else {
                        throw NotValidP7MFileNameException("The file name is not valid for a p7m file: more than enough .p7m extensions found ($totalSignatures expected, ${file.name})")
                    }
                }
                val originalContent = (signedData.signedContent as CMSProcessableByteArray).content as ByteArray
                p7mContent = originalContent
                countSignatures--
            }

            try {
                CMSSignedData(p7mContent)
                throw NotValidP7MFileContentException("The file content is not valid for a p7m file: not enough .p7m extensions found (${file.name})")
            } catch (_: CMSException) {

            }

            val newFile = Files.write(Paths.get(newFileName), p7mContent).toFile()

            return newFile
        }
    }

}