package exceptions

class NotValidP7MFileContentException(message: String? = "Content not valid for a p7m file!") : Exception(message){
}