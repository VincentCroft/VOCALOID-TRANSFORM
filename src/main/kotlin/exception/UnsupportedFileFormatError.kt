package exception

import ui.strings.Strings
import ui.strings.string

open class UnsupportedFileFormatError(override val message: String) : Exception(message)

class UnsupportedStandardMidiError : UnsupportedFileFormatError(
    string(Strings.UnsupportedStandardMidiError),
)

class UnsupportedLegacyPpsfError : UnsupportedFileFormatError(
    string(Strings.UnsupportedLegacyPpsfError),
)
